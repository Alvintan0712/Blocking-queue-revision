import java.util.concurrent.*;

public class GameProducer implements Runnable {
    private Student student;
    private int delay;
    private int index;
    private BlockingQueue<Integer> queue;

    public GameProducer(Student student, int delay, int index, BlockingQueue<Integer> queue) {
        this.student = student;
        this.delay = delay;
        this.index = index;
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.printf("%s start the game!\n", this.student.getFullName());
        while (true) {
            if (Team.isStopRequested()) break;
            Integer i = index;
            try {
                queue.put(i);
            } catch (InterruptedException e) {

            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException error) {

            }
       }
       System.out.printf("Producer %s Thread ended!\n", this.student.getFullName());
    }
}
