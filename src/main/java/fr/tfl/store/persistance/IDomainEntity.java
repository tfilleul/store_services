package fr.tfl.store.persistance;

import java.io.Serializable;

public interface IDomainEntity<ID extends Serializable> extends Serializable {
	
	ID getId();

}
