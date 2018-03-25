package com.example.a772515.SimpleAndroidApp;
/*Author: Simon Fukada
      Date: Fall 2018
      Purpose: DetailActivity to display agent details once selected from ListView on MainActivity
     */
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    Button updateButton;
    Button deleteButton;
    AgentDataSource source;
    Agent agent;
    TextView name;
    TextView phone;
    TextView email;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        agent = (Agent) getIntent().getSerializableExtra("agent");//receives intent from main activity with agent object in it

        //Fill DetailActivity displays with information from agent object
        name = findViewById(R.id.etAgentName);
        name.setText(agent.toString());

        phone = findViewById(R.id.etAgentPhone);
        phone.setText(agent.getAgtBusPhone());

        email = findViewById(R.id.etAgentEmail);
        email.setText(agent.getAgtEmail());

        source = new AgentDataSource(this);
        updateButton = findViewById(R.id.btnUpdate);
        deleteButton = findViewById(R.id.btnDelete);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                source.open();
                Agent agentUpdate = new Agent(agent.getAgentId(), name.getText().toString(), phone.getText().toString(), email.getText().toString());
                int result = source.updateAgent(agentUpdate);
                if(result != 0)
                {
                    Toast.makeText(getApplicationContext(),"Update Successful!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Update Failed!",Toast.LENGTH_LONG).show();
                }

                source.close();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(context);
                }
                builder.setTitle("Delete entry");
                builder.setMessage("Are you sure you want to delete this entry?");
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        source.open();
                        Agent deleteAgent = new Agent(agent.getAgentId(),"","","");
                        int result = source.deleteAgent(deleteAgent);
                        if(result != 0)
                        {
                            Toast.makeText(getApplicationContext(),"Delete Successful!",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Delete Failed!",Toast.LENGTH_LONG).show();
                        }

                        source.close();
                    }
                });
                builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                });
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.show();
            }
        });
    }


}
