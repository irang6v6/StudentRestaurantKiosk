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
	
	private String Url = "jdbc:mysql://localhost/menu1"; // URL ���� ���� ����
	private String user = "root"; // user ���� ���� ���� -> hr
	private String password = "rhksflwk123!"; // password ���� ���� ���� -> hr
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmtAdd = null;
	private PreparedStatement pstmtDel = null;
	private PreparedStatement pstmtUpdate = null;

	public Review(int selectedMenuId) {
		{
			setTitle("����");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);

			preDbTreatment();

			// ���̺� ǥ�õ� ������ ���� ����

			data = new Vector<>();

			// ���̺� ǥ�õ� Ÿ��Ʋ ���� ����, �ʱ�ȭ

			title = new Vector<>();

			title.add("����");
			title.add("��õ");
			
			
			// ���̺� ǥ�õ� �� ��ü ����

			model = new DefaultTableModel();

			Vector result = selectAll(selectedMenuId);

			// �𵨿� ����� ������(result)�� ���� ����

			model.setDataVector(result, title);

			// ���� ���� ���̺� ����

			table = new JTable(model);
			table.setFont(new java.awt.Font("�������", Font.PLAIN, 20));
			table.getColumnModel().getColumn(0).setPreferredWidth(800);
			table.getColumnModel().getColumn(1).setPreferredWidth(30);
			table.setRowHeight(40);
			
			

			// ���̺� ��ũ���� ����

			JScrollPane sp = new JScrollPane(table);

			JPanel panel = new JPanel();
			panel.setBackground(new Color(204, 204, 255));
			// ���� �Է¹ްų� ǥ���� �ؽ�Ʈ�ʵ�(��ȣ, �̸�, �ּ�) ����


			tfReview = new JTextField(40);

			// ���̺� ����


			lblReview = new JLabel("���� �ۼ�");
			lblReview.setFont(new java.awt.Font("�������", Font.PLAIN, 15));


			// ��ư(�߰�, ����, ����, �ʱ�ȭ) ����

			btnAdd = new JButton("�߰�");

			btnClear = new JButton("�ݱ�");
			
			lblRcmd=new JLabel("��õ:");

			JRadioButton rb1 = new JRadioButton("O");
			JRadioButton rb2 = new JRadioButton("X");

			ButtonGroup bg = new ButtonGroup();
			bg.add(rb1);
			bg.add(rb2);

			btnAdd.addActionListener(new ActionListener() {

				// actionPerformed(ActionEvent e) : �߰� ��ư Ŭ���� ȣ��� �޼ҵ�

				// �߰���ư�� Ŭ���ϸ� ó���� ���� �ۼ�

				@Override

				public void actionPerformed(ActionEvent e) {

					// ���� �ؽ�Ʈ �ʵ忡 �ִ� ���� ������ ������ ����

					int menuId = selectedMenuId; // �̸�

					String review = tfReview.getText(); // �ּ�

					Enumeration<AbstractButton> enums = bg.getElements();
					String rc = null;
					while (enums.hasMoreElements()) { // hasMoreElements() Enum�� �� ���� ��ü�� �ִ��� üũ�Ѵ�. ������ false ��ȯ
						AbstractButton ab = enums.nextElement(); // ���׸����� AbstractButton �̴ϱ� �翬�� AbstractButton���� �޾ƾ���
						JRadioButton jb = (JRadioButton) ab; // ����ȯ. ���� ���ٰ� ������ ���ļ� �ٷ� ����ȯ �ؼ� �޾Ƶ� �ȴ�.

						if (jb.isSelected()) // �޾Ƴ� ������ư�� üũ ���¸� Ȯ���Ѵ�. üũ�Ǿ������ true ��ȯ.
							rc = (jb.getText().trim()); // getText() �޼ҵ�� ���ڿ� �޾Ƴ���.
					}

					// ������ ������ ����� ���� �����ͺ��̽��� Insert�ϴ� �޼ҵ�

					insert(menuId, review, rc);

					// �ű� ����� �����͸� �����ͺ��̽����� �ٽ� �о�ͼ� result ���Ϳ� ����

					Vector result = selectAll(selectedMenuId);

					// ����� ������(����)�� �� ���� -> ���̺� ǥ�� ���ŵ�

					model.setDataVector(result, title);

					table.getColumnModel().getColumn(0).setPreferredWidth(800);
					table.getColumnModel().getColumn(1).setPreferredWidth(30);
					tfReview.setText("");

				}

			});

			// �ʱ�ȭ ��ư �̺�Ʈ(Ŭ����) ó�� -> �ؽ�Ʈ�ʵ� �ʱ�ȭ, ��ȣ�ؽ�Ʈ�ʵ忡 Ŀ�� ��ġ

			btnClear.addActionListener(new ActionListener() {

				@Override

				public void actionPerformed(ActionEvent e) {

					dispose();
				}

			});

			// �гο� ������ ���̺�� �ؽ�Ʈ�ʵ� �߰�

			panel.add(lblReview);

			panel.add(tfReview);
			
			panel.add(lblRcmd);

			panel.add(rb1);
			panel.add(rb2);

			// �гο� ��ư �߰�

			panel.add(btnAdd);

			panel.add(btnClear);

			// Frame�� ContentPane �����̳� ��������

			Container c = getContentPane();

			// �����̳ʿ� ���̺�, �г�(�ؽ�Ʈ�ʵ�, ��Ʈ�� ���Ե� �г�) �߰�


			c.add(sp, BorderLayout.CENTER);

			c.add(panel, BorderLayout.SOUTH);

			// ������ ����� ó���� �̺�Ʈ ó��

			addWindowListener(new WindowAdapter() {

				@Override

				public void windowClosing(WindowEvent w) {

					try {

						stmt.close(); // Statement ��ü �ݱ�

						conn.close(); // Connection ��ü �ݱ�

						setVisible(false); // ȭ�� �ݱ�

						dispose(); // �ڿ� �ݳ�

						System.exit(0); // ���� ó��

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

				Vector in = new Vector<String>(); // 1���� ���ڵ� �����ϴ� ���� ����

				//String reviewId = rs.getString(1); // �����ͺ��̽����� ��ȣ�� ������ �ͼ� reviewId ������ ����

				//String menuId = rs.getString(2); // �����ͺ��̽����� �̸��� ������ �ͼ� menuId ������ ����

				String review = rs.getString(3); // �����ͺ��̽����� �ּҰ� ������ �ͼ� review ������ ����

				String recommend = rs.getString(4);

				// ���Ϳ� ������ �� �߰�

				//in.add(reviewId);

				//in.add(menuId);

				in.add(review);

				in.add(recommend);

				// ��ü �����͸� �����ϴ� ���Ϳ� in(1���� ������ ����) ���� �߰�

				data.add(in);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return data; // ��ü ������ �����ϴ� data ���� ����
	}

	private void insert(int selectedMenuId, String review, String rc) {

		try {

			// PreparedStatement ����-> conn�� preDbTreatment() �޼ҵ带 ���� �����Ǿ� ����

			pstmtAdd = conn.prepareStatement("insert into menu1.reviews (menuId,review,likes) values(?,?,?)");

			// insert into member values(? -> 1 ,? -> 2, ? -> 3)" ������ ? �� �� ����

			pstmtAdd.setInt(1, selectedMenuId);

			pstmtAdd.setString(2, review);

			pstmtAdd.setString(3, rc);

			// ���Թ��� ������ ���� -> �Է� (insert)

			pstmtAdd.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

//	private void update(String menuId, String review, String reviewId) {
//
//		try {
//
//			// PreparedStatement ����-> conn�� preDbTreatment() �޼ҵ带 ���� �����Ǿ� ����
//
//			pstmtUpdate = conn.prepareStatement("update menu1.reviews set menuId = ?, review = ? where reviewId = ?");
//
//			// �� ����
//
//			pstmtUpdate.setString(1, menuId);
//
//			pstmtUpdate.setString(2, review);
//
//			// ? ���� �߿� Ȯ�� �ʿ���
//
//			pstmtUpdate.setString(3, reviewId);
//
//			// ���� ����
//
//			pstmtUpdate.executeUpdate();
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//
//		}
//	}

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

		JdbcVectorTableEvnetSample frame = new JdbcVectorTableEvnetSample();

	}
}
