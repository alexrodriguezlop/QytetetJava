/**
 * @author Alejandro Rodríguez López
 * @email alexrodriguezlop@correo.ugr.es
 */

package modeloqytetet;

public class Casilla {
    private int numeroCasilla;
    private int coste;
    private int numHoteles;
    private int numCasas;
    
    private TipoCasilla tipo;
    private TituloPropiedad titulo;

    
    Casilla(int numeroCasilla, int coste, TipoCasilla tipo, TituloPropiedad titulo) {
        this.numeroCasilla = numeroCasilla;
        this.coste = coste;
        this.numHoteles = 0;
        this.numCasas = 0;
        this.tipo = tipo;
        this.titulo = titulo;
        this.titulo.setCasilla(this);
    }

    Casilla(int numeroCasilla, int coste, TipoCasilla tipo) {
        this.numeroCasilla = numeroCasilla;
        this.coste = coste;
        this.numHoteles = 0;
        this.numCasas = 0;
        this.tipo = tipo;
        this.titulo = null;
    }

    //PAQUETE
    public int getNumeroCasilla() {
        return numeroCasilla;
    }

    int getCosteHipoteca(){
        return this.coste;   
    }

    int getNumHoteles() {
        return numHoteles;
    }

    int getNumCasas() {
        return numCasas;
    }

    public TipoCasilla getTipo() {
        return tipo;
    }
    
    
    public TituloPropiedad getTituloPropiedad() {
        return titulo;
    }
    
    
    int getPrecioEdificar(){
        return this.getTituloPropiedad().getPrecioEdificar();
    }

    void setNumHoteles(int numHoteles) {
        this.numHoteles = numHoteles;
    }

    void setNumCasas(int numCasas) {
        this.numCasas = numCasas;
    }
    
    private void setTituloPropiedad(TituloPropiedad titulo) {
        this.titulo = titulo;
    }

   
    
    /**
     * Verifica si la casilla es edificable (Edificable si es de tipo calle).
     * @return TRUE: edificable; FALSE: No edificable
     */
    boolean soyEdificable(){
        return (this.getTipo() == TipoCasilla.CALLE);
    }
    
    
    /**
     * Comprueba en el titulo de propiedad de la casilla si la casilla está hipotecada.
     * @return  TRUE: Está hipotecada, FALSE: no está hipotecada
     */
    boolean estaHipotecada(){
        return (this.getTituloPropiedad().isHipotecada());
    }
    
    
    /**
     * Verifica si la casilla pertenece a algún jugador
     * @return TRUE: La casilla tiene propietario, FALSE: La casilla NO tiene propietario.
     */
    boolean tengoPropietario(){
        return this.getTituloPropiedad().tengoPropietario();
    }

    /**
     * Calcula el coste del alquiler y solicita su cobro.
     * @return Coste del alquiler.
     */
    int cobrarAlquiler(){
        int costeAlquiler, numCasas, numHoteles;
        
        //Obtenemos el coste del alquiler base.
        int costeAlquilerBase = this.getTituloPropiedad().getAlquilerBase();
        
        //Obtenemos el  número de construcciones.
        numCasas = this.getNumCasas();
        numHoteles = this.getNumHoteles();
        
        //Calculamos el coste total del alquiler.
        costeAlquiler = (costeAlquilerBase + (int)(numCasas * 0.5 + numHoteles * 2));
        
        this.getTituloPropiedad().cobrarAlquiler(costeAlquiler);
        
        return costeAlquiler;
    }
    
    
    
    
    /**
     * Asigna un propietario al titulo de propiedad de una casilla.
     * @param jugador Propietario a asignar.
     * @return titulo Titulo de propiedad de la casilla.
     */
    TituloPropiedad asignarPropietario(Jugador jugador){
        TituloPropiedad titulo = this.getTituloPropiedad();
        
        titulo.setPropietario(jugador);
        
        return titulo;
    }
    
    
    /**
     * Verifica si es posible construir una casa (El número de casas tiene que ser como máximo de 4).
     * @return TRUE: Se puede cosntruir una casa; FALSE: NO se puede construir una casa.
     */
    boolean sePuedeEdificarCasa(){
        boolean sePuedeEdificar = false;
        
        if(this.getNumCasas()<4)
            sePuedeEdificar = true;
        
        return sePuedeEdificar;
    }

    
    /**
    * Verifica si es posible construir un hotel (El número de hoteles tiene que ser como máximo de 4).
    * @return TRUE: Se puede cosntruir un hotel; FALSE: NO se puede construir un hotel.
    */
    boolean sePuedeEdificarHotel(){
        boolean sePuedeEdificar = false;
        
        if(this.getNumHoteles()<4)
            sePuedeEdificar = true;
        
        return sePuedeEdificar;   
    }
    
    /**
     * Asigna un titulo de propiedad a una casilla (esta perderia el que ya tenia).
     */
    private void asignarTituloPropiedad(){
        this.setTituloPropiedad(titulo);
    }
    
    /**
     * Incrementa en uno el número de casa costruidas.
     * @return Coste de edificar casa.
     */
    int edificarCasa(){
        //Aumentamos en uno el número de casas.
        this.setNumCasas(this.getNumCasas() +1);
        
        //Obtenemos el coste.
        int costeEdificarCasa = this.getTituloPropiedad().getPrecioEdificar();
        
        return costeEdificarCasa;
    }
        
    
    
    /**
     * Incrementa en uno el número de hoteles costruidos.
     * @return Coste de edificar hotel.
     */
    int edificarHotel(){
        //Aumentamos en uno el número de hoteles.
        this.setNumHoteles(this.getNumHoteles() +1);
        
        //Obtenemos el coste.
        int costeEdificarHotel = this.getTituloPropiedad().getPrecioEdificar();
        
        return costeEdificarHotel;
    }
    
    /**
     * Calcula el valor de la hipoteca.
     * @return Variable de tipo entero con el valor calculado.
     */
    int calcularValorHipoteca(){
        int numCasas, numHoteles, hipotecaBase, valorHipoteca;
        
        numCasas = this.getNumCasas();
        numHoteles = this.getNumHoteles();
        hipotecaBase = this.getTituloPropiedad().getHipotecaBase();
        
        valorHipoteca = (hipotecaBase + (int)((numCasas * 0.5 * hipotecaBase) + (numHoteles * hipotecaBase)));
        
        return valorHipoteca;
    }
    
    
    /**
     * Establece la propiedad como hipotecada.
     * @return Variable de tipo entero con el valor de la hipoteca.
     */
    int hipotecar(){
        //La propiedad pasa a estar hipotecada.
        this.getTituloPropiedad().setHipotecada(true);
        
        //Se devuelve el valor de la hipoteca.
        return this.calcularValorHipoteca();
    }
    
    
    /**
     * Vende una propiedad.
     * @return devuelve el precio de venta.
     */
    int venderTitulo(){
        //obtenemos el número de edificaciones.
        int numEdificaciones = this.getNumCasas() + this.getNumHoteles();
        
        TituloPropiedad titulo = this.getTituloPropiedad();
        
        //Se calcula el precio de venta.      
        int precioCompra = this.getCosteHipoteca()+ (numEdificaciones * titulo.getPrecioEdificar());
        int precioVenta =  precioCompra + (int)(titulo.getFactorRevalorizacion() * precioCompra);
        
        //Anulamos el propietario de la propiedad.
        this.getTituloPropiedad().setPropietario(null);
        
        //Establecemos a 0 el número de edificaciones.
        this.setNumCasas(0);
        this.setNumHoteles(0);
        
        return precioVenta;
    }
    
    /**
     * Comprueba si el propietario de una casilla está encarcelado.
     * @return TRUE: Propietario encarcelado; FALSE: Propietario NO encarcelado.
     */
    boolean propietarioEncarcelado(){
        return this.getTituloPropiedad().propietarioEncarcelado();
    }    
 

    /**
     * Realiza la cancela de la hipoteca.
     * @return Coste de cancelación.
     */
    int cancelarHipoteca(){
        //La propiedad deja de estar hipotecada.
        this.getTituloPropiedad().setHipotecada(false);
        
        //Se devuelve el precio de la cancelación (valorHipoteca + 10%).
        int valorHipoteca = this.calcularValorHipoteca();
        
        int precioCancelarHipoteca =  (valorHipoteca + (int)(valorHipoteca * 0.10));
        
        return precioCancelarHipoteca;
    }
    
    @Override
    public String toString() {
        if(tipo == TipoCasilla.CALLE)
            return "Casilla{" + "numeroCasilla=" + numeroCasilla + ", coste=" + coste + ", numHoteles=" + numHoteles + ", numCasas=" + numCasas + ", tipo=" + tipo + ", \n titulo=" + titulo.toString() + '}';
        else
            return "Casilla{" + "numeroCasilla=" + numeroCasilla + ", coste=" + coste + ", numHoteles=" + numHoteles + ", numCasas=" + numCasas + ", tipo=" + tipo + '}';         
    }
//int precioTotalComprar(){}

}
