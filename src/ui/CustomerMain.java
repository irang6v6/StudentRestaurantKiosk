package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class CustomerMain extends JFrame {
	JPanel groundPane;
	JButton next1, like1;
	int orderNum = 0;
	MyTableModel model;
	JTable table;
	JLabel sum;
	int sum1 = 0;
	int clicked = 70;
	int clicked02 = 15;
	int clicked03 = 0;
	int clicked04 = 46;
	int clicked05 = 5;
	int clicked06 = 10;
	int clicked07 = 13;
	int clicked08 = 76;
	private PreparedStatement pstmtDel = null;

	public CustomerMain() {
		setTitle("학생식당 클라이언트 주문페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// 최하위
		groundPane = new JPanel();
		groundPane.setBackground(new Color(204, 204, 255));
		setContentPane(groundPane);
		groundPane.setLayout(null);

		setSize(1000, 700);

		// 메뉴선택창
		JTabbedPane tabPane = new JTabbedPane();
		groundPane.add(tabPane);
		tabPane.setBounds(20, 20, 700, 620);
		tabPane.setBackground(new Color(255, 255, 255, 100));
		tabPane.setFont(new Font("나눔고딕", Font.BOLD, 25));
		// tabPane.setLayout(null);

		// 합계
		JLabel sum = new JLabel("합계:" + sum1 + "원");
		sum.setBounds(730, 400, 200, 70);
		sum.setFont(new Font("나눔고딕", Font.BOLD, 30));

		groundPane.add(sum);

		// 배열로 이미지추가
		ImageIcon[] menu = new ImageIcon[8];
		String[] vMenus = { "베트남볶음면", "소고기쌀국수", "얼큰쌀국수", "우더미쌀국수", "생면국수3", "왕돈까스", "소고기낙지덮밥", "돈까스카레덮밥" };

		for (int i = 0; i < menu.length; i++) {
			menu[i] = new ImageIcon("images/" + vMenus[i] + ".jpg");
		}

		// 쌀국수탭
		JPanel vietnamNoodle = new JPanel();
		vietnamNoodle.setLayout(new GridLayout(0, 3, 3, 3));

		JPanel vNoodle_01 = new JPanel();
		vietnamNoodle.add(vNoodle_01);
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(menu[0]);
		vNoodle_01.add(imageLabel, BorderLayout.NORTH);

		// 담기
		JButton btnAdd = new JButton(new AbstractAction("담기") {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = 1;
				String name = "베트남볶음면";
				int price = 4500;
				orderNum += 1;
				String restaurant = "골목식당";
				sum1 += 4500;
				sum.setText("합계: " + sum1 + "원");

				try {
					Connection conn = makeConnection();
					Statement stmt = conn.createStatement();
					stmt.executeUpdate("INSERT INTO ordersheet VALUES (" + id + "," + orderNum + ",'" + name + "',"
							+ price + ",'" + restaurant + "')");

					conn.close();
				} catch (SQLException ex) {
					System.err.println("Caught Exception: " + ex.getMessage());
				}
				model.fillTable();
			}
		});
		vNoodle_01.add(btnAdd, BorderLayout.SOUTH);

		// 좋아요
		final JButton likes01 = new JButton("♥(10+)");
		likes01.addActionListener(new AbstractAction("리뷰") {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Review(1);
				groundPane.setVisible(true);
			}
		});
		likes01.setSize(100, 50);
		likes01.setFont(new Font("나눔고딕", Font.BOLD, 15));
		vNoodle_01.add(likes01, BorderLayout.SOUTH);
		vNoodle_01.setBackground(new Color(255, 255, 255, 100));

		JPanel vNoodle_02 = new JPanel();
		vietnamNoodle.add(vNoodle_02);
		JLabel imageLabel02 = new JLabel();
		imageLabel02.setIcon(menu[1]);
		vNoodle_02.add(imageLabel02, BorderLayout.NORTH);

		// 담기
		JButton btnAdd02 = new JButton(new AbstractAction("담기") {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = 2;
				String name = "소고기쌀국수";
				int price = 3000;
				orderNum += 1;
				String restaurant = "골목식당";
				sum1 += 3000;
				sum.setText("합계: " + sum1 + "원");

				try {
					Connection conn = makeConnection();
					Statement stmt = conn.createStatement();
					stmt.executeUpdate("INSERT INTO ordersheet VALUES (" + id + "," + orderNum + ",'" + name + "',"
							+ price + ",'" + restaurant + "')");

					conn.close();
				} catch (SQLException ex) {
					System.err.println("Caught Exception: " + ex.getMessage());
				}
				model.fillTable();
			}
		});
		vNoodle_02.add(btnAdd02, BorderLayout.SOUTH);

		// 좋아요
		final JButton likes02 = new JButton("♥(" + clicked02 + ")");
		likes02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				clicked02++;
				likes02.setText("♥(" + clicked02 + ")");
			}
		});
		likes02.setSize(100, 50);
		likes02.setFont(new Font("나눔고딕", Font.BOLD, 15));
		vNoodle_02.add(likes02, BorderLayout.SOUTH);
		vNoodle_02.setBackground(new Color(255, 255, 255, 100));

		JPanel vNoodle_03 = new JPanel();
		vietnamNoodle.add(vNoodle_03);
		JLabel imageLabel03 = new JLabel();
		imageLabel03.setIcon(menu[2]);
		vNoodle_03.add(imageLabel03, BorderLayout.NORTH);

		// 담기
		JButton btnAdd03 = new JButton(new AbstractAction("담기") {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = 3;
				String name = "얼큰쌀국수";
				int price = 6500;
				orderNum += 1;
				String restaurant = "골목식당";
				sum1 += 6500;
				sum.setText("합계: " + sum1 + "원");

				try {
					Connection conn = makeConnection();
					Statement stmt = conn.createStatement();
					stmt.executeUpdate("INSERT INTO ordersheet VALUES (" + id + "," + orderNum + ",'" + name + "',"
							+ price + ",'" + restaurant + "')");

					conn.close();
				} catch (SQLException ex) {
					System.err.println("Caught Exception: " + ex.getMessage());
				}
				model.fillTable();
			}
		});
		vNoodle_03.add(btnAdd03, BorderLayout.SOUTH);

		// 좋아요
		final JButton likes03 = new JButton("♥(" + clicked03 + ")");
		likes03.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				clicked03++;
				likes03.setText("♥(" + clicked03 + ")");
			}
		});
		likes03.setSize(100, 50);
		likes03.setFont(new Font("나눔고딕", Font.BOLD, 15));
		vNoodle_03.add(likes03, BorderLayout.SOUTH);
		vNoodle_03.setBackground(new Color(255, 255, 255, 100));

		JPanel vNoodle_04 = new JPanel();
		vietnamNoodle.add(vNoodle_04);
		JLabel imageLabel4 = new JLabel();
		imageLabel4.setIcon(menu[3]);
		vNoodle_04.add(imageLabel4, BorderLayout.NORTH);
		vNoodle_04.setSize(300, 200);
		JButton btnAdd4 = new JButton(new AbstractAction("담기") {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = 4;
				String name = "우더미쌀국수";
				int price = 6500;
				orderNum += 1;
				String restaurant = "골목식당";
				sum1 += 6500;
				sum.setText("합계: " + sum1 + "원");

				try {
					Connection conn = makeConnection();
					Statement stmt = conn.createStatement();
					stmt.executeUpdate("INSERT INTO ordersheet VALUES (" + id + "," + orderNum + ",'" + name + "',"
							+ price + ",'" + restaurant + "')");

					conn.close();
				} catch (SQLException ex) {
					System.err.println("Caught Exception: " + ex.getMessage());
				}
				model.fillTable();
			}
		});
		vNoodle_04.add(btnAdd4, BorderLayout.SOUTH);
		vNoodle_04.add(new JButton("♥(90)"), BorderLayout.SOUTH);
		vNoodle_04.setBackground(new Color(255, 255, 255, 100));

		tabPane.addTab("베트남 골목식당", vietnamNoodle);

		// 국수나무탭
		JPanel noodleTree = new JPanel();
		tabPane.addTab("국수나무", noodleTree);
		noodleTree.setLayout(new GridLayout(0, 3, 3, 3));

		JPanel tree01 = new JPanel();
		noodleTree.add(tree01);
		JLabel imageLabel05 = new JLabel();
		imageLabel05.setIcon(menu[4]);
		tree01.add(imageLabel05, BorderLayout.NORTH);

		// 담기
		JButton btnAdd05 = new JButton(new AbstractAction("담기") {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = 5;
				String name = "생면국수";
				int price = 4000;
				orderNum += 1;
				String restaurant = "국수나무";
				sum1 += 4000;
				sum.setText("합계: " + sum1 + "원");

				try {
					Connection conn = makeConnection();
					Statement stmt = conn.createStatement();
					stmt.executeUpdate("INSERT INTO ordersheet VALUES (" + id + "," + orderNum + ",'" + name + "',"
							+ price + ",'" + restaurant + "')");

					conn.close();
				} catch (SQLException ex) {
					System.err.println("Caught Exception: " + ex.getMessage());
				}
				model.fillTable();
			}
		});
		tree01.add(btnAdd05);

		// 좋아요
		final JButton likes05 = new JButton("♥(" + clicked05 + ")");
		likes05.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				clicked05++;
				likes05.setText("♥(" + clicked05 + ")");
			}
		});
		likes05.setSize(100, 50);
		likes05.setFont(new Font("나눔고딕", Font.BOLD, 15));
		tree01.add(likes05, BorderLayout.SOUTH);
		tree01.setBackground(new Color(255, 255, 255, 100));

		JPanel tree02 = new JPanel();
		noodleTree.add(tree02);
		JLabel imageLabel06 = new JLabel();
		imageLabel06.setIcon(menu[5]);
		tree02.add(imageLabel06, BorderLayout.NORTH);

		// 담기
		JButton btnAdd06 = new JButton(new AbstractAction("담기") {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = 6;
				String name = "왕돈까스";
				int price = 6000;
				orderNum += 1;
				String restaurant = "국수나무";
				sum1 += 6000;
				sum.setText("합계: " + sum1 + "원");

				try {
					Connection conn = makeConnection();
					Statement stmt = conn.createStatement();
					stmt.executeUpdate("INSERT INTO ordersheet VALUES (" + id + "," + orderNum + ",'" + name + "',"
							+ price + ",'" + restaurant + "')");

					conn.close();
				} catch (SQLException ex) {
					System.err.println("Caught Exception: " + ex.getMessage());
				}
				model.fillTable();
			}
		});
		tree02.add(btnAdd06, BorderLayout.SOUTH);

		// 좋아요
		final JButton likes06 = new JButton("♥(" + clicked06 + ")");
		likes06.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				clicked06++;
				likes06.setText("♥(" + clicked06 + ")");
			}
		});
		likes06.setSize(100, 50);
		likes06.setFont(new Font("나눔고딕", Font.BOLD, 15));
		tree02.add(likes06, BorderLayout.SOUTH);
		tree02.setBackground(new Color(255, 255, 255, 100));

		//////////
		JPanel tree03 = new JPanel();
		noodleTree.add(tree03);
		JLabel imageLabel07 = new JLabel();
		imageLabel07.setIcon(menu[6]);
		tree03.add(imageLabel07, BorderLayout.NORTH);

		// 담기
		JButton btnAdd07 = new JButton(new AbstractAction("담기") {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = 7;
				String name = "소고기낙지덮밥";
				int price = 6000;
				orderNum += 1;
				String restaurant = "국수나무";
				sum1 += 6000;
				sum.setText("합계: " + sum1 + "원");

				try {
					Connection conn = makeConnection();
					Statement stmt = conn.createStatement();
					stmt.executeUpdate("INSERT INTO ordersheet VALUES (" + id + "," + orderNum + ",'" + name + "',"
							+ price + ",'" + restaurant + "')");

					conn.close();
				} catch (SQLException ex) {
					System.err.println("Caught Exception: " + ex.getMessage());
				}
				model.fillTable();
			}
		});
		tree03.add(btnAdd07, BorderLayout.SOUTH);

		// 좋아요
		final JButton likes07 = new JButton("♥(" + clicked07 + ")");
		likes07.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				clicked07++;
				likes07.setText("♥(" + clicked07 + ")");
			}
		});
		likes07.setSize(100, 50);
		likes07.setFont(new Font("나눔고딕", Font.BOLD, 15));
		tree03.add(likes07, BorderLayout.SOUTH);
		tree03.setBackground(new Color(255, 255, 255, 100));

/////
		JPanel tree04 = new JPanel();
		noodleTree.add(tree04);
		JLabel imageLabel08 = new JLabel();
		imageLabel08.setIcon(menu[7]);
		tree04.add(imageLabel08, BorderLayout.NORTH);

		// 담기
		JButton btnAdd08 = new JButton(new AbstractAction("담기") {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = 8;
				String name = "돈까스카레덮밥";
				int price = 6000;
				orderNum += 1;
				String restaurant = "국수나무";
				sum1 += 6000;
				sum.setText("합계: " + sum1 + "원");

				try {
					Connection conn = makeConnection();
					Statement stmt = conn.createStatement();
					stmt.executeUpdate("INSERT INTO ordersheet VALUES (" + id + "," + orderNum + ",'" + name + "',"
							+ price + ",'" + restaurant + "')");

					conn.close();
				} catch (SQLException ex) {
					System.err.println("Caught Exception: " + ex.getMessage());
				}
				model.fillTable();
			}
		});
		tree04.add(btnAdd08, BorderLayout.SOUTH);

		// 좋아요
		final JButton likes08 = new JButton("♥(" + clicked08 + ")");
		likes08.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				clicked08++;
				likes08.setText("♥(" + clicked08 + ")");
			}
		});
		likes08.setSize(100, 50);
		likes08.setFont(new Font("나눔고딕", Font.BOLD, 15));
		tree04.add(likes08, BorderLayout.SOUTH);
		tree04.setBackground(new Color(255, 255, 255, 100));
		// tabPane.addTab("본래순대", new JLabel("본래순대"));

		// 장바구니

		model = new MyTableModel();
		model.cleanTable();
		model.fillTable();
		JTable table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.setRowHeight(50);

		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(730, 54, 240, 360);
		groundPane.add(tablePanel);
		tablePanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		tablePanel.add(scrollPane);

		JButton clear = new JButton("초기화");
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CustomerMain();
			}

		});
		clear.setBounds(890, 10, 80, 40);
		groundPane.add(clear);

		tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
		scrollPane.setViewportView(table);

		tablePanel.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		tablePanel.setBackground(new Color(255, 255, 255, 100));

		// 다음단계 버튼
		JButton next1 = new JButton(new AbstractAction("결제") {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Order();
				groundPane.setVisible(true);
			}
		});
		groundPane.add(next1);
		next1.setFont(new Font("나눔고딕", Font.BOLD, 50));
		next1.setBounds(730, 470, 240, 170);
		next1.setBackground(new Color(255, 255, 255, 250));
		next1.addActionListener(new MyListener());

		setVisible(true);

	}

	public void delete(String selectedId) throws SQLException {
		Connection conn = makeConnection();
		Statement stmt = conn.createStatement();
		pstmtDel = conn.prepareStatement("delete from menu1.ordersheet where selectedId = ?");
		pstmtDel.setString(1, selectedId);
		pstmtDel.executeUpdate();
	}

	public class MyTableModel extends AbstractTableModel {
		private String[] columnNames = { "주문", "상품명", "가격", "주문처" };
		private static final int ROWS = 15;
		private static final int COLS = 4;
		Object[][] data = new String[ROWS][COLS];

		@Override
		public int getRowCount() {
			return data.length;
		}

		public void addMouseListener(MouseAdapter mouseAdapter) {
			// TODO Auto-generated method stub

		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public String getColumnName(int col) {
			return columnNames[col].toString();
		}

		public void cleanTable() {
			try {
				Connection conn = makeConnection();
				Statement stmt = conn.createStatement();
				int rs = stmt.executeUpdate("TRUNCATE menu1.ordersheet");
			} catch (SQLException e) {
				System.err.println("Caught Exception:" + e.getMessage());
			}
			fireTableDataChanged();
		}

		public void fillTable() {
			try {
				Connection conn = makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM menu1.ordersheet");

				int row = 0;
				while (rs.next()) {
					data[row][0] = rs.getString("orderNum");
					data[row][1] = rs.getString("name");
					data[row][2] = rs.getString("price");
					data[row][3] = rs.getString("restaurant");
					row++;
				}
				conn.close();
			} catch (SQLException e) {
				System.err.println("Caught Exception:" + e.getMessage());
			}
			fireTableDataChanged();
		}

		public void deleteTable() {
			int index = table.getSelectedRow();
			Vector in = (Vector) data[index][0];
			String selectedId = (String) in.get(0);

			try {
				Connection conn = makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("delete FROM menu1.ordersheet where selectedId = " + selectedId + "");

				conn.close();
			} catch (SQLException e) {
				System.err.println("Caught Exception:" + e.getMessage());
			}

			fillTable();

		}

		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}

		@Override
		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

	}

	// db 연결
	public static Connection makeConnection() {
		String url = "jdbc:mysql://localhost/menu1";
		String id = "root";
		String password = "rhksflwk123!";
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url, id, password);
			System.out.println("데이터베이스 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다");
		} catch (SQLException e) {
			System.out.println("연결에 실패하였습니다.");
		}
		return con;
	}

	// 이유를 모르겠지만 버튼 변수명이 인식이 안돼서 보류..ㅠㅠ
	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

	public static void main(String args[]) {
		CustomerMain frame = new CustomerMain();
	}

}
