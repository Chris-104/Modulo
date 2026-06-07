 package proyecto_modulo;

import java.io.File;
import javax.sound.sampled.AudioInputStream;  // ← faltaba
import javax.sound.sampled.AudioSystem;        // ← faltaba
import javax.sound.sampled.Clip;               // ← faltaba
import javax.sound.sampled.LineEvent;          // ← faltaba (para el listener)

public class Reproductor_sonidos {

    private static Clip    musicaFondo;
    private static boolean sonidoActivado = true;
    private static long      posicionMusica = 0;  // Posición guardada al pausar

    public static final String MUSICA_FONDO = "sounds/musica_fondo.wav";
    public static final String SFX_BAÑAR    = "sounds/ducha.wav.wav";
    public static final String SFX_MUERTE   = "sounds/muerte.wav";
    public static final String SFX_COMER    = "sounds/comer.wav";
    public static final String SFX_DUERMA   = "sounds/dormir.wav";
    public static final String SFX_RULETA   = "sounds/ruleta.wav";
    public static final String SFX_COMPRAR  = "sounds/comprar.wav";

    public static void iniciarMusicaFondo() {
        if (!sonidoActivado) return;
        try {
            if (musicaFondo != null && musicaFondo.isRunning()) return;

            File archivoMusica = new File(MUSICA_FONDO);
            if (!archivoMusica.exists()) return;

            AudioInputStream audio = AudioSystem.getAudioInputStream(archivoMusica);
            musicaFondo = AudioSystem.getClip();
            musicaFondo.open(audio);
            musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
            musicaFondo.start();
        } catch (Exception e) {   }
    }

    public static void detenerMusicaFondo() {
        if (musicaFondo != null && musicaFondo.isRunning()) {
            musicaFondo.stop();
            musicaFondo.close();
        }
    }

    public static void pausarMusicaFondo() {
        if (musicaFondo != null && musicaFondo.isRunning()) {
            posicionMusica = musicaFondo.getMicrosecondPosition();
            musicaFondo.stop();
        }
    }

    public static void reanudarMusicaFondo() {
        if (!sonidoActivado) return;
        if (musicaFondo != null && musicaFondo.isOpen()) {
            musicaFondo.setMicrosecondPosition(posicionMusica);
            musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
            musicaFondo.start();
        }
    }

    public static void reproducirEfecto(String rutaArchivo) {
        reproducirEfecto(rutaArchivo, false);
    }

    public static void reproducirEfecto(String rutaArchivo, boolean pausarMusica) {
        if (!sonidoActivado) return;

        if (pausarMusica) pausarMusicaFondo();

        new Thread(() -> {
            try {
                File archivo = new File(rutaArchivo);
                if (!archivo.exists()) return;

                AudioInputStream audio = AudioSystem.getAudioInputStream(archivo);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();

                // Usar listener para reanudar música exactamente cuando termina el efecto
                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                        if (pausarMusica) reanudarMusicaFondo();
                    }
                });

                Thread.sleep(clip.getMicrosecondLength() / 1000);
            } catch (Exception e) {  }
        }).start();
    }

    public static void sfxBañar()  { reproducirEfecto(SFX_BAÑAR);  }
    public static void sfxMuerte() { reproducirEfecto(SFX_MUERTE); }
    public static void sfxDormir() { reproducirEfecto(SFX_DUERMA); }
    public static void sfxComer()  { reproducirEfecto(SFX_COMER);  }

    public static void setSonidoActivado(boolean activado) {
        sonidoActivado = activado;
        if (!activado) detenerMusicaFondo();
        else           iniciarMusicaFondo();
    }

    public static boolean isSonidoActivado() { return sonidoActivado; }

	public static void reiniciarMusica() {
		// TODO Auto-generated method stub
		
	}
}