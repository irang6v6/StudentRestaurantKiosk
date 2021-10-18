package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RandomMenu extends JFrame {
	int arr;

	public RandomMenu() {
		
		String[] menu = { "��Ʈ��������", "�Ұ��ұ���", "��ū�ұ���", "����̽ұ���", "���鱹��", "�յ��", "�Ұ�Ⳬ������", "���ī������" };
		rdMenu();

		setTitle("������ �޴���...");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		JPanel panel = new JPanel();
		panel.setLayout(null);

		setContentPane(panel);
		panel.setBackground(Color.white);

		ImageIcon icon = new ImageIcon("images/ȸ����.jpg");
		Image image=icon.getImage();
		Image image2=image.getScaledInstance(200, 250, java.awt.Image.SCALE_SMOOTH);
		ImageIcon image3=new ImageIcon(image2);
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(image3);
		
		ImageIcon[] menus = new ImageIcon[8];
		String[] vMenus = { "��Ʈ��������", "�Ұ��ұ���", "��ū�ұ���", "����̽ұ���", "���鱹��3", "�յ��", "�Ұ�Ⳬ������", "���ī������" };

		for (int i = 0; i < menus.length; i++) {
			menus[i] = new ImageIcon("images/" + vMenus[i] + ".jpg");
		}
		
		JLabel foodLabel=new JLabel();
		Image fimage=menus[arr].getImage();
		Image fimage2=fimage.getScaledInstance(350,350, java.awt.Image.SCALE_SMOOTH);
		ImageIcon fimage3=new ImageIcon(fimage2);
		foodLabel.setIcon(fimage3);
		

		JLabel ment = new JLabel("������ �޴���....");
		ment.setFont(new Font("�������", Font.BOLD, 25));
		JLabel result= new JLabel(menu[arr]);
		result.setFont(new Font("�������", Font.BOLD, 25));
		JButton btnRetry=new JButton("���� �ȵ��");
		btnRetry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rdMenu();
				result.setText(menu[arr]);
				Image fimage=menus[arr].getImage();
				Image fimage2=fimage.getScaledInstance(350,350, java.awt.Image.SCALE_SMOOTH);
				ImageIcon fimage3=new ImageIcon(fimage2);
				foodLabel.setIcon(fimage3);
			}
			
		}
				);
		
		
		JButton btnClose = new JButton("���� ���");
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		ment.setBounds(350,100,200,40);
		result.setBounds(550,100,200,40);
		imageLabel.setBounds(700, 50, 200, 250);
		foodLabel.setBounds(325, 150, 350, 350);
		btnRetry.setBounds(320, 550, 150, 40);
		btnRetry.setFont(new Font("�������", Font.BOLD, 20));
		btnClose.setBounds(520, 550, 150, 40);
		btnClose.setFont(new Font("�������", Font.BOLD, 20));
	
		panel.add(ment);
		panel.add(result);
		panel.add(imageLabel);
		panel.add(foodLabel);
		panel.add(btnRetry);
		panel.add(btnClose);
		

		setSize(1000,700);
		setVisible(true);

	}
	
	public void rdMenu() {
		Random rd = new Random();
		arr = rd.nextInt(8);
	}
	
	
	public static void main(String[] args) {
		RandomMenu click = new RandomMenu();
	}

}
