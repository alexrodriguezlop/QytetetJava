/**
 * @author Alejandro Rodríguez López
 * @email alexrodriguezlop@correo.ugr.es
 */

package modeloqytetet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


public class Qytetet {
    /*Instancia*/
    private static final Qytetet instance = new Qytetet();

    /**Atributos**/
    public final static int MAX_JUGADORES = 4;
           final static int MAX_CARTAS = 10;
           final static int MAX_CASILLAS = 20;
           final static int PRECIO_LIBERTAD = 200;
           final static int SALDO_SALIDA = 7500;
    
    //**Atributos de referencia**//
    private  Sorpresa cartaActual = null;
    private  Jugador jugadorActual = null;
    private  Tablero tablero;
    private  Dado dado = Dado.getInstance();
    
    private  ArrayList<Jugador> jugadores;
    private  ArrayList<Sorpresa> mazo;

    /**
     * Constructor por defecto.
     */
    private Qytetet() {}

   
    //GET'S
    public static Qytetet getInstance() {
        return instance;
    }

    public Sorpresa getCartaActual(){
        return this.cartaActual;
    } 
    
    public Jugador getJugadorActual(){
        return this.jugadorActual;
    }
 
    //SET'S
    public void setCartaActual(Sorpresa cartaActual) {
        this.cartaActual = cartaActual;
    }

    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }
    
    
    
    /**
     * Inicializa la partida inicializando cartas sorpresa, jugadores y tablero.
     * @param nombres vector de nombres de los jugadores.
     */
    public void inicializarJuego(ArrayList<String> nombres){
        //Importante hacer las inicializaciones en orden.
        inicializarJugadores(nombres); 
        inicializarCartasSorpresa();  
        inicializarTablero();
        salidaJugadores();
    }

    
    /**
     * Inicializa el mazo de cartas sorpresa, añade todas las cartas a mazo y lo baraja.
     */  
    private void inicializarCartasSorpresa(){
        mazo = new ArrayList();
        
        mazo.add(new Sorpresa("La grúa se ha llevado tú choche y además te han multado!", -350, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa("Te encontraste un cheque al portador!", 800, TipoSorpresa.PAGARCOBRAR));
        
        mazo.add(new Sorpresa("Una ráfaga de aire te arrastra a la casilla 8!", 8, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Unos matones te persiguen y terminas en la casilla 3!", 3, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("La policía te ha confundido con un criminal, terminas en la cárcel!", 14, TipoSorpresa.IRACASILLA));
        
        mazo.add(new Sorpresa("Derrama en la comunidad, tendrás que aflojar la mosca!", -250, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa("Recibes la indemnización de aquel recalo que tuviste hace dos años!", 180, TipoSorpresa.PORCASAHOTEL));
        
        mazo.add(new Sorpresa("Has perdido la apuesta, ve soltando la guita!", -50, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa("Has ganado la apuesta! té deben pasta!", 50, TipoSorpresa.PORJUGADOR));
        
        mazo.add(new Sorpresa("Tú abogado se ha ganado el jornal! Quedas en libertad!", 0, TipoSorpresa.SALIRCARCEL));
        
        Collections.shuffle(mazo);
    }
    
    
    
    /**
     * Inicializa los jugadores creando un jugador por cada nombre del vector.
     * @param nombres Vector que contiene los nombres de los jugadores.
     */
    private void inicializarJugadores(ArrayList<String> nombres){
        this.jugadores = new ArrayList();
        
        for(String nombre : nombres)
            this.jugadores.add(new Jugador(nombre));   
    }
    

    /**
     * Inicializa el tablero.
     * Crea un objeto tablero.
     */
    private void inicializarTablero(){
        this.tablero = new Tablero();
    }
    
    
    /**
     * Actualiza el atributo jugadorActual al siguiente jugador.
     */
    public void siguienteJugador(){
        int indiceJugadorActual, indiceJugadorSiguiente;
        
        indiceJugadorActual = this.jugadores.indexOf(this.jugadorActual);

        indiceJugadorSiguiente = ((indiceJugadorActual +1)% this.jugadores.size());
        
        this.jugadorActual = this.jugadores.get(indiceJugadorSiguiente);

    }
    
   
   /**
    * Posiciona a todos los jugadores en la casilla de salida, con un saldo establecido en SALDO_SALIDA. 
    * y asigna de forma aleatoria el jugador actual.
    */
    private void salidaJugadores(){
        //Asignamos la casilla de salida y un saldo inicial de 7500€
        for(Jugador jugador : this.jugadores){
           jugador.modificarSaldo(SALDO_SALIDA);
           jugador.setCasillaActual(this.tablero.obtenerCasillaNumero(0));
        }
        
        //Generamos un íncide aleatorio para determinar que jugador comienza.
        Random rand = new Random();
        //La función devuelve un entero entre 0 y el parámetro.
        int primeroEnJugar = rand.nextInt(this.jugadores.size()-1);
        
        //Establecemos el jugador actual por primera vez.
        this.jugadorActual = this.jugadores.get(primeroEnJugar);   
    }  
    
    /**
     * Devuelve las casillas propiedad del jugadorActual que estén hipotecadas o que no estén hipotecadas.
     * @param hipotecadas TRUE: Devuelve las propiedades hipotecadas; FALSE: Devuelve las propiedades NO hipotecadas.
     * @return TRUE: Array con las casillas propiedad del jugadorActual que estén hipotecadas, FALSE: Con las que no lo esten.
     */
    public ArrayList<Casilla> propiedadesHipotecadasJugador(boolean hipotecadas){
        
        ArrayList<Casilla> listaCasillas = new ArrayList();
        ArrayList<TituloPropiedad> titulosJugadorActual = this.jugadorActual.obtenerPropiedadesHipotecadas(hipotecadas);
        
        for(TituloPropiedad titulo : titulosJugadorActual ){
            listaCasillas.add(titulo.getCasilla());             
        }
        
        return listaCasillas;
    }
    
    
    /**
     * Devuelve la lista de jugadores.
     * @return Array con los jugadores.
     */
    public ArrayList<Jugador> getJugadores(){         
         return this.jugadores;
    }
    
    
    /**
     * Verifica si el jugador puede comprar el título, si es posible lo compra.
     * @return boolean TRUE: Se pudo comprar el título; FALSE: NO se pudo comprar el título.
     */
    public boolean comprarTituloPropiedad(){
        return this.getJugadorActual().comprarTitulo();
    }
    
    
    /**
     * Edifica una nueva casa en una casilla.
     * @param casilla Casilla donde deseamos edificar.
     * @return TRUE: Se puede edificar, tanto jugador como casilla cumplen los requisitos; FALSE: NO se puede edificar.
     */
    public boolean edificarCasa(Casilla casilla){
        boolean puedoEdificar = false;
        
        //Si la casilla es edificable.
        if(casilla.soyEdificable()){
            boolean sePuedeEdificar = casilla.sePuedeEdificarCasa();
            
            //Si se puede edificar una casa.
            if(sePuedeEdificar){
                puedoEdificar = this.getJugadorActual().puedoEdificarCasa(casilla);
                
                //El jugador puede edificar una casa.
                if(puedoEdificar){
                    int costeEdificarCasa = casilla.edificarCasa();
                    
                    //Descontamos el coste de la edificación al jugador.
                    this.getJugadorActual().modificarSaldo(-costeEdificarCasa);
                }
            }
        }
        return puedoEdificar;
    }
    
    
    /**
    * Edifica un nuevo hotel en una casilla.
    * @param casilla Casilla donde deseamos edificar.
    * @return TRUE: Se puede edificar, tanto jugador como casilla cumplen los requisitos; FALSE: NO se puede edificar.
    */
    public boolean edificarHotel(Casilla casilla){
        boolean puedoEdificar = false;
        
        //Si la casilla es edificable.
        if(casilla.soyEdificable()){
            boolean sePuedeEdificar = casilla.sePuedeEdificarHotel();
            
            //Si se puede edificar un hotel.
            if(sePuedeEdificar){
                puedoEdificar = this.getJugadorActual().puedoEdificarHotel(casilla);
                
                //El jugador puede edificar un hotel.
                if(puedoEdificar){
                    int costeEdificarHotel = casilla.edificarHotel();
                    
                    //Descontamos el coste de la edificación al jugador.
                    this.getJugadorActual().modificarSaldo(-costeEdificarHotel);
                }
            }
        }
        return puedoEdificar;
    }

    
    
    /**
     * Hipoteca una casilla.
     * @param casilla Casilla a hipotecar.
     * @return TRUE: El jugador ha podido hipotecar la casilla; FALSE: Cualquier otro caso.
     */
    public boolean hipotecarPropiedad(Casilla casilla){
        boolean puedoHipotecarPropiedad = false;
        
        //Si casilla es edificable y no está hipotecada.
        boolean sePuedeHipotecar = casilla.soyEdificable() && !casilla.estaHipotecada();
        
        if(sePuedeHipotecar){
            //Comprueba que el jugador pueda hipotecar la casilla.
            puedoHipotecarPropiedad = this.getJugadorActual().puedoPagarHipoteca(casilla);
            
            if(puedoHipotecarPropiedad){
                //Cantidad que cobra el jugador por hipotecar.
                int cantidadRecibida = casilla.hipotecar();
                
                //Se añade la cantidad recivida al saldo del jugador.
                this.getJugadorActual().modificarSaldo(cantidadRecibida);
            }
        }
        return  puedoHipotecarPropiedad;
    }
    
    
    /**
     * Vende una casilla propiedad de un jugador.
     * @param casilla Casilla a vender.
     * @return TRUE: Se ha podido vender; FALSE: Cualquier otro caso.
     */
    public boolean venderPropiedad(Casilla casilla){
        boolean puedoVender = false;
        
        Jugador jugador = this.getJugadorActual();
        
        if(casilla.soyEdificable()){
            puedoVender = jugador.puedoVenderPropiedad(casilla);
            
            if(puedoVender){
                jugador.venderPropiedad(casilla);
            }
        }
        return puedoVender;
    }
    
    
    /**
     * Aplica las cartas del tipo sorpresa.
     * @return TRUE:Se desplaza a una casilla con propietario; FALSE: Cualquier otro caso.
     */
    public boolean aplicarSorpresa(){
        boolean tienePropietario = false;
        
        //Para facilitar la comprension del método.
        TipoSorpresa tipoSorpresa = this.getCartaActual().getTipo();
        Jugador jugadorActual = this.getJugadorActual();
        Sorpresa carta = this.getCartaActual();
        
        switch (tipoSorpresa) {
            case PAGARCOBRAR:
                jugadorActual.modificarSaldo(carta.getValor());
            break;
                
            case IRACASILLA:
                boolean esCarcel = this.tablero.esCasillaCarcel(carta.getValor());
                
                if(esCarcel){
                    this.encarcelarJugador();
                }
                else{
                    Casilla nuevaCasilla = this.tablero.obtenerCasillaNumero(carta.getValor());
                    tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);
                }
            break;
                
            case PORCASAHOTEL:
                jugadorActual.pagarCobrarPorCasaYHotel(carta.getValor());
            break;
                
            case PORJUGADOR:
                for(Jugador jugador : this.jugadores){
                    if(jugador != jugadorActual ){
                        //Se incrementa el saldo a los jugadores.
                        jugador.modificarSaldo(carta.getValor());
                        
                        //Se decrementa el saldo al jugador actual.
                        jugadorActual.modificarSaldo(-carta.getValor()); 
                    }
                }
            break;
                
            case SALIRCARCEL:

                //Se asigna al jugador la carta libertad.
                jugadorActual.setCartaLibertad(carta);
                
                //La carta se elimina.
                this.mazo.remove(carta);
                
                //Se añade al final del mazo.
                //this.mazo.add(carta);
            break;
        }
        return tienePropietario;
    }
    
    
    /**
     * Manda al jugador a la cárcel si no tiene carta libertad.
     */
    private void encarcelarJugador(){
        Jugador jugador = this.getJugadorActual();
        
        if(!jugador.tengoCartaLibertad()){
            Casilla casillaCarcel = this.tablero.getCarcel();
            jugador.irACarcel(casillaCarcel);
        }
        else{
            Sorpresa carta = jugador.devolverCartaLibertad();
            //Añadiendo la carta al final del mazo.
            this.mazo.remove(carta);
            this.mazo.add(carta);
        }
    }
    
    
    /**
     * Comprueba si el jugador puede salir de la cárcel, en caso afirmativo lo deja libre.
     * @param metodo Método para intentar salir.
     * @return TRUE: Consigue salir de la cárcel; FALSE: No consigue salir.
     */
    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){
        boolean libre = false;
        
        if(metodo == MetodoSalirCarcel.TIRANDODADO){
            int valorDado = Dado.tirar();
            if(valorDado > 5){
                libre = true;
            }
        }
        else{
            if(metodo == MetodoSalirCarcel.PAGANDOLIBERTAD){
                libre = this.getJugadorActual().pagarLibertad(PRECIO_LIBERTAD);
            }
        }
        
        if(libre){
            this.getJugadorActual().setEncarcelado(false);
        }
        
        return libre;
    }
    
    
    /**
     * Tira el dado, desplaza al jugador por el tablero y aplica las posibles penalizaciones.
     * @return TRUE: La casilla tiene propietario; FALSE: Cualquier otro caso.
     */
    public boolean jugar(){
        Jugador jugador = this.getJugadorActual();
        
        //Tiramos el dado.
        int numDado = Dado.tirar();
        
        //obtenemos la casilla actual del jugador
        Casilla casillaPosicion = jugador.getCasillaActual();
        
        //Calculamos la nueva casilla.
        Casilla nuevaCasilla = this.tablero.obtenerNuevaCasilla(casillaPosicion, numDado);
        
        boolean tienePropietario = jugador.actualizarPosicion(nuevaCasilla);
        
        if(!nuevaCasilla.soyEdificable()){
            if(nuevaCasilla.getTipo() == TipoCasilla.JUEZ){
                this.encarcelarJugador();
            }
            else{
                if(nuevaCasilla.getTipo() == TipoCasilla.SORPRESA){
                    //El jugador coge carta y se elimina del mazo.
                    this.setCartaActual(this.mazo.get(0));
                    this.mazo.remove(0);
                    //Se añade al final del mazo.
                    //this.mazo.add(carta);
                }
            }
        }
        
        return tienePropietario;
    }
    
    
    /**
     * Devuelve el ranking de los jugadores en funcion a su capital.
     * @return Devuelve una estructura MAP con las entradas capital, nombre.
     */
    public Map<Integer, String> obtenerRanking(){
        Map<Integer, String> ranking = new TreeMap <>();
        int capital;
        
        for(Jugador jugador : this.jugadores){
            capital = jugador.obtenerCapital();
            ranking.put(capital, jugador.getNombre());
        }
        
        return ranking;   
    }
    
    
    /**
     * Comprueba si el jugador puede cancelar la hipoteca, en caso afirmativo realiza la cancelación.
     * @param casilla Casilla hipotecada.
     * @return TRUE: Se pudo cancelar la hipoteca; FALSE: NO se pudo cancelar la hipoteca.
     */
    public boolean cancelarHipoteca(Casilla casilla){
            Jugador jugador = this.getJugadorActual();
            
            boolean puedoCancelarHipoteca = false;
            
            if(casilla.estaHipotecada()){
                //Comprueba que el jugador pueda cancelar la hipoteca (Es propietario y tiene dinero).
                puedoCancelarHipoteca = jugador.puedoPagarHipoteca(casilla);
            
                if(puedoCancelarHipoteca){
                    //Cantidad que se le cobra al jugador por cancelar la hipoteca.
                    int costeCancelacion = casilla.cancelarHipoteca();

                    //Se retira la cantidad del saldo del jugador.
                    jugador.modificarSaldo(-costeCancelacion);
                }
        }
        return  puedoCancelarHipoteca;    
    }

    
    @Override
    public String toString() {
        String cadena;
        cadena = "Qytetet{" + "cartaActual=" + cartaActual + ", jugadorActual=" + jugadorActual.getNombre() + "}\n";
        /*for(Jugador jugador : jugadores)
            cadena = cadena + "jugador=" + jugador.toString() + "\n";
        for(Sorpresa sorpresa : mazo)
            cadena = cadena + "mazo=" + sorpresa.toString() + "\n";
        
        cadena = cadena + '}';*/
        
        return cadena;
    }
 
}
