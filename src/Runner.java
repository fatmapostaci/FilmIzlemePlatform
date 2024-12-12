import java.util.List;

/**
 * Runner classındaki main methodu console output verir.
 * FilmCommentApp altındaki main methodu çalıştırılırsa Frame arayüzü ile görsel olarak veri girişi yapabiliriz
 */
public class Runner {
    public static void main(String[] args) {

        //create film object
        Film titanic = new Film("Titanic", "Romantic" ,8.5,5400);
        Film lookUp = new Film("Look Up", "Animation",7,1200);

        //create audience and add films that he watched
        Audience fatma = new Audience("Fatma","ftm@gmail.com");
        fatma.filmList.addAll(List.of(titanic,lookUp));
        System.out.println(fatma);

        //make comment for a film
        Comment commentFatma = new Comment( fatma ,lookUp,"So exciting");
        System.out.println(commentFatma);
    }

}