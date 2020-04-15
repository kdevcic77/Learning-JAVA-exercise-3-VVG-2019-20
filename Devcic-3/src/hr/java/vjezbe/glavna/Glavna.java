package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.Kategorija;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.entitet.Usluga;

/**
 * Predstavlja programski dio koda koji slu�i za kreiranje i objavu oglasa
 * 
 * @author deva
 * @version Devcic-3
 */
public class Glavna {

    private static final String FORMAT_DATUMA = "dd.MM.yyyy.";

    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

    /**
     * @param args predstavlja unos sa tipkovnice broja korisnika oglasnika, odabir
     *             tipa korisnika kao i izvr�avanje metode kreiranja korisnika
     *             zavisno od odabira; tako�er predstavlja unos broja kategorija
     *             oglasnika, te izvr�avanje metode kreiranja kategorije; Nakon
     *             unosa svih tra�enih podataka, potrebno je odabrati aktivne
     *             artikle koji su za prodaju, a zavr�etkom odabira artikala za
     *             prodaju, trenutno aktivni oglasi se ispisuju na konzolnom ekranu
     *             sa pripadaju�im podacima
     */

    public static void main(String[] args) {

	logger.info("Pokretanje programa");

	Scanner ucitavac = new Scanner(System.in);

	System.out.print("Unesite broj korisnika koje �elite unijeti: ");
	int brojKorisnika = ucitavac.nextInt();
	ucitavac.nextLine();

	Korisnik[] korisnici = new Korisnik[brojKorisnika];
	for (int i = 0; i < brojKorisnika; i++) {
	    System.out.println("Unesite tip " + (i + 1) + ". korisnika");
	    int odabraniBrojTipaKorisnika = 0;
	    do {
		odabraniBrojTipaKorisnika = 1;
		System.out.println(odabraniBrojTipaKorisnika + ". Privatni");
		System.out.println((odabraniBrojTipaKorisnika + 1) + ". Poslovni");
		System.out.print("Odabir ->> ");
		odabraniBrojTipaKorisnika = ucitavac.nextInt();
		ucitavac.nextLine();
	    } while (odabraniBrojTipaKorisnika != 1 && odabraniBrojTipaKorisnika != 2);

	    if (odabraniBrojTipaKorisnika == 1) {

		korisnici[i] = unesiPrivatnogKorisnika(ucitavac, i + 1);
	    }
	    if (odabraniBrojTipaKorisnika == 2) {

		korisnici[i] = unesiPoslovnogKorisnika(ucitavac, i + 1);
	    }

	}

	for (int j = 0; j < 100; j++) {
	    System.out.print("-");
	}
	System.out.println();
	System.out.print("Unesite broj kategorija koje �elite unijeti: ");
	int brojKategorija = ucitavac.nextInt();
	ucitavac.nextLine();
	Kategorija[] kategorije = new Kategorija[brojKategorija];

	for (int k = 0; k < kategorije.length; k++) {
	    kategorije[k] = unesiKategoriju(ucitavac, k);

	}
	System.out.print("Unesite broj artikala koji su aktivno na prodaju: ");
	int brojAktivnihOglasa = ucitavac.nextInt();
	ucitavac.nextLine();
	Prodaja[] aktivneProdaje = new Prodaja[brojAktivnihOglasa];

	for (int i = 0; i < aktivneProdaje.length; i++) {
	    aktivneProdaje[i] = unesiProdaju(ucitavac, i, korisnici, kategorije);
	}

	ucitavac.close();

	System.out.println("Trenutno su oglasi na prodaju:");
	for (int j = 0; j < 100; j++) {
	    System.out.print("-");
	}
	for (Prodaja aktivnaProdaja : aktivneProdaje) {
	    System.out.println("\n" + aktivnaProdaja.getArtikl().tekstOglasa());
	    LocalDate datumObjave = aktivnaProdaja.getDatumObjave();
	    String datumObjaveString = datumObjave.format(DateTimeFormatter.ofPattern(FORMAT_DATUMA));
	    System.out.println("Datum objave: " + datumObjaveString);
	    System.out.println(aktivnaProdaja.getKorisnik().dohvatiKontakt());
	    for (int j = 0; j < 100; j++) {
		System.out.print("-");
	    }
	}
    }

    /**
     * Kreira novi objekt prodaje artikla na temelju odabira korisnika, kategorije
     * oglasa kao i pripadaju�eg artikla kategorije
     * 
     * @param ucitavac   omogu�ava korisni�ki unos, odnosno predstavlja Scanner
     *                   objekt u obliku varijable koji u ovom slu�aju omogu�ava
     *                   korisni�ki unos putem tipkovnice
     * @param i          omogu�ava prijenos cijelog broja kao parametra trenutnog
     *                   indeksa polja u koji se sprema kreirani objekt prodaje
     * @param korisnici  podaci o kreiranim korisnicimaa
     * @param kategorije podaci o kreiranim kategorijama sa pripadaju�im artiklima
     * @return vra�a novi objekt prodaje odn. oglas
     */
    private static Prodaja unesiProdaju(Scanner ucitavac, int i, Korisnik[] korisnici, Kategorija[] kategorije) {
	Integer redniBrojKorisnika = 0;
	System.out.println("Odaberite korisnika: ");
	for (int j = 0; j < korisnici.length; j++) {
	    System.out.println((j + 1) + ". " + korisnici[j].dohvatiKontakt());
	}
	System.out.print("Odabir korisnika >> ");
	redniBrojKorisnika = ucitavac.nextInt();
	ucitavac.nextLine();
	Korisnik odabraniKorisnik = korisnici[redniBrojKorisnika - 1];

	Integer redniBrojKategorije = 0;
	System.out.println("Odaberite kategoriju: ");
	for (int j = 0; j < kategorije.length; j++) {
	    System.out.println((j + 1) + ". " + kategorije[j].getNaziv());
	}
	System.out.print("Odabir kategorije >> ");
	redniBrojKategorije = ucitavac.nextInt();
	ucitavac.nextLine();
	Kategorija odabranaKategorija = kategorije[redniBrojKategorije - 1];

	Artikl[] artikliKategorije = new Artikl[kategorije.length];
	artikliKategorije = odabranaKategorija.getArtikli();
	Integer redniBrojArtikla = 0;

	System.out.println("Odaberite artikl:");
	for (int j = 0; j < odabranaKategorija.getArtikli().length; j++) {
	    System.out.println((j + 1) + ". " + artikliKategorije[j].getNaslov());
	}
	System.out.print("Odabir artikla >> ");
	redniBrojArtikla = ucitavac.nextInt();
	ucitavac.nextLine();
	Artikl odabraniArtikl = artikliKategorije[redniBrojArtikla - 1];

	LocalDate datumObjave = LocalDate.now();
	return new Prodaja(odabraniArtikl, odabraniKorisnik, datumObjave);
    }

    /**
     * Kreira novi objekt usluga na temelju unosa sa tipkovnice
     * 
     * @param ucitavac omogu�ava korisni�ki unos, odnosno predstavlja Scanner objekt
     *                 u obliku varijable koji u ovom slu�aju omogu�ava korisni�ki
     *                 unos putem tipkovnice
     * @param i        omogu�ava prijenos cijelog broja kao parametra trenutnog
     *                 indeksa polja u koji se sprema kreirani objekt kategorije
     * @return vra�a novo kreirani objekt usluge sa podacima o naslovu, opisu i
     *         cijeni usluge
     */
    private static Usluga unesiUslugu(Scanner ucitavac, int i) {
	System.out.print("Unesite naslov " + i + ". oglasa usluge: ");
	String naslov = ucitavac.nextLine();
	System.out.print("Unesite opis " + i + ". oglasa usluge: ");
	String opis = ucitavac.nextLine();
	System.out.print("Unesite cijenu " + i + ". oglasa usluge: ");
	BigDecimal cijena = ucitavac.nextBigDecimal();
	ucitavac.nextLine();
	return new Usluga(naslov, opis, cijena);

    }

    /**
     * Kreira novi objekt automobila na temelju unosa sa tipkovnice
     * 
     * @param ucitavac omogu�ava korisni�ki unos, odnosno predstavlja Scanner objekt
     *                 u obliku varijable koji u ovom slu�aju omogu�ava korisni�ki
     *                 unos putem tipkovnice
     * @param i        omogu�ava prijenos cijelog broja kao parametra trenutnog
     *                 indeksa polja u koji se sprema kreirani objekt kategorije
     * @return vra�a novo kreirani objekt automobila sa podacima o naslovu, opisu,
     *         snazi izra�enoj u konjskim snagama kao i cijenom automobila
     */
    private static Automobil unesiAutomobil(Scanner ucitavac, int i) {
	System.out.print("Unesite naslov " + i + ". oglasa automobila: ");
	String naslov = ucitavac.nextLine();
	System.out.print("Unesite opis " + i + ". oglasa automobila: ");
	String opis = ucitavac.nextLine();
	System.out.print("Unesite snagu (u ks) " + i + ". oglasa automobila: ");
	BigDecimal snagaKs = ucitavac.nextBigDecimal();
	ucitavac.nextLine();
	System.out.print("Unesite cijenu " + i + ". oglasa automobila: ");
	BigDecimal cijena = ucitavac.nextBigDecimal();
	ucitavac.nextLine();
	return new Automobil(naslov, opis, snagaKs, cijena);
    }

    /**
     * Kreira novi objekt kategorije na temelju unosa sa tipkovnice
     * 
     * @param ucitavac omogu�ava korisni�ki unos, odnosno predstavlja Scanner objekt
     *                 u obliku varijable koji u ovom slu�aju omogu�ava korisni�ki
     *                 unos putem tipkovnice
     * @param i        omogu�ava prijenos cijelog broja kao parametra trenutnog
     *                 indeksa polja u koji se sprema kreirani objekt kategorije
     * @return vra�a novo kreirani objekt kategorije sa podacima o nazivu, brojem
     *         artikala koji spadaju u tu kategoriju, tipu artikla kao i samim
     *         artiklima koji spadaju u tu kategoriju
     */
    private static Kategorija unesiKategoriju(Scanner ucitavac, int i) {
	System.out.print("Unesite naziv " + (i + 1) + ". kategorije: ");
	String naziv = ucitavac.nextLine();
	naziv = naziv.substring(0, 1).toUpperCase() + naziv.substring(1).toLowerCase();
	System.out.print("Unesite broj artikala koji �elite unijeti za unesenu kategoriju: ");
	int brojArtikalaKategorije = ucitavac.nextInt();
	ucitavac.nextLine();
	Artikl[] artikliKategorije = new Artikl[brojArtikalaKategorije];
	for (int j = 0; j < artikliKategorije.length; j++) {
	    System.out.println("Unesite tip " + (j + 1) + ". artikla");
	    int k = 0;
	    do {
		k = 1;
		System.out.println(k + ". Usluga");
		System.out.println((k + 1) + ". Automobili");
		System.out.print("Odabir ->> ");
		k = ucitavac.nextInt();
		ucitavac.nextLine();
	    } while (k != 1 && k != 2);
	    if (k == 1) {
		artikliKategorije[j] = unesiUslugu(ucitavac, j + 1);
	    }
	    if (k == 2) {
		artikliKategorije[j] = unesiAutomobil(ucitavac, j + 1);
	    }
	}
	return new Kategorija(naziv, artikliKategorije);
    }

    /**
     * Kreira novi objekt privatnog korisnika na temelju unosa sa tipkovnice
     * 
     * @param ucitavac omogu�ava korisni�ki unos, odnosno predstavlja Scanner objekt
     *                 u obliku varijable koji u ovom slu�aju omogu�ava korisni�ki
     *                 unos putem tipkovnice
     * @param i        omogu�ava prijenos cijelog broja kao parametra trenutnog
     *                 indeksa polja u koji se sprema kreirani objekt
     * @return vra�a novo kreiranog privatnog korisnika sa podacima o imenu,
     *         prezimenu, email-u i broju telefona
     */

    private static PrivatniKorisnik unesiPrivatnogKorisnika(Scanner ucitavac, int i) {
	System.out.print("Unesite ime " + i + ". osobe: ");
	String ime = ucitavac.nextLine();
	ime = ime.substring(0, 1).toUpperCase() + ime.substring(1).toLowerCase();
	System.out.print("Unesite prezime " + i + ". osobe: ");
	String prezime = ucitavac.nextLine();
	prezime = prezime.substring(0, 1).toUpperCase() + prezime.substring(1).toLowerCase();
	System.out.print("Unesite email " + i + ". osobe: ");
	String email = ucitavac.nextLine();
	System.out.print("Unesite telefon " + i + ". osobe: ");
	String telefon = ucitavac.nextLine();
	return new PrivatniKorisnik(ime, prezime, email, telefon);
    }

    /**
     * Kreira novi objekt poslovnog korisnika na temelju unosa sa tipkovnice
     * 
     * @param ucitavac omogu�ava korisni�ki unos, odnosno predstavlja Scanner objekt
     *                 u obliku varijable koji u ovom slu�aju omogu�ava korisni�ki
     *                 unos putem tipkovnice
     * @param i        omogu�ava prijenos cijelog broja kao parametra trenutnog
     *                 indeksa polja u koji se sprema kreirani objekt prodaje
     * @return vra�a novo kreiranog poslovnog korisnika sa podacima o nazivu,
     *         email-u, web stranici i broju telefona
     */

    private static PoslovniKorisnik unesiPoslovnogKorisnika(Scanner ucitavac, int i) {
	System.out.print("Unesite naziv " + i + ". tvrtke: ");
	String naziv = ucitavac.nextLine();
	naziv = naziv.substring(0, 1).toUpperCase() + naziv.substring(1).toLowerCase();
	System.out.print("Unesite e-Mail " + i + ". tvrtke: ");
	String email = ucitavac.nextLine();
	System.out.print("Unesite web " + i + ". tvrtke: ");
	String web = ucitavac.nextLine();
	System.out.print("Unesite telefon " + i + ". tvrtke: ");
	String telefon = ucitavac.nextLine();
	return new PoslovniKorisnik(naziv, email, web, telefon);
    }

}
