package javaUtil;


import java.sql.*;
public class mysqlJdbc {
  public static void main(String args[]) {
    try {
      Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
     System.out.println("Success loading Mysql Driver!");
    }
    catch (Exception e) {
      System.out.print("Error loading Mysql Driver!");
      e.printStackTrace();
    }
    try {
      Connection connect = DriverManager.getConnection(
          "jdbc:mysql://jovanht.cn:3306/hotel","hotel","hotel888");
           //����URLΪ   jdbc:mysql//��������ַ/���ݿ���  �������2�������ֱ��ǵ�½�û���������

      System.out.println("Success connect Mysql server!");
      Statement stmt = connect.createStatement();
      ResultSet rs = stmt.executeQuery("select * from user");
                                                              //user Ϊ��������
while (rs.next()) {
        System.out.println(rs.getString("uname"));
      }
    }
    catch (Exception e) {
      System.out.print("get data error!");
      e.printStackTrace();
    }
  }
}
