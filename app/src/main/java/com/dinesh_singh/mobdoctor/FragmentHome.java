package com.dinesh_singh.mobdoctor;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        // Inflate the layout for this fragment

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);

        View v=binding.getRoot();


        binding.patient.setOnClickListener(this);
        binding.doctor.setOnClickListener(this);
        binding.pharmacist.setOnClickListener(this);
        binding.laboratory.setOnClickListener(this);
        binding.frameLayoutPatientHidden.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick( View v ) {

        switch (v.getId()) {
            case R.id.patient:

                unSelect();
                binding.frameLayoutPatientHidden.setVisibility(View.VISIBLE);
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.place_holder,new PatientLoginFragment()).addToBackStack("").commit();
                break;
            case R.id.doctor:

                unSelect();
                binding.frameLayoutDoctorHidden.setVisibility(View.VISIBLE);
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.place_holder,new FragmentLogin()).addToBackStack("").commit();

                break;

            case R.id.pharmacist:
                unSelect();
                binding.frameLayoutPharmacistHidden.setVisibility(View.VISIBLE);
                break;

            case R.id.laboratory:

                unSelect();
                binding.frameLayoutLaboratoryHidden.setVisibility(View.VISIBLE);
                break;

            default:
                 break;

        }

    }


    private void unSelect()
    {

        binding.frameLayoutDoctorHidden.setVisibility(View.GONE);
        binding.frameLayoutPatientHidden.setVisibility(View.GONE);

        binding.frameLayoutPharmacistHidden.setVisibility(View.GONE);
        binding.frameLayoutLaboratoryHidden.setVisibility(View.GONE);

    }


}
