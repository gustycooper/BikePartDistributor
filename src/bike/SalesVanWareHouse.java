package bike;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gusty on 5/4/17.
 */
public class SalesVanWareHouse implements WareHouse {

    String wareHouseName;
    WareHouseInventory svWhDB;

    public SalesVanWareHouse(String whn) {
        svWhDB = new WareHouseInventory();
        wareHouseName = whn;
    }
    @Override
    public boolean addPart(BikePart bp, int count) {
        return svWhDB.addPart(bp, count);
    }

    @Override
    public WareHousePart getPart(String nameOrNum) {
        return svWhDB.getPart(nameOrNum);
    }

    @Override
    public String getWareHouseName() { return wareHouseName; }

    @Override
    public WareHouseInventory getWareHouseInventory() { return svWhDB; }
}
