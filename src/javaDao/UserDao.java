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
	public Result login(String name,String password,int role){
		Result result = new Result();
		result.message = "网络连接错误！";
		String sql="select * from user where name=? and role_id=?";
		Connection con=null;
		try {
			con = conUtil.getCon();
			PreparedStatement pst;
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, role);
			ResultSet res=pst.executeQuery();

			if(!res.next()){
				result.success = false;
				result.message = "该类型用户不存在！";
				return result;
			}else{
				do{
					//user.setId(Integer.parseInt(res.getString("id")));
					if(!res.getString("password").equals(password)){
						result.success = false;
						result.message = "密码错误！";
						return result;
					}
		        }while(res.next());
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
	public int getUserId(String username){
		String sql="select * from user where name=? ";
		int userId = -1;
		Connection con=null;
		try {
			con = conUtil.getCon();
			PreparedStatement pst;
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet res=pst.executeQuery();

			if(!res.next()){
				return -1;
			}else{
				do{
					userId = res.getInt("Id");
		        }while(res.next());
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
		return userId;
	}
}
