package javaView;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import javaModel.Food_type;
import javaModel.Result;
import javaUtil.StrEmptyUtil;
import javaDao.Food_typeDao;
import javaDao.UserDao;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextPane;
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
		
		JButton foodtypebtn = new JButton("菜品管理");
		foodtypebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foodtypedialog();
			}
		});
		foodtypebtn.setBounds(29, 16, 93, 23);
		contentPane.add(foodtypebtn);
		
		
		//设置登录页面居中JFrame
		this.setLocationRelativeTo(null);
	}
	void foodtypedialog(){
		JFrame foodtype_frame = new JFrame();
		foodtype_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		foodtype_frame.setBounds(10, 10, 500, 330);
		foodtype_frame.setTitle("菜品管理");
		JPanel foodtype_panal = new JPanel();
		foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
		foodtype_frame.setContentPane(foodtype_panal);
		ArrayList<Food_type> foodtype = ftd.select();
		Object[][] data = new Object[foodtype.size()][5];
		String[] columnNames = {"序号","菜品类型","菜品说明","创建时间","更新时间"};
		for (int i=0; i < foodtype.size(); i++) {
			data[i][0] = foodtype.get(i).getid();
			data[i][1] = foodtype.get(i).getname();
			data[i][2] = foodtype.get(i).gettext();
			data[i][3] = foodtype.get(i).getcreate_time();
			data[i][4] = foodtype.get(i).getupdate_time();
		}

		JTable table = new JTable(data,columnNames);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setPreferredWidth(130);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//滚动条
		JScrollPane jtablesp= new JScrollPane(table);
		jtablesp.setBounds(10, 10, 460, 220);
		foodtype_panal.add(jtablesp);
		
		foodtype_panal.setLayout(null);
		JButton addfoodtypebtn = new JButton("添加菜品");
		addfoodtypebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addfoodtypeAux(foodtype_frame);
			}
		});
		addfoodtypebtn.setBounds(10, 240, 93, 23);
		foodtype_panal.add(addfoodtypebtn);
		foodtype_frame.setVisible(true);
		//设置登录页面居中JFrame
		foodtype_frame.setLocationRelativeTo(null);
	}
	void addfoodtypeAux(JFrame foodtype_frame){
		JFrame addfoodtype_frame = new JFrame();
		addfoodtype_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addfoodtype_frame.setBounds(10, 10, 300, 250);
		addfoodtype_frame.setTitle("添加菜品");
		JPanel foodtype_panal = new JPanel();
		foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
		addfoodtype_frame.setContentPane(foodtype_panal);
		
		JLabel label = new JLabel("菜品类型：");
		label.setBounds(10, 17, 70, 15);
		addfoodtype_frame.add(label);
		
		JLabel label_1 = new JLabel("菜品说明：");
		label_1.setBounds(10, 53, 70, 15);
		addfoodtype_frame.add(label_1);
		
		JEditorPane name = new JEditorPane();
		name.setBounds(76, 17, 150, 21);
		addfoodtype_frame.add(name);
		
		JTextArea text = new JTextArea();
		text.setBounds(76, 49, 150, 83);
		addfoodtype_frame.add(text);
		
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
					foodtype_frame.dispose();
					foodtypedialog();
					
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
}
