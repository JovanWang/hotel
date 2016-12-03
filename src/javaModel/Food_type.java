package javaModel;

public class Food_type {
private int id;
private String name;
private String text;
private String note;
private String create_time;
private String update_time;
	public Food_type(){
		super();
	}
	public Food_type(String name,String create_time){
		super();
		this.name=name;
		this.create_time=create_time;
	}
	public int getid(){
		return id;
	}
	public String getname(){
		return name;
	}
	public String gettext(){
		return text;
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
	public void setid(int id){
		this.id=id;
	}
	public void setname(String name){
		this.name=name;
	}
	public void settext(String text){
		this.text=text;
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
}
