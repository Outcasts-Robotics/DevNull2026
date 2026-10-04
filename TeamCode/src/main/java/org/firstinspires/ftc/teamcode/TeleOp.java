package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Vision;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")
public class TeleOp extends OpMode {
    private Drive drivetrain;
    private Vision vision;

    private double lastLoopTime;
    private double loopTimeAvgMs;

    @Override
    public void init() {
        drivetrain = new Drive(hardwareMap, () -> 0);
        vision = new Vision(hardwareMap, telemetry);
        vision.initialize();
    }

    @Override
    public void start() {
        lastLoopTime = getRuntime();
    }

    @Override
    public void loop() {
        trackLoopLatency();

        drivetrain.control(-gamepad1.left_stick_y, -gamepad1.left_stick_x, gamepad1.right_stick_x, false);
        telemetry.addData("aprilTags", vision.getDetections(0));
    }

    @Override
    public void stop() {
        vision.close();
    }

    private void trackLoopLatency() {
        double rt = getRuntime();
        loopTimeAvgMs = (loopTimeAvgMs * 9 + (rt - lastLoopTime) * 1000) / 10;
        lastLoopTime = rt;
        telemetry.addData("loop latency (ms)", Math.round(loopTimeAvgMs * 10) / 10.0);
    }
}
