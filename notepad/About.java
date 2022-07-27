/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package notepad;

import javax.swing.*;
public class About extends JFrame {
    About(){
        setBounds(100, 100, 500, 400);
        setTitle("About Notepad Application");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getResource("menu.png"));
        setIconImage(icon.getImage());
        setLayout(null);
        
        JLabel iconLabel = new JLabel(new ImageIcon(getClass().getResource("menu.png")));
        iconLabel.setBounds(100, 50, 80, 80);
        add(iconLabel);
        
        JLabel textLabel = new JLabel("<html>Welcome to Notpad<br> a simple text</html>");
        textLabel.setBounds(100, 50, 350,300);
        
        add(textLabel);
    }
    
    public static void main(String[] args){
        new About().setVisible(true);
    }
    
    
}
