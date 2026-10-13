package org.firstinspires.ftc.teamcode.subsystems;

import android.util.Size;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.GainControl;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Vision {
    private final WebcamName camera;
    private final Telemetry telemetry;
    private final AprilTagProcessor aprilTag;
    private final VisionPortal visionPortal;

    public Vision(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        telemetry.addLine("Initializing Vision...");
        telemetry.update();

        aprilTag = new AprilTagProcessor.Builder()
                .setLensIntrinsics(906.25, 906.25, 636.238, 339.804)
                .setDrawAxes(true)
                .setDrawTagOutline(true)
                .build();

        try {
            camera = hardwareMap.get(WebcamName.class, "Webcam 1");
            telemetry.addLine("Camera 'Webcam 1' found in hardware map");
            telemetry.update();
        } catch (Exception e) {
            telemetry.addLine("ERROR: Camera 'Webcam 1' not found in hardware map!");
            telemetry.addLine("Check camera configuration in Robot Controller app");
            telemetry.update();
            throw new RuntimeException("Camera not found: Webcam 1", e);
        }

        visionPortal = new VisionPortal.Builder()
                .setCamera(camera)
                .setCameraResolution(new Size(1280, 800))
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .addProcessor(aprilTag)
                .enableLiveView(true)
                .build();

        telemetry.addLine("VisionPortal created");
        telemetry.update();
    }

    /**
     * Initializing logic to ensure camera and processing are fully working
     */
    public void initialize() {
        telemetry.addLine("Current camera state: " + visionPortal.getCameraState());
        telemetry.update();

        if (visionPortal.getCameraState() != VisionPortal.CameraState.STREAMING) {
            telemetry.addLine("Waiting for camera state to start streaming...");
            telemetry.update();

            int timeoutSeconds = 10;
            long startTime = System.currentTimeMillis();
            while (visionPortal.getCameraState() != VisionPortal.CameraState.STREAMING) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (System.currentTimeMillis() - startTime > timeoutSeconds * 1000) {
                    telemetry.addLine("ERROR: Camera failed to start streaming after " + timeoutSeconds + " seconds");
                    telemetry.addLine("Final camera state: " + visionPortal.getCameraState());
                    telemetry.update();
                    return;
                }
            }
            telemetry.addLine("Camera is ready");
            telemetry.update();
        }

        // Wait for AprilTag processor to start processing frames
        telemetry.addLine("Waiting for AprilTag processor to initialize...");
        telemetry.update();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        telemetry.addLine("AprilTag processor initialized");
        telemetry.update();

         try {
             ExposureControl exposureControl = getExposureControl();
             telemetry.addLine("Setting exposure");
             telemetry.update();
             if (exposureControl.getMode() != ExposureControl.Mode.Manual) {
                 exposureControl.setMode(ExposureControl.Mode.Manual);
             }
             exposureControl.setExposure(14, TimeUnit.MILLISECONDS);
             GainControl gainControl = getGainControl();
             gainControl.setGain(5);
             telemetry.addLine("Exposure and gain set successfully");
             telemetry.update();
         } catch (Exception e) {
             telemetry.addLine("WARNING: Could not set exposure/gain: " + e.getMessage());
             telemetry.update();
         }
    }

    /**
     * Update loop call hook
     */
    public void update() {
        // no op
    }

    public ArrayList<AprilTagDetection> getDetections(double yawSpeed) {
        ArrayList<AprilTagDetection> dectList = new ArrayList<AprilTagDetection>();
        if (yawSpeed < 10) {
            dectList = aprilTag.getDetections();
            telemetry.addLine("Detections: " + dectList);
            telemetry.update();
        }
        return dectList;
    }

    public double calculateAimToAprilTag(AprilTagDetection apriltag, Pose2D pose) {
        double x = apriltag.ftcPose.x - pose.getX(DistanceUnit.INCH);
        double y = apriltag.ftcPose.y - pose.getY(DistanceUnit.INCH);
        return Math.atan2(y, x);
    }

    public double calculateAimToPose(Pose2D target, Pose2D start) {
        double x = target.getX(DistanceUnit.INCH) - start.getX(DistanceUnit.INCH);
        double y = target.getY(DistanceUnit.INCH) - start.getY(DistanceUnit.INCH);
        return Math.atan2(y, x);
    }

    public void close() {
        if (visionPortal != null) {
            visionPortal.close();
        }
    }

    /**
     * Enables or disable the AprilTag processor. Turn off to save processing resources.
     */
    public void setProcessingEnabled(boolean enabled) {
        visionPortal.setProcessorEnabled(aprilTag, enabled);
    }

    private ExposureControl getExposureControl() {
        return visionPortal.getCameraControl(ExposureControl.class);
    }

    private GainControl getGainControl() {
        return visionPortal.getCameraControl(GainControl.class);
    }

}
