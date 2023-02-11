package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.IntakeClaw;
import org.firstinspires.ftc.teamcode.common.RobotOrientedMecanum;
import org.firstinspires.ftc.teamcode.common.ViperSlideKit;
import org.firstinspires.ftc.teamcode.config.Constants;

@TeleOp
public class MainOpMode extends LinearOpMode {

    public void runOpMode(){
        //Create subsystems, drivebase, and constants
        //FieldOrientedMecanum mecanum = new FieldOrientedMecanum();
        //ViperSlideKit lift = new ViperSlideKit();
        //IntakeClaw intake = new IntakeClaw();
        //Constants constants = new Constants();

        //TelemetryPacket packet;
        //FtcDashboard dashboard;

        //Initalize
        Constants.init(hardwareMap, gamepad1);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            RobotOrientedMecanum.runOpMode();
            ViperSlideKit.runOpMode();
            IntakeClaw.runOpMode();

            //Grab telemetry from subsystems
            double encoderValue = ViperSlideKit.encoderValue;
            telemetry.addData("Encoder:", encoderValue);
            telemetry.update();
        }
    }
}