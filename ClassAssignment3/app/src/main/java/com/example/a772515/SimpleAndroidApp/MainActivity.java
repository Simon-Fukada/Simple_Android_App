package com.example.a772515.SimpleAndroidApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /*Author: Simon Fukada
      Date: Fall 2018
      Purpose: MainActivity for mobile application
     */
    ListView lvAgents;//initialize variable for list view
    AgentDataSource source;//initialize variable for agentdatasource
    Button AddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadActivity();
    }
    //Override method to refresh main activity when returning to it
    @Override
    protected void onStart() {
        super.onStart();
        LoadActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        source.close();
    }

    public void LoadActivity()//Method to create/start main activity
    {
        lvAgents = findViewById(R.id.lvAgents);//set list view variable to list view on main activity
        AddButton = findViewById(R.id.btnAddNew);

        source = new AgentDataSource(this);//Create new AgentDataSource
        source.open();//Call open method in AgentDataSource to get writable database

        ArrayList<Agent> agents = source.getAllAgents();//receives list of agents from database
        ContactAdapter adapter = new ContactAdapter(this, agents);//setting adapter with agents ArrayList to populate List view

        lvAgents.setAdapter(adapter);//set list view with adapter

        //Click Listener to "Listen" for user to select a agent from the listView
        lvAgents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Agent agent = (Agent)lvAgents.getItemAtPosition(position);//Receive agent object that user clicked
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);//Too Pass Data over to other activity
                intent.putExtra("agent", agent);//Insert agent into intent
                startActivity(intent);
            }
        });
        //Click Listener to "Listen" for user to push the add new agent button
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), AddAgentActivity.class);//Create intent object with new activity
                startActivity(intent2);//start new activity
            }
        });


    }
}


