////////////////////////////////////////////////////////////////////
// [Nicolo] [Frison] [1147682]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


import java.util.ArrayList;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.business.Bill;

public class BillTest {
    
    private Bill bill;
    private List<MenuItem> itemsOrdered;
    private MenuItem paninoBase;
    private MenuItem bevandaBase;
    private MenuItem frittoBase;
    
    @Before
    public void before()
    {
        bill = new Bill();
        itemsOrdered = new ArrayList<MenuItem>();
        paninoBase = new MenuItem(MenuItem.products.Panini,"panino1",5.35);
        bevandaBase = new MenuItem(MenuItem.products.Bevande,"bevanda1",2.4);
        frittoBase = new MenuItem(MenuItem.products.Fritti,"fritto1",3.5);
    }
    
    //  Calcolo totale base issue #1
    @Test
    public void testEmptyList() throws TakeAwayBillException {
        assertEquals(0,bill.getOrderPrice(this.itemsOrdered),0.0);
    }
    
    @Test
    public void testOnlyOnePanino() throws TakeAwayBillException {
        this.itemsOrdered.add(this.paninoBase);
        
        double sum = 5.35;
        assertEquals(sum,bill.getOrderPrice(this.itemsOrdered),0.0);
    }
    
    @Test
    public void testOnlyOneBevanda() throws TakeAwayBillException {
        this.itemsOrdered.add(this.bevandaBase);
        
        double sum = 2.4;
        assertEquals(sum,bill.getOrderPrice(this.itemsOrdered),0.0);
    }
    
    @Test
    public void testOnlyOneFritto() throws TakeAwayBillException {
        this.itemsOrdered.add(this.frittoBase);
        
        double sum = 3.5;
        assertEquals(sum,bill.getOrderPrice(this.itemsOrdered),0.0);
    }
    
    @Test
    public void testBaseTotalSum() throws TakeAwayBillException {
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.bevandaBase);
        this.itemsOrdered.add(this.frittoBase);
        
        double sum = 5.35 + 2.4 + 3.5;
        assertEquals(sum,bill.getOrderPrice(this.itemsOrdered),0.0);
    }
    
    //  Calcolo totale sconto 50 issue #2

    // Aggiunta di 5 panini base
    @Test
    public void testDiscount50TotalSum() throws TakeAwayBillException {
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        
        this.itemsOrdered.add(this.bevandaBase);
        this.itemsOrdered.add(this.frittoBase);
        
        double sum = 5.35*5 + 2.4 + 3.5;
        assertEquals(sum,bill.getOrderPrice(this.itemsOrdered),0.0);
    }
    
    // Aggiunta di 5 panini base e 1 con prezzo minore
    @Test
    public void testDiscount50TotalSum1() throws TakeAwayBillException {
        this.itemsOrdered.add(new MenuItem(MenuItem.products.Panini,"paninoDiscount",3.6));
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        
        this.itemsOrdered.add(this.bevandaBase);
        this.itemsOrdered.add(this.frittoBase);
        
        double sum = 5.35*5 + 3.6/2 + 2.4 + 3.5;
        assertEquals(sum,bill.getOrderPrice(this.itemsOrdered),0.0);
    }

    // Aggiunta di 5 panini base e 1 con prezzo maggiore
    @Test
    public void testDiscount50TotalSum2() throws TakeAwayBillException {
        this.itemsOrdered.add(new MenuItem(MenuItem.products.Panini,"paninoDiscount",8));
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        
        this.itemsOrdered.add(this.bevandaBase);
        this.itemsOrdered.add(this.frittoBase);
        
        double sum = 5.35*4 + 5.35/2 + 8 + 2.4 + 3.5;
        assertEquals(sum,bill.getOrderPrice(this.itemsOrdered),0.0001);
    }

    // Aggiunta di 7 panini base
    @Test
    public void testDiscount50TotalSum3() throws TakeAwayBillException {
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.paninoBase);
        
        this.itemsOrdered.add(this.bevandaBase);
        this.itemsOrdered.add(this.frittoBase);
        
        double sum = 5.35*6 + 5.35/2 + 2.4 + 3.5;
        System.out.println(sum);
        System.out.println(bill.getOrderPrice(this.itemsOrdered));
        assertEquals(sum,bill.getOrderPrice(this.itemsOrdered),0.0001);
    }
}
