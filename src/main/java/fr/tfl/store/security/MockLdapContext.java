package fr.tfl.store.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.support.LdapContextSource;

import fr.anses.ct.ldap.persistance.impl.LdapPersistanceImpl;

public class MockLdapContext extends LdapContextSource {
	
	  /** Le logger. */
	  private static final Logger LOGGER = LoggerFactory.getLogger(LdapPersistanceImpl.class);

	public MockLdapContext(){
		super();
	}
	
	@Override
	public void afterPropertiesSet(){
		LOGGER.info("Méthode afterPropertiesSet vide pour MockLdapContext");
	}
}
