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
              ".  .....",
              ".      .",
              "..   ...",
              ".      .",
              ".    X .",
              "........",
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
      } else if (currentRow == 1) {
        System.out.println("The robot cannot go up");
      } else {
        return false;
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
        return false;
      }
      return true;
    }
  
    //check position left of the robot
    public boolean checkDirectionLeft() {
      int currentRow = robotRow;
      int currentCol = robotCol;
  
  
      //check left the robot
      if (currentCol > 0) {
        if (map[currentRow].charAt(currentCol - 1) == '.') {
          System.out.println("The robot cannot go left");
          return false;
        }
      } else if (currentCol == 1) {
        System.out.println("The robot cannot go left");
        return false;
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
            case "RIGHT" -> currentCol++;
        }
  
      // check current position
      if (map[currentRow].charAt(currentCol) == 'X') {
        // Exit gate
        steps++;

        robotRow = currentRow;
        robotCol = currentCol;
        System.out.println("The location of X is: (" + robotCol + ", " + robotRow + ")");


        System.out.println("The robot has reached the Exit Gate");
        System.out.println("Steps to reach the Exit gate " + steps);
        return "win";

      } else {

        // Space => update robot location
        steps++;
        robotRow = currentRow;
        robotCol = currentCol;

        // Print the current position (Phúc)
        System.out.println("Current location: (" + robotCol + ", " + robotRow + ")");
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

      MazeTest maze = new MazeTest();

      String result = "";

      public void solveDeadEnd(int numberOfStepBack) {

          int previousRow, previousCol;


          previousCol = visitedCoordinates.get(visitedCoordinates.size() - numberOfStepBack).get(0);
          previousRow = visitedCoordinates.get(visitedCoordinates.size() - numberOfStepBack).get(1);




          if (previousCol < maze.robotCol && previousRow == maze.robotRow) {
              System.out.println("LEFT");
              result = maze.go("LEFT");
          } else if (previousCol > maze.robotCol && previousRow == maze.robotRow) {
              System.out.println("RIGHT");
              result = maze.go("RIGHT");
          } else if (previousRow < maze.robotRow && previousCol == maze.robotCol) {
              System.out.println("UP");
               result = maze.go("UP");
          } else {
              System.out.println("DOWN");
              result = maze.go("DOWN");
          }

          ArrayList<Integer>nextUpCoords = new ArrayList<>();
          ArrayList<Integer>nextRightCoords = new ArrayList<>();
          ArrayList<Integer>nextDownCoords = new ArrayList<>();
          ArrayList<Integer>nextLeftCoords = new ArrayList<>();

          nextUpCoords.add(0, maze.robotCol);
          nextUpCoords.add(1, maze.robotRow - 1);

          nextRightCoords.add(0, maze.robotCol + 1);
          nextRightCoords.add(1, maze.robotRow);

          nextDownCoords.add(0, maze.robotCol);
          nextDownCoords.add(1, maze.robotRow + 1);

          nextLeftCoords.add(0, maze.robotCol - 1);
          nextLeftCoords.add(1, maze.robotRow);

          if (maze.checkDirectionUp() && !visitedCoordinates.contains(nextUpCoords)) {
              solver();
          } else if (maze.checkDirectionRight() && !visitedCoordinates.contains(nextRightCoords)) {
              solver();
          } else if (maze.checkDirectionDown() && !visitedCoordinates.contains(nextDownCoords)) {
              solver();
          } else if (maze.checkDirectionLeft() && !visitedCoordinates.contains(nextLeftCoords)) {
              solver();
          } else {
              solveDeadEnd(numberOfStepBack + 2);
          }

      }




    public void solver() {
          // maze.findWayOutLocation();



            while (!result.equals("win")) {

              ArrayList<Integer>nextUpCoords = new ArrayList<>();
              ArrayList<Integer>nextRightCoords = new ArrayList<>();
              ArrayList<Integer>nextDownCoords = new ArrayList<>();
              ArrayList<Integer>nextLeftCoords = new ArrayList<>();



              nextUpCoords.add(0, maze.robotCol);
              nextUpCoords.add(1, maze.robotRow - 1);

              nextRightCoords.add(0, maze.robotCol + 1);
              nextRightCoords.add(1, maze.robotRow);

              nextDownCoords.add(0, maze.robotCol);
              nextDownCoords.add(1, maze.robotRow + 1);

              nextLeftCoords.add(0, maze.robotCol - 1);
              nextLeftCoords.add(1, maze.robotRow);




              visitedCoordinates.add(new ArrayList<Integer>());



              visitedCoordinates.get(visitedCoordinates.size() - 1).add(0, maze.robotCol);
              visitedCoordinates.get(visitedCoordinates.size() - 1).add(1, maze.robotRow);



              System.out.println(visitedCoordinates);



              System.out.println("________________________");

              if (maze.checkDirectionUp() && !visitedCoordinates.contains(nextUpCoords)) {
                  System.out.println("UP");
                  result = maze.go("UP");
              } else if (maze.checkDirectionRight() && !visitedCoordinates.contains(nextRightCoords)) {
                  System.out.println("RIGHT");
                  result = maze.go("RIGHT");
              } else if (maze.checkDirectionDown() && !visitedCoordinates.contains(nextDownCoords)) {
                  System.out.println("DOWN");
                  result = maze.go("DOWN");
              } else if (maze.checkDirectionLeft() && !visitedCoordinates.contains(nextLeftCoords)) {
                  System.out.println("LEFT");
                  result = maze.go("LEFT");
              } else {
                  solveDeadEnd(2);
              }

          }

      }

    public void navigate() {
      solver();

      }
    }

