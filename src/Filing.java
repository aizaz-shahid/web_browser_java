

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aizazshahid
 */
public class Filing 
{
    File f=new File("file");
    File f1=new File("hisfile");       
    File f3=new File("firefile");       
    File f2=new File("favfile");
    File temp=new File("tempfile");
    Stack fireStack,favStack,hisStack;
    
    
    public void removeLine(String str) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(f3));
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp));

        
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
        // trim newline when comparing with lineToRemove
        String trimmedLine = currentLine.trim();
        if(trimmedLine.equals(str)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close(); 
        reader.close(); 
        
        f3.delete();
        if (!temp.renameTo(f3)){
        System.out.println("Could not rename file");
        }
        
        
    }
    
    public boolean findWord(String str)
    {
        boolean duz=false;
        
        Scanner scanner = null;

        try {
            scanner = new Scanner(f3);
        } catch(FileNotFoundException e) { 
        //handle this
        }

        //now read the file line by line
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(str.toLowerCase().contains(line.toLowerCase())) { 
                duz=true;
            }
        }
        scanner.close();
        return duz;
    }
    
    public String readHomeFile()
    {
        String str;
        str="";
        try{
            BufferedReader br=new BufferedReader(new FileReader("file"));
                   
            str = br.readLine();
            
            
            return str;
            
        }
        catch(Exception e){}
        return str;
    }
    
    
    public void writeHomeFile(String str)
    {
        
        
        try{
            
                   
            FileWriter fw =new FileWriter(f,false);
            PrintWriter pw=new PrintWriter(f);
            pw.println(str);
            pw.close();
        }
        catch(Exception e){}
    }
    
    public String readHisFile()
    {
        hisStack = new Stack();
        String strHis;
        strHis="";
        try{
            
            BufferedReader br=new BufferedReader(new FileReader("hisfile"));
                   
            strHis = br.readLine();
            while(!strHis.isEmpty())
            {
                hisStack.push(strHis);
                strHis = br.readLine();
            }
            
            return strHis;
            
        }
        catch(Exception e){}
        return strHis;
    }
    
    public void writeHisFile(String str)
    {
        
        
        try{
            System.out.println("chuss");
            FileWriter fw = new FileWriter(f1,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            //This will add a new line to the file content
            //pw.println("");
            /* Below three statements would add three 
            * mentioned Strings to the file in new lines.
            */
            bw.write(str);
            bw.write(" - ");
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            bw.write(dateFormat.format(date)); //2014/08/06 15:59:48
            pw.println("");
    	    bw.close();
            pw.close();
    	    
            
            
            
            
            
            
            
            //pw.close();
        }
        catch(Exception e){}
    }
    
    public String readFavFile()
    {
        favStack = new Stack();
        String strFav;
        strFav="";
        try{
            
            BufferedReader br=new BufferedReader(new FileReader("favfile"));
                   
            strFav = br.readLine();
            while(!strFav.isEmpty())
            {
                favStack.push(strFav);
                strFav = br.readLine();
            }
            
            return strFav;
            
        }
        catch(Exception e){}
        return strFav;
    }
    
    public void writeFavFile(String str)
    {
        
        
        try{
            
                   
            FileWriter fw = new FileWriter(f2,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            //This will add a new line to the file content
            //pw.println("");
            /* Below three statements would add three 
            * mentioned Strings to the file in new lines.
            */
            pw.println(str);
            
            pw.close();
        }
        catch(Exception e){}
    }
    
    public String readFireFile()
    {
        fireStack = new Stack();
        String str;
        str="";
        try{
            
            BufferedReader br=new BufferedReader(new FileReader("firefile"));
                   
            str = br.readLine();
            while(!str.isEmpty())
            {
                fireStack.push(str);
                str = br.readLine();
            }
            
            return str;
            
        }
        catch(Exception e){}
        return str;
    }
    
    public void writeFireFile(String str)
    {
        
        
        try{
            
                   
            FileWriter fw = new FileWriter(f3,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            //This will add a new line to the file content
            //pw.println("");
            /* Below three statements would add three 
            * mentioned Strings to the file in new lines.
            */
            pw.println(str);
            
            pw.close();
        }
        catch(Exception e){}
    }
    
    public void clearFireFile(String str)
    {
        
        
        try{
            
            System.out.println("duzz");       
            FileWriter fw = new FileWriter(f1,false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            pw.println(str);
            
            pw.close();
            
            
        }
        catch(Exception e){}
    }

}
