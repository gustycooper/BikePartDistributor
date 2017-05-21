package basicStuff;

/**
 * Interface for a generic Warehouse
 */
public interface WareHouse<PART> {
    /**
     * Add a generic PART to the warehouse
     *
     * If the part is already in the warehouse, the part price attributes are updated
     * @param part generic part to be added
     * @param count number of parts to be added
     * @return returns true of part added, otherwise false
     */
    boolean addPart(PART part, int count);

    /**
     * Return a generic PART
     * @param nameOrNum String that can be part name or part number
     * @return the generic PART or null
     */
    PART getPart(String nameOrNum);
}
