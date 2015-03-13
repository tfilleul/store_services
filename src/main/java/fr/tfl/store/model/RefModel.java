package fr.tfl.store.model;

import java.io.Serializable;
import java.util.List;

public class RefModel implements Serializable {
	
	private List<ProfilModel> listProfil;

	public List<ProfilModel> getListProfil() {
		return listProfil;
	}

	public void setListProfil(List<ProfilModel> listProfil) {
		this.listProfil = listProfil;
	}

}
