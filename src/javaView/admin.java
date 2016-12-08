package javaView;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import javaModel.Food;
import javaModel.Food_type;
import javaModel.Result;
import javaUtil.StrEmptyUtil;
import javaDao.FoodDao;
import javaDao.Food_typeDao;
import javaDao.UserDao;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;

public class admin extends JFrame {

	private JPanel contentPane;
	private Food_typeDao ftd = new Food_typeDao();
	private FoodDao fd = new FoodDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin frame = new admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public admin() {
		setTitle("酒店管理-后台服务系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1050, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		
		JButton foodtypebtn = new JButton("菜系管理");
		foodtypebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foodtypedialog();
			}
		});
		foodtypebtn.setBounds(29, 16, 93, 23);
		contentPane.add(foodtypebtn);
		JButton foodbtn = new JButton("菜品管理");
		foodbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fooddialog();
			}
		});
		foodbtn.setBounds(145, 16, 93, 23);
		contentPane.add(foodbtn);
		
		
		//设置登录页面居中JFrame
		this.setLocationRelativeTo(null);
	}
	//
	//辅助函数区
	//
	/**
     * 全局jScrollPane
     */
    public static JScrollPane jScrollPane;
    public static JScrollPane getjScrollPane(){
        return jScrollPane;
    }
    public static void setjScrollPane(JScrollPane jScrollPane) {
        admin.jScrollPane = jScrollPane;
    }

    /**
     * 全局JTable
     */
    public static JTable jTable;
    public static JTable getjTable() {
        return jTable;
    }
    public static void setjTable(JTable jTable) {
        admin.jTable = jTable;
    }
    //
	//菜系辅助函数
    //菜系列表类（刷新用）
	public class JTableOperation{
	    public JTable JtableDataInit(){
	    	ArrayList<Food_type> foodtype = ftd.select();
			Object[][] data = new Object[foodtype.size()][5];
			String[] columnNames = {"序号","菜系类型","菜系说明","创建时间","更新时间"};
			for (int i=0; i < foodtype.size(); i++) {
				data[i][0] = foodtype.get(i).getid();
				data[i][1] = foodtype.get(i).getname();
				data[i][2] = foodtype.get(i).gettext();
				data[i][3] = foodtype.get(i).getcreate_time();
				data[i][4] = foodtype.get(i).getupdate_time();
			}
	        JTable jt=new JTable(data,columnNames);
			jt = new JTable(data,columnNames);
			jt.getColumnModel().getColumn(0).setPreferredWidth(40);
			jt.getColumnModel().getColumn(1).setPreferredWidth(100);
			jt.getColumnModel().getColumn(2).setPreferredWidth(150);
			jt.getColumnModel().getColumn(3).setPreferredWidth(135);
			jt.getColumnModel().getColumn(4).setPreferredWidth(135);
			jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			//设置表格内文字内容剧中
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		    tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
		    jt.setDefaultRenderer(Object.class, tcr);
	        return jt;
	    }
	    public void reloadJTable(JTable jtable){
	    	ArrayList<Food_type> foodtype = ftd.select();
			Object[][] data = new Object[foodtype.size()][5];
			String[] columnNames = {"序号","菜系类型","菜系说明","创建时间","更新时间"};
			for (int i=0; i < foodtype.size(); i++) {
				data[i][0] = foodtype.get(i).getid();
				data[i][1] = foodtype.get(i).getname();
				data[i][2] = foodtype.get(i).gettext();
				data[i][3] = foodtype.get(i).getcreate_time();
				data[i][4] = foodtype.get(i).getupdate_time();
			}
			JTable jt=new JTable(data,columnNames);
			jt.getColumnModel().getColumn(0).setPreferredWidth(40);
			jt.getColumnModel().getColumn(1).setPreferredWidth(100);
			jt.getColumnModel().getColumn(2).setPreferredWidth(150);
			jt.getColumnModel().getColumn(3).setPreferredWidth(135);
			jt.getColumnModel().getColumn(4).setPreferredWidth(135);
			jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			//设置表格内文字内容剧中
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		    tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
		    jt.setDefaultRenderer(Object.class, tcr);
			admin.setjTable(jt);
	        admin.getjScrollPane().setViewportView(jt);
	    }
	}
	    
	//菜系列表弹窗
	void foodtypedialog(){
		JFrame foodtype_frame = new JFrame();
		foodtype_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		foodtype_frame.setBounds(10, 10, 540, 330);
		foodtype_frame.setTitle("菜系管理");
		JPanel foodtype_panal = new JPanel();
		foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
		foodtype_frame.setContentPane(foodtype_panal);
		JTableOperation jTableInit = new JTableOperation();
		JTable table = jTableInit.JtableDataInit();
		
		//滚动条
		JScrollPane jtablesp= new JScrollPane(table);
		jtablesp.setBounds(10, 10, 500, 220);
		foodtype_panal.add(jtablesp);
		//将scrollPane与jTable 添加到全局变量中
		admin.setjScrollPane(jtablesp);
		admin.setjTable(jTable);
		
		foodtype_panal.setLayout(null);
		JButton addfoodtypebtn = new JButton("添加菜系");
		addfoodtypebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addfoodtypeAux();
			}
		});
		addfoodtypebtn.setBounds(10, 240, 143, 23);
		foodtype_panal.add(addfoodtypebtn);
		JButton upfoodtypebtn = new JButton("编辑选中菜系");
		upfoodtypebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addfoodtypeAux();
			}
		});
		upfoodtypebtn.setBounds(190, 240, 143, 23);
		foodtype_panal.add(upfoodtypebtn);
		JButton defoodtypebtn = new JButton("删除选中菜系");
		defoodtypebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowN = table.getSelectedRow();
				Object obj = table.getValueAt(rowN, 0);
				int rown = Integer.parseInt(obj.toString());
				Result result = ftd.delete(rown);
				if(result.success){
					try {
		                //刷新列表
		                JTableOperation jTableOperation = new JTableOperation();
		                jTableOperation.reloadJTable(admin.getjTable());
		            } catch (Exception e1) {
		                e1.printStackTrace();
		            }
					JOptionPane.showMessageDialog(null,"删除成功！");
				}else{
					JOptionPane.showMessageDialog(null, result.message);
				}
			}
		});
		defoodtypebtn.setBounds(370, 240, 143, 23);
		foodtype_panal.add(defoodtypebtn);
		foodtype_frame.setVisible(true);
		//设置登录页面居中JFrame
		foodtype_frame.setLocationRelativeTo(null);
	}
	//增加菜系弹窗
	void addfoodtypeAux(){
		JFrame addfoodtype_frame = new JFrame();
		addfoodtype_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addfoodtype_frame.setBounds(10, 10, 300, 250);
		addfoodtype_frame.setTitle("添加菜系");
		JPanel foodtype_panal = new JPanel();
		foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
		addfoodtype_frame.setContentPane(foodtype_panal);
		
		JLabel label = new JLabel("菜系类型：");
		label.setBounds(10, 17, 70, 15);
		addfoodtype_frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("菜系说明：");
		label_1.setBounds(10, 53, 70, 15);
		addfoodtype_frame.getContentPane().add(label_1);
		
		JEditorPane name = new JEditorPane();
		name.setBounds(76, 17, 150, 21);
		addfoodtype_frame.getContentPane().add(name);
		
		JTextArea text = new JTextArea();
		text.setBounds(76, 49, 150, 83);
		addfoodtype_frame.getContentPane().add(text);
		
		foodtype_panal.setLayout(null);
		JButton addfoodtypebtn = new JButton("确认添加");
		addfoodtypebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameStr=StrEmptyUtil.strTrim(name.getText());
				String textStr=StrEmptyUtil.strTrim(new String(text.getText()));
				
				if(StrEmptyUtil.isEmpty(nameStr)){
					JOptionPane.showMessageDialog(null, "名称信息不能为空！");
					return;
				}
				if(StrEmptyUtil.isEmpty(nameStr)){
					JOptionPane.showMessageDialog(null, "说明信息不能为空！");
					return;
				}
				Food_type ft = new Food_type();
				ft.setname(nameStr);
				ft.settext(textStr);
				Result result = ftd.create(ft);
				if(result.success){
					addfoodtype_frame.dispose();
					try {
		                //刷新列表
		                JTableOperation jTableOperation = new JTableOperation();
		                jTableOperation.reloadJTable(admin.getjTable());
		            } catch (Exception e1) {
		                e1.printStackTrace();
		            }
					
				}else{
					JOptionPane.showMessageDialog(null, "添加信息失败请重新添加！");
				}
			}
		});
		addfoodtypebtn.setBounds(10, 180, 93, 23);
		foodtype_panal.add(addfoodtypebtn);
		addfoodtype_frame.setVisible(true);
		//设置登录页面居中JFrame
		addfoodtype_frame.setLocationRelativeTo(null);
	}
	
	//
	//菜品函数
	void fooddialog(){
		JFrame foodtype_frame = new JFrame();
		foodtype_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		foodtype_frame.setBounds(10, 10, 700, 330);
		foodtype_frame.setTitle("菜品管理");
		JPanel foodtype_panal = new JPanel();
		foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
		foodtype_frame.setContentPane(foodtype_panal);
		ArrayList<Food> food = fd.select();
		Object[][] data = new Object[food.size()][8];
		String[] columnNames = {"序号","菜品名","价格","所属菜系","单位","描述","助剂符","创建时间"};
		for (int i=0; i < food.size(); i++) {
			data[i][0] = food.get(i).getid();
			data[i][1] = food.get(i).getname();
			data[i][2] = food.get(i).getprice();
			ArrayList<Food_type> foodtp = ftd.getId(food.get(i).getfood_type_id());
			data[i][3] = foodtp.get(0).getname();
			data[i][4] = food.get(i).getunit();
			data[i][5] = food.get(i).getdescribe();
			data[i][6] = food.get(i).getmnemonic_no();
			data[i][7] = food.get(i).getcreate_time();
		}

		JTable table = new JTable(data,columnNames);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(80);
		table.getColumnModel().getColumn(7).setPreferredWidth(135);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//设置表格内文字内容剧中
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
	    tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
	    table.setDefaultRenderer(Object.class, tcr);
		//滚动条
		JScrollPane jtablesp= new JScrollPane(table);
		jtablesp.setBounds(10, 10, 660, 220);
		foodtype_panal.add(jtablesp);
		
		foodtype_panal.setLayout(null);
		JButton addfoodtypebtn = new JButton("添加菜系");
		addfoodtypebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addfoodtypeAux();
			}
		});
		addfoodtypebtn.setBounds(10, 240, 93, 23);
		foodtype_panal.add(addfoodtypebtn);
		foodtype_frame.setVisible(true);
		//设置登录页面居中JFrame
		foodtype_frame.setLocationRelativeTo(null);
	}
}
