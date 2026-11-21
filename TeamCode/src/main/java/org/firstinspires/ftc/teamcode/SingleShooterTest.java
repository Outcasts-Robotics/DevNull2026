package org.firstinspires.ftc.teamcode;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.bylazar.telemetry.PanelsTelemetry;
import org.firstinspires.ftc.teamcode.pedro.Constants;

public class SingleShooterTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        TelemetryManager panelsTelem = PanelsTelemetry.INSTANCE.getTelemetry();
        DcMotorEx testMotor = hardwareMap.get(DcMotorEx.class, Constants.shooterName);
        testMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        testMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        testMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        telemetry.addData("Status", "Initialized. Ready to test.");
        telemetry.update();

        waitForStart();


        while (opModeIsActive()) {
            testMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,new PIDFCoefficients(Configuration.SHOOTER_KP, Configuration.SHOOTER_KI , Configuration.SHOOTER_KD , Configuration.SHOOTER_KF));
            double tgtVel = 0;
            if (gamepad1.a) {
                tgtVel = Constants.SixkMotorTPS * .4;
            } else if (gamepad1.b) {
                tgtVel = Constants.SixkMotorTPS * -.4;;
            } else {
                tgtVel = 0;
            }

            testMotor.setVelocity(tgtVel);


            telemetry.addData("Encoder Position", testMotor.getCurrentPosition());
            telemetry.addData("target vel ", tgtVel);

            telemetry.addData("Velocity (ticks/sec)", testMotor.getVelocity());
            panelsTelem.addData("target vel ", tgtVel);

            panelsTelem.addData("Velocity (ticks/sec)", testMotor.getVelocity());
            telemetry.update();
            panelsTelem.update();
        }
    }
}
