package proyecto_modulo;

import java.util.Scanner;

// Clase que maneja todos los menús del juego
public class Menu {
	public static final String ROJO = "\u001B[31m";
	public static final String VERDE = "\u001B[32m";
	public static final String AMARILLO = "\u001B[33m";
	public static final String AZUL = "\u001B[34m";
	public static final String MORADO = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String BLANCO = "\u001B[37m";
	public static final String RESET = "\u001B[0m";
	public static final String NEGRO = "\u001B[30m";
			

    private Scanner sc;

    public Menu() {
        this.sc = new Scanner(System.in);
    }

    // ════════════════════════════════════════════════════
    //  MENÚ DE INICIO
    // ════════════════════════════════════════════════════
    
    		public int mostrarMenuInicio() {

    		    limpiarPantalla();

    		    System.out.println(AZUL + "==========================================" + RESET);

    		    System.out.println(AZUL + "" + BLANCO +
    		            "         🐾 MASCOTA VIRTUAL 🐾         "
    		            + AZUL + "" + RESET);

    		    System.out.println(AZUL + "" + BLANCO +
    		            "             POO EDITION              "
    		            + AZUL + "" + RESET);

    		    System.out.println(AZUL + "==========================================" + RESET);

    		    System.out.println(AZUL + "" + VERDE +
    		            " 1. Nueva Partida                     "
    		            + AZUL + "" + RESET);

    		    System.out.println(AZUL + "" + AMARILLO +
    		            " 2. Instrucciones                     "
    		            + AZUL + "" + RESET);

    		    System.out.println(AZUL + "" + CYAN +
    		            " 3. Acerca del juego                  "
    		            + AZUL + "" + RESET);

    		    System.out.println(AZUL + "" + ROJO +
    		            " 4. Salir                             "
    		            + AZUL + "" + RESET);

    		    System.out.println(AZUL + "==========================================" + RESET);

    		    System.out.print(MORADO + "\n👉 Selecciona una opción: " + RESET);

    		    return leerInt();
    		}
    		

   


    

    // ════════════════════════════════════════════════════
    //  MENÚ PRINCIPAL DEL JUEGO
    // ════════════════════════════════════════════════════
  
    				public int mostrarMenuJuego(Mascota mascota) {

    				    System.out.println();

    				    System.out.println(AZUL + "==========================================" + RESET);

    				    System.out.println(AZUL + "" + BLANCO +
    				            "         🎮 MENU DEL JUEGO 🎮          "
    				            + AZUL + "" + RESET);

    				    System.out.println(AZUL + "==========================================" + RESET);

    				    System.out.println(AZUL + "" + CYAN +
    				            " Mascota: " + mascota.getEmoji() + " " +
    				            mascota.getNombre()
    				            + AZUL + " " + RESET);

    				    System.out.println(AZUL + "" + AMARILLO +
    				            " Nivel: " + mascota.getStats().getNivel()
    				            + "   💰 Dinero: $" +
    				            mascota.getStats().getDinero()
    				            + AZUL + " " + RESET);

    				    System.out.println(AZUL + "==========================================" + RESET);

    				    System.out.println(AZUL + "" + VERDE +
    				            " 1. 🍖 Alimentar                       "
    				            + AZUL + "" + RESET);

    				    System.out.println(AZUL + "" + CYAN +
    				            " 2. 🎾 Jugar                           "
    				            + AZUL + "" + RESET);

    				    System.out.println(AZUL + "" + MORADO +
    				            " 3. 🌙 Dormir                          "
    				            + AZUL + "" + RESET);

    				    System.out.println(AZUL + "" + AMARILLO +
    				            " 4. ☀️ Despertar                       "
    				            + AZUL + "" + RESET);

    				    System.out.println(AZUL + "" + CYAN +
    				            " 5. 🛁 Bañar                           "
    				            + AZUL + "=" + RESET);

    				    System.out.println(AZUL + "=" + ROJO +
    				            " 6. 💊 Medicar                         "
    				            + AZUL + "" + RESET);

    				    System.out.println(AZUL + "" + VERDE +
    				            " 7. 💼 Trabajar                        "
    				            + AZUL + "" + RESET);

    				    System.out.println(AZUL + "" + AMARILLO +
    				            " 8. 🏪 Ir a la tienda                  "
    				            + AZUL + "" + RESET);

    				    System.out.println(AZUL + "" + CYAN +
    				            " 9. 📊 Ver estadísticas                "
    				            + AZUL + "" + RESET);

    				    System.out.println(AZUL + "" + ROJO +
    				            "10. 🚪 Volver al menú                  "
    				            + AZUL + "" + RESET);

    				    System.out.println(AZUL + "==========================================" + RESET);

    				    System.out.print(MORADO + "\n👉 Tu opción: " + RESET);

    				    return leerInt();
    				}
    				

    // ════════════════════════════════════════════════════
    //  CREAR MASCOTA
    // ════════════════════════════════════════════════════
    				
    						public Mascota crearMascota() {

    						    limpiarPantalla();

    						    System.out.println(AZUL + "==========================================" + RESET);

    						    System.out.println(AZUL + "" + BLANCO +
    						            "        🌟 CREAR TU MASCOTA 🌟         "
    						            + AZUL + "" + RESET);

    						    System.out.println(AZUL + "==========================================" + RESET);

    						    System.out.print(CYAN +
    						            "\n✏️ ¿Cómo se llamará tu mascota?: "
    						            + RESET);

    						    String nombre = sc.nextLine().trim();

    						    if (nombre.isEmpty()) {
    						        nombre = "Sin nombre";
    						    }

    						    System.out.println(AMARILLO +
    						            "\n🐾 Elige el tipo de mascota:"
    						            + RESET);

    						    System.out.println(VERDE +
    						            " 1. 🐶 Perro      2. 🐱 Gato"
    						            + RESET);

    						    System.out.println(CYAN +
    						            " 3. 🐲 Dragón     4. 🐰 Conejo"
    						            + RESET);

    						    System.out.println(MORADO +
    						            " 5. 🐼 Panda      6. 🦊 Zorro"
    						            + RESET);

    						    System.out.println(AMARILLO +
    						            " 7. 🐺 Lobo       8. ✏️ Otro"
    						            + RESET);

    						    System.out.println(AZUL + "==========================================" + RESET);

    						    System.out.print(MORADO +
    						            "\n👉 Opción: "
    						            + RESET);

    						    int tipoOp = leerInt();

    						    String tipo;

    						    switch (tipoOp) {

    						        case 1:
    						            tipo = "perro";
    						            break;

    						        case 2:
    						            tipo = "gato";
    						            break;

    						        case 3:
    						            tipo = "dragón";
    						            break;

    						        case 4:
    						            tipo = "conejo";
    						            break;

    						        case 5:
    						            tipo = "panda";
    						            break;

    						        case 6:
    						            tipo = "zorro";
    						            break;

    						        case 7:
    						            tipo = "lobo";
    						            break;

    						        default:

    						            System.out.print(CYAN +
    						                    "\n✏️ Escribe el tipo: "
    						                    + RESET);

    						            tipo = sc.nextLine().trim();

    						            if (tipo.isEmpty()) {
    						                tipo = "animal";
    						            }
    						    }

    						    System.out.println(VERDE +
    						            "\n✨ ¡" + nombre + " el/la " + tipo +
    						            " ha llegado a tu vida!"
    						            + RESET);

    						    System.out.println(AMARILLO +
    						            "💖 ¡Cuídalo/a bien!"
    						            + RESET);

    						    pausa();

    						    return new Mascota(nombre, tipo);
    						}
    						

    // ════════════════════════════════════════════════════
    //  PANTALLAS INFORMATIVAS
    // ════════════════════════════════════════════════════
    public void mostrarInstrucciones() {
        limpiarPantalla();
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.println("  ║            📖 INSTRUCCIONES              ║");
        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.println("  ║                                          ║");
        System.out.println("  ║  🎯 OBJETIVO:                            ║");
        System.out.println("  ║  Cuida a tu mascota manteniéndola        ║");
        System.out.println("  ║  feliz, sana y bien alimentada.          ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ║  📊 ESTADÍSTICAS:                        ║");
        System.out.println("  ║  🍗 Hambre   → Aliméntala si sube        ║");
        System.out.println("  ║  ⚡ Energía  → Hazla dormir si baja      ║");
        System.out.println("  ║  😊 Felicidad→ Juega con ella            ║");
        System.out.println("  ║  ❤️  Salud    → Médica si enferma         ║");
        System.out.println("  ║  🛁 Higiene  → Báñala regularmente       ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ║  ⚠️  GAME OVER si:                       ║");
        System.out.println("  ║  - Hambre llega a 100                    ║");
        System.out.println("  ║  - Salud llega a 0                      ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ║  💡 CONSEJOS:                           ║");
        System.out.println("  ║  - Trabaja para ganar dinero            ║");
        System.out.println("  ║  - Usa la tienda para comprar items     ║");
        System.out.println("  ║  - Gana XP para subir de nivel          ║");
        System.out.println("  ╚═════════════════════════════════════════");
        pausa();
    }

    public void mostrarAcercaDe() {
        limpiarPantalla();
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.println("  ║          🏆 ACERCA DEL JUEGO            ║");
        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.println("  ║                                          ║");
        System.out.println("  ║   Mascota Virtual - POO Edition          ║");
        System.out.println("  ║   Versión 2.0                            ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ║   Desarrollado con:                      ║");
        System.out.println("  ║   ☕ Java + Programación Orientada       ║");
        System.out.println("  ║      a Objetos                           ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ║   Clases usadas:                         ║");
        System.out.println("  ║   • Mascota       • Estadisticas         ║");
        System.out.println("  ║   • EstadoMascota • Tienda               ║");
        System.out.println("  ║   • Menu          • JuegoMascota         ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ╚══════════════════════════════════════════╝");
        pausa();
    }

    public void mostrarGameOver(Mascota mascota) {
        System.out.println("\n  ╔══════════════════════════════════════════╗");
        System.out.println("  ║              💀 GAME OVER               ║");
        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.printf ("  ║  %s %s ya no está con nosotros...%n",
                mascota.getEmoji(), mascota.getNombre());
        System.out.printf ("  ║  Vivió %d día(s) y llegó al nivel %d.%n",
                mascota.getStats().getDiasVividos(), mascota.getStats().getNivel());
        System.out.println("  ║  ¡La próxima vez cuídalo/a mejor! 💔    ║");
        System.out.println("  ╚══════════════════════════════════════════╝");
        pausa();
    }

    // ════════════════════════════════════════════════════
    //  HELPERS
    // ════════════════════════════════════════════════════
    public void limpiarPantalla() {
        for (int i = 0; i < 3; i++) System.out.println();
    }

    public void pausa() {
        System.out.print("\n  Presiona Enter para continuar...");
        sc.nextLine();
    }

     int leerInt() {

    		    while (!sc.hasNextInt()) {

    		        System.out.print("Ingresa un número válido: ");
    		        sc.next();
    		    }

    		    int numero = Integer.parseInt(sc.nextLine());

    		    return numero;
    		}
    	

        
    

    public Scanner getSc() { return sc; }
}