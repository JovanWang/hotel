package javaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javaModel.Buffet;
import javaModel.Result;
import javaUtil.TimeNowUtil;
import javaUtil.ConUtil;

public class BuffetDao {
	private ConUtil conUtil=new ConUtil();
	public Result create(int table_no,int seating_num){
		Result result = new Result();
		result.message = "网络连接错误！";
		String createTime = new TimeNowUtil().now();
		String sql;
		Connection con=null;
		if(table_no ==0 || seating_num == 0){
			result.message = "添加新桌失败！";
			result.success = false;
			return result;
		}else{
			sql = "insert into buffet('table_no','seating_num','status','create_time') values(?,?,?,?)";
			try {
				con = conUtil.getCon();
				PreparedStatement pst;
				pst = con.prepareStatement(sql);
				pst.setInt(1, table_no);
				pst.setInt(2, seating_num);
				pst.setInt(3, 1);
				pst.setString(4, createTime);
				int n=pst.executeUpdate();
				if(n <= 0){
					result.message = "添加新桌失败！";
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
		result.message = "添加新桌成功！";
		result.success = true;
		return result;
	}
	public Result update(int id,int seating_num){
		Result result = new Result();
		result.message = "网络连接错误！";
		String updateTime = new TimeNowUtil().now();
		String sql;
		Connection con=null;
		if(seating_num == 0){
			result.message = "更新新桌失败！";
			result.success = false;
			return result;
		}else{
			sql = "update buffet set seating_num = ?,update_time = ? where id = ?";
			try {
				con = conUtil.getCon();
				PreparedStatement pst;
				pst = con.prepareStatement(sql);
				pst.setInt(1, seating_num);
				pst.setString(2, updateTime);
				pst.setInt(3, id);
				int n=pst.executeUpdate();
				if(n <= 0){
					result.message = "更新新桌失败！";
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
		result.message = "更新新桌成功！";
		result.success = true;
		return result;
	}
	public Result delete(int id){
		Result result = new Result();
		result.message = "网络连接错误！";
		String updateTime = new TimeNowUtil().now();
		String sql;
		Connection con=null;
		if(id == 0){
			result.message = "删除新桌失败！";
			result.success = false;
			return result;
		}else{
			sql = "update buffet set delete_time = ? where id = ?";
			try {
				con = conUtil.getCon();
				PreparedStatement pst;
				pst = con.prepareStatement(sql);
				pst.setString(1, updateTime);
				pst.setInt(2, id);
				int n=pst.executeUpdate();
				if(n <= 0){
					result.message = "删除新桌失败！";
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
		result.message = "删除新桌成功！";
		result.success = true;
		return result;
	}
	public Buffet[] select(){
		Buffet[] buffetList = new Buffet[50];
		int buffetN = 0;
		String sql;
		Connection con=null;
		sql = "select top 50 * from buffet where delete_time is NULL";
		try {
			con = conUtil.getCon();
			PreparedStatement pst;
			pst = con.prepareStatement(sql);
			ResultSet res=pst.executeQuery();
			if(!res.next()){
				return null;
			}else{
				do{
					Buffet buffet = new Buffet();
					buffet.setid(res.getInt("id"));
					buffet.settable_no(res.getInt("table_no"));
					buffet.setseating_num(res.getInt("seating_num"));
					buffet.setstatus(res.getInt("status"));
					buffet.setcreate_time(res.getString("create_time"));
					buffet.setupdate_time(res.getString("update_time"));
					buffetList[buffetN] = buffet;
					buffetN++;
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
		return buffetList;
	}
	public Buffet[] getId(int id){
		Buffet[] buffetList = new Buffet[50];
		int buffetN = 0;
		String sql;
		Connection con=null;
		sql = "select top 50 * from buffet where id = ?, delete_time is NULL";
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
					Buffet buffet = new Buffet();
					buffet.setid(res.getInt("id"));
					buffet.settable_no(res.getInt("table_no"));
					buffet.setseating_num(res.getInt("seating_num"));
					buffet.setstatus(res.getInt("status"));
					buffet.setcreate_time(res.getString("create_time"));
					buffet.setupdate_time(res.getString("update_time"));
					buffetList[buffetN] = buffet;
					buffetN++;
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
		return buffetList;
	}
}
