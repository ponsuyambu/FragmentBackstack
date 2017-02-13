package in.ponshere.fragmentbackstack.core;

/**
 * @author Ponsuyambu
 * @since 5/2/17.
 */

public interface BaseFragmentCommunicator {
    int getContainerId();
    boolean onFragmentBackPressed();

    /**
     * This will be invoked by the parent activity/fragment on change in back stack.
     * @param backStackEntryCount number of entries in back stack
     */
    void onBackStackChanged(int backStackEntryCount);
    boolean processBackNavigation();
    boolean isContainerFragment();
}
