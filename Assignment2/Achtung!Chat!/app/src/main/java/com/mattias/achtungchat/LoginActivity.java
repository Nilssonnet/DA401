package com.mattias.achtungchat;

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


public class LoginActivity extends Activity {
    private FragmentManager fragmentManager;
    private LoginFragment loginFragment;
    private RegistrationFragment registrationFragment;
    private AboutFragment aboutFragment;

    private String EMAIL = "e@mail.com";    //Used for storing the email from registration
    private String PASSWORD = "pass";       //Used for storing the password from registration
    private EditText editTextEmailRegistration;
    private EditText editTextPasswordRegistration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        /*
                editTextEmailLogin = (EditText) findViewById(R.id.editText_email);
        editTextPasswordLogin = (EditText) findViewById(R.id.editText_password);

        String email = editTextEmailLogin.getText().toString();
        String password = editTextPasswordLogin.getText().toString();

        if( email.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter email and/or password",
                    Toast.LENGTH_SHORT).show();
        }
        else if (email.equals(_EMAIL) &&
                password.equals(_PASSWORD))
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        } else{
            Toast.makeText(getApplicationContext(), "Wrong email or password",
                    Toast.LENGTH_SHORT).show();
        }
         */
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
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
            EMAIL = email;
            PASSWORD = password;
            super.onBackPressed();
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
}
