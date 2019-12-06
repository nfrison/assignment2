////////////////////////////////////////////////////////////////////
// [Nicolo] [Frison] [1147682]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
    
    /*
     *   Calcolo totale base issue #1
     */
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
    
    /*  
     * Calcolo totale sconto 50 issue #2
     */

    // Aggiunta di 5 panini base
    @Test
    public void testDiscount50TotalSum() throws TakeAwayBillException {
        for( int i = 0 ; i < 5 ; i++ ) {
            this.itemsOrdered.add(this.paninoBase);
        }
        
        this.itemsOrdered.add(this.bevandaBase);
        this.itemsOrdered.add(this.frittoBase);
        
        double sum = 5.35*5 + 2.4 + 3.5;
        assertEquals(sum,bill.getOrderPrice(this.itemsOrdered),0.0);
    }
    
    // Aggiunta di 5 panini base e 1 con prezzo minore
    @Test
    public void testDiscount50TotalSum1() throws TakeAwayBillException {
        this.itemsOrdered.add(new MenuItem(MenuItem.products.Panini,"paninoDiscount",3.6));
        for( int i = 0 ; i < 5 ; i++ ) {
            this.itemsOrdered.add(this.paninoBase);
        }
        
        this.itemsOrdered.add(this.bevandaBase);
        this.itemsOrdered.add(this.frittoBase);
        
        double sum = 5.35*5 + 3.6/2 + 2.4 + 3.5;
        assertEquals(sum,bill.getOrderPrice(this.itemsOrdered),0.0);
    }

    // Aggiunta di 5 panini base e 1 con prezzo maggiore
    @Test
    public void testDiscount50TotalSum2() throws TakeAwayBillException {
        this.itemsOrdered.add(new MenuItem(MenuItem.products.Panini,"paninoDiscount",8));
        for( int i = 0 ; i < 5 ; i++ ) {
            this.itemsOrdered.add(this.paninoBase);
        }
        
        this.itemsOrdered.add(this.bevandaBase);
        this.itemsOrdered.add(this.frittoBase);
        
        double sum = 5.35*4 + 5.35/2 + 8 + 2.4 + 3.5;
        assertEquals(sum,bill.getOrderPrice(this.itemsOrdered),0.0001);
    }

    // Aggiunta di 7 panini base
    @Test
    public void testDiscount50TotalSum3() throws TakeAwayBillException {
        for( int i = 0 ; i < 7 ; i++ ) {
            this.itemsOrdered.add(this.paninoBase);
        }
        
        this.itemsOrdered.add(this.bevandaBase);
        this.itemsOrdered.add(this.frittoBase);
        
        double sum = 5.35*6 + 5.35/2 + 2.4 + 3.5;
        assertEquals(sum,bill.getOrderPrice(this.itemsOrdered),0.0001);
    }
    
    /*  
     * Calcolo totale sconto 50 issue #3
     */
    
    // Aggiunta  di 8 panini base e 3 fritti base
    @Test
    public void testDiscount10TotalSum() throws TakeAwayBillException {
        for( int i = 0 ; i < 8 ; i++ ) {
            this.itemsOrdered.add(this.paninoBase);
        }
        
        for( int i = 0 ; i < 3 ; i++ ) {
            this.itemsOrdered.add(this.frittoBase);
        }
        
        this.itemsOrdered.add(this.bevandaBase);
        
        double sum = 
                5.35*8 + 
                3.5*3 +
                (-5.35/2) +
                (-(5.35*8+3.5*3) * 10 / 100) +
                2.4;
        assertEquals(sum,bill.getOrderPrice(this.itemsOrdered),0.0001);
    }
    
    // Aggiunta  di 5 panini base e 7 fritti base
    @Test
    public void testDiscount10TotalSum1() throws TakeAwayBillException {
        for( int i = 0 ; i < 5 ; i++ ) {
            this.itemsOrdered.add(this.paninoBase);
        }
        
        for( int i = 0 ; i < 7 ; i++ ) {
            this.itemsOrdered.add(this.frittoBase);
        }
        
        this.itemsOrdered.add(this.bevandaBase);
        
        double sum = 
                5.35*5 + 
                3.5*7 +
                (-(5.35*5+3.5*7) * 10 / 100) +
                2.4;
        assertEquals(sum,bill.getOrderPrice(this.itemsOrdered),0.0001);
    }
    
    /*  
     * Calcolo totale sconto 50 issue #4
     */
    private ExpectedException error= ExpectedException.none();
    
    // Aggiunta  di 30 panini base
    @Test
    public void testLimit30Items() throws TakeAwayBillException {
        for( int i = 0 ; i < 30 ; i++ ) {
            this.itemsOrdered.add(this.paninoBase);
        }
        
        double sum = 
                5.35*30 - 
                5.35/2 -
                (5.35*30 * 10 / 100);
        assertEquals(sum,bill.getOrderPrice(this.itemsOrdered),0.0001);
    }
    
    // Aggiunta  di 30 fritti base
    @Test
    public void testLimit30Items1() throws TakeAwayBillException {
        for( int i = 0 ; i < 30 ; i++ ) {
            this.itemsOrdered.add(this.frittoBase);
        }
        
        double sum = 
                3.5*30 -
                (3.5*30 * 10 / 100);
        assertEquals(sum,bill.getOrderPrice(this.itemsOrdered),0.0001);
    }
    
    // Aggiunta  di 15 panini base e 15 fritti base
    @Test
    public void testLimit30Items2() throws TakeAwayBillException {
        for( int i = 0 ; i < 15 ; i++ ) {
            this.itemsOrdered.add(this.paninoBase);
            this.itemsOrdered.add(this.frittoBase);
        }
        
        double sum = 
                5.35 * 15 +
                3.5 * 15 -
                5.35/2 -
                ((5.35*15 + 3.5*15) * 10 / 100);
        assertEquals(sum,bill.getOrderPrice(this.itemsOrdered),0.0001);
    }
    
    // Aggiunta  di 16 panini base e 16 fritti base
    @Test
    public void testLimit30Items3() throws TakeAwayBillException {
        this.error.expect(TakeAwayBillException.class);
        this.error.expectMessage("Gli elementi dell'ordine possono essere al massimo 30");
        
        for( int i = 0 ; i < 16 ; i++ ) {
            this.itemsOrdered.add(this.paninoBase);
            this.itemsOrdered.add(this.frittoBase);
        }
        
        this.bill.getOrderPrice(itemsOrdered);
    }
}
