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

    /*
     * COHERENCIA - pasarTurno()
     * =========================
     * APLICADO:
     *   - Felicidad al despierto: -3 (antes -4) para no bajar tan rapido.
     *   - Al dormir: recupera salud +5 adicionalmente.
     *
     * Reglas actuales:
     *   Despierto: hambre +6, energia -4, felicidad -3, higiene -4.
     *              penalizacion salud si hambre>=80 o higiene<=20.
     *   Dormido:   energia +15, hambre +2, salud +5.
     */
    public void pasarTurno(boolean dormida) {
        if (!dormida) {
            hambre    = Math.min(100, hambre    + 6);
            energia   = Math.max(0,   energia   - 3);
            felicidad = Math.max(0,   felicidad - 3);  // CAMBIO: antes era -4, ahora -3 para no bajar tan rapido
            higiene   = Math.max(0,   higiene   - 3);
            if (hambre >= 80) salud = Math.max(0, salud - 6);
            if (higiene <= 20) salud = Math.max(0, salud - 10);
        } else {
            energia = Math.min(100, energia + 15);
            hambre  = Math.min(100, hambre  + 2);
            salud   = Math.min(100, salud   + 5);  // CAMBIO: recupera salud al descansar profundamente
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

    /*
     * COHERENCIA - getEstado() vs estaViva()
     * ======================================
     * APLICADO: Los umbrales de estaViva() fueron corregidos para coincidir
     * exactamente con los de getEstado(), evitando estados "fantasma".
     * Antes: salud>=0, energia>=15, felicidad>=15, hambre<=100.
     * Ahora: salud>0, energia>10, felicidad>15, hambre<100.
     */
    public EstadoMascota getEstado(boolean dormida) {
        // condiciones de muerte (revisar primero para que sean alcanzables)
        if (salud <= 0)        return EstadoMascota.MUERTO;
        if (energia <= 10)     return EstadoMascota.MUERTO;
        if (felicidad <= 15)   return EstadoMascota.MUERTO;
        if (hambre >= 100)     return EstadoMascota.MUERTO;

        if (dormida)           return EstadoMascota.DORMIDO;
        if (salud <= 30)       return EstadoMascota.ENFERMO;
        if (hambre >= 80)      return EstadoMascota.HAMBRIENTO;
        if (energia <= 20)     return EstadoMascota.CANSADO;
        if (felicidad <= 25)   return EstadoMascota.TRISTE;
        if (felicidad >= 75)   return EstadoMascota.FELIZ;

        return EstadoMascota.NORMAL;
    }

    /*
     * ANALISIS DE COHERENCIA - estaViva()
     * ===================================
     * INCOHERENCIA CRITICA: Los umbrales de este metodo NO coinciden
     * con getEstado(), lo que permite estados de muerte parcial o
     * "fantasma" donde la mascota aparece viva pero el estado dice MUERTO.
     *
     * Cambios sugeridos para alinear con getEstado():
     *   UBICACION: Modificar la linea de retorno de estaViva().
     *   CAMBIO:
     *     return salud > 0 && energia > 10 && felicidad > 15 && hambre < 100;
     *
     * Explicacion:
     *   - salud > 0   : getEstado() dice MUERTO si salud <= 0. Debe ser estrictamente mayor.
     *   - energia > 10: getEstado() dice MUERTO si energia <= 10. No >= 15.
     *   - felicidad > 15: getEstado() dice MUERTO si felicidad <= 15. No >= 15.
     *   - hambre < 100: getEstado() dice MUERTO si hambre >= 100. No <= 100.
     */
    public boolean estaViva() {
        // CAMBIO: umbrales corregidos para coincidir con getEstado()
        // antes: salud >= 0, energia >= 15, felicidad >= 15, hambre <= 100
        // ahora: salud > 0, energia > 10, felicidad > 15, hambre < 100
        return salud > 0 && energia > 10 && felicidad > 15 && hambre < 100;
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

