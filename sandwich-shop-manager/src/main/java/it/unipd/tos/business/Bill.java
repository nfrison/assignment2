////////////////////////////////////////////////////////////////////
// [Nicolo] [Frison] [1147682]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

public class Bill implements TakeAwayBill {
    private final double commission = 0.5;
    
    public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException {
        double total = 0;
        int itemsN = 0;
        
        for( MenuItem i : itemsOrdered ) {
            total += i.getPrice();
            itemsN++;
        }
        
        if( itemsN == 0 ) {
            throw new TakeAwayBillException("Ordine vuoto");
        }
        
        total -= this.getLowestPricePaninoDiscount(itemsOrdered);
        
        total -= this.getPriceOver50Discount(itemsOrdered);
        
        if( itemsN > 30 ) {
            throw new TakeAwayBillException("Gli elementi dell'ordine possono essere al massimo 30");
        }
        
        if( total <= 10 ) {
            total += this.commission;
        }
        
        return total;
    }
    
    private double getLowestPricePaninoDiscount(List<MenuItem> itemsOrdered) {
        double discount = Double.MAX_VALUE;
        int paniniN = 0;
        
        for( MenuItem i : itemsOrdered ) {
            if( i.getType() == MenuItem.products.Panini ) {
                paniniN++;
                discount = i.getPrice() < discount ? i.getPrice() : discount;
            }
        }
        
        if( paniniN > 5 ) {
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
