package in.ponshere.fragmentbackstack.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

/**
 * @author Ponsuyambu
 * @since 5/2/17.
 */

public class BaseFragment extends Fragment implements BaseFragmentCommunicator, FragmentManager.OnBackStackChangedListener {
    protected boolean isAlreadyCreated = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            isAlreadyCreated = savedInstanceState.getBoolean("isAlreadyCreated",false);
        }
        getChildFragmentManager().addOnBackStackChangedListener(this);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("isAlreadyCreated",isAlreadyCreated);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        isAlreadyCreated  = true;
    }

    @Override
    public int getContainerId() {
        return 0;
    }

    public boolean isContainerFragment(){
        return false;
    }

    public boolean processBackNavigation(){
        boolean isBackHandledByChild = false;
        if(getContainerId() == 0){
            isBackHandledByChild = false;
        }
        else if(getChildFragmentManager().findFragmentById(getContainerId()) != null){
            BaseFragmentCommunicator communicator = (BaseFragmentCommunicator)getChildFragmentManager().findFragmentById(getContainerId());
            isBackHandledByChild = communicator.processBackNavigation();
            if (!isBackHandledByChild) { //if child not handled
                if (getChildFragmentManager().getBackStackEntryCount() > 0) {
                    getChildFragmentManager().popBackStack();
                    isBackHandledByChild = true;
                } else {
                    isBackHandledByChild = false;
                }

            }
        }
        //if the child did not handle the back, ask the parent to handle
        isBackHandledByChild =  !isBackHandledByChild ? onFragmentBackPressed() : isBackHandledByChild;
        Log.d("FragmentBackStack","processBackNavigation invoked in - "+this+";; Return value = "+isBackHandledByChild);
        return isBackHandledByChild;
    }

    @Override
    public boolean onFragmentBackPressed() {
        Log.d("FragmentBackStack","onFragmentBackPressed invoked in - "+this);
        return false;
    }

    @Override
    public void onBackStackChanged(int backStackEntryCount) {
        Log.d("FragmentBackStack","onBackStackChanged invoked in - "+this+";;; backStackEntryCount - "+backStackEntryCount);
    }

    @Override
    public final void onBackStackChanged() {
        if (getChildFragmentManager().findFragmentById(getContainerId()) != null) {
            BaseFragmentCommunicator tabContainerCommunicator = (BaseFragmentCommunicator) getChildFragmentManager().findFragmentById(getContainerId());
            tabContainerCommunicator.onBackStackChanged(getChildFragmentManager().getBackStackEntryCount());
        }
    }
}
