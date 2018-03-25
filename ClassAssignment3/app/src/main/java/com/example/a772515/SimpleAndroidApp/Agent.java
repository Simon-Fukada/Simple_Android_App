package com.example.a772515.SimpleAndroidApp;
/*Author: Simon Fukada
      Date: Fall 2018
      Purpose: Class to store agent information
     */
import java.io.Serializable;

public class Agent implements Serializable {
	private int AgentId;
	private String AgtFirstName;
	private String AgtBusPhone;
	private String AgtEmail;

	
	public Agent(int agentId, String agtFirstName, String agtBusPhone, String agtEmail) {
		super();
		AgentId = agentId;
		AgtFirstName = agtFirstName;
		AgtBusPhone = agtBusPhone;
		AgtEmail = agtEmail;
	}

	public int getAgentId() {
		return AgentId;
	}

	public void setAgentId(int agentId) {
		AgentId = agentId;
	}

	public String getAgtFirstName() {
		return AgtFirstName;
	}

	public void setAgtFirstName(String agtFirstName) {
		AgtFirstName = agtFirstName;
	}


	public String getAgtBusPhone() {
		return AgtBusPhone;
	}

	public void setAgtBusPhone(String agtBusPhone) {
		AgtBusPhone = agtBusPhone;
	}

	public String getAgtEmail() {
		return AgtEmail;
	}

	public void setAgtEmail(String agtEmail) {
		AgtEmail = agtEmail;
	}


	@Override
	public String toString() {
		return AgtFirstName;
	}
	
	
}
