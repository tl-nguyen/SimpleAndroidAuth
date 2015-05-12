package eu.artviz.simpleandroidauth.navigation;

import eu.artviz.simpleandroidauth.ui.fragments.BaseFragment;

/**
 * Created by tung.lam.nguyen on 12.5.2015 ã..
 */
public class NavigationEvent {
    private BaseFragment mFragment;
    private int mContainerId;

    public NavigationEvent(BaseFragment fragment, int containerId) {
        this.mFragment = fragment;
        this.mContainerId = containerId;
    }

    public BaseFragment getFragment() {
        return mFragment;
    }

    public int getContainerId() {
        return mContainerId;
    }
}
