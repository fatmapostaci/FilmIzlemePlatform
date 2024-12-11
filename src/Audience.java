import java.util.ArrayList;
import java.util.List;

public class Audience {

    String name;
    int age;
    List<Film> filmList;

    public Audience(String name, int age) {
        this.name = name;
        this.age = age;
        this.filmList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Audience{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", filmList= " + watchedFilmsNames(this.filmList) +
                '}';
    }

    private String watchedFilmsNames(List<Film> filmList){
        StringBuilder filmNames = new StringBuilder();
        for (Film f : filmList)
            filmNames.append(f.name).append(" ");
        return filmNames.toString();
    }
}
