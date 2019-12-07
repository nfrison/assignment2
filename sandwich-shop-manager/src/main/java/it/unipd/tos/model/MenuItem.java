////////////////////////////////////////////////////////////////////
// [Nicolo] [Frison] [1147682]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class MenuItem {
    public enum products{Panini, Fritti, Bevande};
    
    private products type;
    private String name;
    private double price;
    
    public MenuItem(products type, String name, double price) {
        this.type = type;
        if( name.equals("") ) {
            throw new IllegalArgumentException("Il nome non deve essere vuoto");
        } else {
            this.name = name;
        }
        if( price <= 0 ) {
            throw new IllegalArgumentException("Il prezzo non deve essere inferiore o uguale a 0");
        } else {
            this.price = price;
        }
    }
    
    public products getType() {
        return this.type;
    }
    
    public String getName() {
        return this.name;
    }
    
    public double getPrice() {
        return this.price;
    }
}
