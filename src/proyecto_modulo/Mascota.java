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

 
    public String alimentar() {
        if (dormida) return "  😴 " + nombre + " está dormido/a, no puede comer.";
        if (stats.getHambre() <= 5)
            return "  😊 " + nombre + " ya está lleno/a.";

        Reproductor_sonidos.reproducirEfecto(Reproductor_sonidos.SFX_COMER);
 
        stats.setHambre(stats.getHambre() - 35);
        stats.setFelicidad(stats.getFelicidad() + 10);
        boolean subioNivel = stats.ganarExperiencia(15);
        avanzarTurno();
        String msg = "  🍖 ¡" + nombre + " comió con mucho gusto! (+15 XP)";
        if (subioNivel) msg += nivelUpMsg();
        return msg;
    }
 
    public String jugar() {
        if (dormida) return "  😴 " + nombre + " está durmiendo.";
        if (stats.getEnergia() < 17)
            return "  😓 " + nombre + " está muy cansado/a para jugar.";
        if (enferma)
            return "  🤒 " + nombre + " está enfermo/a, no puede jugar.";
 
        stats.setEnergia(stats.getEnergia() - 25);
        stats.setHambre(stats.getHambre() + 10);
        stats.setFelicidad(stats.getFelicidad() + 30);
        boolean subioNivel = stats.ganarExperiencia(25);
        avanzarTurno();
        String msg = "  🎾 ¡" + nombre + " jugó y está muy feliz! (+25 XP)";
        if (subioNivel) msg += nivelUpMsg();
        return msg;
    }
 
    public String dormir() {
        if (dormida) return "  😴 " + nombre + " ya está durmiendo...";

        Reproductor_sonidos.reproducirEfecto(Reproductor_sonidos.SFX_DUERMA);
 
        dormida = true;
        avanzarTurno();
        return "  🌙 " + nombre + " se fue a dormir dulcemente. ¡Buenas noches!";
    }
 
    public String despertar() {
        if (!dormida) return "  ☀️  " + nombre + " ya está despierto/a.";
        dormida = false;
        stats.incrementarDias();
        avanzarTurno();
        return "  ☀️  ¡" + nombre + " se despertó! ¡Buenos días! (Día " + stats.getDiasVividos() + ")";
    }
 
    public String bañar() {
        if (dormida) return "  😴 " + nombre + " está durmiendo.";

        Reproductor_sonidos.reproducirEfecto(Reproductor_sonidos.SFX_BAÑAR);
 
        stats.setHigiene(100);
        stats.setSalud(Math.min(100, stats.getSalud() + 10));
        stats.setFelicidad(stats.getFelicidad() + 5);
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
 
    public String trabajar() {
        if (dormida) return "  😴 " + nombre + " está durmiendo.";
        if (stats.getEnergia() < 30)
            return "  😓 " + nombre + " está muy cansado/a para trabajar.";
 
        int ganancia = 10 + (stats.getNivel() * 5);
        stats.setDinero(stats.getDinero() + ganancia);
        stats.setEnergia(stats.getEnergia() - 20);
        stats.setFelicidad(stats.getFelicidad() - 5);
        boolean subioNivel = stats.ganarExperiencia(20);
        avanzarTurno();
        String msg = "  💼 " + nombre + " trabajó y ganó $" + ganancia + "! (+20 XP)";
        if (subioNivel) msg += nivelUpMsg();
        return msg;
    }

    private void avanzarTurno() {
        turno++;
        stats.pasarTurno(dormida);
 
        // 10% de chance de enfermarse si higiene baja
        if (!enferma && stats.getHigiene() <= 30 && Math.random() < 0.10) {
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

