package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagClusterDetection;

import java.util.ArrayList;
import java.util.List;

public class Vision {
    AprilTagProcessor aprilTag;
    VisionPortal visionPortal;
    Vision(HardwareMap hardwareMap){
        aprilTag = AprilTagProcessor.easyCreateWithDefaults();
        visionPortal = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, "Webcam 1"), aprilTag);
    }


    public List<AprilTagDetection> getDetections(double yawSpeed){
        ArrayList<AprilTagDetection> dectList = new ArrayList<AprilTagDetection>();
        if (yawSpeed < 10) {
            dectList = new ArrayList<AprilTagDetection>(aprilTag.getDetections());
            dectList.removeIf(detection -> !(detection instanceof AprilTagSingleDetection) || ((AprilTagSingleDetection) detection).metadata == null);
        }
        return dectList;
    }
}
