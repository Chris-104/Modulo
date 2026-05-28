package proyecto_modulo;

public class Estadisticas {
	 private int hambre;      // 0=lleno  → 100=muerto de hambre
	    private int energia;     // 0=sin energía → 100=lleno
	    private int felicidad;   // 0=muy triste  → 100=muy feliz
	    private int salud;       // 0=muerto      → 100=sano
	    private int higiene;     // 0=muy sucio   → 100=limpio
	    private int nivel;       // nivel de la mascota
	    private int experiencia; // XP acumulada
	    private int dinero;      // monedas del jugador
	    private int diasVividos; // días que ha vivido
	 
	    public Estadisticas() {
	        this.hambre      = 30;
	        this.energia     = 80;
	        this.felicidad   = 70;
	        this.salud       = 100;
	        this.higiene     = 80;
	        this.nivel       = 1;
	        this.experiencia = 0;
	        this.dinero      = 50;
	        this.diasVividos = 1;
	    }
	 
	    // ── Método que pasa el tiempo (se llama cada turno) ──
	    public void pasarTurno(boolean dormida) {
	        if (!dormida) {
	            hambre    = Math.min(100, hambre    + 6);
	            energia   = Math.max(0,   energia   - 5);
	            felicidad = Math.max(0,   felicidad - 4);
	            higiene   = Math.max(0,   higiene   - 3);
	            // Si tiene mucha hambre o está muy sucio, baja la salud
	            if (hambre >= 80) salud = Math.max(0, salud - 5);
	            if (higiene <= 20) salud = Math.max(0, salud - 3);
	        } else {
	            energia = Math.min(100, energia + 15);
	            hambre  = Math.min(100, hambre  + 2);
	        }
	    }
	 
	    // ── Ganar experiencia y subir nivel ─────────────────
	    public boolean ganarExperiencia(int xp) {
	        experiencia += xp;
	        int xpNecesaria = nivel * 100;
	        if (experiencia >= xpNecesaria) {
	            experiencia -= xpNecesaria;
	            nivel++;
	            salud     = 100; // al subir nivel se cura
	            felicidad = Math.min(100, felicidad + 20);
	            return true; // subió de nivel
	        }
	        return false;
	    }
	 
	    // ── Determinar estado actual ─────────────────────────
	    public EstadoMascota getEstado(boolean dormida) {
	        if (salud <= 0)        return EstadoMascota.MUERTO;
	        if (dormida)           return EstadoMascota.DORMIDO;
	        if (salud <= 30)       return EstadoMascota.ENFERMO;
	        if (hambre >= 80)      return EstadoMascota.HAMBRIENTO;
	        if (energia <= 20)     return EstadoMascota.CANSADO;
	        if (felicidad <= 25)   return EstadoMascota.TRISTE;
	        if (felicidad >= 75)   return EstadoMascota.FELIZ;
	        return EstadoMascota.NORMAL;
	    }
	 
	    public boolean estaViva() {
	        return salud > 0 && hambre < 100;
	    }
	 
	    // ── Getters y Setters ────────────────────────────────
	    public int getHambre()      { return hambre; }
	    public int getEnergia()     { return energia; }
	    public int getFelicidad()   { return felicidad; }
	    public int getSalud()       { return salud; }
	    public int getHigiene()     { return higiene; }
	    public int getNivel()       { return nivel; }
	    public int getExperiencia() { return experiencia; }
	    public int getDinero()      { return dinero; }
	    public int getDiasVividos() { return diasVividos; }
	 
	    public void setHambre(int v)    { hambre    = Math.max(0, Math.min(100, v)); }
	    public void setEnergia(int v)   { energia   = Math.max(0, Math.min(100, v)); }
	    public void setFelicidad(int v) { felicidad = Math.max(0, Math.min(100, v)); }
	    public void setSalud(int v)     { salud     = Math.max(0, Math.min(100, v)); }
	    public void setHigiene(int v)   { higiene   = Math.max(0, Math.min(100, v)); }
	    public void setDinero(int v)    { dinero    = Math.max(0, v); }
	    public void incrementarDias()   { diasVividos++; }
	    public int getXpNecesaria()     { return nivel * 100; }
	}

