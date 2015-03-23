package fr.tfl.store.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import fr.tfl.store.bean.Profil;

public class ProfilModel implements Serializable {
	
	private Long id;
	private String profil;
	private String label;
	private int version;	
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProfil() {
		return profil;
	}
	public void setProfil(String profil) {
		this.profil = profil;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public static List<ProfilModel> copyModel(List<Profil> listProfil) {
		
		List<ProfilModel> listProfilM = new ArrayList<ProfilModel>();
		for (Profil profil : listProfil) {
			ProfilModel profilModel = new ProfilModel();
			BeanUtils.copyProperties(profil,profilModel);
			listProfilM.add(profilModel);
		}
		return listProfilM;
	}
		
}
