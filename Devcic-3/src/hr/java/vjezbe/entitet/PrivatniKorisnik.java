package hr.java.vjezbe.entitet;

public class PrivatniKorisnik extends Korisnik{

    private String ime;
    private String prezime;
    
    public PrivatniKorisnik(String ime, String prezime, String email, String telefon) {
	super(email, telefon);
	this.ime = ime;
	this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String dohvatiKontakt() {
	String kontaktPodaciPrivatni = ("Osobni podaci prodavatelja: "+getIme()+" "+getPrezime()+", mail: "+getEmail()+", tel: "+getTelefon());
	return kontaktPodaciPrivatni;
    }
    
}
