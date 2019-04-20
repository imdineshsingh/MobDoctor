package com.dinesh_singh.mobdoctor;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.dinesh_singh.mobdoctor.databinding.FragmentSignUpBinding;

import java.util.Calendar;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_SignUp extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    FragmentSignUpBinding binding;

    String[] country_codes={"+91","+62","+64","+99","+84","+93","+23","+76","+44","+72","+56"};
    Calendar mCalendar;
    DatePickerDialog dpd;

    public Fragment_SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        // Inflating the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment__sign_up, container, false);
        View v=binding.getRoot();

        listeners();
        /*calling methods which are declared outside onCreate()*/
        usingSpannableString();
        spinnerCountryCodes();

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
            case R.id.button_register_now:
                validate();
                break;

            case R.id.calender_dob:
                        dpd =new DatePickerDialog(Objects.requireNonNull(getContext()), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet( DatePicker view, int year, int month, int day ) {
                                binding.tvSelectDob.setText(day+"/"+(month+1)+"/"+year);
                            }
                        },mYear,mMonth,mDay);
                        dpd.show();
                        break;

            case R.id.calender_practice_start:
                        dpd =new DatePickerDialog(Objects.requireNonNull(getContext()), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet( DatePicker view, int year, int month, int day ) {
                                binding.selectPracticeStart.setText(day+"/"+(month+1)+"/"+year);
                            }
                        },mYear,mMonth,mDay);
                        dpd.show();
                        break;

            case R.id.image_view_back_button:
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.place_holder,new FragmentLogin()).addToBackStack("").commit();
                break;
        }
    }

    @Override
    public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
        Toast.makeText(getContext(), "item clicked", Toast.LENGTH_SHORT).show();
    }



    private void usingSpannableString()
    {
        /*Spannable String for TextView:already_have_account*/

       // String s= getResources().getString(R.string.account_exist);

        SpannableString spannableString =new SpannableString(getResources().getString(R.string.account_exist));
        ClickableSpan clickableSpan =new ClickableSpan() {
            @Override
            public void onClick(View widget ) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.place_holder,new FragmentLogin()).addToBackStack("").commit();

            }
            @Override
            public void updateDrawState( TextPaint ds ) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.colorOrange));
                // ds.setUnderlineText(false);
            }
        };

        spannableString.setSpan(clickableSpan,25,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.textViewAccountExist.setText(spannableString);
        binding.textViewAccountExist.setMovementMethod(LinkMovementMethod.getInstance());

       /* SpannableString for TextView:terms_and_conditions*/

        SpannableString spannableString2 =new SpannableString(getResources().getString(R.string.tnc));
        ClickableSpan clickableSpan2 =new ClickableSpan() {
            @Override
            public void onClick(View widget ) {
                Toast.makeText(getContext(), "Terms and Conditions Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(  TextPaint ds ) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.colorOrange));
                // ds.setUnderlineText(false);

            }

        };

        spannableString2.setSpan(clickableSpan2,11,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.checkboxTermsAndConditions.setText(spannableString2);
        binding.checkboxTermsAndConditions.setMovementMethod(LinkMovementMethod.getInstance());

    }

    @Override
    public void onActivityResult( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void spinnerCountryCodes()
    {
        /*ArrayAdapter For showing data in the spinner*/
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(Objects.requireNonNull(getActivity()).getApplicationContext(),android.R.layout.select_dialog_item,country_codes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerCountryCode.setAdapter(arrayAdapter);

    }


    private void listeners()
    {

        binding.calenderDob.setOnClickListener(this);
        binding.calenderPracticeStart.setOnClickListener(this);
        binding.imageViewBackButton.setOnClickListener(this);
        binding.buttonRegisterNow.setOnClickListener(this);

        //ADDING TEXTWATCHER
        addTextWatchers();
    }





    private void validate()
    {

        final String password=binding.editTextPassword.getText().toString().trim();
        final String confirmPassword=binding.editTextConfirmPassword.getText().toString().trim();

        if(!isValidName(binding.editTextFirstName.getText().toString().trim())) {
            showError(binding.editTextFirstName,binding.tvErrorFirstName);

        }
        else if (!isValidEmail(binding.editTextEmail.getText().toString().trim())) {
            showError(binding.editTextEmail,binding.tvErrorEmail);
        }
        else if(!isValidPhoneNumber(binding.editTextPhoneNumber.getText().toString().trim())) {
            showError(binding.editTextPhoneNumber,binding.tvErrorPhone);
        }
        else if(!isValidPassword(binding.editTextPassword.getText().toString().trim())) {
            showError(binding.editTextPassword,binding.tvErrorPassword);
        }

        else if(!confirmPassword.equals(password)) {
            showError(binding.editTextConfirmPassword,binding.tvErrorConfirmPassword);
        }
        else if(binding.tvSelectDob.getText().toString().length()==0) {
            showError(binding.tvSelectDob,binding.tvErrorDob);
        }

        else if(binding.selectPracticeStart.getText().toString().length()==0) {
            showError(binding.selectPracticeStart,binding.tvErrorPracticeStartDate);
        }

        else if(binding.editTextAddress.getText().toString().trim().length()==0) {
            showError(binding.editTextAddress,binding.tvErrorAddress);
        }
        else if (binding.editTextEducation.getText().toString().trim().length()==0) {
            showError(binding.editTextEducation,binding.tvErrorEducation);
        }

        else if(!binding.checkboxTermsAndConditions.isChecked()) {
            showError(binding.checkboxTermsAndConditions,binding.tvErrorTermsAndConditions);
        }
        else {
            invisibleErrors();
            Toast.makeText(getContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
         }
    }


    private void showError(View viewRequestFocus,View viewSetVisibility) {
        invisibleErrors();
        viewRequestFocus.requestFocus();
        viewSetVisibility.setVisibility(View.VISIBLE);
    }

    private boolean isValidName(String name) {
        binding.tvErrorFirstName.setVisibility(View.INVISIBLE);

        String PERSON_NAME = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(PERSON_NAME);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private boolean isValidEmail(String email) {
        binding.tvErrorEmail.setVisibility(View.INVISIBLE);

        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        binding.tvErrorPhone.setVisibility(View.INVISIBLE);

        return phoneNumber!=null && Patterns.PHONE.matcher(phoneNumber).matches();
    }


    private boolean isValidPassword(String password) {
        binding.tvErrorPassword.setVisibility(View.INVISIBLE);
        return password.length() >= 8 && password.length() <= 16;
    }


   private void invisibleErrors() {
       binding.tvErrorFirstName.setVisibility(View.INVISIBLE);
       binding.tvErrorPassword.setVisibility(View.INVISIBLE);
       binding.tvErrorPhone.setVisibility(View.INVISIBLE);
       binding.tvErrorConfirmPassword.setVisibility(View.INVISIBLE);
       binding.tvErrorEmail.setVisibility(View.INVISIBLE);
       binding.tvErrorTermsAndConditions.setVisibility(View.INVISIBLE);
       binding.tvErrorDob.setVisibility(View.INVISIBLE);
       binding.tvErrorEducation.setVisibility(View.INVISIBLE);
       binding.tvErrorAddress.setVisibility(View.INVISIBLE);
       binding.tvErrorPracticeStartDate.setVisibility(View.INVISIBLE);
       binding.tvErrorPaymentReference.setVisibility(View.INVISIBLE);
   }



   private void addTextWatchers() {

        /*TextWatcher on FirstName*/
       binding.editTextFirstName.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged( CharSequence s, int start, int count, int after ) {
                            //TODO
           }

           @Override
           public void onTextChanged( CharSequence s, int start, int before, int count ) {


               final String firstName=binding.editTextFirstName.getText().toString().trim();
               if (firstName.isEmpty()) {
                   binding.editTextFirstName.requestFocus();
                   binding.tvErrorFirstName.setVisibility(View.VISIBLE);
                   binding.tvErrorFirstName.setText(getResources().getString(R.string.error_empty_first_name));
               }
               else if(!isValidName(firstName)) {
                   binding.editTextFirstName.requestFocus();
                   binding.tvErrorFirstName.setVisibility(View.VISIBLE);
                   binding.tvErrorFirstName.setText(getResources().getString(R.string.error_first_name));
               }
               else{
                   binding.tvErrorFirstName.setVisibility(View.INVISIBLE);
               }
           }

           @Override
           public void afterTextChanged( Editable s ) {
                    //TODO
           }
       });

       /*TextWatcher on Email*/
       binding.editTextEmail.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged( CharSequence s, int start, int count, int after ) {

           }

           @Override
           public void onTextChanged( CharSequence s, int start, int before, int count ) {
               final String email=binding.editTextEmail.getText().toString().trim();
               if(email.isEmpty())
               {
                   binding.editTextEmail.requestFocus();
                   binding.tvErrorEmail.setVisibility(View.VISIBLE);
                   binding.tvErrorEmail.setText(getResources().getString(R.string.error_empty_email));
               }
               else if(!isValidEmail(email)) {
                   binding.editTextEmail.requestFocus();
                   binding.tvErrorEmail.setVisibility(View.VISIBLE);
                   binding.tvErrorEmail.setText(getResources().getString(R.string.error_email));
               }
               else {
                   binding.tvErrorEmail.setVisibility(View.INVISIBLE);
               }

           }

           @Override
           public void afterTextChanged( Editable s ) {

           }
       });


       /*TextWatcher on Phone Number*/
       binding.editTextPhoneNumber.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged( CharSequence s, int start, int count, int after ) {

           }
           @Override
           public void onTextChanged( CharSequence s, int start, int before, int count ) {
               String phone=binding.editTextPhoneNumber.getText().toString().trim();
               if (phone.isEmpty()) {
                   binding.editTextPhoneNumber.requestFocus();
                   binding.tvErrorPhone.setVisibility(View.VISIBLE);
                   binding.tvErrorPhone.setText(getResources().getString(R.string.error_empty_phone));
               }
               else if(phone.length()<10) {
                   binding.editTextPhoneNumber.requestFocus();
                   binding.tvErrorPhone.setVisibility(View.VISIBLE);
                   binding.tvErrorPhone.setText(getResources().getString(R.string.error_phone));
               }
               else {
                   binding.tvErrorPhone.setVisibility(View.INVISIBLE);
               }
           }

           @Override
           public void afterTextChanged( Editable s ) {

           }
       });


       /*TextWatcher on Password*/
       binding.editTextPassword.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged( CharSequence s, int start, int count, int after ) {

           }

           @Override
           public void onTextChanged( CharSequence s, int start, int before, int count ) {
               final String password=binding.editTextPassword.getText().toString().trim();
               if(password.isEmpty()) {
                   binding.editTextPassword.requestFocus();
                   binding.tvErrorPassword.setVisibility(View.VISIBLE);
                   binding.tvErrorPassword.setText(getResources().getString(R.string.error_empty_password));
               }
               else if(!isValidPassword(password)) {
                   binding.editTextPassword.requestFocus();
                   binding.tvErrorPassword.setVisibility(View.VISIBLE);
                   binding.tvErrorPassword.setText(getResources().getString(R.string.error_password));
               }
               else {
                   binding.tvErrorPassword.setVisibility(View.INVISIBLE);
               }
           }

           @Override
           public void afterTextChanged( Editable s ) {

           }
       });


       /*TextWatcher on ConfirmPassword*/
       binding.editTextConfirmPassword.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged( CharSequence s, int start, int count, int after ) {
           }

           @Override
           public void onTextChanged( CharSequence s, int start, int before, int count ) {
               String confirmPassword=binding.editTextConfirmPassword.getText().toString().trim();
               if(confirmPassword.isEmpty()) {
                   binding.editTextConfirmPassword.requestFocus();
                   binding.tvErrorConfirmPassword.setVisibility(View.VISIBLE);
                   binding.tvErrorConfirmPassword.setText(getResources().getString(R.string.error_empty_confirm_password));
               }
               else if(!confirmPassword.matches(binding.editTextPassword.getText().toString())) {
                   binding.editTextConfirmPassword.requestFocus();
                   binding.tvErrorConfirmPassword.setVisibility(View.VISIBLE);
                   binding.tvErrorConfirmPassword.setText(getResources().getString(R.string.error_confirm_password));
               }
               else {
                   binding.tvErrorConfirmPassword.setVisibility(View.INVISIBLE);
               }

           }

           @Override
           public void afterTextChanged( Editable s ) {

           }
       });

       /* TextWatcher Address Field */
       binding.editTextAddress.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged( CharSequence s, int start, int count, int after ) {

           }

           @Override
           public void onTextChanged( CharSequence s, int start, int before, int count ) {

               String address=binding.editTextAddress.getText().toString().trim();
               if (address.isEmpty())
               {
                   binding.editTextAddress.requestFocus();
                   binding.tvErrorAddress.setVisibility(View.VISIBLE);
                   binding.tvErrorAddress.setText(getResources().getString(R.string.error_empty));
               }
               else
               {
                   binding.tvErrorAddress.setVisibility(View.INVISIBLE);
               }


           }

           @Override
           public void afterTextChanged( Editable s ) {

           }
       });


       /*TEXTWATCHER ON EDUCATION FIELD*/
       binding.editTextEducation.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged( CharSequence s, int start, int count, int after ) {
           }
           @Override
           public void onTextChanged( CharSequence s, int start, int before, int count ) {
               String address=binding.editTextEducation.getText().toString().trim();
               if (address.isEmpty()) {
                   binding.editTextEducation.requestFocus();
                   binding.tvErrorEducation.setVisibility(View.VISIBLE);
                   binding.tvErrorEducation.setText(getResources().getString(R.string.error_empty));
               }
               else {
                   binding.tvErrorEducation.setVisibility(View.INVISIBLE);
               }
           }
           @Override
           public void afterTextChanged( Editable s ) {
           }
       });
   }
}
