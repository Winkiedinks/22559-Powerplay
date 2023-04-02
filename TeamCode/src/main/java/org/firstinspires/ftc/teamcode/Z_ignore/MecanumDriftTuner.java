package org.firstinspires.ftc.teamcode.Z_ignore;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.config.Constants;

@Autonomous(group = "tuner")
public class MecanumDriftTuner extends LinearOpMode {
    //Create variables
    IMU imu = Constants.imu;
    DcMotor frontLeftMotor = Constants.frontLeftMotor;
    DcMotor backLeftMotor = Constants.backLeftMotor;
    DcMotor frontRightMotor = Constants.frontRightMotor;
    DcMotor backRightMotor = Constants.backRightMotor;
    double theoHeading;
    double actHeading;
    double rx;

    @Override
    public void runOpMode() throws InterruptedException {
        Constants constants = new Constants();
        constants.init(hardwareMap, gamepad1);

        waitForStart();

        if (isStopRequested()) return;
        imu.resetYaw();

        while (opModeIsActive()) {
            actHeading = -imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

            if (actHeading < 360){
                rx = -.1;
                theoHeading = theoHeading + rx;
            }

            double denominator = Math.max(Math.abs(rx), 1);
            double frontLeftPower = (rx) / denominator;
            double backLeftPower = (rx) / denominator;
            double frontRightPower = (-rx) / denominator;
            double backRightPower = (-rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);


        }
    }
}
