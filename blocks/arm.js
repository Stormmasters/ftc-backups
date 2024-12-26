// IDENTIFIERS_USED=

/**
 * This sample contains the bare minimum Blocks for any regular OpMode. The 3 blue
 * Comment Blocks show where to place Initialization code (runs once, after touching the
 * DS INIT button, and before touching the DS
 * Start arrow), Run code (runs once, after
 * touching Start), and Loop code (runs repeatedly
 * while the OpMode is active, namely not
 * Stopped).
 */
function runOpMode() {
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      telemetry.update();
    }
  }
}
