package Project;

import Project.LinkedListStack;

public class Maze {
  // Attributes
  private int rows;
  private int cols;
  private String[] map;
  private int robotRow;
  private int robotCol;
  private int steps;

  // Constructors
  public Maze() {
    // Note: in my real test, I will create much larger
    // and more complicated map
    rows = 4;
    cols = 5;
    map = new String[rows];
    map[0] = ".....";
    map[1] = ".   X";
    map[2] = ".   .";
    map[3] = ".....";
    robotRow = 2;
    robotCol = 1;
    steps = 0;
  }

  // Methods
  public String go(String direction) {
    if (!direction.equals("UP") &&
            !direction.equals("DOWN") &&
            !direction.equals("LEFT") &&
            !direction.equals("RIGHT")) {
      // invalid direction
      steps++;
      return "false";
    }
    int currentRow = robotRow;
    int currentCol = robotCol;
    if (direction.equals("UP")) {
      currentRow--;
    } else if (direction.equals("DOWN")) {
      currentRow++;
    } else if (direction.equals("LEFT")) {
      currentCol--;
    } else {
      currentCol++;
    }

    // check the next position
    if (map[currentRow].charAt(currentCol) == 'X') {
      // Exit gate
      steps++;
      System.out.println("Steps to reach the Exit gate " + steps);
      return "win";
    } else if (map[currentRow].charAt(currentCol) == '.') {
      // Wall
      steps++;
      return "false";
    } else {
      // Space => update robot location
      steps++;
      robotRow = currentRow;
      robotCol = currentCol;
      return "true";
    }
  }

  public static void main(String[] args) {
    (new Robot()).navigate();
  }
}

class Robot {
  // A very simple implementation
  // where the robot just go randomly
  int robotStartRow = 1;
  int robotStartCol = 1;
  LinkedListStack<Integer> coordinatesStack = new LinkedListStack<Integer>();
  public Object Coordinates;

  public void navigate() {
    Maze maze = new Maze();
    String result = "";

    coordinatesStack.push(new Coordinates(robotStartCol, robotStartCol));

    System.out.println(toString(coordinatesStack.peek()));






  }

}

class Coordinates {
  int X_coord;
  int Y_coord;

  public Coordinates() {}

  public Coordinates(int X, int Y) {
    X = X_coord;
    Y = Y_coord;
  }
}