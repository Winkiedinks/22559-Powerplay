package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

@TeleOp
public class ServoOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo leftServo = hardwareMap.servo.get("leftServo");
        Servo rightServo = hardwareMap.servo.get("rightServo");

        leftServo.setDirection(Servo.Direction.REVERSE);
        //rightServo.setDirection(Servo.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            if (gamepad1.a) {
                leftServo.setPosition(.65);
                rightServo.setPosition(.75);
            }
            if (gamepad1.b) {
                leftServo.setPosition(0);
                rightServo.setPosition(0);
            }

            FtcDashboard dashboard = FtcDashboard.getInstance();
            TelemetryPacket packet = new TelemetryPacket();
            String s4 = String.valueOf(leftServo.getPosition());
            packet.addLine("Left Servo Position:" + s4);
            String s5 = String.valueOf(rightServo.getPosition());
            packet.addLine("Right Servo Position:" + s5);
            dashboard.sendTelemetryPacket(packet);
        }
    }
}