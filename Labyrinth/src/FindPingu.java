public class FindPingu extends Maze {
    static int[][] maze;

    public static void main(String[] args) {
        int width = readInt("Width of the labyrinth?");
        int height = readInt("Height of the labyrinth?");
        int maxDistance = readInt("How far do you dare to go?");
        if (width < 3 || height < 3 || maxDistance < 1) {
            write("Erroneous input!");
            return;
        }
        // always generates the same maze.
        maze = generateStandardPenguinMaze(width, height);
        // generaty random maze.
        // maze = generatePenguinMaze(width, height);
        int penguins = walk(1, 0, maxDistance);
        System.out.println("Rescued pinguins: " + penguins);
    }

    public static int walk(int x, int y, int maxDistance) {
        int numberOfPenguinsFound = 0;
        if(maxDistance < 0 )
            return 0;
        if(x < 0 || y < 0 || x >= maze.length || y >= maze[0].length){
            return 0;
        }
        if(maze[x][y] == OLD_PATH_ACTIVE || maze[x][y] == OLD_PATH_DONE){
            return 0;
        }
        if(maze[x][y] == PENGUIN)
            numberOfPenguinsFound  = 1;

        maze[x][y] = PLAYER;
        draw(maze);
        maze[x][y] = OLD_PATH_ACTIVE;

        numberOfPenguinsFound += walk(x + 1, y,  maxDistance - 1);

        maze[x][y] = PLAYER;
        draw(maze);
        maze[x][y] = OLD_PATH_ACTIVE;
        numberOfPenguinsFound += walk(x,  y + 1,  maxDistance - 1);

        maze[x][y] = PLAYER;
        draw(maze);
        maze[x][y] = OLD_PATH_ACTIVE;
        numberOfPenguinsFound += walk(x - 1, y, maxDistance - 1);

        maze[x][y] = PLAYER;
        draw(maze);
        maze[x][y] = OLD_PATH_DONE;

        numberOfPenguinsFound += walk(x, y - 1, maxDistance - 1);
        return numberOfPenguinsFound;

    }

}