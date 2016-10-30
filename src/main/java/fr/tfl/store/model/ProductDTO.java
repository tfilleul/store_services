package fr.tfl.store.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.stereotype.Component;

import fr.tfl.store.bean.Commande;
import fr.tfl.store.bean.Product;

@Component
public class ProductDTO implements IStoreDTO<Product>,Serializable {
	
	private static final long serialVersionUID = 1L;
	private UUID id;
	private String label;
	private String reference;
	private Integer status;
	private Integer quantity;
	private Commande commande;
	private double price;
	private String idpicture;	
	private int version;
	
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIdpicture() {
		return idpicture;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}


	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setIdpicture(String string) {
		// TODO Auto-generated method stub
		
	}
	
}
