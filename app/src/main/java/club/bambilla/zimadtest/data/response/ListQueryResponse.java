package club.bambilla.zimadtest.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import club.bambilla.zimadtest.data.response.dto.ListQueryItem;

public class ListQueryResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<ListQueryItem> items;

    public String getMessage() {
        return message;
    }

    public List<ListQueryItem> getItems() {
        return items;
    }
}
