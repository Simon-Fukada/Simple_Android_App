package com.example.a772515.SimpleAndroidApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddAgentActivity extends AppCompatActivity {
    /*Author: Simon Fukada
      Date: Fall 2018
      Purpose: AddAgentActivity for mobile application
     */

    Button AddButton;
    EditText agentName;
    EditText agentPhone;
    EditText agentEmail;
    AgentDataSource source;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_agent);

        agentName = findViewById(R.id.etAgentName);
        agentPhone = findViewById(R.id.etAgentPhone);
        agentEmail = findViewById(R.id.etAgentEmail);
        AddButton = findViewById(R.id.btnAddAgent);
        source = new AgentDataSource(this);
        //Button to add agent to database
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Agent agent = new Agent(0,agentName.getText().toString(),agentPhone.getText().toString(),agentEmail.getText().toString());
                source.open();
                long result = source.insertAgent(agent);//Call function to insert agent to database
                source.close();
                //Show message to user of suscessful action
                if(result != 0)
                {
                    Toast.makeText(getApplicationContext(),"New Agent added!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Add Failed!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
