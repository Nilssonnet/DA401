package com.mattias.achtungfirebase;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class LoginActivity extends Activity {
    private FragmentManager fragmentManager;
    private LoginFragment loginFragment;
    private RegistrationFragment registrationFragment;
    private AboutFragment aboutFragment;

    private Firebase mFirebase;

    private String EMAIL = "e@mail.com";    //Used for storing the email from registration
    private String PASSWORD = "pass";       //Used for storing the password from registration
    private EditText editTextEmailLogin;
    private EditText editTextPasswordLogin;
    private EditText editTextEmailRegistration;
    private EditText editTextPasswordRegistration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        //mFirebase = new Firebase("https://da401a.firebaseio.com");
        mFirebase = new Firebase("https://torid-torch-8342.firebaseio.com/");
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            loginFragment = new LoginFragment();
            transaction.replace(R.id.container_login, loginFragment);
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                getActionBar().setDisplayHomeAsUpEnabled(false);
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void Login(View view){
        editTextEmailLogin = (EditText) findViewById(R.id.editText_email);
        editTextPasswordLogin = (EditText) findViewById(R.id.editText_password);

        String email = editTextEmailLogin.getText().toString();
        String password = editTextPasswordLogin.getText().toString();

        if( email.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter email and/or password",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            mFirebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {
                    Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
                    LoginActivity.this.startActivity(intent);
                    LoginActivity.this.finish();
                }

                @Override
                public void onAuthenticationError(FirebaseError error) {
                    // Handle errors
                    errorPrintOut(error);
                }
            });
        }
        /*
        else if (email.equals(_EMAIL) &&
                password.equals(_PASSWORD))
        {
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
            this.finish();
        } else{
            Toast.makeText(getApplicationContext(), "Wrong email or password",
                    Toast.LENGTH_SHORT).show();
        }
         */

    }

    public void Registration(View view){
        getActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        registrationFragment = new RegistrationFragment();
        transaction.replace(R.id.container_login, registrationFragment);
        transaction.addToBackStack("Registration");
        transaction.commit();
    }

    public void Registration_save(View view) {
        editTextEmailRegistration = (EditText) findViewById(R.id.editText_email_registration);
        editTextPasswordRegistration = (EditText) findViewById(R.id.editText_password_registration);

        String email = editTextEmailRegistration.getText().toString();
        String password = editTextPasswordRegistration.getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter valid email and/or password",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            mFirebase.createUser(email, password, new Firebase.ResultHandler() {
                @Override
                public void onSuccess() {
                    Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }

                @Override
                public void onError(FirebaseError error) {
                    // Handle errors
                    errorPrintOut(error);
                }
            });
            //EMAIL = email;
            //PASSWORD = password;
            //super.onBackPressed();
        }
    }

    public void About(View view){
        getActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        aboutFragment = new AboutFragment();
        transaction.replace(R.id.container_login, aboutFragment);
        transaction.addToBackStack("About");
        transaction.commit();
    }

    private void errorPrintOut(FirebaseError error){
        switch (error.getCode()){
            case FirebaseError.AUTHENTICATION_PROVIDER_DISABLED:
                Toast.makeText(getApplicationContext(), "AUTHENTICATION_PROVIDER_DISABLED", Toast.LENGTH_SHORT).show();
                break;
            case FirebaseError.EMAIL_TAKEN:
                Toast.makeText(getApplicationContext(), "EMAIL_TAKEN", Toast.LENGTH_SHORT).show();
                break;
            case FirebaseError.INVALID_AUTH_ARGUMENTS:
                Toast.makeText(getApplicationContext(), "INVALID_AUTH_ARGUMENTS", Toast.LENGTH_SHORT).show();
                break;
            case FirebaseError.INVALID_CONFIGURATION:
                Toast.makeText(getApplicationContext(), "INVALID_CONFIGURATION", Toast.LENGTH_SHORT).show();
                break;
            case FirebaseError.INVALID_CREDENTIALS:
                break;
            case FirebaseError.INVALID_EMAIL:
                Toast.makeText(getApplicationContext(), "INVALID_CREDENTIALS", Toast.LENGTH_SHORT).show();
                break;
            case FirebaseError.INVALID_PASSWORD:
                Toast.makeText(getApplicationContext(), "INVALID_PASSWORD", Toast.LENGTH_SHORT).show();
                break;
            case FirebaseError.INVALID_PROVIDER:
                Toast.makeText(getApplicationContext(), "INVALID_PROVIDER", Toast.LENGTH_SHORT).show();
                break;
            case FirebaseError.INVALID_TOKEN:
                Toast.makeText(getApplicationContext(), "INVALID_TOKEN", Toast.LENGTH_SHORT).show();
                break;
            case FirebaseError.NETWORK_ERROR:
                Toast.makeText(getApplicationContext(), "NETWORK_ERROR", Toast.LENGTH_SHORT).show();
                break;
            case FirebaseError.PREEMPTED:
                Toast.makeText(getApplicationContext(), "PREEMPTED", Toast.LENGTH_SHORT).show();
                break;
            case FirebaseError.PROVIDER_ERROR:
                Toast.makeText(getApplicationContext(), "PROVIDER_ERROR", Toast.LENGTH_SHORT).show();
                break;
            case FirebaseError.UNKNOWN_ERROR:
                Toast.makeText(getApplicationContext(), "UNKNOWN_ERROR", Toast.LENGTH_SHORT).show();
                break;
            case FirebaseError.USER_DOES_NOT_EXIST:
                Toast.makeText(getApplicationContext(), "USER_DOES_NOT_EXIST", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
