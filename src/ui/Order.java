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
	public Order(){
	setTitle("주문서");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	
	//최하위
	groundPane2=new JPanel();
	groundPane2.setBackground(new Color(204,204,255));
	setContentPane(groundPane2);
	groundPane2.setLayout(null);
	
	
	
	JLabel sum=new JLabel("결제가 완료되었습니다.");
	sum.setBounds(350,300,400,30);
	sum.setFont(new Font("나눔고딕",Font.BOLD,30));
	groundPane2.add(sum);
	
	
	JButton button1 = new JButton("확인");
    button1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            new CustomerMain();
            groundPane2.setVisible(false);
        }
    });
    button1.setBounds(400,400,200,70);
	button1.setFont(new Font("나눔고딕",Font.BOLD,20));
	button1.setBackground(Color.white);
    groundPane2.add(button1);
    
	
	setSize(1000,700);
	setVisible(true);
	}
	public static void main(String[] args) {
		Order orderList=new Order();
	
	}
}
