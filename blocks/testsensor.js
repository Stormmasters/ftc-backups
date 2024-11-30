// IDENTIFIERS_USED=clawLeftAsServo,clawRightAsServo,clawWristAsServo,colorSensorAsREVColorRangeSensor,ControlHubAsServoController,gamepad1,gamepad2

var hue, gain, color, normalizedColors, saturation, value;

/**
 * Describe this function...
 */
function claw() {
  if (gamepad2.getLeftTrigger() > 0) {
    clawLeftAsServo.setPosition(0.25);
  }
  if (gamepad2.getLeftBumper()) {
    clawLeftAsServo.setPosition(0.5);
  }
  if (gamepad2.getRightTrigger() > 0) {
    clawRightAsServo.setPosition(0.5);
  }
  if (gamepad2.getRightBumper()) {
    clawRightAsServo.setPosition(0.25);
  }
  if (gamepad2.getA()) {
    clawWristAsServo.setPosition(0);
  }
  if (gamepad2.getB()) {
    clawWristAsServo.setPosition(1);
  }
}

/**
 * This function is executed when this OpMode is selected from the Driver Station.
 */
function runOpMode() {
  clawInit();
  gain = 2;
  telemetryAddTextData('Color Distance Example', 'Press start to continue...');
  telemetry.update();
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      claw();
      cplor();
      telemetry.addTextData('Dist to tgt (cm)', String(colorSensorAsREVColorRangeSensor.getDistance("CM")));
      telemetry.addTextData('Light detected', String(colorSensorAsREVColorRangeSensor.getLightDetected()));
      if (gamepad1.getA()) {
        gain = (typeof gain == 'number' ? gain : 0) + 0.005;
      } else if (gamepad1.getB() && gain >= 1.005) {
        gain = (typeof gain == 'number' ? gain : 0) + -0.005;
      }
      colorSensorAsREVColorRangeSensor.setGain(gain);
      telemetry.addTextData('Gain', String(colorSensorAsREVColorRangeSensor.getGain()));
      normalizedColors = JSON.parse(colorSensorAsREVColorRangeSensor.getNormalizedColors());
      telemetry.addTextData('Red', String(miscAccess.roundDecimal(startBlockExecution("NormalizedColors.Red") ? endBlockExecution(normalizedColors.Red) : 0, 3)));
      telemetry.addTextData('Green', String(miscAccess.roundDecimal(startBlockExecution("NormalizedColors.Green") ? endBlockExecution(normalizedColors.Green) : 0, 3)));
      telemetry.addTextData('Blue', String(miscAccess.roundDecimal(startBlockExecution("NormalizedColors.Blue") ? endBlockExecution(normalizedColors.Blue) : 0, 3)));
      color = startBlockExecution("NormalizedColors.Color") ? endBlockExecution(normalizedColors.Color) : 0;
      hue = colorAccess.getHue(color);
      saturation = colorAccess.getSaturation(color);
      value = colorAccess.getValue(color);
      telemetry.addTextData('Hue', String(miscAccess.roundDecimal(hue, 0)));
      telemetry.addTextData('Saturation', String(miscAccess.roundDecimal(saturation, 3)));
      telemetry.addTextData('Value', String(miscAccess.roundDecimal(value, 3)));
      telemetry.addTextData('Alpha', String(miscAccess.roundDecimal(startBlockExecution("NormalizedColors.Alpha") ? endBlockExecution(normalizedColors.Alpha) : 0, 3)));
      colorAccess.showColor(color);
      if (hue < 30) {
        telemetryAddTextData('Color', 'Red');
      } else if (hue < 60) {
        telemetryAddTextData('Color', 'Orange');
      } else if (hue < 90) {
        telemetryAddTextData('Color', 'Yellow');
      } else if (hue < 150) {
        telemetryAddTextData('Color', 'Green');
      } else if (hue < 225) {
        telemetryAddTextData('Color', 'Blue');
      } else if (hue < 350) {
        telemetryAddTextData('Color', 'purple');
      } else {
        telemetryAddTextData('Color', 'Red');
      }
      if (saturation < 0.2) {
        telemetryAddTextData('Check Sat', 'Is surface white?');
      }
      telemetry.update();
      if (value < 0.16) {
        telemetryAddTextData('Check Val', 'Is surface black?');
      }
      // Get the current time in milliseconds. The value returned represents the number of milliseconds since midnight, January 1, 1970 UTC.
      telemetryAddTextData('currentTimeMillis', systemAccess.currentTimeMillis());
    }
    colorAccess.showColor(colorAccess.textToColor('white'));
  }
}

/**
 * Describe this function...
 */
function clawInit() {
  ControlHubAsServoController.pwmEnable();
}

/**
 * Describe this function...
 */
function cplor() {
  hue = colorAccess.getHue(color);
  if (hue < 30) {
    telemetryAddTextData('Color', 'Red');
  } else if (hue < 60) {
    telemetryAddTextData('Color', 'Orange');
  } else if (hue < 90) {
    telemetryAddTextData('Color', 'Yellow');
  } else if (hue < 150) {
    telemetryAddTextData('Color', 'Green');
  } else if (hue < 225) {
    telemetryAddTextData('Color', 'Blue');
  } else if (hue < 350) {
    telemetryAddTextData('Color', 'purple');
  } else {
    telemetryAddTextData('Color', 'Red');
  }
}
