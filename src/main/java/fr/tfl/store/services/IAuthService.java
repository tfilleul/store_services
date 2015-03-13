package fr.tfl.store.services;

import fr.tfl.store.bean.CredentialImpl;
import fr.tfl.store.bean.User;

public interface IAuthService {
	
	public User auth(CredentialImpl credential);

}
