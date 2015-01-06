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
public class AllIncomesFragment extends Fragment {
    private ListView listIncomes;
    private EconomicsAdapter adapter;
    private DBController dbController;

    public AllIncomesFragment() {
        // Required empty public constructor
    }

    public static AllIncomesFragment newInstance() {
        AllIncomesFragment fragment = new AllIncomesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_all_incomes, container, false);

        listIncomes = (ListView) view.findViewById(R.id.listViewIncomes);
        listIncomes.setAdapter(adapter);


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

        Cursor c = dbController.getIncomes();
        adapter = new EconomicsAdapter(getActivity(), c, true);
        listIncomes.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        dbController.close();
    }
}
