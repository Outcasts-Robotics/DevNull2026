package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Trackables;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;

public class Shooter {
    // TODO: Implement Shooter
    private final DcMotor shooterMotor;
    private final DcMotor turretMotor;

    Trackables trackables;

    public Shooter(HardwareMap hardwareMap, Trackables trackables) {
        shooterMotor = hardwareMap.get(DcMotor.class, "shooter");
        turretMotor = hardwareMap.get(DcMotor.class, "turret");

        turretMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        turretMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turretMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        this.trackables = trackables;
    }
     public void setTurretAngle(double angle){
        turretMotor.setTargetPosition((int) (angle / Constants.TURRET_TICKS_PER_REV));
     }
    public void trackTag(Vision vision, double yawSpeed) {
        ArrayList<AprilTagDetection> detections = vision.getDetections(yawSpeed);
        if(!detections.isEmpty()){
            AprilTagDetection detection = detections.get(0);
            setTurretAngle(detection.ftcPose.yaw);
        }
    }
}
