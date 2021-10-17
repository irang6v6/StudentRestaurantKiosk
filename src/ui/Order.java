package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Order extends JFrame {
	JPanel groundPane2=new JPanel();
	String orderNum="714";
	public Order(){
	setTitle("ÁÖ¹®¼­");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	
	//ÃÖÇÏÀ§
	groundPane2=new JPanel();
	groundPane2.setBackground(new Color(204,204,255));
	setContentPane(groundPane2);
	groundPane2.setLayout(null);
	
	
	JLabel label1=new JLabel("ÁÖ¹®¹øÈ£");
	label1.setBounds(460,150,400,30);
	label1.setFont(new Font("³ª´®°íµñ",Font.BOLD,20));
	groundPane2.add(label1);
	
	JLabel num=new JLabel(orderNum);
	num.setBounds(450,200,400,60);
	num.setFont(new Font("³ª´®°íµñ",Font.BOLD,60));
	groundPane2.add(num);
	
	JLabel sum=new JLabel("°áÁ¦°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
	sum.setBounds(350,300,400,30);
	sum.setFont(new Font("³ª´®°íµñ",Font.BOLD,30));
	groundPane2.add(sum);
	
	
	JButton button1 = new JButton("È®ÀÎ");
    button1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            new CustomerMain();
            groundPane2.setVisible(false);
        }
    });
    button1.setBounds(400,400,200,70);
	button1.setFont(new Font("³ª´®°íµñ",Font.BOLD,20));
	button1.setBackground(Color.white);
    groundPane2.add(button1);
    
	
	setSize(1000,700);
	setVisible(true);
	}
	public static void main(String[] args) {
		Order orderList=new Order();
	
	}
}
