package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Transfer;
@TeleOp
public class TransferTest extends OpMode {
    Transfer transfer;
    boolean aButtonPrev = false;
    @Override
    public void init() {
        transfer = new Transfer(hardwareMap);
    }

    public void loop() {
        transfer.update();

        for (String[] command : transfer.commands) {
            telemetry.addData("Command", command[0]);
            telemetry.addData("Parameters", command[1]);
        }
        telemetry.update();

        if(gamepad1.a && !aButtonPrev){
            transfer.runTransfer(1, .5);
        }
        aButtonPrev = gamepad1.a;
    }
}
