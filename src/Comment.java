public class Comment {

    Film film;
    Audience audience;
    String comment;

    public Comment(Audience audience, Film film, String comment) {
        this.film = film;
        this.audience = audience;
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
