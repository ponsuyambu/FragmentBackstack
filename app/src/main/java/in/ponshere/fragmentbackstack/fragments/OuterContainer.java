package in.ponshere.fragmentbackstack.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.ponshere.fragmentbackstack.R;
import in.ponshere.fragmentbackstack.core.BaseFragment;

/**
 *
 * @author Ponsuyambu
 * @since 4/2/17.
 */

public class OuterContainer extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_container,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvContainerName = (TextView) view.findViewById(R.id.tvContainerName);
        tvContainerName.setText("Outer Container");
        if(!isAlreadyCreated){
            getChildFragmentManager().beginTransaction().replace(getContainerId(),new FragmentA()).commit();
        }
    }



    @Override
    public int getContainerId() {
        return R.id.rlFragmentContainer;
    }
}
