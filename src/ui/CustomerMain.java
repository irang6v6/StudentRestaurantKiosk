package ui;

import java.awt.BorderLayout;
//ctrl+shift+o
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class CustomerMain extends JFrame {
	JPanel groundPane;
	public CustomerMain() {
		setTitle("학생식당 클라이언트 주문페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		//최하위
		groundPane=new JPanel();
		groundPane.setBackground(new Color(204,204,255));
		setContentPane(groundPane);
		groundPane.setLayout(null);
		
		setSize(1000,700);
		
		//메뉴선택창 
		JTabbedPane tabPane=new JTabbedPane();
		groundPane.add(tabPane);
		tabPane.setBounds(20,20,700,620);
		tabPane.setBackground(new Color(255,255,255,100));
		
		
		
		tabPane.setFont(new Font("나눔고딕",Font.BOLD,25));
		//tabPane.setLayout(null);
		
		//배열로 이미지추가
		ImageIcon[] menu=new ImageIcon[5];
		menu[0]=new ImageIcon("images/베트남볶음면.jpg");
		menu[1]=new ImageIcon("images/소고기쌀국수.jpg");
		menu[2]=new ImageIcon("images/얼큰쌀국수.jpg");
		menu[3]=new ImageIcon("images/우더미쌀국수.jpg");
		menu[4]=new ImageIcon("images/파인애플볶음밥.jpg");
		
		String[] names= {"베트남 볶음면","소고기 쌀국수","얼큰 쌀국수","우더미 쌀국수","파인애플 볶음밥"};
		
		//쌀국수탭
		JPanel vietnamNoodle=new JPanel();
		vietnamNoodle.setLayout(new GridLayout(0,3,3,3));
		
		JPanel vNoodle_01=new JPanel();
		vietnamNoodle.add(vNoodle_01);
		JLabel imageLabel=new JLabel();
		imageLabel.setIcon(menu[0]);
		vNoodle_01.add(imageLabel,BorderLayout.NORTH);
		JButton btnAdd=new JButton("담기");
		vNoodle_01.add(btnAdd,BorderLayout.SOUTH);
		vNoodle_01.add(new JButton("★(3.8)"),BorderLayout.SOUTH);
		vNoodle_01.setBackground(Color.black);
		
		JPanel vNoodle_02=new JPanel();
		vietnamNoodle.add(vNoodle_02);
		JLabel imageLabel2=new JLabel();
		imageLabel2.setIcon(menu[1]);
		vNoodle_02.add(imageLabel2,BorderLayout.NORTH);
		vNoodle_02.setSize(300,200);
		JButton btnAdd2=new JButton("담기");
		vNoodle_02.add(btnAdd2,BorderLayout.SOUTH);
		vNoodle_02.add(new JButton("★(3.8)"),BorderLayout.SOUTH);
		vNoodle_02.setBackground(Color.yellow);
		
		JPanel vNoodle_03=new JPanel();
		vietnamNoodle.add(vNoodle_03);
		vNoodle_03.setBackground(Color.red);
		
		tabPane.addTab("베트남 골목식당", vietnamNoodle);
		
		//국수나무탭
		JPanel panel2_2=new JPanel();
		panel2_2.setLayout(new GridLayout());
		tabPane.addTab("국수나무", new JLabel("국수나무"));
		
		tabPane.addTab("본래순대", new JLabel("본래순대"));
		
		//장바구니
		JPanel panel3=new JPanel();
		groundPane.add(panel3);
		panel3.setBounds(730,20,240,440);
		panel3.setBackground(new Color(255,255,255,100));
		
		
		//다음단계 버튼
		JButton next1=new JButton("다음");
		groundPane.add(next1);
		next1.setFont(new Font("나눔고딕",Font.BOLD,50));
		next1.setBounds(730,470,240,170);
		next1.setBackground(new Color(255,255,255,250));
		
		
		setVisible(true);
	}
	
	public static void main(String args[]) {
		CustomerMain frame=new CustomerMain();
	}
}
