package bike;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Created by gusty on 5/4/17.
 */
public class WareHouseInventory implements Iterable<WareHousePart> {

    List<WareHousePart> db;

    public WareHouseInventory() {
        db = new ArrayList<WareHousePart>();
    }

    public boolean addPart(BikePart bp, int count) {
        boolean newPart = true;
        for (WareHousePart whItem : db) {
            if (whItem.getBikePart().equals(bp)) {
                whItem.getBikePart().updatePriceAttributes(bp);
                whItem.updateCount(count, "add");
                newPart = false;
                break;
            }
        }
        if (newPart)
            db.add(new WareHousePart(bp, count));
        return true;
    }

    public WareHousePart getPart(String nameOrNum) {
        for (WareHousePart whItem : db) {
            if (whItem.getBikePart().getName().equals(nameOrNum) || whItem.getBikePart().getNumber().equals(nameOrNum)) {
                return whItem;
            }
        }
        return null;
    }

    public boolean movePart(BikePart bpToMove, int numberToMove, WareHouse whFrom, WareHouse whTo) {
        return true;

    }

    @Override
    public Iterator<WareHousePart> iterator() {
        return new Iterator<WareHousePart>() {
            private final Iterator<WareHousePart> iter = db.iterator();
            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public WareHousePart next() {
                return iter.next();
            }
            @Override
            public void remove() {}
        };
    }
}
