package club.bambilla.zimadtest.tabs.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import club.bambilla.zimadtest.R;
import club.bambilla.zimadtest.base.BaseMvpFragmentAbs;
import club.bambilla.zimadtest.models.ListItem;

public class DetailsFragment
        extends BaseMvpFragmentAbs<DetailsContract.View, DetailsContract.Presenter>
        implements DetailsContract.View {
    private static final String TAG = DetailsFragment.class.getSimpleName();
    private static final String KEY_LIST_ITEM = TAG + "_LIST_ITEM";
    private static final String KEY_POSITION = TAG + "_POSITION";

    private ImageView iconImageView;
    private TextView positionTextView, titleTextView;

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
        if (getArguments() == null) {
            throw new IllegalArgumentException("The arguments is null. Create fragment from 'newInstance'");
        }

        ListItem listItem = (ListItem) getArguments().getSerializable(KEY_LIST_ITEM);
        int position = getArguments().getInt(KEY_POSITION);

        return new DetailsPresenter(listItem, position);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initIds(view);
    }

    private void initIds(@NonNull View view) {
        iconImageView = view.findViewById(R.id.image_view_icon);
        positionTextView = view.findViewById(R.id.text_view_position);
        titleTextView = view.findViewById(R.id.text_view_title);
    }

    @Override
    public void showContent(ListItem listItem, int position) {
        Picasso.get()
                .load(listItem.getUrl())
                .placeholder(android.R.drawable.stat_notify_sync)
                .error(android.R.drawable.stat_notify_error)
                .into(iconImageView);

        positionTextView.setText(String.valueOf(position));
        titleTextView.setText(listItem.getTitle());
    }
}
