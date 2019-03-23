package com.dinesh_singh.mobdoctor;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.dinesh_singh.mobdoctor.databinding.FragmentSignUpBinding;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_SignUp extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    FragmentSignUpBinding binding;

    String[] country_codes={"+91","+62","+64","+99","+84","+93","+23","+76","+44","+72","+56"};


    DatePickerDialog dpd;
    private Calendar mCalendar;

    public Fragment_SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment__sign_up, container, false);
        View v=binding.getRoot();


     //   ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,Android.R.layout.select_dialog_item,code);

   //     ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.select_dialog_item,code);

        //autoCompleteTextView.setThreshold(1); ////will start working from first character
     //   autoCompleteTextView.setAdapter(arrayAdapter);

        binding.signIn.setOnClickListener(this);
        binding.calender1.setOnClickListener(this);
        binding.calender2.setOnClickListener(this);
        binding.backButton.setOnClickListener(this);


      //  binding.spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.select_dialog_item,country_codes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(arrayAdapter);


        return v;
    }

    @Override
    public void onClick( View v ) {

        mCalendar = Calendar.getInstance();
        final int mYear = mCalendar.get(Calendar.YEAR);
        final int mMonth = mCalendar.get(Calendar.MONTH);
        final int mDay = mCalendar.get(Calendar.DAY_OF_MONTH);

        switch (v.getId())
        {
            case R.id.signIn:
                        getFragmentManager().beginTransaction().replace(R.id.place_holder,new FragmentLogin()).addToBackStack("Rupesh").commit();
                        break;

            case R.id.calender1:



                        dpd =new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet( DatePicker view, int year, int month, int day ) {

                                binding.dob.setText(day+"/"+(month+1)+"/"+year);
                            }
                        },mYear,mMonth,mDay);
                        dpd.show();

                        break;

            case R.id.calender2:

                        dpd =new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet( DatePicker view, int year, int month, int day ) {

                                binding.etPracticeStart.setText(day+"/"+(month+1)+"/"+year);
                            }
                        },mYear,mMonth,mDay);
                        dpd.show();

                        break;
            case R.id.back_button:
                getFragmentManager().beginTransaction().replace(R.id.place_holder,new FragmentLogin()).addToBackStack("").commit();
                break;




        }

    }

    @Override
    public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {

        Toast.makeText(getContext(), "ok bhai", Toast.LENGTH_SHORT).show();

    }
}
