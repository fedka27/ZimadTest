package club.bambilla.zimadtest.base;

import android.support.v4.app.Fragment;

public abstract class BaseFragment
        extends Fragment
        implements FragmentBackClickListener{

    @Override
    public boolean handleOnBack() {
        return false;
    }
}
