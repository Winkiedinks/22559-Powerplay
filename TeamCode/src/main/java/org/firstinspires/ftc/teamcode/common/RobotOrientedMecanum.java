package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.config.Constants;

public class RobotOrientedMecanum{
    //Grab gamepad from Constants file
    static Gamepad gamepad = Constants.gamepad;

    //Grab all drivebase motors from Constants file
    static DcMotor frontLeftMotor = Constants.frontLeftMotor;
    static DcMotor backLeftMotor = Constants.backLeftMotor;
    static DcMotor frontRightMotor = Constants.frontRightMotor;
    static DcMotor backRightMotor = Constants.backRightMotor;

    public static void runOpMode() {
        //I'm going to be completely honest, this doesn't make sense to me. It's a bunch of complex and fancy math that I copy-pasted.
        //https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html (Make sure to copy the Robot Oriented code)

        //Gamepad readings
        //Dividing numbers are to slow down the robot, make sure to keep those
        double x = gamepad.left_stick_y/2;
        double y = gamepad.left_stick_x * 1.1/2; // Counteract imperfect strafing
        double rx = -gamepad.right_stick_x/3;

        // Fancy Math
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        //Run motors based on the fancy math
        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);
    }

    public static void stop() {
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
    }

    public static void backward() {
        frontLeftMotor.setPower(1);
        backLeftMotor.setPower(-1);
        frontRightMotor.setPower(-1);
        backRightMotor.setPower(1);
    }

    public static void left() {
        frontLeftMotor.setPower(-1);
        backLeftMotor.setPower(-1);
        frontRightMotor.setPower(-1);
        backRightMotor.setPower(-1);
    }

    public static void right(){
        frontLeftMotor.setPower(1);
        backLeftMotor.setPower(1);
        frontRightMotor.setPower(1);
        backRightMotor.setPower(1);
    }

    public static void forward(){
        frontLeftMotor.setPower(-1);
        backLeftMotor.setPower(1);
        frontRightMotor.setPower(1);
        backRightMotor.setPower(-1);
    }
}
