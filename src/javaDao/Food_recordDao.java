package javaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javaModel.Bill;
import javaModel.Food_record;
import javaModel.Result;
import javaUtil.ConUtil;
import javaUtil.TimeNowUtil;

public class Food_recordDao {
	private ConUtil conUtil=new ConUtil();
	//创建餐单
	public Result create(int bill_id,int food_id,int food_num){
		Result result = new Result();
		result.message = "网络连接错误！";
		String createTime = new TimeNowUtil().now();
		String sql="insert into food_record('bill_id','food_id','food_num','create_time') values(?,?,?,?)";
		Connection con=null;
		try {
			con = conUtil.getCon();
			PreparedStatement pst;
			pst = con.prepareStatement(sql);
			pst.setInt(1, bill_id);
			pst.setInt(2, food_id);
			pst.setInt(3, food_num);
			pst.setString(4, createTime);
			int n=pst.executeUpdate();
			if(n <= 0){
				result.message = "创建餐单失败！";
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
		result.message = "创建餐单成功！";
		result.success = true;
		return result;
	}
	//取消订单
			public Result abolish(int id){
				Result result = new Result();
				result.message = "网络连接错误！";
				String deleteTime = new TimeNowUtil().now();
				String sql="update food_record set delete_time = ? where id = ?";
				Connection con=null;
				try {
					con = conUtil.getCon();
					PreparedStatement pst;
					pst = con.prepareStatement(sql);
					pst.setString(1, deleteTime);
					pst.setInt(2, id);
					int n=pst.executeUpdate();
					if(n <= 0){
						result.message = "餐单取消失败！";
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
				result.message = "餐单取消成功！";
				result.success = true;
				return result;
			}
	//根据定单号获取餐单
	public Food_record[] list(int bill_id){
		Food_record[] frList = new Food_record[50];
		int frN = 0;
		String sql="select top 50 food_record.*,food.name as food_name from food_record left join food on food_record.food_id = food.id  where food_record.bill_id=?  and food_record.delete_time is NULL";
		Connection con=null;
		try {
			con = conUtil.getCon();
			PreparedStatement pst;
			pst = con.prepareStatement(sql);
			pst.setInt(1, bill_id);
			ResultSet res=pst.executeQuery();
			if(!res.next()){
				return null;
			}else{
				do{
					Food_record fr = new Food_record();
					fr.setid(res.getInt("id"));
					fr.setfood_name(res.getString("food_name"));
					fr.setfood_num(res.getInt("food_num"));
					fr.setcreate_time(res.getString("create_time"));
					frList[frN] = fr;
					frN++;
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
		return frList;
		}
}
