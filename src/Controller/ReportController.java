package Controller;

import helper.Scenes;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class ReportController {
    public Button backButton;






    public void toMain(ActionEvent event) throws IOException {
        Scenes.toMain(event);
    }
}
