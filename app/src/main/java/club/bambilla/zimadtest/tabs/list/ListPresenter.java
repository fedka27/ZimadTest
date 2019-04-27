package club.bambilla.zimadtest.tabs.list;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import club.bambilla.zimadtest.base.BaseMvpPresenterAbs;
import club.bambilla.zimadtest.business.ListInteractor;
import club.bambilla.zimadtest.business.ListType;
import club.bambilla.zimadtest.models.ListItem;

public class ListPresenter
        extends BaseMvpPresenterAbs<ListContract.View>
        implements ListContract.Presenter {
    private static final String TAG = ListPresenter.class.getSimpleName();

    private ListType listType;
    private ListInteractor listInteractor;

    private List<ListItem> listItems;

    public ListPresenter(ListType listType, ListInteractor listInteractor) {
        this.listType = listType;
        this.listInteractor = listInteractor;

        this.listItems = new ArrayList<>();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: type - " + listType);

        if (listItems.isEmpty()) {
            view.showProgress();
            listInteractor.queryItems(listType, new ListInteractor.CallBackListener() {
                @Override
                public void onSuccess(List<ListItem> items) {
                    listItems.clear();
                    listItems.addAll(items);

                    view.setItems(listItems);

                    view.hideProgress();
                }

                @Override
                public void onError(Throwable throwable) {
                    throwable.printStackTrace();
                    view.showError(throwable.getMessage());

                    view.hideProgress();
                }
            });
        } else {
            view.setItems(listItems);
        }
    }
}
