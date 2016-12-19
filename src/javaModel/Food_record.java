package javaModel;

public class Food_record {
	private int id;
	private int bill_id;
	private int food_id;
	private int food_num;
	private double record_price;
	private String create_time;
	private String delete_time;
	private String food_name;
public Food_record(){
	super();
}
public Food_record(int bill_id,int food_id,String create_time){
	super();
	this.bill_id=bill_id;
	this.food_id=food_id;
	this.create_time=create_time;
	
}
public int getid(){
	return id;
}
public int getbill_id(){
	return bill_id;
}
public int getfood_id(){
	return food_id;
}
public int getfood_num(){
	return food_num;
}
public double getrecord_price(){
	return record_price;
}
public String getcreate_time(){
	return create_time;
}

public String getdelete_time(){
	return delete_time;
}

public String getfood_name(){
	return food_name;
}
public void setid(int id){
	this.id=id;
}
public void setbill_id(int bill_id){
	this.bill_id=bill_id;
}
public void setfood_ids(int food_id){
	this.food_id=food_id;
}
public void setfood_num(int food_num){
	this.food_num=food_num;
}
public void setrecord_price(double record_price){
	this.record_price=record_price;
}
public void setcreate_time(String create_time){
	this.create_time=create_time;
}
public void setdelete_time(String delete_time){
	this.delete_time=delete_time;
}
public void setfood_name(String food_name){
	this.food_name=food_name;
}
}
