package ui;

import java.awt.BorderLayout;
//ctrl+shift+o
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class CustomerMain extends JFrame implements MouseListener {
	JPanel groundPane;
	public CustomerMain() {
		setTitle("�л��Ĵ� Ŭ���̾�Ʈ �ֹ�������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		//������
		groundPane=new JPanel();
		groundPane.setBackground(new Color(204,204,255));
		setContentPane(groundPane);
		groundPane.setLayout(null);
		
		setSize(1000,700);
		
		//�޴�����â 
		JTabbedPane tabPane=new JTabbedPane();
		groundPane.add(tabPane);
		tabPane.setBounds(20,20,700,620);
		tabPane.setBackground(new Color(255,255,255,100));
		tabPane.setFont(new Font("�������",Font.BOLD,25));
		//tabPane.setLayout(null);
		
		//�迭�� �̹����߰�
		ImageIcon[] menu=new ImageIcon[5];
		String[] vMenus= {"��Ʈ��������","�Ұ��ұ���","��ū�ұ���","����̽ұ���","���ξ��ú�����"};
		
		for(int i=0;i<menu.length;i++) {
			menu[i]=new ImageIcon("images/"+vMenus[i]+".jpg");
		}
		
		//�ұ�����
		JPanel vietnamNoodle=new JPanel();
		vietnamNoodle.setLayout(new GridLayout(0,3,3,3));
		

		JPanel vNoodle_01=new JPanel();
		vietnamNoodle.add(vNoodle_01);
		JLabel imageLabel=new JLabel();
		imageLabel.setIcon(menu[0]);
		vNoodle_01.add(imageLabel,BorderLayout.NORTH);
		JButton btnAdd=new JButton("���");
		vNoodle_01.add(btnAdd,BorderLayout.SOUTH);
		vNoodle_01.add(new JButton("��(45)"),BorderLayout.SOUTH);
		vNoodle_01.setBackground(Color.black);
		
		JPanel vNoodle_02=new JPanel();
		vietnamNoodle.add(vNoodle_02);
		JLabel imageLabel2=new JLabel();
		imageLabel2.setIcon(menu[1]);
		vNoodle_02.add(imageLabel2,BorderLayout.NORTH);
		vNoodle_02.setSize(300,200);
		JButton btnAdd2=new JButton("���");
		vNoodle_02.add(btnAdd2,BorderLayout.SOUTH);
		vNoodle_02.add(new JButton("��(45)"),BorderLayout.SOUTH);
		vNoodle_02.setBackground(Color.yellow);
		
		JPanel vNoodle_03=new JPanel();
		vietnamNoodle.add(vNoodle_03);
		vNoodle_03.setBackground(Color.red);
		
		JPanel vNoodle_04=new JPanel();
		vietnamNoodle.add(vNoodle_04);
		vNoodle_04.setBackground(Color.green);
		
		tabPane.addTab("��Ʈ�� ���Ĵ�", vietnamNoodle);
		
		//����������
		JPanel panel2_2=new JPanel();
		panel2_2.setLayout(new GridLayout());
		tabPane.addTab("��������", new JLabel("��������"));
		
		tabPane.addTab("��������", new JLabel("��������"));
		
		//��ٱ���
		JPanel panel3=new JPanel();
		groundPane.add(panel3);
		panel3.setBounds(730,20,240,440);
		panel3.setBackground(new Color(255,255,255,100));
		
		
		//�����ܰ� ��ư
		JButton next1=new JButton("����");
		groundPane.add(next1);
		next1.setFont(new Font("�������",Font.BOLD,50));
		next1.setBounds(730,470,240,170);
		next1.setBackground(new Color(255,255,255,250));
		next1.addMouseListener(this);
		
		
		setVisible(true);
		
		
	}
	
	
	
	
	public static void main(String args[]) {
		CustomerMain frame=new CustomerMain();
	}




	@Override
	public void mouseClicked(MouseEvent e) {
		new Order();
		this.setVisible(false);
	}




	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
