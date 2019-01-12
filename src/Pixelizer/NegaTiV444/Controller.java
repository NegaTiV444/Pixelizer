package Pixelizer.NegaTiV444;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {

    private Image currentImage;

    @FXML
    public static Stage STAGE;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgInitial;

    @FXML
    private ImageView imgTransformed;

    @FXML
    private Slider transformLvl;

    @FXML
    private RadioButton rbBrighter;

    @FXML
    private ToggleGroup a;

    @FXML
    private RadioButton rbDarker;

    @FXML
    private RadioButton rbSaturate;

    @FXML
    private ToggleGroup b;

    @FXML
    private RadioButton rbDesaturate;

    @FXML
    private RadioButton rbStBr;

    @FXML
    private RadioButton rbStSat;

    @FXML
    private Button btTransform;

    @FXML
    private ToggleButton btInvert;

    @FXML
    void initialize() {

    }

    @FXML
    private void OpenFile()
    {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        try
        {
            File file = fileChooser.showOpenDialog(STAGE);
            currentImage = new Image(file.toURI().toString());
            imgInitial.setImage(currentImage);
        }
        catch (Exception e)
        {
            showAlert("Error", "Please choose another file");
            System.out.println(e);
        }

    }

    @FXML
    private void btTransformClick()
    {
        if (currentImage != null) {
            int brightness, saturation, pixelSize;
            boolean isInvert;
            if (rbBrighter.isSelected())
                brightness = 1;
            else if (rbDarker.isSelected())
                brightness = -1;
            else
                brightness = 0;

            if (rbSaturate.isSelected())
                saturation = 1;
            else if (rbDesaturate.isSelected())
                saturation = -1;
            else
                saturation = 0;
            pixelSize = (int) transformLvl.getValue();
            isInvert = btInvert.isSelected();
            imgTransformed.setImage(Transformer.transform(currentImage, pixelSize, brightness, saturation, isInvert));
        }
        else
            showAlert("Error", "Please select image");
    }

    private void showAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
