package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.common.drivebase.FieldOrientedMecanum;
import org.firstinspires.ftc.teamcode.common.subsystem.ViperSlideKit;

@Config
@TeleOp
public class MainOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //Create subsystems and drivebase
        FieldOrientedMecanum mecanum = new FieldOrientedMecanum();
        ViperSlideKit lift = new ViperSlideKit();

        //Initalize functions
        lift.init();
        mecanum.init();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            mecanum.runOpMode();
            lift.runOpMode();

            //Create dashboard and packet for telemetry
            FtcDashboard dashboard = FtcDashboard.getInstance();
            TelemetryPacket packet = new TelemetryPacket();

            //Grab telemetry from subsystems
            lift.telemtry(dashboard, packet);

            dashboard.sendTelemetryPacket(packet);
        }
    }
}