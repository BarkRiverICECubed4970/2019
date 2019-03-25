/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.vision.VisionPipeline;
import edu.wpi.first.vision.VisionThread;

import org.opencv.core.Mat;
import org.opencv.core.*;
import org.opencv.imgproc.*;

import visionpipeline.GripPipeline;

/*
   JSON format:
   {
       "team": <team number>,
       "ntmode": <"client" or "server", "client" if unspecified>
       "cameras": [
           {
               "name": <camera name>
               "path": <path, e.g. "/dev/video0">
               "pixel format": <"MJPEG", "YUYV", etc>   // optional
               "width": <video mode width>              // optional
               "height": <video mode height>            // optional
               "fps": <video mode fps>                  // optional
               "brightness": <percentage brightness>    // optional
               "white balance": <"auto", "hold", value> // optional
               "exposure": <"auto", "hold", value>      // optional
               "properties": [                          // optional
                   {
                       "name": <property name>
                       "value": <property value>
                   }
               ],
               "stream": {                              // optional
                   "properties": [
                       {
                           "name": <stream property name>
                           "value": <stream property value>
                       }
                   ]
               }
           }
       ]
   }
 */

public final class Main {
  private static String configFile = "/boot/frc.json";

  @SuppressWarnings("MemberName")
  public static class CameraConfig {
    public String name;
    public String path;
    public JsonObject config;
    public JsonElement streamConfig;
  }

  public static int team;
  public static boolean server;
  public static List<CameraConfig> cameraConfigs = new ArrayList<>();
  public static UsbCamera camera;
  private static CvSource outputStream;
  private static CvSink cvSink;
  private static GripPipeline pipe = new GripPipeline();
  private static Mat source = new Mat();

  private static NetworkTableEntry centerXEntry;
  private static NetworkTableEntry centerXHeight;
  private static NetworkTableEntry centerXValid;
  private static NetworkTableEntry exposureEntry;
  private static NetworkTableEntry targetAngleEntry;
  private static double centerX = 0.0;
  private static double centerXTemp = 0.0;
  private static double targetAngle = 0.0;
  private static boolean exposureLow = false;
  private static NetworkTable table;
  private static double numTargets = 0.0;
  private static Object imgLock = new Object();
  private static List<Rect> rectArray = new ArrayList<>();
  private static List<RotatedRect> rotRectArray = new ArrayList<>();
  private static MatOfPoint2f contourMOP2f = new MatOfPoint2f();
  private static Rect r1;
  private static Rect r2;
  private static double targetHeight = 0.0;
  private static boolean targetFound = false;
  private static double rightAngle = 0.0;
  private static double rightIndex = 0.0;
  private static double leftAngle = 0.0;
  private static double leftIndex = 0.0;

  private static final double CENTER_IMAGE = 80.0;

  private Main() {
  }

  /**
   * Report parse error.
   */
  public static void parseError(String str) {
    System.err.println("config error in '" + configFile + "': " + str);
  }

  /**
   * Read single camera configuration.
   */
  public static boolean readCameraConfig(JsonObject config) {
    CameraConfig cam = new CameraConfig();

    // name
    JsonElement nameElement = config.get("name");
    if (nameElement == null) {
      parseError("could not read camera name");
      return false;
    }
    cam.name = nameElement.getAsString();

    // path
    JsonElement pathElement = config.get("path");
    if (pathElement == null) {
      parseError("camera '" + cam.name + "': could not read path");
      return false;
    }
    cam.path = pathElement.getAsString();

    // stream properties
    cam.streamConfig = config.get("stream");

    cam.config = config;

    cameraConfigs.add(cam);
    return true;
  }

  /**
   * Read configuration file.
   */
  @SuppressWarnings("PMD.CyclomaticComplexity")
  public static boolean readConfig() {
    // parse file
    JsonElement top;
    try {
      top = new JsonParser().parse(Files.newBufferedReader(Paths.get(configFile)));
    } catch (IOException ex) {
      System.err.println("could not open '" + configFile + "': " + ex);
      return false;
    }

    // top level must be an object
    if (!top.isJsonObject()) {
      parseError("must be JSON object");
      return false;
    }
    JsonObject obj = top.getAsJsonObject();

    // team number
    JsonElement teamElement = obj.get("team");
    if (teamElement == null) {
      parseError("could not read team number");
      return false;
    }
    team = teamElement.getAsInt();

    // ntmode (optional)
    if (obj.has("ntmode")) {
      String str = obj.get("ntmode").getAsString();
      if ("client".equalsIgnoreCase(str)) {
        server = false;
      } else if ("server".equalsIgnoreCase(str)) {
        server = true;
      } else {
        parseError("could not understand ntmode value '" + str + "'");
      }
    }

    // cameras
    JsonElement camerasElement = obj.get("cameras");
    if (camerasElement == null) {
      parseError("could not read cameras");
      return false;
    }
    JsonArray cameras = camerasElement.getAsJsonArray();
    for (JsonElement camera : cameras) {
      if (!readCameraConfig(camera.getAsJsonObject())) {
        return false;
      }
    }

    return true;
  }

  /**
   * Start running the camera.
   */
  public static VideoSource startCamera(CameraConfig config) {
    System.out.println("Starting camera '" + config.name + "' on " + config.path);
    CameraServer inst = CameraServer.getInstance();
    camera = new UsbCamera(config.name, config.path);
    MjpegServer server = inst.startAutomaticCapture(camera);

    cvSink = inst.getVideo();
    outputStream = inst.putVideo("processed", 320, 240);


    Gson gson = new GsonBuilder().create();

    camera.setConfigJson(gson.toJson(config.config));
    camera.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);

    if (config.streamConfig != null) {
//      server.setConfigJson(gson.toJson(config.streamConfig));
    }

    return camera;
  }

  /**
   * Example pipeline.
   */
  public static class MyPipeline implements VisionPipeline {
    public int val;

    @Override
    public void process(Mat mat) {
      val += 1;
    }
  }

  /**
   * Main.
   */
  public static void main(String... args) {

    if (args.length > 0) {
      configFile = args[0];
    }

    // read configuration
    if (!readConfig()) {
      return;
    }

    // start NetworkTables
    NetworkTableInstance ntinst = NetworkTableInstance.getDefault();
    if (server) {
      System.out.println("Setting up NetworkTables server");
      ntinst.startServer();
    } else {
      System.out.println("Setting up NetworkTables client for team " + team);
      ntinst.startClientTeam(team);
    }
    table = ntinst.getTable("visionTable");
    centerXEntry = table.getEntry("centerX");
    centerXHeight = table.getEntry("centerXHeight");
    centerXValid = table.getEntry("centerXValid");
    exposureEntry = table.getEntry("piCamExposure");
    targetAngleEntry = table.getEntry("targetAngle");

    // start cameras
    List<VideoSource> cameras = new ArrayList<>();
    for (CameraConfig cameraConfig : cameraConfigs) {
      cameras.add(startCamera(cameraConfig));
    }

    // start image processing on camera 0 if present
    if (cameras.size() >= 1) {
  //    VisionThread visionThread = new VisionThread(cameras.get(0),
  //            new MyPipeline(), pipeline -> {
        // do something with pipeline results
 //     });

      VisionThread visionThread = new VisionThread(cameras.get(0),
      pipe, pipeline -> {

	  rectArray.clear();
	  rotRectArray.clear();
  	  rightAngle = 0.0;
  	  leftAngle = 0.0;
  	  rightIndex = 0.0;
  	  leftIndex = 0.0;
          centerXTemp = 0.0;
          centerX = 0.0;
	  targetHeight = 0.0;
	  targetFound = false;
	  targetAngle = 0.0;

          numTargets = pipeline.filterContoursOutput().size();

  	  if (numTargets == 1) {
		pipeline.filterContoursOutput().get(0).convertTo(contourMOP2f, CvType.CV_32F);
		rectArray.add(Imgproc.boundingRect(pipeline.filterContoursOutput().get(0)));
		rotRectArray.add(Imgproc.minAreaRect(contourMOP2f));
		targetAngle = rotRectArray.get(0).angle;

	  }
  	  if (numTargets >= 2) {
		  
            for (int i = 0; i < numTargets; i++)
	    {
		pipeline.filterContoursOutput().get(i).convertTo(contourMOP2f, CvType.CV_32F);
		rectArray.add(Imgproc.boundingRect(pipeline.filterContoursOutput().get(i)));
		rotRectArray.add(Imgproc.minAreaRect(contourMOP2f));
	 
//		System.out.println("angle " + i + ": " + rotRectArray.get(i).angle);

		/* at least on second target */
		if (i > 0)
		{
			rightAngle = rotRectArray.get(i-1).angle;
			rightIndex = rectArray.get(i-1).y;
			leftAngle = rotRectArray.get(i).angle;
			leftIndex = rectArray.get(i).y;
		/*
		 *  Contour arrays are built from top to bottom, so for vision processing,
		 *  place the camera sideways and now we don't need to sort the arrays.
		 *  Now, centerX is actually centerY!
		 */ 
		  if (rotRectArray.get(i-1).angle > rotRectArray.get(i).angle)
		  {
			/* found a potential target pair... which one is closest
			 * to center image */
                        r1 = rectArray.get(i-1);
			r2 = rectArray.get(i);

                        centerXTemp = ((r1.y + r1.height) - r2.y)/2.0 + r2.y;
//		        System.out.println("centerXTemp = " + centerXTemp);

			if (i == 1)
			{
			  /* first target... assume it is closest */
			  centerX = centerXTemp;
			} else if (Math.abs(centerXTemp - CENTER_IMAGE) < Math.abs(centerX - CENTER_IMAGE))
			{
                          // subtract the image center
                          centerX = centerXTemp - CENTER_IMAGE;

			  targetHeight = r1.height;

			  targetFound = true;
			}
		  }
		}
	
	    }

          }

          centerXEntry.setDouble(centerX);
          centerXHeight.setDouble(targetHeight);
          centerXValid.setBoolean(targetFound);
	  targetAngleEntry.setDouble(targetAngle);

            cvSink.grabFrameNoTimeout(source);
	    outputStream.putFrame(pipeline.hslThresholdOutput());

	   // System.out.println("Grip sees " +   + " targets");

	    if (exposureLow == false)
	    {
	      camera.setExposureManual(1);
	      exposureLow = true;
	    }
      });
      visionThread.start();
    }

    // loop forever
    for (;;) {
      try {
        Thread.sleep(1000);
        System.out.println("PICAM found " + numTargets + "contours!");
//        System.out.println("target angle = " + targetAngle);        
        System.out.println("CenterX = " + centerX);        
        System.out.println("targetHeight = " + targetHeight);        
        System.out.println("targetFound = " + targetFound);        
        System.out.println("right angle = " + rightAngle);        
        System.out.println("right index = " + rightIndex);        
        System.out.println("left angle = " + leftAngle);        
        System.out.println("left index = " + leftIndex);        
      } catch (InterruptedException ex) {
        return;
      }
    }
  }
}
