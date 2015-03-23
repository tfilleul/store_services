package fr.tfl.store.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.tfl.store.bean.Profil;
import fr.tfl.store.model.ProfilModel;
import fr.tfl.store.model.RefModel;
import fr.tfl.store.persistance.IRefDao;
import fr.tfl.store.services.IRefService;

/**
 * Couche service User
 * @author TFILLEUL
 *
 */
@Service("refServiceImpl")
@Transactional
public class RefServiceImpl implements IRefService {
	
	@Autowired
	private IRefDao refDao;
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(RefServiceImpl.class);

	@Transactional(readOnly = true)
	public RefModel loadRef(int type) {		
		List<Profil> listProfil = refDao.findAll();
		List<ProfilModel> listProfilM = ProfilModel.copyModel(listProfil);
		RefModel ref = new RefModel();
		ref.setListProfil(listProfilM);
		return ref;
	}	
}
