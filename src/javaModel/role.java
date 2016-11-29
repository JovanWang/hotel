package javaModel;

public class Role {
	private String name;
	private String create_time;
	private String text;
	private String note;
	private int id;
	public Role(){
		super();
	}
	/////////////////////////////////
	public Role(String name,String create_time){
		super();
		this.name = name;
		this.create_time=create_time;
	}
	/////////////////////////////////
	public String getName(){
		return name;
	}
	public String getcreate_time(){
		return create_time;
	}
	public String gettext(){
		return text;
	}
	public String getnote(){
		return note;
	}
	public int getid(){
		return id;
	}
	///////////////////////////////
	public void setName(String name){
		this.name = name;
	}
	public void setcreate_time(String create_time){
		this.create_time=create_time;
	}
	public void settext(String text){
		this.text=text;
	}
	public void setnote(String note){
		this.note=note;
	}
	public void setid(int id){
		this.id=id;
	}
}
