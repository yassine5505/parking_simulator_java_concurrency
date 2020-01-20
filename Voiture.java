public class Voiture implements Runnable {
    String nom;
    Parking park;
    private long temps = 10000;
    public Voiture(String n, Parking park){
        this.nom=n;
        this.park=park;
    }

    @Override
    public void run(){ 
        Util.log(nom + " a démarré.");

        while(true) {
            try {
                Thread.sleep(tempsAleatoire());
                Util.log(nom + " attend à l'extérieur");

                entrer(); 

                Thread.sleep(tempsAleatoire());
                Util.log(nom + " est parkée");

                park.leave(this);
                Util.log(nom + " est sortie");

            } catch(Exception e) {
                Util.log("Exception levée par " + nom + "\n" + e.getMessage());
            } 
        }
    }
    
    public static void main(String[] args) {
        int tailleParking = 8;
        int nbVoitures = 15;
        Parking leParking = new Parking(tailleParking);
        Thread voitures[] = new Thread[nbVoitures];
        for (int i = 0; i< nbVoitures; i++){
            voitures[i]= new Thread(new Voiture(String.format("Voiture %d ", i) , leParking));
            voitures[i].start();
        }
    }

    public void entrer() {	
		try {
            while (!(this.park.accept(this))){
                // Si pas autorisée à entrer
                // interrompue pour une durée aléatoire
                Thread.sleep(tempsAleatoire());
                Util.log(nom + " tente de rentrer");
            }
        } catch(Exception e) {
            Util.log("Exception levée par " + nom + "\n" + e.getMessage());
        }
    }
    
    private long tempsAleatoire() {
        // Retourne une duree aleatoire
        return (long) (temps * Math.random());
    }
}