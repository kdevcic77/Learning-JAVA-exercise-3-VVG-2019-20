package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class Stan extends Artikl implements Nekretnina {
    private int kvadratura;

    public Stan( String naslov, String opis, int kvadratura, BigDecimal cijena) {
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
	String tekstOglasa = ("Naslov nekretnine: " + getNaslov() + "\nOpis nekretnine: " + getOpis()
		+ "\nKvadratura Nekretnine: " + getKvadratura() + "\nPorez na nekretnine: " +izracunajPorez(getCijena())+"\nCijena nekretnine " + getCijena());
	return tekstOglasa;
    }

}
