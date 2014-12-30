package com.mattias.achtungfirebase;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends Fragment{
    private ChatFragment chatFragment;
    private FragmentTransaction transaction;
    private Firebase mFirebase;
    private ArrayAdapter<Group> adapter;
    private ArrayList<Group> groups;
    private View view;
    private ListView groupList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Group List");
        groups = new ArrayList<Group>();
        mFirebase = new Firebase("https://da401a.firebaseio.com");
        adapter = new ArrayAdapter<Group>(
                getActivity(), android.R.layout.simple_list_item_1, groups);
        mFirebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String s) {
                // Add children to your list, and then notify the adapter of the changes
                adapter.add(new Group(snapshot.getName(), (String) snapshot.child("name").getValue()));
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group, container, false);
        groupList = (ListView) view.findViewById(R.id.listViewGroup);
        groupList.setAdapter(adapter);

        groupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Group group = adapter.getItem(position);
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
                chatFragment = ChatFragment.newInstance(group);
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container_chat, chatFragment);
                transaction.addToBackStack("Chat");
                transaction.commit();
            }
        });
        return view;
    }
}
