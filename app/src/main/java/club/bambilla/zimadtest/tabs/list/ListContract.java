package club.bambilla.zimadtest.tabs.list;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.List;

import club.bambilla.zimadtest.base.BaseContract;
import club.bambilla.zimadtest.base.ViewProgressListener;
import club.bambilla.zimadtest.models.ListItem;

public interface ListContract {
    interface View extends BaseContract.View,
            ViewProgressListener {

        void showContent(List<ListItem> listItems);

        void showToast(String message);

        void showListError();

    }

    interface Presenter extends BaseContract.Presenter<View> {

        void refresh();

        void onItemClick(ListItem listItem);

    }
}
