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
public class ExpenseFragment extends Fragment {
    private DBController dbController;
    private EditText title, amount;
    private ListView listExpenses;
    private EconomicsAdapter adapter;

    public ExpenseFragment() {
        // Required empty public constructor
    }

    public static ExpenseFragment newInstance() {
        ExpenseFragment fragment = new ExpenseFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense, container, false);

        title = (EditText) view.findViewById(R.id.editTextExpenseTitle);
        amount = (EditText) view.findViewById(R.id.editTextExpenseAmount);
        listExpenses = (ListView) view.findViewById(R.id.listViewExpenses);
        listExpenses.setAdapter(adapter);

        Button buttonInput = (Button) view.findViewById(R.id.buttonExpense);
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
                    long id = dbController.dataExpenses(newTitle, newAmount, date);
                    Cursor c = dbController.getExpenses();
                    adapter = new EconomicsAdapter(getActivity(), c, true);
                    listExpenses.setAdapter(adapter);
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
