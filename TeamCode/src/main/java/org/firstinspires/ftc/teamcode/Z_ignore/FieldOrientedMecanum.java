package org.firstinspires.ftc.teamcode.Z_ignore;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;

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
            //BE WARNED: THIS DID NOT WORK THE LAST TIME I TESTED IT

            //I'm going to be completely honest, this doesn't make sense to me, even more than the Robot Oriented.
            // It's a bunch of complex and fancy math that I copy-pasted.
            //https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html (Make sure to copy the Field Oriented code)

            //Gamepad readings
            //Dividing numbers are to slow down the robot, make sure to keep those
            double y = gamepad.left_stick_y/2;
            double x = -gamepad.left_stick_x * 1.1/2; // Counteract imperfect strafing
            double rx = -gamepad.right_stick_x/3;

            // Get inverse IMU heading (rotation)
            double botHeading = -imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            //Fancy math
            double rotX = x * Math.cos(botHeading) - y * Math.sin(botHeading);
            double rotY = x * Math.sin(botHeading) + y * Math.cos(botHeading);
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            //Run motors based on fancy math
            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);
        }
}
