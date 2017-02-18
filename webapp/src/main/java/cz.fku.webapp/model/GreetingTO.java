package cz.fku.webapp.model;

/**
 * Created by Filip on 18.02.2017.
 */
public class GreetingTO {
    private final long id;
    private final String content;

    public GreetingTO(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
