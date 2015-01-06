package com.mattias.economics;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ActionBar;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch( item.getItemId()){

            case R.id.action_summary:
                getFragmentManager().beginTransaction().replace(R.id.container_main, SummaryFragment.newInstance(), "summary").addToBackStack("summary").commit();
                return true;

            case R.id.action_income:
                getFragmentManager().beginTransaction().replace(R.id.container_main, IncomeFragment.newInstance(), "income").addToBackStack("income").commit();
                return true;

            case R.id.action_expense:
                getFragmentManager().beginTransaction().replace(R.id.container_main, ExpenseFragment.newInstance(), "expense").addToBackStack("expense").commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
