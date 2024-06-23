public class Team {
    private static final int SCORE_THRESHOLD = 10;
    private static volatile boolean stopRequested = false;

    private int score;
    private String name;

    public Team(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return this.name;
    }

    public synchronized boolean addScore() {
        this.score++;
        if (this.score >= SCORE_THRESHOLD) {
            stopRequested = true;
            return true;
        }
        return false;
    }

    public int getScore() {
        return this.score;
    }

    public static boolean isStopRequested() {
        return stopRequested;
    }
}
