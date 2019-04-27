package club.bambilla.zimadtest.tabs.list;

import java.util.List;

import club.bambilla.zimadtest.base.BaseContract;
import club.bambilla.zimadtest.base.ViewProgressListener;
import club.bambilla.zimadtest.models.ListItem;

public interface ListContract {
    interface View extends BaseContract.View,
            ViewProgressListener {

        void showError(String message);

        void setItems(List<ListItem> listItems);
    }

    interface Presenter extends BaseContract.Presenter<View> {

    }
}
