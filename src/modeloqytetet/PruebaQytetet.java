/**
 * @author Alejandro Rodríguez López
 * @email alexrodriguezlop@correo.ugr.es
 */

package modeloqytetet;

import java.util.ArrayList;

public class PruebaQytetet {
    /*Mazo de cartas tipo sorpresa*/
    private static ArrayList<Sorpresa> mazo = new ArrayList();
    private static Tablero tablero;
    
    /**
     *Genera el tablero para comenzar el juego creando el mazo Sorpresas
     */
    /*private static void inicializarSorpresas(){
       
        mazo.add(new Sorpresa("La grúa se ha llevado tú choche y además te han multado!", -350, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa("Te encontraste un cheque al portador!", 800, TipoSorpresa.PAGARCOBRAR));
        
        mazo.add(new Sorpresa("Una ráfaga de aire te arrastra a la casilla 8!", 8, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Unos matones te persiguen y terminas en la casilla 3!", 3, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("La policía te ha confundido con un criminal, terminas en la cárcel!", tablero.getCarcel().getNumeroCasilla(), TipoSorpresa.IRACASILLA));
        
        mazo.add(new Sorpresa("Derrama en la comunidad, tendrás que aflojar la mosca!", -250, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa("Recibes la indemnización de aquel recalo que tuviste hace dos años!", 180, TipoSorpresa.PORCASAHOTEL));
        
        mazo.add(new Sorpresa("Has perdido la apuesta, ve soltando la guita!", -50, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa("Has ganado la apuesta! té deben pasta!", 50, TipoSorpresa.PORJUGADOR));
        
        mazo.add(new Sorpresa("Tú abogado se ha ganado el jornal! Quedas en libertad!", 0, TipoSorpresa.SALIRCARCEL));
    }
    
    /**Selecciona las sorpresas con valor mayor a cero.
     * @return Arraylist sorpresas.
     */
    private static ArrayList<Sorpresa> getMayorCero(){
        ArrayList<Sorpresa> sorpresas = new ArrayList();
        
        for (Sorpresa sorpresa : mazo) {
            if(sorpresa.getValor() > 0)
                sorpresas.add(sorpresa);   
        }
        
        return sorpresas;        
    }
    
    
     /**Selecciona las sorpresas con tipo IRACASILLA.
     * @return Arraylist sorpresas.
     */
    private static ArrayList<Sorpresa> getTipoIracasillas(){
        ArrayList<Sorpresa> sorpresas = new ArrayList();
        
        for (Sorpresa sorpresa : mazo) {
            if(sorpresa.getTipo()== TipoSorpresa.IRACASILLA)
                sorpresas.add(sorpresa);   
        }
        
        return sorpresas;        
    }

    
     /**Selecciona las sorpresas del tipo proporcionado como parámetro.
     * @param Tipo, tipo de sorpresa. 
     * @return Arraylist sorpresas.
     */
    private static ArrayList<Sorpresa> getTipo(TipoSorpresa tS){
        ArrayList<Sorpresa> sorpresas = new ArrayList();
        
        for (Sorpresa sorpresa : mazo) {
            if(sorpresa.getTipo() == tS)
                sorpresas.add(sorpresa);   
        }
        
        return sorpresas;        
    }
    
    
    /**
     * @param args the command line arguments
     */
    //public static void main(String[] args) {
    /*
        // TODO code application logic here
        /**Inicializamos el mazo de sorpresas**/
        //inicializarSorpresas();
        
        
        /**Inicializamos el tablero
        Tablero.inicializar();
        
        System.out.println("\n Consultar todas \n");       
        
        for (Sorpresa sorpresa : mazo) {
            System.out.println(sorpresa.toString());
        }
        
        System.out.println("\n Consultar mayor a 0 \n");
        
        ArrayList<Sorpresa> tmp = getMayorCero();
        for (Sorpresa sorpresa : tmp) {
            System.out.println(sorpresa.toString());
        }
        
        System.out.println("\n consultar Tipo IRACASILLAS \n");
        
        tmp = getTipoIracasillas();
        for (Sorpresa sorpresa : tmp) {
            System.out.println(sorpresa.toString());
        }
        
        System.out.println("\n Consultar tipo \n");
        
        tmp = getTipo(TipoSorpresa.PAGARCOBRAR);
        for (Sorpresa sorpresa : tmp) {
            System.out.println(sorpresa.toString());
      }
    */
    /* 
        /*********Prueba**********
    Qytetet partida = Qytetet.getInstance();
    
    ArrayList<String> nombres = new ArrayList(4);
    nombres.add("Jugador1");
    nombres.add("Jugador2");
    nombres.add("Jugador3");
    nombres.add("Jugador4");
    partida.inicializarJuego(nombres);
    
    System.out.print(partida.toString());
        /*
    /*******************************************************************
     * Cuidado, los elementos no inicializados estan igualados a null. 
     * En el toString, añadir el método toString() al objeto.
    ********************************************************************/
   // }
    
}
