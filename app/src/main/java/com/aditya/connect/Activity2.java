package com.aditya.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    ListView listView;
    TextView textView;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.textView3);
        String item[] = {"Name", "Branch", "Course", "College Name", "College code", "AKTU Roll number"};

        ListAdapter listItem = new ArrayAdapter<String>(this, R.layout.listview, item);
        listView.setAdapter(listItem);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String messege;
                if(position==0)
                {
                    messege = "Aditya Kumar";
                    textView.setText(messege);
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.name);
                    mediaPlayer.start();
                }
               else if(position==1) {
                    messege = "Computer Science";
                    textView.setText(messege);
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.branch);
                    mediaPlayer.start();
                }
                else if(position==2)
                {
                    messege = "B.Tech";
                    textView.setText(messege);
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.course);
                    mediaPlayer.start();
                }
                else if(position==3)
                {
                    messege = "Saroj Institute of Technology";
                    textView.setText(messege);
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.college_name);
                    mediaPlayer.start();
                }
                else if(position==4)
                {
                    messege = "123";
                    textView.setText(messege);
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.college_code);
                    mediaPlayer.start();
                }
                else if(position==5)
                {
                    messege = "1901230100001";
                    textView.setText(messege);
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.roll_number);
                    mediaPlayer.start();
                }
            }
        });
    }
}