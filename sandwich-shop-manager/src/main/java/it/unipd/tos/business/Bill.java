////////////////////////////////////////////////////////////////////
// [Nicolo] [Frison] [1147682]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

public class Bill implements TakeAwayBill {
    public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException {
        double total = 0;
        int nPanini = 0;
        
        for( MenuItem i : itemsOrdered ) {
            total += i.getPrice();
        }
        
        total -= this.getLowestPricePaninoDiscount(itemsOrdered);
        
        total -= this.getPriceOver50Discount(itemsOrdered);
        
        return total;
    }
    
    private double getLowestPricePaninoDiscount(List<MenuItem> itemsOrdered) {
        double discount = Double.MAX_VALUE;
        int nPanini = 0;
        
        for( MenuItem i : itemsOrdered ) {
            if( i.getType() == MenuItem.products.Panini ) {
                nPanini++;
                discount = i.getPrice() < discount ? i.getPrice() : discount;
            }
        }
        
        if( nPanini > 5 ) {
            discount /= 2;
        } else {
            discount = 0;
        }
        
        return discount;
    }
    
    private double getPriceOver50Discount(List<MenuItem> itemsOrdered) {
        double discount = 0;
        
        for( MenuItem i : itemsOrdered ) {
            if( i.getType() == MenuItem.products.Panini || i.getType() == MenuItem.products.Fritti ) {
                discount += i.getPrice();
            }
        }
        
        if( discount > 50 ) {
            discount = discount * 10 / 100;
        } else {
            discount = 0;
        }
        
        return discount;
    }
}
