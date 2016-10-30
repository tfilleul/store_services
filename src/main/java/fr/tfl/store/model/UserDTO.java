package fr.tfl.store.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.tfl.store.bean.User;

@Component
public class UserDTO implements IStoreDTO<User>,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private ProfilDTO profil;
	private String name;
	private String firstname;
	private String password;
	private String mail;
	private Date birthdate;
	private int version;
	private String idpicture;

	public UserDTO() {
		super();
	}

	public void setIdpicture(String idpicture) {
		this.idpicture = idpicture;
	}

	public UserDTO copyModel(User user) {
		ProfilDTO ProfilDTO = new ProfilDTO();
		BeanUtils.copyProperties(user, this);
		BeanUtils.copyProperties(user.getProfil(), ProfilDTO);
		this.setProfil(ProfilDTO);
		return this;
	}
	
	public List<UserDTO> copyModel(List<User> users) {
		ProfilDTO ProfilDTO = null;
		List<UserDTO> listModel = new ArrayList<UserDTO>();
		for (User user : users) {
			ProfilDTO = new ProfilDTO();
			BeanUtils.copyProperties(user, this);
			BeanUtils.copyProperties(user.getProfil(), ProfilDTO);
			this.setProfil(ProfilDTO);
			listModel.add(this);
		}	
		return listModel;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnoreProperties(ignoreUnknown=true)
	public ProfilDTO getProfil() {
		return profil;
	}

	public void setProfil(ProfilDTO profil) {
		this.profil = profil;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}	

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getIdpicture() {
		return idpicture;
	}
		
}
