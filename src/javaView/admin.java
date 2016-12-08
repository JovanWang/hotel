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
import javaDao.Food_typeDao;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class admin extends JFrame {

	private JPanel contentPane;
	private JTable table;
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
		ArrayList<Food_type> foodtype = ftd.select();
		Object[][] data = new Object[foodtype.size()][2];
		String[] columnNames = {"序号","菜品类型"};
		for (int i=0; i < foodtype.size(); i++) {
			data[i][0] = foodtype.get(i).getid();
			data[i][1] = foodtype.get(i).getname();
		}
		table = new JTable(data,columnNames);
		JScrollPane jtablesp= new JScrollPane(table);
		jtablesp.setBounds(29, 49, 209, 111);
		contentPane.add(jtablesp);
		

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
		foodtype_frame.setBounds(10, 10, 300, 330);
		foodtype_frame.setTitle("菜品管理");
		JPanel foodtype_panal = new JPanel();
		foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
		foodtype_frame.setContentPane(foodtype_panal);
		ArrayList<Food_type> foodtype = ftd.select();
		Object[][] data = new Object[foodtype.size()][2];
		String[] columnNames = {"序号","菜品类型"};
		for (int i=0; i < foodtype.size(); i++) {
			data[i][0] = foodtype.get(i).getid();
			data[i][1] = foodtype.get(i).getname();
		}
		table = new JTable(data,columnNames);
		//滚动条
		JScrollPane jtablesp= new JScrollPane(table);
		jtablesp.setBounds(10, 10, 250, 220);
		foodtype_panal.add(jtablesp);
		
		foodtype_panal.setLayout(null);
		foodtype_frame.setVisible(true);
		//设置登录页面居中JFrame
		foodtype_frame.setLocationRelativeTo(null);
	}
}
