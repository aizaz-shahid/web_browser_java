
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aizazshahid
 */
public class radio_handler implements ActionListener
{
    
    GUIManager objGUI;
    BtnHandler obj;
    
   public void radio_handler(BtnHandler ih)
   {
    obj= ih;  
    
    
   }
   
   
    
    Filing fout = new Filing();
    boolean true1=false,true2=false,true3=false;
    //GUIManager temp=new GUIManager();
    
    
    public void actionPerformed(ActionEvent h) 
    {
        if(h.getActionCommand().equals("viewString")) {
            true1 = true;
            true2=false;
            true3=false;
            
        }
        else if(h.getActionCommand().equals("CurrentString")) {
            
            true2 = true;
            true1=false;
            
            true3=false;
        }
        else if(h.getActionCommand().equals("setString")) {
            true3 = true;
            true1=false;
            true2=false;
        }
        else if(h.getActionCommand().equals("OK")) {
            System.out.println("ok pressed");
            if (true1) {
                JOptionPane.showMessageDialog(null,fout.readHomeFile(),"Home Page",JOptionPane.PLAIN_MESSAGE);
            }
            else if (true2) {
                //System.out.println(objGUI.tfAddress.getText());
                fout.writeHomeFile(objGUI.tfAddress.getText());
            }
            else if (true3) {
                String s;
                s = JOptionPane.showInputDialog("");
                fout.writeHomeFile(s);
            }
            else    {
                JOptionPane.showMessageDialog(null,"BULL SHIT","Home Page",JOptionPane.PLAIN_MESSAGE);
            }
            
        }
    }
    
}






/*BufferedReader br=new BufferedReader(new FileReader(new File("History.txt")));
            s=br.readLine();
            while (s!=null){
                StringTokenizer st=new StringTokenizer(s," ");
                url.add(st.nextToken());
                s_list.add(s);
                s=br.readLine();
            }
            list=new JList(s_list);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            f.add(new JScrollPane(list));
            
            list.addListSelectionListener(new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent e){
                    pageLoad(url.get(list.getSelectedIndex()));
                }
            });
*/