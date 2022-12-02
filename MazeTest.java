// package project_real;

import java.util.ArrayList;
import java.util.Arrays;

public class MazeTest {
    int rows; // số hàng
    int cols; // số cột
    String[] map; // tạo array
    int robotRow; // vị trí x của robot
    int robotCol; // vị trí y của robot
    int steps; // số bước của robot
  
    // Constructor
    public MazeTest() {
      // Note: in my real test, I will create much larger
      // and more complicated map
  
  
      // rows = 5;
      // cols = 5;
      // map = new String[rows]; // tạo số element dựa trên số row
      // map[0] = ".....";
      // map[1] = ".   X";
      // map[2] = ". ...";
      // map[3] = ".   .";
      // map[4] = ".....";
  
      map = new String[]{
        "........",
        ".      .",
        ".   .  X",
        ".      .",
        "....   .",
        ".      .",
        ".      .",
        "........",

  
        // "....................................................................................................",
        // ".                                              ..                                                  .",
        // ".                                              ..                          ..                      .",
        // ".                                              ..                          ..                      .",
        // ".      ..............                          ..                  ...     ..                      .",
        // ".            .............                     ..                  ...     ..   ....................",
        // ".                                              ..                  ...     ..   ....................",
        // ".       ....  .   .  .  .....                                                   ...     ..         .",
        // ".       .  .  .. ..  .    .            ........                     .........   ...     ..         .",
        // ".       ....  . . .  .    .            ........                    .........    ...     ..         .",
        // ".       ..    .   .  .    .            ........                                 ...     ..         .",
        // ".       . .   .   .  .    .        .   ........                        ..                       ....",
        // ".       .  .  .   .  .    .        .   ........                        ..                       ....",
        // ".                                  .                                   ..  ..           .......    .",
        // ".    ..       ..    ..       ..    .        ..      ..    ...          ..  ..           .......    .",
        // ".    ....     ..    ....     ..    .        ..      ..    .....        ..  ..                      .",
        // ".    .. ..    ..    .. ..    ..        .    ..      ..    ..  ...  ..      ..                      .",
        // ".    ..  ..   ..    ..  ..   ..   .......   ..........    ..   ..  ..      ..                      .",
        // ".    ..   ..  ..    ..   ..  ..   .......   ..........    ..   ..  ..      ..                      .",
        // ".    ..    .. ..    ..    .. ..        .    ..      ..    ..  ...          ..                      .",
        // ".    ..     ....    ..     ....             ..      ..    .....            ..    ..........        .",
        // ".    ..       ..    ..       ..             ..      ..    ...              ..    ..........        .",
        // ".                                                                          ..                      .",
        // ".      .            .           .            .                             ..          X           .",
        // ".      .     .      .           .            .                             ..                      .",
        // ".      .     .      .                        .        .                    ..                      .",
        // ".      .     .      .                        .        .                    ..                      .",
        // ".      .     .      .           .            .        .                    ..                      .",
        // ".            .                  .                     .                    ..                      .",
        // "....................................................................................................",
      };
  
      rows = 8;
      cols = 8;
  
      robotRow = 1;
      robotCol = 1;
      steps = 0;


    }



    //Check position above the robot
    public boolean checkDirectionUp() {
      int currentRow = robotRow;
      int currentCol = robotCol;



      //check above the robot
      if (currentRow > 0) {
        if (map[currentRow - 1].charAt(currentCol) == '.') {
          System.out.println("The robot cannot go up");
          return false;
        }
      } else if (currentRow == 0) {
        System.out.println("The robot cannot go up");
      } else {
        return true;
      }
      return true;
    }
  
    //check position below the robot
    public boolean checkDirectionDown() {
      int currentRow = robotRow;
      int currentCol = robotCol;

  
      //check below the robot
      if (currentRow < rows) {
        if (map[currentRow + 1].charAt(currentCol) == '.') {
          System.out.println("The robot cannot go down");
          return false;
        }
      } else if (currentRow == rows) {
        System.out.println("The robot cannot go down");
      }
      return true;
    }
  
    //check position left of the robot
    public boolean checkDirectionleft() {
      int currentRow = robotRow;
      int currentCol = robotCol;
  
  
      //check left the robot
      if (currentCol > 0) {
        if (map[currentRow].charAt(currentCol - 1) == '.') {
          System.out.println("The robot cannot go left");
          return false;
        }
      } else if (currentCol == 0) {
        System.out.println("The robot cannot go left");
      }
      return true;
    }
  
    //check position right of the robot
    public boolean checkDirectionRight() {
      int currentRow = robotRow;
      int currentCol = robotCol;




  
      //check right the robot
      if (currentCol < cols) {
        if (map[currentRow].charAt(currentCol + 1) == '.') {
          System.out.println("The robot cannot go right");
          return false;
        }
      } else if (currentCol == cols) {
        System.out.println("The robot cannot go right");
      }
      return true;
    }

  
  
  
    public String go(String direction) {

      // check direction ko phải up, dowm, left, right
      if (!direction.equals("UP") &&
        !direction.equals("DOWN") &&
        !direction.equals("LEFT") &&
        !direction.equals("RIGHT")) {
        // invalid direction
        steps++; // tăng step
        return "false";
      }
  
      int currentRow = robotRow; // 2
      int currentCol = robotCol; // 1
  
      // check hướng đi robot và thay đổi vị trí
        switch (direction) {
            case "UP" -> currentRow--;
            case "DOWN" -> currentRow++;
            case "LEFT" -> currentCol--;
            default -> currentCol++;
        }
  
      // check the next position
      if (map[currentRow].charAt(currentCol) == 'X') {
        // Exit gate
        steps++;
        robotRow = currentRow;
        robotCol = currentCol;
        System.out.println("The location of X is: (" + robotRow + ", " + robotCol + ")");


        System.out.println("The robot has reached the Exit Gate");
        System.out.println("Steps to reach the Exit gate " + steps);
        return "win";

      } else {

        // Space => update robot location
        steps++;
        robotRow = currentRow;
        robotCol = currentCol;

        // Print the current position (Phúc)
        System.out.println("Current location: (" + robotRow + ", " + robotCol + ")");
        return "true";
      }
    }
  

  
    public static void main(String[] args) {



      new Robot().navigate();
    }
  }


  class Robot {
    // A very simple implementation
    // where the robot just go randomly
    ArrayList<ArrayList<Integer>> visitedCoordinates = new ArrayList<>();
    ArrayList<ArrayList<Integer>> deadendCoordinates = new ArrayList<ArrayList<Integer>>();


    public void navigate() {
      MazeTest maze = new MazeTest();
      // maze.findWayOutLocation();

  


          for (int i = 0; i <= visitedCoordinates.size(); ++i) {


              ArrayList<Integer>nextUpCoords = new ArrayList<>();
              int[] nextRightCoords = {maze.robotRow, maze.robotCol + 1};

              nextUpCoords.add(0, maze.robotRow - 1);
              nextUpCoords.add(1, maze.robotCol);


              String result = "";

              visitedCoordinates.add(new ArrayList<Integer>());
              visitedCoordinates.get(i).add(0, maze.robotRow);
              visitedCoordinates.get(i).add(1, maze.robotCol);

              System.out.println(nextUpCoords);
              System.out.println(visitedCoordinates);





              System.out.println("________________________");
              if (maze.checkDirectionUp() && !visitedCoordinates.contains(nextUpCoords)) {
                  System.out.println("UP");
                  result = maze.go("UP");
              } else if (maze.checkDirectionRight() && !visitedCoordinates.contains(nextRightCoords)) {
                  System.out.println("RIGHT");
                  result = maze.go("RIGHT");
              } else if (maze.checkDirectionDown()) {
                  System.out.println("DOWN");
                  result = maze.go("DOWN");
              } else {
                  System.out.println("LEFT");
                  result = maze.go("LEFT");
              }

              if (result.equals("win")){
                  break;
              }
          }
      }
    }

