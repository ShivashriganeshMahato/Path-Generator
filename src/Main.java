import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Shivashriganesh Mahato
 */
public class Main {
    public static void main(String[] args) {
        Path path = new Path(25, 25, 0, 0);

        for (int z = 0; z < 5; z++) {
            path.generate();
            path.print();
            path.reset();
            System.out.println();
        }
    }
}
