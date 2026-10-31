package org.firstinspires.ftc.teamcode;


import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.qualcomm.robotcore.robot.RobotState;




import com.qualcomm.robotcore.eventloop.opmode.OpMode;


import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;




@com.qualcomm.robotcore.eventloop.opmode.TeleOp(group = "TeleOp", name= "OpMode")
public class TeleOp extends OpMode {
    Follower follower;
    Shooter launcher;
    TelemetryManager panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
    Trackables trackables;


    private Trackables.Side startingSide = Trackables.Side.BLUE;
    private RobotState currentRobotState = RobotState.UNKNOWN;


    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
        trackables = new Trackables(startingSide);
        launcher = new Shooter(hardwareMap, trackables);
        currentRobotState = RobotState.INIT;


    }


    @Override
    public void init_loop() {
        currentRobotState = RobotState.NOT_STARTED;


        if (gamepad1.x) {
            startingSide = Trackables.Side.BLUE;
            Trackables.side = Trackables.Side.BLUE;
        } else if (gamepad1.b) {
            startingSide = Trackables.Side.RED;
            Trackables.side = Trackables.Side.RED;
        }


        telemetry.addData("Selected Starting Side", Trackables.side);
        telemetry.update();
    }


    public void start() {
        currentRobotState = RobotState.RUNNING;
        Trackables.robotState = Trackables.RobotTaskState.DEFAULT;
    }

    @Override
    public void loop() {
        follower.update();

        DrivePowers powers = ManualDrive.fieldCentric(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                -gamepad1.right_stick_x,
                follower.pose().heading()
        );
        follower.manual(powers);

        if (gamepad1.a) {
            Trackables.robotState = Trackables.RobotTaskState.SHOOT;
            launcher.triggerLaunchSequence();
        }
        launcher.updateLauncherState();


        if (Trackables.robotState == Trackables.RobotTaskState.SHOOT && Trackables.state == Trackables.ShooterState.DEFAULT)
            Trackables.robotState = Trackables.RobotTaskState.DEFAULT;


    }
    @Override
    public void stop() {
        currentRobotState = RobotState.STOPPED;
    }
}

