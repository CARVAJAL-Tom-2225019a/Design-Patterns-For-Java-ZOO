package viewApplication;

<<<<<<< HEAD
=======
import javax.sound.sampled.*;
import javax.sound.sampled.DataLine.Info;
>>>>>>> 3bcb77ffe5977d3efa396f31039a8686830caf85
import java.io.File;
import java.io.IOException;

import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;

/**
 * Classe permettant de gérer le son que font ques les créatures
 */
public class Son {
	
	/**
	 * Méthode permettant de lancer une piste audio
	 * @param url de la piste audio
	 */
    public void play(String url) {
        final File file = new File("assets/" + url);

        try (final AudioInputStream in = getAudioInputStream(file)) {

            final AudioFormat outFormat = getOutFormat(in.getFormat());
            final Info info = new Info(SourceDataLine.class, outFormat);

            try (final SourceDataLine line =
                         (SourceDataLine) AudioSystem.getLine(info)) {

                if (line != null) {
                    line.open(outFormat);
                    line.start();
                    stream(getAudioInputStream(outFormat, in), line);
                    line.drain();
                    line.stop();
                }
            }
        } catch (UnsupportedAudioFileException
                 | LineUnavailableException
                 | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Méthode permettant de récupérer le format audio
     * @param inFormat le format
     * @return un format
     */
    private AudioFormat getOutFormat(AudioFormat inFormat) {
        final int ch = inFormat.getChannels();
        final float rate = inFormat.getSampleRate();
        return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
    }

    /**
     * Méthode stream
     * @param in format audio
     * @param line source date
     * @throws IOException en cas d'erreur lors du stream
     */
    private void stream(AudioInputStream in, SourceDataLine line)
            throws IOException {
        final byte[] buffer = new byte[65536];
        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            line.write(buffer, 0, n);
        }
    }
}
