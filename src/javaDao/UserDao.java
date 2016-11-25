package javaDao;

import java.sql.Connection;

import javaModel.Result;
import javaModel.User;
import javaUtil.ConUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	private ConUtil conUtil=new ConUtil();
	public Result login(String name,String password){
		Result result = new Result();
		result.message = "网络连接错误！";
		User user = new User();
		String sql="select * from user where name=?";
		Connection con=null;
		try {
			con = conUtil.getCon();
			PreparedStatement pst;
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet res=pst.executeQuery();

			if(!res.next()){
				result.success = false;
				result.message = "用户不存在！";
				return result;
			}
			while(res.next()){
				user=new User();
				user.setId(Integer.parseInt(res.getString("id")));
				user.setName(res.getString("UserName"));
				if(!user.getPassword().equals(password)){
					result.success = false;
					result.message = "密码错误！";
					return result;
				}
				user.setPassword(res.getString("Password"));
	        }
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			try {
				conUtil.closeCon(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		result.message = "登录成功！";
		result.success = true;
		return result;
	}
}
