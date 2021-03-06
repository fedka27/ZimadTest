package club.bambilla.zimadtest.tabs;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import club.bambilla.zimadtest.R;
import club.bambilla.zimadtest.base.BaseFragment;
import club.bambilla.zimadtest.tabs.list.ListFragment;
import club.bambilla.zimadtest.business.ListType;

public class TabsFragment extends BaseFragment {
    private static final String TAG = TabsFragment.class.getSimpleName();
    private static final String KEY_BUNDLE_SELECTED_POSITION = TAG + "_BUNDLE_SELECTED_POSITION";

    private static final String FRAGMENT_TAG_CATS = TAG + "_CATS";
    private static final String FRAGMENT_TAG_DOGS = TAG + "_DOGS";

    private static final int TAB_POSITION_CATS = 0;
    private static final int TAB_POSITION_DOGS = 1;

    private TabLayout tabLayout;

    private Fragment catsListFragment;
    private Fragment dogsListFragment;

    public static TabsFragment newInstance() {

        Bundle args = new Bundle();

        TabsFragment fragment = new TabsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tabs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initIds(view);
        initFragments();
        initViews();

        selectTab(TAB_POSITION_CATS);
    }

    private void selectTab(int tabPosition) {
        @Nullable TabLayout.Tab tab = tabLayout.getTabAt(tabPosition);
        if (tab == null) {
            throw new IllegalArgumentException("Invalid position of the tab: " + tabPosition);
        }

        tab.select();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            /*Restore tab layout*/
            int selectedPosition = savedInstanceState.getInt(KEY_BUNDLE_SELECTED_POSITION);
            @Nullable TabLayout.Tab selectedTab = tabLayout.getTabAt(selectedPosition);
            if (selectedTab != null) {
                selectedTab.select();
            }
        }
    }

    private void initIds(@NonNull View view) {
        tabLayout = view.findViewById(R.id.tab_layout);
    }

    private void initFragments() {

        catsListFragment = getChildFragmentManager().findFragmentByTag(FRAGMENT_TAG_CATS);
        dogsListFragment = getChildFragmentManager().findFragmentByTag(FRAGMENT_TAG_DOGS);

        @IdRes int containerId = R.id.container_content;
        if (catsListFragment == null) {
            catsListFragment = ListFragment.getInstance(ListType.CATS);

            //init cats fragment
            getChildFragmentManager()
                    .beginTransaction()
                    .add(containerId, catsListFragment, FRAGMENT_TAG_CATS)
                    .detach(catsListFragment)
                    .commit();
        }

        if (dogsListFragment == null) {
            dogsListFragment = ListFragment.getInstance(ListType.DOGS);

            //init dogs fragment
            getChildFragmentManager()
                    .beginTransaction()
                    .add(containerId, dogsListFragment, FRAGMENT_TAG_DOGS)
                    .detach(dogsListFragment)
                    .commit();
        }

    }

    private void initViews() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                handleTabSelected(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /*Init tab of the cats*/
        TabLayout.Tab tabCats = tabLayout.newTab();
        tabCats.setText(R.string.tabs_tab_cats);

        /*Init tab of the dogs*/
        TabLayout.Tab tabDogs = tabLayout.newTab();
        tabDogs.setText(R.string.tabs_tab_dogs);

        tabLayout.addTab(tabCats, TAB_POSITION_CATS, false);
        tabLayout.addTab(tabDogs, TAB_POSITION_DOGS, false);

    }

    private void handleTabSelected(int position) {
        switch (position) {
            case TAB_POSITION_CATS: {
                changeContent(catsListFragment);
                break;
            }
            case TAB_POSITION_DOGS: {
                changeContent(dogsListFragment);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unsupported operation of the tab: " + position);
            }
        }
    }


    private void changeContent(Fragment newFragment) {
        @Nullable Fragment currentFragment =
                getChildFragmentManager().findFragmentById(R.id.container_content);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        if (currentFragment != null) {
            transaction.detach(currentFragment);
        }

        transaction.attach(newFragment);

        transaction.commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_BUNDLE_SELECTED_POSITION, tabLayout.getSelectedTabPosition());
    }

    @Override
    public boolean handleOnBack() {
        @Nullable BaseFragment currentFragment = (BaseFragment) getChildFragmentManager()
                .findFragmentById(R.id.container_content);

        if (currentFragment != null) {
            if (currentFragment.handleOnBack()) {
                return true;
            }
            return handlePopBackStack();
        } else {
            return handlePopBackStack();
        }
    }

    private boolean handlePopBackStack() {
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {

            getChildFragmentManager().popBackStack();
            return true;

        } else {
            return super.handleOnBack();
        }
    }
}
