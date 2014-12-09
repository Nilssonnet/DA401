package com.mattias.achtungfirebase;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {
    private Firebase mFirebase;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebase = new Firebase("https://da401a.firebaseio.com");
        //mFirebase = new Firebase("https://torid-torch-8342.firebaseio.com/");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }


}
