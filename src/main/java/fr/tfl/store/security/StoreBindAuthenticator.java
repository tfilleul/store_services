package fr.tfl.store.security;

import javax.naming.InvalidNameException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.ldap.LdapName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.AbstractContextSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.ldap.authentication.AbstractLdapAuthenticator;

import fr.anses.ct.common.transverse.ExceptionFactory;
import fr.anses.ct.ldap.persistance.impl.oe.User;
import fr.anses.ct.ldap.persistance.impl.oe.UserAuthentification;
import fr.anses.ct.ldap.service.ILdapService;
import fr.tfl.store.transverse.IRfaMessage;

public class StoreBindAuthenticator extends AbstractLdapAuthenticator {
	
	/** Logger **/
	private static final Logger logger = LoggerFactory.getLogger(StoreBindAuthenticator.class);

	private ILdapService ldapService;

	private ExceptionFactory exceptionFactory;

	
	public StoreBindAuthenticator(ContextSource contextSource) {
		super(contextSource);
		AbstractContextSource ldapContexte = (AbstractContextSource) contextSource;
		logger.debug(ldapContexte.toString());
	}

	public DirContextOperations authenticate(Authentication authentication) {

	     UserAuthentification userAuthentication = new UserAuthentification();
	     userAuthentication.setLogin(authentication.getName());
	     userAuthentication.setPassword((String)authentication.getCredentials());
	     
	     User user = ldapService.ldap_s05_authentify(userAuthentication);
	     
	     if (user == null) {
	    	exceptionFactory.throwRfaException(IRfaMessage.LDAP_BAD_CREDENTIALS_403, userAuthentication.getLogin());
	     }
	     
	     Attributes attrs = new BasicAttributes();
	     for (String attr : user.getAttributes().keySet()){
	    	 attrs.put(attr, user.getAttributes().get(attr));
	     }
	     
	     try {
			DirContextAdapter result = new DirContextAdapter(attrs, new LdapName(user.getDn()));
			return result;
		} catch (InvalidNameException ine) {
			exceptionFactory.throwRfaException(IRfaMessage.READ_DN_ERROR_403, user.getDn(), ine);
		}
	     
		return null;
	}
	
	@Override
	public void afterPropertiesSet() {
		logger.info("Méthode afterPropertiesSet vide pour RfaBindAuthenticator");
	}

	/**
	 * @return the ldapService
	 */
	public ILdapService getLdapService() {
		return ldapService;
	}

	/**
	 * @param ldapService the ldapService to set
	 */
	public void setLdapService(ILdapService ldapService) {
		this.ldapService = ldapService;
	}

	/**
	 * @return the exceptionFactory
	 */
	public ExceptionFactory getExceptionFactory() {
		return exceptionFactory;
	}

	/**
	 * @param exceptionFactory the exceptionFactory to set
	 */
	public void setExceptionFactory(ExceptionFactory exceptionFactory) {
		this.exceptionFactory = exceptionFactory;
	}




	

}
