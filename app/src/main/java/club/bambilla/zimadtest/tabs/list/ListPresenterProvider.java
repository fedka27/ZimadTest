package club.bambilla.zimadtest.tabs.list;

import android.support.annotation.Nullable;

import club.bambilla.zimadtest.business.ListInteractor;
import club.bambilla.zimadtest.business.ListInteractorProvider;
import club.bambilla.zimadtest.business.ListType;

public class ListPresenterProvider {
    @Nullable
    private static ListContract.Presenter presenter;

    public static ListContract.Presenter providePresenter(ListType listType) {
        if (presenter == null) {
            ListInteractor listInteractor = ListInteractorProvider.provideListInteractor();
            presenter = new ListPresenter(listType, listInteractor);
        }
        return presenter;
    }
}
