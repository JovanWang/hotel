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
		setTitle("�û����");


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
		names.add("����");
		names.add("����");
		names.add("����");
		names.add("�۸�");
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
		//������
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		contentPane.setLayout(null);
		JScrollPane jtablesp= new JScrollPane(table_1);
		jtablesp.setBounds(10, 38, 384, 601);
		contentPane.add(jtablesp);
		JLabel label_1 = new JLabel("����Ĳ˵�");
		label_1.setFont(new Font("������", Font.BOLD, 14));
		label_1.setBounds(150, 10, 116, 15);
		contentPane.add(label_1);
		
		JButton btnNewButton = new JButton("����һ��");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowN = 0;
				rowN= table_1.getSelectedRow();
				Object obj = "";
				try{
				 obj = table_1.getValueAt(rowN, 2);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "��ѡ����Ҫ����һ�ݵĲ�Ʒ����");
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
		
		JButton button = new JButton("����һ��");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowN = -1;
				rowN= table_1.getSelectedRow();
				Object obj = "";
				try{
				 obj = table_1.getValueAt(rowN, 2);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "��ѡ����Ҫ����һ�ݵĲ�Ʒ����");
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
		
		JButton btnNewButton_1 = new JButton("�ύ����");
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
					JOptionPane.showMessageDialog(null, "�����ύ�ɹ���������Ϊ��"+b_id+"");
					dispose();
					new finish(b_id,username).setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "�����ύʧ�ܣ��������ύ��");
				}
				
			}
		});
		btnNewButton_1.setBounds(198, 655, 93, 23);
		contentPane.add(btnNewButton_1);
		
		JButton button_1 = new JButton("ȡ������");
		button_1.setBounds(301, 655, 93, 23);
		contentPane.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Result result = bld.abolish(b_id);
				if(result.success){
					dispose();
					new index(username).setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "ȡ������ʧ�������������ȡ����");
				}
				
			}
			});
		ArrayList<Food_type> food_type = ftd.select();
		Vector modeltype = new Vector();
		for(int i=0;i<food_type.size();i++){
			modeltype.addElement(new Item(food_type.get(i).getid(),food_type.get(i).getname()));
		}
		
		
		JLabel label = new JLabel("��ѡ���ϵ��");
		label.setBounds(406, 45, 106, 15);
		contentPane.add(label);
		
		JTable panelList = new JTable();
		panelList.setLayout(null);
		
		//������
		JScrollPane jscp= new JScrollPane(panelList);
		jscp.setBounds(404, 87, 850, 580);
		jscp.getHorizontalScrollBar().setAutoscrolls(false);
		jscp.getVerticalScrollBar().setAutoscrolls(true);
		jscp.setViewportView(panelList);
		contentPane.add(jscp);
		JLabel lblNewLabel_3 = new JLabel("��ѡ���Ʒ��");
		lblNewLabel_3.setBounds(406, 65, 125, 15);
		contentPane.add(lblNewLabel_3);
		
		ArrayList<Food> food_info = fd.getType(food_type.get(0).getid());
		panelList.setPreferredSize(new Dimension(280, 150*food_info.size()));//����ÿ�����Ĵ�С
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
		
		JLabel lblNewLabel_1 = new JLabel("������"+food_info.get(i).getname()+"");
		lblNewLabel_1.setBounds(351, 40, 196, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("�۸�"+food_info.get(i).getprice()+"Ԫ");
		lblNewLabel_2.setBounds(351, 72, 98, 36);
		panel.add(lblNewLabel_2);
		
		JButton addbtng = new JButton("��һ��");
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
						JOptionPane.showMessageDialog(null, "��ѡ����Ҫ����һ�ݵĲ�Ʒ����");
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
					panelList.setSize(new Dimension(280, 150*food_info.size()));//�������setPreferredSize���ʹ��������������С
					
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
						
						JLabel lblNewLabel_1 = new JLabel("������"+food_info.get(i).getname()+"");
						lblNewLabel_1.setBounds(351, 40, 196, 15);
						panel.add(lblNewLabel_1);
						
						JLabel lblNewLabel_2 = new JLabel("�۸�"+food_info.get(i).getprice()+"Ԫ");
						lblNewLabel_2.setBounds(351, 72, 98, 36);
						panel.add(lblNewLabel_2);
						
						JButton addbtng = new JButton("��һ��");
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
										JOptionPane.showMessageDialog(null, "��ѡ����Ҫ����һ�ݵĲ�Ʒ����");
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

		//����ҳ�����JFrame
		setLocationRelativeTo(null);
		
	}
	 //item��
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
 // ʵ�ֲ��ɱ༭��ѡ�еĵı����
    public class MyTable extends JTable {						
    	// ��дJTable��Ĺ��췽��
    	public MyTable(DefaultTableModel tableModel) {//Vector rowData, Vector columnNames
    		super(tableModel);						// ���ø���Ĺ��췽��
    	}
    	// ��дJTable���getTableHeader()����
    	public JTableHeader getTableHeader() {					// ������ͷ
    		JTableHeader tableHeader = super.getTableHeader();	// ��ñ��ͷ����
    		tableHeader.setReorderingAllowed(false);			// ���ñ���в�������
    		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader
    				.getDefaultRenderer(); 						// ��ñ��ͷ�ĵ�Ԫ�����
    		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);		// ��������������ʾ
    		return tableHeader;
    	}
    	// ��дJTable���getDefaultRenderer(Class<?> columnClass)����
    	public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {	// ���嵥Ԫ��
    		DefaultTableCellRenderer cr = (DefaultTableCellRenderer) super
    				.getDefaultRenderer(columnClass); 						// ��ñ��ĵ�Ԫ�����
    		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER); 	// ���õ�Ԫ�����ݾ�����ʾ
    		return cr;
    	}
    	// ��дJTable���isCellEditable(int row, int column)����
    	public boolean isCellEditable(int row, int column) {				// ��񲻿ɱ༭
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
