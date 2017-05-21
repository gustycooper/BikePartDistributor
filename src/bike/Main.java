package bike;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static boolean DEBUG = true;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("bike.fxml"));
        primaryStage.setTitle("Gusty's Bicycle Parts Distributorship");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }


    public static BikeDB bdb = new BikeDB();

    public static void main(String[] args) {
        bdb.users.add(new SystemAdminstrator("Gusty", "Cooper", "gusty@gusty.bike", "admin", "minda"));
        WareHouseManager gusty = new WareHouseManager("Gusty", "Cooper", "gusty@gusty.bike", "gusty", "gusty", bdb.whf, "Main WH");
        bdb.users.add(gusty);
        gusty.loadMainWareHouse("loadMainWH.txt");

        SalesAssociate emily = new SalesAssociate("emily", "Cooper", "emily@gusty.bike", "emily", "emily", bdb.whf, "Em's WH");
        bdb.users.add(emily);
        emily.loadSalesVanWareHouse("Main WH", "em.txt");

        OfficeManager coletta = new OfficeManager("Coletta", "Cooper", "coletta@gusty.bike", "coletta", "coletta");
        bdb.users.add(coletta);
        launch(args);
    }
}
