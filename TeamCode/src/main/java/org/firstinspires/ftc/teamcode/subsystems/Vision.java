package org.firstinspires.ftc.teamcode.subsystems;

import android.util.Size;

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
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Vision {
    private final WebcamName camera;
    private final Telemetry telemetry;
    private final AprilTagProcessor aprilTag;
    private final VisionPortal visionPortal;

    public Vision(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        aprilTag = new AprilTagProcessor.Builder()
                .setLensIntrinsics(898.706, 898.706, 646.798, 349.201)
                .setDrawAxes(true)
                .setDrawTagOutline(true)
                .build();
        camera = hardwareMap.get(WebcamName.class, "Webcam 1");
        visionPortal = new VisionPortal.Builder()
                .setCamera(camera)
                .setCameraResolution(new Size(800, 600))
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .addProcessor(aprilTag)
                .enableLiveView(true)
                .build();
    }

    /**
     * Initializing logic to ensure camera and processing are fully working
     */
    public void initialize() {
        if (visionPortal.getCameraState() != VisionPortal.CameraState.STREAMING) {
            telemetry.addLine("Waiting for camera state to start streaming");
            telemetry.update();
            while (visionPortal.getCameraState() != VisionPortal.CameraState.STREAMING) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            telemetry.addLine("Camera is ready");
            telemetry.update();
        }

        ExposureControl exposureControl = getExposureControl();
        telemetry.addLine("Setting exposure");
        telemetry.update();
        if (exposureControl.getMode() != ExposureControl.Mode.Manual) {
            exposureControl.setMode(ExposureControl.Mode.Manual);
        }
        exposureControl.setExposure(14, TimeUnit.MILLISECONDS);
        GainControl gainControl = getGainControl();
        gainControl.setGain(2);
    }

    /**
     * Update loop call hook
     */
    public void update() {
        // no op
    }

    public ArrayList<AprilTagSingleDetection> getDetections(double yawSpeed) {
        ArrayList<AprilTagDetection> dectList = new ArrayList<AprilTagDetection>();
        if (yawSpeed < 10) {
            dectList = new ArrayList<AprilTagDetection>(aprilTag.getDetections());
            dectList.removeIf(detection -> !(detection instanceof AprilTagSingleDetection) || ((AprilTagSingleDetection) detection).metadata == null);
        }

        ArrayList<AprilTagSingleDetection> newdectList = new ArrayList<>();
        for (AprilTagDetection detection : dectList) {
            newdectList.add((AprilTagSingleDetection) detection);
        }
        return newdectList;
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
