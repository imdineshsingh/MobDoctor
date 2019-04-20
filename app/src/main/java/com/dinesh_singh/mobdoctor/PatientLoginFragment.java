package com.dinesh_singh.mobdoctor;


import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dinesh_singh.mobdoctor.databinding.FragmentPatientLoginBinding;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class PatientLoginFragment extends Fragment implements View.OnClickListener {

FragmentPatientLoginBinding binding;

RadioGroup radioGroup;

    public PatientLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_patient_login, container, false);
        View view= binding.getRoot();



        if( binding.rbTvError.isChecked())
        {
            binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick( View v ) {

                    rbTextViewErrors();

                }
            });

          //  rbTextViewErrors();
        }
        else if(binding.rbBoxError.isChecked())
        {
            binding.btnSubmit.setOnClickListener(this);

        }

        binding.tietName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (binding.tietName.getRight() - binding.tietName.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())) {


                        Log.e("click","yes");





                        return true;
                    }
                }
                return false;
            }
        });

        return view;
    }


    @Override
    public void onClick( View v ) {

        switch (v.getId())
        {
            case R.id.btn_submit:

                validate();
                break;

        }

    }


    private void validate()
    {

        String name=binding.tietName.getText().toString().trim();
        String email=binding.tietEmail.getText().toString().trim();
        String phone=binding.tietPhone.getText().toString().trim();
        String password=binding.tietPassword.getText().toString().trim();
        String confirmPassword=binding.tietConfirmPassword.getText().toString().trim();
        /*
        String name= Objects.requireNonNull(binding.tilName.getEditText()).getText().toString().trim();
        String email=binding.tilEmail.getEditText().getText().toString().trim();
        String phone=binding.tilPhone.getEditText().getText().toString().trim();
        String password=binding.tilPassword.getEditText().getText().toString().trim();
        String confirmPassword=binding.tilConfirmPassword.getEditText().getText().toString().trim();
*/


        if(!isValidName(name))
        {
            if(binding.rbTvError.isChecked())
            {
                disappearTvErrors();
                binding.tietName.requestFocus();
                /* binding.tilName.setErrorEnabled(true);*/
                binding.tilName.setError("*Name can't be empty");
            }
            else if(binding.rbBoxError.isChecked()){
                binding.tietName.requestFocus();
                disappearErrors();
                // binding.tilName.setErrorTextColor();
                binding.tietName.setError("*Name can't be empty");

            }

        }
        else if(!isValidEmail(email))
        {
            if(binding.rbTvError.isChecked())
            {
                disappearTvErrors();
                binding.tietName.requestFocus();
                binding.tilName.setErrorEnabled(true);
                binding.tilEmail.setError("*Please enter valid email");
            }

            else if(binding.rbBoxError.isChecked())
            {
                binding.tietEmail.requestFocus();
                disappearErrors();
                // binding.tilEmail.setError("*please enter valid email");

                binding.tietEmail.setError("*Please enter valid email");

            }



        }
        else if(binding.tietPhone.getText().toString().trim().length()<10)
        {
            if(binding.rbTvError.isChecked())
            {
                disappearTvErrors();
                binding.tietName.requestFocus();
                binding.tilName.setErrorEnabled(true);
                binding.tilPhone.setError("*Please enter valid phone number");
            }
            else if(binding.rbBoxError.isChecked())
            {

                binding.tietPhone.requestFocus();
                disappearErrors();
                //binding.tilPhone.setError("*Please enter valid phone number");
                binding.tietPhone.setError("*Please enter valid phone number");
            }

        }
        else if(!isValidPassword(password))
        {
            if(binding.rbTvError.isChecked())
            {
                disappearTvErrors();
                binding.tietName.requestFocus();
                binding.tilName.setErrorEnabled(true);
                binding.tilPassword.setError("*Please enter valid Password");
            }
            else if(binding.rbBoxError.isChecked())
                {
                    disappearTvErrors();
                    binding.tietPassword.requestFocus();
                    disappearErrors();
                    //binding.tilPassword.setError("*Please enter valid Password");
                    binding.tietPassword.setError("*Please enter valid Password");
                }


        }
        else if(!confirmPassword.equals(password))
        {
            if(binding.rbTvError.isChecked())
            {
                disappearTvErrors();
                binding.tietName.requestFocus();
                binding.tilName.setErrorEnabled(true);
                binding.tilConfirmPassword.setError("*Password must be same");


            }
            else if(binding.rbBoxError.isChecked())
            {

                binding.tietConfirmPassword.requestFocus();
                disappearErrors();
                //binding.tilConfirmPassword.setError("*Password must be same");
                binding.tietConfirmPassword.setError("*Password must be same");

            }
        }

        else
        {
            disappearErrors();
            disappearTvErrors();

            Snackbar snackbar=Snackbar.make(binding.coordinatorLayout,"Submitted successfully",Snackbar.LENGTH_LONG)
                    .setAction("Reset All Fields", new View.OnClickListener() {
                        @Override
                        public void onClick( View v ) {

                            resetFields();
                            Snackbar snackbar1 = Snackbar.make(binding.coordinatorLayout, "All field resetted", Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                            snackbar1.setActionTextColor(getResources().getColor(R.color.colorYellow));
                            snackbar1.getView().setBackgroundResource(R.drawable.gradient_login_bg_patient);

                        }
                    });
            snackbar.show();
            snackbar.getView().setBackgroundResource(R.drawable.gradient_login_bg_patient);
            snackbar.setActionTextColor(getResources().getColor(R.color.colorYellow));


        }



    }

    private void resetFields()
    {
        binding.tietName.setText("");
        binding.tietEmail.setText("");
        binding.tietPhone.setText("");
        binding.tietPassword.setText("");
        binding.tietConfirmPassword.setText("");

    }


    private boolean isValidName(String name)
    {
     //   binding.tilName.setVisibility(View.INVISIBLE);
        String PERSON_NAME = "^[\\p{L} .'-]+$";

        Pattern pattern = Pattern.compile(PERSON_NAME);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private boolean isValidEmail(String email)
    {
       // binding.tilEmail.setVisibility(View.INVISIBLE);
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

        return matcher.find();
    }

   /* private boolean isValidPhoneNumber(String phoneNumber)
    {
       // binding.tilPhone.setVisibility(View.INVISIBLE);


        return Patterns.PHONE.matcher(phoneNumber).matches();

    }

*/
    private boolean isValidPassword(String password)
    {
       // binding.tilPassword.setVisibility(View.INVISIBLE);
        return password.length() >= 8 && password.length() <= 16;
    }



    private void disappearErrors()
    {

        binding.tilName.setErrorEnabled(false);
        binding.tilEmail.setErrorEnabled(false);
        binding.tilPhone.setErrorEnabled(false);
        binding.tilPassword.setErrorEnabled(false);
        binding.tilConfirmPassword.setErrorEnabled(false);


    }

    private void disappearTvErrors()
    {
        binding.tilName.setErrorEnabled(false);


        binding.tilEmail.setErrorEnabled(false);

        binding.tilPhone.setErrorEnabled(false);

        binding.tilPassword.setErrorEnabled(false);

        binding.tilConfirmPassword.setErrorEnabled(false);

    }



   public void rbTextViewErrors()
   {
               String name=binding.tietName.getText().toString().trim();
               String email=binding.tietEmail.getText().toString().trim();
               String phone=binding.tietPhone.getText().toString().trim();
               String password=binding.tietPassword.getText().toString().trim();
               String confirmPassword=binding.tietConfirmPassword.getText().toString().trim();

                   if(!isValidName(name))
                   {
                       //binding.tilPhone.setError("*Please enter valid phone number");
                       binding.tilName.setError("Please enter valid Name");
                       binding.tietName.requestFocus();
                       disappearErrors();
                       // binding.tilName.setErrorTextColor();
                       //binding.tietName.setError("*Name can't be empty");

                   }
                   else if(!isValidEmail(email))
                   {

                       binding.tietEmail.requestFocus();

                       disappearErrors();
                       // binding.tilEmail.setError("*please enter valid email");

                       binding.tilEmail.setError("Please enter valid email");
                   }
                   else if(binding.tietPhone.getText().toString().trim().length()<10)
                   {
                       binding.tietPhone.requestFocus();
                       disappearErrors();
                       //binding.tilPhone.setError("*Please enter valid phone number");
                       binding.tilPhone.setError("Please enter valid phone number");
                   }
                   else if(!isValidPassword(password))
                   {
                       binding.tietPassword.requestFocus();
                       disappearErrors();
                       //binding.tilPassword.setError("*Please enter valid Password");
                       binding.tilPassword.setError("Please enter valid Password");
                   }
                   else if(!confirmPassword.equals(password))
                   {
                       binding.tietConfirmPassword.requestFocus();
                       disappearErrors();
                       //binding.tilConfirmPassword.setError("*Password must be same");
                       binding.tilConfirmPassword.setError("Password must be same");
                   }


                   else{
                       Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                   }



           }





}
