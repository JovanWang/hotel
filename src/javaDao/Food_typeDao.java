package javaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javaModel.Result;
import javaModel.Food_type;
import javaUtil.ConUtil;
import javaUtil.TimeNowUtil;

public class Food_typeDao {
	private ConUtil conUtil=new ConUtil();
	//开始创建新菜品
	public Result create(Food_type  ft){
		Result result = new Result();
		result.message = "网络连接错误！";
		String createTime = new TimeNowUtil().now();
		String sql;
		Connection con=null;
		if(ft.gettext()!=""){
			sql = "insert into food_type(name,text,create_time) values(?,?,?)";
			try {
				con = conUtil.getCon();
				PreparedStatement pst;
				pst = con.prepareStatement(sql);
				pst.setString(1, ft.getname());
				pst.setString(2, ft.gettext());
				pst.setString(3, createTime);
				int n=pst.executeUpdate();
				if(n <= 0){
					result.message = "添加新菜品失败！";
					result.success = false;
					return result;
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
		}else{
			sql = "insert into food_type(name,create_time) values(?,?)";
			try {
				con = conUtil.getCon();
				PreparedStatement pst;
				pst = con.prepareStatement(sql);
				pst.setString(1, ft.getname());
				pst.setString(2, createTime);
				int n=pst.executeUpdate();
				if(n <= 0){
					result.message = "添加新菜品失败！";
					result.success = false;
					return result;
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
		}
		
		
		result.message = "添加新菜品成功！";
		result.success = true;
		return result;
	}
	//更新菜品
	public Result update(Food_type  ft){
		Result result = new Result();
		result.message = "网络连接错误！";
		Food_type ftNow = new Food_type();
		String updateTime = new TimeNowUtil().now();
		String sql1 = "select * from food_type where id = ?";
		String sql2 = "update food_type set name = ?,text = ?,update_time = ?";
		Connection con=null;
		try {
			con = conUtil.getCon();
			con.setAutoCommit(false);
			PreparedStatement pst1,pst2;
			pst1 = con.prepareStatement(sql1);
			pst1.setInt(1, ft.getid());
			ResultSet res=pst1.executeQuery();
			if(!res.next()){
				result.success = false;
				result.message = "菜品不存在！";
				return result;
			}else{
				do{
					if(ft.getname()==""){
						ftNow.setname(res.getString("name"));
					}else{
						ftNow.setname(ft.getname());
					}
					if(ft.gettext()==""){
						ftNow.settext(res.getString("text"));
					}else{
						ftNow.settext(ft.gettext());
					}
		        }while(res.next());
			}

			pst2 = con.prepareStatement(sql2);
			pst2.setString(2, ftNow.gettext());
			pst2.setString(2, ftNow.gettext());
			pst2.setString(3, updateTime);
			int n=pst2.executeUpdate();
			if(n <= 0){
				result.message = "更新菜品失败！";
				result.success = false;
				return result;
			}
			con.commit();
		} catch (Exception e1) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			e1.printStackTrace();
		}finally{
			try {
				conUtil.closeCon(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		result.message = "添加新菜品成功！";
		result.success = true;
		return result;
	}
	//删除菜品
		public Result delete(int id){
			Result result = new Result();
			result.message = "网络连接错误！";
			String sql;
			Connection con=null;
			sql = "delete from food_type where id = ?";
			try {
				con = conUtil.getCon();
				PreparedStatement pst;
				pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				int n=pst.executeUpdate();
				if(n <= 0){
					result.message = "删除菜品失败！";
					result.success = false;
					return result;
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
			
			
			result.message = "删除菜品成功！";
			result.success = true;
			return result;
		}
		
		//获取所有菜品
		public ArrayList select(){
			ArrayList<Food_type> ftList = new ArrayList();
			String sql;
			Connection con=null;
			sql = "select * from food_type limit 0, 50";
			try {
				con = conUtil.getCon();
				PreparedStatement pst;
				pst = con.prepareStatement(sql);
				ResultSet res=pst.executeQuery();
				if(!res.next()){
					return null;
				}else{
					do{
						Food_type ft = new Food_type();
						ft.setid(res.getInt("id"));
						ft.setname(res.getString("name"));
						ft.settext(res.getString("text"));
						ft.setcreate_time(res.getString("create_time"));
						ft.setupdate_time(res.getString("update_time"));
						ftList.add(ft);
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
			return ftList;
		}
		//根据id获取菜品
				public ArrayList getId(int id){
					ArrayList<Food_type> ftList = new ArrayList();
					String sql;
					Connection con=null;
					sql = "select * from food_type where id = ? limit 0, 50";
					try {
						con = conUtil.getCon();
						PreparedStatement pst;
						pst = con.prepareStatement(sql);
						pst.setInt(1, id);
						ResultSet res=pst.executeQuery();
						if(!res.next()){
							return null;
						}else{
							do{
								Food_type ft = new Food_type();
								ft.setid(res.getInt("id"));
								ft.setname(res.getString("name"));
								ft.settext(res.getString("text"));
								ft.setcreate_time(res.getString("create_time"));
								ft.setupdate_time(res.getString("update_time"));
								ftList.add(ft);
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
					return ftList;
					
				}
}
