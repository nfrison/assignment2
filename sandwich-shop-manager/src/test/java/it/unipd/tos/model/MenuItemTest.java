////////////////////////////////////////////////////////////////////
// [Nicolo] [Frison] [1147682]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;


import java.util.ArrayList;

import it.unipd.tos.model.MenuItem;

public class MenuItemTest {
    
    @org.junit.Rule
    public ExpectedException error= ExpectedException.none();
    
    @Test
    public void testGetMethods() throws IllegalArgumentException {
        MenuItem bevanda = new MenuItem(MenuItem.products.Bevande,"bevanda",1);
        
        bevanda.getName();
        bevanda.getPrice();
        bevanda.getType();
    }
    
    @Test
    public void testEmptyName() throws IllegalArgumentException {
        this.error.expect(IllegalArgumentException.class);
        this.error.expectMessage("Il nome non deve essere vuoto");
        
        MenuItem bevanda = new MenuItem(MenuItem.products.Bevande,"",1);
    }
    
    @Test
    public void testNegativePrice() throws IllegalArgumentException {
        this.error.expect(IllegalArgumentException.class);
        this.error.expectMessage("Il prezzo non deve essere inferiore o uguale a 0");
        
        MenuItem bevanda = new MenuItem(MenuItem.products.Bevande,"bevanda",-1.5);
    }
    
    @Test
    public void testEmptyNameAndNegativePrice() throws IllegalArgumentException {
        this.error.expect(IllegalArgumentException.class);
        this.error.expectMessage("Il nome non deve essere vuoto");
        
        MenuItem bevanda = new MenuItem(MenuItem.products.Bevande,"",-1.5);
    }
}
