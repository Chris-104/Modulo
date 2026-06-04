package proyecto_modulo;

public class Mascota_virtual {
	public static final String ROJO = "\u001B[31m";
	public static final String VERDE = "\u001B[32m";
	public static final String AMARILLO = "\u001B[33m";
	public static final String AZUL = "\u001B[34m";
	public static final String MORADO = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String BLANCO = "\u001B[37m";
	public static final String RESET1 = "\u001B[0m";
	public static final String NEGRO = "\u001B[30m";

	private static final int RESET = 0;
	public static void main(String[] args) {
		 
        Menu    menu    = new Menu();
        boolean jugando = true;

        Reproductor_sonidos.iniciarMusicaFondo();

        while (jugando) {
            int opInicio = menu.mostrarMenuInicio();

            switch (opInicio) {
 
                case 1:
                    Mascota mascota = menu.crearMascota();
                    iniciarPartida(mascota, menu);

                    Reproductor_sonidos.iniciarMusicaFondo();
                    break;
 
                case 2:
                    menu.mostrarInstrucciones();
                    break;
 
                case 3:
                    menu.mostrarAcercaDe();
                    break;
 
                case 4:
                    Reproductor_sonidos.detenerMusicaFondo();
                    System.out.println("\n  👋 ¡Hasta luego! ¡Gracias por jugar!\n");
                    jugando = false;
                    break;
 
                default:
                    System.out.println("  ❌ Opción no válida.");
                    menu.pausa();
            }
        }
    }

    private static void iniciarPartida(Mascota mascota, Menu menu) {
        boolean enPartida = true;
 
        while (enPartida && mascota.estaViva()) {
 
            mascota.mostrarEstado();
            int opcion = menu.mostrarMenuJuego(mascota);
         
            System.out.println();
 
            String resultado = "";
 
            switch (opcion) {
                case 1:
                    resultado = mascota.alimentar();
                    break;
                case 2:
                    resultado = mascota.jugar();
                    break;
                case 3:
                    resultado = mascota.dormir();
                    break;
                case 4:
                    resultado = mascota.despertar();
                    break;
                case 5:
                    resultado = mascota.bañar();
                    break;
                case 6:
                    resultado = mascota.medicar();
                    break;
                case 7:
                    resultado = mascota.trabajar();
                    break;
                case 8:
                    Tienda.mostrar(mascota, menu.getSc());
                    resultado = "  🏪 Volviste de la tienda.";
                    break;
                case 9:
                    mostrarEstadisticasDetalladas(mascota);
                    menu.pausa();
                    continue;
                case 10:
                    mostrarMenuAudioEnJuego(menu);
                    continue;
                case 11:
                    enPartida = false;
                    resultado = "  🚪 Volviendo al menú principal...";
                    break;
                default:
                    resultado = "  ❌ Opción no válida.";
            }
 
            System.out.println(resultado);

            if (!mascota.estaViva()) {
                menu.mostrarGameOver(mascota);
                enPartida = false;
            } else if (opcion != 11) {
                menu.pausa();
            }
        }
    }

    
    		private static void mostrarMenuAudioEnJuego(Menu menu) {

    		    boolean enAudio = true;

    		    while (enAudio) {

    		        System.out.println();

    		        System.out.println(AZUL + "===========================================" + RESET1);

    		        System.out.println(BLANCO +
    		                "        🎵 CONFIGURACION DE AUDIO 🎵       "
    		                + RESET1);

    		        System.out.println(AZUL + "===========================================" + RESET1);

    		        System.out.println(CYAN +
    		                "🔊 Sonido global: "
    		                + (Reproductor_sonidos.isSonidoActivado()
    		                ? "✅ Activado"
    		                : "❌ Desactivado")
    		                + RESET1);

    		        System.out.println(AZUL + "===========================================" + RESET1);

    		        System.out.println(VERDE +
    		                "1. 🔊 Activar / Desactivar sonido"
    		                + RESET1);

    		        System.out.println(AMARILLO +
    		                "2. 🎵 Reiniciar musica de fondo"
    		                + RESET1);

    		        System.out.println(ROJO +
    		                "3. 🔇 Detener musica de fondo"
    		                + RESET1);

    		        System.out.println(MORADO +
    		                "4. 🔙 Volver al juego"
    		                + RESET1);

    		        System.out.println(AZUL + "===========================================" + RESET1);

    		        System.out.print(MORADO +
    		                "\n👉 Opcion: "
    		                + RESET1);

    		        int op = menu.leerInt();

    		        switch (op) {

    		            case 1:
    		                Reproductor_sonidos.setSonidoActivado(
    		                        !Reproductor_sonidos.isSonidoActivado());

    		                System.out.println(VERDE +
    		                        "\n✅ Configuracion actualizada."
    		                        + RESET1);
    		                break;

    		            case 2:
    		                Reproductor_sonidos.reiniciarMusica();
    		                break;

    		            case 3:
    		                Reproductor_sonidos.detenerMusicaFondo();
    		                break;

    		            case 4:
    		                enAudio = false;
    		                break;

    		            default:
    		                System.out.println(ROJO +
    		                        "\n❌ Opcion invalida."
    		                        + RESET1);
    		        }
    		    }
    		
    	

            int op = 0;
			switch (op) {
                case 1:
                    Reproductor_sonidos.setSonidoActivado(!Reproductor_sonidos.isSonidoActivado());
                    System.out.println("  🔊 Sonido: " +
                            (Reproductor_sonidos.isSonidoActivado() ? "ACTIVADO ✅" : "DESACTIVADO ❌"));
                    break;
                case 2:
                    Reproductor_sonidos.iniciarMusicaFondo();
                    System.out.println("  🎵 Música de fondo reiniciada.");
                    break;
                case 3:
                    Reproductor_sonidos.detenerMusicaFondo();
                    System.out.println("  🔇 Música de fondo detenida.");
                    break;
                case 4:
                    enAudio = false;
                    break;
                default:
                    System.out.println("  ❌ Opción no válida.");
            }
        }
    

    	
    			// ERROR DETECTADO: En este método se usa la constante RESET (int = 0) en vez de RESET1 (String = "\u001B[0m").
    			// Al concatenar String + int, Java convierte el 0 a texto, por eso aparece un "0" al final de cada línea.
    			// Además los códigos de color ANSI no se resetean correctamente.
    			private static void mostrarEstadisticasDetalladas(Mascota mascota) {

    			    Estadisticas s = mascota.getStats();

    				    System.out.println();

    				    System.out.println(AZUL + "===========================================" + RESET1);

    				    System.out.println(BLANCO +
    				            "       📊 ESTADISTICAS DETALLADAS 📊       "
    				            + RESET1);

    				    System.out.println(AZUL + "===========================================" + RESET1);

    				    System.out.println(CYAN +
    				            "Nombre : " + mascota.getNombre()
    				            + RESET1);

    				    System.out.println(CYAN +
    				            "Tipo   : " + mascota.getEmoji() + " " + mascota.getTipo()
    				            + RESET1);

    				    System.out.println(AMARILLO +
    				            "Nivel  : " + s.getNivel()
    				            + RESET1);

    				    System.out.println(AMARILLO +
    				            "XP     : " + s.getExperiencia()
    				            + " / " + s.getXpNecesaria()
    				            + RESET1);

    				    System.out.println(VERDE +
    				            "Dias   : " + s.getDiasVividos()
    				            + RESET1);

    				    System.out.println(VERDE +
    				            "Dinero : $" + s.getDinero()
    				            + RESET1);

    				    System.out.println(AZUL + "===========================================" + RESET1);

    				    System.out.println(ROJO +
    				            "🍗 Hambre     : " + s.getHambre() + "%"
    				            + RESET1);

    				    System.out.println(AMARILLO +
    				            "⚡ Energia    : " + s.getEnergia() + "%"
    				            + RESET1);

    				    System.out.println(MORADO +
    				            "😊 Felicidad  : " + s.getFelicidad() + "%"
    				            + RESET1);

    				    System.out.println(CYAN +
    				            "❤️ Salud      : " + s.getSalud() + "%"
    				            + RESET1);

    				    System.out.println(VERDE +
    				            "🛁 Higiene    : " + s.getHigiene() + "%"
    				            + RESET1);

    				    System.out.println(AZUL + "===========================================" + RESET1);

    				    System.out.println(BLANCO +
    				            "Estado : "
    				            + s.getEstado(mascota.isDormida()).getDescripcion()
    				            + RESET1);

    				    System.out.println(mascota.isEnferma()
    				            ? ROJO + "Enferma : Si 🤒" + RESET1
    				            : VERDE + "Enferma : No ✅" + RESET1);

    				    System.out.println(AZUL + "===========================================" + RESET1);
    				}
}
