
import java.util.List;
import java.util.ArrayList;
public class Osoba {

    private String imie;
    private String nazwisko;
    private String dataUro;
    private String dataPierwWynaj;


    public Osoba(String imie, String nazwisko, String dataUro) {

        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUro = dataUro;

    }


    List<Pomieszczenie> listaPomOs = new ArrayList<>();

    public void wynajmij(Pomieszczenie pom, String dataWynaj) {
        if (pom.getPowodWylaczenia() == null && pom.isJuzWynajete()==false) {

            if (dataPierwWynaj == null)
                dataPierwWynaj = dataWynaj;


            listaPomOs.add(pom);
            pom.setJuzWynajete(true);
        }else
            System.out.println("Nie mozna wynająć pomieszczenia nr. "+pom.getIdPom() + " - "+ (pom.isJuzWynajete() ? "juz wynajęte" : pom.getPowodWylaczenia()));

    }

    public String getDataPierwWynaj() throws NeverRentException {


        if (dataPierwWynaj == null)
            throw new NeverRentException();
        else
            return dataPierwWynaj;


    }

    @Override
    public String toString() {
        try {
            return "IMIE I NAZWISKO: " + imie  +" "+ nazwisko + " DATA URODZENIA: " + dataUro + " DATA PIERWSZEGO WYNAJMU: " + this.getDataPierwWynaj();
        } catch (NeverRentException e) {
            //System.out.println("Do tej pory nie wynajeto zadnego pomieszczenia!");
            return "IMIE I NAZWISKO: " + imie  +" "+ nazwisko + " DATA URODZENIA: " + dataUro + " DATA PIERWSZEGO WYNAJMU: ----";
        }

    }
    public String getImie(){ return imie;}
    public String getNazwisko(){ return nazwisko;}

}
