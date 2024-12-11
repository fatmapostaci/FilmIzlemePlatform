import java.util.List;

public class Film {

    String name;
    String type;
    double grade;
    int watchedCounter;
    Comment comment;

    public Film(String name, String type, double grade, int watchedCounter) {
        this.name = name;
        this.comment = comment;
        this.grade = grade;
        this.type = type;
        this.watchedCounter = watchedCounter;
    }

    @Override
    public String toString() {
        return "Film{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", grade=" + grade +
                ", watchedCounter=" + watchedCounter +
                ", comment=" + comment +
                '}';
    }
}
