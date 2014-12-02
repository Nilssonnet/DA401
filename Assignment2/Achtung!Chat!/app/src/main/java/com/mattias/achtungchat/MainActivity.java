package com.mattias.achtungchat;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {
    private FragmentManager fragmentManager;
    private GroupFragment groupFragment;
    private ChatFragment chatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));

        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        groupFragment = new GroupFragment();
        transaction.replace(R.id.container_main, groupFragment);
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
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

    public void Chat(View view){
        getActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        chatFragment = new ChatFragment();
        transaction.replace(R.id.container_main, chatFragment);
        transaction.addToBackStack("Chat");
        transaction.commit();
    }
}
