/**
 * @author Alejandro Rodríguez López
 * @email alexrodriguezlop@correo.ugr.es
 */

package modeloqytetet;

import java.util.ArrayList;


public class Jugador {
    private boolean encarcelado;
    private String nombre;
    private int saldo = 7500;
    
    private Casilla casillaActual;
    private Sorpresa cartaLibertad = null;
    private ArrayList<TituloPropiedad> propiedades;

    public Jugador(String nombre) {
        this.encarcelado = false;
        this.nombre = nombre;
        this.casillaActual = null;
        this.propiedades = new ArrayList();
    }
    
    public Jugador(String nombre, Casilla salida) {
        this.encarcelado = false;
        this.nombre = nombre;
        this.casillaActual = salida;
        this.propiedades = new ArrayList();
    }
     
    
    public Casilla getCasillaActual(){
        return this.casillaActual;
    }
    
    public boolean getEncarcelado(){
        return this.encarcelado;
    }

    public String getNombre() {
        return nombre;
    }
    
    void setCartaLibertad(Sorpresa cartaActual){
        this.cartaLibertad = cartaActual;
    }
    
    void setCasillaActual(Casilla casilla){
        this.casillaActual = casilla;
    }

    void setEncarcelado(boolean encarcelado) {
        this.encarcelado = encarcelado;
    }
      
    
    /**
     * Devuelve el número de propiedades que posee el jugador.
     * @return un entero con el número de propiedades.
     */
    private int cuantasCasasHotelesTengo(){
        return this.propiedades.size();
    }
    
    
    /**
     * Devuelve la carta libertad y se le anula al jugador.
     * @return la carta libertad.
     */
    Sorpresa devolverCartaLibertad(){
        //Apuntamos a la carta libertad
        Sorpresa cartaLibertadDevuelta = this.cartaLibertad;
        
        //Se anula la carta en el jugador
        this.cartaLibertad = null;
        
        return cartaLibertadDevuelta;
    }
    
    
    /**
     * Devuelve el capital del que dispone el jugador.
     * @return Variable entera con el capital total.
     */
    int obtenerCapital(){
        int capital, valorPropiedades = 0, numEdificaciones = 0;
        
        //Calculamos el valor de todas las propiedades del jugador.
        for(TituloPropiedad propiedad: this.propiedades){
            
            //Obtenemos el número de edificacionesque tiene el titulo de propiedad. cada hotel equivale a cuatro casas mas coste edificación.
            numEdificaciones = propiedad.getCasilla().getNumCasas() + propiedad.getCasilla().getNumHoteles();
            
            //Valor total de la propiedad
            valorPropiedades = valorPropiedades + propiedad.getAlquilerBase() +(numEdificaciones * propiedad.getPrecioEdificar());
            
            if(propiedad.isHipotecada())
                valorPropiedades = valorPropiedades - propiedad.getHipotecaBase();
        }
        
        capital = valorPropiedades + this.saldo;
        
        return capital;
    }
    
    
    /**
     * Comprueba si el jugador dispone de la carta libertad.
     * @return TRUE: Si el atributo carta libertad no es nulo; FALSE: Cualquier otro caso.
     */
    boolean tengoCartaLibertad(){
        return this.cartaLibertad != null;
    }
    
    
    /**
     * Comprueba si una casilla es de la propiedad del jugador.
     * @param casilla Casilla a verificar.
     * @return TRUE: Si la casilla se encuentra entre los titulos; FALSE: La casilla NO se encuentra entre los titulos de propiedad.
     */
    private boolean esDeMipropiedad(Casilla casilla){
        boolean encontrado = false;
        
            for(TituloPropiedad propiedad: this.propiedades){
                if(propiedad.getCasilla() == casilla){
                    encontrado = true;
                    break;
                }
            }
        return encontrado;        
    }
    
    
    /**
     * Verfica que una casilla es de la propiedad del jugador y no está hipotecada.
     * @param casilla Casilla a verfificar
     * @return TRUE: La casilla se puede vender; FALSE: La casilla NO se puede vender
     */
    boolean puedoVenderPropiedad(Casilla casilla){
        return(esDeMipropiedad(casilla) && !casilla.estaHipotecada());
    }
    
    
    /**
     * Borra el titulo de propiedad referente a una casilla del array de propiedades del jugador
     * @param casilla Casilla a borrar.
     */
    private void eliminarDeMisPropiedades(Casilla casilla){
        if(esDeMipropiedad(casilla)){
            propiedades.remove(casilla.getTituloPropiedad());
        }
           
    }
      
    
    /**
     * Modifica el saldo del jugador.
     * @param cantidad Cantidad a sumar o restar del saldo del jugador(Valores negativos se le restaran, positivos se le sumaran).
     */        
    void modificarSaldo(int cantidad){
        this.saldo = this.saldo + cantidad;
    }
    
    
    /**
    * Devuelve las propiedades del jugadorActual que estén hipotecadas o que no estén hipotecadas.
    * @param hipotecada TRUE: Devuelve las propiedades hipotecadas; FALSE: Devuelve las propiedades NO hipotecadas.
    * @return TRUE: Array con los titulos de propiedad del jugadorActual que estén hipotecadas, FALSE: Con los que no lo esten.
    */
    ArrayList<TituloPropiedad> obtenerPropiedadesHipotecadas(boolean hipotecada){
     
        ArrayList<TituloPropiedad> propiedadesJugador = new ArrayList();      
         
        if(hipotecada){
            for(TituloPropiedad titulo : this.propiedades )
                if(titulo.isHipotecada())
                    propiedadesJugador.add(titulo);          
        }
        else{
            for(TituloPropiedad titulo : this.propiedades)
                if(!titulo.isHipotecada())
                    propiedadesJugador.add(titulo);  
        }
        
        return propiedadesJugador;
    }
    
    
    /**
     * Cmprueba si el jugador tiene alguna propiedad. 
     * @return TRUE: El jugador tiene al menos una propiedad; FALSE: El jugador no tiene ninguna propiedad.
     */
    public boolean tengoPropiedades(){
        return this.propiedades.size() > 0;
    }
    
    
    /**
     * Comprueba si el jugador dispone de la cantidad pasada como parámetro.
     * @param cantidad Cantidad de saldo a verificar.
     * @return TRUE: El jugador dispone de esa cantidad de saldo; FALSE: El jugador no dispone de esa catidad de saldo
     */
    public boolean tengoSaldo(int cantidad){
        return this.saldo >= cantidad;
    }//Private
    
    
    
    /**
     * Desplaza la posición de un jugador en el tablero.
     * @param casilla Casilla destino del desplazamiento.
     * @return TRUE: La casilla destino tiene propietario; FALSE: La casilla destino no tiene propietario.  
     */
    boolean actualizarPosicion(Casilla casilla){
        //Pasando por la casilla de salida.
        if(casilla.getNumeroCasilla() < casillaActual.getNumeroCasilla())
            modificarSaldo(Qytetet.SALDO_SALIDA);
        
        boolean tienePropietario = false;
                
        //Actualizamos la casilla actual.
        setCasillaActual(casilla);
        
        //Si la casilla es edificable.
        if(casilla.soyEdificable()){
             tienePropietario =  casilla.tengoPropietario();
             
            //Si la casilla tiene propietario.
            if(tienePropietario){
             
                boolean propietarioEncarcelado = casilla.propietarioEncarcelado();
                
                //Comprobamos si el propietario NO está encarcelado.
                if(!propietarioEncarcelado){
                    //Ingresa el coste del alquiler al propietario.
                    int costeAlquiler = casilla.cobrarAlquiler();
                    //Resta el coste del alquiler al jugador que paga.
                    this.modificarSaldo(-costeAlquiler);
                }
            }
        }
        else{//no es edificable
            //Si es impuesto
            if(casilla.getTipo() == TipoCasilla.IMPUESTO){
                //Calculamos es coste del impuesto.
                int costeImpuesto = casilla.getCosteHipoteca();
                //Resta el coste del impuesto al jugador.
                this.modificarSaldo(-costeImpuesto);
            }
        }
        return tienePropietario;
    }
    
    
    
    /**
     * Realiza la compra de un título.
     * @return TRUE: Se pudo llevar a cabo la compra del título, FALSE: NO pudo realizarse la compra del título.
     */
    boolean comprarTitulo(){
        boolean puedoComprar = false;
        
        //obtenemos la casilla actual para facilitar la comprensión del método.
        Casilla casilla = this.getCasillaActual();
        
        //Comprobar si la casilla es edificable.
        if(casilla.soyEdificable()){
            boolean tengoPropietario = casilla.getTituloPropiedad().tengoPropietario();
            
            //Comprobar que no tenga propietario.
            if(!tengoPropietario){  
                //Calcular su coste.
                int costeCompra = casilla.getCosteHipoteca();
                
                //Comprobar si el jugador dispone de saldo para comprarla.
                if(costeCompra <= this.saldo){
                    //El jugador pasa a ser propietario del titulo de propiedad.
                    TituloPropiedad titulo = casilla.asignarPropietario(this);
                    this.propiedades.add(titulo);
                    
                    //Se cobra el coste de la compra al jugador.
                    this.modificarSaldo(-costeCompra);
                    
                    //Compra satisfactoria.
                    puedoComprar = true;
                }
            }
        }
        return puedoComprar;    
    }
    
    
    /**
     * Verifica que el jugador pueda construir en una casilla. (Sea propietario y tenga saldo).
     * @param casilla Casilla donde se desea construir.
     * @return TRUE: Se puede construir; FALSE: NO se puede construir.
     */
    boolean puedoEdificarCasa(Casilla casilla){
        boolean puedoEdificar = false;
        
        //Se comprueba si el jugador es propietario de la casilla.
        boolean esMia = this.esDeMipropiedad(casilla);
        
        if(esMia){
            //Se obtiene el precio de edificación.
            int costeEdificarCasa = casilla.getPrecioEdificar();
            
            //Se comprueba que el jugador tiene saldo para llevar a cabo la edificación.
            boolean tengoSaldo = this.tengoSaldo(costeEdificarCasa);
            
            if(tengoSaldo){
                puedoEdificar = true;
            }   
        }
        return puedoEdificar;    
    }
    
    
    
    
    boolean puedoEdificarHotel(Casilla casilla){
        boolean puedoEdificar = false;
        
        //Se comprueba si el jugador es propietario de la casilla.
        boolean esMia = this.esDeMipropiedad(casilla);
        
        if(esMia){
            //Se obtiene el precio de edificación.
            int costeEdificarHotel = casilla.getPrecioEdificar();
            
            //Se comprueba que el jugador tiene saldo para llevar a cabo la edificación.
            boolean tengoSaldo = this.tengoSaldo(costeEdificarHotel);
            
            int numCasas = casilla.getNumCasas();
            //Se comprueba si el judador tiene al menos 4 casas costruidas.
            if(tengoSaldo && numCasas >= 4){
                puedoEdificar = true;
            }   
        }
        return puedoEdificar;   
    }
             
             
             
             
    /**
     * Comprueba si el jugador puede hipotecar una casilla.
     * @param casilla Casilla a comprobar.
     * @return TRUE: Puede hipotecar; FALSE: No puede hipotecar.
     */
    boolean puedoHipotecar(Casilla casilla){
        //Para poder hipotecar la casilla tiene que ser propiedad del jugador.
        return this.esDeMipropiedad(casilla);
    }
    
    
    /**
     * Vende una casilla pasada como parámetro (Jugador pierde la propiedad y aumenta su saldo en el valor de esta).
     * @param casilla Casilla a vender.
     */
    void venderPropiedad(Casilla casilla){
        int precioVenta = casilla.venderTitulo();
        
        this.modificarSaldo(precioVenta);
        
        this.eliminarDeMisPropiedades(casilla);
    }
    
    
    
    /**
     * Manda a la cárcel al jugador.
     * @param carcel Casilla cárcel.
     */
    void irACarcel(Casilla carcel){
        this.setCasillaActual(carcel);
        this.setEncarcelado(true);
    }
    
    
    /**
     * Cobra o paga el jugador en función de su número de propiedades.
     * @param cantidad Cantidad a pagar o cobrar.
     */
    void pagarCobrarPorCasaYHotel(int cantidad){
        int numEdificaciones = this.cuantasCasasHotelesTengo();
        
        this.modificarSaldo(numEdificaciones * cantidad);
    }
    
    /**
     * Comprueba si el jugador puede pagar su libertad, en caso afirmativo la paga.
     * @param PrecioLibertad Precio a pagar.
     * @return TRUE: El jugador puede pagar su libertad; FALSE: El jugador NO puede pagar su libertad.
     */
    boolean pagarLibertad(int PrecioLibertad){
        boolean tengoSaldo = this.tengoSaldo(PrecioLibertad);
        
        if(tengoSaldo){
            this.modificarSaldo(-PrecioLibertad);
        }
        
        return tengoSaldo;
    }
    

    /**
     * Comprueba que el jugador sea propietario y pueda pagar la cancelación
     * @param casilla Casilla hipotecada.
     * @return TRUE: Puede cancelar la hipoteca; FALSE: No cumple los requisitos para cancelarla.
     */
    boolean puedoPagarHipoteca(Casilla casilla){
        int valorHipoteca = casilla.calcularValorHipoteca();
        int costeCancelacion = (valorHipoteca + (int)(valorHipoteca * 0.10));
        
        return (this.tengoSaldo(costeCancelacion) && this.esDeMipropiedad(casilla));
    }

    

    @Override
    public String toString() {
        String cadena;
        
        cadena = "Jugador{" + "encarcelado=" + encarcelado + ", nombre=" + nombre + ", saldo=" + saldo + ", casillaActual=" + casillaActual.toString() + ", cartaLibertad=" + cartaLibertad + '\n';
        for(TituloPropiedad titulo : propiedades)
            cadena = cadena + "propiedades=" + titulo.toString() + '\n';
        
        return cadena;
    }
    
    
    
    
    //*********************
    /**
     * Obtiene una lista de nombres de propiedades del jugador (USO DESDE CONTROLADOR).
     * @return Lista de nombres de propiedades.
     */
    public ArrayList<String> getNombrePropiedades(){
        ArrayList<String> listaPropiedades = new ArrayList();
        
        for(TituloPropiedad propiedad :propiedades){
            listaPropiedades.add(propiedad.getNombre());
        }
        return listaPropiedades;
    }

    
    /**
     * Obtiene la casilla de una propiedad(USO DESDE CONTROLADOR).
     * @param indicePropiedad indice de tituloPropiedad.
     * @return Casilla correspondiente al titulo del índice proporcionado.
     */
    public Casilla getMiCasilla(int indicePropiedad){
       TituloPropiedad titulo = this.propiedades.get(indicePropiedad);
       Casilla casilla = titulo.getCasilla();
      
        return casilla;
    }
    
    
    
}
