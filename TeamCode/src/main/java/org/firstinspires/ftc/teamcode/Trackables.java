package org.firstinspires.ftc.teamcode;
// This will be used to keep track of per-match constants and things that changes in the course of a match
// Everything in here should be public static, no methods

public class Trackables {
    // needs to be initialized in OpMode
    public Side side = Side.RED;
    public enum Side{
        RED,
        BLUE
    }
}

