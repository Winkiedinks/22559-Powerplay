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
        //Initalize
        Constants.init(hardwareMap, gamepad1);

        //Wait for start button to be pressed
        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {
            //Run all of the subsystems
            RobotOrientedMecanum.runOpMode(); //change this to "FieldOrientedMecanum.runOpMode();" to use field oriented mecanum (not recommended)
            ViperSlideKit.runOpMode();
            IntakeClaw.runOpMode();

            //Grab telemetry data from subsystems, add a new line with a caption and value, and update on Driver Station
            double encoderValue = ViperSlideKit.encoderValue;
            telemetry.addData("Encoder:", encoderValue);
            telemetry.update();
        }
    }
}