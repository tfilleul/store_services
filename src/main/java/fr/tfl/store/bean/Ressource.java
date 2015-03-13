package fr.tfl.store.bean;

// Generated 2 mars 2015 10:44:38 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Ressource generated by hbm2java
 */
@Entity
@Table(name = "ressource", catalog = "store")
public class Ressource implements java.io.Serializable {

	private Integer id;
	private int version;
	private String label;
	private String code;
	private Set<AclStore> aclStores = new HashSet<AclStore>(0);

	public Ressource() {
	}

	public Ressource(String label, String code) {
		this.label = label;
		this.code = code;
	}

	public Ressource(String label, String code, Set<AclStore> aclStores) {
		this.label = label;
		this.code = code;
		this.aclStores = aclStores;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Version
	@Column(name = "version", nullable = false)
	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Column(name = "label", nullable = false, length = 100)
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "code", nullable = false, length = 10)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ressource")
	public Set<AclStore> getAclStores() {
		return this.aclStores;
	}

	public void setAclStores(Set<AclStore> aclStores) {
		this.aclStores = aclStores;
	}

}
