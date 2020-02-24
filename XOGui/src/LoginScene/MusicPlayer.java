package LoginScene;

import java.io.File;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fegoo
 */
public class MusicPlayer {
   static final  String path = "src/Song1.mp3";
   static final  Media media = new Media(new File(path).toURI().toString());
   static MediaPlayer mediaplayer =null;
   static int firstTimeCounter = 0;

    public static MediaPlayer getMediaplayer() {
        if(mediaplayer ==null)
        {
            mediaplayer= new MediaPlayer (media);
        }
        return mediaplayer;
    }

    public static void checkStatus(ImageView IMute, ImageView INoMute) {
        if (MusicPlayer.getMediaplayer().getStatus() == MediaPlayer.Status.PAUSED) {
            MusicPlayer.getMediaplayer().play();
            IMute.setVisible(false);
            INoMute.setVisible(true);

        } else {
            System.out.println("play");
            MusicPlayer.getMediaplayer().pause();

            IMute.setVisible(true);
            INoMute.setVisible(false);
        }
    }
    
}
