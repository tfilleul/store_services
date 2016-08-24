package fr.tfl.store.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.tfl.store.bean.User;

public class ProductModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private ProfilModel profil;
	private String name;
	private String firstname;
	private String password;
	private String mail;
	private Date birthdate;
	private int age;
	private int version;
	
	public ProductModel copyModel(User user) {
		ProfilModel profilModel = new ProfilModel();
		BeanUtils.copyProperties(user, this);
		BeanUtils.copyProperties(user.getProfil(), profilModel);
		this.setProfil(profilModel);
		return this;
	}
	
	public List<ProductModel> copyModel(List<User> users) {
		ProfilModel profilModel = null;
		List<ProductModel> listModel = new ArrayList<ProductModel>();
		for (User user : users) {
			profilModel = new ProfilModel();
			BeanUtils.copyProperties(user, this);
			BeanUtils.copyProperties(user.getProfil(), profilModel);
			this.setProfil(profilModel);
			listModel.add(this);
		}	
		return listModel;
	}	
	

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonIgnoreProperties(ignoreUnknown=true)
	public ProfilModel getProfil() {
		return profil;
	}

	public void setProfil(ProfilModel profil) {
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
		
}
