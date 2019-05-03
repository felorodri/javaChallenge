/******************************************************************************
 * Compilation:  javac TenPinBowlingGame.java
 *
 * Created by: Julian Rodriguez
 * Created on: 02/05/2019
 * Description: Ten pin bowling game class. This class contains all ten pin
 * bowling game rules specified in the java challenge PDF.
 *
 ******************************************************************************/

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TenPinBowlingGame {

  private List<String[]> gameData;
  private List<Player> game;

  public TenPinBowlingGame (List<String[]> gameData){
    this.gameData = gameData;
    this.game = new ArrayList<>();
    Map<String, List<String>> gameMap = this.dataIntegrityChecker();
    this.recreateGame(gameMap);
  }

  /**
  * Description: This method is used to check if the parsed data make sense for the
  * game rules.
  * @return Map<String, List<String>>. Map representing the game chances per player.
  */
  private Map<String, List<String>> dataIntegrityChecker(){
    int listSize = this.gameData.size();
    List<String> playersNames = this.findPlayers();
    Map<String, List<String>> gameMap = new HashMap<>();
    try {
      gameMap = this.assignChancesToPlayers(playersNames);
      if (!this.numberOfChancesChecker(playersNames, gameMap)) {
        throw new Exception("FileFormatError");
      }
    } catch (Exception e) {
      ErrorHandler error = new ErrorHandler(e);
    }
    return gameMap;
  }

  /**
  * Description: This method is used to find all the names of the participating players.
  * @return List<String>. List of strings. Each string is a player name.
  */
  private List<String> findPlayers() {
    List<String> players = new ArrayList<>();
    try {
      for (String[] dataLine : this.gameData) {
        if (players.indexOf(dataLine[0]) == -1) {
          players.add(dataLine[0]);
        }
      }
    } catch (Exception e) {
      ErrorHandler error = new ErrorHandler(e);
    }
    return players;
  }

  /**
  * Description: This method is used to assign all chances to the corresponding player name.
  * @param playersNames A list of strings with all participant player names.
  * @return Map<String, List<String>>. Map representing the game chances per player.
  */
  private Map<String, List<String>> assignChancesToPlayers(List<String> playersNames){
    Map<String, List<String>> playersMap = new HashMap<>();
    try {
      for (String playerName : playersNames) {
        playersMap.put(playerName, new ArrayList<>());
      }
      for (String[] dataLine : this.gameData) {
        playersMap.get(dataLine[0]).add(dataLine[1]);
      }
    } catch (Exception e) {
      ErrorHandler error = new ErrorHandler(e);
    }
    return playersMap;
  }

  /**
  * Description: This method is used to check if all chances per player make sense for the game rules.
  * @param playersNames A list of strings with all participant player names.
  * @param playersMap Map representing the game chances per player.
  * @return boolean. True or false depending on if the chances per player make sense for the game rules or not.
  */
  private boolean numberOfChancesChecker(List<String> playersNames, Map<String, List<String>> playersMap){
    boolean response = true;
    for (String playerName : playersNames) {
      if (playersMap.get(playerName).size() < 12 || playersMap.get(playerName).size() > 21) {
        System.out.println("Number of registered chances for player " + playerName + " does not match the game rules.");
        response = false;
        break;
      }
    }
    return response;
  }

  /**
  * Description: This method is used to recreate the bowling game. It creates each pleayer and assign the
  * pinfalls for each chance according to the game rules.
  * @param gameMap Map representing the game chances per player.
  * @return void. Nothing.
  */
  private void recreateGame(Map<String, List<String>> gameMap) {
    for (String playerName : gameMap.keySet() ) {
      Player newPlayer = new Player(playerName);
      List<Integer> playerChances = new ArrayList<>();
      for (String chance : gameMap.get(playerName)) {
        String chanceValue = "";
        if (chance.equals("F")) {
          chanceValue = "0";
        } else {
          chanceValue = chance;
        }
        try {
          Integer intChanceValue;
          try {
            intChanceValue = Integer.parseInt(chanceValue);
            if (intChanceValue < 0 || intChanceValue > 10 ) {
              throw new Exception("FileFormatError");
            }
          } catch (Exception e) {
            System.out.println("Invalid chance result found for player " + playerName + ". It does not match with the game rules.");
            throw new Exception("FileFormatError");
          }
          playerChances.add(intChanceValue);
        } catch (Exception e) {
          ErrorHandler error = new ErrorHandler(e);
        }
      }
      newPlayer.setChances(playerChances);
    }
  }

  /**
  * Description: This method is used to calculate the player score in each frame according to the game rules.
  * @return void. Nothing.
  */
  private void calculateScorePerFrame(){

  }

}