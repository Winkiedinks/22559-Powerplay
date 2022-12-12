package org.firstinspires.ftc.teamcode.config;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

public class Constants {
    public static HardwareMap hardwareMap;
    public static Gamepad gamepad;
    public static FtcDashboard dashboard;
    public static TelemetryPacket packet;

    public static IMU imu = hardwareMap.get(IMU.class, "imu");
    RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
    RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;
    RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

    public static DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
    public static DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRight");
    public static DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeft");
    public static DcMotor backRightMotor = hardwareMap.dcMotor.get("backRight");
    public static DcMotor liftMotor = hardwareMap.dcMotor.get("liftMotor");

    public void init(HardwareMap hardwareMap, Gamepad gamepad) {
        this.hardwareMap = hardwareMap;
        this.gamepad = gamepad;
        this.dashboard = FtcDashboard.getInstance();
        this.packet = new TelemetryPacket();

        imu.initialize(new IMU.Parameters(orientationOnRobot));

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

}
