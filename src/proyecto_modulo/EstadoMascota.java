package proyecto_modulo;

public enum EstadoMascota {
	 FELIZ("¡Muy feliz! 🥰"),
	    NORMAL("Bien 😊"),
	    TRISTE("Triste 😢"),
	    ENFERMO("Enfermo/a 🤒"),
	    HAMBRIENTO("Hambriento/a 😵"),
	    CANSADO("Cansado/a 😓"),
	    DORMIDO("Durmiendo 😴"),
	    MUERTO("Ha fallecido 💀");
	 
	    private final String descripcion;
	 
	    EstadoMascota(String descripcion) {
	        this.descripcion = descripcion;
	    }
	 
	    public String getDescripcion() { return descripcion; }
	}
	 

