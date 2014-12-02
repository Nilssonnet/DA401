package com.mattias.achtungchat;


import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends ListFragment {
    private String[] listString = {"Chat here", "Chat there", "Chat everywhere"};

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
        ((MainActivity) getActivity()).changeFragments(clicked);
    }
}
