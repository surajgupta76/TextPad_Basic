import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;


class Notepad_accio implements ActionListener{
    JFrame f;
    JMenuBar menubar;
    JMenu file,Themes;
    JMenuItem open,New,dark,light;
    JTextArea textarea;

    Notepad_accio(){
        f = new JFrame("Untitled_Document-1");
        menubar = new JMenuBar();
        file = new JMenu("File");
        Themes = new JMenu("Themes");
        menubar.add(file);
        menubar.add(Themes);
        f.setJMenuBar(menubar);


        open = new JMenuItem("Open");       //file menu
        New = new JMenuItem("New");
        file.add(open);
        file.add(New);
        dark = new JMenuItem("Dark");
        light = new JMenuItem("Light");
        Themes.add(dark);
        Themes.add(light);

        textarea = new JTextArea(50,70);
        f.add(textarea);
        open.addActionListener(this); //open the file
        New.addActionListener(this);
        dark.addActionListener(this);
        light.addActionListener(this);

        f.setSize(1000,596);
        f.setResizable(false);
        f.setLocation(250,100);
        f.setLayout(new FlowLayout());
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        KeyListener k = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) {
                // int keyCode = e.getKeyCode();
            }

            @Override
            public void keyReleased(KeyEvent e) { }
        };
        textarea.addKeyListener(k);

        f.setSize(1000,596);
        f.setResizable(false);
        f.setLocation(250,100);
        f.setLayout(new FlowLayout());
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e) {
    if (e.getSource()==open){
        JFileChooser chooseFile = new JFileChooser();
        int i = chooseFile.showOpenDialog(f);
        if (i == JFileChooser.APPROVE_OPTION){
            File file = chooseFile.getSelectedFile(); //select the file
            String filePath = file.getPath(); //get the file path
            String fileNameToShow = file.getName(); //get the file name
            f.setTitle(fileNameToShow);

           try {
               BufferedReader readFile = new BufferedReader(new FileReader(filePath));
               String tempString1 = "";
               String tempString2 = "";

               while ((tempString1 = readFile.readLine()) != null)
                    tempString2 += tempString1 + "\n";

               textarea.setText(tempString2);
               readFile.close();
           }catch (Exception ae){
               ae.printStackTrace();
           }
        }
    }
    if (e.getSource()==New) textarea.setText("");

    if(e.getSource()==dark) 
    {
        textarea.setBackground(Color.darkGray);
        textarea.setForeground(Color.white);
    }
    if(e.getSource()==light) 
    {
        textarea.setBackground(new Color(255,255,255));
        textarea.setForeground(Color.black);
    }

}
    public static void main(String[] args) {
        new Notepad_accio();
    }
}
