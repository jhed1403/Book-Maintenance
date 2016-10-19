 
package book.maintenance;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Programmer: Jhed Factolerin<br>
 * Program: BookGUI.java <br>
 * Date: October 2016 <br>
 * version 1.0
 */
public class BookGUI extends Application implements EventHandler<ActionEvent> {

    /**Button that displays the addRecordStage**/
    private Button btnAdd;
    /**Button that allows the user to update a record**/
    private Button btnUpdate;
    /**Button that deletes a record**/
    private Button btnDelete;
    /**Button that confirms if the user want to commit to the changes**/
    private Button btnSave;
    /**Button that cancels the changes**/
    private Button btnCancel;
    /**Button that closes the application**/
    private Button btnExit;
    /**Button that moves to the first record**/
    private Button btnFirst;
    /**Button that moves to the next record**/
    private Button btnNext;
    /**Button that moves to the previous record**/
    private Button btnPrevious;
    /**Button that moves to the last record**/
    private Button btnLast;
    /**Button that commits the executeUpdate**/
    private Button btnYes;
    /**Button that roll backs the executeUpdate**/
    private Button btnNo;
    /**TextField that displays the BookCodes**/   
    private TextField txtCode;
    /**TextField that displays the BookTitle**/   
    private TextField txtTitle;
    /**TextField that displays the BookPrice**/   
    private TextField txtPrice;
    /**Label that displays Book Maintenance**/   
    private Label mainTitle;
    /**Label for TextField txtCode**/   
    private Label lblCode;
    /**Label for TextField txtTitle**/   
    private Label lblTitle;
    /**Label for TextField txtPrice**/   
    private Label lblPrice;
    /**Font object to set the fonts of the mainTitle**/ 
    private Font font;
    /**Stage that displays the addRecord Form**/
    private final Stage addRecordStage = new Stage();
    /**Stage that displays the confirmation window**/
    private final Stage confirmationStage = new Stage();
    /**Stage that displays the error window**/
    private final Stage stage = new Stage();
    /**BooleanProperty that is used for removing default auto focus**/
    private final BooleanProperty firstTime = new SimpleBooleanProperty(true);
    /**HBox for lblCode and txtCode**/
    private HBox codeHBox;
    /**HBox for lblTitle and txtTitle**/
    private HBox titleHBox;
    /**HBox for lblPrice and txtPrice**/
    private HBox priceHBox;
    /**HBox for update buttons(btnAdd, btnDelete, btnUpdate, etc.)**/
    private HBox editHBox;
    /**HBox for navigating buttons**/
    private HBox navigateHBox;
    /**VBox for textFields and their labels**/
    private VBox txtFieldsVBox;
    /**GridPane to position Nodes**/
    private GridPane pane;
    /**BookDB object**/
    private BookDB f;
    /**Book object**/
    private Book one;
    /**ResultSet object**/
    private ResultSet results;
    /**Index to keep track the position of the records**/
    private int index;
    /**Keeps track of the number of rows**/
    private int numRows;
    /**Boolean used to disable and enable TextFields and Buttons**/
    private boolean isTrue;
    /**Boolean that checks if the btnDelete is selected**/
    private boolean isDeleteSelected;
    /**Boolean that checks if the btnAdd is selected**/
    private boolean isAddSelected;
    /**Boolean that checks if the btnUpdate is selected**/
    private boolean isUpdateSelected;

    /**
     * Methods that initializes everything
     */
    @Override
    public void init() {
        //Initializing TextFields
        txtCode = new TextField();
        txtTitle = new TextField();
        txtPrice = new TextField();

        //Initializing Buttons
        btnLast = new Button("Last");
        btnPrevious = new Button("Previous");
        btnNext = new Button("Next");
        btnFirst = new Button("First");
        btnAdd = new Button("Add");
        btnUpdate = new Button("Update");
        btnDelete = new Button("Delete");
        btnSave = new Button("Save");
        btnCancel = new Button("Cancel");
        btnExit = new Button("Exit");
        btnYes = new Button("Yes");
        btnNo = new Button("No");

        //Initializing Other Variables
        index = 1;
        numRows = 1;
        isTrue = false;
        isDeleteSelected = false;
        isAddSelected = false;
        isUpdateSelected = false;

        //Initializing Labels
        mainTitle = new Label("Book Maintenance");
        lblCode = new Label("Code");
        lblTitle = new Label("Title");
        lblPrice = new Label("Price");
        
        //Setting fonts
        font = new Font("Arial Black", 30);
        setFont(lblCode);
        setFont(lblTitle);
        setFont(lblPrice);
        
        //Repositioning Maintitle
        mainTitle.setFont(font);
        mainTitle.setPadding(new Insets(20, 0, 10, 180));

        
        //Resizing Buttons
        btnFirst.setPrefSize(84, 20);
        btnNext.setPrefSize(84, 20);
        btnPrevious.setPrefSize(84, 20);
        btnLast.setPrefSize(84, 20);
        btnAdd.setPrefSize(100, 20);
        btnUpdate.setPrefSize(100, 20);
        btnDelete.setPrefSize(100, 20);
        btnSave.setPrefSize(100, 20);
        btnCancel.setPrefSize(100, 20);
        btnExit.setPrefSize(100, 20);

        //Positioning Nodes
        codeHBox = new HBox(11);
        codeHBox.getChildren().addAll(lblCode, txtCode);
        codeHBox.setPadding(new Insets(10, 15, 10, 170));

        titleHBox = new HBox(15);
        titleHBox.getChildren().addAll(lblTitle, txtTitle);
        titleHBox.setPadding(new Insets(10, 15, 10, 170));

        priceHBox = new HBox(11);
        priceHBox.getChildren().addAll(lblPrice, txtPrice);
        priceHBox.setPadding(new Insets(10, 15, 30, 170));

        editHBox = new HBox(5);
        editHBox.setPrefWidth(600);
        editHBox.getChildren().addAll(btnAdd, btnUpdate, btnDelete, btnSave, btnCancel, btnExit);
        editHBox.setPadding(new Insets(10, 0, 0, 70));

        navigateHBox = new HBox(5);
        navigateHBox.setPrefWidth(200);
        navigateHBox.getChildren().addAll(btnFirst, btnPrevious, btnNext, btnLast);
        navigateHBox.setPadding(new Insets(10, 0, 0, 159));

        txtFieldsVBox = new VBox();
        txtFieldsVBox.getChildren().addAll(codeHBox, titleHBox, priceHBox, editHBox, navigateHBox);

        //Resizing TextField
        resizeTextField(txtCode);
        resizeTextField(txtTitle);
        resizeTextField(txtPrice);
        
        //Disabling TextFields and btnSave and btnCancel
        setEditable(isTrue);
        btnSave.setDisable(true);
        btnCancel.setDisable(true);

        //Removing Autofocus
        txtCode.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                txtFieldsVBox.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        //Adding nodes to the pane
        pane = new GridPane();
        pane.add(mainTitle, 0, 0);
        pane.add(txtFieldsVBox, 0, 1);
        pane.add(editHBox, 0, 3);
        pane.add(navigateHBox, 0, 4);

        //Event handling for the buttons
        btnFirst.setOnAction(this);
        btnNext.setOnAction(this);
        btnPrevious.setOnAction(this);
        btnLast.setOnAction(this);
        btnAdd.setOnAction(this);
        btnDelete.setOnAction(this);
        btnUpdate.setOnAction(this);
        btnCancel.setOnAction(this);
        btnSave.setOnAction(this);
        btnNo.setOnAction(this);
        btnYes.setOnAction(this);

        try {
            f = new BookDB();
            f.connect();
            f.open("SELECT BookCode, BookTitle, BookPrice FROM Books");
            results = f.getResultSet();
            results.last();
            numRows = results.getRow();
            results.first();
            one = new Book(results.getString(1), results.getString(2), results.getDouble(3));
            txtCode.setText(one.getCode());
            txtTitle.setText(one.getTitle());
            txtPrice.setText(one.currencyFormat(one.getPrice()));
            //System.out.println(numRows);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Displays the main stage
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        //Setting addRecordStage the only clickable Stage
        addRecordStage.initOwner(primaryStage);
        addRecordStage.initModality(Modality.APPLICATION_MODAL);

        //Setting confirmationStage the only clickable Stage
        confirmationStage.initOwner(primaryStage);
        confirmationStage.initModality(Modality.APPLICATION_MODAL);
        
        //Setting stage for error the only clickable stage
        stage.initOwner(primaryStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initModality(Modality.WINDOW_MODAL);
        
        //Event handling for btnExit
        btnExit.setOnAction((ActionEvent e) -> {
            primaryStage.close();
            try {
                f.close();
            } catch (SQLException z){
                System.out.println(z.getMessage());
            }
        });

        Scene scene = new Scene(pane, 660, 350);
        primaryStage.setTitle("Book Maintenance");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Method that displays window for adding record
     */
    private void addRecordWindow() {
        //TextFields to get input from the user
        TextField code = new TextField();
        TextField title = new TextField();
        TextField price = new TextField();

        //Buttons to add or cancel the inserting record
        Button add = new Button("Add");
        Button cancel = new Button("Cancel");

        //Setting the labels
        Label mTitle = new Label("Add Record");
        Label addLblCode = new Label("Code");
        Label addLblTitle = new Label("Title");
        Label addLblPrice = new Label("Price");

        //Setting the font
        Font mFont = new Font("Arial Black", 30);
        mTitle.setFont(mFont);
        setFont(addLblCode);
        setFont(addLblTitle);
        setFont(addLblPrice);
        
        //Positioning mainTitle
        mTitle.setPadding(new Insets(10, 0, 10, 175));

        //Resizing buttons
        add.setPrefSize(100, 20);
        cancel.setPrefSize(100, 20);

        //Positioning nodes
        HBox cHBox = new HBox(11);
        cHBox.getChildren().addAll(addLblCode, code);
        cHBox.setPadding(new Insets(10, 15, 10, 100));

        HBox tHBox = new HBox(15);
        tHBox.getChildren().addAll(addLblTitle, title);
        tHBox.setPadding(new Insets(10, 15, 10, 100));

        HBox pHBox = new HBox(11);
        pHBox.getChildren().addAll(addLblPrice, price);
        pHBox.setPadding(new Insets(10, 15, 30, 100));

        HBox eHBox = new HBox(5);
        eHBox.setPrefWidth(500);
        eHBox.getChildren().addAll(add, cancel);
        eHBox.setPadding(new Insets(10, 0, 0, 165));

        VBox tFVBox = new VBox();
        tFVBox.getChildren().addAll(cHBox, tHBox, pHBox, eHBox);

        //Resizing TextFields in the addRecordStage
        resizeTextField(code);
        resizeTextField(title);
        resizeTextField(price);

        //GridPane for the addRecordStage's nodes
        GridPane paneAdd = new GridPane();
        paneAdd.add(mTitle, 0, 0);
        paneAdd.add(tFVBox, 0, 1);
        paneAdd.add(eHBox, 0, 3);

        //Event handling for btnCancel
        cancel.setOnAction((ActionEvent e) -> {
            addRecordStage.close();
        });

        //Event handling for add
        add.setOnAction((ActionEvent e) ->  {
            try {
                CheckInput.checkCode(code.getText());
                f.checkPrimaryKeys(code.getText());
                f.getResultSet();
                CheckInput.checkTitle(title.getText());
                CheckInput.checkPrice(price.getText());
                one.setCode(code.getText());
                one.setTitle(title.getText());
                one.setPrice(Double.parseDouble(price.getText()));
                f.addRecord(one);
                confirmation("Add this record?");
            } catch (Exception z) {
                showStage(z.getMessage());
            }
        });

        Scene scene = new Scene(paneAdd, 550, 300);
        addRecordStage.setTitle("Add a Record");
        addRecordStage.setScene(scene);
        addRecordStage.show();

    }

    /**
     * Window that asks user for confirmation of their action
     *
     * @param message Question to be asked to the user
     */
    private void confirmation(String message) {
        //Setting the message
        Text isContinue = new Text(message);
        
        //Aligning btnYes and btnNo
        btnYes.setPadding(new Insets(5, 25, 5, 25));
        btnNo.setPadding(new Insets(5, 25, 5, 25));

        //GridPane for Buttons in continue window
        GridPane confirm = new GridPane();
        confirm.add(btnYes, 1, 0);
        confirm.add(btnNo, 2, 0);
        confirm.setPadding(new Insets(0, 10, 15, 125));
        confirm.setHgap(30);

        //Setting font style
        Font msgFont = new Font("Verdana", 18);
        Font okayFont = new Font("Verdana", 15);
        isContinue.setFont(msgFont);
        btnYes.setFont(okayFont);
        btnNo.setFont(okayFont);

        //BorderPane for isContinue text and buttons
        BorderPane cPane = new BorderPane();
        cPane.setCenter(isContinue);
        cPane.setBottom(confirm);

        Scene scene = new Scene(cPane, 500, 130);
        confirmationStage.setTitle("Confirmation");
        confirmationStage.centerOnScreen();
        confirmationStage.setScene(scene);
        confirmationStage.show();
    }

    /**
     * Displays the error window if invalid inputs are entered
     *
     * @param message The error message/String
     */
    private void showStage(String message) {
        Button okay = new Button("OK");
        okay.setPadding(new Insets(5, 25, 5, 25));

        //HBox for button okay
        HBox confirm = new HBox();
        confirm.getChildren().addAll(okay);
        confirm.setAlignment(Pos.CENTER);
        confirm.setPadding(new Insets(-15, 0, 10, 0));

        //Text object and setting font style
        Text error = new Text(message);
        Font eFont = new Font("Verdana", 17);
        Font okayFont = new Font("Verdana", 15);
        error.setFont(eFont);
        okay.setFont(okayFont);

        //BorderPane for message and button
        BorderPane sPane = new BorderPane();
        sPane.setCenter(error);
        sPane.setBottom(confirm);

        okay.setOnAction((ActionEvent e) -> {
            stage.close();
        });

        Scene scene = new Scene(sPane, 400, 120);
        stage.setTitle("Error");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method that sets texts to the TextFields
     */
    private void setText() {
        txtCode.setText(one.getCode());
        txtTitle.setText(one.getTitle());
        txtPrice.setText(one.currencyFormat((one.getPrice())));
    }

    /**
     * Method that sets the font style of labels
     *
     * @param l Label to be styled
     */
    private void setFont(Label l) {
        Font lFont = new Font("Arial Black", 15);
        l.setFont(lFont);
    }

    /**
     * Method that sets TextField to be editable or not
     *
     * @param a makes the TextField editable if false; otherwise not editable
     */
    private void setEditable(Boolean a) {
        txtCode.setEditable(a);
        txtTitle.setEditable(a);
        txtPrice.setEditable(a);
    }

    /**
     * Method that sets Buttons to be enabled or disabled
     *
     * @param a makes the Buttons enable if false; otherwise not disable
     */
    private void disableButtons(Boolean a) {
        btnAdd.setDisable(a);
        btnDelete.setDisable(a);
        btnUpdate.setDisable(a);
        btnExit.setDisable(a);
        btnFirst.setDisable(a);
        btnNext.setDisable(a);
        btnPrevious.setDisable(a);
        btnLast.setDisable(a);
        btnSave.setDisable(!a);
        btnCancel.setDisable(!a);

    }

    /**
     * Method that resizes TextFields
     *
     * @param textField TextField to be resized
     */
    private void resizeTextField(TextField textField) {
        textField.setMinWidth(50);
        textField.setPrefWidth(270);
        textField.setMaxWidth(400);
        textField.setPrefHeight(20);
    }

    /**
     * Main method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method that handles ActionEvents
     *
     * @param e ActionEvent
     */
    @Override
    public void handle(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnNext) {
            try {
                if(!results.isLast() && index < numRows){
                    index++;
                    System.out.println(index);
                }
                f.moveNext(one);
                setText();
            } catch (SQLException z){
                
            }
        } else if (source == btnPrevious) {
            try {
                if(!results.isFirst() && index > 0 && index != 1){
                    index--;
                    System.out.println(index);
                }
                f.movePrevious(one);
                setText();
            } catch (SQLException z){
                
            }
        } else if (source == btnLast) {
                f.moveLast(one);
                index = numRows;
                System.out.println(index);
                setText();
        } else if (source == btnFirst) {
            index = 1;
            System.out.println(index);
            f.moveFirst(one);
            setText();
        } else if (source == btnAdd) {
            addRecordWindow();
            isAddSelected = true;
        } else if (source == btnDelete) {
            isDeleteSelected = true;
            //one.setCode(txtCode.getText());
            f.deleteRecord();
            confirmation("Delete this record?");
        } else if (source == btnUpdate) {
            isUpdateSelected = true;
            isTrue = true;
            disableButtons(isTrue);
            setEditable(isTrue);
            txtPrice.setText(one.removeDollar(txtPrice.getText()));
            txtCode.requestFocus();
        } else if (source == btnCancel) {
            isTrue = false;
            disableButtons(isTrue);
            setEditable(isTrue);
            f.rollback();
            f.getResultSet();
            f.moveToIndex(one, index);
            setText();
        } else if (source == btnSave) {
            try{
                CheckInput.checkCode(txtCode.getText());
                CheckInput.checkTitle(txtTitle.getText());
                CheckInput.checkPrice(txtPrice.getText());
                one.setCode(txtCode.getText());
                one.setTitle(txtTitle.getText());
                one.setPrice(Double.parseDouble(txtPrice.getText()));
                f.updateRecord(one);
                confirmation("Commit this changes?");
            } catch (Exception z) {
                showStage(z.getMessage());
            }
        } else if (source == btnYes) {
            isTrue = false;
            f.commit();
            f.getResultSet();
            addRecordStage.close();
            confirmationStage.close();
            disableButtons(isTrue);
            setEditable(isTrue);
            if (isDeleteSelected == true) {
                numRows--;
                if (index == 1) {
                    f.moveToIndex(one, index);
                } else if (index != 1) {
                    f.moveToIndex(one, --index);
                }
            } else if (isAddSelected == true) {
                numRows++;
            } else if (isUpdateSelected == true) {
                f.moveToIndex(one, index);
            }
            setText();
            isDeleteSelected = false;
            isAddSelected = false;
            isUpdateSelected = false;
        } else if (source == btnNo) {
            f.rollback();
            f.getResultSet();
            confirmationStage.close();
            isDeleteSelected = false;
            isAddSelected = false;
        }
    }
}
