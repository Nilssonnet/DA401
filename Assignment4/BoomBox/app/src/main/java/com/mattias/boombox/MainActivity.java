package com.mattias.boombox;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements SensorEventListener{
    private FragmentManager fragmentManager;
    private PlayFragment playFragment;
    private SensorManager sensorManager;
    private Sensor senAccelerometer;

    private final static int SAMPLING_TIME = 20;
    private int amountOfKnocks = 0;
    private long lastUpdate = 0, timeFirstKnock = 0, knockingTime = 1500;
    private float last_z, avgValue = 0;
    private final static double KNOCK_THRESHOLD = 0.6;

    private boolean knockActivated = false, firstStartup = true;

    private ArrayList<Float> arraylistValues = new ArrayList<Float>();

    private static final String TAG = "mainactivity";

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
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
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
        float zValue = event.values[2];
        arraylistValues.add(zValue);
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastUpdate) > SAMPLING_TIME) {
            lastUpdate = currentTime;
            for (Float value : arraylistValues) {

                avgValue += value;

            }
            if (amountOfKnocks > 4) {
                knockActivated = false;
                amountOfKnocks = 0;
                timeFirstKnock = 0;
                arraylistValues.clear();
            }
            avgValue /= arraylistValues.size();


            avgValue = Math.abs(avgValue - last_z);
            Log.d(TAG, "avgValue " + avgValue);

            if (avgValue > KNOCK_THRESHOLD) {
                timeFirstKnock = System.currentTimeMillis();
                knockActivated = true;
                amountOfKnocks++;
                //Log.d(TAG, "avgValue " + avgValue);
            }
            avgValue = 0;
            last_z = zValue;
        }
        if (currentTime - timeFirstKnock > knockingTime && knockActivated) {
            if (firstStartup){
                firstStartup=false;
            }
            else{
                playFragment.musicPlayer(amountOfKnocks);
                //Log.d(TAG, "amountOfKnocks " + amountOfKnocks);
                avgValue = 0;
                knockActivated = false;
                amountOfKnocks = 0;
                timeFirstKnock = 0;
                arraylistValues.clear();
            }

        }
    }


    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
