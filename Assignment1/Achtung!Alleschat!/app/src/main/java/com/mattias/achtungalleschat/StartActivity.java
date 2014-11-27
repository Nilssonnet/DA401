package com.mattias.achtungalleschat;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class StartActivity extends Activity {

    private static String _EMAIL = "e@mail.com";
    private static String _PASSWORD = "pass";
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonRegistration;
    private Button buttonAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        buttonLogin = (Button) findViewById(R.id.button_login);
        buttonRegistration = (Button) findViewById(R.id.button_registration);
        buttonAbout = (Button) findViewById(R.id.button_about);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextEmail = (EditText) findViewById(R.id.editText_email);
                editTextPassword = (EditText) findViewById(R.id.editText_password);
                Login();
            }
        });
        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Registration fragment
            }
        });
        buttonAbout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //About fragment
            }
        });
    }


    //@Override
    /*public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void Login(){
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if(_EMAIL != email || _PASSWORD != password){
            Toast.makeText(getApplicationContext(), "Wrong email or password",
                    Toast.LENGTH_LONG).show();
        } else if (_EMAIL == email && _PASSWORD == password){
            //Go to MainActivity
        }
    }
}
