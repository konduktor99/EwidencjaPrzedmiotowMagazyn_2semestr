
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pomieszczenie<T extends Pomieszczenie,E extends Przedmiot> implements Comparable<T>{

    private int idPom;
    private static int licznik;
    private double rozmiarPom;
    private double suma;
    private String powodWylaczenia;
    private boolean juzWynajete;

    List<E> listaPrzedmiotow = new ArrayList<>();

    public Pomieszczenie(double rozmiarPom){

        idPom=licznik++;
        this.rozmiarPom=rozmiarPom;
    }
    public Pomieszczenie(double rozmiarPom, String powodWylaczenia ){

        idPom=licznik++;
        this.rozmiarPom=rozmiarPom;
        this.powodWylaczenia=powodWylaczenia;

    }

    public void wloz(E e) throws TooManyThingsException{

            if(suma+e.getRozmiarPrzedm()>rozmiarPom)
                throw new TooManyThingsException();
            else{
            listaPrzedmiotow.add(e);
            suma += e.getRozmiarPrzedm();}

        Collections.sort(listaPrzedmiotow);
    }
    public void wyjmij(E e){
        listaPrzedmiotow.remove(e);
        suma-=e.getRozmiarPrzedm();
    }

    public void zmienRozmiar(E e,Double nowyRoz) throws TooManyThingsException {
        if (suma - (e.getRozmiarPrzedm() - nowyRoz)> rozmiarPom)
        throw new TooManyThingsException();
        else{
            suma = suma - (e.getRozmiarPrzedm() - nowyRoz);
            e.setRozmiarPrzedm(nowyRoz);

        }
    }

    public int getIdPom() {
        return idPom;
    }

    public double getRozmiarPom() {
        return rozmiarPom;
    }


    public String getPowodWylaczenia() {
        return powodWylaczenia;
    }

    public String toString(){
      return "POMIESZCZENIE NR. "+idPom+" - ROZMIAR: "+rozmiarPom;
    }


    public boolean isJuzWynajete() {
        return juzWynajete;
    }

    public void setJuzWynajete(boolean juzWynajete) {
        this.juzWynajete = juzWynajete;
    }

    @Override
    public int compareTo(T o) {
        if(rozmiarPom>o.getRozmiarPom())
            return 1;
        if(rozmiarPom<o.getRozmiarPom())
            return -1;
        else
        return 0;
    }
}

