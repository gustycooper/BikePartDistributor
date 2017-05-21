package bike;

import basicStuff.Cost;
import basicStuff.Part;

/** Defines a bike part by extending a part
 *
 */
public class BikePart extends Part {
    private Cost salePrice;
    private Boolean onSale;

    public BikePart(String name, String number, String listPrice, String salePrice, Boolean onSale) {
        super(name, number, listPrice);
        this.salePrice = new Cost(salePrice);
        this.onSale = onSale;
    }

    public void setSale(boolean onSale) { this.onSale = onSale; }
    public boolean onSale() { return onSale; }

    public String getSalePrice() { return salePrice.toString(); }
    public void setSalePrice(String salePrice) { this.salePrice = new Cost(salePrice); }

    public void updatePriceAttributes(BikePart bp) {
        price = bp.price;
        salePrice = bp.salePrice;
        onSale = bp.onSale;
    }

    public boolean equals(BikePart bp) {
        return  salePrice.equals(bp.salePrice) &&
                onSale == bp.onSale &&
                price.equals(bp.price) &&
                name.equals(bp.name) &&
                number.equals(bp.number);
    }

    public String toString() {
        return name + ", " + number + ", " + price + ", " + salePrice + ", " + onSale;
    }

    public boolean getOnSale() {
        return onSale;
    }
}
