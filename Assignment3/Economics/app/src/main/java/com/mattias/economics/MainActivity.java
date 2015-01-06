package com.mattias.economics;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ActionBar;


public class MainActivity extends Activity {
    private FragmentManager fragmentManager;
    private SummaryFragment summaryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*if (savedInstanceState == null) {
            fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            summaryFragment = new SummaryFragment();
            transaction.replace(R.id.container_main, summaryFragment);
            transaction.commit();
            getActionBar().setDisplayHomeAsUpEnabled(false);
        }*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
        */
        switch( item.getItemId()){

            case R.id.action_summary:
                getFragmentManager().beginTransaction().replace(R.id.container_main, SummaryFragment.newInstance("", ""), "blank").addToBackStack("blank").commit();
                return true;

            case R.id.action_income:
                getFragmentManager().beginTransaction().replace(R.id.container_main, IncomeFragment.newInstance("", ""), "item").addToBackStack("item").commit();
                return true;

            case R.id.action_expense:
                getFragmentManager().beginTransaction().replace(R.id.container_main, ExpenseFragment.newInstance("", ""), "item").addToBackStack("item").commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
