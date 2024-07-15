 package Gym_Management;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class InspirationImages extends JFrame
{
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
    JPanel p1,p2;
    Font f1;
    InspirationImages()
    {
        super("Inspirational Images");
        setSize(1600,900);
        setResizable(false);
        
        f1=new Font("Lucida Fax",Font.BOLD,26);

        // Setting Icon on FRAME

        ImageIcon loginImage = new ImageIcon(getClass().getResource("/Gym_Management/icons/777.png"));
        Image resultloginImage = loginImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        setIconImage(resultloginImage);
        
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("Gym_Management/icons/M001.jpg"));
        Image imag=img.getImage().getScaledInstance(300,340,Image.SCALE_DEFAULT);
        l1=new JLabel(new ImageIcon(imag));
        
        ImageIcon img1=new ImageIcon(ClassLoader.getSystemResource("Gym_Management/icons/M002.jpg"));
        Image imag1=img1.getImage().getScaledInstance(300,340,Image.SCALE_DEFAULT);
        l2=new JLabel(new ImageIcon(imag1));
        
        ImageIcon img2=new ImageIcon(ClassLoader.getSystemResource("Gym_Management/icons/M003.jpg"));
        Image imag2=img2.getImage().getScaledInstance(300,340,Image.SCALE_DEFAULT);
        l3=new JLabel(new ImageIcon(imag2));
        
        ImageIcon img3=new ImageIcon(ClassLoader.getSystemResource("Gym_Management/icons/M004.jpg"));
        Image imag3=img3.getImage().getScaledInstance(300,340,Image.SCALE_DEFAULT);
        l4=new JLabel(new ImageIcon(imag3));
        
        ImageIcon img4=new ImageIcon(ClassLoader.getSystemResource("Gym_Management/icons/M011.jpg"));
        Image imag4=img4.getImage().getScaledInstance(300,340,Image.SCALE_DEFAULT);
        l5=new JLabel(new ImageIcon(imag4));
        
        ImageIcon img5=new ImageIcon(ClassLoader.getSystemResource("Gym_Management/icons/M006.jpg"));
        Image imag5=img5.getImage().getScaledInstance(300,340,Image.SCALE_DEFAULT);
        l6=new JLabel(new ImageIcon(imag5));
        
        ImageIcon img6=new ImageIcon(ClassLoader.getSystemResource("Gym_Management/icons/M008.jpg"));
        Image imag6=img6.getImage().getScaledInstance(300,340,Image.SCALE_DEFAULT);
        l7=new JLabel(new ImageIcon(imag6));
        
        ImageIcon img7=new ImageIcon(ClassLoader.getSystemResource("Gym_Management/icons/M009.jpg"));
        Image imag7=img7.getImage().getScaledInstance(300,340,Image.SCALE_DEFAULT);
        l8=new JLabel(new ImageIcon(imag7));      
        
        l9=new JLabel("Inspirational Images");
        l9.setFont(f1);
        l9.setForeground(Color.YELLOW);
        l9.setHorizontalAlignment(JLabel.CENTER);
        
        p1=new JPanel();
        p1.setLayout(new FlowLayout());
        p1.setBackground(Color.BLACK);
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        p1.add(l5);
        p1.add(l6);
        p1.add(l7);
        p1.add(l8);
        
        p2=new JPanel();
        p2.setLayout(new GridLayout(1,1,10,10));
        p2.setBackground(Color.BLACK);
        p2.add(l9);
        
        setLayout(new BorderLayout(0,0));
        
        add(p1,"Center");
        add(p2,"North");

        // Add a window listener to handle the close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
    public static void main(String args[])
    {
        new InspirationImages().setVisible(true);
    }
}
