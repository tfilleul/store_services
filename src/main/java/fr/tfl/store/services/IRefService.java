package fr.tfl.store.services;

import fr.tfl.store.model.RefDTO;

public interface IRefService {

	RefDTO loadRef(int type);

}
