
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aizazshahid
 */
public class Firewall {
    JFrame fr;
    GUIManager g;
    JRadioButton on=new JRadioButton("ON",true);
    JRadioButton off=new JRadioButton("OFF",false);
    ButtonGroup group=new ButtonGroup();
    public Firewall(GUIManager G){
        g=G;
    }
    public void initFire(){
        fr=new JFrame("Toggle");
        fr.setSize(180,100);
        fr.setLocationRelativeTo(null);
        fr.setLayout(new FlowLayout());
        fr.setVisible(true);
        fr.add(on);
        fr.add(off);
        group.add(on);
        group.add(off);
        
        on.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                g.fire=true;
            }});
        off.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                g.fire=false;
            }
        });
    }
}
