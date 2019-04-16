package billcalc;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Tay
 */

public class BillCalc extends Application {

    private Text text;

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        text = new Text("Bill Calculator");
        text.setFont(Font.font("Times New Roman", FontWeight.THIN, 20));
        root.setTop(text);

        GridPane gp = new GridPane();

        Label menuP = new Label("Menu Price:");
        gp.add(menuP, 0, 0);
        gp.setVgap(20);

        TextField textP = new TextField();
        textP.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(!newValue.matches("[0-9]*")){
                textP.setText(oldValue);
            }
        });
        
        gp.add(textP, 1, 0);
        gp.setHgap(20);

        Label discount = new Label("Discount %:");
        gp.add(discount, 0, 1);

        TextField textD = new TextField();
        textD.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(!newValue.matches("[0-9]*")){
                textD.setText(oldValue);
            }
        });
        gp.add(textD, 1, 1);

        Label tip = new Label("Tip %:");
        gp.add(tip, 0, 2);

        TextField textT = new TextField();
        textT.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(!newValue.matches("[0-9]*")){
                textT.setText(oldValue);
            }
        });
        gp.add(textT, 1, 2);

        Label totalBill = new Label("Total Bill:");
        gp.add(totalBill, 0, 3);

        Label tb = new Label();
        gp.add(tb, 1, 3);

        HBox hb = new HBox(10);
        Button btnReset = new Button("Reset");
        btnReset.setPrefSize(100, 20);
        Button btnSubmit = new Button("Submit");
        btnSubmit.setPrefSize(100, 20);
        Button btnExit = new Button("Exit");
        btnExit.setPrefSize(100, 20);
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(3);
        hb.getChildren().addAll(btnReset, btnSubmit, btnExit);
        root.setBottom(hb);

        root.setCenter(gp);

        final Text actionT = new Text();
        btnSubmit.setOnAction((ActionEvent event) -> {           
            if ((!textP.getText().contains("-")) && (!textD.getText().contains("-"))
                    && (!textT.getText().contains("-"))) {
                
                tb.setText("$" + Double.parseDouble(textP.getText()) * ((100 - Double.parseDouble(textD.getText())) / 100)
                        * ((100 + Double.parseDouble(textT.getText())) / 100));
                
            } else if ((!textP.getText().contains("0")) && (!textD.getText().contains("0"))
                    && (!textT.getText().contains("0"))) {
                
                tb.setText("$" + Double.parseDouble(textP.getText()) * ((100 - Double.parseDouble(textD.getText())) / 100)
                        * ((100 + Double.parseDouble(textT.getText())) / 100));
                
            } else {
                
                actionT.setFill(Color.FIREBRICK);
                actionT.setText("Error: No negative!!!");
                tb.setText("");
            }
        });
        
        btnReset.setOnAction((ActionEvent event) -> {
            textP.clear();
            textD.clear();
            textT.clear();
            tb.setText("");
            actionT.setText("");
        });
        
        btnExit.setOnAction((ActionEvent event)-> {
            System.exit(0);
        });
        
        gp.add(actionT, 1, 3);
        
        Scene scene = new Scene(root, 300, 285);

        primaryStage.setTitle("Bill Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
