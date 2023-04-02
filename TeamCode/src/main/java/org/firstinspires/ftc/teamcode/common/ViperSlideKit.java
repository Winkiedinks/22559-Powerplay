package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.config.Constants;

public class ViperSlideKit {
    //Grab gamepad from Constants file
    static Gamepad gamepad = Constants.gamepad;
    //Grab slides motor from Constants file
    static DcMotor liftMotor = Constants.liftMotor;
    //Variable for the encoder value from the slidees motor
    public static double encoderValue;

    public static void runOpMode() {
        //Set encoderValue to the current encoder position of the slides motor
        encoderValue = liftMotor.getCurrentPosition();

        // If lift is in between max and min, any input goes
        if (encoderValue > -3300 && encoderValue < -1) {
            liftMotor.setPower(gamepad.left_trigger - gamepad.right_trigger);}
        //If lift is at max, only down input works
        else if (encoderValue < -3300) {
            liftMotor.setPower(gamepad.left_trigger);
        }
        //If lift is at min, only up input works
        else if (encoderValue > -1) {
            liftMotor.setPower(-gamepad.right_trigger);
        }
        //Reset button for the slides motor's encoder
        if (gamepad.x){
            liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            encoderValue = 0;
        }
    }
}
