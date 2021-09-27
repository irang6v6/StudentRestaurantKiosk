package ui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Order extends JFrame {
	JPanel groundPane2=new JPanel();
	public Order(){
	setTitle("주문서");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	
	//최하위
	groundPane2=new JPanel();
	groundPane2.setBackground(new Color(204,204,255));
	setContentPane(groundPane2);
	groundPane2.setLayout(null);
	
	setSize(1000,700);
	setVisible(true);
	}
	public static void main(String[] args) {
		Order orderList=new Order();
	
	}
}
