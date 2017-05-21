package bike;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gusty on 5/4/17.
 */
public class MainWareHouse implements WareHouse {

    String wareHouseName;
    WareHouseInventory mWhDB;

    public MainWareHouse(String whn) {
        mWhDB = new WareHouseInventory();
        wareHouseName = whn;
    }
    @Override
    public boolean addPart(BikePart bp, int count) {
        return mWhDB.addPart(bp, count);
    }

    @Override
    public WareHousePart getPart(String nameOrNum) {
        return mWhDB.getPart(nameOrNum);
    }

    @Override
    public String getWareHouseName() { return wareHouseName; }

    @Override
    public WareHouseInventory getWareHouseInventory() { return mWhDB; }
}
