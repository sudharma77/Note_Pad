/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package notepad;

/**
 *
 * @author hp
 */
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
///////////////////////////
import java.awt.Font;
import javax.swing.undo.UndoManager;
import javax.swing.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
//import java.io.Writer;
import javax.swing.filechooser.FileNameExtensionFilter;
import static javax.swing.text.html.HTML.Attribute.N;

public class NotePad extends JFrame implements ActionListener {
    int fsize = 17;
    String fontFamilyValues[] = {"Agency FB", "Antiqua", "Architect", "Arial", "Calibri", "Comic Sans", "Courier", "Cursive", "Impact", "Serif"};
    String fontSizeValues[] = {"5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70"};
    int[] stylevalue = {Font.PLAIN, Font.BOLD, Font.ITALIC};
    //Defining List of Font Styles for Text
    //int[] stylevalue = {Font.PLAIN, Font.BOLD, Font.ITALIC};
    String[] fontStyleValues = {"PLAIN", "BOLD", "ITALIC"};
    //JList sizelist;
    String fontF, fontS, fontSt;
    int fstyle;
    Font newFont ;
    int cl;
    int linecount;
    
    //JList fontFamilyList, fontStyleList, fontSizeList;
    JMenuBar menubar = new JMenuBar();
    JMenu File = new JMenu("File");
    JMenu Edit = new JMenu("Edit");
    JMenu Format = new JMenu("Format");
    JMenu Color = new JMenu("Color");
    JMenu Help = new JMenu("Help");
    
    // color for background and font
    JMenuItem White = new JMenuItem("WHITE");
    JMenuItem Black = new JMenuItem("BLACK");
    JMenuItem Red = new JMenuItem("RED");
    JMenuItem Blue = new JMenuItem("BLUE");
    JMenuItem Gradient = new JMenuItem("GRAY");
    //format menu bar
    JMenuItem wrap = new JMenuItem("wrap word off");
    JMenuItem fontSize = new JMenuItem("Font Size");
    JMenuItem  fontStyle= new JMenuItem("Font Style");
    JMenuItem  fontFamily= new JMenuItem("Font Family");
    //list
    JList familylist = new JList(fontFamilyValues);
    JList stylelist = new JList(fontStyleValues);
    JList sizelist = new JList(fontSizeValues);
    
    // sizelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
//    familylist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//    stylelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    //sizelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    
    JMenuItem newFile = new JMenuItem("New");
    JMenuItem openFile = new JMenuItem("Open");
    JMenuItem saveFile = new JMenuItem("Save");
    JMenuItem print = new JMenuItem("Print");
    JMenuItem exit = new JMenuItem("Exit");
    
    //edit menu item
    JMenuItem cut = new JMenuItem("Cut");
    JMenuItem copy = new JMenuItem("Copy");
    JMenuItem paste = new JMenuItem("Paste");
    JMenuItem select = new JMenuItem("Select");
    JMenuItem selectAll = new JMenuItem("Select All");
    JMenuItem Undo = new JMenuItem("Undo");
    JMenuItem Redo = new JMenuItem("Redo");
    
    //help value
    JMenuItem about = new JMenuItem("About");
    
    UndoManager undoManager = new UndoManager();
    JTextArea textArea = new JTextArea(); 
    /////////////////////////////////////////////undo function
    public void updateButtons() {
        Undo.setText(undoManager.getUndoPresentationName());
        Redo.setText(undoManager.getRedoPresentationName());
        Undo.setEnabled(undoManager.canUndo());
        Redo.setEnabled(undoManager.canRedo());
    }
    
    public NotePad(){
        setTitle("Notepad Project");
        setBounds(100, 100, 1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //icon create
        ImageIcon icon = new ImageIcon(getClass().getResource("icon.jpg"));
        setIconImage(icon.getImage());
        //set menu bar
        setJMenuBar(menubar);
        menubar.add(File);
        menubar.add(Edit);
        menubar.add(Format);
        menubar.add(Color);
        menubar.add(Help);
        
        //format sub menu
        Format.add(wrap);
        Format.add(fontSize);
        Format.add(fontStyle);
        Format.add(fontFamily);
        //file menu
        File.add(newFile);
        File.add(openFile);
        File.add(saveFile);
        File.add(print);
        File.add(exit);
        
        //edit menu
        Edit.add(cut);
        Edit.add(paste);
        Edit.add(copy);
        Edit.add(select);
        Edit.add(selectAll);
        Edit.add(Undo);
        Edit.add(Redo);
        
        //color menu
        Color.add(White);
        Color.add(Black);
        Color.add(Red);
        Color.add(Blue);
        Color.add(Gradient);
        
        //help menu        
        Help.add(about);
        
        // teat area add
        JScrollPane scrollpane = new JScrollPane(textArea);
        add(scrollpane);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());//remove double line
        
        
        textArea.setFont(new Font("Verdana", Font.PLAIN, 20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.getDocument().addUndoableEditListener((UndoableEditEvent e) -> {
            undoManager.addEdit(e.getEdit());
            Undo.setText(undoManager.getUndoPresentationName());
            Redo.setText(undoManager.getRedoPresentationName());
            Undo.setEnabled(undoManager.canUndo());
            Redo.setEnabled(undoManager.canRedo());
        });
        
        //action performance
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        select.addActionListener(this);
        //Undo.addActionListener(this);
        //Redo.addActionListener(this);
        about.addActionListener(this);
        fontSize.addActionListener(this);
        fontFamily.addActionListener(this);
        fontStyle.addActionListener(this);
        White.addActionListener(this);
        Black.addActionListener(this);
        Blue.addActionListener(this);
        Red.addActionListener(this);
        Gradient.addActionListener(this);
        
        ////////////////////////////////////
        Undo.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
              undoManager.undo();
            } catch (CannotRedoException cre) {
              cre.printStackTrace();
            }
            updateButtons();
            }
        });
        
        Redo.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
              undoManager.redo();
            } catch (CannotRedoException cre) {
              cre.printStackTrace();
            }
            updateButtons();
        }
        });
        ///////////////////////////////////
        
        
        //shortcut of all menu
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("New")){
            textArea.setText(null);
        }else if(e.getActionCommand().equalsIgnoreCase("Save")){
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);
            
            int action = fileChooser.showSaveDialog(null);
            if(action != JFileChooser.APPROVE_OPTION){
                //return;
            }else{
                String fileName = fileChooser.getSelectedFile().getAbsolutePath();
                if(!fileName.contains(".txt"))
                    fileName = fileName + ".txt";
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    //Writer writer;
                    textArea.write(writer);
                }catch(IOException ex){
                    //ex.printStackTrace();
                }
            }
        }else if(e.getActionCommand().equalsIgnoreCase("Open")){
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);
            
            int action = fileChooser.showOpenDialog(null);
            if(action != JFileChooser.APPROVE_OPTION){
                //return;
            }else{
                
                try{
                    BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                    //Writer writer;
                    textArea.read(reader, null);
                }catch(IOException ex){
                    //ex.printStackTrace();
                }
            }
        }else if(e.getActionCommand().equalsIgnoreCase("Print")){
            try{                    
                    textArea.print();
                }catch(PrinterException ex){
                    //Logger.getLogger(NotePad.class.getName().log(Level.SEVERE, null, ex));// programm run after this error
                }
        }else if(e.getActionCommand().equalsIgnoreCase("Exit")){
            System.exit(0);
        }else if(e.getActionCommand().equalsIgnoreCase("Cut")){
            textArea.cut();
        }else if(e.getActionCommand().equalsIgnoreCase("Copy")){
            textArea.copy();
        }else if(e.getActionCommand().equalsIgnoreCase("Paste")){
            textArea.paste();
        }else if(e.getActionCommand().equalsIgnoreCase("SelectAll")){
            textArea.selectAll();
        }else if(e.getActionCommand().equalsIgnoreCase("About")){
            new About().setVisible(true);
        }else if(e.getActionCommand().equalsIgnoreCase("Select")){
            textArea.select(SOMEBITS, PROPERTIES);
        }
        else if(e.getActionCommand().equals("Font Size")){
            JOptionPane.showConfirmDialog(null, sizelist, "Choose Font Size", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            fontS = String.valueOf(sizelist.getSelectedValue());
            fsize = Integer.parseInt(fontS);
            newFont = new Font(fontF, fstyle, fsize);
            textArea.setFont(newFont);
        }else if(e.getActionCommand().equals("Font Family")){
            JOptionPane.showConfirmDialog(null, familylist, "Choose Font Size", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            fontF = String.valueOf(familylist.getSelectedValue());
            //fsize = Integer.parseInt(fontS);
            newFont = new Font(fontF, fstyle, fsize);
            textArea.setFont(newFont);
        }else if(e.getActionCommand().equals("Font Style")){
            JOptionPane.showConfirmDialog(null, stylelist, "Choose Font Size", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            fontF = String.valueOf(stylelist.getSelectedValue());
            //fsize = Integer.parseInt(fontS);
            fstyle = stylevalue[stylelist.getSelectedIndex()];
            newFont = new Font(fontF, fstyle, fsize);
            textArea.setFont(newFont);
        }else if(e.getActionCommand().equals("Undo")){  
                try {
                    undoManager.undo();
                } catch (CannotRedoException cre) {
                cre.printStackTrace();
                }
                //updateButtons();
            
         
            Undo.setText(undoManager.getUndoPresentationName());
            Redo.setText(undoManager.getRedoPresentationName());
            Undo.setEnabled(undoManager.canUndo());
            Redo.setEnabled(undoManager.canRedo());
        }else if(e.getActionCommand().equals("Redo")){
            try {
                 undoManager.redo();
            } catch (CannotRedoException cre) {
            }
            Undo.setText(undoManager.getUndoPresentationName());
            Redo.setText(undoManager.getRedoPresentationName());
            Undo.setEnabled(undoManager.canUndo());
            Redo.setEnabled(undoManager.canRedo());
        }else if(e.getActionCommand().equalsIgnoreCase("Black")){
            textArea.setBackground(new Color(0 , 0, 06));
            textArea.setForeground(new Color(255, 255, 255));                
        }else if(e.getActionCommand().equalsIgnoreCase("White")){
            textArea.setBackground(new Color(255, 255, 255));
            textArea.setForeground(new Color(0 , 0, 0));                
        }else if(e.getActionCommand().equalsIgnoreCase("Red")){
            textArea.setBackground(new Color(255, 128, 128));
            textArea.setForeground(new Color(0 , 0, 0));                
        }else if(e.getActionCommand().equalsIgnoreCase("Blue")){
            textArea.setBackground(new Color(0, 0, 255));
            textArea.setForeground(new Color(255, 255, 255));                
        }else if(e.getActionCommand().equalsIgnoreCase("Gradient")){
            textArea.setBackground(new Color(191, 191, 191));
            textArea.setForeground(new Color(0 , 0, 0));                
        }
        
    }
    
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());// set open dialog box in program like open box
        new NotePad().setVisible(true);
        System.out.println("NotePad");
    }

}
