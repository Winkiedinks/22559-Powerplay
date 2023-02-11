package org.firstinspires.ftc.teamcode.config;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

public class Constants {
    public static HardwareMap hardwareMap;
    public static Gamepad gamepad;
    public static IMU imu;
    static RevHubOrientationOnRobot.LogoFacingDirection logoDirection;
    static RevHubOrientationOnRobot.UsbFacingDirection usbDirection;
    static RevHubOrientationOnRobot orientationOnRobot;
    public static DcMotor frontRightMotor;
    public static DcMotor frontLeftMotor;
    public static DcMotor backLeftMotor;
    public static DcMotor backRightMotor;
    public static DcMotor liftMotor;

    public static Servo clawServo;

    public static void init(HardwareMap hardwareMap, Gamepad gamepad) {
        Constants.hardwareMap = hardwareMap;
        Constants.gamepad = gamepad;

        imu = hardwareMap.get(IMU.class, "imu");
        logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
        usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;
        orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot)); frontRightMotor = hardwareMap.dcMotor.get("frontRight");

        backLeftMotor = hardwareMap.dcMotor.get("backLeft");
        backRightMotor = hardwareMap.dcMotor.get("backRight");
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        liftMotor = hardwareMap.dcMotor.get("liftMotor");

	    liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        clawServo = hardwareMap.servo.get("clawServo");
    }

}
