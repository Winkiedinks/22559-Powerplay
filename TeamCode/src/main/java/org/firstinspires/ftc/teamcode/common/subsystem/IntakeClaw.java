package org.firstinspires.ftc.teamcode.common.subsystem;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
;
import org.firstinspires.ftc.teamcode.config.Constants;

public class IntakeClaw {
    static Servo leftServo = Constants.leftServo;
    static Servo rightServo = Constants.rightServo;
    static Gamepad gamepad1 = Constants.gamepad;

    public static void runOpMode(){
        if (gamepad1.a){
            leftServo.setPosition(.9);
            rightServo.setPosition(1);
        }
        if (gamepad1.b){
            leftServo.setPosition(.5);
            rightServo.setPosition(.2);
        }
    }

    //Add servo positions to dashboard
    public void telemetry(TelemetryPacket packet) {
        //String leftPos = String.valueOf(leftServo.getPosition());
        //packet.addLine("Left Servo Position:" + leftPos);
        //String rightPos = String.valueOf(rightServo.getPosition());
        //packet.addLine("Right Servo Position:" + rightPos);
    }
}