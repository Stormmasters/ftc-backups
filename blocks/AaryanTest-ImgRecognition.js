var vuMarkResult;

/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  vuforiaAccess.initialize_withCameraDirection("", "BACK", true, true, "AXES", 0, 0, 0, 0, 0, 0, true);
  telemetryAddTextData('VuMark Example', 'Press start to continue...');
  telemetry.update();
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    vuforiaAccess.activate();
    while (linearOpMode.opModeIsActive()) {
      vuMarkResult = JSON.parse(vuforiaAccess.track("RELIC"));
      if (vuMarkResult.IsVisible) {
        telemetryAddTextData('VuMark', 'A VuMark is visible.');
        if (vuMarkResult.RelicRecoveryVuMark == "LEFT") {
          telemetryAddTextData('Relic Target', 'Go for the LEFT goal!');
        } else if (vuMarkResult.RelicRecoveryVuMark == "CENTER") {
          telemetryAddTextData('Relic Target', 'Go for the CENTER goal!');
        } else if (vuMarkResult.RelicRecoveryVuMark == "RIGHT") {
          telemetryAddTextData('Relic Target', 'Go for the RIGHT goal!');
        } else {
          telemetryAddTextData('Relic Target', 'VuMark of UNKNOWN type...');
        }
      } else {
        telemetryAddTextData('VuMark', 'No VuMarks are visible.');
      }
      telemetry.update();
    }
    vuforiaAccess.deactivate();
  }
}
