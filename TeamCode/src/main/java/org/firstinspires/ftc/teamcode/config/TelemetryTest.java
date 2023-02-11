package org.firstinspires.ftc.teamcode.config;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TelemetryTest extends LinearOpMode {

    public void runOpMode() throws InterruptedException {
        waitForStart();

        if (isStopRequested()) return;

        telemetry.addLine("test successful!");
        telemetry.update();
    }
}