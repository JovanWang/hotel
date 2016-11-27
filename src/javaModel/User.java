package javaModel;

public class User {
	private int id;
	private String name;
	private String password;
	private String pay_password;
	private int role_id;
	private String email;
	private int phone;
	private double money;
	private int integral;
	private String describe;
	private String sex;
	private int age;
	private String img_src;
	private String note;
	private String create_time;
	private String update_time;
	private String delete_time;
	public User(){
		super();
	}
	public User(String name,String password,int role_id,String email,int integral,String create_time){
		super();
		this.name = name;
		this.password = password;
		this.role_id = role_id;
		this.email = email;
		this.integral = integral;
		this.create_time=create_time;
	}
	//////////////////////////////////////////////////////////////////////////////////////
	public int getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getPassword(){
		return password;
	}
	public String getpay_password(){
		return pay_password;
	}
	public int getrole_id(){
		return role_id;
	}
	public String getemail(){
		return email;
	}
	public int getphone(){
		return phone;
	}
	public double getmoney(){
		return money;
	}
	public int getintegral(){
		return integral;
	}
	public String getdescribe(){
		return describe;
	}
	public String getsex(){
		return sex;
	}
	public int getage(){
		return age;
	}
	public String getimg_src(){
		return img_src;
	}
	public String getnote(){
		return note;
	}
	public String getcreate_time(){
		return create_time;
	}
	public String getupdate_time(){
		return update_time;
	}
	public String getdelete_time(){
		return delete_time;
	}
	//////////////////////////////////////////////////////////////////////////
	public void setId(int id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void setpay_password(String pay_password){
		this.pay_password=pay_password;
	}
	public void setrole_id(int role_id){
		this.role_id=role_id;
	}
	public void setemail(String email){
		this.email=email;
	}
	public void setphone(int phone){
		this.phone=phone;
	}
	public void setmoney(double money){
		this.money=money;
	}
	public void setintegral(int integral){
		this.integral=integral;
	}
	public void setdescribe(String describe){
		this.describe=describe;
	}
	public void setsex(String sex){
		this.sex=sex;
	}
	public void setage(int age){
		this.age=age;
	}
	public void setimg_src(String img_src){
		this.img_src=img_src;
	}
	public void setnote(String note){
		this.note=note;
	}
	public void setcreate_time(String create_time){
		this.create_time=create_time;
	}
	public void setupdate_time(String update_time){
		this.update_time=update_time;
	}
	public void setdelete_time(String delete_time){
		this.delete_time=delete_time;
	}}