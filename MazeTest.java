// package project_real;

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
        ".                   .       .     X .",
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
  
      rows = map.length;
      cols = map[0].length();
  
      robotRow = 2;
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
  
    // check X location
    public void findWayOutLocation(){
      int currentXRow = 0;
      int currentXCol = 0;
  
      
      for (int i = 1; i < map.length - 1; i++){
        for (int j = 1; j < map[0].length() - 1; j++) {
          if(map[i].charAt(j) == 'X'){
            currentXRow = i;
            currentXCol = j;
            break;
          }
        }
      }
  
      if (currentXRow == 0 && currentXCol == 0) {
        System.out.println("No Exit Way");
      } else {
        System.out.println("X Row: " + currentXRow + "\nX Col: " + currentXCol);
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
      MazeTest maze = new MazeTest();
      // maze.findWayOutLocation();
  
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