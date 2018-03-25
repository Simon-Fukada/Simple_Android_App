package com.example.a772515.SimpleAndroidApp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
    /*Author: Simon Fukada
      Date: Fall 2018
      Purpose: Class to provide CRUD operations for application
     */

public class AgentDataSource {

    private DBHelper helper;//Initialize helper variable
    private SQLiteDatabase db;//Initialize db variable


    public AgentDataSource(Context context){//Constructor for AgentDataSource
        helper = new DBHelper(context);
    }

    public void open()
    {
        db = helper.getWritableDatabase();
    }

    public void close()
    {
        helper.close();
    }


    public ArrayList<Agent> getAllAgents()//Method to get agents from database and return them in an ArrayList
    {
        ArrayList<Agent> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT*FROM agents", null);//Set up cursor with sql statement to get information from database

        while(cursor.moveToNext())
        {
            //Make agent object from cursor
            Agent agent = new Agent(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getString(3));

            list.add(agent);
        }

        return list;
    }

    public long insertAgent(Agent agent)//Method to insert new agents into database
    {
        SQLiteStatement stmt = db.compileStatement("INSERT INTO agents(AgtFirstName, AgtBusPhone, AgtEmail) VALUES(?,?,?)");
        stmt.bindString(1, agent.getAgtFirstName());
        stmt.bindString(2,agent.getAgtBusPhone());
        stmt.bindString(3,agent.getAgtEmail());
        return stmt.executeInsert();
    }

    public int updateAgent(Agent agent)//Method to update existing agents in database
    {
        SQLiteStatement stmt = db.compileStatement("UPDATE agents SET agtFirstName=?, AgtBusPhone=?,AgtEmail=? WHERE agentId=?");
        stmt.bindString(1,agent.getAgtFirstName());
        stmt.bindString(2,agent.getAgtBusPhone());
        stmt.bindString(3,agent.getAgtEmail());
        stmt.bindString(4,agent.getAgentId() + "");
        return stmt.executeUpdateDelete();
    }

    public int deleteAgent(Agent agent)//Method to delete existing agents from database
    {
        SQLiteStatement stmt = db.compileStatement("DELETE FROM agents WHERE agentId=?");
        stmt.bindString(1,agent.getAgentId() + "");
        return stmt.executeUpdateDelete();
    }

}
