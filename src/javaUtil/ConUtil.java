package javaUtil;

/**
 * 获取数据库连接，关闭数据库连接
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class ConUtil {

	private String dbUrl="jdbc:mysql://localhost:3306/test";
	private String dbUserName="root";//用户名
	private String dbPasssword="hao456789";//密码
	private String jdbcName="com.mysql.jdbc.Driver";
	//获取数据库连接
	public Connection getCon() throws Exception {
		Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPasssword);
		return con;
	}

	// 关闭数据库连接
	public void closeCon(Connection con) throws Exception {
		if (con != null) {
			con.close();
		}
	}
}
