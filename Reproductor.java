import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

class Reproductor
{

	Clip clip;

    public void playMusic()
    {

        String musicLocation = "./musica/MusicUpstream.wav";

        try {

            File musicPath = new File(musicLocation);

            if (musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else
            {
                System.out.println("Error al intentar reproducir");
            }

        }catch (Exception e)
        {

        }
    }
    
    public void stopPlaying()
    {
        clip.stop();
    }

	
}