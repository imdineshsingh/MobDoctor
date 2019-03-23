package com.dinesh_singh.mobdoctor;

import android.app.DatePickerDialog;
import android.content.Context;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.DatePicker;

import com.dinesh_singh.mobdoctor.databinding.FragmentSignUpBinding;

import java.util.Calendar;

public class DatePickerClass
{

/*
    public DatePickerDialog datePickerDialog( Context context, final ScriptGroup.Binding  binding, View view )
    {


        DatePickerDialog dpd;
        Calendar mCalendar;





        mCalendar = Calendar.getInstance();
        final int mYear = mCalendar.get(Calendar.YEAR);
        final int mMonth = mCalendar.get(Calendar.MONTH);
        final int mDay = mCalendar.get(Calendar.DAY_OF_MONTH);

        dpd =new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day ) {

                binding.view.setText(day+"/"+(month+1)+"/"+year);
            }
        },mYear,mMonth,mDay);
        dpd.show();


        return dpd;

    }

    */

}
