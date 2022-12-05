//importing
import java.util.ArrayList;
import java.util.Arrays;


public class MazeTest {

    // Initialize the maze and its parameters

    int rows; // number of rows in the maze
    int cols; // number of columns in the maze
    String[] map; // creating array
    int robotRow; // robot position X coordinates
    int robotCol; // robot position Y coordinates
    int steps; // steps to solve the maze

  // Constructor of the maze
  public MazeTest() {



    map = new String[]{

            // The maze

            /* The maze map:
            0 1 2 3 4 5 6 7 ..
            1
            2
            3
            4
            5
            6
            7
            ..
             */
      ".....................................",
      ".   .           .       .   .       .",
      ".   .   .....   .   .   .   .   .   .",
      ".       .       .   .   .   .   .   .",
      ".........   .....   .   .   .   .   .",
      ".       .       .   .   .       .   .",
      ".   .   .....   .   .   .........   .",
      ".   .       .       .               .",
      ".   .................   .............",
      ".                   .       .       .",
      ".   .............   .....   .   .   .",
      ".       .       .           .   .   .",
      ".....   .   .....................   .",
      ".       .               .           .",
      ".   .............   .   .....   .   .",
      ".               .   .   .       .   .",
      ".............   .   .   .   .....   .",
      ".                   .       . X     .",
      "....................................."

      


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

    rows = map.length; // number of rows in the maze
    cols = map[0].length(); // number of columns in the maze

    // robot position
    robotRow = 3;
    robotCol = 1;

    // steps of the robot
    steps = 0;
  }



  /* Check if position above the robot is a wall
   return false if the position above the robot is wall
   return true if it is not a wall
   */
  public boolean checkDirectionUp() {

      // get the robot current location
    int currentRow = robotRow;
    int currentCol = robotCol;


    //check above the robot
    if (currentRow > 0) {
        return map[currentRow - 1].charAt(currentCol) != '.';
    }
    return true;
  }

    /* Check if position below the robot is a wall
   return false if the position below the robot is wall
   return true if it is not a wall
   */
  public boolean checkDirectionDown() {

      // get the robot current location
    int currentRow = robotRow;
    int currentCol = robotCol;

    //check below the robot
    if (currentRow < rows) {
        return map[currentRow + 1].charAt(currentCol) != '.';
    } else return currentRow != rows;
  }

    /* Check if position on the left of the robot is a wall
   return false if the position on the left of the robot is wall
   return true if it is not a wall
   */
  public boolean checkDirectionLeft() {

      // get the robot current location
    int currentRow = robotRow;
    int currentCol = robotCol;


    //check left of the robot
    if (currentCol > 0) {
        return map[currentRow].charAt(currentCol - 1) != '.';
    }
    return true;
  }

    /* Check if position on the right the robot is a wall
   return false if the position on the right of the robot is wall
   return true if it is not a wall
   */
  public boolean checkDirectionRight() {

    // get the robot current location
    int currentRow = robotRow;
    int currentCol = robotCol;


    //check right of the robot
    if (currentCol < cols) {
        return map[currentRow].charAt(currentCol + 1) != '.';
    }
    return true;
  }
   /*
   method for the robot to move
   This method has 2 steps:
   Step 1: move the robot
   Step 2: check the robot current location
    */
  public String go(String direction) {

    //get the robot current location
    int currentRow = robotRow;
    int currentCol = robotCol;

    // moving the robot
      switch (direction) {
          case "UP" -> currentRow--;
          case "DOWN" -> currentRow++;
          case "LEFT" -> currentCol--;
          case "RIGHT" -> currentCol++;
      }

    /*
     check the current position that the robot is stepping on
     return "win" when robot is on X
     return "true when robot is not on X
     */

      //Exit gate
    if (map[currentRow].charAt(currentCol) == 'X') {

        // increase the steps
      steps++;

      // get the robot current location
      robotRow = currentRow;
      robotCol = currentCol;

      // inform the user the X location
      System.out.println("The location of X is: (" + robotCol + ", " + robotRow + ")");

      System.out.println("The robot has reached the Exit Gate");

      // inform the user the steps robot taken to reach
      System.out.println("Steps to reach the Exit gate " + steps);
      return "win";

    } else {

        // not Exit Gate situation
      steps++; // increase steps

      // update robot location
      robotRow = currentRow;
      robotCol = currentCol;

      // Print the current position
      System.out.println("Current location: (" + robotCol + ", " + robotRow + ")");
      return "true";
    }
  }

  public static void main(String[] args) {

    // call the robot to navigate through the maze
    new Robot().navigate();
  }
}

// robot class
class Robot {
      // a 2D array to track unique visited coordinates
      ArrayList<ArrayList<Integer>> visitedCoordinates = new ArrayList<>();

      // create the maze
      MazeTest maze = new MazeTest();

      // the result to keep the program running
      String result = "";

      /*
      solving when robot goes into a dead-end, using recursion
      Definition of a dead-end:
      1) From the robot current location, 3 directions of the robot are walls and have no choice other than going back
      2) From the robot current location, 4 directions of the robot are already visited coordinates
      Step to solve when robot reaches a dead-end:
      Step 1: get the robot's previous location from the current location

      Step 2: Compare the previous location to current location so that the robot knows how to get back to previous location

      Step 3: Move to previous location

      Step 4: Check 4 directions of the robot for another path
      If robot finds another way, move to step 5.1. If not, 5.2

      Step 5.1: move to that location that leads to the other way, call solver() to continue traverse through the maze

      Step 5.2: Go back to step 1
       */
      public void solveDeadEnd(int numberOfStepBack) {

          // get the previous coordinates from the robot current location
          int previousRow, previousCol;
          previousCol = visitedCoordinates.get(visitedCoordinates.size() - numberOfStepBack).get(0);
          previousRow = visitedCoordinates.get(visitedCoordinates.size() - numberOfStepBack).get(1);

          // compare previous location's coordinates to current location so that the robot can move to the previous location
          if (previousCol < maze.robotCol && previousRow == maze.robotRow) {
            System.out.println("LEFT");

            // the robot goes left
            result = maze.go("LEFT");

          } else if (previousCol > maze.robotCol && previousRow == maze.robotRow) {
              System.out.println("RIGHT");

              // the robot goes right
              result = maze.go("RIGHT");

          } else if (previousRow < maze.robotRow && previousCol == maze.robotCol) {
            System.out.println("UP");

            // the robot goes up
            result = maze.go("UP");

          } else {
            System.out.println("DOWN");

            // the robot goes down
            result = maze.go("DOWN");

          } 

          System.out.println("________________________");

          // create arrays to obtain the coordinates of 4 direction
          ArrayList<Integer>upperCoords = new ArrayList<>();
          ArrayList<Integer>rightCoords = new ArrayList<>();
          ArrayList<Integer>belowCoords = new ArrayList<>();
          ArrayList<Integer>leftCoords = new ArrayList<>();

          // get the coordinates on top of the robot
          upperCoords.add(0, maze.robotCol);
          upperCoords.add(1, maze.robotRow - 1);

          // get the coordinates on the right of the robot
          rightCoords.add(0, maze.robotCol + 1);
          rightCoords.add(1, maze.robotRow);

          // get the coordinates below of the robot
          belowCoords.add(0, maze.robotCol);
          belowCoords.add(1, maze.robotRow + 1);

          // get the coordinates on the left of the robot
          leftCoords.add(0, maze.robotCol - 1);
          leftCoords.add(1, maze.robotRow);

          if (maze.checkDirectionUp() && !visitedCoordinates.contains(upperCoords)) {
            solver();
          } else if (maze.checkDirectionRight() && !visitedCoordinates.contains(rightCoords)) {
            solver();
          } else if (maze.checkDirectionDown() && !visitedCoordinates.contains(belowCoords)) {
            solver();
          } else if (maze.checkDirectionLeft() && !visitedCoordinates.contains(leftCoords)) {
            solver();
          } else {
              solveDeadEnd(numberOfStepBack + 1);
          }

      }

      /*
      The solver() method is to help the robot traverse through the maze and solve the maze
       */
  public void solver() {

    // While the robot is not reach the "X"
    while (!result.equals("win")) {

        // create arrays to obtain the coordinates of 4 direction
        ArrayList<Integer>upperCoords = new ArrayList<>();
        ArrayList<Integer>rightCoords = new ArrayList<>();
        ArrayList<Integer>belowCoords = new ArrayList<>();
        ArrayList<Integer>leftCoords = new ArrayList<>();

        // get the coordinates on top of the robot
        upperCoords.add(0, maze.robotCol);
        upperCoords.add(1, maze.robotRow - 1);

        // get the coordinates on the right of the robot
        rightCoords.add(0, maze.robotCol + 1);
        rightCoords.add(1, maze.robotRow);

        // get the coordinates below of the robot
        belowCoords.add(0, maze.robotCol);
        belowCoords.add(1, maze.robotRow + 1);

        // get the coordinates on the left of the robot
        leftCoords.add(0, maze.robotCol - 1);
        leftCoords.add(1, maze.robotRow);

        // add a new empty array to the visited-coordinates array
        visitedCoordinates.add(new ArrayList<>());

        // add the current location to the visited-coordinates array
        visitedCoordinates.get(visitedCoordinates.size() - 1).add(0, maze.robotCol);
        visitedCoordinates.get(visitedCoordinates.size() - 1).add(1, maze.robotRow);

        System.out.println("________________________");

      /*
      (moving priority: UP RIGHT DOWN LEFT)
      The robot traverses through the maze
      Step 1: the robot add its current location

      Step 2: the robot check direction (UP first) for walls

      step 3: the robot check whether the coordinate is already visited or not

      If it is not visited and not wall:
      Step 4.1: move to that location
      Move to step 5.1

      If it is already visited or it is wall:
      Step 4.2: go back to step 2  (Until you have checked all directions)
      If the robot can't move, move to step 5.2

      Step 5.1: go back to step 1 (Until you reach X)

      Step 5.2: if the robot reaches this step, this means that the robot has reached the dead-end, call solveDeadEnd() method
       */
      if (maze.checkDirectionUp() && !visitedCoordinates.contains(upperCoords)) {
          System.out.println("UP");

          // the robot goes up
          result = maze.go("UP");
      } else if (maze.checkDirectionRight() && !visitedCoordinates.contains(rightCoords)) {
          System.out.println("RIGHT");

          // the robot goes right
          result = maze.go("RIGHT");
      } else if (maze.checkDirectionDown() && !visitedCoordinates.contains(belowCoords)) {
          System.out.println("DOWN");

          //the robot goes down
          result = maze.go("DOWN");
      } else if (maze.checkDirectionLeft() && !visitedCoordinates.contains(leftCoords)) {
          System.out.println("LEFT");

          // the robot goes left
          result = maze.go("LEFT");
      } else {

          /*
           call solveDeadEnd() when robot reaches a dead-end. To get the previous location's coordinates,
           we go back 2 item from the end of the array
           */
          solveDeadEnd(2);
      }
    }
  }

    // create a map to display the route that robot has taken
    ArrayList<String> mapAfter = new ArrayList<>();
 

  public String replaceChar(String str, char ch, int index) {
    return str.substring(0, index) + ch + str.substring(index + 1);
  }

  public void printMapAfter() {
      mapAfter.addAll(Arrays.asList(maze.map));
  
    for (int mapAfterRows = 1; mapAfterRows < mapAfter.size() - 1 ; mapAfterRows++) {
        for (ArrayList<Integer> visitedCoordinate : visitedCoordinates) {
            String mapPart2 = mapAfter.get(mapAfterRows);

            if (visitedCoordinate.get(1) == mapAfterRows) {
                mapPart2 = replaceChar(mapPart2, '+', visitedCoordinate.get(0));
                mapAfter.set(mapAfterRows, mapPart2);
            }
        }
    }

    System.out.println("---------------------");
    System.out.println("Road after robot finding in maze: ");
    for (int mapDeployed = 0; mapDeployed < maze.map.length ; mapDeployed++) {
      System.out.println(mapAfter.get(mapDeployed));
    }
  }

  public void navigate() {
    solver();
    printMapAfter();
  }
}