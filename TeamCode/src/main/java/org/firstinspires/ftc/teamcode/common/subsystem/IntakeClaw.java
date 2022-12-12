package org.firstinspires.ftc.teamcode.common.subsystem;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import com.qualcomm.robotcore.hardware.Servo;
;
import org.firstinspires.ftc.teamcode.config.Constants;

public class IntakeClaw {
    Servo leftServo = Constants.leftServo;
    Servo rightServo = Constants.rightServo;

    public void runOpMode(){

    }

    //Add servo positions to dashboard
    public void telemetry(TelemetryPacket packet) {
        String leftPos = String.valueOf(leftServo.getPosition());
        packet.addLine("Left Servo Position:" + leftPos);
        String rightPos = String.valueOf(rightServo.getPosition());
        packet.addLine("Right Servo Position:" + rightPos);
    }
}