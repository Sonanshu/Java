package playerMp3;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jlme.util.Player;

class Songs{

    public void songplay(String songname) {

        try {
            FileInputStream fs = new FileInputStream(songname);
            BufferedInputStream bs = new BufferedInputStream(fs);
            Player play = new Player(bs);
            play.play();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

public class PlayerMp3 {

    public static void main(String[] args) {
        Songs obj = new Songs();
        obj.songplay("C:\\Users\\sonanshu\\Desktop\\Baarish.mp3");
           
    }

}
