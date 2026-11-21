package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedro.Constants;

public class Intake {

    private final DcMotorEx intakeMotor;
    private double targetPower = 0.0;
    private double lastPower = Double.NaN;

    public Intake(HardwareMap hardwareMap) {

        intakeMotor = hardwareMap.get(DcMotorEx.class, Constants.intakeName);
        intakeMotor.setDirection(DcMotorEx.Direction.FORWARD);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

    }

    public void intake(double power) {
        targetPower = Math.abs(power);
    }

    public void outtake(double power) {
        targetPower = -Math.abs(power);
    }

    public void stop() {
        targetPower = 0.0;
    }

    public double getPower() {
        return targetPower;
    }

    public void update() {

        if (Double.isNaN(lastPower) || Math.abs(targetPower - lastPower) > 1e-4) {
            intakeMotor.setPower(targetPower);
            lastPower = targetPower;
        }
    }
}
