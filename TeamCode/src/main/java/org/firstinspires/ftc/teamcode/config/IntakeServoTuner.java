package org.firstinspires.ftc.teamcode.config;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(group = "tuner")
public class IntakeServoTuner extends LinearOpMode{
    /*//Create variables
    Servo leftServo = Constants.leftServo;
    Servo rightServo = Constants.rightServo;
    FtcDashboard dashboard = Constants.dashboard;*/
    @Override
    public void runOpMode() throws InterruptedException {
        /*Constants constants = new Constants();
        constants.init(hardwareMap, gamepad1);*/

        waitForStart();

        if (isStopRequested()) return;
        while (opModeIsActive()) {
            /*
            if (gamepad1.a){
                leftServo.setPosition(leftServo.getPosition() + .1);
            }
            if (gamepad1.b){
                rightServo.setPosition(rightServo.getPosition() + .1);
            }

            TelemetryPacket packet = new TelemetryPacket();
            String leftPos = String.valueOf(leftServo.getPosition());
            packet.addLine("Left Servo Position:" + leftPos);
            String rightPos = String.valueOf(rightServo.getPosition());
            packet.addLine("Right Servo Position:" + rightPos);;
            dashboard.sendTelemetryPacket(packet);*/
        }
    }
}
