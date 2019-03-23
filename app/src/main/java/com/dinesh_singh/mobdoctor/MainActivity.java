package com.dinesh_singh.mobdoctor;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity  {



    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        changeFragment(new FragmentHome());



    }

    private void changeFragment(Fragment fragment )
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.place_holder,fragment).commit();

    }


}
