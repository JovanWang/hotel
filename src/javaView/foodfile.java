package javaView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import javaModel.Food;
import javaModel.Food_type;
import javaModel.Result;
import javaUtil.StrEmptyUtil;
import javaView.admin.Item;
import javaDao.BillDao;
import javaDao.BuffetDao;
import javaDao.FoodDao;
import javaDao.Food_recordDao;
import javaDao.Food_typeDao;
import javaDao.UserDao;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;

import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.BoxLayout;

public class foodfile extends JFrame{

	private JPanel contentPane;
	//private JTable table_1;
	private Food_typeDao ftd = new Food_typeDao();
	private FoodDao fd = new FoodDao();
	private BillDao bld = new BillDao();
	private Food_recordDao frd = new Food_recordDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					foodfile Frame = new foodfile(-1,"jovan");
					Frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the Frame.
	 */
	public foodfile(int b_id,String username) {
		setTitle("用户点餐");


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 717, 426);
		setBounds(100, 100, 1300, 750);
		contentPane = new JPanelImg();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		DefaultTableModel model = new DefaultTableModel();
		MyTable table_1 = new MyTable(model);
		Vector data = new Vector();
		Vector names = new Vector();
		names.add("单价");
		names.add("菜名");
		names.add("数量");
		names.add("价格");
		names.add("food_id");
		table_1.setEnabled(true);
		table_1.setOpaque(false);
		model.setDataVector(data, names);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(0);
		table_1.getColumnModel().getColumn(0).setMinWidth(0);
		table_1.getColumnModel().getColumn(0).setMaxWidth(0);
		table_1.getColumnModel().getColumn(0).setWidth(0);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(150);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(0);
		table_1.getColumnModel().getColumn(4).setMinWidth(0);
		table_1.getColumnModel().getColumn(4).setMaxWidth(0);
		table_1.getColumnModel().getColumn(4).setWidth(0);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//滚动条
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		contentPane.setLayout(null);
		JScrollPane jtablesp= new JScrollPane(table_1);
		jtablesp.setBounds(10, 38, 384, 601);
		contentPane.add(jtablesp);
		JLabel label_1 = new JLabel("您点的菜单");
		label_1.setFont(new Font("新宋体", Font.BOLD, 14));
		label_1.setBounds(150, 10, 116, 15);
		contentPane.add(label_1);
		
		JButton btnNewButton = new JButton("再来一份");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowN = 0;
				rowN= table_1.getSelectedRow();
				Object obj = "";
				try{
				 obj = table_1.getValueAt(rowN, 2);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "请选择需要再来一份的菜品！！");
					return;
				}
				
				int row_n = Integer.parseInt(obj.toString());
				row_n++;
				Object price = table_1.getValueAt(rowN, 3);
				double row_pu = Double.parseDouble(table_1.getValueAt(rowN, 0).toString());
				double row_p = Double.parseDouble(price.toString());
				row_p = row_p + row_pu;
				table_1.setValueAt(row_n, rowN, 2);
				table_1.setValueAt(row_p, rowN, 3);
			}
		});
		btnNewButton.setBounds(10, 655, 86, 23);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("减少一份");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowN = -1;
				rowN= table_1.getSelectedRow();
				Object obj = "";
				try{
				 obj = table_1.getValueAt(rowN, 2);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "请选择需要再来一份的菜品！！");
					return;
				}
				
				int row_n = Integer.parseInt(obj.toString());
				row_n--;
				if(row_n <= 0){
					model.removeRow(rowN);
				}else{
					Object price = table_1.getValueAt(rowN, 3);
					double row_pu = Double.parseDouble(table_1.getValueAt(rowN, 0).toString());
					double row_p = Double.parseDouble(price.toString());
					row_p =row_p - row_pu;
					table_1.setValueAt(row_n, rowN, 2);
					table_1.setValueAt(row_p, rowN, 3);
				}
				
			}
		});
		button.setBounds(101, 655, 93, 23);
		contentPane.add(button);
		
		JButton btnNewButton_1 = new JButton("提交订单");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double price_all = 0;
				boolean comflag = false;
				for(int i = 0;i < table_1.getRowCount();i++){
					int food_id = Integer.parseInt(table_1.getValueAt(i,4).toString());
					int food_num = Integer.parseInt(table_1.getValueAt(i,2).toString());
					double price_num = Double.parseDouble(table_1.getValueAt(i,3).toString());
					price_all += price_num;
					Result result = frd.create(b_id, food_id, food_num);
					if(result.success){
						comflag = true;
					}else{
						comflag = false;
						break;
					}
				}
				Result result = bld.userCommit(b_id, price_all);
				if(result.success){
					comflag = true;
				}else{
					comflag = false;
				}
				if(comflag){
					JOptionPane.showMessageDialog(null, "订单提交成功！订单号为："+b_id+"");
					dispose();
					new finish(b_id,username).setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "订单提交失败，请重新提交！");
				}
				
			}
		});
		btnNewButton_1.setBounds(198, 655, 93, 23);
		contentPane.add(btnNewButton_1);
		
		JButton button_1 = new JButton("取消订单");
		button_1.setBounds(301, 655, 93, 23);
		contentPane.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Result result = bld.abolish(b_id);
				if(result.success){
					dispose();
					new index(username).setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "取消订单失败请检查网络后再取消！");
				}
				
			}
			});
		ArrayList<Food_type> food_type = ftd.select();
		Vector modeltype = new Vector();
		for(int i=0;i<food_type.size();i++){
			modeltype.addElement(new Item(food_type.get(i).getid(),food_type.get(i).getname()));
		}
		
		
		JLabel label = new JLabel("请选择菜系：");
		label.setBounds(406, 45, 106, 15);
		contentPane.add(label);
		
		JTable panelList = new JTable();
		panelList.setLayout(null);
		
		//滚动条
		JScrollPane jscp= new JScrollPane(panelList);
		jscp.setBounds(404, 87, 850, 580);
		jscp.getHorizontalScrollBar().setAutoscrolls(false);
		jscp.getVerticalScrollBar().setAutoscrolls(true);
		jscp.setViewportView(panelList);
		contentPane.add(jscp);
		JLabel lblNewLabel_3 = new JLabel("请选择菜品：");
		lblNewLabel_3.setBounds(406, 65, 125, 15);
		contentPane.add(lblNewLabel_3);
		
		ArrayList<Food> food_info = fd.getType(food_type.get(0).getid());
		panelList.setPreferredSize(new Dimension(280, 150*food_info.size()));//设置每个面板的大小
		//panelList.setSize(new Dimension(280, 150*food_info.size()));

		panelList.setOpaque(false);
		
		for(int i=0;i<food_info.size();i++){

		JPanel panel = new JPanel();
		panel.setBounds(1, 1+150*i, 845, 145);
		panelList.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		String s = food_info.get(i).getimg_src();
		lblNewLabel.setIcon(new ImageIcon(foodfile.class.getResource(s)));
		lblNewLabel.setBounds(10, 10, 304, 128);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("菜名："+food_info.get(i).getname()+"");
		lblNewLabel_1.setBounds(351, 40, 196, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("价格："+food_info.get(i).getprice()+"元");
		lblNewLabel_2.setBounds(351, 72, 98, 36);
		panel.add(lblNewLabel_2);
		
		JButton addbtng = new JButton("来一份");
		int food_id = food_info.get(i).getid();
		String name = food_info.get(i).getname();
		double price = food_info.get(i).getprice();
		addbtng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				int addflag = -1;
				
				for(int i = 0; i < table_1.getRowCount(); i++){
					int row_id = Integer.parseInt(table_1.getValueAt(i, 4).toString());
					if(food_id == row_id){
						addflag = i;
					}
				}
				if(addflag == -1){
					Vector row = new Vector();
					row.add(price);
					row.add(name);
					row.add(1);
					row.add(price);
					row.add(food_id);
					model.addRow(row);
				}else{
					Object obj = "";
					try{
					 obj = table_1.getValueAt(addflag, 2);
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "请选择需要再来一份的菜品！！");
						return;
					}
					
					int row_n = Integer.parseInt(obj.toString());
					row_n++;
					Object price = table_1.getValueAt(addflag, 3);
					double row_pu = Double.parseDouble(table_1.getValueAt(addflag, 0).toString());
					double row_p = Double.parseDouble(price.toString());
					row_p = row_p + row_pu;
					table_1.setValueAt(row_n, addflag, 2);
					table_1.setValueAt(row_p, addflag, 3);
				}
				
			}
		});
		addbtng.setBounds(679, 60, 83, 23);
		panel.add(addbtng);
		}
		
		JComboBox comboBox = new JComboBox(modeltype);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					Item item = (Item) comboBox.getSelectedItem();
					int listInt = item.getId();
					ArrayList<Food> food_info = fd.getType(listInt);

					panelList.removeAll();
					panelList.repaint();
					panelList.validate();
					panelList.setLayout(null);
					panelList.setPreferredSize(new Dimension(280, 150*food_info.size()));
					panelList.setSize(new Dimension(280, 150*food_info.size()));//与上面的setPreferredSize配合使用重新设置面板大小
					
					for(int i=0;i<food_info.size();i++){

						JPanel panel = new JPanel();
						panel.setBounds(1, 1+150*i, 845, 145);
						panelList.add(panel);
						panel.setLayout(null);
						
						JLabel lblNewLabel = new JLabel("");
						String s = food_info.get(i).getimg_src();
						lblNewLabel.setIcon(new ImageIcon(foodfile.class.getResource(s)));
						lblNewLabel.setBounds(10, 10, 304, 128);
						panel.add(lblNewLabel);
						
						JLabel lblNewLabel_1 = new JLabel("菜名："+food_info.get(i).getname()+"");
						lblNewLabel_1.setBounds(351, 40, 196, 15);
						panel.add(lblNewLabel_1);
						
						JLabel lblNewLabel_2 = new JLabel("价格："+food_info.get(i).getprice()+"元");
						lblNewLabel_2.setBounds(351, 72, 98, 36);
						panel.add(lblNewLabel_2);
						
						JButton addbtng = new JButton("来一份");
						int food_id = food_info.get(i).getid();
						String name = food_info.get(i).getname();
						double price = food_info.get(i).getprice();
						addbtng.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e2) {
								int addflag = -1;
								for(int i = 0; i < table_1.getRowCount(); i++){
									int row_id = Integer.parseInt(table_1.getValueAt(i, 4).toString());
									if(food_id == row_id){
										addflag = i;
									}
								}
								if(addflag == -1){
									Vector row = new Vector();
									row.add(price);
									row.add(name);
									row.add(1);
									row.add(price);
									row.add(food_id);
									model.addRow(row);
								}else{
									Object obj = "";
									try{
									 obj = table_1.getValueAt(addflag, 2);
									}catch(Exception e){
										JOptionPane.showMessageDialog(null, "请选择需要再来一份的菜品！！");
										return;
									}
									
									int row_n = Integer.parseInt(obj.toString());
									row_n++;
									Object price = table_1.getValueAt(addflag, 3);
									double row_pu = Double.parseDouble(table_1.getValueAt(addflag, 0).toString());
									double row_p = Double.parseDouble(price.toString());
									row_p = row_p + row_pu;
									table_1.setValueAt(row_n, addflag, 2);
									table_1.setValueAt(row_p, addflag, 3);
								}
								
							}
						});
						addbtng.setBounds(679, 60, 83, 23);
					panel.add(addbtng);
					}
				}
				
			}
		});
		comboBox.setBounds(502, 42, 93, 21);
		contentPane.add(comboBox);

		//设置页面居中JFrame
		setLocationRelativeTo(null);
		
	}
	 //item类
    class Item {
        private int id;
        private String description;

        public Item(int id, String description) {
          this.id = id;
          this.description = description;
        }

        public int getId() {
          return id;
        }

        public String getDescription() {
          return description;
        }

        public String toString() {
          return description;
        }
      }
 // 实现不可编辑可选中的的表格类
    public class MyTable extends JTable {						
    	// 重写JTable类的构造方法
    	public MyTable(DefaultTableModel tableModel) {//Vector rowData, Vector columnNames
    		super(tableModel);						// 调用父类的构造方法
    	}
    	// 重写JTable类的getTableHeader()方法
    	public JTableHeader getTableHeader() {					// 定义表格头
    		JTableHeader tableHeader = super.getTableHeader();	// 获得表格头对象
    		tableHeader.setReorderingAllowed(false);			// 设置表格列不可重排
    		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader
    				.getDefaultRenderer(); 						// 获得表格头的单元格对象
    		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);		// 设置列名居中显示
    		return tableHeader;
    	}
    	// 重写JTable类的getDefaultRenderer(Class<?> columnClass)方法
    	public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {	// 定义单元格
    		DefaultTableCellRenderer cr = (DefaultTableCellRenderer) super
    				.getDefaultRenderer(columnClass); 						// 获得表格的单元格对象
    		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER); 	// 设置单元格内容居中显示
    		return cr;
    	}
    	// 重写JTable类的isCellEditable(int row, int column)方法
    	public boolean isCellEditable(int row, int column) {				// 表格不可编辑
    		return false;
    	}
    }
    class JPanelImg extends JPanel{
		Image img;
		public JPanelImg(){
			img=new ImageIcon(index.class.getResource("/img/f.jpeg")).getImage();
		}
		public void paintComponent(Graphics g)
		{
			g.drawImage(img,0,0,1300,950,null );
		}
	}
   
 
	
}
