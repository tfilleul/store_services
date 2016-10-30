package fr.tfl.store.model;

import java.io.Serializable;
import java.util.List;

public class RefDTO implements Serializable {
	
	private List<ProfilDTO> listProfil;

	public List<ProfilDTO> getListProfil() {
		return listProfil;
	}

	public void setListProfil(List<ProfilDTO> listProfil) {
		this.listProfil = listProfil;
	}

}
