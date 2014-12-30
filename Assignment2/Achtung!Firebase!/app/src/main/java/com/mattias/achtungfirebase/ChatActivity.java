package com.mattias.achtungfirebase;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;


public class ChatActivity extends Activity {
    private FragmentManager fragmentManager;
    private GroupFragment groupFragment;


    private EditText editTextAddGroup;

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
        getFragmentManager().beginTransaction().add(R.id.container_chat, groupFragment).commit();
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

    public void AddGroup(View view){
        editTextAddGroup = (EditText) findViewById(R.id.editText_new_group);
        String newGroup = editTextAddGroup.getText().toString();
        if(newGroup.isEmpty()){
            Toast.makeText(getApplicationContext(), "You have to enter a name.",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            String id = mFirebase.push().getName();
            Map<String, Object> node = new HashMap<String, Object>();
            Map<String, Object> nodeValues = new HashMap<String, Object>();
            nodeValues.put("name", newGroup);
            nodeValues.put("id", id);
            node.put(id, nodeValues);
            mFirebase.updateChildren(node);
            editTextAddGroup.setText("");
        }
    }

    public void sendMessage(View view){
        EditText editTextSendMessage = (EditText) findViewById(R.id.editText_send_message);
        String message = editTextSendMessage.getText().toString();
        if(message.isEmpty()){
            Toast.makeText(getApplicationContext(), "You can not send an empty message.",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            ChatFragment chatFragment = (ChatFragment) getFragmentManager().findFragmentById(R.id.container_chat);
            chatFragment.sending(message);
            editTextSendMessage.setText("");
        }
    }
}
