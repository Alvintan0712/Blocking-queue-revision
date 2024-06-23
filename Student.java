public class Student {
    private String firstName;
    private String lastName;
    private int score;
    private Team team;

    public Student(String firstName, String lastName, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
        this.score = 0;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public boolean addScore() {
        this.score++;
        return this.team.addScore();
    }

    public int getScore() {
        return this.score;
    }
}
