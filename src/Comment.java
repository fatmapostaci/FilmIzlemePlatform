public class Comment {

    Film film;
    Audience audience;
    String comment;

    public Comment(Audience audience, Film film, String comment) {

        this.audience = audience;
        this.film = film;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                " audience=" + audience.name +
                " film=" + film.name +
                " comment='" + comment + '\'' +
                '}';
    }

}
