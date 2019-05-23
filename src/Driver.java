/*
 * Driver.java
 *
 * Created on April 19, 2007, 3:41 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Anzar
 */
public class Driver {
    
    /** Creates a new instance of Driver */
    public Driver() {
    }
    
    public static void main(String[] args){
        GUIManager g = new GUIManager();
        g.initGUI();
        g.loadPage(g.fout.readHomeFile());
        BtnHandler h=new BtnHandler();
        
        
    
    }
}
