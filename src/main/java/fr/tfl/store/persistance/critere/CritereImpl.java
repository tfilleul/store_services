package fr.tfl.store.persistance.critere;

import java.io.Serializable;

public class CritereImpl implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String firstName;
	String age;
	String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
