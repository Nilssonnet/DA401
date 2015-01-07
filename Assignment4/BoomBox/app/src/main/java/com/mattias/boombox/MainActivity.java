package com.mattias.boombox;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends ActionBarActivity implements SensorEventListener, Runnable{
    private FragmentManager fragmentManager;
    private PlayFragment playFragment;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            playFragment = new PlayFragment();
            transaction.replace(R.id.container_main, playFragment);
            transaction.commit();
        }
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onAccuracyChanged(Sensor sensor,int accuracy){

    }

    public void onSensorChanged(SensorEvent event){
        // check sensor type
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){

            // assign directions
            //float x=event.values[0];
            //float y=event.values[1];
            float z=event.values[2];

            playFragment.sensorListeners(z);
        }

    }

    public void run(){

    }
}
