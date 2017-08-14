package com.demos.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
