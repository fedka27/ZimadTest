package club.bambilla.zimadtest.tabs.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import club.bambilla.zimadtest.R;
import club.bambilla.zimadtest.base.BaseMvpFragmentAbs;

public class ListFragment
        extends BaseMvpFragmentAbs<ListContract.View, ListContract.Presenter>
        implements ListContract.View {

    private static final String TAG = ListFragment.class.getSimpleName();
    private static final String KEY_EXTRA_LIST_TYPE = TAG + "_EXTRA_LIST_TYPE";

    public static ListFragment getInstance(@NonNull ListType listType) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(KEY_EXTRA_LIST_TYPE, listType);

        ListFragment listFragment = new ListFragment();
        listFragment.setArguments(arguments);

        return listFragment;
    }

    @Override
    protected club.bambilla.zimadtest.tabs.list.ListContract.Presenter getPresenter() {
        if (getArguments() == null) {
            throw new IllegalArgumentException("The arguments is null. Create fragment from 'newInstance'");
        }
        ListType listType = (ListType) getArguments().getSerializable(KEY_EXTRA_LIST_TYPE);

        return ListPresenterProvider.providePresenter(listType);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
