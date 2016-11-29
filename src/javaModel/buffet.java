package javaModel;

public class Buffet {
 private int id;
 private int table_no;
 private int seating_num;
 private String status;
 private String create_time;
 private String update_time;
 private String delete_time;
 private String note;
 //////////////////////////
 public Buffet(){
	 super();
 }
 public Buffet(int table_no,String create_time){
	 super();
	 this.table_no=table_no;
	 this.create_time=create_time;
 }
 public int getid(){
	 return id;
 }
 public int gettable_no(){
	 return table_no;
 }
 public int getseating_num(){
	 return seating_num;
 }
 public String getstatus(){
	 return status;
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
 public String getnote(){
	 return note;
 }
 public void setid(int id){
	this.id=id;
 }
 public void settable_no(int table_no){
	 this.table_no=table_no;
 }
 public void setseating_num(int seating_num){
	 this.seating_num=seating_num;
 }
 public void setstatus(String status){
	 this.status=status;
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
 public void setnote(String note){
	 this.note=note;
 }
}
