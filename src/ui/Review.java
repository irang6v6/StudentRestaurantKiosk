package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Review extends JFrame {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")

	private Vector data = null;

	@SuppressWarnings("rawtypes")

	private Vector title = null;
	private JTable table = null;
	private DefaultTableModel model = null;
	private JButton btnAdd = null;
	private JButton btnClear = null;
	private JTextField tfReviewId = null;
	private JTextField tfMenuId = null;
	private JTextField tfReview = null;
	private JLabel lblReviewId = null;
	private JLabel lblMenuId = null;
	private JLabel lblReview = null;
	private JLabel lblRcmd = null;

	private String Url = "jdbc:mysql://localhost/menu1";
	private String user = "root";
	private String password = "rhksflwk123!";
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmtAdd = null;
	private PreparedStatement pstmtDel = null;
	private PreparedStatement pstmtUpdate = null;

	public Review(int selectedMenuId) {
		{
			setTitle("리뷰");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);

			preDbTreatment();

			data = new Vector<>();

			title = new Vector<>();

			title.add("리뷰");
			title.add("추천");

			model = new DefaultTableModel();

			Vector result = selectAll(selectedMenuId);

			model.setDataVector(result, title);

			table = new JTable(model);
			table.setFont(new java.awt.Font("나눔고딕", Font.PLAIN, 20));
			table.getColumnModel().getColumn(0).setPreferredWidth(800);
			table.getColumnModel().getColumn(1).setPreferredWidth(30);
			table.setRowHeight(40);

			// 테이블에 스크롤팬 생성

			JScrollPane sp = new JScrollPane(table);

			JPanel panel = new JPanel();
			panel.setBackground(new Color(204, 204, 255));

			tfReview = new JTextField(40);

			// 레이블 생성

			lblReview = new JLabel("리뷰 작성");
			lblReview.setFont(new java.awt.Font("나눔고딕", Font.PLAIN, 15));

			// 버튼(추가, 삭제, 수정, 초기화) 생성

			btnAdd = new JButton("추가");
			btnAdd.setFont(new java.awt.Font("나눔고딕", Font.PLAIN, 15));

			btnClear = new JButton("닫기");
			btnClear.setFont(new java.awt.Font("나눔고딕", Font.PLAIN, 15));

			lblRcmd = new JLabel("추천:");
			lblRcmd.setFont(new java.awt.Font("나눔고딕", Font.PLAIN, 15));

			JRadioButton rb1 = new JRadioButton("O");
			JRadioButton rb2 = new JRadioButton("X");

			ButtonGroup bg = new ButtonGroup();
			bg.add(rb1);
			bg.add(rb2);

			btnAdd.addActionListener(new ActionListener() {

				@Override

				public void actionPerformed(ActionEvent e) {

					// 현재 텍스트 필드에 있는 값을 각각의 변수에 대입

					int menuId = selectedMenuId;

					String review = tfReview.getText().trim();

					Enumeration<AbstractButton> enums = bg.getElements();
					String rc = null;
					while (enums.hasMoreElements()) {
						AbstractButton ab = enums.nextElement();
						JRadioButton jb = (JRadioButton) ab;

						if (jb.isSelected())
							rc = (jb.getText().trim()); // getText() 메소드로 문자열 받아낸다.
					}

					if (review.equals("")) {
						JOptionPane.showMessageDialog(null, "리뷰를 입력해주세요", "오류", JOptionPane.ERROR_MESSAGE);

					} else {
						insert(menuId, review, rc);
						Vector result = selectAll(selectedMenuId);

						model.setDataVector(result, title);

						table.getColumnModel().getColumn(0).setPreferredWidth(800);
						table.getColumnModel().getColumn(1).setPreferredWidth(30);
						tfReview.setText("");
					}

				}

			});

			btnClear.addActionListener(new ActionListener() {

				@Override

				public void actionPerformed(ActionEvent e) {

					dispose();
				}

			});

			// 패널에 각각의 레이블과 텍스트필드 추가

			panel.add(lblReview);

			panel.add(tfReview);

			panel.add(lblRcmd);

			panel.add(rb1);
			panel.add(rb2);

			// 패널에 버튼 추가

			panel.add(btnAdd);

			panel.add(btnClear);

			Container c = getContentPane();

			c.add(sp, BorderLayout.CENTER);

			c.add(panel, BorderLayout.SOUTH);

			// 프레임 종료시 처리될 이벤트 처리

			addWindowListener(new WindowAdapter() {

				@Override

				public void windowClosing(WindowEvent w) {

					try {

						stmt.close();

						conn.close();

						setVisible(false);

						dispose();

						System.exit(0);

					} catch (Exception e) {

					}
				}
			});
			setSize(900, 500);
			setVisible(true);
		}
	}

	private Vector selectAll(int selectedMenuId) {

		data.clear();

		try {

			ResultSet rs = stmt.executeQuery("select * from menu1.reviews WHERE menuId='" + selectedMenuId + "'");

			while (rs.next()) {

				Vector in = new Vector<String>();

				String review = rs.getString(3);
				String recommend = rs.getString(4);

				in.add(review);

				in.add(recommend);

				data.add(in);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return data;
	}

	private void insert(int selectedMenuId, String review, String rc) {

		try {

			pstmtAdd = conn.prepareStatement("insert into menu1.reviews (menuId,review,likes) values(?,?,?)");

			pstmtAdd.setInt(1, selectedMenuId);

			pstmtAdd.setString(2, review);

			pstmtAdd.setString(3, rc);

			pstmtAdd.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	private void preDbTreatment() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(Url, user, password);

			stmt = conn.createStatement();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public static void main(String[] args) {

		Review frame = new Review(1);

	}
}
