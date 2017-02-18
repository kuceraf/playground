package cz.fku.webapp.model;

/**
 * Created by Filip on 18.02.2017.
 */
public class GreetingTO {
    private  long id;
    private String content;

    public GreetingTO() {

    }

    public GreetingTO(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
