package com.mattias.economics;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    private DBController dbController;

    public SummaryFragment() {
        // Required empty public constructor
    }

    public static SummaryFragment newInstance() {
        SummaryFragment fragment = new SummaryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_summary, container, false);
        TextView textViewInc = (TextView) root.findViewById(R.id.textViewIncomes);
        TextView textViewExp = (TextView) root.findViewById(R.id.textViewExpenses);
        TextView textViewSum = (TextView) root.findViewById(R.id.textViewSummary);
        textViewInc.setText("Total incomes:\n" + dbController.getIncomesAmount() + "\n");
        textViewExp.setText("Total expenses:\n" + (- dbController.getExpensesAmount()) + "\n");
        textViewSum.setText("Total sum:\n" +
                (dbController.getIncomesAmount() - dbController.getExpensesAmount()) + "\n");
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbController = new DBController(getActivity());
        dbController.open();
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
