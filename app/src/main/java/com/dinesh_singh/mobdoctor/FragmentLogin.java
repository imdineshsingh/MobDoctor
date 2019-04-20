package com.dinesh_singh.mobdoctor;


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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dinesh_singh.mobdoctor.databinding.FragmentLoginBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */

public class FragmentLogin extends Fragment implements View.OnClickListener {

    FragmentLoginBinding binding;
    private Fragment mFragment;

    public FragmentLogin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {

        // Inflating the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        View v = binding.getRoot();

        binding.tvErrorEmail.setVisibility(View.INVISIBLE);
        binding.tvErrorPassword.setVisibility(View.INVISIBLE);

        /*calling the methods which are declared outside oncreate() */
        listeners();
        usingSpannableString();

        return v;
    }

    private void listeners() {
        binding.imageViewBackButton.setOnClickListener(this);
        binding.buttonLogIn.setOnClickListener(this);
        binding.textViewForgotPassword.setOnClickListener(this);
        addTextWatchers();
    }
    @Override
    public void onClick( View v ) {
        switch (v.getId()) {
            case R.id.button_log_in:
                checkValidation();
                break;
            case R.id.image_view_back_button:
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.place_holder, new FragmentHome()).addToBackStack("").commit();
                break;
            case R.id.text_view_forgot_password:
                Toast.makeText(getContext(), "Forgot Password Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void checkValidation()
    {
        if (binding.editTextEmail.getText().toString().trim().isEmpty()) {
            binding.editTextEmail.requestFocus();
            binding.tvErrorEmail.setText(getResources().getString(R.string.error_empty_email));
            binding.tvErrorEmail.setVisibility(View.VISIBLE);
        } else if (!isValidEmail()) {
           binding.editTextEmail.requestFocus();
            binding.tvErrorEmail.setText(getResources().getString(R.string.error_email));
            binding.tvErrorEmail.setVisibility(View.VISIBLE);
        } else if (binding.editTextPassword.getText().toString().trim().isEmpty()) {
           binding.editTextPassword.requestFocus();
            binding.tvErrorPassword.setText(getResources().getString(R.string.error_empty_password));
            binding.tvErrorPassword.setVisibility(View.VISIBLE);
        } else if (!isValidPassword()) {
            binding.editTextPassword.requestFocus();
            binding.tvErrorPassword.setText(getResources().getString(R.string.error_password));
            binding.tvErrorPassword.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(getContext(), "Successfully Logged In", Toast.LENGTH_SHORT).show();
        }
    }

    //METHOD TO VALIDATE
    private boolean isValidEmail() {
        binding.tvErrorPassword.setVisibility(View.INVISIBLE);
        final String email = binding.editTextEmail.getText().toString().trim();
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    private boolean isValidPassword() {
        binding.tvErrorEmail.setVisibility(View.INVISIBLE);
        final String password = binding.editTextPassword.getText().toString().trim();
        // final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[A-Z]).{8,16}$";
        return password.length() >= 8 && password.length() <= 16;
    }

    /*USING SPANNABLE STRING*/
    private void usingSpannableString() {
        SpannableString spannableString = new SpannableString(getResources().getString(R.string.no_account));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick( View widget ) {
                //mFragment=new Fragment_SignUp();
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.place_holder, new Fragment_SignUp()).addToBackStack("").commit();
            }
            @Override
            public void updateDrawState( TextPaint ds ) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.colorEditTextBackground));
                // ds.setUnderlineText(false);
            }
        };
        spannableString.setSpan(clickableSpan, 23, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.textViewSignUp.setText(spannableString);
        binding.textViewSignUp.setMovementMethod(LinkMovementMethod.getInstance());
    }
    private void addTextWatchers() {
        //   TextWatcher on Email
        binding.editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged( CharSequence s, int start, int count, int after ) {
                binding.tvErrorEmail.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onTextChanged( CharSequence s, int start, int before, int count ) {
                final String email=binding.editTextEmail.getText().toString().trim();
                if(email.isEmpty()){
                    binding.editTextEmail.requestFocus();
                    binding.tvErrorEmail.setVisibility(View.VISIBLE);
                    binding.tvErrorEmail.setText(getResources().getString(R.string.error_empty_email));
                }
                else if(!isValidEmail()) {
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

        // TextWatcher on Password
        binding.editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged( CharSequence s, int start, int count, int after ) {
                binding.tvErrorPassword.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onTextChanged( CharSequence s, int start, int before, int count ) {
                final String password=binding.editTextPassword.getText().toString().trim();
                if (password.isEmpty()) {
                    binding.editTextPassword.requestFocus();
                    binding.tvErrorPassword.setVisibility(View.VISIBLE);
                    binding.tvErrorPassword.setText(getResources().getString(R.string.error_empty_password));
                }
                else if(!isValidPassword()) {
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
    }
}
