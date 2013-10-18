package fr.istic.tpjpa.domain;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Equipements {
	private Long id;
	private String Genre;
	private String Consomation;
	private Home Habitation;
	public Equipements(String genre, String consomation, Home habitation){
             setGenre(genre);
             setConsomation(consomation);
             Habitation=habitation;
	}
	
	public Equipements(){
	}
	
	public String getGenre() {
		return Genre;
	}
	
	public void setGenre(String genre) {
		Genre = genre;
	}
	
	public String getConsomation() {
		return Consomation;
	}
	
	public void setConsomation(String consomation) {
		Consomation = consomation;
	}
	
	@Id
    @GeneratedValue

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    } 
    
    
    @ManyToOne(cascade = CascadeType.PERSIST) // un pour plusieurs
    public Home getHabitation(){
    	return Habitation;
    }
    
    public void setHabitation(Home Habitation){
    	this.Habitation=Habitation;
    }
    
    
    @Override
    public String toString(){
    return "Equipements[id="+id+", Genre="+Genre+", Consomation="+Consomation+", Habitation="+Habitation+"]";
    }
}
