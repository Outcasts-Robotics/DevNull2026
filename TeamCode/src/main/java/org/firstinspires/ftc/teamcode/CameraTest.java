package org.firstinspires.ftc.teamcode;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Vision;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;

@TeleOp (name="Camera Test")
public class CameraTest extends OpMode {
    private Vision vision;

    private double lastLoopTime;
    private double loopTimeAvgMs;

    TelemetryManager panelsTelemetry;

    @Override
    public void init() {
        vision = new Vision(hardwareMap, telemetry);
        vision.initialize();

        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

    }

    @Override
    public void start() {
        lastLoopTime = getRuntime();
    }

    @Override
    public void loop() {
        trackLoopLatency();

        panelsTelemetry.addData("aprilTags", vision.getDetections(0));
        telemetry.update();
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
