package club.bambilla.zimadtest.tabs.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import club.bambilla.zimadtest.R;
import club.bambilla.zimadtest.base.BaseMvpFragmentAbs;
import club.bambilla.zimadtest.business.ListInteractor;
import club.bambilla.zimadtest.business.ListInteractorProvider;
import club.bambilla.zimadtest.business.ListType;
import club.bambilla.zimadtest.models.ListItem;
import club.bambilla.zimadtest.tabs.details.DetailsFragment;
import club.bambilla.zimadtest.tabs.list.adapter.ListItemAdapter;

public class ListFragment
        extends BaseMvpFragmentAbs<ListContract.View, ListContract.Presenter>
        implements ListContract.View {

    private static final String TAG = ListFragment.class.getSimpleName();
    private static final String KEY_EXTRA_LIST_TYPE = TAG + "_EXTRA_LIST_TYPE";

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private View errorListLoad;
    private ProgressBar progressBar;

    private ListItemAdapter listItemAdapter;

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

        ListInteractor listInteractor = ListInteractorProvider.provideListInteractor();
        return new ListPresenter(listType, listInteractor);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initIds(view);
        initRecycler();
        initListeners();
    }

    private void initIds(@NonNull View view) {
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        recyclerView = view.findViewById(R.id.recycler_view);
        errorListLoad = view.findViewById(R.id.content_error_load);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    private void initRecycler() {
        listItemAdapter = new ListItemAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(listItemAdapter);
    }

    private void initListeners() {
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.refresh());
        listItemAdapter.setListItemClickListener((listItem, position) ->
                presenter.onItemClick(listItem, position));
    }

    @Override
    public void showContent(List<ListItem> listItems) {
        listItemAdapter.setListItems(listItems);

        recyclerView.setVisibility(View.VISIBLE);
        errorListLoad.setVisibility(View.GONE);
    }

    @Override
    public void showListError() {
        errorListLoad.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openDetailsScreen(ListItem listItem, int position) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(getId(), DetailsFragment.newInstance(listItem, position))
                .addToBackStack(null)
                .commit();
    }
}
