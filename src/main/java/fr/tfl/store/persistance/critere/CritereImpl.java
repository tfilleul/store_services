package fr.tfl.store.persistance.critere;

import java.io.Serializable;

public class CritereImpl implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String firstname;
	String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFistname() {
		return firstname;
	}

	public void setFistname(String fistname) {
		this.firstname = fistname;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	
}
