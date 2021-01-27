/**
 * Sample Skeleton for 'OpticalRoughnessApplicationFXML.fxml' Controller Class
 */
package opticalroughnessapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import javafx.stage.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;
import statutils.StatFormulae;
import binmethod.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.ValueAxis;
import mathutils.*;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
//import opticalroughnessapplication.OpticalRoughnessApplicationMain;

public class OpticalRoughnessApplicationFXMLController {

    List<Float> exampleData;
    List<Float> binned_normalised_data;
    List<Float> fittedLineData;
    BinFormulae binformula;
    StatFormulae statformulaeInstance;
    XYChart.Series set1;
    XYChart.Series series2;
    CurveFitter curveFitterInstance;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Button1"
    private Button Button1; // Value injected by FXMLLoader

    @FXML // fx:id="Button1"
    private Button Button2; // Value injected by FXMLLoader

    @FXML // fx:id="BarChart1"
    private BarChart<Number, Float> BarChart1; // Value injected by FXMLLoader

    @FXML
    private LineChart<Number, Double> LineChart1;

    @FXML // fx:id="YAxis1"
    private NumberAxis YAxis1;

    @FXML // fx:id="Button1"
    private Button ClearData; // Value injected by FXMLLoader

    @FXML
    private Label SigmaLabel;

    @FXML
    private Label MuoLabel;

    @FXML
    private Label AlphaLabel;

    @FXML // fx:id="ComboBox1"
    private ComboBox<String> ComboBox1; // Value injected by FXMLLoader

    @FXML // fx:id="CheckBox1"
    private CheckBox CheckBox1; // Value injected by FXMLLoader

    @FXML // fx:id="mean_label"
    private Label mean_label; // Value injected by FXMLLoader

    @FXML // fx:id="var_label"
    private Label var_label; // Value injected by FXMLLoader

    @FXML // fx:id="median_label"
    private Label median_label; // Value injected by FXMLLoader

    @FXML // fx:id="std_label"
    private Label std_label; // Value injected by FXMLLoader

    @FXML // fx:id="importDatabutton"
    private MenuItem importDatabutton; // Value injected by FXMLLoader

    @FXML
    void importDataButtonPressed(ActionEvent event) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open the .txt data file");
        File file = fileChooser.showOpenDialog(stage);
        System.out.printf("%s", file);
        exampleData = new ArrayList<Float>();
        try {
            //Read in the data from the file
            Scanner Reader = new Scanner(file);

            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                Float data2 = Float.parseFloat(data);
                exampleData.add(data2);
            }
            String inputstr = new String();
            statformulaeInstance = new StatFormulae();

            statformulaeInstance.setData(exampleData);
            mean_label.setText(inputstr.valueOf(statformulaeInstance.getMean()));
            var_label.setText(inputstr.valueOf(statformulaeInstance.getVar()));
            median_label.setText(inputstr.valueOf(statformulaeInstance.getMedian()));
            std_label.setText(inputstr.valueOf(statformulaeInstance.getStd()));
            statformulaeInstance.getMax();
            statformulaeInstance.getMin();
            Reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("There was an error!");
            e.printStackTrace();
        }
    }

    @FXML
    void calculateButtonPressed() {
        //First get the Bin method
        String bin_method = ComboBox1.getValue();

        //Then set up the bin formula class based on what is selected 
        switch (bin_method) {
            case "Rice Rule":
                binformula = new RiceRule(exampleData);
                break;
            case "Square Root Choice":
                binformula = new SquareRootChoice(exampleData);
                break;
            case "Sturges Formula":
                binformula = new SturgesFormula(exampleData);
                break;
        }
        //We now need to calculate the bin number.

        binformula.calculateNumberOfBins();

        //Number of bins has been calulated and now needs to be 
        statformulaeInstance.binDataCount(binformula);

        binned_normalised_data = new ArrayList<Float>();
        //List for graph 
        System.out.printf("binned_normalised_data.size()--------------------- %d \n", binned_normalised_data.size());
        binned_normalised_data = statformulaeInstance.histoNorm(binformula);

        float sum = 0;

        for (int i = 0; i < binned_normalised_data.size(); i++) {
            //System.out.printf("%f \n", binned_normalised_data.get(i));
            sum += binned_normalised_data.get(i);
        }

        System.out.printf("Check %f should equal 1", sum);

        if (CheckBox1.isSelected()) {
            //Do code to produce the line fitting here
            curveFitterInstance = new CurveFitter(binned_normalised_data);

            fittedLineData = new ArrayList<Float>();
            curveFitterInstance.perform_calculations();
            fittedLineData = curveFitterInstance.CreateLineData();

            //Create line and assign the curveFitter line data to it
            //https://stackoverflow.com/questions/28788117/stacking-charts-in-javafx
            series2 = new XYChart.Series<>();

            for (int i = 0; i < fittedLineData.size(); i++) {
                String n = String.valueOf(i);
                System.out.printf("%f \n", fittedLineData.get(i));
                series2.getData().add(new XYChart.Data(n, fittedLineData.get(i)));
            }

            LineChart1.getData().addAll(series2);
//            YAxis1.setAutoRanging(false);
//            YAxis1.setAutoRanging(false);
//            YAxis1.setUpperBound(1);
//            YAxis1.setLowerBound(1);
            SigmaLabel.setText(String.valueOf((float) curveFitterInstance.getSigma()));
            MuoLabel.setText(String.valueOf((float) curveFitterInstance.getMean()));
            AlphaLabel.setText(String.valueOf((float) curveFitterInstance.getNormFactor()));

        }

        set1 = new XYChart.Series<>();

        for (int i = 0; i < binned_normalised_data.size(); i++) {
            String n = String.valueOf(i);
            set1.getData().add(new XYChart.Data(n, binned_normalised_data.get(i)));
        }
        BarChart1.setCategoryGap(0);
        BarChart1.getData().addAll(set1);

    }

    @FXML
    void saveImage() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Chart as Image");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(stage);

        if (file == null) {
            return;
        }

        BarChart<Number, Float> BarChart2 = BarChart1;
        LineChart<Number, Double> LineChart2 = LineChart1;

        Scene scene2 = new Scene(new Group(), 595, 400);

        ((Group) scene2.getRoot()).getChildren().add(BarChart2);
        ((Group) scene2.getRoot()).getChildren().add(LineChart2);

        WritableImage image = scene2.snapshot(null);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
        } catch (IOException ex) {
            Logger.getLogger(OpticalRoughnessApplicationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Image Saved");

        System.gc();
    }

    @FXML

    public void clearGraphandData() {
        //Insert code here to set back to original ready to import different data
        //Clear all arrays and lists
        exampleData.clear();
        binned_normalised_data.clear();
        //Clear charts
        set1.getData().clear();
        series2.getData().clear();
        LineChart1.getData().clear();
        BarChart1.getData().clear();
        fittedLineData.clear();

        //Clear all labels
        mean_label.setText("0");
        var_label.setText("0");
        median_label.setText("0");
        std_label.setText("0");
        
        AlphaLabel.setText("0");
        MuoLabel.setText("0");
        SigmaLabel.setText("0");

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert Button1 != null : "fx:id=\"Button1\" was not injected: check your FXML file 'OpticalRoughnessApplicationFXML.fxml'.";
        assert BarChart1 != null : "fx:id=\"BarChart1\" was not injected: check your FXML file 'OpticalRoughnessApplicationFXML.fxml'.";
        assert ComboBox1 != null : "fx:id=\"ComboBox1\" was not injected: check your FXML file 'OpticalRoughnessApplicationFXML.fxml'.";
        assert CheckBox1 != null : "fx:id=\"CheckBox1\" was not injected: check your FXML file 'OpticalRoughnessApplicationFXML.fxml'.";
        assert mean_label != null : "fx:id=\"mean_label\" was not injected: check your FXML file 'OpticalRoughnessApplicationFXML.fxml'.";
        assert var_label != null : "fx:id=\"var_label\" was not injected: check your FXML file 'OpticalRoughnessApplicationFXML.fxml'.";
        assert median_label != null : "fx:id=\"median_label\" was not injected: check your FXML file 'OpticalRoughnessApplicationFXML.fxml'.";
        assert std_label != null : "fx:id=\"std_label\" was not injected: check your FXML file 'OpticalRoughnessApplicationFXML.fxml'.";
        assert YAxis1 != null : "fx:id=\"YAxis1\" was not injected: check your FXML file 'OpticalRoughnessApplicationFXML.fxml'.";
        assert ClearData != null : "fx:id=\"ClearData\" was not injected: check your FXML file 'OpticalRoughnessApplicationFXML.fxml'.";
        assert SigmaLabel != null : "fx:id=\"SigmaLabel\" was not injected: check your FXML file 'OpticalRoughnessApplicationFXML.fxml'.";
        assert MuoLabel != null : "fx:id=\"MuoLabel\" was not injected: check your FXML file 'OpticalRoughnessApplicationFXML.fxml'.";
        assert AlphaLabel != null : "fx:id=\"AlphaLabel\" was not injected: check your FXML file 'OpticalRoughnessApplicationFXML.fxml'.";
        assert importDatabutton != null : "fx:id=\"importDatabutton\" was not injected: check your FXML file 'OpticalRoughnessApplicationFXML.fxml'.";

        ComboBox1.getItems().setAll("Rice Rule", "Square Root Choice", "Sturges Formula");

        LineChart1.setLegendVisible(false);
        LineChart1.setAnimated(false);
        LineChart1.setCreateSymbols(true);
        LineChart1.setAlternativeRowFillVisible(false);
        LineChart1.setAlternativeColumnFillVisible(false);
        LineChart1.setHorizontalGridLinesVisible(false);
        LineChart1.setVerticalGridLinesVisible(false);
        LineChart1.getXAxis().setVisible(false);

    }
}
