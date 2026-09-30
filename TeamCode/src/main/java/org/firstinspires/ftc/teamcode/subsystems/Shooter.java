package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Trackables;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

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
        ArrayList<AprilTagSingleDetection> detections = vision.getDetections(yawSpeed);
        if(!detections.isEmpty()){
            for (AprilTagSingleDetection detection : detections){
                switch(trackables.side){
                    case RED:
                        if(detection.metadata.id >= 30 && detection.metadata.id <= 37){
                            setTurretAngle(detection.ftcPose.yaw);
                            return;
                        }
                        break;
                    case BLUE:
                        if(detection.metadata.id >= 38 && detection.metadata.id <= 45) {
                            setTurretAngle(detection.ftcPose.yaw);
                            return;
                        }
                        break;

                }
            }

        }
    }
}
