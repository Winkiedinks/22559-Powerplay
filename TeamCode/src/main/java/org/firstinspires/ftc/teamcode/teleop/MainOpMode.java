package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.drivebase.FieldOrientedMecanum;
import org.firstinspires.ftc.teamcode.common.subsystem.ViperSlideKit;
import org.firstinspires.ftc.teamcode.config.Constants;

@Config
@TeleOp
public class MainOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //Create subsystems, drivebase, and constants
        FieldOrientedMecanum mecanum = new FieldOrientedMecanum();
        ViperSlideKit lift = new ViperSlideKit();
        Constants constants = new Constants();

        //Initalize
        constants.init(hardwareMap, gamepad1);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            mecanum.runOpMode();
            lift.runOpMode();

            //Create dashboard and packet for telemetry
            FtcDashboard dashboard = FtcDashboard.getInstance();
            TelemetryPacket packet = new TelemetryPacket();

            //Grab telemetry from subsystems
            lift.telemetry(dashboard, packet);

            dashboard.sendTelemetryPacket(packet);
        }
    }
}