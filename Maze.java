// package project_real;

public class Maze {
  int rows; // số hàng
  int cols; // số cột
  String[] map; // tạo array
  int robotRow; // vị trí x của robot
  int robotCol; // vị trí y của robot
  int steps; // số bước của robot

  // Constructor
  public Maze() {
    // Note: in my real test, I will create much larger
    // and more complicated map
    rows = 5;
    cols = 5;
    map = new String[rows]; // tạo số element dựa trên số row
    map[0] = ".....";
    map[1] = ".   X";
    map[2] = ". ...";
    map[3] = ".   .";
    map[4] = ".....";
    robotRow = 2;
    robotCol = 1;
    steps = 0;
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
      // Alert if the robot hits a wall & print the current position (Phúc)
      System.out.println("The robot hits a wall");
      System.out.println("Current location: (" + robotRow + ", " + robotCol + ")");
      return "false";
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
    (new Robot()).navigate();
  }
}

class Robot {
  // A very simple implementation
  // where the robot just go randomly
  public void navigate() {
    Maze maze = new Maze();
    String result = "";

    while (!result.equals("win")) {
      double rnd = Math.random();
      
      if (rnd <= 0.25) {
        System.out.println("UP");
        result = maze.go("UP");
      } else if (rnd <= 0.50) {
        System.out.println("DOWN");
        result = maze.go("DOWN");
      } else if (rnd <= 0.75) {
        System.out.println("LEFT");
        result = maze.go("LEFT");
      } else {
        System.out.println("RIGHT");
        result = maze.go("RIGHT");
      }
    }
  }
}