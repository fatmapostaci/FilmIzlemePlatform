import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Runner {
    public static void main(String[] args) {

        //create film object
        Film titanic = new Film("Titanic", "dram",8.5,5400);
        Film lookUp = new Film("Look Up", "animation",7,1200);

        //create audience and add films that he watched
        Audience fatma = new Audience("Fatma",20);
        fatma.filmList.addAll(List.of(titanic,lookUp));
        System.out.println(fatma);

        //make comment for a film
        Comment commentFatma = new Comment( fatma ,lookUp,"So exciting");
        System.out.println(commentFatma);
    }

}