package ui;

import java.awt.BorderLayout;
//ctrl+shift+o
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import javax.swing.table.DefaultTableModel;

public class CustomerMain extends JFrame {
	JPanel groundPane;
	JButton next1;
	int orderNum=0;
	MyTableModel model;
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
		String[] vMenus= {"베트남볶음면","소고기쌀국수","얼큰쌀국수","우더미쌀국수","파인애플볶음밥"};

		for(int i=0;i<menu.length;i++) {
			menu[i]=new ImageIcon("images/"+vMenus[i]+".jpg");
		}

		//쌀국수탭
		JPanel vietnamNoodle=new JPanel();
		vietnamNoodle.setLayout(new GridLayout(0,3,3,3));


		JPanel vNoodle_01=new JPanel();
		vietnamNoodle.add(vNoodle_01);
		JLabel imageLabel=new JLabel();
		imageLabel.setIcon(menu[0]);
		vNoodle_01.add(imageLabel,BorderLayout.NORTH);
		JButton btnAdd=new JButton( new AbstractAction("담기") {
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	        	int id=1;
	            String name="베트남볶음면";
	            int price=4500;
	            orderNum+=1;
	            String restaurant="골목식당";
	            
	            try {
	            	Connection conn=makeConnection();
	            	Statement stmt=conn.createStatement();
	            	stmt.executeUpdate("INSERT INTO ordersheet VALUES ("+id+","+orderNum+",'"+name+"',"+price+",'"+restaurant+"')");
	            	
	            	conn.close();
	            }catch(SQLException ex) {
	            	System.err.println("Caught Exception: "+ex.getMessage());
	            }
	            model.fillTable();
	        }
	    });
		vNoodle_01.add(btnAdd,BorderLayout.SOUTH);
		vNoodle_01.add(new JButton("♥(14)"),BorderLayout.SOUTH);
		vNoodle_01.setBackground(new Color(255,255,255,100));

		JPanel vNoodle_02=new JPanel();
		vietnamNoodle.add(vNoodle_02);
		JLabel imageLabel2=new JLabel();
		imageLabel2.setIcon(menu[1]);
		vNoodle_02.add(imageLabel2,BorderLayout.NORTH);
		vNoodle_02.setSize(300,200);
		JButton btnAdd2=new JButton( new AbstractAction("담기") {
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	        	int id=2;
	            String name="베트남쌀국수";
	            int price=3000;
	            orderNum+=1;
	            String restaurant="골목식당";
	            
	            try {
	            	Connection conn=makeConnection();
	            	Statement stmt=conn.createStatement();
	            	stmt.executeUpdate("INSERT INTO ordersheet VALUES ("+id+","+orderNum+",'"+name+"',"+price+",'"+restaurant+"')");
	            	
	            	conn.close();
	            }catch(SQLException ex) {
	            	System.err.println("Caught Exception: "+ex.getMessage());
	            }
	            model.fillTable();
	        }
	    });
		vNoodle_02.add(btnAdd2,BorderLayout.SOUTH);
		vNoodle_02.add(new JButton("♥(45)"),BorderLayout.SOUTH);
		vNoodle_02.setBackground(new Color(255,255,255,100));

		JPanel vNoodle_03=new JPanel();
		vietnamNoodle.add(vNoodle_03);
		vNoodle_03.setBackground(new Color(255,255,255,100));

		JPanel vNoodle_04=new JPanel();
		vietnamNoodle.add(vNoodle_04);
		vNoodle_04.setBackground(new Color(255,255,255,100));

		tabPane.addTab("베트남 골목식당", vietnamNoodle);

		//국수나무탭
		JPanel panel2_2=new JPanel();
		panel2_2.setLayout(new GridLayout());
		tabPane.addTab("국수나무", new JLabel("국수나무"));

		//tabPane.addTab("본래순대", new JLabel("본래순대"));

		//장바구니

		model=new MyTableModel();
		model.fillTable();
		JTable table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);



		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(730,20,240,440);
		groundPane.add(tablePanel);
		tablePanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		tablePanel.add(scrollPane);

		tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
		scrollPane.setViewportView(table);

		table.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		tablePanel.setBackground(new Color(255,255,255,100));



		//다음단계 버튼
		JButton next1=new JButton( new AbstractAction("결제") {
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	            new Order();
	            groundPane.setVisible(false);
	        }
	    });
		groundPane.add(next1);
		next1.setFont(new Font("나눔고딕",Font.BOLD,50));
		next1.setBounds(730,470,240,170);
		next1.setBackground(new Color(255,255,255,250));
		next1.addActionListener(new MyListener());


		setVisible(true);


	}
	
	public class MyTableModel extends AbstractTableModel{
		private String[] columnNames= {"주문","상품명","가격","주문처"};
		private static final int ROWS=10;
		private static final int COLS=4;
		Object[][] data=new String[ROWS][COLS];
		@Override
		public int getRowCount() {
			return data.length;
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public String getColumnName(int col) {
			return columnNames[col].toString();
		}
		
		public void fillTable() {
			try {
				Connection conn=makeConnection();
				Statement stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery("SELECT * FROM menu1.ordersheet");
				
				int row=0;
				while(rs.next()) {
					data[row][0]=rs.getString("orderNum");
					data[row][1]=rs.getString("name");
					data[row][2]=rs.getString("price");
					data[row][3]=rs.getString("restaurant");
					row++;
				}conn.close();
			}catch(SQLException e) {
				System.err.println("Caught Exception:"+e.getMessage());
			}
			fireTableDataChanged();
		}
		
		public void setValueAt(Object value, int row, int col) {
			data[row][col]=value;
			fireTableCellUpdated(row,col);
		}

		@Override
		public Object getValueAt(int row, int col) {
			return data[row][col];
		}
		
	}
	
	//db 연결
	public static Connection makeConnection() {
		String url="jdbc:mysql://localhost/menu1";
		String id="root";
		String password="rhksflwk123!";
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 적재 성공");
			con=DriverManager.getConnection(url,id,password);
			System.out.println("데이터베이스 연결 성공");
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다");
		}catch (SQLException e) {
			System.out.println("연결에 실패하였습니다.");
		}return con;
	}
	
	

	//이유를 모르겠지만 버튼 변수명이 인식이 안돼서 보류..ㅠㅠ 
	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == next1) { 
				new Order();
				groundPane.setVisible(false);
			}else { 
				new Order();
				groundPane.setVisible(false);
			}


		}
	}


	public static void main(String args[]) {
		CustomerMain frame=new CustomerMain();
	}


}
