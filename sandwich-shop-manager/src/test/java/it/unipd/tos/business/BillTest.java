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
    
    @Test
    public void testEmptyList() throws TakeAwayBillException {
        assertEquals(0,bill.getOrderPrice(this.itemsOrdered),0.0);
    }
    
    @Test
    public void testOnlyOnePanino() throws TakeAwayBillException {
        this.itemsOrdered.add(this.paninoBase);
        
        assertEquals(5.35,bill.getOrderPrice(this.itemsOrdered),0.0);
    }
    
    @Test
    public void testOnlyOneBevanda() throws TakeAwayBillException {
        this.itemsOrdered.add(this.bevandaBase);
        
        assertEquals(2.4,bill.getOrderPrice(this.itemsOrdered),0.0);
    }
    
    @Test
    public void testOnlyOneFritto() throws TakeAwayBillException {
        this.itemsOrdered.add(this.frittoBase);
        
        assertEquals(3.5,bill.getOrderPrice(this.itemsOrdered),0.0);
    }
    
    @Test
    public void testBaseTotalSum() throws TakeAwayBillException {
        this.itemsOrdered.add(this.paninoBase);
        this.itemsOrdered.add(this.bevandaBase);
        this.itemsOrdered.add(this.frittoBase);
        
        assertEquals(11.25,bill.getOrderPrice(this.itemsOrdered),0.0);
    }
}
