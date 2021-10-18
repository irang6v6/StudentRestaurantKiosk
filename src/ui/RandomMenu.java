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
		
		String[] menu = { "º£Æ®³²ººÀ½¸é", "¼Ò°í±â½Ò±¹¼ö", "¾óÅ«½Ò±¹¼ö", "¿ì´õ¹Ì½Ò±¹¼ö", "»ý¸é±¹¼ö", "¿Õµ·±î½º", "¼Ò°í±â³«Áöµ¤¹ä", "µ·±î½ºÄ«·¹µ¤¹ä" };
		rdMenu();

		setTitle("¿À´ÃÀÇ ¸Þ´º´Â...");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		JPanel panel = new JPanel();
		panel.setLayout(null);

		setContentPane(panel);
		panel.setBackground(Color.white);

		ImageIcon icon = new ImageIcon("images/È¸ºÏÀÌ.jpg");
		Image image=icon.getImage();
		Image image2=image.getScaledInstance(200, 250, java.awt.Image.SCALE_SMOOTH);
		ImageIcon image3=new ImageIcon(image2);
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(image3);
		
		ImageIcon[] menus = new ImageIcon[8];
		String[] vMenus = { "º£Æ®³²ººÀ½¸é", "¼Ò°í±â½Ò±¹¼ö", "¾óÅ«½Ò±¹¼ö", "¿ì´õ¹Ì½Ò±¹¼ö", "»ý¸é±¹¼ö3", "¿Õµ·±î½º", "¼Ò°í±â³«Áöµ¤¹ä", "µ·±î½ºÄ«·¹µ¤¹ä" };

		for (int i = 0; i < menus.length; i++) {
			menus[i] = new ImageIcon("images/" + vMenus[i] + ".jpg");
		}
		
		JLabel foodLabel=new JLabel();
		Image fimage=menus[arr].getImage();
		Image fimage2=fimage.getScaledInstance(350,350, java.awt.Image.SCALE_SMOOTH);
		ImageIcon fimage3=new ImageIcon(fimage2);
		foodLabel.setIcon(fimage3);
		

		JLabel ment = new JLabel("¿À´ÃÀÇ ¸Þ´º´Â....");
		ment.setFont(new Font("³ª´®°íµñ", Font.BOLD, 25));
		JLabel result= new JLabel(menu[arr]);
		result.setFont(new Font("³ª´®°íµñ", Font.BOLD, 25));
		JButton btnRetry=new JButton("¸¾¿¡ ¾Èµé¾î");
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
		
		
		JButton btnClose = new JButton("¸¾¿¡ µé¾î");
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
		btnRetry.setFont(new Font("³ª´®°íµñ", Font.BOLD, 20));
		btnClose.setBounds(520, 550, 150, 40);
		btnClose.setFont(new Font("³ª´®°íµñ", Font.BOLD, 20));
	
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
