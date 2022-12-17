package io.springboot.main.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity   //specifies that the class is an entity and is mapped to a database table

@Table(name ="employees")       // to create the table in database 
public class Employees {
	
	@Id                               //primary key 
	@GeneratedValue(strategy = GenerationType.IDENTITY) //tells orm how to figure out the value of that field
	private long id;
	
	@Column(name = "first_name") //to create the column in the table
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email_id")
	private String emailId;
	

	public Employees() {
		// TODO Auto-generated constructor stub
	}


	public Employees(String firstName, String lastName, String emailId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}




	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	

}

//The entity name defaults to the name of the class. We can change its name using the name element.
//we can also change the name using name element
//The value can be AUTO, TABLE, SEQUENCE, or IDENTITY.