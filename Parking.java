import java.util.*;
public class Parking {
    int placesOccupees;
    int capacite;
    public HashSet<Voiture> infoVoitures = new HashSet<Voiture>();

    Parking(int size){
        this.capacite = size;
    }

    public int places(){ 
        return capacite - placesOccupees;
    }

    public synchronized boolean accept(Voiture v) {
        // Accepter la voiture, si la capacite est suffisante
        if(places() > 0){
            infoVoitures.add(v);
            placesOccupees++;
            Util.log(v.nom + " est entrée. Nb Voitures: " + placesOccupees);
            return true;
        }
        return false;
    }

    public synchronized void leave(Voiture v) {
        infoVoitures.remove(v);
        --placesOccupees;
        Util.log(v.nom + " est sortie. Nb voitures: " + placesOccupees);
    }
}