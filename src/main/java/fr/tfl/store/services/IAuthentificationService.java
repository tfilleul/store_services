package fr.tfl.store.services;

import fr.tfl.store.bean.AuthentificationBean;


public interface IAuthentificationService {

	/**
	 * Méthode d'authentification d'un utilisateur aux services RFA
	 * @param authentificationBean
	 * @return AuthentificationBean
	 */
	public AuthentificationBean RFA_S20_logon(AuthentificationBean authentificationBean);
	
	/**
	 * Méthode de déconnexion du service
	 * @param authentificationBean
	 */
	public void RFA_S21_logoff(AuthentificationBean authentificationBean);
}
