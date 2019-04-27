package club.bambilla.zimadtest.business;

import java.util.ArrayList;
import java.util.List;

import club.bambilla.zimadtest.data.response.ListQueryResponse;
import club.bambilla.zimadtest.data.response.dto.ListQueryItem;
import club.bambilla.zimadtest.models.ListItem;

public class ListResponseMapper {
    public List<ListItem> mapData(ListQueryResponse listQueryItems) {
        List<ListItem> listItems = new ArrayList<>();
        for (ListQueryItem queryItem : listQueryItems.getItems()) {
            listItems.add(new ListItem(queryItem.getUrl(), queryItem.getTitle()));
        }
        return listItems;
    }
}
