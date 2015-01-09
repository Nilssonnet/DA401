package com.mattias.economics;


import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeFragment extends Fragment {
    private DBController dbController;

    private ListView listIncomes;
    private EconomicsAdapter adapter;

    public IncomeFragment() {
        // Required empty public constructor
    }

    public static IncomeFragment newInstance() {
        IncomeFragment fragment = new IncomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income, container, false);

        listIncomes = (ListView) view.findViewById(R.id.listViewIncomes);
        listIncomes.setAdapter(adapter);

        Button buttonIncomeInput = (Button) view.findViewById(R.id.buttonIncomeInput);
        buttonIncomeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IncomeInputFragment incomeInputFragment = IncomeInputFragment.newInstance();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container_main, incomeInputFragment);
                transaction.addToBackStack("Income input");
                transaction.commit();
            }
        });
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
