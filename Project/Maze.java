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
                ".......",
                ".    X.",
                ".     .",
                ".     .",
                ".......",


//
//                "....................................................................................................",
//                ".                                              ..                                                  .",
//                ".                                              ..                          ..                      .",
//                ".                                              ..                          ..                      .",
//                ".      ..............                          ..                  ...     ..                      .",
//                ".            .............                     ..                  ...     ..   ....................",
//                ".                                              ..                  ...     ..   ....................",
//                ".       ....  .   .  .  .....                                                   ...     ..         .",
//                ".       .  .  .. ..  .    .            ........                     .........   ...     ..         .",
//                ".       ....  . . .  .    .            ........                    .........    ...     ..         .",
//                ".       ..    .   .  .    .            ........                                 ...     ..         .",
//                ".       . .   .   .  .    .        .   ........                        ..                       ....",
//                ".       .  .  .   .  .    .        .   ........                        ..                       ....",
//                ".                                  .                                   ..  ..           .......    .",
//                ".    ..       ..    ..       ..    .        ..      ..    ...          ..  ..           .......    .",
//                ".    ....     ..    ....     ..    .        ..      ..    .....        ..  ..                      .",
//                ".    .. ..    ..    .. ..    ..        .    ..      ..    ..  ...  ..      ..                      .",
//                ".    ..  ..   ..    ..  ..   ..   .......   ..........    ..   ..  ..      ..                      .",
//                ".    ..   ..  ..    ..   ..  ..   .......   ..........    ..   ..  ..      ..                      .",
//                ".    ..    .. ..    ..    .. ..        .    ..      ..    ..  ...          ..                      .",
//                ".    ..     ....    ..     ....             ..      ..    .....            ..    ..........        .",
//                ".    ..       ..    ..       ..             ..      ..    ...              ..    ..........        .",
//                ".                                                                          ..                      .",
//                ".      .            .           .            .                             ..          X           .",
//                ".      .     .      .           .            .                             ..                      .",
//                ".      .     .      .                        .        .                    ..                      .",
//                ".      .     .      .                        .        .                    ..                      .",
//                ".      .     .      .           .            .        .                    ..                      .",
//                ".            .                  .                     .                    ..                      .",
//                "....................................................................................................",
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

class Robot {
    // A very simple implementation
    // where the robot just go randomly
    Maze maze = new Maze();

    int numOfCoords = 1;
    int numOfDirections = 0;

    int robotStartRow = 0;
    int robotStartCol = 0;


    String result = "";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";


    LinkedListStack<Integer> directionStack = new LinkedListStack<>();
    int[][] visitedCoordinates = new int[1][2];


    public boolean checkExistCoords(int[] coords) {

        for (int[] visitedCoordinate : visitedCoordinates) {
            if (coords[0] == visitedCoordinate[0] && coords[1] == visitedCoordinate[1]) {
                return true;
            }
        }
        return false;
    }

    // increase size of array
    public void assignCoords() {

        int[][] temp = new int[numOfCoords + 1][2];

        int length = visitedCoordinates.length;
        for (int i = 0; i < length; i++) {
            temp[i] = visitedCoordinates[i];
        }
        visitedCoordinates = temp;

    }

    public boolean checkExistCoordsUp() {
        int[] upperCoords = new int[2];

        //check above the robot
        int upperRobotRow = robotStartRow - 1;

        upperCoords[0] = robotStartCol;
        upperCoords[1] = upperRobotRow;


        return checkExistCoords(upperCoords);
    }

    public boolean checkExistCoordsDown() {
        int[] belowCoords = new int[2];

        //check above the robot
        int belowRobotRow = robotStartRow + 1;

        belowCoords[0] = robotStartCol;
        belowCoords[1] = belowRobotRow;

        return checkExistCoords(belowCoords);
    }

    public boolean checkExistCoordsLeft() {
        int[] leftCoords = new int[2];

        //check above the robot
        int leftRobotCol = robotStartCol - 1;

        leftCoords[0] = leftRobotCol;
        leftCoords[1] = robotStartRow;

        return checkExistCoords(leftCoords);
    }

    public boolean checkExistCoordsRight() {
        int[] rightCoords = new int[2];

        //check above the robot
        int rightRobotCol = robotStartCol + 1;

        rightCoords[0] = rightRobotCol;
        rightCoords[1] = robotStartRow;

        return checkExistCoords(rightCoords);
    }

    public void hitWall(String direction) {
        System.out.println(ANSI_RED + "Oops! Robot hits a wall. The robot is going back" + ANSI_RESET);

        directionStack.pop();
        numOfDirections--;


        visitedCoordinates[numOfCoords][0] = robotStartCol;
        visitedCoordinates[numOfCoords][1] = robotStartRow;

        switch (direction) {
            case "UP" -> {
                System.out.println(ANSI_YELLOW + "Going back DOWN" + ANSI_RESET);
                robotStartRow++;
            }
            case "RIGHT" -> {
                System.out.println(ANSI_YELLOW + "Going back LEFT" + ANSI_RESET);
                robotStartCol--;
            }
            case "DOWN" -> {
                System.out.println(ANSI_YELLOW + "Going back UP" + ANSI_RESET);
                robotStartRow--;
            }
            default -> {
                System.out.println(ANSI_YELLOW + "Going back RIGHT" + ANSI_RESET);
                robotStartCol++;
            }
        }

        numOfCoords++;

        assignCoords();

    }

    public void solveDeadEnd() {
        System.out.println(ANSI_RED + "Oops dead-end! Backtracking next step" + ANSI_RESET);
        String lastDirection = directionStack.peek();

        switch (lastDirection) {
            case "UP" -> {
                System.out.println(ANSI_RED + "Backtracking: going DOWN" + ANSI_RESET);
                result = maze.go("DOWN");
                robotStartRow++;
                break;
            }
            case "RIGHT" -> {
                System.out.println(ANSI_RED + "Backtracking: going LEFT" + ANSI_RESET);
                result = maze.go("LEFT");
                robotStartCol--;
                break;
            }
            case "DOWN" -> {
                System.out.println(ANSI_RED + "Backtracking: going UP" + ANSI_RESET);
                result = maze.go("UP");
                robotStartRow--;
                break;
            }
            default -> {
                System.out.println(ANSI_RED + "Backtracking: going RIGHT" + ANSI_RESET);
                result = maze.go("RIGHT");
                robotStartCol++;
                break;
            }
        }

        directionStack.pop();
    }

    public void navigate() {


        visitedCoordinates[0][0] = robotStartCol;
        visitedCoordinates[0][1] = robotStartRow;

        while (!result.equals("win")) {

            assignCoords();


            if (!checkExistCoordsUp()) {
                System.out.println(ANSI_GREEN + "The robot goes up" + ANSI_RESET);
                result = maze.go("UP");
                robotStartRow--;

                directionStack.push("UP");
                numOfDirections++;


                if (result.equals("false")) {
                    hitWall("UP");
                }

            } else if (!checkExistCoordsRight()) {
                System.out.println(ANSI_GREEN + "The robot goes right" + ANSI_RESET);
                result = maze.go("RIGHT");
                robotStartCol++;

                directionStack.push("RIGHT");
                numOfDirections++;

                if (result.equals("false")) {
                    hitWall("RIGHT");
                }



            } else if (!checkExistCoordsDown()) {
                System.out.println(ANSI_GREEN + "The robot goes down" + ANSI_RESET);
                result = maze.go("DOWN");
                robotStartRow++;

                directionStack.push("DOWN");
                numOfDirections++;

                if (result.equals("false")) {
                    hitWall("DOWN");
                }



            } else if (!checkExistCoordsLeft()) {
                System.out.println(ANSI_GREEN + "The robot goes left" + ANSI_RESET);
                result = maze.go("LEFT");
                robotStartCol--;

                directionStack.push("LEFT");
                numOfDirections++;

                if (result.equals("false")) {
                    hitWall("LEFT");
                }

            } else {
                solveDeadEnd();
            }


            visitedCoordinates[numOfCoords][0] = robotStartCol;
            visitedCoordinates[numOfCoords][1] = robotStartRow;


            numOfCoords++;
        }

        System.out.println(ANSI_CYAN + "Moving priorities: UP RIGHT LEFT DOWN. On first time, the robot will bump into walls and walk to dead-ends. " +
                "The shortest way will be the optimal way to reach X based on the path explored by robot" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "After the robot reaches X, the shortest way to X is: " + numOfDirections + " step(s)" + ANSI_RESET);
    }
}
