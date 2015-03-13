package fr.tfl.store.services;

import fr.tfl.store.bean.CredentialImpl;
import fr.tfl.store.model.UserModel;

public interface IAuthService {
	
	public UserModel auth(CredentialImpl credential);

}
