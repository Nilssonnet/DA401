package com.mattias.achtungfirebase;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class ChatFragment extends Fragment {
    private Firebase mFirebase;
    private ArrayAdapter<ChatMessage> adapter;
    private ArrayList<ChatMessage> chats;
    private View view;
    private ListView chatList;

    private static String groupName;
    private static String groupId;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Current chat");
        if(getArguments() != null) {
            groupName = getArguments().getString("groupName");
            groupId = getArguments().getString("groupId");
        }
        chats = new ArrayList<ChatMessage>();
        mFirebase.setAndroidContext(getActivity());
        mFirebase = new Firebase(("https://da401a.firebaseio.com")).child(groupId).child("messages");
        //mFirebase = new Firebase("https://da401a.firebaseio.com");
        //mFirebase = new Firebase("https://torid-torch-8342.firebaseio.com/");

        adapter = new ArrayAdapter<ChatMessage>(
                getActivity(), android.R.layout.simple_list_item_1, chats);
        mFirebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String s) {
                // Add children to your list, and then notify the adapter of the changes
                adapter.add(new ChatMessage((String) snapshot.child("id").getValue(),
                        (String) snapshot.child("from").getValue(),
                        (String) snapshot.child("message").getValue(),
                        (String) snapshot.child("time").getValue()));
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
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        chatList = (ListView) view.findViewById(R.id.listViewChat);
        chatList.setAdapter(adapter);
        return view;
        //return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    public static ChatFragment newInstance(Group group){
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString("groupName", group.getName());
        args.putString("groupId", group.getId());
        fragment.setArguments(args);
        return fragment;
    }
}
