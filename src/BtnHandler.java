


import java.awt.BorderLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aizazshahid
 */
public class BtnHandler implements ActionListener
{
    GUIManager refg;
    Stack stack1,stack2;
    JFrame jframe,jframe1,jframe2,jframe3;
    JPanel p1,p2;
    JButton ok;
    //JRadioButton <String>viewButton,
    public JList<String> firewallList,favList,hisList;
    radio_handler rhnd;
    
   public void btnHandler(GUIManager ih)
   {
    refg= ih;  
    stack1 = new Stack();
    stack2 = new Stack();
    
   }
    
    String str,mainStr;
    Filing fout = new Filing();   
    
    //GUIManager temp=new GUIManager();
    
    
    public void actionPerformed(ActionEvent e) 
    {
        rhnd = new radio_handler();
        rhnd.radio_handler(this);
        try{
        
        if (e.getActionCommand().equals("set")) {
            //str = JOptionPane.showInputDialog("");
            //fout.writeHomeFile(str);
            
            
            ok = new JButton("OK");
            
            jframe3 = new JFrame();
            jframe3.getContentPane();
            jframe3.setLayout(new BorderLayout());
            
            p1 = new JPanel();
            
            p1.setLayout(new BorderLayout());
            p2 = new JPanel();
            
            p2.setLayout(new BorderLayout());
            
            JRadioButton viewButton = new JRadioButton("View Current Home Page");
            viewButton.setActionCommand("viewString");

            JRadioButton currentButton = new JRadioButton("Set Current");
            currentButton.setActionCommand("CurrentString");

            JRadioButton setButton = new JRadioButton("Set Home Page");
            setButton.setActionCommand("setString");

            //Group the radio buttons.
            ButtonGroup group = new ButtonGroup();
            group.add(viewButton);
            group.add(currentButton);
            group.add(setButton);

            //Register a listener for the radio buttons.
            viewButton.addActionListener(rhnd);
            currentButton.addActionListener(rhnd);
            setButton.addActionListener(rhnd);
            ok.addActionListener(rhnd);
        
            p1.add(viewButton,BorderLayout.NORTH);
            p1.add(currentButton,BorderLayout.CENTER);
            p1.add(setButton,BorderLayout.SOUTH);
            
            p2.add(ok,BorderLayout.EAST);
            
            jframe3.add(p1,BorderLayout.CENTER);
            jframe3.add(p2,BorderLayout.SOUTH);
            
            jframe3.setLocation(200, 300);
            jframe3.setSize(200, 300);
            jframe3.setVisible(true);
            refg.fMain.add(jframe3);
        }
        /*else if(e.getActionCommand().equals("View Home Page")) {
            JOptionPane.showMessageDialog(null,fout.readHomeFile(),"Home Page",JOptionPane.PLAIN_MESSAGE);
        }*/
        else if(e.getActionCommand().equals("View Keywords")) {
            
            String currentLine;
            DefaultListModel listModel = new DefaultListModel();
            fout.readFireFile();
            System.out.println(fout.fireStack.size());
            
            //currentLine = (String)fout.fireStack.pop();
            
                
            if (fout.fireStack.empty()) {
                JOptionPane.showMessageDialog(null,"No Keywords to Display","Failed",JOptionPane.ERROR_MESSAGE);
            }
            else{
            //create the list
            jframe = new JFrame();
            jframe.getContentPane();
            jframe.setLayout(new BorderLayout());
            
            while(!fout.fireStack.empty())
            {
                
                currentLine = (String)fout.fireStack.pop();
                listModel.addElement(currentLine);
            }
            
            firewallList = new JList(listModel);
            
            JScrollPane scrPane1 = new JScrollPane(firewallList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            jframe.add(scrPane1,BorderLayout.CENTER);
            jframe.setLocation(200, 300);
            jframe.setSize(200, 300);
            jframe.setVisible(true);
            refg.fMain.add(jframe);
            }
        }
        else if (e.getActionCommand().equals("Add")) {
            str = JOptionPane.showInputDialog("");
            fout.writeFireFile(str);
        }
        else if (e.getActionCommand().equals("Clear History")) {
            
            fout.clearFireFile(" ");
        }
        else if (e.getActionCommand().equals("Delete")) {
            str = JOptionPane.showInputDialog("");
            try {
                fout.removeLine(str);
            }catch (IOException ex) {
                Logger.getLogger(GUIManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (e.getActionCommand().equals("Toggle Firewall")) {
            refg.fGUI.initFire();
            
        }
        else if(e.getActionCommand().equals("prev")) {
            
            refg.btnNxt.setEnabled(true);
            stack2.push(stack1.pop());
            refg.loadPage((String)stack1.lastElement());
            System.out.println(stack1.size());
            if (stack1.size()==1) {
                refg.btnPrev.setEnabled(false);
            }
        }
        else if(e.getActionCommand().equals("next")) {
            String temp;
            temp = (String)stack2.pop();
            refg.loadPage(temp);
            stack1.push(temp);
            if (stack2.empty()) {
                refg.btnNxt.setEnabled(false);
            }
            if (!stack1.empty()) {
                refg.btnPrev.setEnabled(true);
            }
        }
        else if(e.getActionCommand().equals("home")) {
            //refg.loadPage(fout.readHomeFile());
            refg.jepMain.setPage(fout.readHomeFile());
            refg.tfAddress.setText(fout.readHomeFile());
            fout.writeHisFile(refg.tfAddress.getText());
            
            if (!stack1.empty()) {
                stack1.push(refg.tfAddress.getText());
            }
        }
        else if(e.getActionCommand().equals("refresh")) {
            refg.loadPage(refg.tfAddress.getText());
        }
        else if(e.getActionCommand().equals("openfav")) {
            
            String currentLine;
            DefaultListModel listModel = new DefaultListModel();
            fout.readFavFile();
            System.out.println(fout.favStack.size());
            //currentLine = (String)fout.favStack.pop();
            if (fout.favStack.empty()) {
                JOptionPane.showMessageDialog(null,"No URL Favourite","Failed",JOptionPane.ERROR_MESSAGE);
            }
            else{
            while(!fout.favStack.empty())
            {
                
                currentLine = (String)fout.favStack.pop();
                listModel.addElement(currentLine);
            }
                
            jframe1 = new JFrame();
            jframe1.getContentPane();
            jframe1.setLayout(new BorderLayout());
 
            //create the list
            favList = new JList(listModel);
            
            favList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            
            favList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                if(!e.getValueIsAdjusting()) {
                    
                    refg.btnPrev.setEnabled(true);
                    List<String> selectedValuesList = favList.getSelectedValuesList();
                    String str = selectedValuesList.toString();
                    str = str.substring(1, str.length()-1);
                    refg.tfAddress.setText(str);
                    
                    refg.loadPage(str);
                    stack1.push(refg.tfAddress.getText());
                    
                    System.out.println(selectedValuesList);
                }
            }
            });   
            
            JScrollPane scrPane1 = new JScrollPane(favList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            jframe1.add(scrPane1,BorderLayout.CENTER);
            jframe1.setLocation(400, 100);
            jframe1.setSize(200, 200);
            jframe1.setVisible(true);
            refg.fMain.add(jframe1);
            }
        }
        else if(e.getActionCommand().equals("search")) {
            
            
            String text=refg.jepMain.getText();
            String key=JOptionPane.showInputDialog("Enter the text you wanted to search:");
                
            int counter=0;
            for (int i=0; (i=text.indexOf(key, i))!= -1 ;i++)
                counter++;
                
            JOptionPane.showMessageDialog(null, "Number of occurences are: " + counter);
            //str = JOptionPane.showInputDialog("");
            //mainStr = refg.jepMain.getText();
            System.out.println(str);
            System.out.println(mainStr);
            /*int j=0;
            
            if (mainStr.toLowerCase().contains(str.toLowerCase())) {
                j++;
            }
            
                
            //if(e.getActionCommand().equals("OK")) {
            JOptionPane.showMessageDialog(null,j,"Search Result",JOptionPane.PLAIN_MESSAGE);
            
            //}*/
            
        }
        else if(e.getActionCommand().equals("fav")) {
            String str4 = refg.tfAddress.getText();
                
            System.out.println(str4);
            if (str4.length()>0) {
                JOptionPane.showMessageDialog(null,"Marked as Favourite","Done",JOptionPane.PLAIN_MESSAGE);
                fout.writeFavFile(refg.tfAddress.getText());
                
                //System.out.println("some text");
            }
            else{
                JOptionPane.showMessageDialog(null,"No URL to Mark as Favourite","Failed",JOptionPane.ERROR_MESSAGE);
                    //System.out.println("no url");
                    
            }
        }
        else if(e.getActionCommand().equals("Go")) {
            String str4 = refg.tfAddress.getText();
                
            System.out.println(str4);
            if (str4.length()>0) {
                
                if (!fout.findWord(refg.tfAddress.getText())) {
                    refg.btnPrev.setEnabled(true);
                    stack1.push(refg.tfAddress.getText());
                }
                refg.loadPage(refg.tfAddress.getText());
                
            }
            else{
                JOptionPane.showMessageDialog(null,"NO URL","Sorry",JOptionPane.ERROR_MESSAGE);
                
                    
            }
        }
        else if(e.getActionCommand().equals("history")) {
            
            String currentLine;
            DefaultListModel listModel = new DefaultListModel();
            fout.readHisFile();
            System.out.println(fout.hisStack.size());
            
            
            if (fout.hisStack.empty()) {
                JOptionPane.showMessageDialog(null,"No History to Show","Failed",JOptionPane.ERROR_MESSAGE);
            }
            else{
            
            jframe2 = new JFrame();
            jframe2.getContentPane();
            jframe2.setLayout(new BorderLayout());    
                
            while(!fout.hisStack.empty())
            {
                
                currentLine = (String)fout.hisStack.pop();
                listModel.addElement(currentLine);
            }
                
            
 
            //create the list
            hisList = new JList(listModel);
            
            hisList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            
            hisList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                if(!e.getValueIsAdjusting()) {
                    final List<String> selectedValuesList = hisList.getSelectedValuesList();
                    String str = selectedValuesList.toString();
                    str = str.substring(1, str.length()-1);
                    StringTokenizer st=new StringTokenizer(str," ");
                    refg.tfAddress.setText(st.nextToken());
                    refg.loadPage(refg.tfAddress.getText());
                    
                    System.out.println(refg.tfAddress.getText());
                }
            }
            });
            
            JScrollPane scrPane1 = new JScrollPane(hisList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            jframe2.add(scrPane1,BorderLayout.CENTER);
            jframe2.setLocation(600, 400);
            jframe2.setSize(200, 200);
            jframe2.setVisible(true);
            refg.fMain.add(jframe2);
            }
        }
        }catch(Exception ed){}
        
        
        
        
    }
}
    

