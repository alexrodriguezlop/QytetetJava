/**
 * @author Alejandro Rodríguez López
 * @email alexrodriguezlop@correo.ugr.es
 */

package modeloqytetet;


public class TituloPropiedad {
    private String nombre;
    private boolean hipotecada;
    private int alquilerBase;
    private float factorRevalorizacion;
    private int hipotecaBase;
    private int precioEdificar;
    
    private Jugador propietario = null;
    private Casilla casilla = null;

    TituloPropiedad(String nombre, int alquilerBase, float factorRevalorizacion, int hipotecaBase, int precioEdificar) {
        this.nombre = nombre;
        this.hipotecada = false;
        this.alquilerBase = alquilerBase;
        this.factorRevalorizacion = factorRevalorizacion;
        this.hipotecaBase = hipotecaBase;
        this.precioEdificar = precioEdificar;
    }

    //PAQUETE
    public String getNombre() {
        return nombre;
    }

    int getAlquilerBase() {
        return alquilerBase;
    }

    public Jugador getPropietario() {
        return propietario;
    }
    

    float getFactorRevalorizacion() {
        return factorRevalorizacion;
    }

    int getHipotecaBase() {
        return hipotecaBase;
    }

    int getPrecioEdificar() {
        return precioEdificar;
    }

    
    public Casilla getCasilla() {
        return casilla;
    }
    

    void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }

    void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }

    void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }
    
    
    /**
    * Comprueba si el título de propiedad está hipotecado.
    * @return TRUE: Hipotecado; FALSE Cualquier otro caso.
    */
    boolean isHipotecada() {
        return hipotecada;
    }
    
    
    /**
     * Comprueba si una propiedad tiene propietario.
     * @return TRUE: Propietario es diferente de null; FALSE: Cualquier otro caso.
     */
    boolean tengoPropietario(){
        return this.getPropietario() != null;
    }
    
    
    /**
     * Comprueba si el propietario del titulo de propiedad está encarcelado.
     * @return TRUE: Está encarcelado; FALSE: NO lo está.
     */
    boolean propietarioEncarcelado(){
        return this.getPropietario().getEncarcelado();
    }
    
    
    /**
     * Realiza el cobro del alquiler aumentando el saldo del jugador que recibe el cobro.
     * @param coste Coste del alquiler.
     */ 
    void cobrarAlquiler(int coste){
        //Modifica el saldo del jugador que recibe el cobro.
        this.getPropietario().modificarSaldo(-coste);
    }
  
    
 

    @Override
    public String toString() {
        return "TituloPropiedad{" + "nombre=" + nombre + ", hipotecada=" + hipotecada + ", alquilerBase=" + alquilerBase + ", factorRevalorizacion=" + factorRevalorizacion + ", hipotecaBase=" + hipotecaBase + ", precioEdificar=" + precioEdificar + '}';
    }
    

}
