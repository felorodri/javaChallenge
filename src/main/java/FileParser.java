/******************************************************************************
 *  Compilation:  javac FileParser.java
 *
 * Created by: Julian Rodriguez
 * Created on: 02/05/2019
 * Description: Input file parser helper class.
 *
 ******************************************************************************/

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileParser {

  private String filePath = "";
  private List<String> data = new ArrayList<>();
  private List<String[]> parsedData;

  public FileParser (String filePath){
    this.filePath = filePath;
    this.data = this.readFile();
    this.parsedData = this.dataStructureChecker();
  }

  /**
  * Description: This method is used to read the input file and generate
  * a list of strings wich contains file lines.
  * @return list Returns a list of strings.
  */
  private List<String> readFile () {
    List<String> list = new ArrayList<>();
    if (this.fileExtensionChecker()) {
      try (Stream<String> stream = Files.lines(Paths.get(this.filePath))) {
        list = stream.collect(Collectors.toList());
      } catch (Exception e) {
        ErrorHandler newError = new ErrorHandler(e);
      }
    } else {
      System.out.println("Results file must be .txt file.");
      System.out.println("Exiting process...");
      System.exit(0);
    }
    return list;
  }

  /**
  * Description: This method is used to check that the input file is actually
  * a .txt file as it should.
  * @return boolean Returns true or false depending on the file extension.
  */
  private boolean fileExtensionChecker(){
    Boolean response = false;
    try {
      String fileExt = this.filePath.substring(this.filePath.lastIndexOf("."));
      if (fileExt.equals(".txt")) {
        response = true;
      }
    } catch (Exception e) {
      ErrorHandler newError = new ErrorHandler(e);
    }
    return response;
  }

  /**
  * Description: This method is used to check if the input file structure is
  * as the challenge PDF describes it. Throw an Exception if not.
  * @return List<String[]> Returns a list of String[] with a player name and a pinfalls number for each chance.
  */
  private List<String[]> dataStructureChecker() {
    List<String[]> structuredData = new ArrayList<>();
    try {
      for (String line : this.data) {
        String[] lineContent = line.split(" ");
        if (lineContent.length != 2) {
          throw new Exception("FileFormatError");
        } else {
          structuredData.add(lineContent);
        }
      }
    } catch (Exception e) {
      ErrorHandler error = new ErrorHandler(e);
    }
    return structuredData;
  }

  /**
  * Description: This method is used to get ParsedData attribute from a external class.
  * @return List<String[]> Returns a list of String[] with a player name and a pinfalls number for each chance.
  */
  public List<String[]> getParsedData() {
    return this.parsedData;
  }

}
