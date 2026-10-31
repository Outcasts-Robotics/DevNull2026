package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.pedro.Constants;

import java.util.ArrayList;

public class Transfer {
    DcMotorEx transferMotor;
    ElapsedTime time;

    public ArrayList<String[]> commands = new ArrayList<>(0);
    boolean isRunningCommand = false;
    boolean toggleBool = false;
    public Transfer(HardwareMap hardwareMap) {
        transferMotor = hardwareMap.get(DcMotorEx.class, Constants.transferName);
        transferMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        transferMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        time = new ElapsedTime();
        time.reset();

    }
    public void runTransfer(int seconds, double power){
        String[] timeCommand = { "timeRun", String.valueOf(seconds), String.valueOf(power)};
        commands.add(timeCommand);
    }

    public void clearCommands(){
        commands.clear();
        isRunningCommand = false;
        toggleBool = false;
        transferMotor.setPower(0);
    }

    public void toggleMotor(double power){
        String[] command = {"toggle", Double.toString(power)};
        commands.add(0,command);
    }

    public void toggleOff(){
        toggleMotor(0);
    }

    public void update(){
        if (commands.isEmpty()) {
            return;
        }
        
        if (toggleBool && !commands.get(0)[0].equals("toggle")) {
            return;
        }
        
        String[] currentCommand;
        currentCommand = commands.get(0);



        switch(currentCommand[0]){
            case "timeRun":
                int seconds = Integer.parseInt(currentCommand[1]);
                if(isRunningCommand){
                    if(time.seconds() > seconds){
                        isRunningCommand = false;
                        commands.remove(0);
                        transferMotor.setPower(0);
                    }
                }else {
                    isRunningCommand = true;
                    double power = Double.parseDouble(currentCommand[2]);
                    transferMotor.setPower(power);
                    time.reset();
                }
                break;
            case "toggle":
                double power = Double.parseDouble(currentCommand[1]);
                transferMotor.setPower(power);
                commands.remove(0);
                if (power == 0) {
                    toggleBool = false;
                } else {
                    toggleBool = true;
                }
                break;
            default:
                break;
        }
    }
}