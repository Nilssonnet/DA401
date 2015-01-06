package com.mattias.economics;


import android.os.Bundle;
import android.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeFragment extends Fragment {
    private DBController dbController;
    private EditText title, amount;

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

        Button buttonInput = (Button) view.findViewById(R.id.buttonIncome);
        buttonInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = title.getText().toString();
                String newAmount = amount.getText().toString();
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String date = simpleDateFormat.format(calendar.getTime());
                long id = dbController.dataIncomes(newTitle, newAmount, date);
                Toast.makeText(getActivity(), "Income with " + id + " and date " + date +
                        " was created", Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonList = (Button) view.findViewById(R.id.buttonListIncomes);
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.container_main,
                        AllIncomesFragment.newInstance(), "all incomes").addToBackStack("expense")
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
