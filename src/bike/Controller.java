package bike;

import basicStuff.Cost;
import basicStuff.LoginAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.time.chrono.MinguoChronology;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Controller {

    @FXML
    private TabPane mainTabPane;

    /**
     * userId is a label that indicates the current logged in user
     */
    @FXML
    private Label userId;

    /**
     * login tab JavaFX variables
     */
    @FXML
    private Tab loginTab;

    @FXML
    private TextField userNameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label loginMessage;

    @FXML
    private Button loginButton;

    /**
     * admin tab JavaFX variables
     */
    @FXML
    private Tab adminTab;

    @FXML
    private Button createAccountButton;

    @FXML
    private Button deleteAccountButton;

    @FXML
    private TextField fnTextField;

    @FXML
    private TextField lnTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private ComboBox<String> accountTypeComboBox;

    @FXML
    private TextField warehouseNameTextField;

    @FXML
    private TextArea createAccountTextArea;

    @FXML
    private Tab officeMangerTab;

    @FXML
    private TextField startDateTextField;

    @FXML
    private TextField endDateTextField;

    @FXML
    private Button commisionButton;

    @FXML
    private TextArea officeManagerTextArea;

    /**
     * warehouse manager tab JavaFX Variables
     */
    @FXML
    private Tab wareHouseManagerTab;

    @FXML
    private TextArea warehouseManagerTextArea;

    @FXML
    private TextField warehouseDeliveryFileTextField;

    @FXML
    private Button loadWarehouseButton;

    @FXML
    private ComboBox<String> viewWarehouseComboBox;

    @FXML
    private Button viewWarehouseButton;

    /**
     * sales associate tab JavaFX variables
     */
    @FXML
    private Tab salesAssociateTab;

    @FXML
    private TextField salesVanDeliveryFileTextField;

    @FXML
    private Button loadVanButton;

    @FXML
    private TextField partNameTextField;

    @FXML
    private TextField partNumTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField salesPriceTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private TextField customerTextField;

    @FXML
    private Button addToInvoiceButton;

    @FXML
    private TextArea invoiceTextArea;

    @FXML
    private TextField receivedByTextField;

    /**
     * part tab JavaFX variables
     */
    @FXML
    private Tab partTab;

    @FXML
    private TextField partPartNameTextField;

    @FXML
    private TextField partPartNumTextField;

    @FXML
    private TextField partPriceTextField;

    @FXML
    private TextField partSalesPriceTextField;

    @FXML
    private TextField partQuantityTextField;

    @FXML
    private Button partViewButton;

    @FXML
    private TextArea partTextArea;

    /* ******************************************** */

    @FXML
    void handleCommission(ActionEvent event) {
        Date start, end;
        String startDate = startDateTextField.getText();
        String endDate = endDateTextField.getText();
        if (startDate.equals("") || endDate.equals("")) { // generate all sales
            start = null;
            end = null;
        }
        else {
            // TODO - convert String startDate to Date start
            start = new Date();
            end = new Date();
        }

        for (LoginAccount a : Main.bdb.users) {
            if (a instanceof SalesAssociate) {
                SalesAssociate sa = (SalesAssociate) a;
                List<SalesInvoice> sil = sa.getSales(start, end);
                for (SalesInvoice si : sil) {
                    officeManagerTextArea.appendText(si.toString());
                }
            }
        }
    }

    /**
     * Methods for processing Sales tab.
     */

    @FXML
    void handleLoadVan(ActionEvent event) {
        String fn = salesVanDeliveryFileTextField.getText();
        SalesAssociate sa = (SalesAssociate)Main.bdb.currentUser;
        sa.loadSalesVanWareHouse("Main WH", fn);
    }

    /**
     * salesInvoice collects a sales invoice as the Add button on Sales tab is selected
     */
    private SalesInvoice salesInvoice;
    //                                   name num  pri sal qty tot
    private String salesInvoiceFormat = "%10s %10s %5s %5s %3s %6s \n";
    private double totalSalesInvoice = 0;

    @FXML
    void handleCreateInvoice(ActionEvent event) {
        String customer = customerTextField.getText();
        if (customer.equals("")) {
            invoiceTextArea.appendText("Must enter customer.");
            return;
        }
        salesInvoice = new SalesInvoice(new Date(), customer);
        invoiceTextArea.appendText("Sales Invoice for: " + customer + "\n");
        invoiceTextArea.appendText("                      List  Today          \n");
        invoiceTextArea.appendText(" Part Name   Part Num Price Price Qty   Total\n");
        totalSalesInvoice = 0;
    }

    @FXML
    void handleInvoicePartName(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String partName = partNameTextField.getText();
            String partNum = partNumTextField.getText();
            String getPart = !partName.equals("") ? partName : partNum;
            WareHousePart whp = Main.bdb.whf.getWareHousePart(getPart);
            if (whp == null) {
                partNameTextField.setText("Not Part!!");
                return;
            }
            partName = whp.getBikePart().getName();
            partNum = whp.getBikePart().getNumber();
            String price = whp.getBikePart().getPrice();
            String salesPrice = whp.getBikePart().getSalePrice();
            SalesAssociate sa = (SalesAssociate)Main.bdb.currentUser;
            WareHousePart saWhp = sa.getWareHouse().getWareHouseInventory().getPart(partName);
            String qty = "" + (saWhp == null ? 0 : saWhp.getCount());
            partNameTextField.setText(partName);
            partNumTextField.setText(partNum);
            priceTextField.setText(price);
            System.out.println(whp.getBikePart().onSale());
            if (!whp.getBikePart().onSale())
                salesPrice = price;
            salesPriceTextField.setText(salesPrice);
            quantityTextField.setText(qty);
            //invoiceTextArea.appendText(whp.toString() + "\n");
        }
    }

    @FXML
    void handleAddToInvoice(ActionEvent event) {
        // TODO - check fields for ""
        // TODO - better display in invoiceTextArea - good format, show customer
        String partName = partNameTextField.getText();
        String partNum = partNumTextField.getText();
        String price = priceTextField.getText();
        String salesPrice = salesPriceTextField.getText();
        String quantity = quantityTextField.getText();
        WareHousePart whp = new WareHousePart(new BikePart(partName, partNum, price, salesPrice, false), Integer.parseInt(quantity));
        salesInvoice.addInvoice(whp);
        //invoiceTextArea.appendText(whp.toString());
        int qty = Integer.parseInt(quantity);
        String total = String.format("%.2f",(Double.parseDouble(salesPrice) * qty));
        SalesAssociate sa = (SalesAssociate)Main.bdb.currentUser;
        sa.getWareHouse().getWareHouseInventory().getPart(partName).updateCount(qty, "subtract");
        totalSalesInvoice += Double.parseDouble(salesPrice) * qty;
        invoiceTextArea.appendText(String.format(salesInvoiceFormat,partName, partNum, price, salesPrice, quantity, total));

    }

    @FXML
    void handleReceivedBy(KeyEvent event) {
        // TODO - add code to terminate the ongoing invoice, clear SA panel
        if (event.getCode() == KeyCode.ENTER) {
            String name = receivedByTextField.getText();
            System.out.println("Received by: " + name);
            invoiceTextArea.appendText(String.format(salesInvoiceFormat,"", "", "", "", "", totalSalesInvoice));
            invoiceTextArea.appendText("Received by: " + name);
            SalesAssociate sa = (SalesAssociate)Main.bdb.currentUser;
            sa.addSalesInvoice(salesInvoice);
        }
    }

    /**
     * Methods for processing Admin tab
     */

    @FXML
    void handleCreateAccount(ActionEvent event) {
        String fn = fnTextField.getText();
        String ln = lnTextField.getText();
        String email = emailTextField.getText();
        String userName = userNameTextField.getText();
        String passWord = passwordTextField.getText();
        String warehouseName = warehouseNameTextField.getText();
        String accountType = "";
        if (accountTypeComboBox.getSelectionModel().getSelectedItem() != null)
            accountType = accountTypeComboBox.getSelectionModel().getSelectedItem().toString();
        if (fn.equals("") || ln.equals("") || userName.equals("") || passWord.equals("") || accountType.equals("")) {
            System.out.println("All fields must be entered.");
            createAccountTextArea.setText("All fields must be entered.");
        }
        else {
            createAccountTextArea.setText("Account Created");
        }
        System.out.println(accountType);
        switch (accountType) {
            case "Warehouse Manager":
                Main.bdb.users.add(new WareHouseManager(fn, ln, email, userName, passWord, Main.bdb.whf, warehouseName));
                break;
            case "Sales Associate":
                Main.bdb.users.add(new SalesAssociate(fn, ln, email, userName, passWord, Main.bdb.whf, warehouseName));
                break;
            case "Office Manager":
                Main.bdb.users.add(new OfficeManager(fn, ln, email, userName, passWord));
                break;
        }

    }

    @FXML
    void handleDeleteAccount(ActionEvent event) {
        // TODO - implement handleDeleteAccount
    }

    /**
     * Methods for processing WHMan tab.
     */

    @FXML
    void handleLoadWarehouse(ActionEvent event) {
        String fn = warehouseDeliveryFileTextField.getText();
        WareHouseManager whm = (WareHouseManager)Main.bdb.currentUser;
        whm.loadMainWareHouse(fn);

    }

    @FXML
    void handleViewWarehouse(ActionEvent event) {
        WareHouseManager whm = (WareHouseManager)Main.bdb.currentUser;
        String warehouseName = "All";
        if (viewWarehouseComboBox.getSelectionModel().getSelectedItem() != null)
            warehouseName = viewWarehouseComboBox.getSelectionModel().getSelectedItem().toString();

        if (warehouseName.equals("All")) {
            for (WareHouse wh : Main.bdb.whf.allWareHouses) {
                for (WareHousePart whi : wh.getWareHouseInventory()) {
                    warehouseManagerTextArea.appendText(wh.getWareHouseName() + ": " + whi + "\n");
                }
                warehouseManagerTextArea.appendText("\n");
            }

        }
        else {
            WareHouse wh = Main.bdb.whf.getWareHouseByName(warehouseName);
            for (WareHousePart whi : wh.getWareHouseInventory()) {
                warehouseManagerTextArea.appendText(wh.getWareHouseName() + ": " + whi.toString() + "\n");
            }
            warehouseManagerTextArea.appendText("\n");
        }


    }

    /**
     * Metods for processing Part tab
     */

    @FXML
    void handlePartView(ActionEvent event) {
        String partName = partPartNameTextField.getText();
        String partNum = partPartNumTextField.getText();
        if (partName.equals("") && partNum.equals("")) {
            partTextArea.appendText("Enter a Part Name or Part Number.");
            return;
        }
        // TODO - Add getPart method to WareHouseFactory that returns whp
        boolean partFound = false;
        for (LoginAccount a : Main.bdb.users) {
            if (a instanceof WareHouseManager) {
                WareHousePart whp = null;
                String getPart = !partName.equals("") ? partName : partNum;
                whp = ((WareHouseManager) a).getWarehouse().getPart(getPart);
                if (whp != null) {
                    partName = whp.getBikePart().getName();
                    partNum = whp.getBikePart().getNumber();
                    String price = whp.getBikePart().getPrice();
                    String salesPrice = whp.getBikePart().getSalePrice();
                    String qty = "" + Main.bdb.whf.getCount(whp.getBikePart());
                    partPartNameTextField.setText(partName);
                    partPartNumTextField.setText(partNum);
                    partPriceTextField.setText(price);
                    partSalesPriceTextField.setText(salesPrice);
                    partQuantityTextField.setText(qty);
                    System.out.println(whp.toString());
                    System.out.println(partName + "," + partNum + "," + price + "," + salesPrice);
                    partTextArea.appendText(whp.toString() + "\n");
                    partFound = true;
                }
            }
        }
        if (!partFound) {
            partTextArea.appendText("Part not found.");
        }
    }

    /**
     * Methods for processing Login tab.
     */

    @FXML
    void handleLoginButton(ActionEvent event) {
        boolean validLogin = false;
        String user = userNameField.getText();
        String pw = passwordField.getText();
        SingleSelectionModel<Tab> selectionModel = mainTabPane.getSelectionModel();
        LoginAccount loggedIn = null;
        System.out.println(user + " " + pw);
        for (LoginAccount a : Main.bdb.users) {
            System.out.println(a);
            if (a.validateLoginAccount(user, pw)) {
                    System.out.println("Login Valid");
                    userNameField.setText("");
                    passwordField.setText("");
                    loginMessage.setText("");
                    validLogin = true;
                    userId.setText(user);
                    loggedIn = a;
                    Main.bdb.currentUser = loggedIn;
                }
        }
        if (user.equals("debug")) {
            System.out.println("Number of users: " + Main.bdb.users.size());
            for (LoginAccount la : Main.bdb.users)
                System.out.println("User: " + la);
            System.out.println();
            for (WareHouse wh : Main.bdb.whf.allWareHouses) {
                for (WareHousePart whi : wh.getWareHouseInventory()) {
                    System.out.println(wh.getWareHouseName() + whi);
                }
                System.out.println();
            }
        }
        if (!validLogin) {
            loginMessage.setText("Username or Password invalid.");
            return;
        }
        if (loggedIn instanceof SystemAdminstrator) {
            adminTab.setDisable(false);
            partTab.setDisable(false);
            loginTab.setDisable(true);
            officeMangerTab.setDisable(false);
            wareHouseManagerTab.setDisable(false);
            salesAssociateTab.setDisable(false);
            accountTypeComboBox.getItems().removeAll(accountTypeComboBox.getItems());
            accountTypeComboBox.getItems().addAll("Office Manager", "Warehouse Manager", "Sales Associate");
            selectionModel.select(adminTab);
        }
        else if (loggedIn instanceof OfficeManager) {
            adminTab.setDisable(true);
            loginTab.setDisable(true);
            officeMangerTab.setDisable(false);
            partTab.setDisable(false);
            wareHouseManagerTab.setDisable(true);
            salesAssociateTab.setDisable(true);
            selectionModel.select(officeMangerTab);
        }
        else if (loggedIn instanceof WareHouseManager) {
            adminTab.setDisable(true);
            loginTab.setDisable(true);
            officeMangerTab.setDisable(true);
            wareHouseManagerTab.setDisable(false);
            partTab.setDisable(false);
            salesAssociateTab.setDisable(true);
            String[] warehouseNames = Main.bdb.whf.getWareHouseNames();
            String[] warehouseNamesWithAll = new String[warehouseNames.length + 1];
            warehouseNamesWithAll[0] = "All"; // prepend "All"
            System.arraycopy(warehouseNames, 0,warehouseNamesWithAll, 1, warehouseNames.length);
            viewWarehouseComboBox.getItems().removeAll(viewWarehouseComboBox.getItems());
            viewWarehouseComboBox.getItems().addAll(warehouseNamesWithAll);
            warehouseDeliveryFileTextField.setText("");
            warehouseManagerTextArea.setText("");
            selectionModel.select(wareHouseManagerTab);
        }
        else if (loggedIn instanceof SalesAssociate) {
            adminTab.setDisable(true);
            loginTab.setDisable(true);
            officeMangerTab.setDisable(true);
            wareHouseManagerTab.setDisable(true);
            salesAssociateTab.setDisable(false);
            selectionModel.select(salesAssociateTab);
        }

    }

    @FXML
    void handleLogoutButton(ActionEvent event) {
        loginTab.setDisable(false);
        adminTab.setDisable(true);
        officeMangerTab.setDisable(true);
        wareHouseManagerTab.setDisable(true);
        salesAssociateTab.setDisable(true);
        partTab.setDisable(true);
        userId.setText("");
        SingleSelectionModel<Tab> selectionModel = mainTabPane.getSelectionModel();
        selectionModel.select(loginTab);
    }

}

