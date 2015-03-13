package fr.tfl.store.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

import fr.anses.ct.common.transverse.ExceptionFactory;
import fr.anses.ct.ldap.persistance.impl.oe.User;
import fr.anses.ct.ldap.service.ILdapService;
import fr.tfl.store.bean.AuthentificationBean;
import fr.tfl.store.security.TokenUtils;
import fr.tfl.store.transverse.IRfaMessage;


/**
 * Implémentation du service d'Authentification.
 * 
 * @author $Author: hhichri $
 * @version $Revision: 21 $
 */
public class AuthentificationServiceImpl implements IAuthentificationService {

  private ILdapService ldapService;

  private AuthenticationManager authManager;

  private ExceptionFactory exceptionFactory;

  private String baseDn;

  /**
   * Constructeur de la classe.
   */
  public AuthentificationServiceImpl() {

  }

  /**
   * {@inheritDoc}
   */
  public AuthentificationBean RFA_S20_logon(final AuthentificationBean ab) {
    // Utilisation de spring security
    try {
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(ab.getLogin(),
        ab.getPassword());
      Authentication authentication = this.authManager.authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      
      /*
       * Reload user as password of authentication principal will be null after authorization and
       * password is needed for token generation
       */
      LdapUserDetails ldapUser = (LdapUserDetails) authentication.getPrincipal();
      User user = this.ldapService.ldap_s01_search(ldapUser.getDn());

      ab.setToken(TokenUtils.createToken(user));
      if (user.getAttributes().containsKey("GIVENNAME")) {
        ab.setFirstName((String)user.getAttributes().get("GIVENNAME"));
      }

      if (user.getAttributes().containsKey("DISPLAYNAME")) {
        ab.setFullName((String) user.getAttributes().get("DISPLAYNAME"));
      }

    } catch (BadCredentialsException bce) {
      exceptionFactory.throwRfaException(IRfaMessage.LDAP_BAD_CREDENTIALS_403, ab.getLogin(), bce);
    }

    // Do not resend password
    ab.setPassword(null);
    return ab;
  }

  /**
   * {@inheritDoc}
   */
  public void RFA_S21_logoff(final AuthentificationBean ab) {
    SecurityContextHolder.getContext().setAuthentication(null);
  }

  /**
   * @return the ldapService
   */
  public ILdapService getLdapService() {
    return ldapService;
  }

  /**
   * @param ldapService
   *          the ldapService to set
   */
  public void setLdapService(final ILdapService ldapService) {
    this.ldapService = ldapService;
  }

  /**
   * @return the authManager
   */
  public AuthenticationManager getAuthManager() {
    return authManager;
  }

  /**
   * @param authManager
   *          the authManager to set
   */
  public void setAuthManager(final AuthenticationManager authManager) {
    this.authManager = authManager;
  }

  /**
   * @return the exceptionFactory
   */
  public ExceptionFactory getExceptionFactory() {
    return exceptionFactory;
  }

  /**
   * @param exceptionFactory
   *          the exceptionFactory to set
   */
  public void setExceptionFactory(final ExceptionFactory exceptionFactory) {
    this.exceptionFactory = exceptionFactory;
  }

  /**
   * @return the baseDn
   */
  public String getBaseDn() {
    return baseDn;
  }

  /**
   * @param baseDn
   *          the baseDn to set
   */
  public void setBaseDn(final String baseDn) {
    this.baseDn = baseDn;
  }

}
