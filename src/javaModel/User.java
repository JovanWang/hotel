package javaModel;

public class User {
	private int id;
	private String name;
	private String password;
	
	public User(){
		super();
	}
	public User(int id,String name,String password){
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public int getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getPassword(){
		return password;
	}
	
	public void setId(int id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setPassword(String password){
		this.password = password;
	}
}
