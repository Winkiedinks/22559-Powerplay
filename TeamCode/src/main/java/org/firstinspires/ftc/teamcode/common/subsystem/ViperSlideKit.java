package org.firstinspires.ftc.teamcode.common.subsystem;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.config.Constants;

public class ViperSlideKit {
    //Create Variables
    DcMotor liftMotor = Constants.liftMotor;
    String encoder;
    double encoderValue;
    Gamepad gamepad1 = Constants.gamepad;

    public void runOpMode() {
        //Get current lift position
        encoderValue = liftMotor.getCurrentPosition();

        // If lift is in between max and min, any input goes
        if (encoderValue > -4000 && encoderValue < 1) {
            liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            liftMotor.setPower(gamepad1.right_trigger - gamepad1.left_trigger);}
        //If lift is at max, only down input goes
        else if (encoderValue < -4000) {
            liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            liftMotor.setPower(-gamepad1.left_trigger);
        }
        //If lift is at min, only up input goes
        else if (encoderValue > 1) {
            liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            liftMotor.setPower(gamepad1.right_trigger);
        }
    }

    //Add encoder position to FTCDashboard
    public void telemetry(FtcDashboard dashboard, TelemetryPacket packet) {
        encoder = String.valueOf(encoderValue);
        packet.addLine("Encoder:" + encoder);
    }
}
