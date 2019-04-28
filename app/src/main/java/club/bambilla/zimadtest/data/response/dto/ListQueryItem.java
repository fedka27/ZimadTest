package club.bambilla.zimadtest.data.response.dto;

import com.google.gson.annotations.SerializedName;

public class ListQueryItem {
    @SerializedName("url")
    private String url;

    @SerializedName("title")
    private String title;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
