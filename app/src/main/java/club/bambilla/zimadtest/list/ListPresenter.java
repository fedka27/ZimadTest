package club.bambilla.zimadtest.list;

import android.util.Log;

import club.bambilla.zimadtest.base.BaseMvpPresenterAbs;

public class ListPresenter
        extends BaseMvpPresenterAbs<ListContract.View>
        implements ListContract.Presenter {
    private static final String TAG = ListPresenter.class.getSimpleName();

    private ListType listType;

    public ListPresenter(ListType listType) {
        this.listType = listType;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: type - " + listType);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: type - " + listType);
    }
}
