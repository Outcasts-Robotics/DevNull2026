package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.utils.Angle;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@Autonomous
public class RedSidePreloadParkAuto  extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap);
        Shooter shooter = new Shooter(hardwareMap);
        Follower follower = Constants.create(hardwareMap);
        follower.setPose(new Pose(60, 8, 90 * Math.PI/ (180)));
        waitForStart();


        drive.drive(.3, 0 , 0);

        sleep(500);
        drive.stop();
        follower.update();
        shooter.setTurretAngle(Math.atan2(follower.pose().y() -  50, follower.pose().x() - 60));


        sleep(200);
        shooter.triggerLaunchSequence();
        double thistime = 0;
        while( thistime < 1000){
            shooter.updateLauncherState();

            thistime++;
        }

        drive.drive(.3, 0, 0);
        sleep(1000);

        drive.stop();
        drive.drive(0, -.3, 0);

        sleep(500);
        drive.stop();




    }
}
