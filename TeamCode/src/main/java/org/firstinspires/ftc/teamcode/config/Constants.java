package org.firstinspires.ftc.teamcode.config;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

public class Constants {
    //hardwareMap is the base for connecting to all hardware components
    public static HardwareMap hardwareMap;

    //Logitech Gamepad
    public static Gamepad gamepad;

    //All of the IMU Variables needed
    public static IMU imu;
    static RevHubOrientationOnRobot.LogoFacingDirection logoDirection;
    static RevHubOrientationOnRobot.UsbFacingDirection usbDirection;
    static RevHubOrientationOnRobot orientationOnRobot;

    //Drivebase/Mecanum Motors
    public static DcMotor frontRightMotor;
    public static DcMotor frontLeftMotor;
    public static DcMotor backLeftMotor;
    public static DcMotor backRightMotor;

    //Viper Slides Motor
    public static DcMotor liftMotor;

    //Loony Claw Servo
    public static Servo clawServo;

    //Function that intializes all needed variables, imu, motors, and servo
    public static void init(HardwareMap hardwareMap, Gamepad gamepad) {
        //Grab hardwareMap from MainOpMode to be able to initialize imu, motors, and servo
        Constants.hardwareMap = hardwareMap;
        //Grab the gamepad from MainOpMode
        Constants.gamepad = gamepad;

        //Initialize IMU. Check REV IMU documentation for more information.
        //https://ftc-docs.firstinspires.org/en/latest/programming_resources/imu/imu.html
        imu = hardwareMap.get(IMU.class, "imu");
        logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
        usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;
        orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot));

        //Initialize drivebase motors and slides motor
        frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        backLeftMotor = hardwareMap.dcMotor.get("backLeft");
        backRightMotor = hardwareMap.dcMotor.get("backRight");
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        liftMotor = hardwareMap.dcMotor.get("liftMotor");

        //Set the slides motor to brake mode, which makes the motor stop in place instead of being able to move
	    liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //Intialize the slides motor encoder and reset it to 0
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Reverse the front right and back right drivebase motors so they go the same direction as the left motors
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //Initalize the claw servo
        clawServo = hardwareMap.servo.get("clawServo");
    }

}
