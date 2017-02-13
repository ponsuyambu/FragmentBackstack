package in.ponshere.fragmentbackstack;

import android.os.Bundle;

import in.ponshere.fragmentbackstack.core.BaseActivity;
import in.ponshere.fragmentbackstack.fragments.OuterContainer;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.rlContainer,new OuterContainer()).commit();
    }

    @Override
    public int getContainerId() {
        return R.id.rlContainer;
    }
}
