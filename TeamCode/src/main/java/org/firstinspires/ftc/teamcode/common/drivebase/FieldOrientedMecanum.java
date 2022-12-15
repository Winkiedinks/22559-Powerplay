package org.firstinspires.ftc.teamcode.common.drivebase;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.config.Constants;

public class FieldOrientedMecanum{
    //Create variables
    static IMU imu = Constants.imu;
    static DcMotor frontLeftMotor = Constants.frontLeftMotor;
    static DcMotor frontRightMotor = Constants.frontRightMotor;
    static DcMotor backLeftMotor = Constants.backLeftMotor;
    static DcMotor backRightMotor = Constants.backRightMotor;
    static Gamepad gamepad = Constants.gamepad;

    public static void runOpMode() {
            //Gamepad readings
            double y = gamepad.left_stick_y;
            double x = -gamepad.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = -gamepad.right_stick_x;

            // Get inverse IMU heading (rotation)
            double botHeading = -imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            //Math I can't be bothered to understand
            double rotX = x * Math.cos(botHeading) - y * Math.sin(botHeading);
            double rotY = x * Math.sin(botHeading) + y * Math.cos(botHeading);

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);
        }
}
