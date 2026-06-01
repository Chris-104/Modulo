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
    		            + AZUL + "" + RESET);

    		    System.out.println(AZUL + "" + ROJO +
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
						" 10.🚪 Ver menu sonido                  "
						+ AZUL + "" + RESET);

				System.out.println(AZUL + "" + CYAN +
						" 11.📊 Volver a menu principal          "
						+ AZUL + "" + RESET);

				System.out.println(AZUL + "==========================================" + RESET);

				System.out.print(MORADO + "\n👉 Tu opción: " + RESET);

				return leerInt();
			}
    				
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

				System.out.println(AZUL + "========= =================================" + RESET);

				String tipo;

			while (true) {
				System.out.print(MORADO +
						"\n👉 Opción: "
						+ RESET);

				int tipoOp = leerInt();

				tipo = "";

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
					case 8:
						System.out.print(CYAN +
								"\n✏️ Escribe el tipo: "
								+ RESET);

						tipo = sc.nextLine().trim();

						if (tipo.isEmpty()) {
							tipo = "animal";
						}
						break;
    				default:
						System.out.println(ROJO + "Elija una opcion valida" + RESET);
						continue;
				}
				break;
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
    						


	public void mostrarInstrucciones() {

	    limpiarPantalla();

	    System.out.println(AZUL + "===========================================" + RESET);

	    System.out.println(BLANCO +
	            "             📖 INSTRUCCIONES              "
	            + RESET);

	    System.out.println(AZUL + "===========================================" + RESET);

	    System.out.println();

	    System.out.println(AMARILLO +
	            "🎯 OBJETIVO:" + RESET);

	    System.out.println(BLANCO +
	            "Cuida a tu mascota manteniendola feliz,");

	    System.out.println(BLANCO +
	            "sana y bien alimentada." + RESET);

	    System.out.println();

	    System.out.println(CYAN +
	            "📊 ESTADISTICAS:" + RESET);

	    System.out.println(VERDE +
	            "🍗 Hambre    → Alimentala si sube" + RESET);

	    System.out.println(AMARILLO +
	            "⚡ Energia   → Hazla dormir si baja" + RESET);

	    System.out.println(MORADO +
	            "😊 Felicidad → Juega con ella" + RESET);

	    System.out.println(ROJO +
	            "❤️ Salud     → Medicala si enferma" + RESET);

	    System.out.println(CYAN +
	            "🛁 Higiene   → Bañala regularmente" + RESET);

	    System.out.println();

	    System.out.println(ROJO +
	            "⚠️ GAME OVER SI:" + RESET);

	    System.out.println(BLANCO +
	            "- Hambre llega a 100" + RESET);

	    System.out.println(BLANCO +
	            "- Salud llega a 0" + RESET);

	    System.out.println();

	    System.out.println(VERDE +
	            "💡 CONSEJOS:" + RESET);

	    System.out.println(BLANCO +
	            "- Trabaja para ganar dinero" + RESET);

	    System.out.println(BLANCO +
	            "- Usa la tienda para comprar items" + RESET);

	    System.out.println(BLANCO +
	            "- Gana XP para subir de nivel" + RESET);

	    System.out.println();

	    System.out.println(AZUL + "===========================================" + RESET);

	    pausa();
	}



	
			public void mostrarAcercaDe() {

			    limpiarPantalla();

			    System.out.println(AZUL + "===========================================" + RESET);

			    System.out.println(BLANCO +
			            "           🏆 ACERCA DEL JUEGO 🏆          "
			            + RESET);

			    System.out.println(AZUL + "===========================================" + RESET);

			    System.out.println();

			    System.out.println(CYAN +
			            "Mascota Virtual - POO Edition" + RESET);

			    System.out.println(AMARILLO +
			            "Version BETA" + RESET);

			    System.out.println();

			    System.out.println(VERDE +
			            "💻 Desarrollado con:" + RESET);

			    System.out.println(BLANCO +
			            "☕ Java + Programacion Orientada" + RESET);

			    System.out.println(BLANCO +
			            "a Objetos" + RESET);

			    System.out.println();

			    System.out.println(MORADO +
			            "📚 Desarrolladores implicados:" + RESET);

			    System.out.println(BLANCO +
			            "• Christian Alexander Hernandez Rivera" + RESET);

			    System.out.println(BLANCO +
			            "• Steven Emmanuel Guerrero Magaña" + RESET);

			    System.out.println(BLANCO +
			            "• Carlos Alexander Ayala Martinez" + RESET);


			    System.out.println();

			    System.out.println(AZUL + "===========================================" + RESET);

			    pausa();
			}
			

			
					public void mostrarGameOver(Mascota mascota) {

					    System.out.println();

					    System.out.println(ROJO + "===========================================" + RESET);

					    System.out.println(BLANCO +
					            "              💀 GAME OVER 💀             "
					            + RESET);

					    System.out.println(ROJO + "===========================================" + RESET);

					    System.out.println();

					    System.out.println(AMARILLO +
					            mascota.getEmoji() + " " + mascota.getNombre()
					            + " ya no esta con nosotros..."
					            + RESET);

					    System.out.println(CYAN +
					            "📅 Vivio " + mascota.getStats().getDiasVividos()
					            + " dia(s)"
					            + RESET);

					    System.out.println(VERDE +
					            "⭐ Llego al nivel "
					            + mascota.getStats().getNivel()
					            + RESET);

					    System.out.println();

					    System.out.println(ROJO +
					            "💔 La proxima vez cuidalo/a mejor."
					            + RESET);

					    System.out.println();

					    System.out.println(ROJO + "===========================================" + RESET);

					    pausa();
					}
				


    public void limpiarPantalla() {
        for (int i = 0; i < 3; i++) System.out.println();
    }

    public void pausa() {
        System.out.print("\n  Presiona Enter para continuar...");
        sc.nextLine();
    }

     int leerInt() {
		while (true) {
			try {
				return Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.print("Ingresa un número válido: ");
			}
		}
	}

    public Scanner getSc() { return sc; }
}