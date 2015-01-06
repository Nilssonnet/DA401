package com.mattias.economics;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenseFragment extends Fragment {
    private DBController dbController;
    private EditText title, amount;

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

        Button buttonInput = (Button) view.findViewById(R.id.buttonExpense);
        buttonInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = title.getText().toString();
                String newAmount = amount.getText().toString();
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String date = simpleDateFormat.format(calendar.getTime());
                long id = dbController.dataExpenses(newTitle, newAmount, date);
                Toast.makeText(getActivity(), "Expense with " + id + " and date " + date +
                        " was created", Toast.LENGTH_SHORT).show();
            }
        });
        Button buttonList = (Button) view.findViewById(R.id.buttonListExpenses);
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.container_main,
                        AllExpensesFragment.newInstance(), "all expenses").addToBackStack("expense")
                        .commit();
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
    }

    @Override
    public void onPause() {
        super.onPause();

        dbController.close();
    }
}
