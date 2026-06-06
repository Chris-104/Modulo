package proyecto_modulo;

import java.util.Scanner;

public class Tienda {
	// Clase que maneja la tienda del juego
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

    // El catálogo general se mantiene exactamente como lo tenían
    private static final Producto[] PRODUCTOS = {
        new Producto("Comida Premium",   "🥩", 15, "hambre-40,felicidad+10"),
        new Producto("Vitaminas",        "💊",  20, "salud+30"),
        new Producto("Juguete Pelota",   "🎾",  10, "felicidad+25,energia-10"),
        new Producto("Champú Especial",  "🧴",  12, "higiene+50"),
        new Producto("Cama Cómoda",      "🛏️",  25, "energia+40"),
        new Producto("Snack Energético", "🍬",   8, "energia+20,hambre-10")
    };

    // Las comidas especiales de tus compañeros no se tocan en lo absoluto
    private static Producto[] obtenerComidas(Mascota mascota) {
        switch(mascota.getTipo().toLowerCase()) {
            case "perro":
                return new Producto[] {
                    new Producto("Hueso Gigante", "🦴", 10, "hambre-25,felicidad+5"),
                    new Producto("Croquetas Premium", "🥩", 15, "hambre-40,felicidad+10"),
                    new Producto("Carne Asada", "🍖", 20, "hambre-60,felicidad+15")
                };
            case "gato":
                return new Producto[] {
                    new Producto("Atún", "🐟", 10, "hambre-25,felicidad+5"),
                    new Producto("Pescado Fresco", "🐠", 15, "hambre-40,felicidad+10"),
                    new Producto("Leche Especial", "🥛", 20, "hambre-60,felicidad+15")
                };
            case "conejo":
                return new Producto[] {
                    new Producto("Zanahoria", "🥕", 10, "hambre-25"),
                    new Producto("Lechuga", "🥬", 15, "hambre-40"),
                    new Producto("Ensalada Deluxe", "🥗", 20, "hambre-60,felicidad+10")
                };
            case "panda":
                return new Producto[] {
                    new Producto("Bambú Fresco", "🎋", 10, "hambre-25"),
                    new Producto("Bambú Premium", "🎍", 15, "hambre-40"),
                    new Producto("Pastel de Bambú", "🍰", 25, "hambre-60,felicidad+15")
                };
            case "dragón":
            case "dragon":
                return new Producto[] {
                    new Producto("Carne Gigante", "🍖", 25, "hambre-40"),
                    new Producto("Lava Mágica", "🔥", 35, "hambre-60,energia+20"),
                    new Producto("Cristal de Fuego", "💎", 50, "hambre-80,felicidad+20")
                };
            case "zorro":
                return new Producto[] {
                    new Producto("Pollo", "🍗", 10, "hambre-25"),
                    new Producto("Conejo Salvaje", "🥩", 20, "hambre-50"),
                    new Producto("Banquete del Bosque", "🍖", 30, "hambre-70,felicidad+15")
                };
            case "lobo":
                return new Producto[] {
                    new Producto("Carne Cruda", "🥩", 15, "hambre-30"),
                    new Producto("Costillas", "🍖", 25, "hambre-55"),
                    new Producto("Banquete Alfa", "🍗", 40, "hambre-80,felicidad+20")
                };
            default:
                return new Producto[] {
                    new Producto("Comida Universal", "🍎", 15, "hambre-40")
                };
        }
    }

    public static void mostrar(Mascota mascota, Scanner sc) {
        boolean enTienda = true;
        
        while (enTienda) {
            Producto[] productos = obtenerComidas(mascota);

            System.out.println(Colores.CIAN + "======================================" + Colores.RESET);
            System.out.println(Colores.MORADO + Colores.NEGRITA + "        🏪 TIENDA DEL JUEGO        " + Colores.RESET);
            System.out.println(Colores.CIAN + "======================================" + Colores.RESET);

            System.out.println(Colores.AMARILLO + "💰 Dinero: $" + mascota.getStats().getDinero() + Colores.RESET);
            
            // Muestra las comidas exactamente como estaban antes
            for (int i = 0; i < productos.length; i++) {
                Producto p = productos[i];
                System.out.println(Colores.VERDE + "[" + (i + 1) + "] " + p.emoji + " " + p.nombre + Colores.RESET);
                System.out.println(Colores.AZUL + "   💲 Precio: $" + p.precio + Colores.RESET);
                System.out.println(Colores.MORADO + "   ✨ Efecto: " + p.efecto + Colores.RESET);
                System.out.println(Colores.CIAN + "----------------------------------" + Colores.RESET);
            }
            
            // Opción añadida al final de la lista de comida de forma segura
            int opcionAccesorios = productos.length + 1; // Dinámicamente se vuelve la opción [4]
            System.out.println(Colores.AMARILLO + "[" + opcionAccesorios + "] 🦮 Ir a la sección de accesorios" + Colores.RESET);
            System.out.println(Colores.CIAN + "----------------------------------" + Colores.RESET);
            
            System.out.println(Colores.ROJO + "[0] 🚪 Salir de la tienda" + Colores.RESET);
            System.out.print(Colores.AZUL + "🎮 Elige una opción: " + Colores.RESET);
            
            int op = leerInt(sc);

            if (op == 0) {
                enTienda = false;
            }
            // Si elige el 4, salta a tus accesorios exclusivos
            else if (op == opcionAccesorios) {
                mostrarMenuAccesorios(mascota, sc);
            }
            // Si elige 1, 2 o 3, ejecuta la compra original intacta
            else if (op >= 1 && op <= productos.length) {
                comprar(mascota, productos[op - 1], sc);
            }
            else {
                System.out.println("Opción inválida");
            }
        }
    }

    private static void comprar(Mascota mascota, Producto p, Scanner sc) {
        if (mascota.getStats().getDinero() < p.precio) {
            System.out.println("  💸 No tienes suficiente dinero. Necesitas $" + p.precio);
            System.out.print("  👉 Presiona Enter para continuar...");
            sc.nextLine();
            sc.nextLine(); 
            return;
        }
        
        mascota.getStats().setDinero(mascota.getStats().getDinero() - p.precio);
        aplicarEfecto(mascota, p.efecto);
        
        System.out.println("  ✅ Compraste " + p.emoji + " " + p.nombre +
                " por $" + p.precio + ". ¡Aplicado a " + mascota.getNombre() + "!");
                
        System.out.print("  👉 Presiona Enter para continuar...");
        sc.nextLine();
        sc.nextLine(); 
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

    // ====================================================================
    // 🦮 SECCIÓN APARTE DE ACCESORIOS (MÁXIMO $25 Y EFECTOS MULTIPLES)
    // ====================================================================
    private static Producto[] obtenerAccesorios(Mascota mascota) {
        switch(mascota.getTipo().toLowerCase()) {
            case "perro":
                return new Producto[] {
                    new Producto("Collar de Cuero Especial", "🦮", 25, "higiene+20,salud+20,energia+30"),
                    new Producto("Hueso de Goma",            "🦴", 15, "energia+25"),
                    new Producto("Pañuelo Rojo",             "🧣", 10, "felicidad+20"),
                    new Producto("Placa Dorada",             "🥇", 20, "salud+30")
                };
            case "gato":
                return new Producto[] {
                    new Producto("Cascabel Mágico",   "🔔", 25, "higiene+20,salud+20,energia+30"),
                    new Producto("Ratón de Juguete",  "🐭", 12, "energia+20"),
                    new Producto("Corona de Lana",    "👑", 22, "felicidad+30"),
                    new Producto("Lentes Cool",       "🕶️", 18, "felicidad+20,energia+10")
                };
            case "conejo":
                return new Producto[] {
                    new Producto("Amuleto de Pata Real", "🍀", 25, "higiene+20,salud+20,energia+30"),
                    new Producto("Zanahoria Suprema",    "🥕", 15, "energia+25"),
                    new Producto("Moño Elegante",        "🎀", 10, "felicidad+15"),
                    new Producto("Sombrero de Mago",     "🎩", 20, "felicidad+30")
                };
            case "panda":
                return new Producto[] {
                    new Producto("Chaleco Imperial",  "🦺", 25, "higiene+20,salud+20,energia+30"),
                    new Producto("Brote Energético",  "🌱", 15, "energia+20"),
                    new Producto("Abanico Oriental",  "🪭", 12, "felicidad+20"),
                    new Producto("Medalla de Honor",  "🏅", 22, "salud+30")
                };
            case "dragón":
            case "dragon":
                return new Producto[] {
                    new Producto("Collar de Fuego Supremo", "🔥", 25, "higiene+20,salud+20,energia+30"),
                    new Producto("Armadura Escamas",        "🛡️", 22, "salud+25"),
                    new Producto("Alas de Acero",           "🪽", 18, "energia+20,felicidad+10"),
                    new Producto("Corona de Lava",          "👑", 24, "salud+30")
                };
            case "zorro":
                return new Producto[] {
                    new Producto("Capa Mística",      "🧣", 25, "higiene+20,salud+20,energia+30"),
                    new Producto("Baya Mística",      "🍒", 15, "energia+25"),
                    new Producto("Máscara Ancestral", "🎭", 20, "felicidad+30"),
                    new Producto("Anillo de Suerte",  "💍", 22, "salud+15,energia+15")
                };
            case "lobo":
                return new Producto[] {
                    new Producto("Colmillo Alfa Runas", "🦷", 25, "higiene+20,salud+20,energia+30"),
                    new Producto("Elíxir Lunar",       "🧪", 15, "energia+25"),
                    new Producto("Manto de Piel",      "🧥", 20, "salud+25"),
                    new Producto("Garra de Obsidiana",  "🐾", 22, "energia+30")
                };
            default:
                return new Producto[] {};
        }
    }

    private static void mostrarMenuAccesorios(Mascota mascota, Scanner sc) {
        boolean enMenu = true;
        while (enMenu) {
            Producto[] accesorios = obtenerAccesorios(mascota);

            System.out.println(Colores.CIAN + "======================================" + Colores.RESET);
            System.out.println(Colores.MORADO + Colores.NEGRITA + "       🦮 ACCESORIOS PARA TU MASCOTA      " + Colores.RESET);
            System.out.println(Colores.CIAN + "======================================" + Colores.RESET);
            System.out.println(Colores.AMARILLO + "💰 Dinero: $" + mascota.getStats().getDinero() + Colores.RESET);

            if (accesorios.length == 0) {
                System.out.println("❌ Esta mascota no tiene accesorios disponibles.");
                System.out.println(Colores.ROJO + "[0] 🚪 Volver a la sección de comida" + Colores.RESET);
                int op = leerInt(sc);
                enMenu = false;
                break;
            }

            for (int i = 0; i < accesorios.length; i++) {
                Producto p = accesorios[i];
                System.out.println(Colores.VERDE + "[" + (i + 1) + "] " + p.emoji + " " + p.nombre + Colores.RESET);
                System.out.println(Colores.AZUL + "   💲 Precio: $" + p.precio + Colores.RESET);
                System.out.println(Colores.MORADO + "   ✨ Efecto: " + p.efecto + Colores.RESET);
                System.out.println(Colores.CIAN + "----------------------------------" + Colores.RESET);
            }
            System.out.println(Colores.ROJO + "[0] 🚪 Volver a la sección de comida" + Colores.RESET);
            System.out.print(Colores.AZUL + "🎮 Elige un accesorio para comprar: " + Colores.RESET);

            int op = leerInt(sc);

            if (op == 0) {
                enMenu = false; 
            } else if (op >= 1 && op <= accesorios.length) {
                comprar(mascota, accesorios[op - 1], sc);
            } else {
                System.out.println("Opción inválida");
            }
        }
    }
}
