package club.bambilla.zimadtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import club.bambilla.zimadtest.base.BaseFragment;
import club.bambilla.zimadtest.tabs.TabsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
    }

    private void initFragment() {
        Fragment tabsFragment = getSupportFragmentManager().findFragmentById(R.id.container_content);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (tabsFragment != null) {
            fragmentTransaction.show(tabsFragment);
        } else {
            tabsFragment = TabsFragment.newInstance();
            fragmentTransaction.replace(R.id.container_content, tabsFragment);
        }

        fragmentTransaction
                .disallowAddToBackStack()
                .commit();
    }

    @Override
    public void onBackPressed() {
        @Nullable BaseFragment currentFragment = (BaseFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container_content);

        if (currentFragment != null) {
            if (!currentFragment.handleOnBack()) {
                handlePopBackStack();
            }
        } else {
            handlePopBackStack();
        }

    }

    private void handlePopBackStack() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
