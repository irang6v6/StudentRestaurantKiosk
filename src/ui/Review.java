package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Review {
	
	
	
	//db ����
		public static Connection makeConnection() {
			String url="jdbc:mysql://localhost/menu1";
			String id="root";
			String password="rhksflwk123!";
			Connection con=null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("����̹� ���� ����");
				con=DriverManager.getConnection(url,id,password);
				System.out.println("�����ͺ��̽� ���� ����");
			}catch (ClassNotFoundException e) {
				System.out.println("����̹��� ã�� �� �����ϴ�");
			}catch (SQLException e) {
				System.out.println("���ῡ �����Ͽ����ϴ�.");
			}return con;
		}
		
		public static void main(String[] args) {
			Review review=new Review();
		}

}
