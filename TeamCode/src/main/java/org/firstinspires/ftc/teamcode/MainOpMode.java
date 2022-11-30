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
public class MainOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("backLeft");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("backRight");
        DcMotor liftMotor = hardwareMap.dcMotor.get("liftMotor");

        Servo leftServo = hardwareMap.servo.get("leftServo");
        Servo rightServo = hardwareMap.servo.get("rightServo");

        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        leftServo.setDirection(Servo.Direction.REVERSE);
        rightServo.setDirection(Servo.Direction.REVERSE);


        IMU imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot));

        double theoBotHeading = 0;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = gamepad1.left_stick_y; // Remember, this is reversed!
            double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = -gamepad1.right_stick_x;

            // Theortical Robot Heading
            theoBotHeading -= gamepad1.right_stick_x;

            // Read inverse IMU heading, as the IMU heading is CW positive
            double actualBotHeading = -imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            double rotX = x * Math.cos(actualBotHeading) - y * Math.sin(actualBotHeading);
            double rotY = x * Math.sin(actualBotHeading) + y * Math.cos(actualBotHeading);

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);

            double a = liftMotor.getCurrentPosition();
            if (a>-4000 && a<1) {
                liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                liftMotor.setPower(gamepad1.right_trigger - gamepad1.left_trigger);}
            else if (a<-4000) {
                liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                liftMotor.setPower(-gamepad1.left_trigger);
            }
            else if (a>1) {
                liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                liftMotor.setPower(gamepad1.right_trigger);
            }

            if (gamepad1.a) {
                leftServo.setPosition(.5);
                rightServo.setPosition(.5);
            }
            if (gamepad1.b) {
                leftServo.setPosition(0);
                rightServo.setPosition(0);
            }


            FtcDashboard dashboard = FtcDashboard.getInstance();
            TelemetryPacket packet = new TelemetryPacket();
            String s1 = String.valueOf(a);
            packet.addLine("Encoder:" + s1);
            String s2 = String.valueOf(theoBotHeading);
            packet.addLine("Theoretical Bot Heading:" + s2);
            String s3 = String.valueOf(actualBotHeading);
            packet.addLine("Actual Bot Heading:" + s3);
            String s4 = String.valueOf(leftServo.getPosition());
            packet.addLine("Left Servo Position:" + s4);
            String s5 = String.valueOf(rightServo.getPosition());
            packet.addLine("Right Servo Position:" + s5);
            dashboard.sendTelemetryPacket(packet);
        }
    }
}