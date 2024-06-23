import java.util.*;
import java.util.concurrent.*;

public class GameConsumer implements Runnable {
    private static final int SCORE_THRESHOLD = 10;
    private static volatile boolean stopProducing = false;

    private int delay;
    private List<Student> students;
    private BlockingQueue<Integer> queue;

    public GameConsumer(int delay, List<Student> students, BlockingQueue<Integer> queue) {
        this.delay = delay;
        this.students = students;
        this.queue = queue;
    }

    @Override
    public void run() {
        int index;
        try {
            while ((index = queue.take()) != -1) {
                Student student = students.get(index);
                if (student.addScore()) {
                    stopProducing = true;
                    break;
                }
                System.out.printf("%s win the game: %d\n", student.getFullName(), student.getScore());
                Thread.sleep(delay);
            }
            System.out.println("Consumer Thread ended!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
