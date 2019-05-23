/*
 * GUIManager.java
 *
 * Created on April 19, 2007, 3:25 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import static com.sun.javafx.tk.Toolkit.getToolkit;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;


/**
 *
 * @author Anzar
 */
public class GUIManager {
    JFrame fMain;
    JEditorPane jepMain;
    JPanel jpanel,jpanel2,jpanel1,jpanel3,jpanel4;
    JMenuBar menu;
    BtnHandler hnd;
    radio_handler rhnd;
    String str,mainStr;
    boolean fire;
    //JScrollPane scrPane;
    
    JProgressBar jpb;
    JSlider slider;
    Firewall fGUI;
    JButton btnGo,btnPrev,btnNxt,btnRef,btnHome,btnSearch,btnFav,btnHis,btnOpenFav;
    static JTextField tfAddress;
    Filing fout = new Filing();
    
    /** Creates a new instance of GUIManager */
    public GUIManager() {
        fire=true;
    }
    public void initGUI(){
        
        
        fMain = new JFrame("Web Browser");
        jpanel = new JPanel();
        jpanel1 = new JPanel();
        jpanel2 = new JPanel();
        jpanel3 = new JPanel();
        jpanel4 = new JPanel();
        jpb = new JProgressBar();
        
        hnd=new BtnHandler();
        rhnd=new radio_handler();
        hnd.btnHandler(this);
        //rhnd.radio_handler(this);
        
       
        jepMain = new JEditorPane();
        jepMain.setEditable(false);
        
        
        slider = new JSlider();
        
        hnd.stack1.push(fout.readHomeFile());
        
        menu = new JMenuBar();
        
        JMenuItem set = new JMenuItem("set");
        set.addActionListener(hnd);
        //JMenuItem view1 = new JMenuItem("View Home Page");
        //view1.addActionListener(hnd);
        
        JMenuItem clr = new JMenuItem("Clear History");
        clr.addActionListener(hnd);
        
        JMenu setHome = new JMenu("Home");
        setHome.addActionListener(hnd);
        menu.add(setHome);
        //setHome.add(view1);
        setHome.add(set);
        
        JMenu clrhis = new JMenu("History");
        clrhis.addActionListener(hnd);
        menu.add(clrhis);
        clrhis.add(clr);
        
        JMenu firewall=new JMenu("FireWall");
        menu.add(firewall);
        
        JMenuItem add=new JMenuItem("Add");
        add.addActionListener(hnd);
        JMenuItem del=new JMenuItem("Delete");
        del.addActionListener(hnd);
        JMenuItem view2=new JMenuItem("View Keywords");
        view2.addActionListener(hnd);
        JMenuItem toggle=new JMenuItem("Toggle Firewall");
        fGUI=new Firewall(this);
        toggle.addActionListener(hnd);
        
        
        firewall.add(add);
        firewall.add(del) ;
        firewall.add(view2) ;
        firewall.add(toggle);
        
        
        
        jepMain.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if(e.getEventType()==HyperlinkEvent.EventType.ACTIVATED)
                    loadPage(e.getURL().toString());
            }
        });
        
        
        
        slider.addMouseWheelListener(new MouseWheelListener() {
        public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0) {
            System.out.println("Mouse wheel moved UP " + -notches + " notch(es)");
            slider.setValue(slider.getValue() + 1);
        } else
        if (notches > 0) {
            System.out.println("Mouse wheel moved DOWN " + notches + " notch(es)");
            slider.setValue(slider.getValue() - 1);
        }
            }
        });
        
        
        
        
        

        btnPrev = new JButton("prev",(new ImageIcon("C:\\Users\\aizazshahid\\Desktop\\BasicWebBrowser\\previous.png")));
        btnPrev.addActionListener(hnd);
        btnPrev.setEnabled(false);
        
        
        btnNxt = new JButton("next",new ImageIcon("C:\\Users\\aizazshahid\\Desktop\\BasicWebBrowser\\next.png"));
        btnNxt.addActionListener(hnd);
        btnNxt.setEnabled(false);
        
        btnHome = new JButton("home",new ImageIcon("C:\\\\Users\\\\aizazshahid\\\\Desktop\\\\BasicWebBrowser\\\\home-xl.png"));
        btnHome.addActionListener(hnd);
        
        btnRef = new JButton("refresh",new ImageIcon("C:\\Users\\aizazshahid\\Desktop\\BasicWebBrowser\\refresh-xl.png"));
        btnRef.addActionListener(hnd);
        
        btnSearch = new JButton("search",new ImageIcon("C:\\Users\\aizazshahid\\Desktop\\BasicWebBrowser\\search.png"));
        btnSearch.addActionListener(hnd);
        
        btnOpenFav = new JButton("openfav",new ImageIcon("C:\\Users\\aizazshahid\\Desktop\\BasicWebBrowser\\favourites.png"));
        btnOpenFav.addActionListener(hnd);
        
        btnFav = new JButton("fav",new ImageIcon("C:\\Users\\aizazshahid\\Desktop\\BasicWebBrowser\\favourite-xl.png"));
        btnFav.addActionListener(hnd);
        
        btnGo = new JButton("Go");
        btnGo.addActionListener(hnd);
        
        btnHis = new JButton("history",new ImageIcon("C:\\Users\\aizazshahid\\Desktop\\BasicWebBrowser\\history.png"));
        btnHis.addActionListener(hnd);
        
        tfAddress = new JTextField(30);
        tfAddress.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            loadPage(e.getActionCommand());
                
                
            }
        });
        JScrollPane scrPane = new JScrollPane(jepMain,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        Container c = fMain.getContentPane();
        c.setLayout(new BorderLayout());
        
        
        jpanel.setSize(800, 250);
        jpanel1.setSize(800, 150);
        jpanel2.setSize(800, 100);
        
        jpanel1.setLayout(new FlowLayout());
        jpanel2.setLayout(new FlowLayout());
        jpanel.setLayout(new BorderLayout());
        jpanel3.setLayout(new BorderLayout());
        jpanel1.setBackground(Color.WHITE);
        jpanel2.setBackground(Color.WHITE);
        
        
        jpanel3.add(menu,BorderLayout.NORTH);
        jpanel.add(jpanel3,BorderLayout.NORTH);
        jpanel3.add(jpanel1,BorderLayout.SOUTH);
        jpanel4.setLayout(new BorderLayout());
        
        jpanel1.add(btnPrev);
        jpanel1.add(btnNxt);
        jpanel1.add(btnHome);
        jpanel1.add(btnRef);
        
        jpanel1.add(btnFav);
        jpanel1.add(btnOpenFav);
        jpanel1.add(btnHis);
        jpanel1.add(btnSearch);
        jpanel.add(jpanel2,BorderLayout.SOUTH);
        
        
        jpanel2.add(tfAddress);
        jpanel2.add(btnGo);
        jpanel4.add(scrPane,BorderLayout.CENTER);
        jpanel4.add(jpb,BorderLayout.SOUTH);
        
        

        
        
        fMain.setLayout(new BorderLayout());
        fMain.add(jpanel,BorderLayout.NORTH);
        
        fMain.add(jpanel4,BorderLayout.CENTER);
        fMain.setSize(800,600);
        fMain.setResizable(true);
        fMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fMain.setLocation(200,200);
        fMain.setVisible(true);
        fMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void loadPage(String url){
        try{
            if (fire==true){
                if (fout.findWord(tfAddress.getText())) {
                    JOptionPane.showMessageDialog(null,"Sorry! Browser Cannot Open The Requested URL","Not Allowed",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    jepMain.setPage(url);
                    tfAddress.setText(url);
                    fout.writeHisFile(tfAddress.getText());
                }
            }
            else
            {
                jepMain.setPage(url);
                tfAddress.setText(url);
                fout.writeHisFile(tfAddress.getText());
            }
        }
        catch(IOException ioexp){
            JOptionPane.showMessageDialog(null,"page not found","bad url",JOptionPane.ERROR_MESSAGE);    
        }
    }
}
