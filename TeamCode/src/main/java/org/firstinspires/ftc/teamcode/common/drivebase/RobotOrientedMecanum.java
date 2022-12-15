package org.firstinspires.ftc.teamcode.common.drivebase;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.config.Constants;

public class RobotOrientedMecanum{
    //Create variables
    static DcMotor frontLeftMotor = Constants.frontLeftMotor;
    static DcMotor backLeftMotor = Constants.backLeftMotor;
    static DcMotor frontRightMotor = Constants.frontRightMotor;
    static DcMotor backRightMotor = Constants.backRightMotor;
    static Gamepad gamepad = Constants.gamepad;

    public static void runOpMode() {
        //Gamepad readings
        double y = gamepad.left_stick_y;
        double x = -gamepad.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = -gamepad.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio, but only when
        // at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);
    }
}
