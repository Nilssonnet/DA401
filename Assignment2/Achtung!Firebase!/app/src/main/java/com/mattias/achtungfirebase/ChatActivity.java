package com.mattias.achtungfirebase;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.firebase.client.Firebase;


public class ChatActivity extends Activity {
    private FragmentManager fragmentManager;
    private GroupFragment groupFragment;
    private ChatFragment chatFragment;

    private Firebase mFirebase;

    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebase = new Firebase("https://da401a.firebaseio.com");
        //mFirebase = new Firebase("https://torid-torch-8342.firebaseio.com/");
        setContentView(R.layout.activity_chat);
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        groupFragment = new GroupFragment();
        transaction.add(R.id.container_chat, groupFragment);
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chat, menu);
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

    public void changeFragments(String group){
        getActionBar().setDisplayHomeAsUpEnabled(true);
        transaction = fragmentManager.beginTransaction();
        chatFragment = new ChatFragment();
        transaction.replace(R.id.container_chat, chatFragment);
        transaction.addToBackStack("Chat");
        transaction.commit();
    }
}
