package com.mattias.boombox;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment {
    private Button buttonPlayPause;
    private Button buttonStop;
    private Button buttonBackward;
    private Button buttonForward;

    MediaPlayer mediaPlayer;
    private ArrayList<Integer> songList = new ArrayList<Integer>();
    private int songCounter = 0, currentSong;

    public PlayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        songList.add(R.raw.game_of_thrones);
        songList.add(R.raw.the_big_bang_theory);
        songList.add(R.raw.the_simpsons);
        currentSong = songList.get(songCounter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);

        buttonPlayPause = (Button) view.findViewById(R.id.buttonPlayPause);
        buttonStop = (Button) view.findViewById(R.id.buttonStop);
        buttonBackward = (Button) view.findViewById(R.id.buttonBackward);
        buttonForward = (Button) view.findViewById(R.id.buttonForward);
        buttonListeners();
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

    private int nextSong() {
        if (songCounter > 2) {
            songCounter = 0;
        } else {
            currentSong = songList.get(songCounter);
            songCounter++;
        }
        return currentSong;
    }

    private int prevSong() {
        if (songCounter < 0) {
            songCounter = 2;
        } else {
            currentSong = songList.get(songCounter);
            songCounter--;
        }
        return currentSong;
    }

    public void musicPlayer(int selection){
        switch (selection){
            //Play/pause
            case 1:
                if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
                    Toast.makeText(getActivity(), "Play",
                            Toast.LENGTH_SHORT).show();
                    mediaPlayer = MediaPlayer.create(getActivity().getBaseContext(), currentSong);
                    mediaPlayer.start();
                } else if(mediaPlayer != null && mediaPlayer.isPlaying()) {
                    Toast.makeText(getActivity(), "Pause",
                            Toast.LENGTH_SHORT).show();
                    mediaPlayer.pause();
                }

                break;
            //Forward
            case 2:
                Toast.makeText(getActivity(), "Forward",
                        Toast.LENGTH_SHORT).show();
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(getActivity().getBaseContext(), nextSong());
                    mediaPlayer.start();
                }
                break;
            //Backward
            case 3:
                Toast.makeText(getActivity(), "Backward",
                        Toast.LENGTH_SHORT).show();
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(getActivity().getBaseContext(), prevSong());
                    mediaPlayer.start();
                }
                break;
            //Stop
            case 4:
                Toast.makeText(getActivity(), "Stop",
                        Toast.LENGTH_SHORT).show();
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                break;
            default:
                break;
        }
    }

}
