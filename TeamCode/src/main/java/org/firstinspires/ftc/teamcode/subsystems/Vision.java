package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import java.util.ArrayList;

public class Vision {
    AprilTagProcessor aprilTag;
    VisionPortal visionPortal;
    Vision(HardwareMap hardwareMap){
        aprilTag = AprilTagProcessor.easyCreateWithDefaults();
        visionPortal = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, "Webcam 1"), aprilTag);
    }


    public ArrayList<AprilTagSingleDetection> getDetections(double yawSpeed){
        ArrayList<AprilTagDetection> dectList = new ArrayList<AprilTagDetection>();
        if (yawSpeed < 10) {
            dectList = new ArrayList<AprilTagDetection>(aprilTag.getDetections());
            dectList.removeIf(detection -> !(detection instanceof AprilTagSingleDetection) || ((AprilTagSingleDetection) detection).metadata == null);
        }
        return dectList;
    }

    public double calculateAimToAprilTag(AprilTagDetection apriltag, Pose2D pose){
        double x = apriltag.ftcPose.x - pose.getX(DistanceUnit.INCH);
        double y = apriltag.ftcPose.y - pose.getY(DistanceUnit.INCH);
        return Math.atan2(y, x);
    }

    public double calculateAimToPose(Pose2D target, Pose2D start){
        double x = target.getX(DistanceUnit.INCH) - start.getX(DistanceUnit.INCH);
        double y = target.getY(DistanceUnit.INCH) - start.getY(DistanceUnit.INCH);
        return Math.atan2(y, x);
    }
}
