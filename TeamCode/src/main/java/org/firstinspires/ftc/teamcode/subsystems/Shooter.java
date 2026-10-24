package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Trackables;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;

public class Shooter {
    // TODO: Implement Shooter
    private final DcMotorEx shooterMotor;
    private final DcMotorEx turretMotor;
    private final ElapsedTime launcherTimer = new ElapsedTime();
    private final Trackables trackables;
    private static final double TARGET_VELOCITY = 2000;
    private static final double VELOCITY_TOLERANCE = 100;

    public Shooter(HardwareMap hardwareMap, Trackables trackables) {
        shooterMotor = hardwareMap.get(DcMotorEx.class, Constants.shooterName);
        turretMotor = hardwareMap.get(DcMotorEx.class, Constants.turretName);

        turretMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turretMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turretMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        this.trackables = trackables;
    }

    public void updateLauncherState() {
        switch (Trackables.state) {

            case DEFAULT:
                shooterMotor.setVelocity(0);
                break;

            case REV_UP:
                shooterMotor.setVelocity(TARGET_VELOCITY);

                if ((Math.abs(shooterMotor.getVelocity() - TARGET_VELOCITY)) < VELOCITY_TOLERANCE) {
                    Trackables.state = Trackables.ShooterState.SHOOT;
                    launcherTimer.reset();
                }
                break;

            case SHOOT:
                shooterMotor.setVelocity(TARGET_VELOCITY);

                if (launcherTimer.seconds() >= 1.0) {
                    Trackables.state = Trackables.ShooterState.DEFAULT;
                }
                break;

        }
    }

    public void triggerLaunchSequence() {
        if (Trackables.state == Trackables.ShooterState.DEFAULT) {
            Trackables.state = Trackables.ShooterState.REV_UP;
        }
    }

    public Trackables.ShooterState getLauncherState() {
        return Trackables.state;
    }

    public void setTurretAngle(double angle) {
        turretMotor.setTargetPosition((int) (angle / Constants.TURRET_TICKS_PER_REV));
    }

    public void trackTag(Vision vision, double yawSpeed) {
        ArrayList<AprilTagDetection> detections = vision.getDetections(yawSpeed);
        if (!detections.isEmpty()) {
            AprilTagDetection detection = detections.get(0);
            setTurretAngle(detection.ftcPose.yaw);
        }
    }
}
