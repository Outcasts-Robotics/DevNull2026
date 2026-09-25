package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.function.DoubleSupplier;

public class Drive {
     DcMotor frontLeft;
     DcMotor frontRight;
     DcMotor backLeft;
     DcMotor backRight;
    private final DoubleSupplier yawInRadProvider;

    Drive(HardwareMap hardwareMap, DoubleSupplier yawInRadProvider){
        this.frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        this.frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        this.backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        this.backRight = hardwareMap.get(DcMotor.class, "backRight");
        this.yawInRadProvider = yawInRadProvider;

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void control(double forward, double strafe, double rotate , boolean fieldCentric){
        if(!fieldCentric){
            frontLeft.setPower(forward + strafe + rotate);
            frontRight.setPower(forward - strafe - rotate);
            backLeft.setPower(forward - strafe + rotate);
            backRight.setPower(forward + strafe - rotate);
        } else {
            double theta = Math.atan2(forward, strafe);
            double r = Math.hypot(strafe, forward);
            theta = AngleUnit.normalizeRadians(theta - yawInRadProvider.getAsDouble());
            double newForward = r * Math.sin(theta);
            double newRight = r * Math.cos(theta);
            control(newForward, newRight, rotate, true);
        }
    }
}
