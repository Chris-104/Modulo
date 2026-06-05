package proyecto_modulo;

import java.util.concurrent.ThreadLocalRandom;

public class Estadisticas {
    private int hambre;      // 0=lleno  → maxHambre=muerto de hambre
    private int energia;     // 0=sin energía → maxEnergia=lleno
    private int felicidad;   // 0=muy triste  → maxFelicidad=muy feliz
    private int salud;       // 0=muerto      → maxSalud=sano
    private int higiene;     // 0=muy sucio   → maxHigiene=limpio
    private int nivel;       // nivel de la mascota
    private int experiencia; // XP acumulada
    private int dinero;      // monedas del jugador
    private int diasVividos; // días que ha vivido

    // MÁXIMOS personalizables según tipo de mascota (base 100)
    private int maxHambre    = 100;
    private int maxEnergia   = 100;
    private int maxFelicidad = 100;
    private int maxSalud     = 100;
    private int maxHigiene   = 100;

    private int numerosRandom(){
        return ThreadLocalRandom.current().nextInt(30, 76);
    }

    public Estadisticas() {
        this.hambre      = 50;
        this.energia     = 50;
        this.felicidad   = 50;
        this.salud       = 100;
        this.higiene     = 50;

        this.nivel       = 1;
        this.experiencia = 0;
        this.dinero      = numerosRandom();
        this.diasVividos = 1;
    }

    /*
     * MODIFICADORES DE TIPO DE MASCOTA (MÁXIMOS)
     * ============================================
     * Ahora estos modificadores alteran el TECHO de cada estadística,
     * no el valor inicial. Así un dragón puede llegar a 120 de hambre
     * y 115 de energía, pero sigue empezando en 50 como todos.
     *
     * INSTRUCCIONES PARA MODIFICAR:
     * - Busca el case de tu animal y cambia los números.
     * - +X aumenta el máximo, -X lo reduce.
     * - El valor actual (50) NO cambia aquí.
     * - La opción "otro" (default) deja todo en 100.
     */
    public void aplicarModificadoresTipo(String tipo) {
        switch (tipo.toLowerCase()) {
            case "dragón":
            case "dragon":
                // DRAGON: Máximos altos de hambre y energía, salud moderada.
                this.maxHambre  = 100 + 20;  // Máx 120% hambre
                this.maxEnergia = 100 + 15;  // Máx 115% energía
                this.maxSalud   = 100 + 10;  // Máx 110% salud
                break;

            case "perro":
                // PERRO: Máximos altos de felicidad y energía.
                this.maxFelicidad = 100 + 15; // Máx 115% felicidad
                this.maxEnergia   = 100 + 10; // Máx 110% energía
                break;

            case "gato":
                // GATO: Máximo alto de higiene, hambre moderada.
                this.maxHigiene = 100 + 15; // Máx 115% higiene
                this.maxHambre  = 100 + 10; // Máx 110% hambre
                break;

            case "conejo":
                // CONEJO: Máximo alto de energía, salud reducida.
                this.maxEnergia = 100 + 20; // Máx 120% energía
                this.maxSalud   = 100 - 10; // Máx 90% salud
                break;

            case "panda":
                // PANDA: Máximo alto de salud y hambre.
                this.maxSalud  = 100 + 15; // Máx 115% salud
                this.maxHambre = 100 + 10; // Máx 110% hambre
                break;

            case "zorro":
                // ZORRO: Máximos moderados de energía y felicidad.
                this.maxEnergia   = 100 + 10; // Máx 110% energía
                this.maxFelicidad = 100 + 10; // Máx 110% felicidad
                break;

            case "lobo":
                // LOBO: Máximos moderados de hambre y energía.
                this.maxHambre  = 100 + 10; // Máx 110% hambre
                this.maxEnergia = 100 + 10; // Máx 110% energía
                break;

            default:
                // OTRO: Todos los máximos quedan en 100.
                break;
        }
    }

    public void pasarTurno(boolean dormida) {
        if (!dormida) {
            hambre    = Math.min(maxHambre, hambre    + 6);
            energia   = Math.max(0,        energia   - 3);
            felicidad = Math.max(0,        felicidad - 3);
            higiene   = Math.max(0,        higiene   - 3);
            if (hambre >= maxHambre * 0.8) salud = Math.max(0, salud - 6);
            if (higiene <= 20) salud = Math.max(0, salud - 10);
        } else {
            energia = Math.min(maxEnergia, energia + 15);
            hambre  = Math.min(maxHambre,  hambre  + 2);
            salud   = Math.min(maxSalud,   salud   + 5);
        }
    }

    public boolean ganarExperiencia(int xp) {
        experiencia += xp;
        int xpNecesaria = nivel * 100;
        if (experiencia >= xpNecesaria) {
            experiencia -= xpNecesaria;
            nivel++;
            salud     = maxSalud;
            felicidad = Math.min(maxFelicidad, felicidad + 20);
            return true;
        }
        return false;
    }

    public EstadoMascota getEstado(boolean dormida) {
        if (salud <= 0)        return EstadoMascota.MUERTO;
        if (energia <= 10)     return EstadoMascota.MUERTO;
        if (felicidad <= 15)   return EstadoMascota.MUERTO;
        if (hambre >= maxHambre) return EstadoMascota.MUERTO;

        if (dormida)           return EstadoMascota.DORMIDO;
        if (salud <= 30)       return EstadoMascota.ENFERMO;
        if (hambre >= maxHambre * 0.8) return EstadoMascota.HAMBRIENTO;
        if (energia <= 20)     return EstadoMascota.CANSADO;
        if (felicidad <= 25)   return EstadoMascota.TRISTE;
        if (felicidad >= maxFelicidad * 0.75) return EstadoMascota.FELIZ;

        return EstadoMascota.NORMAL;
    }

    public boolean estaViva() {
        return salud > 0 && energia > 10 && felicidad > 15 && hambre < maxHambre;
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

    public int getMaxHambre()    { return maxHambre; }
    public int getMaxEnergia()   { return maxEnergia; }
    public int getMaxFelicidad() { return maxFelicidad; }
    public int getMaxSalud()     { return maxSalud; }
    public int getMaxHigiene()   { return maxHigiene; }

    public void setHambre(int v)    { hambre    = Math.max(0, Math.min(maxHambre,    v)); }
    public void setEnergia(int v)   { energia   = Math.max(0, Math.min(maxEnergia,   v)); }
    public void setFelicidad(int v) { felicidad = Math.max(0, Math.min(maxFelicidad, v)); }
    public void setSalud(int v)     { salud     = Math.max(0, Math.min(maxSalud,     v)); }
    public void setHigiene(int v)   { higiene   = Math.max(0, Math.min(maxHigiene,   v)); }
    public void setDinero(int v)    { dinero    = Math.max(0, v); }
    public void incrementarDias()   { diasVividos++; }
    public int getXpNecesaria()     { return nivel * 100; }
}
