package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Intake;


@TeleOp
public class IntakeTest extends OpMode {
    Intake intake;

    @Override
    public void init()
    {
        intake = new Intake(hardwareMap);
        telemetry.addLine("Intake Testing Ready");
        telemetry.update();
    }

    @Override
    public void loop()
    {
        double currentPower = 0.0;
        if (gamepad1.right_trigger > 0.1)
        {
            currentPower = gamepad1.right_trigger;
            intake.intake(currentPower);
        } else if (gamepad1.left_trigger > 0.1) {
            currentPower = gamepad1.left_trigger;
            intake.outtake(currentPower);
        } else
        {
            intake.stop();
        }

        intake.update();

        telemetry.addData("Intended Intake Power", "%.2f", currentPower);
        telemetry.addData("Actual Intake Power", "%.2f", intake.getPower());
        telemetry.update();
    }
}
