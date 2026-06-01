package proyecto_modulo;

import java.util.concurrent.ThreadLocalRandom;

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

	//metodo para generar numeros randoms se pone numero menor numero mayor y se manda
	//a llamar solo con el nombre
	private int numerosRandom(){
		return ThreadLocalRandom.current().nextInt(30, 76);
	}
	    public Estadisticas() {
	        this.hambre      = numerosRandom();
	        this.energia     = numerosRandom();
	        this.felicidad   = numerosRandom();
	        this.salud       = numerosRandom();
	        this.higiene     = numerosRandom();
	        this.nivel       = 1;
	        this.experiencia = 0;
	        this.dinero      = numerosRandom();
	        this.diasVividos = 1;
	    }

	    public void pasarTurno(boolean dormida) {
	        if (!dormida) {
	            hambre    = Math.min(100, hambre    + 6);
	            energia   = Math.max(0,   energia   - 5);
	            felicidad = Math.max(0,   felicidad - 4);
	            higiene   = Math.max(0,   higiene   - 3);
	            if (hambre >= 80) salud = Math.max(0, salud - 6);
	            if (higiene <= 20) salud = Math.max(0, salud - 10);
	        } else {
	            energia = Math.min(100, energia + 15);
	            hambre  = Math.min(100, hambre  + 2);
	        }
	    }

	    public boolean ganarExperiencia(int xp) {
	        experiencia += xp;
	        int xpNecesaria = nivel * 100;
	        if (experiencia >= xpNecesaria) {
	            experiencia -= xpNecesaria;
	            nivel++;
	            salud     = 100;
	            felicidad = Math.min(100, felicidad + 20);
	            return true;
	        }
	        return false;
	    }

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

