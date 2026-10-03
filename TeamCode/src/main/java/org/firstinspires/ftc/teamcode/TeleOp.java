package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Vision;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode {
    Drive drivetrain;
    Vision vision;
    public void init() {
         drivetrain = new Drive(hardwareMap, ()-> 0);
         vision = new Vision(hardwareMap);
    }
    public void loop() {

        drivetrain.control(gamepad1.left_stick_y , -gamepad1.left_stick_x , gamepad1.right_stick_x, false);
        telemetry.addData("aprilTags", vision.getDetections(0));
        telemetry.update();
    }
}
