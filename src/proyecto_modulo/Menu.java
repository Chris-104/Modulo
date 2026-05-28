package proyecto_modulo;

import java.util.Scanner;

// Clase que maneja todos los menús del juego
public class Menu {

    private Scanner sc;

    public Menu() {
        this.sc = new Scanner(System.in);
    }

    // ════════════════════════════════════════════════════
    //  MENÚ DE INICIO
    // ════════════════════════════════════════════════════
    public int mostrarMenuInicio() {
        limpiarPantalla();
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.println("  ║                                          ║");
        System.out.println("  ║     🐾  MASCOTA VIRTUAL  🐾              ║");
        System.out.println("  ║          POO Edition                     ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.println("  ║                                          ║");
        System.out.println("  ║   1. 🌟 Nueva Partida                   ║");
        System.out.println("  ║   2. 📖 Instrucciones                   ║");
        System.out.println("  ║   3. 🏆 Acerca del juego                ║");
        System.out.println("  ║   4. ❌ Salir                           ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ╚══════════════════════════════════════════╝");
        System.out.print("  👉 Selecciona una opción: ");
        return leerInt();
    }

    // ════════════════════════════════════════════════════
    //  MENÚ PRINCIPAL DEL JUEGO
    // ════════════════════════════════════════════════════
    public int mostrarMenuJuego(Mascota mascota) {
        System.out.println("\n  ╔══════════════════════════════════════════╗");
        System.out.printf ("  ║  %s %-10s  Nv.%d  💰$%d%n",
                mascota.getEmoji(), mascota.getNombre(),
                mascota.getStats().getNivel(), mascota.getStats().getDinero());
        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.println("  ║  ¿Qué deseas hacer?                     ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ║   1. 🍖 Alimentar                       ║");
        System.out.println("  ║   2. 🎾 Jugar                           ║");
        System.out.println("  ║   3. 🌙 Dormir                          ║");
        System.out.println("  ║   4. ☀️  Despertar                       ║");
        System.out.println("  ║   5. 🛁 Bañar                           ║");
        System.out.println("  ║   6. 💊 Medicar                         ║");
        System.out.println("  ║   7. 💼 Trabajar (ganar dinero)         ║");
        System.out.println("  ║   8. 🏪 Ir a la tienda                  ║");
        System.out.println("  ║   9. 📊 Ver estadísticas detalladas     ║");
        System.out.println("  ║  10. 🚪 Volver al menú principal        ║");
        System.out.println("  ╚══════════════════════════════════════════╝");
        System.out.print("  👉 Tu opción: ");
        return leerInt();
    }

    // ════════════════════════════════════════════════════
    //  CREAR MASCOTA
    // ════════════════════════════════════════════════════
    public Mascota crearMascota() {
        limpiarPantalla();
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.println("  ║       🌟 CREAR TU MASCOTA               ║");
        System.out.println("  ╚══════════════════════════════════════════╝\n");

        System.out.print("  ✏️  ¿Cómo se llamará tu mascota? ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) nombre = "Sin nombre";

        System.out.println("\n  🐾 Elige el tipo de mascota:");
        System.out.println("     1. 🐶 Perro     2. 🐱 Gato");
        System.out.println("     3. 🐲 Dragón    4. 🐰 Conejo");
        System.out.println("     5. 🐼 Panda     6. 🦊 Zorro");
        System.out.println("     7. 🐺 Lobo      8. Otro (escribe tú)");
        System.out.print("  👉 Opción: ");
        int tipoOp = leerInt();

        String tipo;
        switch (tipoOp) {
            case 1: tipo = "perro";  break;
            case 2: tipo = "gato";   break;
            case 3: tipo = "dragón"; break;
            case 4: tipo = "conejo"; break;
            case 5: tipo = "panda";  break;
            case 6: tipo = "zorro";  break;
            case 7: tipo = "lobo";   break;
            default:
                System.out.print("  ✏️  Escribe el tipo: ");
                tipo = sc.nextLine().trim();
                if (tipo.isEmpty()) tipo = "animal";
        }

        System.out.println("\n  ✨ ¡" + nombre + " el/la " + tipo + " ha llegado a tu vida!");
        System.out.println("  ¡Cuídalo/a bien!\n");
        pausa();
        return new Mascota(nombre, tipo);
    }

    // ════════════════════════════════════════════════════
    //  PANTALLAS INFORMATIVAS
    // ════════════════════════════════════════════════════
    public void mostrarInstrucciones() {
        limpiarPantalla();
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.println("  ║            📖 INSTRUCCIONES             ║");
        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.println("  ║                                          ║");
        System.out.println("  ║  🎯 OBJETIVO:                           ║");
        System.out.println("  ║  Cuida a tu mascota manteniéndola       ║");
        System.out.println("  ║  feliz, sana y bien alimentada.         ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ║  📊 ESTADÍSTICAS:                       ║");
        System.out.println("  ║  🍗 Hambre   → Aliméntala si sube       ║");
        System.out.println("  ║  ⚡ Energía  → Hazla dormir si baja     ║");
        System.out.println("  ║  😊 Felicidad→ Juega con ella           ║");
        System.out.println("  ║  ❤️  Salud    → Médica si enferma        ║");
        System.out.println("  ║  🛁 Higiene  → Báñala regularmente      ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ║  ⚠️  GAME OVER si:                       ║");
        System.out.println("  ║  - Hambre llega a 100                   ║");
        System.out.println("  ║  - Salud llega a 0                      ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ║  💡 CONSEJOS:                           ║");
        System.out.println("  ║  - Trabaja para ganar dinero            ║");
        System.out.println("  ║  - Usa la tienda para comprar items     ║");
        System.out.println("  ║  - Gana XP para subir de nivel         ║");
        System.out.println("  ╚══════════════════════════════════════════╝");
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

    public int leerInt() {
        while (!sc.hasNextInt()) {
            System.out.print("  Ingresa un número válido: ");
            sc.next();
        }
        int v = sc.nextInt();
        sc.nextLine();
        return v;
    }

    public Scanner getSc() { return sc; }
}