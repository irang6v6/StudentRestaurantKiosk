package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CustomerMain extends JFrame {
	JPanel panel1;
	public CustomerMain() {
		setTitle("�л��Ĵ� Ŭ���̾�Ʈ �ֹ�������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		//������
		panel1=new JPanel();
		panel1.setBackground(new Color(204,204,255));
		setContentPane(panel1);
		panel1.setLayout(null);
		
		setSize(1000,700);
		
		//�޴�����â 
		JPanel panel2=new JPanel();
		panel1.add(panel2);
		panel2.setBounds(20,20,700,620);
		panel2.setBackground(new Color(255,255,255,100));
		
		//��ٱ���
		JPanel panel3=new JPanel();
		panel1.add(panel3);
		panel3.setBounds(730,20,240,440);
		panel3.setBackground(new Color(255,255,255,100));
		
		//�����ܰ� ��ư
		JButton next1=new JButton("����");
		panel1.add(next1);
		next1.setFont(new Font("���",Font.BOLD,50));
		next1.setBounds(730,470,240,170);
		next1.setBackground(new Color(255,255,255,250));
		
		
		setVisible(true);
	}
	
	public static void main(String args[]) {
		CustomerMain frame=new CustomerMain();
	}
}
