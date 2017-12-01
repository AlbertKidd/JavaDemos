package com.kidd.demos.beans;

import lombok.Data;

import java.io.Serializable;
@Data
public class RegisterResult  implements Serializable {
	
	/**
	 * 
	 */
	private boolean success;
	private String errorMsg;
	private PubUcustomer ucustomer;
	
}
