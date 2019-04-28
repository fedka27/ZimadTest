package club.bambilla.zimadtest.tabs.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    private static final String KEY_BUNDLE_LIST_TYPE = TAG + "_BUNDLE_LIST_TYPE";
    private static final String KEY_BUNDLE_LIST = TAG + "_BUNDLE_LIST";

    private ListType listType;
    private ListInteractor listInteractor;

    private List<ListItem> listItems;

    public ListPresenter(ListType listType, ListInteractor listInteractor) {
        this.listType = listType;
        this.listInteractor = listInteractor;

        this.listItems = new ArrayList<>();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
        if (savedInstance != null){
            listType = (ListType) savedInstance.getSerializable(KEY_BUNDLE_LIST_TYPE);
            listItems = (List<ListItem>) savedInstance.getSerializable(KEY_BUNDLE_LIST);
        }
    }

    @Override
    public void onSaveInstance(@NonNull Bundle outState) {
        super.onSaveInstance(outState);
        outState.putSerializable(KEY_BUNDLE_LIST_TYPE, listType);
        outState.putSerializable(KEY_BUNDLE_LIST, new ArrayList(listItems));
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: type - " + listType);

        if (listItems.isEmpty()) {
            view.showProgress();
            loadItems();
        } else {
            view.showContent(listItems);
        }
    }

    @Override
    public void refresh() {
        loadItems();
    }

    private void loadItems() {
        listInteractor.queryItems(listType, new ListInteractor.CallBackListener() {
            @Override
            public void onSuccess(List<ListItem> items) {
                listItems.clear();
                listItems.addAll(items);

                view.showContent(listItems);

                view.hideProgress();
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                view.showToast(throwable.getMessage());

                if (listItems.isEmpty()) {
                    view.showListError();
                }

                view.hideProgress();
            }
        });
    }

    @Override
    public void onItemClick(ListItem listItem, int position) {
        view.openDetailsScreen(listItem, position);
        //todo open details screen
    }

    @Override
    public void onStop() {
        super.onStop();
        listInteractor.cancel();
    }
}
