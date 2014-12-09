package com.mattias.achtungfirebase;


import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends ListFragment {
    private Firebase mFirebase;

    private String[] listString = {"Chat here", "Chat there", "Chat everywhere"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebase = new Firebase("https://torid-torch-8342.firebaseio.com/");
        //mFirebase = new Firebase("https://da401a.firebaseio.com");
        mFirebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String s) {
                // Add children to your list, and then notify the adapter of the changes
            }
            @Override
            public void onChildChanged(DataSnapshot snapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot snapshot, String s) {
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Groups");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, listString);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String clicked = (String) l.getItemAtPosition(position);
        ((ChatActivity) getActivity()).changeFragments(clicked);
    }
}
