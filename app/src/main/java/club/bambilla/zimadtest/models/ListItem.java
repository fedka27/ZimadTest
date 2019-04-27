package club.bambilla.zimadtest.models;

import java.io.Serializable;

public class ListItem implements Serializable {
    private String url;
    private String title;

    public ListItem(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
