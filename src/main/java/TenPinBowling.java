/******************************************************************************
 *  Compilation:  javac TenPinBowling.java
 *  Execution:    java TenPinBowling
 *
 * Created by: Julian Rodriguez
 * Created on: 02/05/2019
 * Description: Ten pin bowling java challenge executable class.
 *
 ******************************************************************************/
import java.util.List;

public class TenPinBowling {

  /**
  * Description: Main method. Checks the given args and execute program job.
  * @param args Execution args.
  * @return void. Nothing.
  */
  public static void main(String[] args) {
    String filePath = "";
    if (args.length == 0) {
      System.out.println("File path excpected. No file path given.");
      System.exit(0);
    }else{
      filePath = args[0].toString();
      System.out.println("Starting process...");
      System.out.println("Given path: " + filePath);
      FileParser parser = new FileParser(filePath);
      List<String[]> gameData = parser.getParsedData();
      TenPinBowlingGame newGame = new TenPinBowlingGame(gameData);
    }
  }
}
