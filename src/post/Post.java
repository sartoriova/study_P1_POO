package post;

import java.time.LocalDate;

public class Post {
    private final String quote;
    private final LocalDate date;
    private final UserAccount user;
    private int claps;
    private int boos;

    Post(UserAccount user, String quote) {
        this.user = user;
        this.quote = quote;
        this.date = LocalDate.now();
    }

    //todo trocar por string.format
    public String getPostAsString() {
        return String.format("[%s] %s says '%s' | Claps: %d | Boos %d", date, user.getUserName(), quote, claps, boos);
    }

    public void clap() {
        claps++;
    }

    public void boo() {
        boos++;
    }

    public LocalDate getDate() {
        return date;
    }
}
