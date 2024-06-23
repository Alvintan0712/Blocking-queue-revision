import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

        List<Team> teams = Arrays.asList(
            new Team("Team 1"),
            new Team("Team 2")
        );

        List<Student> students = Arrays.asList(
            new Student("Alvin", "Tan", teams.get(0)),
            new Student("Jeremy", "Tan", teams.get(0)),
            new Student("Samuel", "Thien", teams.get(1)),
            new Student("Jeffrey", "Sean", teams.get(1))
        );

        List<Thread> producerThreads = Arrays.asList(
            new Thread(new GameProducer(students.get(0), 1, 0, queue)),
            new Thread(new GameProducer(students.get(1), 1, 1, queue)),
            new Thread(new GameProducer(students.get(2), 1, 2, queue)),
            new Thread(new GameProducer(students.get(3), 1, 3, queue))
        );

        Thread consumerThread = new Thread(new GameConsumer(1, students, queue));
        consumerThread.start();

        for (Thread t : producerThreads) t.start();
        
        try {
            consumerThread.join();
            for (Thread t : producerThreads) t.interrupt();
        } catch (InterruptedException e) {
            System.out.println(consumerThread.getName() + " interrupted.");
        }
    
        System.out.println("=========== Score Board ===========");

        for (Team t : teams) System.out.printf("%s: %d\n", t.getName(), t.getScore());
        for (Student s : students) System.out.printf("%s: %d\n", s.getFullName(), s.getScore());

        System.out.println("Game Ended");
    }
}
