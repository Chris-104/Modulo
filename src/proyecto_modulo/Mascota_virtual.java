package proyecto_modulo;

public class Mascota_virtual {

	public static void main(String[] args) {
		 
        Menu    menu    = new Menu();
        boolean jugando = true;
 
        // 🔊 Iniciar música de fondo del menú al arrancar
        Reproductor_sonidos.iniciarMusicaFondo();
 
        // ════════════════════════════════════════════════
        //  LOOP DEL MENÚ DE INICIO
        // ════════════════════════════════════════════════
        while (jugando) {
            int opInicio = menu.mostrarMenuInicio();
 
            // 🔊 Click en cada selección del menú
          
 
            switch (opInicio) {
 
                case 1: // Nueva Partida
                    Mascota mascota = menu.crearMascota();
                    iniciarPartida(mascota, menu);
                    // 🔊 Al volver al menú, retomar música de fondo
                    Reproductor_sonidos.iniciarMusicaFondo();
                    break;
 
                case 2:
                    menu.mostrarInstrucciones();
                    break;
 
                case 3:
                    menu.mostrarAcercaDe();
                    break;
 
                case 4:
                    // 🔊 Detener todo el sonido al salir
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
 
    // ════════════════════════════════════════════════════
    //  LOOP PRINCIPAL DE LA PARTIDA
    // ════════════════════════════════════════════════════
    private static void iniciarPartida(Mascota mascota, Menu menu) {
        boolean enPartida = true;
 
        while (enPartida && mascota.estaViva()) {
 
            mascota.mostrarEstado();
            int opcion = menu.mostrarMenuJuego(mascota);
 
            // 🔊 Click en cada opción del menú de juego
         
            System.out.println();
 
            String resultado = "";
 
            switch (opcion) {
                case 1:
                    // alimentar() ya llama a Reproductor.sfxComer() internamente
                    resultado = mascota.alimentar();
                    break;
                case 2:
                    resultado = mascota.jugar();
                    break;
                case 3:
                    // dormir() ya llama a Reproductor.sfxDuerma() internamente
                    resultado = mascota.dormir();
                    break;
                case 4:
                    resultado = mascota.despertar();
                    break;
                case 5:
                    // bañar() ya llama a Reproductor.sfxBañar() internamente
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
 
            // Verificar si la mascota murió
            // (el sonido de muerte ya se llama desde avanzarTurno() en Mascota)
            if (!mascota.estaViva()) {
                menu.mostrarGameOver(mascota);
                enPartida = false;
            } else if (opcion != 11) {
                menu.pausa();
            }
        }
    }
 
    // ════════════════════════════════════════════════════
    //  MENÚ DE AUDIO DENTRO DEL JUEGO
    // ════════════════════════════════════════════════════
    private static void mostrarMenuAudioEnJuego(Menu menu) {
        boolean enAudio = true;
        while (enAudio) {
            System.out.println("\n  ╔══════════════════════════════════════════╗");
            System.out.println("  ║          🎵 CONFIGURACIÓN DE AUDIO      ║");
            System.out.println("  ╠══════════════════════════════════════════╣");
            System.out.printf ("  ║  Sonido global: %-25s║%n",
                    Reproductor_sonidos.isSonidoActivado() ? "✅ Activado" : "❌ Desactivado");
            System.out.println("  ╠══════════════════════════════════════════╣");
            System.out.println("  ║  1. 🔊 Activar / Desactivar todo sonido ║");
            System.out.println("  ║  2. 🎵 Reiniciar música de fondo        ║");
            System.out.println("  ║  3. 🔇 Detener música de fondo          ║");
            System.out.println("  ║  4. 🔙 Volver al juego                  ║");
            System.out.println("  ╚══════════════════════════════════════════╝");
            System.out.print("  👉 Opción: ");
            int op = menu.leerInt();
 
            switch (op) {
                case 1:
                    // Alterna sonido ON/OFF
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
    }
 
    // ════════════════════════════════════════════════════
    //  ESTADÍSTICAS DETALLADAS
    // ════════════════════════════════════════════════════
    private static void mostrarEstadisticasDetalladas(Mascota mascota) {
        Estadisticas s = mascota.getStats();
        System.out.println("\n  ╔══════════════════════════════════════════╗");
        System.out.println("  ║       📊 ESTADÍSTICAS DETALLADAS        ║");
        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.printf ("  ║  Nombre  : %-31s║%n", mascota.getNombre());
        System.out.printf ("  ║  Tipo    : %-31s║%n", mascota.getEmoji() + " " + mascota.getTipo());
        System.out.printf ("  ║  Nivel   : %-31s║%n", s.getNivel());
        System.out.printf ("  ║  XP      : %d / %-26s║%n", s.getExperiencia(), s.getXpNecesaria());
        System.out.printf ("  ║  Días    : %-31s║%n", s.getDiasVividos());
        System.out.printf ("  ║  Dinero  : $%-30s║%n", s.getDinero());
        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.printf ("  ║  🍗 Hambre   : %3d%%                     ║%n", s.getHambre());
        System.out.printf ("  ║  ⚡ Energía  : %3d%%                     ║%n", s.getEnergia());
        System.out.printf ("  ║  😊 Felicidad: %3d%%                     ║%n", s.getFelicidad());
        System.out.printf ("  ║  ❤️  Salud    : %3d%%                     ║%n", s.getSalud());
        System.out.printf ("  ║  🛁 Higiene  : %3d%%                     ║%n", s.getHigiene());
        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.printf ("  ║  Estado  : %-31s║%n",
                s.getEstado(mascota.isDormida()).getDescripcion());
        System.out.printf ("  ║  Enferma : %-31s║%n",
                mascota.isEnferma() ? "Sí 🤒" : "No ✅");
        System.out.println("  ╚══════════════════════════════════════════╝");
    }
}