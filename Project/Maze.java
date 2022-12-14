package Project;

public class Maze {
    // Attributes
    private int rows;
    private int cols;
    String[] map;
    private int robotRow;
    private int robotCol;
    private int steps;

    // Constructors
    public Maze() {
        // Note: in my real test, I will create much larger
        // and more complicated map
        map = new String[]{
//                ".......",
//                ".    X.",
//                ".     .",
//                ".     .",
//                ".......",

             "....................................................................................................",
             ".                                              ..                                                  .",
             ".                                              ..                          ..                      .",
             ".                                              ..                          ..                      .",
             ".      ..............                          ..                  ...     ..                      .",
             ".            .............                     ..                  ...     ..   ....................",
             ".                                              ..                  ...     ..   ....................",
             ".       ....  .   .  .  .....                                                   ...     ..         .",
             ".       .  .  .. ..  .    .            ........                     .........   ...     ..         .",
             ".       ....  . . .  .    .            ........                    .........    ...     ..         .",
             ".       ..    .   .  .    .            ........                                 ...     ..         .",
             ".       . .   .   .  .    .        .   ........                        ..                       ....",
             ".       .  .  .   .  .    .        .   ........                        ..                       ....",
             ".                                  .                                   ..  ..           .......    .",
             ".    ..       ..    ..       ..    .        ..      ..    ...          ..  ..           .......    .",
             ".    ....     ..    ....     ..    .        ..      ..    .....        ..  ..                      .",
             ".    .. ..    ..    .. ..    ..        .    ..      ..    ..  ...  ..      ..                      .",
             ".    ..  ..   ..    ..  ..   ..   .......   ..........    ..   ..  ..      ..                      .",
             ".    ..   ..  ..    ..   ..  ..   .......   ..........    ..   ..  ..      ..                      .",
             ".    ..    .. ..    ..    .. ..        .    ..      ..    ..  ...          ..                      .",
             ".    ..     ....    ..     ....             ..      ..    .....            ..    ..........        .",
             ".    ..       ..    ..       ..             ..      ..    ...              ..    ..........        .",
             ".                                                                          ..                      .",
             ".      .            .           .            .                             ..          X           .",
             ".      .     .      .           .            .                             ..                      .",
             ".      .     .      .                        .        .                    ..                      .",
             ".      .     .      .                        .        .                    ..                      .",
             ".      .     .      .           .            .        .                    ..                      .",
             ".            .                  .                     .                    ..                      .",
             "....................................................................................................",
        };
        rows = map.length;
        cols = map[0].length();

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

// Command line styles Class
class Style {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
}


// robot class
class Robot {
    // create the maze
    Maze maze = new Maze();

    public int numOfCoords = 1;        // number of coordinates added into the 2D array, use to increase size of the array
    public int numOfDirections = 0;    // number of direction, use as number of stacks

    // consider the current position of the robot as virtual coordinates (0,0) regardless of its spawn position
    public int robotStartRow = 0;
    public int robotStartCol = 0;

    // the result to keep the program running
    public String result = "";

    


    // stack to record robot's direction
    LinkedListStack<String> directionStack = new LinkedListStack<>();


    // 2D array to store virtual coordinates
    int[][] visitedCoordinates = new int[1][2];

    // increase size of 2D array
    public void assignCoords() {

        int[][] temp = new int[numOfCoords + 1][2]; // Initialize of temp 2D array

        int length = visitedCoordinates.length;

        for (int i = 0; i < length; i++) {
            temp[i] = visitedCoordinates[i];
        }
        visitedCoordinates = temp;

    }

    /*
     * Check 4 direction of robot current location to see if it has been going through or not
     */

    // check if robot has gone to this location or not
    public boolean checkExistCoords(int[] coords) {
        /*
        return true if the coordinate is in the 2D array
        return false if the coordinate isn't in the 2D array
         */
        for (int[] visitedCoordinate : visitedCoordinates) {
            if (coords[0] == visitedCoordinate[0] && coords[1] == visitedCoordinate[1]) {
                return true;
            }
        }
        return false;
    }

    // check top location
    public boolean checkExistCoordsUp() {
        int[] upperCoords = new int[2];

        //check above the robot
        int upperRobotRow = robotStartRow - 1;

        upperCoords[0] = robotStartCol;
        upperCoords[1] = upperRobotRow;


        return checkExistCoords(upperCoords);
    }

    
    // check bottom direction
    public boolean checkExistCoordsDown() {
        int[] belowCoords = new int[2];

        //check above the robot
        int belowRobotRow = robotStartRow + 1;

        belowCoords[0] = robotStartCol;
        belowCoords[1] = belowRobotRow;

        return checkExistCoords(belowCoords);
    }

    // check left direction
    public boolean checkExistCoordsLeft() {
        int[] leftCoords = new int[2];

        //check above the robot
        int leftRobotCol = robotStartCol - 1;

        leftCoords[0] = leftRobotCol;
        leftCoords[1] = robotStartRow;

        return checkExistCoords(leftCoords);
    }

    // check right direction
    public boolean checkExistCoordsRight() {
        int[] rightCoords = new int[2];

        //check above the robot
        int rightRobotCol = robotStartCol + 1;

        rightCoords[0] = rightRobotCol;
        rightCoords[1] = robotStartRow;

        return checkExistCoords(rightCoords);
    }

    /*
    call this function when robots hit a wall
        
    */
    public void hitWall(String direction) {

        // announce the user
        System.out.println(Style.ANSI_RED + "Oops! Robot hits a wall. The robot is going back" + Style.ANSI_RESET);

        // pop the last stack (direction)
        directionStack.pop();

        // decrease the number of stacks
        numOfDirections--;

        // assign the coordinates into the 2D array
        visitedCoordinates[numOfCoords][0] = robotStartCol;
        visitedCoordinates[numOfCoords][1] = robotStartRow;

        // going back based on last direction before the robot hit the wall
        switch (direction) {
            case "UP":
                System.out.println(Style.ANSI_YELLOW + "Going back DOWN" + Style.ANSI_RESET);
                robotStartRow++;
                break;
            case "RIGHT":
                System.out.println(Style.ANSI_YELLOW + "Going back LEFT" + Style.ANSI_RESET);
                robotStartCol--;
                break;
            case "DOWN":
                System.out.println(Style.ANSI_YELLOW + "Going back UP" + Style.ANSI_RESET);
                robotStartRow--;
                break;
            default:
                System.out.println(Style.ANSI_YELLOW + "Going back RIGHT" + Style.ANSI_RESET);
                robotStartCol++;
                break;
        }

        // increase the number of added coordinates into the 2D array
        numOfCoords++;

        // increase number of size of 2D array
        assignCoords();

    }

    

    /*
        solving when robot goes into a dead-end

        Definition of a dead-end:
        1) From the robot current location, 3 directions of the robot are walls and have no choice other than going back
        2) From the robot current location, 4 directions of the robot are already visited location
      
        How to solve dead end:
        Check each reversed direction can go, if that direction can go, the robot will go until robot can go to another direction.
        And directions after robot has finished going backward is removed.
     
    */
    public void solveDeadEnd() {

        // announce the user
        System.out.println(Style.ANSI_RED + "Oops dead-end! Backtracking next step" + Style.ANSI_RESET);

        // get the last direction moved by the robot
        String lastDirection = directionStack.peek();

        switch (lastDirection) {
            case "UP":
                System.out.println(Style.ANSI_RED + "Backtracking: going DOWN" + Style.ANSI_RESET);
                result = maze.go("DOWN");
                robotStartRow++;
                break;
            case "RIGHT":
                System.out.println(Style.ANSI_RED + "Backtracking: going LEFT" + Style.ANSI_RESET);
                result = maze.go("LEFT");
                robotStartCol--;
                break;
            case "DOWN":
                System.out.println(Style.ANSI_RED + "Backtracking: going UP" + Style.ANSI_RESET);
                result = maze.go("UP");
                robotStartRow--;
                break;
            default:
                System.out.println(Style.ANSI_RED + "Backtracking: going RIGHT" + Style.ANSI_RESET);
                result = maze.go("RIGHT");
                robotStartCol++;
                break;
        }

        directionStack.pop();

        numOfDirections--;
    }

    public void robotDirection() {
        String [] robotDirection = new String[numOfDirections];
        String direction;

        int times = 1;

        for (int i = 0; i < numOfDirections; i++) {
            direction = directionStack.peek();
            robotDirection[numOfDirections - 1 - i] = direction;
            directionStack.pop();
        }

        System.out.println("Robot optimal directions to solve the maze:");
        for (int i = 0; i < robotDirection.length; i++) {
            if (i > 0 && robotDirection[i].equals(robotDirection[i - 1])) {
                times++;
                System.out.print(robotDirection[i] +  " " + times + "    ");
            } else {
                System.out.print(robotDirection[i] + "    ");
                times = 1;
            }
        }
    }


    /* 
     *  Starting by set the current robot standing is coordinate (0, 0)
     *  The robot will walk according to moving priorities (UP RIGHT DOWN LEFT) until reaching the way out 'X'. There are 2 conditions to reach the way out.
     *      - Condition 01: IF robot touch 'wall', it will go backward based on the last direction when hitting walls and dodge that wall.
     *      - Condition 02: IF robot reach dead-end, it will backtracking that road until it find another way to go.
     * 
     *  During robot is walking, it's recorded direction using stacks and amount of steps so when reaching the way out,
     * the direction list will show to user. 
    */
    public void navigate() {

        // add the (0,0) coordinate to the 2D array
        visitedCoordinates[0][0] = robotStartCol;
        visitedCoordinates[0][1] = robotStartRow;

        // when robot hasn't reached X yet
        while (!result.equals("win")) {

            // increase size of array by 1
            assignCoords();


            if (!checkExistCoordsUp()) {
                System.out.println(Style.ANSI_GREEN + "The robot goes up" + Style.ANSI_RESET);
                result = maze.go("UP");
                robotStartRow--;

                directionStack.push("UP");
                numOfDirections++;

                // when the robot hit walls
                if (result.equals("false")) {
                    hitWall("UP");
                }

            } else if (!checkExistCoordsRight()) {
                System.out.println(Style.ANSI_GREEN + "The robot goes right" + Style.ANSI_RESET);
                result = maze.go("RIGHT");
                robotStartCol++;

                directionStack.push("RIGHT");
                numOfDirections++;

                // when the robot hit walls
                if (result.equals("false")) {
                    hitWall("RIGHT");
                }



            } else if (!checkExistCoordsDown()) {
                System.out.println(Style.ANSI_GREEN + "The robot goes down" + Style.ANSI_RESET);
                result = maze.go("DOWN");
                robotStartRow++;

                directionStack.push("DOWN");
                numOfDirections++;

                // when the robot hit walls
                if (result.equals("false")) {
                    hitWall("DOWN");
                }



            } else if (!checkExistCoordsLeft()) {
                System.out.println(Style.ANSI_GREEN + "The robot goes left" + Style.ANSI_RESET);
                result = maze.go("LEFT");
                robotStartCol--;

                directionStack.push("LEFT");
                numOfDirections++;

                // when the robot hit walls
                if (result.equals("false")) {
                    hitWall("LEFT");
                }

            } else {
                // when robot has reached the dead-end
                solveDeadEnd();
            }

            // assign coordinates to the 2D array
            visitedCoordinates[numOfCoords][0] = robotStartCol;
            visitedCoordinates[numOfCoords][1] = robotStartRow;

            // increase the number of coordinates added into the 2D array
            numOfCoords++;
        }

        System.out.println(Style.ANSI_CYAN + "Moving priorities: UP RIGHT LEFT DOWN. On first time, the robot will bump into walls and walk to dead-ends. " +
                "The shortest way will be the optimal way to reach X based on the path explored by robot" + Style.ANSI_RESET);

        System.out.println(Style.ANSI_PURPLE + "After the robot reaches X, the shortest way to X is: " + numOfDirections + " step(s)" + Style.ANSI_RESET);

        // Display the result of the optimal way of maze solver
        robotDirection();
        
    }
}
