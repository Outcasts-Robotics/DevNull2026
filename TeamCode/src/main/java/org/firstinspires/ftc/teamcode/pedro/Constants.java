package org.firstinspires.ftc.teamcode.pedro;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

//This file will be used for all Pedro and general constants. After tuning, we will merge Configurables to this file
// everything not pedro should be public static final.
public class Constants {

    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(-5.246554397222564);
        c.yPodOffset.set(1.3668797710749108);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.17068472859431372);
                Controller secondaryTranslationalForward = Controller.proportional(0.06306344643565379);
                Controller primaryTranslationalLateral = Controller.proportional(0.22659498350322962);
                Controller secondaryTranslationalLateral = Controller.proportional(0.0837207916749726);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.012222046397902281));
                c.brake.set(Controller.proportionalFeedforward(0.010388739438216938));

                c.headingFeedback.set(Controller.proportional(3.111069823415102));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.0455400911202309, 0.006148878732018796));

                c.linearBrakeCoefficients.set(Matrix.diag(0.08229015116093262, 0.0780964232718792));
                c.quadraticBrakeCoefficients.set(Matrix.diag(9.427171038521077E-4, 0.0011365766900322542));

                c.maxAchievableForwardVelocity.set(60.60480775810508);
                c.maxAchievableStrafeVelocity.set(49.93451951623172);
                c.naturalForwardDeceleration.set(26.3689998467984);
                c.naturalStrafeDeceleration.set(35.06266913886677);
            }
    );

    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("frontLeft");
        c.frontRightName.set("frontRight");
        c.backLeftName.set("backLeft");
        c.backRightName.set("backRight");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });
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