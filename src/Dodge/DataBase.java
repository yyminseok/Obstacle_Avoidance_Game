package Dodge;
import java.sql.*;
public class DataBase {
	public static void inserts(String id,int score,int level) {
		Connection conn;
	    Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ranking", "root", "arche");
			System.out.println("DB ���� �Ϸ�");
			stmt = conn.createStatement();
			String sql = "insert into game_rank(gameid,score,level) values('" + id + "'," + score + "," + level + ");";
			stmt.executeUpdate(sql);
			System.out.print("���� �Ϸ�");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args[]) {

	}
}