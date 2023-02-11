package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.config.Constants;

public class ViperSlideKit {
    //Create Variables
    //DcMotor liftMotor = Constants.liftMotor;
    public static double encoderValue;
    static Gamepad gamepad1 = Constants.gamepad;
    static DcMotor liftMotor = Constants.liftMotor;
    static String encoder;

    public static void runOpMode() {
        //Get current lift position
        encoderValue = liftMotor.getCurrentPosition();

        // If lift is in between max and min, any input goes
        if (encoderValue > -3300 && encoderValue < -1) {
            liftMotor.setPower(gamepad1.left_trigger - gamepad1.right_trigger);}
        //If lift is at max, only down input goes
        else if (encoderValue < -3300) {
            liftMotor.setPower(gamepad1.left_trigger);
        }
        //If lift is at min, only up input goes
        else if (encoderValue > -1) {
            liftMotor.setPower(-gamepad1.right_trigger);
        }

        if (gamepad1.x){
            liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            encoderValue = 0;
        }
    }

    //Add encoder position to FTCDashboard
    public static void telemetry() {


    }
}
