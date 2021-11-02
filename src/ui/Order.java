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
	public Order(int payment){
	setTitle("ÁÖ¹®¼­");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	setLocation(400,200);

	
	//ÃÖÇÏÀ§
	groundPane2=new JPanel();
	groundPane2.setBackground(new Color(204,204,255));
	setContentPane(groundPane2);
	groundPane2.setLayout(null);
	
	
	JLabel label1=new JLabel("ÁÖ¹®¹øÈ£");
	label1.setBounds(450,150,400,30);
	label1.setFont(new Font("³ª´®°íµñ",Font.BOLD,30));
	groundPane2.add(label1);
	
	JLabel num=new JLabel(orderNum);
	num.setBounds(450,200,110,60);
	num.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));
	num.setFont(new Font("³ª´®°íµñ",Font.BOLD,60));
	num.setOpaque(true);
	num.setBackground(Color.white);
	groundPane2.add(num);
	
	JLabel sum=new JLabel(payment+"¿ø °áÁ¦°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
	sum.setBounds(330,300,500,30);
	sum.setFont(new Font("³ª´®°íµñ",Font.BOLD,30));
	groundPane2.add(sum);
	
	JLabel ending=new JLabel("¸ÀÀÖ°Ô µå¼¼¿ä!");
	ending.setBounds(420,340,500,30);
	ending.setFont(new Font("³ª´®°íµñ",Font.BOLD,30));
	groundPane2.add(ending);
	
	
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
		Order orderList=new Order(0);
	
	}
}
