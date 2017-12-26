/**
 * @author Alejandro Rodríguez López
 * @email alexrodriguezlop@correo.ugr.es
 */

package modeloqytetet;

import java.util.Random;


public class Dado {
    private static final Dado instance = new Dado();
    // El constructor privado asegura que no se puede instanciar desde otras clases
    private Dado() { }
    
    /**
     * Inicializa la instancia de la clase.
     * @return la instancia de la clase.
     */
    public static Dado getInstance() {
        return instance;
    }
    
    /**
     * genera un número aleatorio del 0-5 y le suma 1.
     * @return x, que contendrá un número del 1-6.
     */
    static int tirar(){
        //int rand = ThreadLocalRandom.current().nextInt(x,y);
        
        Random rand = new Random();
        int x = rand.nextInt(6);
        return x +1;
    }
}
