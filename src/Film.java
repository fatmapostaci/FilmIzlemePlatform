public class Film {

    String name;
    String type;
    double grade;
    int watchedCounter;

    public Film(String name, String type, double grade, int watchedCounter) {
        this.name = name;
        this.type = type;
        this.grade = grade;
        this.watchedCounter = watchedCounter;
    }

    @Override
    public String toString() {
        return "Film{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", grade=" + grade +
                ", watchedCounter=" + watchedCounter +
                '}';
    }
}
