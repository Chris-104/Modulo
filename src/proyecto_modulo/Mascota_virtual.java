package proyecto_modulo;

public class Mascota_virtual {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			 
	        Menu   menu   = new Menu();
	        boolean jugando = true;
	 
	        // ════════════════════════════════════════════════
	        //  LOOP DEL MENÚ DE INICIO
	        // ════════════════════════════════════════════════
	        while (jugando) {
	            int opInicio = menu.mostrarMenuInicio();
	 
	            switch (opInicio) {
	 
	                case 1: // Nueva Partida
	                    Mascota mascota = menu.crearMascota();
	                    iniciarPartida(mascota, menu);
	                    break;
	 
	                case 2: // Instrucciones
	                    menu.mostrarInstrucciones();
	                    break;
	 
	                case 3: // Acerca de
	                    menu.mostrarAcercaDe();
	                    break;
	 
	                case 4: // Salir
	                    System.out.println("\n  👋 ¡Hasta luego! ¡Gracias por jugar!\n");
	                    jugando = false;
	                    break;
	 
	                default:
	                    System.out.println("  ❌ Opción no válida.");
	                    menu.pausa();
	            }
	        }
	    }
	 
	    // ════════════════════════════════════════════════════
	    //  LOOP PRINCIPAL DE LA PARTIDA
	    // ════════════════════════════════════════════════════
	    private static void iniciarPartida(Mascota mascota, Menu menu) {
	        boolean enPartida = true;
	 
	        while (enPartida && mascota.estaViva()) {
	 
	            // Mostrar estado actual de la mascota
	            mascota.mostrarEstado();
	 
	            // Mostrar menú y leer opción
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
	                    continue; // no imprimir resultado vacío
	                case 10:
	                    enPartida = false;
	                    resultado = "  🚪 Volviendo al menú principal...";
	                    break;
	                default:
	                    resultado = "  ❌ Opción no válida.";
	            }
	 
	            System.out.println(resultado);
	 
	            // Verificar si la mascota murió
	            if (!mascota.estaViva()) {
	                menu.mostrarGameOver(mascota);
	                enPartida = false;
	            } else if (opcion != 10) {
	                menu.pausa();
	            }
	        }
	    }
	 
	    // ════════════════════════════════════════════════════
	    //  ESTADÍSTICAS DETALLADAS
	    // ════════════════════════════════════════════════════
	    private static void mostrarEstadisticasDetalladas(Mascota mascota) {
	        Estadisticas s = mascota.getStats();
	        System.out.println("\n =========================================");
	        System.out.println("          📊 ESTADÍSTICAS DETALLADAS        ");
	        System.out.println("   =========================================");
	        System.out.printf ("     Nombre  : %-31s%n", mascota.getNombre());
	        System.out.printf ("     Tipo    : %-31s%n", mascota.getEmoji() + " " + mascota.getTipo());
	        System.out.printf ("    Nivel   : %-31s%n", s.getNivel());
	        System.out.printf ("     XP      : %d / %-26s%n", s.getExperiencia(), s.getXpNecesaria());
	        System.out.printf ("     Días    : %-31s%n", s.getDiasVividos());
	        System.out.printf ("     Dinero  : $%-30s%n", s.getDinero());
	        System.out.println("  ===========================================");
	        System.out.printf ("     🍗 Hambre   : %3d%%                       %n", s.getHambre());
	        System.out.printf ("     ⚡ Energía  : %3d%%                       %n", s.getEnergia());
	        System.out.printf ("     😊 Felicidad: %3d%%                       %n", s.getFelicidad());
	        System.out.printf ("     ❤️  Salud    : %3d%%                      %n", s.getSalud());
	        System.out.printf ("     🛁 Higiene  : %3d%%                       %n", s.getHigiene());
	        System.out.println("  ===========================================");
	        System.out.printf ("     Estado  : %-31s%n",
	                s.getEstado(mascota.isDormida()).getDescripcion());
	        System.out.printf ("     Enferma : %-31s%n",
	                mascota.isEnferma() ? "Sí 🤒" : "No ✅");
	        System.out.println("  ===========================================");
	    }
}