/**
 * @author Alejandro Rodríguez López
 * @email alexrodriguezlop@correo.ugr.es
 */

package InterfazTextualQytetet;

import java.util.ArrayList;
import modeloqytetet.Casilla;
import modeloqytetet.Jugador;
import modeloqytetet.MetodoSalirCarcel;
import modeloqytetet.Qytetet;
import modeloqytetet.TipoCasilla;



public class ControladorQytetet {
    VistaTextualQytetet vista;
    Casilla casilla;
    Qytetet juego;
    Jugador jugador;
    
    private Casilla elegirPropiedad(ArrayList<Casilla> propiedades){ 
        //este metodo toma una lista de propiedades y genera una lista de strings, con el numero y nombre de las propiedades
        //luego llama a la vista para que el usuario pueda elegir.
        vista.mostrar("\tCasilla\tTitulo");
        int seleccion;
        ArrayList<String> listaPropiedades= new ArrayList();
        for ( Casilla casilla: propiedades){
                //HE CAMBIADO LA VISIBILIDAD:
                listaPropiedades.add("\t"+casilla.getNumeroCasilla()+"\t"+casilla.getTituloPropiedad().getNombre());
        }
        seleccion=vista.menuElegirPropiedad(listaPropiedades);  
        return propiedades.get(seleccion);        
    }
    
    public void desarrolloJuego(){
        
        //Jugar mientras no caiga en banca rota:
        do{
            boolean libre = false;
            boolean tienePropietario = false;      

            System.out.println("Le toca jugar a: " + jugador.getNombre() + "\nEstado:" + jugador.toString());
            System.out.println("El jugador "+ jugador.getNombre()+" se encuentra en la casilla: \n" + casilla.toString());

            //Si está en la carcel
            if(jugador.getEncarcelado()){
                System.out.println("El jugador "+ jugador.getNombre()+"  está en la carcel.");
                //Elegir el metodo para salir de la carcel:
                int metodo=vista.menuSalirCarcel();

                //Ha elegido salir tirando el dado:
                if(metodo == 0){
                    libre = juego.intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO);
                }
                else//Ha elegido salir pagando libertad:
                    libre= juego.intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD);

                //Si el jugador está libre:
                if(libre){
                    System.out.println("Has podido salir de la carcel. ");
                }
                else 
                    System.out.println("El jugador "+ jugador.getNombre()+"  sigue encarcelado.");
            }
          
            
            //Si no está encarcelado:
            if(!jugador.getEncarcelado()){
                System.out.println("El jugador "+ jugador.getNombre()+" tira los dados.");
                tienePropietario = juego.jugar();
                
                //Casilla ha podido cambiar
                casilla = jugador.getCasillaActual();
                System.out.println("El jugador "+ jugador.getNombre()+"  se encuentra en la casilla: \n" + casilla.toString());
                
                if(jugador.tengoSaldo(1)){
                    if(!jugador.getEncarcelado()){                        
                        
                        //Elegir dependiendo de la casilla en la que hayas caido:
                        switch(casilla.getTipo()){
                            //Ha tocado SOPRESA:
                            case SORPRESA: 
                                tienePropietario = juego.aplicarSorpresa();
                                
                                //Casilla ha podido cambiar
                                casilla = jugador.getCasillaActual();
                                System.out.println("El jugador "+ jugador.getNombre()+"  se encuentra en la casilla: \n" + casilla.toString());
                                
                                if(!jugador.getEncarcelado()){
                                    if(jugador.tengoSaldo(1)){
                                        //Si el tipo sorpresa era IRCASILLA
                                        if(casilla.getTipo()== TipoCasilla.CALLE){                                          
                                            if(!tienePropietario){
                                                if(vista.elegirQuieroComprar()){
                                                    juego.comprarTituloPropiedad();
                                                }
                                            }
                                        }
                                    }
                                }
                                break;

                            case CALLE:
                                if(!tienePropietario){
                                    if(vista.elegirQuieroComprar()){
                                        juego.comprarTituloPropiedad();
                                    }
                                }
                                break;
                        }//Fin SWITCH   
                    }
                }
                //Si el jugador no está encarcelado, no está en bancaRota y tiene propiedades:
                if((!jugador.getEncarcelado() && jugador.tengoSaldo(1) && jugador.tengoPropiedades())){
                    int opcion, indicePropiedad;

                    do{
                    jugador = juego.getJugadorActual();
                        opcion = vista.menuGestionInmobiliaria();
                        System.out.println("Le toca jugar a: " + jugador.getNombre() + "\nEstado:" + jugador.toString());
                        if(opcion != 0){
                            indicePropiedad = vista.menuElegirPropiedad(jugador.getNombrePropiedades());
                            Casilla casillaElegida = jugador.getMiCasilla(indicePropiedad);
                            switch(opcion){
                                //En caso de que elija Edificar una casa:
                                case 1:
                                    juego.edificarCasa(casillaElegida);
                                    break;
                                //En caso de que elija Edificar un hotel:                                
                                case 2:
                                    juego.edificarHotel(casillaElegida);
                                    break;  
                                //En caso de que elija Vender propiedad:                                
                                case 3:
                                    juego.venderPropiedad(casillaElegida);
                                    break; 
                                //En caso de que elija Hipotecar Propiedad:                                
                                case 4:
                                    juego.hipotecarPropiedad(casillaElegida);
                                    break; 
                                //En caso de que elija Edificar un hotel:                                
                                case 5:
                                    juego.cancelarHipoteca(casillaElegida);
                                    break;  
                            } 
                        }
                    }while(opcion != 0 );//Mientras el jugador no seleccione 0, Mostramos menú.
                }

            }
            //Cambio de jugadores
            juego.siguienteJugador();            
            jugador = juego.getJugadorActual();
            
            //Actualizamos la casilla
            casilla = jugador.getCasillaActual();
       }while(jugador.tengoSaldo(1));
        
        juego.obtenerRanking();
                
    }

    public void inicializacionJuego(){
        vista = new VistaTextualQytetet();
        juego = Qytetet.getInstance();
        //Creacion de los nombres introducidos por teclado:
        ArrayList<String> nombres = vista.obtenerNombreJugadores();
        juego.inicializarJuego(nombres);
        jugador = juego.getJugadorActual();
        casilla = jugador.getCasillaActual();
        System.out.println(juego);
    }
    
    
    public static void main(String[] args){
        ControladorQytetet c = new ControladorQytetet();
        c.inicializacionJuego();
        c.desarrolloJuego();
    }    
 
}
