package GameScene;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author fegoo
 */
public class GameController implements Initializable {
    private static final Integer STARTTIME = 15;
    public Label timelabel;
    private Timeline timeline;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    public Button button;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timelabel.textProperty().bind(timeSeconds.asString());
    }
    public void start(ActionEvent actionEvent) {
        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds.set(STARTTIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME+1),
                        new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();


    }
}
