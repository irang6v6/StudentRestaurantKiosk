package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

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
	private String Url = "jdbc:mysql://localhost/menu1"; // URL ���� ���� ����
	private String user = "root"; // user ���� ���� ���� -> hr
	private String password = "rhksflwk123!"; // password ���� ���� ���� -> hr
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmtAdd = null;
	private PreparedStatement pstmtDel = null;
	private PreparedStatement pstmtUpdate = null;

	public Review() {
		{

			setTitle("����");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);

			preDbTreatment();

			// ���̺� ǥ�õ� ������ ���� ����

			data = new Vector<>();

			// ���̺� ǥ�õ� Ÿ��Ʋ ���� ����, �ʱ�ȭ

			title = new Vector<>();

			title.add("��ȣ");

			title.add("�޴�");

			title.add("����");
			title.add("��õ");

			// ���̺� ǥ�õ� �� ��ü ����

			model = new DefaultTableModel();

			Vector result = selectAll();

			// �𵨿� ����� ������(result)�� ���� ����

			model.setDataVector(result, title);

			// ���� ���� ���̺� ����

			table = new JTable(model);

			// ���̺� ��ũ���� ����

			JScrollPane sp = new JScrollPane(table);

			JPanel panel = new JPanel();

			// ���� �Է¹ްų� ǥ���� �ؽ�Ʈ�ʵ�(��ȣ, �̸�, �ּ�) ����

			tfReviewId = new JTextField(8);

			tfMenuId = new JTextField(10);

			tfReview = new JTextField(20);

			// ���̺� ����

			lblReviewId = new JLabel("��ȣ");

			lblMenuId = new JLabel("�޴�");

			lblReview = new JLabel("����");

			// ��ư(�߰�, ����, ����, �ʱ�ȭ) ����

			btnAdd = new JButton("�߰�");

			btnClear = new JButton("�ݱ�");

			btnAdd.addActionListener(new ActionListener() {

				// actionPerformed(ActionEvent e) : �߰� ��ư Ŭ���� ȣ��� �޼ҵ�

				// �߰���ư�� Ŭ���ϸ� ó���� ���� �ۼ�

				@Override

				public void actionPerformed(ActionEvent e) {

					// ���� �ؽ�Ʈ �ʵ忡 �ִ� ���� ������ ������ ����

					String reviewId = tfReviewId.getText(); // ��ȣ

					String menuId = tfMenuId.getText(); // �̸�

					String review = tfReview.getText(); // �ּ�

					// ������ ������ ����� ���� �����ͺ��̽��� Insert�ϴ� �޼ҵ�

					insert(reviewId, menuId, review);

					// �ű� ����� �����͸� �����ͺ��̽����� �ٽ� �о�ͼ� result ���Ϳ� ����

					Vector result = selectAll();

					// ����� ������(����)�� �� ���� -> ���̺� ǥ�� ���ŵ�

					model.setDataVector(result, title);

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

			panel.add(lblReviewId);

			panel.add(tfReviewId);

			panel.add(lblMenuId);

			panel.add(tfMenuId);

			panel.add(lblReview);

			panel.add(tfReview);

			JRadioButton rb1 = new JRadioButton("����");
			JRadioButton rb2 = new JRadioButton("����");

			ButtonGroup bg = new ButtonGroup();
			bg.add(rb1);
			bg.add(rb2);

			panel.add(rb1);
			panel.add(rb2);

			// �гο� ��ư �߰�

			panel.add(btnAdd);

			panel.add(btnClear);

			// Frame�� ContentPane �����̳� ��������

			Container c = getContentPane();

			// �����̳ʿ� ���̺�, �г�(�ؽ�Ʈ�ʵ�, ��Ʈ�� ���Ե� �г�) �߰�

			c.add(new JLabel("����", JLabel.CENTER), "North");

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


	private Vector selectAll() {

		data.clear();

		try {

			ResultSet rs = stmt.executeQuery("select * from menu1.reviews");

			while (rs.next()) {

				Vector in = new Vector<String>(); // 1���� ���ڵ� �����ϴ� ���� ����

				String reviewId = rs.getString(1); // �����ͺ��̽����� ��ȣ�� ������ �ͼ� reviewId ������ ����

				String menuId = rs.getString(2); // �����ͺ��̽����� �̸��� ������ �ͼ� menuId ������ ����

				String review = rs.getString(3); // �����ͺ��̽����� �ּҰ� ������ �ͼ� review ������ ����

				String recommend = rs.getString(4);

				// ���Ϳ� ������ �� �߰�

				in.add(reviewId);

				in.add(menuId);

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

	private void insert(String reviewId, String menuId, String review) {

		try {

			// PreparedStatement ����-> conn�� preDbTreatment() �޼ҵ带 ���� �����Ǿ� ����

			pstmtAdd = conn.prepareStatement("insert into menu1.reviews values(?,?,?)");

			// insert into member values(? -> 1 ,? -> 2, ? -> 3)" ������ ? �� �� ����

			pstmtAdd.setString(1, reviewId);

			pstmtAdd.setString(2, menuId);

			pstmtAdd.setString(3, review);

			// ���Թ��� ������ ���� -> �Է� (insert)

			pstmtAdd.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	private void update(String menuId, String review, String reviewId) {

		try {

			// PreparedStatement ����-> conn�� preDbTreatment() �޼ҵ带 ���� �����Ǿ� ����

			pstmtUpdate = conn.prepareStatement("update menu1.reviews set menuId = ?, review = ? where reviewId = ?");

			// �� ����

			pstmtUpdate.setString(1, menuId);

			pstmtUpdate.setString(2, review);

			// ? ���� �߿� Ȯ�� �ʿ���

			pstmtUpdate.setString(3, reviewId);

			// ���� ����

			pstmtUpdate.executeUpdate();

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

		JdbcVectorTableEvnetSample frame = new JdbcVectorTableEvnetSample();

	}
}
