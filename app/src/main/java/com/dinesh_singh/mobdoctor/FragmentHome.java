package com.dinesh_singh.mobdoctor;


import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dinesh_singh.mobdoctor.databinding.FragmentHomeBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment implements  View.OnClickListener{

    FragmentHomeBinding binding;


    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        // Inflate the layout for this fragment

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);

        View v=binding.getRoot();


        binding.patient.setOnClickListener(this);
        binding.doctor.setOnClickListener(this);
        binding.pharmacist.setOnClickListener(this);
        binding.laboratory.setOnClickListener(this);
        binding.patientFrame2.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick( View v ) {

        switch (v.getId()) {
            case R.id.patient:

                unSelect();
                binding.patientFrame2.setVisibility(View.VISIBLE);
                break;
            case R.id.doctor:

                unSelect();
                binding.doctorFrame2.setVisibility(View.VISIBLE);
                getFragmentManager().beginTransaction().replace(R.id.place_holder,new FragmentLogin()).addToBackStack("chhutki").commit();


                break;

            case R.id.pharmacist:
                unSelect();
                binding.pharmacistFrame2.setVisibility(View.VISIBLE);
                break;

            case R.id.laboratory:

                unSelect();
                binding.laboratoryFrame2.setVisibility(View.VISIBLE);
                break;

            default:
                 break;

        }

    }


    private void unSelect()
    {

        binding.doctorFrame2.setVisibility(View.GONE);
        binding.patientFrame2.setVisibility(View.GONE);

        binding.pharmacistFrame2.setVisibility(View.GONE);
        binding.laboratoryFrame2.setVisibility(View.GONE);

    }


}
