package in.ponshere.fragmentbackstack.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Ponsuyambu
 * @since 5/2/17.
 */

public class BaseActivity extends AppCompatActivity implements BaseFragmentCommunicator, FragmentManager.OnBackStackChangedListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    public void onBackPressed() {

        if(getContainerId() != 0 && processBackNavigation()){
            //Back handled by child, hence do nothing
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public int getContainerId() {
        return 0;
    }

    @Override
    public boolean onFragmentBackPressed() {
        //There is no value for this method inside activity
        return false;
    }

    @Override
    public void onBackStackChanged(int backStackEntryCount) {

    }

    @Override
    public void onBackStackChanged() {
        BaseFragmentCommunicator communicator = (BaseFragmentCommunicator)getSupportFragmentManager()
                .findFragmentById(getContainerId());
        if(communicator != null && communicator.onFragmentBackPressed()){
            communicator.onBackStackChanged(getSupportFragmentManager().getBackStackEntryCount());
        }
    }

    @Override
    public boolean processBackNavigation() {
        boolean isBackHandledByChild = false;
        BaseFragmentCommunicator communicator = (BaseFragmentCommunicator)getSupportFragmentManager()
                .findFragmentById(getContainerId());
        if(communicator != null){
            isBackHandledByChild = communicator.processBackNavigation();
        }
        return isBackHandledByChild;
    }

    @Override
    public boolean isContainerFragment() {
        return false;
    }
}
