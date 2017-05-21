package bike;

import basicStuff.LoginAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gusty on 5/7/17.
 */
public class BikeDB {
    public WareHouseFactory whf;
    public List<LoginAccount> users;
    public LoginAccount currentUser;

    public BikeDB() {
        whf = new WareHouseFactory();
        users = new ArrayList<>();
    }
}
