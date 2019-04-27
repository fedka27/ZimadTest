package club.bambilla.zimadtest.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ListPresenterProvider {
    @Nullable
    private static ListContract.Presenter presenter;

    public static ListContract.Presenter providePresenter(ListType listType) {
        if (presenter == null) {
            presenter = new ListPresenter(listType);
        }
        return presenter;
    }
}
