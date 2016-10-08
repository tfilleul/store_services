package fr.tfl.store.persistance.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.tfl.store.bean.Profil;
import fr.tfl.store.persistance.IRefDao;

@Repository
public class RefDaoImpl extends AbstractDao<Profil, Long> implements IRefDao{
	
	public RefDaoImpl() {
		super(Profil.class);
	}
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(RefDaoImpl.class);
	
		
}