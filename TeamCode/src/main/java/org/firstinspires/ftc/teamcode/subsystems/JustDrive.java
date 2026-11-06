package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class JustDrive extends OpMode {
    Drive drive;
    @Override
    public void init() {
        drive = new Drive(hardwareMap, () -> 0);
    }

    @Override
    public void loop() {
        drive.control(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, false);
    }
}

