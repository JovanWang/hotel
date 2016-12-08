package javaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javaModel.Food;
import javaModel.Food_type;
import javaModel.Result;
import javaUtil.ConUtil;
import javaUtil.TimeNowUtil;

public class FoodDao {
	private ConUtil conUtil=new ConUtil();
	public Result create(Food food){
		Result result = new Result();
		result.message = "ÍøÂçÁ¬½Ó´íÎó£¡";
		String createTime = new TimeNowUtil().now();
		String sql;
		Connection con=null;
		if(food.getname() =="" || food.getprice() == 0){
			result.message = "Ìí¼ÓÐÂ²ËÊ§°Ü£¡";
			result.success = false;
			return result;
		}else{
			sql = "insert into food('name','food_type_id','mnemonic_no','price','unit','img_src','create_time') values(?,?,?,?,?,?,?)";
			try {
				con = conUtil.getCon();
				PreparedStatement pst;
				pst = con.prepareStatement(sql);
				pst.setString(1, food.getname());
				pst.setInt(2, food.getfood_type_id());
				pst.setString(3, food.getmnemonic_no());
				pst.setDouble(4, food.getprice());
				pst.setString(5, food.getunit());
				pst.setString(6, "/img/");
				pst.setString(7, createTime);
				int n=pst.executeUpdate();
				if(n <= 0){
					result.message = "Ìí¼ÓÐÂ²ËÊ§°Ü£¡";
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
		result.message = "Ìí¼ÓÐÂ²Ë³É¹¦£¡";
		result.success = true;
		return result;
	}
	public Result update(Food food){
		Result result = new Result();
		result.message = "ÍøÂçÁ¬½Ó´íÎó£¡";
		String updateTime = new TimeNowUtil().now();
		String sql;
		Connection con=null;
		if(food.getid() == 0){
			result.message = "¸üÐÂÐÂ²ËÊ§°Ü£¡";
			result.success = false;
			return result;
		}else{
			sql = "update food set price = ?,describe = ?,rank = ?,update_time = ? where id = ?";
			try {
				con = conUtil.getCon();
				PreparedStatement pst;
				pst = con.prepareStatement(sql);
				pst = con.prepareStatement(sql);
				pst.setDouble(1, food.getprice());
				pst.setString(2, food.getdescribe());
				pst.setInt(3, food.getrank());
				pst.setString(4, updateTime);
				pst.setInt(5, food.getid());
				int n=pst.executeUpdate();
				if(n <= 0){
					result.message = "¸üÐÂÐÂ²ËÊ§°Ü£¡";
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
		result.message = "¸üÐÂÐÂ²Ë³É¹¦£¡";
		result.success = true;
		return result;
	}
	public Result delete(int id){
		Result result = new Result();
		result.message = "ÍøÂçÁ¬½Ó´íÎó£¡";
		String updateTime = new TimeNowUtil().now();
		String sql;
		Connection con=null;
		if(id == 0){
			result.message = "É¾³ýÐÂ²ËÊ§°Ü£¡";
			result.success = false;
			return result;
		}else{
			sql = "update food set delete_time = ? where id = ?";
			try {
				con = conUtil.getCon();
				PreparedStatement pst;
				pst = con.prepareStatement(sql);
				pst.setString(1, updateTime);
				pst.setInt(2, id);
				int n=pst.executeUpdate();
				if(n <= 0){
					result.message = "É¾³ýÐÂ²ËÊ§°Ü£¡";
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
		result.message = "É¾³ýÐÂ²Ë³É¹¦£¡";
		result.success = true;
		return result;
	}
	public ArrayList select(){
		ArrayList<Food> foodList = new ArrayList();
		String sql;
		Connection con=null;
		sql = "select * from food where delete_time is NULL limit 0, 50";
		try {
			con = conUtil.getCon();
			PreparedStatement pst;
			pst = con.prepareStatement(sql);
			ResultSet res=pst.executeQuery();
			if(!res.next()){
				return null;
			}else{
				do{
					Food food = new Food();
					food.setid(res.getInt("id"));
					food.setname(res.getString("name"));
					food.setfood_type_id(res.getInt("food_type_id"));
					food.setmnemonic_no(res.getString("mnemonic_no"));
					food.setprice(res.getDouble("price"));
					food.setunit(res.getString("unit"));
					food.setrank(res.getInt("rank"));
					food.setimg_src(res.getString("img_src"));
					food.setdescribe(res.getString("describe"));
					food.setcreate_time(res.getString("create_time"));
					food.setupdate_time(res.getString("update_time"));
					foodList.add(food);
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
		return foodList;
	}
	public ArrayList getId(int id){
		ArrayList<Food> foodList = new ArrayList();
		String sql;
		Connection con=null;
		sql = "select * from food where id = ?, delete_time is NULL limit 0, 50";
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
					Food food = new Food();
					food.setid(res.getInt("id"));
					food.setname(res.getString("name"));
					food.setfood_type_id(res.getInt("food_type_id"));
					food.setmnemonic_no(res.getString("mnemonic_no"));
					food.setprice(res.getDouble("price"));
					food.setunit(res.getString("unit"));
					food.setrank(res.getInt("rank"));
					food.setimg_src(res.getString("img_src"));
					food.setdescribe(res.getString("describe"));
					food.setcreate_time(res.getString("create_time"));
					food.setupdate_time(res.getString("update_time"));
					foodList.add(food);
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
		return foodList;
	}
	public ArrayList getType(int id){
		ArrayList<Food> foodList = new ArrayList();
		String sql;
		Connection con=null;
		sql = "select * from food where food_type_id = ?, delete_time is NULL limit 0, 50";
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
					Food food = new Food();
					food.setid(res.getInt("id"));
					food.setname(res.getString("name"));
					food.setfood_type_id(res.getInt("food_type_id"));
					food.setmnemonic_no(res.getString("mnemonic_no"));
					food.setprice(res.getDouble("price"));
					food.setunit(res.getString("unit"));
					food.setrank(res.getInt("rank"));
					food.setimg_src(res.getString("img_src"));
					food.setdescribe(res.getString("describe"));
					food.setcreate_time(res.getString("create_time"));
					food.setupdate_time(res.getString("update_time"));
					foodList.add(food);
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
		return foodList;
	}
}
