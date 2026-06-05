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

    private static final Producto[] PRODUCTOS = {
        new Producto("Comida Premium",   "🥩", 15, "hambre-40,felicidad+10"),
        new Producto("Vitaminas",        "💊",  20, "salud+30"),
        new Producto("Juguete Pelota",   "🎾",  10, "felicidad+25,energia-10"),
        new Producto("Champú Especial",  "🧴",  12, "higiene+50"),
        new Producto("Cama Cómoda",      "🛏️",  25, "energia+40"),
        new Producto("Snack Energético", "🍬",   8, "energia+20,hambre-10")
    };
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

        	System.out.println(Colores.AMARILLO + "💰 Dinero: $" 
        	        + mascota.getStats().getDinero() + Colores.RESET);
        	for (int i = 0; i < productos.length; i++) {

        	    Producto p = productos[i];

        	    System.out.println(
        	        Colores.VERDE + "[" + (i + 1) + "] " + p.emoji + " " + p.nombre 
        	        + Colores.RESET
        	    );

        	    System.out.println(
        	        Colores.AZUL + "   💲 Precio: $" + p.precio + Colores.RESET
        	    );

        	    System.out.println(
        	        Colores.MORADO + "   ✨ Efecto: " + p.efecto + Colores.RESET
        	    );

        	    System.out.println(Colores.CIAN + "----------------------------------" + Colores.RESET);
        	}
        	System.out.println(Colores.ROJO + "[0] 🚪 Salir de la tienda" + Colores.RESET);

        	System.out.print(Colores.AZUL + "🎮 Elige una opción: " + Colores.RESET);
            int op = leerInt(sc);

            if (op == 0) {
                enTienda = false;
            }
            else if (op >= 1 && op <= productos.length) {
                comprar(mascota, productos[op - 1], sc);
            }
            else {
                System.out.println("Opción inválida");
            }
        }
    }

        // 🔴 AQUÍ TERMINA EL MÉTODO

    

    private static void comprar(Mascota mascota, Producto p, Scanner sc) {
    	if (mascota.getStats().getDinero() < p.precio) {
            System.out.println("  💸 No tienes suficiente dinero. Necesitas $" + p.precio);
            System.out.print("  👉 Presiona Enter para continuar...");

            sc.nextLine();

            sc.nextLine(); // Pausa para que no se borre el mensaje

            return;
        }
        
        mascota.getStats().setDinero(mascota.getStats().getDinero() - p.precio);
        aplicarEfecto(mascota, p.efecto);
        
        System.out.println("  ✅ Compraste " + p.emoji + " " + p.nombre +
                " por $" + p.precio + ". ¡Aplicado a " + mascota.getNombre() + "!");
                
        System.out.print("  👉 Presiona Enter para continuar...");

        sc.nextLine();

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