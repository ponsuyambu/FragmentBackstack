package in.ponshere.fragmentbackstack.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.ponshere.fragmentbackstack.R;
import in.ponshere.fragmentbackstack.core.BaseFragment;
import in.ponshere.fragmentbackstack.core.BaseFragmentCommunicator;

/**
 * https://medium.com/google-developers/making-loading-data-on-android-lifecycle-aware-897e12760832#.fgxj7kl6w
 *
 * @author Ponsuyambu
 * @since 4/2/17.
 */

public class FragmentB1 extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b1,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnStartFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(((BaseFragmentCommunicator)(getParentFragment())).getContainerId(), new FragmentA1())
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

}
