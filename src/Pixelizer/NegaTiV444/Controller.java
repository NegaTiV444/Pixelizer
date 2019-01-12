package Pixelizer.NegaTiV444;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
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
    void initialize() {

    }

    @FXML
    private void OpenFile()
    {

        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Open File");//Заголовок диалога
        try
        {
            File file = fileChooser.showOpenDialog(STAGE);//Указываем текущую сцену
            currentImage = new Image(file.toURI().toString());
            imgInitial.setImage(currentImage);
            imgTransformed.setImage(Transformer.transform(currentImage, 16));
        }
        catch (Exception e)
        {
            showAlert("Error", "Please choose another file");
            System.out.println(e);
        }

    }


    private void showAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
