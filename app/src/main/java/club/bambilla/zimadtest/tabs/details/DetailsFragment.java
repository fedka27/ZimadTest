package club.bambilla.zimadtest.tabs.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import club.bambilla.zimadtest.R;
import club.bambilla.zimadtest.base.BaseMvpFragmentAbs;
import club.bambilla.zimadtest.models.ListItem;

public class DetailsFragment
        extends BaseMvpFragmentAbs<DetailsContract.View, DetailsContract.Presenter>
        implements DetailsContract.View {
    private static final String TAG = DetailsFragment.class.getSimpleName();
    private static final String KEY_LIST_ITEM = TAG + "_LIST_ITEM";
    private static final String KEY_POSITION = TAG + "_POSITION";

    public static DetailsFragment newInstance(ListItem listItem, int position) {

        Bundle args = new Bundle();
        args.putSerializable(KEY_LIST_ITEM, listItem);
        args.putSerializable(KEY_POSITION, position);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected DetailsContract.Presenter getPresenter() {
        return new DetailsPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details_item, container, false);
    }
}
