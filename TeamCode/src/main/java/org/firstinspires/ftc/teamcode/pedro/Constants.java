package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.follower.Follower;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.pedropathing.localization.localizers.PinpointLocalizer;
import org.firstinspires.ftc.teamcode.GoBildaPinpointDriver;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.pedropathing.localization.localizers.PinpointLocalizer;

public class Constants {
    public static Follower create(HardwareMap h) {
        // return new Follower(Drivetrain, Localizer, Foresight);

        GoBildaPinpointDriver pinpoint = h.get(GoBildaPinpointDriver.class, "pinpoint");
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD); // idk which one we're using, change based on which one is in use
        pinpoint.setEncoderDirection(
                GoBildaPinpointDriver.EncoderDirection.FORWARD, // this is x direction
                GoBildaPinpointDriver.EncoderDirection.REVERSED // this is y direction
        );

        pinpoint.setOffsets(50.0, -30.0, DistanceUnit.MM); // will change these to be less sucky with our robot design
        pinpoint.resetPosAndIMU();

        PinpointLocalizer localizer = new PinpointLocalizer(pinpoint);

        return new Follower(h, driveConfig, localizer);
    }

    public static MecanumConfig driveConfig = new MecanumConfig(
            c -> {
                c.frontLeftName.set("left_front");
                c.backLeftName.set("left_back");
                c.frontRightName.set("right_front");
                c.backRightName.set("right_back");
                c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
                c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
            }
    );
}