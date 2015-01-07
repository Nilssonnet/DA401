package com.mattias.economics;


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
    private EditText title, amount;
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

        title = (EditText) view.findViewById(R.id.editTextIncomeTitle);
        amount = (EditText) view.findViewById(R.id.editTextIncomeAmount);
        listIncomes = (ListView) view.findViewById(R.id.listViewIncomes);
        listIncomes.setAdapter(adapter);

        Button buttonInput = (Button) view.findViewById(R.id.buttonIncome);
        buttonInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = title.getText().toString();
                String newAmount = amount.getText().toString();
                if(newTitle.isEmpty() || newAmount.isEmpty()){
                    Toast.makeText(getActivity(), "You have to fill in both fields",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String date = simpleDateFormat.format(calendar.getTime());
                    long id = dbController.dataIncomes(newTitle, newAmount, date);
                    Cursor c = dbController.getIncomes();
                    adapter = new EconomicsAdapter(getActivity(), c, true);
                    listIncomes.setAdapter(adapter);
                    title.setText("");
                    amount.setText("");
                }
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
