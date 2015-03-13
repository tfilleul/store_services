package fr.tfl.store.bean;

import java.util.Calendar;
import java.util.List;

public class AuthentificationBean {
	
	/**
	 * Token d'authentification
	 */
	String token;
	
	/**
	 * Login de l'utilisateur
	 */
	String login;
	
	/**
	 * Password de l'utilisateur
	 */
	String password ;
	
	/**
	 * Prénom
	 */
	String firstName;
	
	/**
	 * fullName
	 */
	String fullName;
	
	
	/**
	 * Nom
	 */
	String lastName;

	/**
	 * Derniere connexion
	 */
	Calendar lastConnexionDate;
	

	
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the lastConnexionDate
	 */
	public Calendar getLastConnexionDate() {
		return lastConnexionDate;
	}

	/**
	 * @param lastConnexionDate the lastConnexionDate to set
	 */
	public void setLastConnexionDate(Calendar lastConnexionDate) {
		this.lastConnexionDate = lastConnexionDate;
	}

	
	
	

}
