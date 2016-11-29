package javaModel;

public class Bill {
private int id;
private int buffet_id;
private int user_id;
private int admin_id;
private double price_all;
private String settle_time;
private String create_time;
private String delete_time;
private double billcol;
public Bill(){
	super();
}
public Bill(int buffet_id,int user_id,String create_time){
	super();
	this.buffet_id=buffet_id;
	this.user_id=user_id;
	this.create_time=create_time;
	
}
public int getid(){
	return id;
}
public int getbuffet_id(){
	return buffet_id;
}
public int getuser_id(){
	return user_id;
}
public int getadmin_id(){
	return admin_id;
}
public double getprice_all(){
	return price_all;
}
public String getsettle_time(){
	return settle_time;
}
public String getcreate_time(){
	return create_time;
}
public String getdelete_time(){
	return delete_time;
}
public double getbillcol(){
	return billcol;
}
/////////////////////////////////////////
public void getid(int id){
	this.id =id;
}

public void setbuffet_id(int buffet_id){
	this.buffet_id=buffet_id;
}
public void setuser_id(int user_id){
	this.user_id=user_id;
}
public void setadmin_id(int admin_id){
	this.admin_id=admin_id;
}
public void setprice_all(double price_all){
	this.price_all=price_all;
}
public void setsettle_time(String settle_time){
	this.settle_time=settle_time;
}
public void setcreate_time(String create_time){
	this.create_time=create_time;
}
public void setdelete_time(String delete_time){
this.delete_time=delete_time;
}
public void setbillcol(double billcol){
	this.billcol=billcol;
}
}
