package com.example.csit228_f1_v2;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


import static com.example.csit228_f1_v2.DeleteData.deleteData;

public class HomeController implements Initializable{

    public ToggleButton tbNight;
    public ProgressIndicator piProgress;
    public Slider slSlider;
    public ProgressBar pbProgress;
    static Integer loggedInID;
    @FXML
    TextField tfProductName;
    @FXML
    TextField tfProductPrice;
    @FXML
    TextArea taProductDescription;
    @FXML
    Label lblMarketplace;
    protected URL loc;
    protected ResourceBundle rsbundle;
    public Label txtTitle;
    public VBox vbOutput;
    public AnchorPane apViewProduct;

    public TextField tfViewProductName;
    public TextField tfViewProductPrice;
    public TextArea taViewProductDescription;

    public Button btnSaveChanges;
    public Button btnClose_view;
    public Button btnDelete_view;

    public AnchorPane apYourProducts;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loc = location;
        rsbundle = resources;

        vbOutput.getChildren().clear();
        if (CreateTable.productListTable()) {
            ResultSet yourProducts = ReadData.all_products();
            try {
                while (yourProducts.next()) {
//                System.out.println("Title: " + yourNotes.getString("title") + "\n" + "Content: " + yourNotes.getString("contents"));
                    int product_id = yourProducts.getInt("prodid");
                    String name = yourProducts.getString("prodname");
                    double price = yourProducts.getDouble("price");
                    String contents = yourProducts.getString("description");

                    Label prodName = new Label(name);
                    prodName.setPrefWidth(260);
                    Button view = new Button("View");
                    view.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white; ");
                    view.setFont(Font.font("Book Antiqua", FontWeight.EXTRA_BOLD,12));
                    Button delete_product = new Button("Delete");
                    delete_product.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
                    delete_product.setFont(Font.font("Book Antiqua", FontWeight.EXTRA_BOLD,12));
                    HBox hbox = new HBox(prodName, view, delete_product);
                    hbox.setSpacing(10);
                    vbOutput.getChildren().add(hbox);
                    vbOutput.setSpacing(3);
                    view.setOnAction(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            apViewProduct.setVisible(true);
                            vbOutput.setVisible(false);
                            tfViewProductName.setText(name);
                            tfViewProductPrice.setText(String.valueOf(price));
                            taViewProductDescription.setText(contents);
                            btnSaveChanges.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    Double product_price;
                                    try{
                                        product_price = Double.parseDouble(tfViewProductPrice.getText());
                                        UpdateData.updateProduct(product_id, tfViewProductName.getText(), product_price, taViewProductDescription.getText());
                                        prodName.setText(tfViewProductName.getText()); // Update product name label

                                        tfViewProductName.setText(tfProductName.getText());
                                        tfViewProductPrice.setText(tfProductPrice.getText());
                                        taViewProductDescription.setText(taViewProductDescription.getText()); // Update product description
                                    }catch (NumberFormatException e){
                                        showAlert("Invalid product price format");
                                    }

                                    initialize(loc, rsbundle);
                                    onCloseViewProduct();
                                }
                            });

                            btnDelete_view.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    onDeleteProduct(product_id);
                                    vbOutput.getChildren().remove(hbox); // Remove the hbox from the VBox
                                    onCloseViewProduct();
                                }
                            });
                        }
                    });
                    delete_product.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            onDeleteProduct(product_id);
                            initialize(loc, rsbundle);
                        }
                    });
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (yourProducts != null) {
                        yourProducts.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void addProduct (ActionEvent actionEvent) {
        try {
            String productName = tfProductName.getText();
            double productPrice = Double.parseDouble(tfProductPrice.getText());
            String productDescription = taProductDescription.getText();

            InsertData.insertProductData(productName, productPrice, productDescription);
            System.out.println("Product added successfully!");
            tfProductName.clear();
            tfProductPrice.clear();
            taProductDescription.clear();

            initialize(loc, rsbundle);
        } catch (NumberFormatException e) {
            showAlert("Invalid product price format.");
            System.err.println("Invalid product price format!");
        }
    }

    public void onDeleteAllProducts(ActionEvent actionEvent) {
        DeleteData.deleteAllProducts(HomeController.loggedInID);
        initialize(loc, rsbundle);
    }//sakto
    public void onDeleteProduct(int prod_id){
        DeleteData.deleteProduct(prod_id);
        initialize(loc, rsbundle);
    }//sakto

    public void onCloseViewProduct() {
        apViewProduct.setVisible(false);
        vbOutput.setVisible(true);
    }//sakto

    public void setLoggedInID (Integer id){
        this.loggedInID = id;
    }//sakto


    public void onSliderChange () {
        double val = slSlider.getValue();
        System.out.println(val);
        piProgress.setProgress(val / 100);
        pbProgress.setProgress(val / 100);
        if (val == 100) {
            System.exit(0);
        }
    }

    public void onNightModeClick () {
        if (tbNight.isSelected()) {
            tbNight.getParent().setStyle("-fx-background-color: BLACK");
            tbNight.setText("DAY");
            lblMarketplace.setStyle("-fx-text-fill: WHITE");
        } else {
            tbNight.getParent().setStyle("-fx-background-color: WHITE");
            tbNight.setText("NIGHT");
            lblMarketplace.setStyle("-fx-text-fill: BLACK");
        }
    }
    public void signout () {
        try {
            Stage stage = (Stage) HelloApplication.primaryStage.getScene().getWindow();
            HelloApplication.setStartingScene();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteAccount () {
        deleteData(loggedInID);
        //put alert and then goback to hello-view

    }

    public void updateAccount () {
        try {
            Stage stage = (Stage) HelloApplication.primaryStage.getScene().getWindow();
            HelloApplication.setUpdateScene();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update Status");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
