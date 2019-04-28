package club.bambilla.zimadtest.tabs.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import club.bambilla.zimadtest.base.BaseMvpPresenterAbs;
import club.bambilla.zimadtest.models.ListItem;

public class DetailsPresenter
        extends BaseMvpPresenterAbs<DetailsContract.View>
        implements DetailsContract.Presenter {
    private static final String TAG = DetailsPresenter.class.getSimpleName();
    private static final String KEY_BUNDLE_LIST_ITEM = TAG + "_BUNDLE_LIST_ITEM";
    private static final String KEY_BUNDLE_POSITION = TAG + "_POSITION";

    private ListItem listItem;
    private int position;

    public DetailsPresenter(ListItem listItem, int position) {
        this.listItem = listItem;
        this.position = position;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
        if (savedInstance != null) {
            listItem = (ListItem) savedInstance.getSerializable(KEY_BUNDLE_LIST_ITEM);
            position = savedInstance.getInt(KEY_BUNDLE_POSITION);
        }

        view.showContent(listItem, position);
    }

    @Override
    public void onSaveInstance(@NonNull Bundle outState) {
        super.onSaveInstance(outState);
        outState.putSerializable(KEY_BUNDLE_LIST_ITEM, listItem);
        outState.putInt(KEY_BUNDLE_POSITION, position);
    }
}
