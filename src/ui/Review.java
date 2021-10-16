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
	
	private String Url = "jdbc:mysql://localhost/menu1"; // URL 정보 저장 변수
	private String user = "root"; // user 정보 저장 변수 -> hr
	private String password = "rhksflwk123!"; // password 정보 저장 변수 -> hr
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

			// 테이블에 표시될 데이터 벡터 생성

			data = new Vector<>();

			// 테이블에 표시될 타이틀 벡터 생성, 초기화

			title = new Vector<>();

			title.add("리뷰");
			title.add("추천");
			
			
			// 테이블에 표시될 모델 객체 생성

			model = new DefaultTableModel();

			Vector result = selectAll(selectedMenuId);

			// 모델에 변경된 데이터(result)를 새로 적용

			model.setDataVector(result, title);

			// 모델을 통해 테이블 생성

			table = new JTable(model);
			table.setFont(new java.awt.Font("나눔고딕", Font.PLAIN, 20));
			table.getColumnModel().getColumn(0).setPreferredWidth(800);
			table.getColumnModel().getColumn(1).setPreferredWidth(30);
			table.setRowHeight(40);
			
			

			// 테이블에 스크롤팬 생성

			JScrollPane sp = new JScrollPane(table);

			JPanel panel = new JPanel();
			panel.setBackground(new Color(204, 204, 255));
			// 값을 입력받거나 표시할 텍스트필드(번호, 이름, 주소) 생성


			tfReview = new JTextField(40);

			// 레이블 생성


			lblReview = new JLabel("리뷰 작성");
			lblReview.setFont(new java.awt.Font("나눔고딕", Font.PLAIN, 15));


			// 버튼(추가, 삭제, 수정, 초기화) 생성

			btnAdd = new JButton("추가");

			btnClear = new JButton("닫기");
			
			lblRcmd=new JLabel("추천:");

			JRadioButton rb1 = new JRadioButton("O");
			JRadioButton rb2 = new JRadioButton("X");

			ButtonGroup bg = new ButtonGroup();
			bg.add(rb1);
			bg.add(rb2);

			btnAdd.addActionListener(new ActionListener() {

				// actionPerformed(ActionEvent e) : 추가 버튼 클릭시 호출될 메소드

				// 추가버튼을 클릭하면 처리할 내용 작성

				@Override

				public void actionPerformed(ActionEvent e) {

					// 현재 텍스트 필드에 있는 값을 각각의 변수에 대입

					int menuId = selectedMenuId; // 이름

					String review = tfReview.getText(); // 주소

					Enumeration<AbstractButton> enums = bg.getElements();
					String rc = null;
					while (enums.hasMoreElements()) { // hasMoreElements() Enum에 더 꺼낼 개체가 있는지 체크한다. 없으며 false 반환
						AbstractButton ab = enums.nextElement(); // 제네릭스가 AbstractButton 이니까 당연히 AbstractButton으로 받아야함
						JRadioButton jb = (JRadioButton) ab; // 형변환. 물론 윗줄과 이줄을 합쳐서 바로 형변환 해서 받아도 된다.

						if (jb.isSelected()) // 받아낸 라디오버튼의 체크 상태를 확인한다. 체크되었을경우 true 반환.
							rc = (jb.getText().trim()); // getText() 메소드로 문자열 받아낸다.
					}

					// 각각의 변수에 저장된 값을 데이터베이스에 Insert하는 메소드

					insert(menuId, review, rc);

					// 신규 저장된 데이터를 데이터베이스에서 다시 읽어와서 result 벡터에 저장

					Vector result = selectAll(selectedMenuId);

					// 변경된 데이터(벡터)로 모델 갱신 -> 테이블 표시 갱신됨

					model.setDataVector(result, title);

					table.getColumnModel().getColumn(0).setPreferredWidth(800);
					table.getColumnModel().getColumn(1).setPreferredWidth(30);
					tfReview.setText("");

				}

			});

			// 초기화 버튼 이벤트(클릭시) 처리 -> 텍스트필드 초기화, 번호텍스트필드에 커서 위치

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

			// Frame의 ContentPane 컨테이너 가지오기

			Container c = getContentPane();

			// 컨테이너에 테이블, 패널(텍스트필드, 번트이 포함된 패널) 추가


			c.add(sp, BorderLayout.CENTER);

			c.add(panel, BorderLayout.SOUTH);

			// 프레임 종료시 처리될 이벤트 처리

			addWindowListener(new WindowAdapter() {

				@Override

				public void windowClosing(WindowEvent w) {

					try {

						stmt.close(); // Statement 객체 닫기

						conn.close(); // Connection 객체 닫기

						setVisible(false); // 화면 닫기

						dispose(); // 자원 반납

						System.exit(0); // 종료 처리

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

				Vector in = new Vector<String>(); // 1개의 레코드 저장하는 벡터 생성

				//String reviewId = rs.getString(1); // 데이터베이스에서 번호값 가지고 와서 reviewId 변수에 저장

				//String menuId = rs.getString(2); // 데이터베이스에서 이름값 가지고 와서 menuId 변수에 저장

				String review = rs.getString(3); // 데이터베이스에서 주소값 가지고 와서 review 변수에 저장

				String recommend = rs.getString(4);

				// 벡터에 각각의 값 추가

				//in.add(reviewId);

				//in.add(menuId);

				in.add(review);

				in.add(recommend);

				// 전체 데이터를 저장하는 벡터에 in(1명의 데이터 저장) 벡터 추가

				data.add(in);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return data; // 전체 데이터 저장하는 data 벡터 리턴
	}

	private void insert(int selectedMenuId, String review, String rc) {

		try {

			// PreparedStatement 생성-> conn은 preDbTreatment() 메소드를 통해 생성되어 있음

			pstmtAdd = conn.prepareStatement("insert into menu1.reviews (menuId,review,likes) values(?,?,?)");

			// insert into member values(? -> 1 ,? -> 2, ? -> 3)" 각각의 ? 에 값 대입

			pstmtAdd.setInt(1, selectedMenuId);

			pstmtAdd.setString(2, review);

			pstmtAdd.setString(3, rc);

			// 대입받은 쿼리를 실행 -> 입력 (insert)

			pstmtAdd.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

//	private void update(String menuId, String review, String reviewId) {
//
//		try {
//
//			// PreparedStatement 생성-> conn은 preDbTreatment() 메소드를 통해 생성되어 있음
//
//			pstmtUpdate = conn.prepareStatement("update menu1.reviews set menuId = ?, review = ? where reviewId = ?");
//
//			// 값 대입
//
//			pstmtUpdate.setString(1, menuId);
//
//			pstmtUpdate.setString(2, review);
//
//			// ? 순서 중요 확인 필요함
//
//			pstmtUpdate.setString(3, reviewId);
//
//			// 쿼리 실행
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
