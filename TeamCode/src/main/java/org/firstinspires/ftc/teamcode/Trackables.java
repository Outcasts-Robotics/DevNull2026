package org.firstinspires.ftc.teamcode;
// This will be used to keep track of per-match constants and things that changes in the course of a match
// Everything in here should be public static, no methods

public class Trackables {
    // needs to be initialized in OpMode
    public static Side side = Side.RED;
    public static ShooterState state = ShooterState.DEFAULT;
    public static RobotTaskState robotState = RobotTaskState.DEFAULT;



    public enum Side{
        RED,
        BLUE
    }

    public enum RobotTaskState{
        DEFAULT,
        SHOOT,
        CLAW_UP,
        PARK,
        END
    }

    public enum ShooterState{
        DEFAULT,
        REV_UP,
        SHOOT
    }
    public Trackables(Side startingSide){
        Trackables.side = startingSide;
    }
}

