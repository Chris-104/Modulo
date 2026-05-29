package proyecto_modulo;

import java.util.Scanner;

// Clase que maneja la tienda del juego
public class Tienda {

    // Clase interna para representar un producto
    static class Producto {
        String nombre;
        String emoji;
        int precio;
        String efecto;

        Producto(String nombre, String emoji, int precio, String efecto) {
            this.nombre  = nombre;
            this.emoji   = emoji;
            this.precio  = precio;
            this.efecto  = efecto;
        }
    }

    private static final Producto[] PRODUCTOS = {
        new Producto("Comida Premium",   "🥩", 15, "hambre-40,felicidad+10"),
        new Producto("Vitaminas",        "💊",  20, "salud+30"),
        new Producto("Juguete Pelota",   "🎾",  10, "felicidad+25,energia-10"),
        new Producto("Champú Especial",  "🧴",  12, "higiene+50"),
        new Producto("Cama Cómoda",      "🛏️",  25, "energia+40"),
        new Producto("Snack Energético", "🍬",   8, "energia+20,hambre-10"),
    };

    public static void mostrar(Mascota mascota, Scanner sc) {
        boolean enTienda = true;

        while (enTienda) {
            System.out.println("\n ===========================================");
            System.out.println("              🏪 TIENDA VIRTUAL               ");
            System.out.printf ("            💰 Tu dinero: $%-5d              %n",
                    mascota.getStats().getDinero());
            System.out.println("   ===========================================");

            for (int i = 0; i < PRODUCTOS.length; i++) {
                Producto p = PRODUCTOS[i];
                System.out.printf("     %d. %s %-18s  $%d%n",
                        i + 1, p.emoji, p.nombre, p.precio);
                System.out.printf("        Efecto: %-30s  %n", p.efecto);
                System.out.println("                                          ");
            }
            System.out.println("     0. Salir de la tienda                    ");
            System.out.println("   ===========================================");
            System.out.print("  👉 ¿Qué deseas comprar? ");

            int op = leerInt(sc);
            if (op == 0) {
                enTienda = false;
            } else if (op >= 1 && op <= PRODUCTOS.length) {
                comprar(mascota, PRODUCTOS[op - 1],sc);
            } else {
                System.out.println("  ❌ Opción no válida.");
            }
        }
    }

    private static void comprar(Mascota mascota, Producto p, Scanner sc) {
    	if (mascota.getStats().getDinero() < p.precio) {
            System.out.println("  💸 No tienes suficiente dinero. Necesitas $" + p.precio);
            System.out.print("  👉 Presiona Enter para continuar...");
            sc.nextLine(); // Pausa para que no se borre el mensaje
            return;
        }
        
        mascota.getStats().setDinero(mascota.getStats().getDinero() - p.precio);
        aplicarEfecto(mascota, p.efecto);
        
        System.out.println("  ✅ Compraste " + p.emoji + " " + p.nombre +
                " por $" + p.precio + ". ¡Aplicado a " + mascota.getNombre() + "!");
                
        System.out.print("  👉 Presiona Enter para continuar...");
        sc.nextLine(); // Pausa para el mensaje de éxito
    }
    private static void aplicarEfecto(Mascota mascota, String efecto) {
        String[] partes = efecto.split(",");
        Estadisticas s = mascota.getStats();
        for (String parte : partes) {
            if      (parte.startsWith("hambre"))    aplicar(s, "hambre",    parte);
            else if (parte.startsWith("energia"))   aplicar(s, "energia",   parte);
            else if (parte.startsWith("felicidad")) aplicar(s, "felicidad", parte);
            else if (parte.startsWith("salud"))     aplicar(s, "salud",     parte);
            else if (parte.startsWith("higiene"))   aplicar(s, "higiene",   parte);
        }
    }

    private static void aplicar(Estadisticas s, String stat, String expresion) {
        boolean suma = expresion.contains("+");
        int valor = Integer.parseInt(expresion.replaceAll("[^0-9]", ""));
        int actual = getStat(s, stat);
        int nuevo  = suma ? actual + valor : actual - valor;
        setStat(s, stat, nuevo);
    }

    private static int getStat(Estadisticas s, String stat) {
        switch(stat) {
            case "hambre":    return s.getHambre();
            case "energia":   return s.getEnergia();
            case "felicidad": return s.getFelicidad();
            case "salud":     return s.getSalud();
            case "higiene":   return s.getHigiene();
            default:          return 0;
        }
    }

    private static void setStat(Estadisticas s, String stat, int val) {
        switch(stat) {
            case "hambre":    s.setHambre(val);    break;
            case "energia":   s.setEnergia(val);   break;
            case "felicidad": s.setFelicidad(val); break;
            case "salud":     s.setSalud(val);     break;
            case "higiene":   s.setHigiene(val);   break;
        }
    }

    private static int leerInt(Scanner sc) {
        while (!sc.hasNextInt()) { sc.next(); }
        int v = sc.nextInt();
        sc.nextLine();
        return v;
    }
}