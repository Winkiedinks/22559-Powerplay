package org.firstinspires.ftc.teamcode.config;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.RobotOrientedMecanum;
import org.firstinspires.ftc.teamcode.config.Constants;

@TeleOp
public class TestOpMode extends LinearOpMode {

    public void runOpMode() throws InterruptedException {
        //Initalize
        Constants.init(hardwareMap, gamepad1);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            if (gamepad1.b){
                RobotOrientedMecanum.right();
                Thread.sleep(250);
                RobotOrientedMecanum.stop();
            }
            if (gamepad1.a){
                RobotOrientedMecanum.backward();
                Thread.sleep(250);
                RobotOrientedMecanum.stop();
            }
            if (gamepad1.x){
                RobotOrientedMecanum.left();
                Thread.sleep(250);
                RobotOrientedMecanum.stop();
            }
            if (gamepad1.y){
                RobotOrientedMecanum.forward();
                Thread.sleep(250);
                RobotOrientedMecanum.stop();
            }
        }
    }
}