/**
 * @author Alejandro Rodríguez López
 * @email alexrodriguezlop@correo.ugr.es
 */

package modeloqytetet;

import java.util.ArrayList;
import static modeloqytetet.Qytetet.MAX_CASILLAS;


public class Tablero {
    private  ArrayList<Casilla> casillas;
    private  Casilla carcel;

    /**
     * Contructor de tablero
     */
    Tablero() {
        inicializar();
    }
    
    /**
     * Devuelve la casilla designada cárcel
     * @return casilla carcel.
     */
    Casilla getCarcel() {
        return carcel;
    }

    /**
     * Inicializa el tablero.
     * Crea los títulos de propiedad y las casillas.
     */
    private void inicializar(){
       /*Inicializando el array*/
        this.casillas = new ArrayList();
        
        /*Creando Titulos de propiedad para las calles*/
        TituloPropiedad tP1 = new TituloPropiedad("SANTA CATALINA", 50, 12, 180, 250);
        TituloPropiedad tP2 = new TituloPropiedad("DEL OLIVAR", 80, 13, 200, 300);
        TituloPropiedad tP3 = new TituloPropiedad("PLAZA DE ESPAÑA", 90, 14, 800, 380);
        TituloPropiedad tP4 = new TituloPropiedad("PUERTO CRISTO", 600, 18, 850, 700);
        TituloPropiedad tP5 = new TituloPropiedad("VELLAMOSA", 55, 15, 150, 310);
        TituloPropiedad tP6 = new TituloPropiedad("PASEO DEL PRAT", 99, 14, 400, 500);
        TituloPropiedad tP7 = new TituloPropiedad("RIVERA DEL DUERO", 62, 10, 580, 260);
        TituloPropiedad tP8 = new TituloPropiedad("PLAZA DE LOS MARTIRRES", 78, 13, 200, 610);
        TituloPropiedad tP9 = new TituloPropiedad("AEROPUERTO INTERNACIONAL", 87, 20, 1000, 680);
        TituloPropiedad tP10 = new TituloPropiedad("ESTACIÓN DE FERROCARRIL", 63, 19, 800, 642);
        TituloPropiedad tP11 = new TituloPropiedad("PASEO DEL VIOLÓN", 76, 15, 700, 585);
        TituloPropiedad tP12 = new TituloPropiedad("TORRE DE LA PÓLVORA", 51, 11, 600, 292);
        
        
        
        /**Creando las Casillas del tablero**/
        this.casillas.add(new Casilla(0, 1000, TipoCasilla.SALIDA));
        
        this.casillas.add(new Casilla(1, 0, TipoCasilla.CALLE, tP1));

        this.casillas.add(new Casilla(2, 0, TipoCasilla.CALLE, tP2));        
        
        this.casillas.add(new Casilla(3, 0, TipoCasilla.SORPRESA));
        
        this.casillas.add(new Casilla(4, 0, TipoCasilla.CALLE, tP8));
        this.casillas.add(new Casilla(5, 0, TipoCasilla.CALLE, tP9));
        
        this.casillas.add(new Casilla(6, 0, TipoCasilla.JUEZ));    
        
        this.casillas.add(new Casilla(7, 0, TipoCasilla.CALLE, tP10));
        
        this.casillas.add(new Casilla(8, 0, TipoCasilla.SORPRESA));
        
        this.casillas.add(new Casilla(9, 0, TipoCasilla.CALLE, tP11));
        this.casillas.add(new Casilla(10, 0, TipoCasilla.CALLE, tP12));
        
        this.casillas.add(new Casilla(11, 0, TipoCasilla.SORPRESA));
        
        this.casillas.add(new Casilla(12, 0, TipoCasilla.CALLE, tP6));
        this.casillas.add(new Casilla(13, 0, TipoCasilla.CALLE, tP7)); 
        
        /**Apuntamos la casilla de la carcel a su casilla **/   
        this.carcel = (new Casilla(14, 0, TipoCasilla.CARCEL));
        this.casillas.add(carcel);
        
        this.casillas.add(new Casilla(15, 0, TipoCasilla.CALLE, tP5));
        
        this.casillas.add(new Casilla(16, 0, TipoCasilla.IMPUESTO));
        
        this.casillas.add(new Casilla(17, 0, TipoCasilla.CALLE, tP3));
        this.casillas.add(new Casilla(18, 0, TipoCasilla.CALLE, tP4));

        this.casillas.add(new Casilla(19, 0, TipoCasilla.PARKING));
   
        
    }
    
    
    
    /**
     * Devuelve la casilla correspondiente a un índice.
     * @param numeroCasilla índice .
     * @return casilla correspondiente al índice.
     */
    Casilla obtenerCasillaNumero(int numeroCasilla){
        return casillas.get(numeroCasilla);
    }
    
    
    /**
     * Comprueba sí la casilla correspondiente a un número es la casilla cárcel.
     * @param numeroCasilla número de casilla a comprobar.
     * @return true: la casilla se corresponde con la cárcel, false: cualquier otro caso.
     */   
    boolean esCasillaCarcel(int numeroCasilla){
        return(this.getCarcel().getNumeroCasilla() == numeroCasilla);
    }
   
    
    /**
     * Devuleve la casilla destino en un desplzamiento por el tablero.
     * @param casilla Casilla origen.
     * @param desplazamiento Número de casillas a desplazar.
     * @return casilla destino.
     */
    Casilla obtenerNuevaCasilla(Casilla casilla, int desplazamiento){
        int NumCasillaDestino;
        
        /*Recorrido circular del vector de casillas*/
        NumCasillaDestino = ((casilla.getNumeroCasilla() + desplazamiento)% MAX_CASILLAS);
        
        return this.casillas.get(NumCasillaDestino);
    }
    
    
    
    
    @Override
    public String toString() {
        String cadena;
        
        cadena = "Tablero{" + ", carcel=" + carcel.toString() + ", \n";
        for (Casilla casilla : casillas){
            cadena = cadena + "casilla=" + casilla.toString() + "\n";
        }
        
        return cadena;
    }
    
}
