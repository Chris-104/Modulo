package proyecto_modulo;

import java.io.File;
import javax.sound.sampled.AudioInputStream;  // ← faltaba
import javax.sound.sampled.AudioSystem;        // ← faltaba
import javax.sound.sampled.Clip;               // ← faltaba
import javax.sound.sampled.LineEvent;          // ← faltaba (para el listener)

public class Reproductor_sonidos {

    private static Clip    musicaFondo;
    private static boolean sonidoActivado = true;

    public static final String MUSICA_FONDO = "sounds/musica_fondo.wav";
    public static final String SFX_BAÑAR    = "sounds/ducha.wav";   // ← tenías ducha.wav.wav (doble extensión)
    public static final String SFX_MUERTE   = "sounds/muerte.wav";
    public static final String SFX_COMER    = "sounds/comer.wav";
    public static final String SFX_DUERMA   = "sounds/dormir.wav";

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
        } catch (Exception e) {
            // Sigue sin sonido si hay error
        }
    }

    public static void detenerMusicaFondo() {
        if (musicaFondo != null && musicaFondo.isRunning()) {
            musicaFondo.stop();
            musicaFondo.close();
        }
    }

    public static void reproducirEfecto(String rutaArchivo) {
        if (!sonidoActivado) return;

        new Thread(() -> {
            try {
                File archivo = new File(rutaArchivo);
                if (!archivo.exists()) return;

                AudioInputStream audio = AudioSystem.getAudioInputStream(archivo);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();

                Thread.sleep(clip.getMicrosecondLength() / 1000);
                clip.close();
            } catch (Exception e) {
                // Silencio si hay error
            }
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
}