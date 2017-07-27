import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Shivashriganesh Mahato
 */
public class Path {
    private final int Width;
    private final int Height;

    private int[][] map;
    private Vector curPos;
    private Directions[] moveQueue;
    private ArrayList<Directions> posMoves;

    public Path(int width, int height, int startX, int startY) {
        Width = width;
        Height = height;

        map = new int[Height][Width];
        curPos = new Vector(startX, startY);
        moveQueue = new Directions[2];
        posMoves = new ArrayList<Directions>();
    }

    public void generate() {
        while (curPos.x != map[0].length - 1) {
            posMoves.clear();
            posMoves.addAll(Arrays.asList(Directions.NORTH, Directions.SOUTH, Directions.EAST));

            for (Directions _move : moveQueue) {
                if (_move != null) {
                    posMoves.remove(_move.getOpposite());
                }
            }

            Iterator<Directions> iter = posMoves.iterator();

            while (iter.hasNext()) {
                Directions posMove = iter.next();
                int x = posMove.coorsAfterMove(curPos).x;
                int y = posMove.coorsAfterMove(curPos).y;

                if (x == -1 || x == map[0].length || y == -1 || y == map.length)
                    iter.remove();
            }

            moveQueue[0] = moveQueue[1];
            try {
                moveQueue[1] = posMoves.get(ThreadLocalRandom.current().nextInt(0, posMoves.size()));
            } catch (IllegalArgumentException e) {
                // IllegalArgumentException occurs with random generation when the max is less than or equal to the min
                // When there are no possible moves, the max is 0 (same as min)
                // So, reset when there are no more possible moves
                reset();
                continue;
            }

            map[curPos.y][curPos.x] = 1;

            curPos.x = moveQueue[1].coorsAfterMove(curPos).x;
            curPos.y = moveQueue[1].coorsAfterMove(curPos).y;
        }
    }

    public void print() {
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                if (map[r][c] == 0)
                    System.out.print("_ ");
                else
                    System.out.print("O ");
            }
            System.out.println();
        }
    }

    public void reset() {
        moveQueue = new Directions[2];
        map = new int[Height][Width];
        curPos.set(0, 0);
    }
}
