package club.bambilla.zimadtest.business;

import android.support.annotation.NonNull;

import java.util.List;

import club.bambilla.zimadtest.models.ListItem;

public interface ListInteractor {

    void queryItems(ListType listType,
                    CallBackListener callBackListener);

    void cancel();

    interface CallBackListener{
        void onSuccess(List<ListItem> listItems);

        void onError(Throwable throwable);
    }
}
