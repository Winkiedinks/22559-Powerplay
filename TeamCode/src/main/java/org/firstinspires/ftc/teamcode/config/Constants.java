package org.firstinspires.ftc.teamcode.config;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Const;

public class Constants {
    public static HardwareMap hardwareMap;
    public static Gamepad gamepad;
    public static FtcDashboard dashboard;
    public static IMU imu;
    static RevHubOrientationOnRobot.LogoFacingDirection logoDirection;
    static RevHubOrientationOnRobot.UsbFacingDirection usbDirection;
    static RevHubOrientationOnRobot orientationOnRobot;
    public static DcMotor frontRightMotor;public static DcMotor frontLeftMotor;public static DcMotor backLeftMotor; public static DcMotor backRightMotor;
    public static DcMotor liftMotor;

    //public static Servo leftServo = hardwareMap.servo.get("leftServo");
    //public static Servo rightServo = hardwareMap.servo.get("rightServo");

    public static void init(HardwareMap hardwareMap, Gamepad gamepad) {
        Constants.hardwareMap = hardwareMap;
        Constants.gamepad = gamepad;
        Constants.dashboard = FtcDashboard.getInstance();

        imu = hardwareMap.get(IMU.class, "imu");
        logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;
        orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot)); frontRightMotor = hardwareMap.dcMotor.get("frontRight");
         backLeftMotor = hardwareMap.dcMotor.get("backLeft");
         backRightMotor = hardwareMap.dcMotor.get("backRight");
         frontLeftMotor = hardwareMap.dcMotor.get("frontLeft"); liftMotor = hardwareMap.dcMotor.get("liftMotor");
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //leftServo.setDirection(Servo.Direction.REVERSE);
    }

}
