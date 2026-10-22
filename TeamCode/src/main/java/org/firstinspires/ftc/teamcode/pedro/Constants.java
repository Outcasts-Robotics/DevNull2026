package org.firstinspires.ftc.teamcode.pedro;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.follower.Follower;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
//This file will be used for all Pedro and general constants. After tuning, we will merge Configurables to this file
// everything not pedro should be public static final.
public class Constants {

    public static PinpointConfig localizerConfig = new PinpointConfig(
            c -> {
                c.name.set("pinpoint");
                c.xPodOffset.set(1.9685);
                c.yPodOffset.set(-1.1811);
                c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
                c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
                c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD); // change to goBILDA_4_BAR_POD if needed
            }
    );

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                
                c.headingDriveRatio.set(0.5);
                c.brakeAtEnd.set(true);
            }
    );

    public static MecanumConfig drivetrainConfig = new MecanumConfig(
            c -> {
                c.frontLeftName.set("frontLeft");
                c.backLeftName.set("backLeft");
                c.frontRightName.set("frontRight");
                c.backRightName.set("backRight");
                c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
                c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
            }
    );
    public static Follower create(HardwareMap h) {
        PinpointLocalizer localizer = new PinpointLocalizer(h, localizerConfig);
        Mecanum drivetrain = new Mecanum(h, drivetrainConfig);
        Foresight foresight = new Foresight(foresightConfig);

        return new Follower(localizer, drivetrain, foresight);
    }

    public static int TURRET_TICKS_PER_REV = 145;

    //
    // Focals (pixels) - Fx: 906.25 Fy: 906.25
    // Optical center - Cx: 636.238 Cy: 339.804

    public static final String  shooterName = "shooter";
    public static final String  turretName = "turret";

    public static final String transferName = "transfer";
}