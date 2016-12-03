package javaModel;

public class Food {
	private int id;
	private String name;
	private int food_type_id;
	private String mnemonic_no;
	private double price;
	private String unit;
	private int rank;
	private String describe;
	private String img_src;
	private String note;
	private String create_time;
	private String update_time;
	private String delete_time;
public Food(){
	super();
}
public Food(String name,int food_type_id,double price,String create_time){
	super();
	this.name=name;
	this.food_type_id=food_type_id;
	this.price=price;
	this.create_time=create_time;
}
public int getid(){
	return id;
}
public int getfood_type_id(){
	return food_type_id;
}
public String getmnemonic_no(){
	return mnemonic_no;
}
public double getprice(){
	return price;
}
public String getunit(){
	return unit;
}
public int getrank(){
	return rank;
}
public String getdescribe(){
	return describe;
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
public String getname(){
	return name;
}
public void setid(int id){
	this.id=id;
}
public void setfood_type_id(int food_type_id){
	this.food_type_id=food_type_id;
}
public void setmnemonic_no(String mnemonic_no){
	this.mnemonic_no=mnemonic_no;
}
public void setprice(double price){
	this.price=price;
}
public void setunit(String unit){
	this.unit=unit;
}
public void setrank(int rank){
	this.rank=rank;
}
public void setdescribe(String describe){
	this.describe=describe;
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
}
public void setname(String name){
	this.name=name;
}
}
