package javaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javaModel.Result;
import javaModel.Bill;
import javaModel.Food_type;
import javaDao.UserDao;
import javaUtil.ConUtil;
import javaUtil.TimeNowUtil;

public class BillDao {
	private ConUtil conUtil=new ConUtil();
	//开始点餐
	public Result create(int buffet_id,String  username){
		Result result = new Result();
		result.message = "网络连接错误！";
		int userId =new UserDao().getUserId(username);
		String createTime = new TimeNowUtil().now();
		String sql="insert into bill(buffet_id,user_id,create_time,status) values(?,?,?,?)";
		Connection con=null;
		try {
			con = conUtil.getCon();
			PreparedStatement pst;
			pst = con.prepareStatement(sql);
			pst.setInt(1, buffet_id);
			pst.setInt(2, userId);
			pst.setString(3, createTime);
			pst.setInt(4, 1);
			int n=pst.executeUpdate();
			if(n <= 0){
				result.message = "创建订单失败！";
				result.success = false;
				return result;
			}
			pst = con.prepareStatement("select @@IDENTITY as idnew");
			ResultSet res=pst.executeQuery();
			if(!res.next()){
				result.message = "创建订单失败！";
				result.success = false;
				return result;
			}else{
				do{
					result.data = res.getInt("idnew");
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
		result.message = "创建订单成功！";
		result.success = true;
		System.out.println(result.data);
		return result;
	}
	//用户提交点餐
	public Result userCommit(int id,double price_all){
		Result result = new Result();
		result.message = "网络连接错误！";
		String sql="update bill set price_all = ?,status = ? where id= ?";
		Connection con=null;
		try {
			con = conUtil.getCon();
			PreparedStatement pst;
			pst = con.prepareStatement(sql);
			pst.setDouble(1, price_all);
			pst.setInt(2, 2);
			pst.setInt(3, id);
			int n=pst.executeUpdate();
			if(n <= 0){
				result.message = "订单提交失败！";
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
		result.message = "订单提交成功！";
		result.success = true;
		return result;
	}
	//服务员结算订单
	public Result adminSettle(int id,double pay_price,String adminname){
		Result result = new Result();
		result.message = "网络连接错误！";
		int adminId =new UserDao().getUserId(adminname);
		String settleTime = new TimeNowUtil().now();
		String sql="update bill set pay_price = ?,admin_id = ?,settle_time = ?,status = ? where id = ?";
		Connection con=null;
		try {
			con = conUtil.getCon();
			PreparedStatement pst;
			pst = con.prepareStatement(sql);
			pst.setDouble(1, pay_price);
			pst.setInt(2, adminId);
			pst.setString(3, settleTime);
			pst.setInt(4, 3);
			pst.setInt(5, id);
			int n=pst.executeUpdate();
			if(n <= 0){
				result.message = "订单结算失败！";
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
		result.message = "订单结算成功！";
		result.success = true;
		return result;
	}
	//取消订单
		public Result abolish(int id){
			Result result = new Result();
			result.message = "网络连接错误！";
			String deleteTime = new TimeNowUtil().now();
			String sql="update bill set delete_time = ? where id = ?";
			Connection con=null;
			try {
				con = conUtil.getCon();
				PreparedStatement pst;
				pst = con.prepareStatement(sql);
				pst.setString(1, deleteTime);
				pst.setInt(2, id);
				int n=pst.executeUpdate();
				if(n <= 0){
					result.message = "订单取消失败！";
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
			result.message = "订单取消成功！";
			result.success = true;
			return result;
		}
	//根据用户返回订单列表
	public ArrayList list(String username){
		ArrayList<Bill> billList = new ArrayList();
		int billN = 0;
		int userId =new UserDao().getUserId(username);
		String sql="select  * from bill where user_id=? and delete_time is NULL limit 0, 10";
		Connection con=null;
		try {
			con = conUtil.getCon();
			PreparedStatement pst;
			pst = con.prepareStatement(sql);
			pst.setInt(1, userId);
			ResultSet res=pst.executeQuery();
			if(!res.next()){
				return null;
			}else{
				do{
					Bill bill = new Bill();
					bill.setid(res.getInt("id"));
					bill.setprice_all(res.getDouble("price_all"));
					bill.setpay_price(res.getDouble("pay_price"));
					bill.setsettle_time(res.getString("settle_time"));
					billList.add(bill);
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
		return billList;
		}
	//显示所有订单
	public ArrayList select(){
		ArrayList<Bill> billList = new ArrayList();
		int billN = 0;
		String sql="select  * from bill where delete_time is NULL limit 0, 10";
		Connection con=null;
		try {
			con = conUtil.getCon();
			PreparedStatement pst;
			pst = con.prepareStatement(sql);
			ResultSet res=pst.executeQuery();
			if(!res.next()){
				return null;
			}else{
				do{
					Bill bill = new Bill();
					bill.setid(res.getInt("id"));
					bill.setbuffet_id(res.getInt("buffet_id"));
					bill.setuser_id(res.getInt("user_id"));
					bill.setprice_all(res.getDouble("price_all"));
					bill.setpay_price(res.getDouble("pay_price"));
					bill.setstatus(res.getInt("status"));
					bill.setcreate_time(res.getString("create_time"));
					bill.setsettle_time(res.getString("settle_time"));
					billList.add(bill);
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
		return billList;
		}
}
