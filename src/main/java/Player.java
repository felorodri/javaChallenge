/******************************************************************************
 * Compilation:  javac Player.java
 *
 * Created by: Julian Rodriguez
 * Created on: 02/05/2019
 * Description: Ten pin bowling game player class. This class contains all ten pin
 * bowling game player atributes.
 *
 ******************************************************************************/

import java.util.List;
import java.util.ArrayList;

public class Player {
  private String name;
  private List<Integer> chances = new ArrayList<>();
  private List<Integer> score = new ArrayList<>();

  public Player (String name){
    this.name = name;
  }

  /**
  * Description: This method is used to get the name attribute from a external class.
  * @return String Returns the name attribute of the player.
  */
  public String getName() {
    return this.name;
  }

  /**
  * Description: This method is used to get the chances attribute from a external class.
  * @return List<Integer> Returns the list of chanes attribute of the player.
  */
  public List<Integer> getChances() {
    return this.chances;
  }

  /**
  * Description: This method is used to set the chances attribute from a external class.
  * @param chances A list of integer values with all player chances.
  * @return void Nothing.
  */
  public void setChances(List<Integer> chances) {
    this.chances = chances;
  }

  /**
  * Description: This method is used to set the score attribute from a external class.
  * @param score A list of integer values with each frame score.
  * @return void Nothing.
  */
  public void setScore(List<Integer> score) {
    this.chances = chances;
  }
}