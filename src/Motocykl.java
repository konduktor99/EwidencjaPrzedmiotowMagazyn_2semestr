
public class Motocykl extends Przedmiot{
    boolean homologacja;

    public Motocykl(boolean homologacja, String nazwa, double rozmiarPrzedm){
        super(nazwa,rozmiarPrzedm);
        this.homologacja=homologacja;
    }

    public String toString() {
        return "|MOTOCYKL| NAZWA: " + super.getNazwa()+ " HOMOLOGACJA: " + (homologacja?"posiada":"nie posiada") + " ROZMIAR: " + super.getRozmiarPrzedm();
    }




}
