package com.mattias.economics;


import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllExpensesFragment extends Fragment {
    private ListView listExpenses;
    private EconomicsAdapter adapter;
    private DBController dbController;

    public AllExpensesFragment() {
        // Required empty public constructor
    }

    public static AllExpensesFragment newInstance() {
        AllExpensesFragment fragment = new AllExpensesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_all_expenses, container, false);

        listExpenses = (ListView) view.findViewById(R.id.listViewExpenses);
        listExpenses.setAdapter(adapter);


        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbController = new DBController(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        dbController.open();

        Cursor c = dbController.getExpenses();
        adapter = new EconomicsAdapter(getActivity(), c, true);
        listExpenses.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        dbController.close();
    }
}