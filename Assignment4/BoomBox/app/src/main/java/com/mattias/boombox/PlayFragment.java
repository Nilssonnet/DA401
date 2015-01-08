package com.mattias.boombox;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment {
    private Button buttonPlayPause;
    private Button buttonStop;
    private Button buttonBackward;
    private Button buttonForward;

    private boolean play = false;

    //private SensorManager sensorManager;
    //private Sensor sensor;

    public PlayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);

        buttonPlayPause = (Button) view.findViewById(R.id.buttonPlayPause);
        buttonStop = (Button) view.findViewById(R.id.buttonStop);
        buttonBackward = (Button) view.findViewById(R.id.buttonBackward);
        buttonForward = (Button) view.findViewById(R.id.buttonForward);
        //sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        //sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        buttonListeners();
        //sensorListeners();

        return view;
    }

    private void buttonListeners(){
        buttonPlayPause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                musicPlayer(1);
            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                musicPlayer(4);
            }
        });
        buttonBackward.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                musicPlayer(3);
            }
        });
        buttonForward.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                musicPlayer(2);
            }
        });
    }

    public void sensorListeners(float z){
        if(z>8.65)
        Toast.makeText(getActivity(), "result: " + z,
                Toast.LENGTH_SHORT).show();
    }

    public void musicPlayer(int selection){
        switch (selection){
            //Play/pause
            case 1:
                if (play) {
                    play = false;
                    Toast.makeText(getActivity(), "Pause",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    play = true;
                    Toast.makeText(getActivity(), "Play",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            //Forward
            case 2:
                Toast.makeText(getActivity(), "Forward",
                        Toast.LENGTH_SHORT).show();
                break;
            //Backward
            case 3:
                Toast.makeText(getActivity(), "Backward",
                        Toast.LENGTH_SHORT).show();
                break;
            //Stop
            case 4:
                Toast.makeText(getActivity(), "Stop",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

}
