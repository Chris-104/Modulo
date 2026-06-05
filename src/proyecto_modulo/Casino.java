package proyecto_modulo;

import java.util.Random;
import java.util.Scanner;

public class Casino {
	private static int recordGanancia = 0;
	public static final String ROJO = "\u001B[31m";
	public static final String VERDE = "\u001B[32m";
	public static final String AMARILLO = "\u001B[33m";
	public static final String AZUL = "\u001B[34m";
	public static final String MORADO = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String BLANCO = "\u001B[37m";
	public static final String RESET = "\u001B[0m";
    public static void jugar(Mascota mascota, Scanner sc) {

        Random random = new Random();

        System.out.println(MORADO +
        		"\n====================================");
        		System.out.println(
        		"      🎰 CASINO DE MASCOTAS 🎰");
        		System.out.println(
        		"===================================="
        		+ RESET);

        System.out.println("💰 Dinero disponible: $" +
                mascota.getStats().getDinero());

        System.out.println("\n🎯 Selecciona una dificultad:\n");

        System.out.println(VERDE +
                "1. 🟢 Fácil    [1 - 3]    x1.5"
                + RESET);

        System.out.println(AMARILLO +
                "2. 🟢 Medio    [1 - 5]    x2"
                + RESET);

        System.out.println(ROJO +
                "3. 🔴 Extremo  [1 - 10]   x5"
                + RESET);

        System.out.print("\n👉 Opción: ");
        int opcion = Integer.parseInt(sc.nextLine());

        int limite = 0;
        double multiplicador = 0;

        switch(opcion) {

            case 1:
                limite = 3;
                multiplicador = 1.5;
                break;

            case 2:
                limite = 5;
                multiplicador = 2;
                break;

            case 3:
                limite = 10;
                multiplicador = 5;
                break;

            default:
                System.out.println("❌ Opción inválida.");
                return;
        
        }

        System.out.print("\n💸 ¿Cuánto deseas apostar?: ");
        int apuesta = Integer.parseInt(sc.nextLine());

        if(apuesta <= 0) {
            System.out.println("❌ La apuesta debe ser mayor que 0.");
            return;
        }

        if(apuesta > mascota.getStats().getDinero()) {
            System.out.println("❌ No tienes suficiente dinero.");
            return;
        }

        System.out.print("🎲 Adivina un número del 1 al " + limite + ": ");
        int numeroUsuario = Integer.parseInt(sc.nextLine());

        int numeroCorrecto = random.nextInt(limite) + 1;
        System.out.print("\n🎰 Girando ruleta");

        for(int i = 0; i < 5; i++) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print(".");
        }

        System.out.println();		

        System.out.println("\n🎯 Número correcto: " + numeroCorrecto);

        if(numeroUsuario == numeroCorrecto) {

            int premio = (int)(apuesta * multiplicador);
            if(premio > recordGanancia) {

                recordGanancia = premio;

                System.out.println(
                    AMARILLO +
                    "🏆 ¡NUEVO RECORD DE GANANCIA!" +
                    RESET);

                System.out.println(
                    "💰 Record actual: $" +
                    recordGanancia);
            }
            mascota.getStats().setDinero(
                    mascota.getStats().getDinero()
                    - apuesta
                    + premio);

            System.out.println("🎉 ¡GANASTE!");
            System.out.println("💰 Premio: $" + premio);

        } else {

            mascota.getStats().setDinero(
                    mascota.getStats().getDinero()
                    - apuesta);

            System.out.println("💀 Perdiste la apuesta.");
        }

        System.out.println(
                "💵 Dinero actual: $" +
                mascota.getStats().getDinero());
    }
}