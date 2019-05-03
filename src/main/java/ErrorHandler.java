/******************************************************************************
 * Compilation:  javac errorHandler.java
 *
 * Created by: Julian Rodriguez
 * Created on: 02/05/2019
 * Description: Ten pin bowling game error handler class. This class manage all
 * application errors.
 *
 ******************************************************************************/

public class ErrorHandler {

  private Exception error = null;

  public ErrorHandler (Exception error){
    this.error = error;
    this.handleError();
  }

  /**
  * Description: This method is used to handle and generate error message accordign to the
  * error class attribute.
  * @param playersNames A list of strings with all participant player names.
  * @return Map<String, List<String>>. Map representing the game chances per player.
  */

  private void handleError() {
    if (this.error.getClass().getName() == "java.nio.file.NoSuchFileException") {
      this.finishProcessWithMessage("Results file not found. Check the given file path and try again.", 0);
    } else if (this.error.getClass().getName() == "java.lang.StringIndexOutOfBoundsException") {
      this.finishProcessWithMessage("Results file path must include file extension. Check the given file path and try again.", 0);
    } else if (this.error.toString().equals("java.lang.Exception: FileFormatError")) {
      this.finishProcessWithMessage("File structure checker failed. Please check the results file format and try again", 0);
    } else {
      this.error.printStackTrace();
      System.out.println(this.error);
      this.finishProcessWithMessage("Unexpected error occured. Please submit an issue in https://github.com/felorodri/javaChallenge", 1);
    }
  }

  /**
  * Description: This method is used to finish the program process and print the error message.
  * @param message String with a given error message.
  * @param exitCode Integer with exit code number.
  * @return void Nothing.
  */
  private void finishProcessWithMessage(String message, int exitCode){
    System.out.println(message);
    System.out.println("Exiting process...");
    System.exit(exitCode);
  }
}