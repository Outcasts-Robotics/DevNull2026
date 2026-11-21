package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.pedro.Constants;

public class SingleShooterTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        DcMotorEx testMotor = hardwareMap.get(DcMotorEx.class, Constants.shooterName);

        testMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        testMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized. Ready to test.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                testMotor.setPower(0.4);
            } else if (gamepad1.b) {
                testMotor.setPower(-0.4);
            } else {
                testMotor.setPower(0.0);
            }

            telemetry.addData("Power", testMotor.getPower());
            telemetry.addData("Encoder Position", testMotor.getCurrentPosition());
            telemetry.addData("Velocity (ticks/sec)", testMotor.getVelocity());
            telemetry.update();
        }
    }
}
