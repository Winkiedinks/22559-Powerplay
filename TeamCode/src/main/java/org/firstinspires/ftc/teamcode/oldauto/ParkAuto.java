package org.firstinspires.ftc.teamcode.oldauto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.RobotOrientedMecanum;
import org.firstinspires.ftc.teamcode.config.Constants;

@Autonomous
public class ParkAuto extends LinearOpMode {

    public void runOpMode() throws InterruptedException {

        //Initalize
        Constants.init(hardwareMap, gamepad1);
        telemetry.addLine("make sure robot is oriented to the left!!!");
        telemetry.update();
        waitForStart();

        if (isStopRequested()) return;

        RobotOrientedMecanum.backward();
        Thread.sleep(500);

    }
}