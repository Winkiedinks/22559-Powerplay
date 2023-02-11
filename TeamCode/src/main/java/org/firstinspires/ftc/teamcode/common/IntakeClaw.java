package org.firstinspires.ftc.teamcode.common;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

;import org.firstinspires.ftc.teamcode.config.Constants;

public class IntakeClaw {
    static Servo clawServo = Constants.clawServo;
    static Gamepad gamepad1 = Constants.gamepad;

    public static void runOpMode(){
        if (gamepad1.a){
            clawServo.setPosition(.5);
        }
        if (gamepad1.b){
            clawServo.setPosition(.74);
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