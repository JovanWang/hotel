package javaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javaModel.Result;
import javaModel.Bill;
import javaDao.UserDao;
import javaUtil.ConUtil;
import javaUtil.TimeNowUtil;

public class BillDao {
	private ConUtil conUtil=new ConUtil();
	//��ʼ���
	public Result create(int buffet_id,String  username){
		Result result = new Result();
		result.message = "�������Ӵ���";
		int userId =new UserDao().getUserId(username);
		String createTime = new TimeNowUtil().now();
		String sql="insert into bill('buffet_id','user_id','create_time') values(?,?,?)";
		Connection con=null;
		try {
			con = conUtil.getCon();
			PreparedStatement pst;
			pst = con.prepareStatement(sql);
			pst.setInt(1, buffet_id);
			pst.setInt(2, userId);
			pst.setString(3, createTime);
			int n=pst.executeUpdate();
			if(n <= 0){
				result.message = "��������ʧ�ܣ�";
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
		result.message = "���������ɹ���";
		result.success = true;
		return result;
	}
	//�û��ύ���
	public Result userCommit(int id,double price_all){
		Result result = new Result();
		result.message = "�������Ӵ���";
		String sql="update bill set price_all = ? where id= ?";
		Connection con=null;
		try {
			con = conUtil.getCon();
			PreparedStatement pst;
			pst = con.prepareStatement(sql);
			pst.setDouble(1, price_all);
			pst.setInt(2, id);
			int n=pst.executeUpdate();
			if(n <= 0){
				result.message = "�����ύʧ�ܣ�";
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
		result.message = "�����ύ�ɹ���";
		result.success = true;
		return result;
	}
	//����Ա���㶩��
	public Result adminSettle(int id,double pay_price,String adminname){
		Result result = new Result();
		result.message = "�������Ӵ���";
		int adminId =new UserDao().getUserId(adminname);
		String settleTime = new TimeNowUtil().now();
		String sql="update bill set pay_price = ?,admin_id = ?,settle_time = ? where id = ?";
		Connection con=null;
		try {
			con = conUtil.getCon();
			PreparedStatement pst;
			pst = con.prepareStatement(sql);
			pst.setDouble(1, pay_price);
			pst.setInt(2, adminId);
			pst.setString(3, settleTime);
			pst.setInt(4, id);
			int n=pst.executeUpdate();
			if(n <= 0){
				result.message = "��������ʧ�ܣ�";
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
		result.message = "��������ɹ���";
		result.success = true;
		return result;
	}
	//ȡ������
		public Result abolish(int id){
			Result result = new Result();
			result.message = "�������Ӵ���";
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
					result.message = "����ȡ��ʧ�ܣ�";
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
			result.message = "����ȡ���ɹ���";
			result.success = true;
			return result;
		}
	//�����û����ض����б�
	public Bill[] list(String username){
		Bill[] billList = new Bill[10];
		int billN = 0;
		int userId =new UserDao().getUserId(username);
		String sql="select top 10 * from bill where user_id=? and delete_time is NULL";
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
					billList[billN] = bill;
					billN++;
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