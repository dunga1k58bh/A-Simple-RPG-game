package  Audio;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Music {
    private MediaPlayer mediaPlayer;

    public Music(String fileName){
        Media m = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/" + fileName);
        MediaPlayer mediaPlayer = new MediaPlayer(m);
        this.mediaPlayer = mediaPlayer;
    }
    public void startMusic(){
        mediaPlayer.play();
    }
    public void jumpTo(long times){
        mediaPlayer.setStartTime(Duration.seconds(times));
    }

    public void setCycle(){
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }
    public void pauseMusic(){
        mediaPlayer.pause();
    }
    public void setVolume(double vol){
        mediaPlayer.setVolume(vol);
    }
}



