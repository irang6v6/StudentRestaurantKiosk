package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RandomMenu extends JFrame {

	public RandomMenu() {
		
		String[] menu = { "�Ұ��ұ���", "���ξ��ú�����", "���񵤹�", "��¼��", "��¼��" };
		Random rd = new Random();

		int arr = rd.nextInt(5);
		
		setTitle("������ �����޴�!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		JPanel panel=new JPanel();
		
		setContentPane(panel);
		
		
		ImageIcon icon=new ImageIcon("images/ȸ����.jpg");
		JLabel imageLabel= new JLabel();
		imageLabel.setIcon(icon);
		
		JLabel result=new JLabel("������ "+menu[arr]+" ��Ű���?");
		
		JButton btnClose=new JButton("�ݱ�");
		btnClose.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				dispose();
			}

		});
		
		panel.add(result);
		panel.add(imageLabel);
		panel.add(btnClose);
		
		
		setSize(900,500);
		setVisible(true);

	}

	public static void main(String[] args) {
		RandomMenu click = new RandomMenu();
	}

}
