package org.firstinspires.ftc.teamcode;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(group = "TeleOp", name= "OpMode")
public class TeleOp extends OpMode {
    Follower follower;
    Shooter launcher;
    TelemetryManager panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);

    }

    @Override
    public void loop() {
        follower.update();

        DrivePowers powers = ManualDrive.fieldCentric(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, follower.pose().heading());
        follower.manual(powers);
//        launcher = new Shooter();

//        if (gamepad1.a) {
//            launcher.
//        } else {
//            launcher.stop();
//        }
    }


}
