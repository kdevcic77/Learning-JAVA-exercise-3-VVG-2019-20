package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;

public class Stan extends Artikl implements Nekretnina {
    
    public static final Logger logger = (Logger) LoggerFactory.getLogger(Stan.class);
    
    private int kvadratura;

    public Stan(String naslov, String opis, int kvadratura, BigDecimal cijena) {
	super(naslov, opis, cijena);
	this.kvadratura = kvadratura;
    }

    public int getKvadratura() {
	return kvadratura;
    }

    public void setKvadratura(int kvadratura) {
	this.kvadratura = kvadratura;
    }

    @Override
    public String tekstOglasa() {
	String izracunatPorez = "";
	try {
	    izracunatPorez = ("" + izracunajPorez(getCijena()));
	} catch (CijenaJePreniskaException e) {
	    izracunatPorez = ("Cijena ne smije biti manja od 10000kn");
	    logger.error(e.getMessage(), e);
	}
	String tekstOglasa = ("Naslov nekretnine: " + getNaslov() + "\nOpis nekretnine: " + getOpis()
		+ "\nKvadratura Nekretnine: " + getKvadratura() + "\nPorez na nekretnine: " + izracunatPorez
		+ "\nCijena nekretnine " + getCijena());
	return tekstOglasa;
    }

}
