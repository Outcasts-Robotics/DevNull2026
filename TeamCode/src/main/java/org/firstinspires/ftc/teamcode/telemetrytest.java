package org.firstinspires.ftc.teamcode;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp
public class telemetrytest extends OpMode {
    TelemetryManager panelsTelemetry;
    @Override
    public void init() {
        telemetry.addLine("Init");
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        panelsTelemetry.addLine("Init panels");
    }

    @Override
    public void loop() {
        telemetry.addData("hello ", "2");
        panelsTelemetry.addData("hello from panels", "k");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
