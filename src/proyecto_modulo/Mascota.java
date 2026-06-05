package proyecto_modulo;
 
public class Mascota {
	public static final String ROJO = "\u001B[31m";
	public static final String VERDE = "\u001B[32m";
	public static final String AMARILLO = "\u001B[33m";
	public static final String AZUL = "\u001B[34m";
	public static final String MORADO = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String BLANCO = "\u001B[37m";
	public static final String RESET1 = "\u001B[0m";
	public static final String NEGRO = "\u001B[30m";
	
	 
    private String nombre;
    private String tipo;
    private String emoji;
    private boolean dormida;
    private boolean enferma;
    private Estadisticas stats;
    private int turno;
 
    public Mascota(String nombre, String tipo) {
        this.nombre  = nombre;
        this.tipo    = tipo;
        this.emoji   = asignarEmoji(tipo);
        this.dormida = false;
        this.enferma = false;
        this.stats   = new Estadisticas();
        this.turno   = 0;
    }
 
    private String asignarEmoji(String tipo) {
        switch (tipo.toLowerCase()) {
            case "perro":           return "🐶";
            case "gato":            return "🐱";
            case "dragon":
            case "dragón":          return "🐲";
            case "conejo":          return "🐰";
            case "panda":           return "🐼";
            case "zorro":           return "🦊";
            case "lobo":            return "🐺";
            default:                return "🐾";
        }
    }

 
    /*
     * COHERENCIA - alimentar()
     * ========================
     * APLICADO: Comer ahora baja higiene -2 (ensucia levemente el plato).
     * Esto hace que el bano sea mas necesario y equilibra todas las stats.
     */
    public String alimentar() {
        if (dormida) return "  😴 " + nombre + " está dormido/a, no puede comer.";
        if (stats.getHambre() <= 5)
            return "  😊 " + nombre + " ya está lleno/a.";

        Reproductor_sonidos.reproducirEfecto(Reproductor_sonidos.SFX_COMER);
 
        stats.setHambre(stats.getHambre() - 35);
        stats.setFelicidad(stats.getFelicidad() + 10);
        stats.setHigiene(stats.getHigiene() - 2);  // CAMBIO: comer ensucia levemente
        boolean subioNivel = stats.ganarExperiencia(15);
        avanzarTurno();
        String msg = "  🍖 ¡" + nombre + " comió con mucho gusto! (+15 XP)";
        if (subioNivel) msg += nivelUpMsg();
        return msg;
    }
 
    /*
     * COHERENCIA - jugar()
     * ====================
     * APLICADO: Jugar ahora baja higiene -10 (correr y saltar ensucian).
     * Esto equilibra el metodo para que no sea siempre la mejor opcion.
     */
    public String jugar() {
        if (dormida) return "  😴 " + nombre + " está durmiendo.";
        if (stats.getEnergia() < 17)
            return "  😓 " + nombre + " está muy cansado/a para jugar.";
        if (enferma)
            return "  🤒 " + nombre + " está enfermo/a, no puede jugar.";
  
        stats.setEnergia(stats.getEnergia() - 20);
        stats.setHambre(stats.getHambre() + 10);
        stats.setHigiene(stats.getHigiene() - 7);  // CAMBIO: jugar ensucia por el esfuerzo fisico
        stats.setFelicidad(stats.getFelicidad() + 30);
        boolean subioNivel = stats.ganarExperiencia(25);
        avanzarTurno();
        String msg = "  🎾 ¡" + nombre + " jugó y está muy feliz! (+25 XP)";
        if (subioNivel) msg += nivelUpMsg();
        return msg;
    }
 
    /*
     * COHERENCIA - dormir()
     * =====================
     * Al dormir se pone dormida=true antes de avanzarTurno(), ejecutando
     * la rama 'dormida=true' de pasarTurno() donde:
     *   - energia sube (+15)  -> descansar recupera energia
     *   - hambre sube (+2)    -> metabolismo bajo
     *   - felicidad NO baja   -> no se pone triste durmiendo
     *   - higiene NO baja     -> no se ensucia al dormir
     *   - salud sube (+5)     -> descansar profundo recupera salud
     */
    public String dormir(int horas) {
        if (dormida) return "  😴 " + nombre + " ya está durmiendo...";

        Reproductor_sonidos.reproducirEfecto(Reproductor_sonidos.SFX_DUERMA);

        // Sistema de tiempo real: 2 segundos = 1 hora del juego
        long tiempoEspera = horas * 2000L;
        System.out.println("  🌙 " + nombre + " se fue a dormir...");
        System.out.println("  ⏰ Durmiendo " + horas + " horas (" + (tiempoEspera / 1000) + " segundos reales)...");

        // Cuenta regresiva en tiempo real
        int segundosTotales = (int) (tiempoEspera / 1000);
        for (int i = segundosTotales; i > 0; i--) {
            System.out.print("\r  ⏰ Tiempo restante: " + i + " segundos...     ");
            System.out.flush();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "  ⏰ " + nombre + " fue despertado antes de tiempo.";
            }
        }
        System.out.println(); // Salto de linea despues de la cuenta regresiva

        // Recuperacion proporcional a las horas dormidas:
        // 10% energia por hora, 5% salud por hora
        // NOTA: La hambre NO cambia durante el sueno (pedido del usuario)
        int energiaRecuperada = Math.min(100 - stats.getEnergia(), horas * 10);
        int saludRecuperada = Math.min(100 - stats.getSalud(), horas * 5);

        stats.setEnergia(stats.getEnergia() + energiaRecuperada);
        stats.setSalud(stats.getSalud() + saludRecuperada);
        // No se baja felicidad, higiene ni hambre mientras duerme (coherente)

        // Verificar si murio durante el sueno por hambre extrema
        if (!stats.estaViva()) {
            Reproductor_sonidos.reproducirEfecto(Reproductor_sonidos.SFX_MUERTE);
            Reproductor_sonidos.detenerMusicaFondo();
            return "  💀 " + nombre + " no resistio el sueno profundo...";
        }

        // Despertar automaticamente al terminar el tiempo
        dormida = false;
        stats.incrementarDias();
        return "  ☀️ ¡" + nombre + " se despertó automaticamente! (Día " + stats.getDiasVividos() + ") Recuperó +" + energiaRecuperada + "% de energía.";
    }
 
    /*
     * ANALISIS DE COHERENCIA - despertar()
     * =====================================
     * APLICADO: Se elimino la llamada a avanzarTurno() que existia aqui.
     * Antes, al despertar se ejecutaba pasarTurno(dormida=false), lo que
     * inmediatamente bajaba energia (-4), subia hambre (+6), bajaba
     * felicidad (-4) e higiene (-4), arruinando el beneficio del descanso.
     * Ahora el despertar es una transicion sin costo de turno.
     * El costo de estar despierto se aplica en la siguiente accion.
     */
    public String despertar() {
        if (!dormida) return "  ☀️  " + nombre + " ya está despierto/a.";
        dormida = false;
        stats.incrementarDias();
       
        return "  ☀️  ¡" + nombre + " se despertó! ¡Buenos días! (Día " + stats.getDiasVividos() + ")";
    }
 
    /*
     * COHERENCIA - bañar()
     * ====================
     * APLICADO: La felicidad ahora es condicional segun la higiene previa.
     * Si estaba muy sucio (higiene < 30): felicidad +15 (alivio mayor).
     * Si estaba limpio: felicidad +5 (beneficio normal).
     * Se guarda el valor de higiene ANTES de ponerlo a 100.
     */
    public String bañar() {
        if (dormida) return "  😴 " + nombre + " está durmiendo.";

        Reproductor_sonidos.reproducirEfecto(Reproductor_sonidos.SFX_BAÑAR);
  
        int higieneAntes = stats.getHigiene();  // CAMBIO: guardamos valor antes de limpiar
        stats.setHigiene(100);
        stats.setSalud(Math.min(100, stats.getSalud() + 10));
        if (higieneAntes < 30) {
            stats.setFelicidad(stats.getFelicidad() + 15);  // CAMBIO: mas felicidad si estaba muy sucio
        } else {
            stats.setFelicidad(stats.getFelicidad() + 5);
        }
        boolean subioNivel = stats.ganarExperiencia(10);
        avanzarTurno();
        String msg = "  🛁 ¡" + nombre + " está limpio/a y fresco/a! (+10 XP)";
        if (subioNivel) msg += nivelUpMsg();
        return msg;
    }
 
    public String medicar() {
        if (!enferma) return "  💊 " + nombre + " no está enfermo/a.";
        if (stats.getDinero() < 20)
            return "  💸 No tienes suficiente dinero. Necesitas $20.";
 
        stats.setDinero(stats.getDinero() - 20);
        stats.setSalud(Math.min(100, stats.getSalud() + 40));
        enferma = false;
        boolean subioNivel = stats.ganarExperiencia(20);
        avanzarTurno();
        String msg = "  💊 ¡" + nombre + " tomó su medicina y se recupera! (+20 XP)";
        if (subioNivel) msg += nivelUpMsg();
        return msg;
    }
 
    /*
     * COHERENCIA - trabajar()
     * =======================
     * APLICADO: Trabajar ahora baja higiene -8 (esfuerzo laboral ensucia).
     * El calculo de ganancia basado en nivel se mantiene.
     * Esto equilibra la accion con jugar, que tambien ensucia.
     */
    public String trabajar() {
        if (dormida) return "  😴 " + nombre + " está durmiendo.";
        if (stats.getEnergia() < 30)
            return "  😓 " + nombre + " está muy cansado/a para trabajar.";
  
        int ganancia = 10 + (stats.getNivel() * 5);
        stats.setDinero(stats.getDinero() + ganancia);
        stats.setEnergia(stats.getEnergia() - 20);
        stats.setFelicidad(stats.getFelicidad() - 5);
        stats.setHigiene(stats.getHigiene() - 6);  // CAMBIO: trabajar ensucia por el esfuerzo
        boolean subioNivel = stats.ganarExperiencia(20);
        avanzarTurno();
        String msg = "  💼 " + nombre + " trabajó y ganó $" + ganancia + "! (+20 XP)";
        if (subioNivel) msg += nivelUpMsg();
        return msg;
    }

    /*
     * COHERENCIA - avanzarTurno()
     * ===========================
     * Eje central del paso del tiempo. Llama a stats.pasarTurno(dormida).
     *
     * APLICADO: Segunda condicion de enfermedad por hambre extrema.
     *   - 10% chance si higiene <= 30 (original).
     *   - 15% chance si hambre >= 90 (nuevo).
     * Esto hace que alimentar sea tan critico como banar.
     */
    private void avanzarTurno() {
        turno++;
        stats.pasarTurno(dormida);
 
        // 10% de chance de enfermarse si higiene baja
        if (!enferma && stats.getHigiene() <= 30 && Math.random() < 0.10) {
            enferma = true;
        }

        // CAMBIO: 15% de chance de enfermarse si hambre es extrema
        if (!enferma && stats.getHambre() >= 90 && Math.random() < 0.15) {
            enferma = true;
        }

        if (!stats.estaViva()) {
            Reproductor_sonidos.reproducirEfecto(Reproductor_sonidos.SFX_MUERTE);
            Reproductor_sonidos.detenerMusicaFondo();
        }
    }
 
    private String nivelUpMsg() {
        return "\n  ⭐ ¡¡SUBISTE AL NIVEL " + stats.getNivel() + "!! ¡Felicidades!";
    }

   
    		public void mostrarEstado() {

    		    EstadoMascota estado = stats.getEstado(dormida);

    		    System.out.println();

    		    
				System.out.println(AZUL + "===========================================" + AZUL);

    		    System.out.println(BLANCO +
    		            "      " + emoji + " " + nombre +
    		            " | Dia " + stats.getDiasVividos() +
    		            " | Nivel " + stats.getNivel()
    		            + AZUL);

    		    System.out.println(AZUL + "===========================================" + AZUL);

    		    System.out.println(CYAN +
    		            "Estado: " + estado.getDescripcion()
    		            + AZUL);

    		    System.out.println(AMARILLO +
    		            "💰 Dinero: $" + stats.getDinero()
    		            + AZUL);

    		    System.out.println(VERDE +
    		            "⭐ XP: " + stats.getExperiencia()
    		            + "/" + stats.getXpNecesaria()
    		            + AZUL);

    		    System.out.println(AZUL + "===========================================" + AZUL);

    		    System.out.println(ROJO +
    		            "🍗 Hambre    : " +
    		            barra(stats.getHambre(), true)
    		            + AZUL);

    		    System.out.println(AMARILLO +
    		            "⚡ Energia   : " +
    		            barra(stats.getEnergia(), false)
    		            + AZUL);

    		    System.out.println(MORADO +
    		            "😊 Felicidad : " +
    		            barra(stats.getFelicidad(), false)
    		            + AZUL);

    		    System.out.println(CYAN +
    		            "❤️ Salud     : " +
    		            barra(stats.getSalud(), false)
    		            + AZUL);

    		    System.out.println(VERDE +
    		            "🛁 Higiene   : " +
    		            barra(stats.getHigiene(), false)
    		            + AZUL);

    		    if (enferma) {
    		        System.out.println(ROJO +
    		                "\n⚠️ ¡Tu mascota esta enferma! Usa medicina."
    		                + AZUL);
    		    }

    		    System.out.println(AZUL + "===========================================" + AZUL);
    		}
    		

 
    private String barra(int valor, boolean invertido) {
        int bloques = valor / 10;
        String lleno = invertido ? "🟥" : "🟩";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++)
            sb.append(i < bloques ? lleno : "⬜");
        sb.append(String.format(" %3d%%", valor));
        return sb.toString();
    }

    public String getNombre()       { return nombre; }
    public String getTipo()         { return tipo; }
    public String getEmoji()        { return emoji; }
    public boolean isDormida()      { return dormida; }
    public boolean isEnferma()      { return enferma; }
    public Estadisticas getStats()  { return stats; }
    public boolean estaViva()       { return stats.estaViva(); }
}

