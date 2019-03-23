package com.dinesh_singh.mobdoctor;


import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dinesh_singh.mobdoctor.databinding.FragmentHomeBinding;
import com.dinesh_singh.mobdoctor.databinding.FragmentLoginBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment implements View.OnClickListener{

    FragmentLoginBinding binding;


    public FragmentLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        // Inflate the layout for this fragment



      binding= DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false);
      View v=binding.getRoot();

      binding.b1.setOnClickListener(this);
      binding.tvSignUp.setOnClickListener(this);

      binding.backButton.setOnClickListener(this);

       //binding.et1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

       return v;
    }


    @Override
    public void onClick( View v ) {

        switch (v.getId())
        {
            case R.id.tv_sign_up:
                getFragmentManager().beginTransaction().replace(R.id.place_holder,new Fragment_SignUp()).addToBackStack("").commit();
                break;

            case R.id.back_button:
                getFragmentManager().beginTransaction().replace(R.id.place_holder,new FragmentHome()).addToBackStack("").commit();
                break;
        }


    }
}
