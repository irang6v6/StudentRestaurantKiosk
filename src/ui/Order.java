package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Order extends JFrame {
	JPanel groundPane2=new JPanel();
	public Order(){
	setTitle("�ֹ���");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	
	//������
	groundPane2=new JPanel();
	groundPane2.setBackground(new Color(204,204,255));
	setContentPane(groundPane2);
	groundPane2.setLayout(null);
	
	
	
	JLabel sum=new JLabel("�����Ϸ�");
	sum.setBounds(400,300,200,70);
	sum.setFont(new Font("�������",Font.BOLD,30));
	groundPane2.add(sum);
	
	setSize(1000,700);
	setVisible(true);
	}
	public static void main(String[] args) {
		Order orderList=new Order();
	
	}
}
