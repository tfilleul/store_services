package fr.tfl.store.transverse;

public interface IRfaMessage {
  public final String HEADER_READ_ERROR = "HEADER_READ_ERROR";
  public final String LIGNE_OUTOFBOUNDS_ERROR = "LIGNE_OUTOFBOUNDS_ERROR";
  public final String LIGNE_READ_ERROR = "LIGNE_READ_ERROR";
  public final String FILE_READ_ERROR = "FILE_READ_ERROR";
  public final String FILE_HEADER_INCOMPLETE = "FILE_HEADER_INCOMPLETE";
  public final String NOT_ENOUGHT_PARAMETERS = "NOT_ENOUGHT_PARAMETERS";
  public final String ERREUR_APPEL_BDD = "ERREUR_APPEL_BDD";
  public final String OBJECT_NOT_FOUND = "OBJECT_NOT_FOUND";
  public final String LDAP_BAD_CREDENTIALS_403 = "LDAP_BAD_CREDENTIALS_403";
  public final String READ_DN_ERROR_403 = "READ_DN_ERROR_403";
  public final String IMPORT_NODE_ERROR = "IMPORT_NODE_ERROR";
  public final String ERREUR_RELATION_PROCESS = "ERREUR_RELATION_PROCESS";
  public final String ERREUR_NODE_PROCESS = "ERREUR_NODE_PROCESS";
  public final String THESAURUS_NOT_FOUND = "THESAURUS_NOT_FOUND";
  public final String ERROR_FORMAT = "ERROR_FORMAT";
  public final String ERROR_THESAURUS_DELETE = "ERROR_THESAURUS_DELETE";
  public final String ERROR_UNIT_403 = "ERROR_UNIT_403";
  public final String ERROR_DUPLICATE_THESAURUS = "ERROR_DUPLICATE_THESAURUS";
  public final String ERROR_DUPLICATE_NODE = "ERROR_DUPLICATE_NODE";
  public final String ERROR_EXPORT_NODE = "ERROR_EXPORT_NODE";
  public final String ERROR_DATA_FORMAT = "ERROR_DATA_FORMAT";
  public final String ERROR_UNKNOWN_NODE_LINK = "ERROR_UNKNOWN_NODE_LINK";
  public final String ERROR_IMPORT_NODE_LINK_FOUND = "ERROR_IMPORT_NODE_LINK_FOUND";
}
