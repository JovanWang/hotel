package javaUtil;

/**
 * ��ȡ���ݿ����ӣ��ر����ݿ�����
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class ConUtil {

	private String dbUrl="jdbc:mysql://jovanht.cn:3306/hotel?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true";
	private String dbUserName="hotel";//�û���
	private String dbPasssword="hotel888";//����
	private String jdbcName="com.mysql.jdbc.Driver";
	//��ȡ���ݿ�����
	public Connection getCon() throws Exception {
		Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPasssword);
		return con;
	}

	// �ر����ݿ�����
	public void closeCon(Connection con) throws Exception {
		if (con != null) {
			con.close();
		}
	}
}
