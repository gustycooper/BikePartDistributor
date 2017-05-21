package bike;

/**
 * Created by gusty on 5/4/17.
 */
public interface WareHouse {
    /**
     * Add a part to the warehouse
     *
     * If the part is already in the warehouse, the part price attributes are updated
     * @param bp bike part to be added
     * @return returns true of part added, otherwise false
     */
    boolean addPart(BikePart bp, int count);
    WareHousePart getPart(String nameOrNum);
    String getWareHouseName();
    WareHouseInventory getWareHouseInventory();
}
