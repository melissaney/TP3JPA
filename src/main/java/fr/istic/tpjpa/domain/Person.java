package fr.istic.tpjpa.domain;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Person {
	private Long id;
	private String prenom;
	private String nom;
	private String facebook;
	private Date anniversaire;
	private String genre;

//	private List<Person> amis = new ArrayList<Person>();
	private List<Home> proprietees = new ArrayList<Home>();
	public Person(Long id, String prenom, String nom, String facebook,
			Date anniversaire, String genre) {
		this.id = id;
		this.setPrenom(prenom);
		this.setNom(nom);
		this.setFacebook(facebook);
		this.setAnniversaire(anniversaire);
		this.setGenre(genre);
	}

	public Person() {
	}

	public Person(String prenom, String nom) {
		this.setPrenom(prenom);
		this.setNom(nom);
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public Date getAnniversaire() {
		return anniversaire;
	}

	public void setAnniversaire(Date anniversaire) {
		this.anniversaire = anniversaire;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

//	public List<Person> getAmis() {
//		return amis;
//	}
//
//	public void setAmis(List<Person> amis) {
//		this.amis = amis;
//	}

	public List<Home> getProprietees() {
		return proprietees;
	}

	public void setProprietees(List<Home> proprietees) {
		this.proprietees = proprietees;
	}

	// @ManyToMany
	// public List<Person> getamis(){
	// return amis;
	// }

	@OneToMany(mappedBy = "id", cascade = CascadeType.PERSIST)
	public List<Home> getproprietees() {
		return proprietees;
	}

	@Override
	public String toString() {

		return "Person [id=" + id + ", prenom=" + prenom + ", nom="

		+ nom + ", facebook=" + facebook + ", anniversaire=" + anniversaire
				+ ", genre=" + genre + "]";

	}

}
