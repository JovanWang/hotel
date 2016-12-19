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

import javaModel.Bill;
import javaModel.Buffet;
import javaModel.Food;
import javaModel.Food_record;
import javaModel.Food_type;
import javaModel.Result;
import javaModel.User;
import javaUtil.StrEmptyUtil;
import javaDao.BillDao;
import javaDao.BuffetDao;
import javaDao.FoodDao;
import javaDao.Food_recordDao;
import javaDao.Food_typeDao;
import javaDao.UserDao;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JList;

public class admin extends JFrame {

	private JPanel contentPane;
	private Food_typeDao ftd = new Food_typeDao();
	private FoodDao fd = new FoodDao();
	private BuffetDao bfd = new BuffetDao();
	private BillDao bld = new BillDao();
	private UserDao userd = new UserDao();
	private Food_recordDao frd = new Food_recordDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin frame = new admin("root");
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
	public admin(String username) {
		setResizable(false);
		setTitle("�Ƶ����-��̨����ϵͳ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1050, 750);
		contentPane = new JPanelImg();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		
		JButton foodtypebtn = new JButton("��ϵ����");
		foodtypebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foodtypedialog();
			}
		});
		foodtypebtn.setBounds(329, 26, 93, 23);
		contentPane.add(foodtypebtn);
		JButton foodbtn = new JButton("��Ʒ����");
		foodbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fooddialog();
			}
		});
		foodbtn.setBounds(445, 26, 93, 23);
		contentPane.add(foodbtn);
		
		JButton buffetbtn = new JButton("��������");
		buffetbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buffetdialog();
			}
		});
		buffetbtn.setBounds(562, 26, 93, 23);
		contentPane.add(buffetbtn);
		

		JTableOperationadmin jTableInit = new JTableOperationadmin();
		JTable table = jTableInit.JtableDataInit();
		//������
		JScrollPane jtablesp= new JScrollPane(table);
		jtablesp.setBounds(32, 94, 950, 500);
		contentPane.add(jtablesp);
		//��scrollPane��jTable ��ӵ�ȫ�ֱ�����
		admin.setjScrollPane(jtablesp);
		admin.setjTable(table);
		
		JButton settlebtn = new JButton("����ѡ���˵�");
		settlebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settleAux(username);
			}
		});
		settlebtn.setBounds(859, 604, 123, 23);
		contentPane.add(settlebtn);
		
		JButton detailbtn = new JButton("�鿴ѡ�в˵�");
		detailbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				detailAux(username);
			}
		});
		detailbtn.setBounds(722, 604, 123, 23);
		contentPane.add(detailbtn);
		
		JButton rebutton = new JButton("ˢ���б�");
		rebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new admin(username).setVisible(true);
			}
		});
		rebutton.setBounds(583, 604, 123, 23);
		contentPane.add(rebutton);
		
		//���õ�¼ҳ�����JFrame
		this.setLocationRelativeTo(null);
	}
	//
	//�����ݺ�����
	//
	 //�˵��б��ࣨˢ���ã�
		public class JTableOperationadmin{
		    public JTable JtableDataInit(){
		    	ArrayList<Bill> bill = bld.select();
				Object[][] data = new Object[bill.size()][9];
				String[] columnNames = {"���","����","�Ͳ��û�","���ѽ��","������","״̬","����ʱ��","����ʱ��","����id"};
				for (int i=0; i < bill.size(); i++) {
					data[i][0] = bill.get(i).getid();
					int buffetid = bill.get(i).getbuffet_id();
					if(bfd.getId(buffetid) == null){
						data[i][1] = "��";
					}else{
						ArrayList<Buffet> buffet =bfd.getId(buffetid);
						data[i][1] = buffet.get(0).gettable_no();
					}
					
					int userid = bill.get(i).getuser_id();
					if(userd.getId(userid) == null){
						data[i][2] = "jovan";
					}else{
						ArrayList<User> user =userd.getId(userid);
						data[i][2] = user.get(0).getName();
					}
					
					
					data[i][3] = bill.get(i).getprice_all();
					data[i][4] = bill.get(i).getpay_price();
					if(bill.get(i).getstatus() == 3){
						data[i][5] = "�ѽ���";
					}else if(bill.get(i).getstatus() != 3){
						data[i][5] = "δ����";
					}
					
					data[i][6] = bill.get(i).getcreate_time();
					data[i][7] = bill.get(i).getsettle_time();
					data[i][8] = bill.get(i).getbuffet_id();
				}
		        JTable jt=new JTable(data,columnNames);
				jt = new JTable(data,columnNames);
				jt.getColumnModel().getColumn(0).setPreferredWidth(40);
				jt.getColumnModel().getColumn(1).setPreferredWidth(60);
				jt.getColumnModel().getColumn(2).setPreferredWidth(100);
				jt.getColumnModel().getColumn(3).setPreferredWidth(120);
				jt.getColumnModel().getColumn(4).setPreferredWidth(120);
				jt.getColumnModel().getColumn(5).setPreferredWidth(120);
				jt.getColumnModel().getColumn(6).setPreferredWidth(135);
				jt.getColumnModel().getColumn(7).setPreferredWidth(135);
				jt.getColumnModel().getColumn(8).setPreferredWidth(0);
				jt.getColumnModel().getColumn(8).setMinWidth(0);
				jt.getColumnModel().getColumn(8).setMaxWidth(0);
				jt.getColumnModel().getColumn(8).setWidth(0);
				jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//				jt.setEnabled(false);
				//���ñ�����������ݾ���
				DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
			    tcr.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
			    jt.setDefaultRenderer(Object.class, tcr);
		        return jt;
		    }
		    public void reloadJTable(JTable jtable){
		    	ArrayList<Bill> bill = bld.select();
				Object[][] data = new Object[bill.size()][9];
				String[] columnNames = {"���","����","�Ͳ��û�","���ѽ��","������","״̬","����ʱ��","����ʱ��","����id"};
				for (int i=0; i < bill.size(); i++) {
					data[i][0] = bill.get(i).getid();
					int buffetid = bill.get(i).getbuffet_id();
					if(bfd.getId(buffetid) == null){
						data[i][1] = "��";
					}else{
						ArrayList<Buffet> buffet =bfd.getId(buffetid);
						data[i][1] = buffet.get(0).gettable_no();
					}
					
					int userid = bill.get(i).getuser_id();
					if(userd.getId(userid) == null){
						data[i][2] = "jovan";
					}else{
						ArrayList<User> user =userd.getId(userid);
						data[i][2] = user.get(0).getName();
					}
					
					data[i][3] = bill.get(i).getprice_all();
					data[i][4] = bill.get(i).getpay_price();
					if(bill.get(i).getstatus() == 3){
						data[i][5] = "�ѽ���";
					}else if(bill.get(i).getstatus() != 3){
						data[i][5] = "δ����";
					}
					
					data[i][6] = bill.get(i).getcreate_time();
					data[i][7] = bill.get(i).getsettle_time();
					data[i][8] = bill.get(i).getbuffet_id();
				}
		        JTable jt=new JTable(data,columnNames);
				jt = new JTable(data,columnNames);
				jt.getColumnModel().getColumn(0).setPreferredWidth(40);
				jt.getColumnModel().getColumn(1).setPreferredWidth(60);
				jt.getColumnModel().getColumn(2).setPreferredWidth(100);
				jt.getColumnModel().getColumn(3).setPreferredWidth(120);
				jt.getColumnModel().getColumn(4).setPreferredWidth(120);
				jt.getColumnModel().getColumn(5).setPreferredWidth(120);
				jt.getColumnModel().getColumn(6).setPreferredWidth(135);
				jt.getColumnModel().getColumn(7).setPreferredWidth(135);
				jt.getColumnModel().getColumn(8).setPreferredWidth(0);
				jt.getColumnModel().getColumn(8).setMinWidth(0);
				jt.getColumnModel().getColumn(8).setMaxWidth(0);
				jt.getColumnModel().getColumn(8).setWidth(0);
				jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				//���ñ�����������ݾ���
				DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
			    tcr.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
			    jt.setDefaultRenderer(Object.class, tcr);
				admin.setjTable(jt);
		        admin.getjScrollPane().setViewportView(jt);
		    }
		}
		//�鿴������鵯��
		//�˼�¼�б��ࣨˢ���ã�
		public class JTableOperationfr{
		    public JTable JtableDataInit(int rowId){
		    	ArrayList<Food_record> foodrecord = frd.list(rowId);
				Object[][] data = new Object[foodrecord.size()][4];
				String[] columnNames = {"���","����","����","�Ƽ�"};
				for (int i=0; i < foodrecord.size(); i++) {
					data[i][0] = foodrecord.get(i).getid();
					data[i][1] = foodrecord.get(i).getfood_name();
					data[i][2] = foodrecord.get(i).getfood_num();
					data[i][3] = foodrecord.get(i).getrecord_price();
				}
		        JTable jt=new JTable(data,columnNames);
				jt = new JTable(data,columnNames);
				jt.getColumnModel().getColumn(0).setPreferredWidth(40);
				jt.getColumnModel().getColumn(1).setPreferredWidth(120);
				jt.getColumnModel().getColumn(2).setPreferredWidth(100);
				jt.getColumnModel().getColumn(3).setPreferredWidth(120);
				jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//				jt.setEnabled(false);
				//���ñ�����������ݾ���
				DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
			    tcr.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
			    jt.setDefaultRenderer(Object.class, tcr);
		        return jt;
		    }
		    public void reloadJTable(JTable jtable,int rowId){
		    	ArrayList<Food_record> foodrecord = frd.list(rowId);
				Object[][] data = new Object[foodrecord.size()][4];
				String[] columnNames = {"���","����","����","�ܼ�"};
				for (int i=0; i < foodrecord.size(); i++) {
					data[i][0] = foodrecord.get(i).getid();
					data[i][1] = foodrecord.get(i).getfood_name();
					data[i][2] = foodrecord.get(i).getfood_num();
					data[i][3] = foodrecord.get(i).getrecord_price();
				}
		        JTable jt=new JTable(data,columnNames);
				jt = new JTable(data,columnNames);
				jt.getColumnModel().getColumn(0).setPreferredWidth(40);
				jt.getColumnModel().getColumn(1).setPreferredWidth(120);
				jt.getColumnModel().getColumn(2).setPreferredWidth(100);
				jt.getColumnModel().getColumn(3).setPreferredWidth(120);
				jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				//���ñ�����������ݾ���
				DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
			    tcr.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
			    jt.setDefaultRenderer(Object.class, tcr);
				admin.setjTable(jt);
		        admin.getjScrollPane().setViewportView(jt);
		    }
		}

		//��ϵ�б���
				void detailAux(String username){
						int rowN = admin.getjTable().getSelectedRow();
						Object objId = admin.getjTable().getValueAt(rowN, 0);
						int rowId = Integer.parseInt(objId.toString());
						JFrame foodtype_frame = new JFrame();
						foodtype_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						foodtype_frame.setBounds(10, 10, 540, 330);
						foodtype_frame.setTitle("�˵�����");
						JPanel foodtype_panal = new JPanel();
						foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
						foodtype_frame.setContentPane(foodtype_panal);
						JTableOperationfr jTableInit = new JTableOperationfr();
						JTable table = jTableInit.JtableDataInit(rowId);
						
						//������
						JScrollPane jtablesp= new JScrollPane(table);
						jtablesp.setBounds(10, 10, 500, 220);
						foodtype_panal.add(jtablesp);
						//��scrollPane��jTable ��ӵ�ȫ�ֱ�����
						admin.setjScrollPane(jtablesp);
						admin.setjTable(table);
						
						foodtype_panal.setLayout(null);
						JButton addfoodtypebtn = new JButton("�������");
						addfoodtypebtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								addfoodrecordAux(rowId);
							}
						});
						addfoodtypebtn.setBounds(200, 240, 143, 23);
						foodtype_panal.add(addfoodtypebtn);
						JButton defoodtypebtn = new JButton("ɾ��ѡ�в�Ʒ");
						defoodtypebtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								int rowN = admin.getjTable().getSelectedRow();
								Object obj = admin.getjTable().getValueAt(rowN, 0);
								int rown = Integer.parseInt(obj.toString());
								Result result = frd.delete(rown);
								if(result.success){
									try {
						                //ˢ���б�
										JTableOperationfr table = new JTableOperationfr();
						                table.reloadJTable(admin.getjTable(),rowId);
						            } catch (Exception e1) {
						                e1.printStackTrace();
						            }
									JOptionPane.showMessageDialog(null,"ɾ���ɹ���");
								}else{
									JOptionPane.showMessageDialog(null, result.message);
								}
							}
						});
						defoodtypebtn.setBounds(370, 240, 143, 23);
						foodtype_panal.add(defoodtypebtn);
						foodtype_frame.setVisible(true);
						//���õ�¼ҳ�����JFrame
						foodtype_frame.setLocationRelativeTo(null);
					
					
				}
				//�������Ӳ�Ʒ����
				void addfoodrecordAux(int rowId){
					JFrame addfood_frame = new JFrame();
					addfood_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					addfood_frame.setBounds(10, 10, 300, 320);
					addfood_frame.setTitle("������Ӳ�Ʒ");
					JPanel foodtype_panal = new JPanel();
					foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
					addfood_frame.setContentPane(foodtype_panal);
					

					JLabel lblNewLabel_1 = new JLabel("��Ʒ���ƣ�");
					lblNewLabel_1.setBounds(10, 15, 70, 15);
					foodtype_panal.add(lblNewLabel_1);
					
					ArrayList<Food> food_r_list = fd.select();
					Vector model = new Vector();
					for (int i=0; i < food_r_list.size(); i++) {
						model.addElement(new Item(food_r_list.get(i).getid(), food_r_list.get(i).getname()));
					}
					JComboBox combox = new JComboBox(model);
					combox.setBounds(93, 15, 150, 21);
					foodtype_panal.add(combox);
					
					JLabel lblNewLabel = new JLabel("������");
					lblNewLabel.setBounds(10, 60, 70, 15);
					foodtype_panal.add(lblNewLabel);
					
					JEditorPane nameText = new JEditorPane();
					nameText.setBounds(93, 60, 150, 21);
					foodtype_panal.add(nameText);
					
					
					foodtype_panal.setLayout(null);
					JButton addfoodtypebtn = new JButton("ȷ�����");
					addfoodtypebtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String nameStr=StrEmptyUtil.strTrim(nameText.getText());
							int nameInt = 0;
							Item item = (Item) combox.getSelectedItem();
							int listInt = item.getId();
							
							if(StrEmptyUtil.isEmpty(nameStr)){
								JOptionPane.showMessageDialog(null, "������Ϣ����Ϊ�գ�");
								return;
							}else{
								try {
									nameInt = Integer.parseInt(nameStr);
									} catch (NumberFormatException e1) {
									//����
										JOptionPane.showMessageDialog(null, "������Ϣֻ��Ϊ�����֣�");
										return;
									}
							}
							
							Result result = frd.create(rowId,listInt,nameInt);
							if(result.success){
								addfood_frame.dispose();
								try {
					                //ˢ���б�
									JTableOperationfr table = new JTableOperationfr();
					                table.reloadJTable(admin.getjTable(),rowId);
					            } catch (Exception e1) {
					                e1.printStackTrace();
					            }
								
							}else{
								JOptionPane.showMessageDialog(null, "�����Ϣʧ����������ӣ�");
							}
						}
					});
					addfoodtypebtn.setBounds(100, 250, 93, 23);
					foodtype_panal.add(addfoodtypebtn);
					addfood_frame.setVisible(true);
					//���õ�¼ҳ�����JFrame
					addfood_frame.setLocationRelativeTo(null);
				}
	
		//���㵯��
		void settleAux(String username){
			int rowN = admin.getjTable().getSelectedRow();
			Object objId = admin.getjTable().getValueAt(rowN, 0);
			int rowId = Integer.parseInt(objId.toString());
			Object objpa = admin.getjTable().getValueAt(rowN, 3);
			String rowpa = String.valueOf(objpa);
			Object objpp = admin.getjTable().getValueAt(rowN, 4);
			String rowpp = String.valueOf(objpp);
			int rowbfId = Integer.parseInt(admin.getjTable().getValueAt(rowN, 8).toString());
			
			JFrame addfoodtype_frame = new JFrame();
			addfoodtype_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			addfoodtype_frame.setBounds(10, 10, 300, 250);
			addfoodtype_frame.setTitle("�����˵�");
			JPanel foodtype_panal = new JPanel();
			foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
			addfoodtype_frame.setContentPane(foodtype_panal);
			
			JLabel lblNewLabel = new JLabel("���ѽ�");
			lblNewLabel.setBounds(10, 15, 70, 15);
			foodtype_panal.add(lblNewLabel);
			
			JEditorPane noText = new JEditorPane();
			noText.setText(rowpa);
			noText.setEnabled(false);
			noText.setBounds(93, 15, 150, 21);
			foodtype_panal.add(noText);
			
			JLabel lblNewLabel_1 = new JLabel("�����");
			lblNewLabel_1.setBounds(10, 60, 70, 15);
			foodtype_panal.add(lblNewLabel_1);
			
			JEditorPane numText = new JEditorPane();
			numText.setText(rowpp);
			numText.setBounds(93, 60, 150, 21);
			foodtype_panal.add(numText);
			
			foodtype_panal.setLayout(null);
			JButton addfoodtypebtn = new JButton("ȷ�Ͻ���");
			addfoodtypebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String ppStr=StrEmptyUtil.strTrim(numText.getText());
					double ppDou = 0;
					
					
					if(StrEmptyUtil.isEmpty(ppStr)){
						JOptionPane.showMessageDialog(null, "�������Ϊ�գ�");
						return;
					}else{
						try{
							ppDou = Double.parseDouble(ppStr);
						} catch (NumberFormatException e1) {
							//����
						JOptionPane.showMessageDialog(null, "������ֻ��Ϊ�����֣�");
						return;
						}
					}
					Result result = bld.adminSettle(rowId,ppDou,username);

					Result result2 = bfd.upstatus(rowbfId,1);
					if(result.success&&result2.success){
						addfoodtype_frame.dispose();
						try {
			                //ˢ���б�
							JTableOperationadmin table = new JTableOperationadmin();
			                table.reloadJTable(admin.getjTable());
			            } catch (Exception e1) {
			                e1.printStackTrace();
			            }
						
					}else{
						JOptionPane.showMessageDialog(null, "������Ϣʧ�������½��㣡");
					}
				}
			});
			addfoodtypebtn.setBounds(10, 180, 93, 23);
			foodtype_panal.add(addfoodtypebtn);
			addfoodtype_frame.setVisible(true);
			//���õ�¼ҳ�����JFrame
			addfoodtype_frame.setLocationRelativeTo(null);
		}
	//
	//����������
	//
	/**
     * ȫ��jScrollPane
     */
    public static JScrollPane jScrollPane;
    public static JScrollPane getjScrollPane(){
        return jScrollPane;
    }
    public static void setjScrollPane(JScrollPane jScrollPane) {
        admin.jScrollPane = jScrollPane;
    }

    /**
     * ȫ��JTable
     */
    public static JTable jTable;
    public static JTable getjTable() {
        return jTable;
    }
    public static void setjTable(JTable jTable) {
        admin.jTable = jTable;
    }
    //
	//��ϵ��������
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
    
    //��ϵ�б��ࣨˢ���ã�
	public class JTableOperationft{
	    public JTable JtableDataInit(){
	    	ArrayList<Food_type> foodtype = ftd.select();
			Object[][] data = new Object[foodtype.size()][5];
			String[] columnNames = {"���","��ϵ����","��ϵ˵��","����ʱ��","����ʱ��"};
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
//			jt.setEnabled(false);
			//���ñ�����������ݾ���
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
		    tcr.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
		    jt.setDefaultRenderer(Object.class, tcr);
	        return jt;
	    }
	    public void reloadJTable(JTable jtable){
	    	ArrayList<Food_type> foodtype = ftd.select();
			Object[][] data = new Object[foodtype.size()][5];
			String[] columnNames = {"���","��ϵ����","��ϵ˵��","����ʱ��","����ʱ��"};
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
			//���ñ�����������ݾ���
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
		    tcr.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
		    jt.setDefaultRenderer(Object.class, tcr);
			admin.setjTable(jt);
	        admin.getjScrollPane().setViewportView(jt);
	    }
	}
	    
	//��ϵ�б���
	void foodtypedialog(){
		JFrame foodtype_frame = new JFrame();
		foodtype_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		foodtype_frame.setBounds(10, 10, 540, 330);
		foodtype_frame.setTitle("��ϵ����");
		JPanel foodtype_panal = new JPanel();
		foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
		foodtype_frame.setContentPane(foodtype_panal);
		JTableOperationft jTableInit = new JTableOperationft();
		JTable table = jTableInit.JtableDataInit();
		
		//������
		JScrollPane jtablesp= new JScrollPane(table);
		jtablesp.setBounds(10, 10, 500, 220);
		foodtype_panal.add(jtablesp);
		//��scrollPane��jTable ��ӵ�ȫ�ֱ�����
		admin.setjScrollPane(jtablesp);
		admin.setjTable(table);
		
		foodtype_panal.setLayout(null);
		JButton addfoodtypebtn = new JButton("��Ӳ�ϵ");
		addfoodtypebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addfoodtypeAux();
			}
		});
		addfoodtypebtn.setBounds(10, 240, 143, 23);
		foodtype_panal.add(addfoodtypebtn);
		JButton upfoodtypebtn = new JButton("�༭ѡ�в�ϵ");
		upfoodtypebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upfoodtypeAux();
			}
		});
		upfoodtypebtn.setBounds(190, 240, 143, 23);
		foodtype_panal.add(upfoodtypebtn);
		JButton defoodtypebtn = new JButton("ɾ��ѡ�в�ϵ");
		defoodtypebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowN = admin.getjTable().getSelectedRow();
				Object obj = admin.getjTable().getValueAt(rowN, 0);
				int rown = Integer.parseInt(obj.toString());
				Result result = ftd.delete(rown);
				if(result.success){
					try {
		                //ˢ���б�
		                JTableOperationft table = new JTableOperationft();
		                table.reloadJTable(admin.getjTable());
		            } catch (Exception e1) {
		                e1.printStackTrace();
		            }
					JOptionPane.showMessageDialog(null,"ɾ���ɹ���");
				}else{
					JOptionPane.showMessageDialog(null, result.message);
				}
			}
		});
		defoodtypebtn.setBounds(370, 240, 143, 23);
		foodtype_panal.add(defoodtypebtn);
		foodtype_frame.setVisible(true);
		//���õ�¼ҳ�����JFrame
		foodtype_frame.setLocationRelativeTo(null);
	}
	//���Ӳ�ϵ����
	void addfoodtypeAux(){
		
		JFrame addfoodtype_frame = new JFrame();
		addfoodtype_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addfoodtype_frame.setBounds(10, 10, 300, 250);
		addfoodtype_frame.setTitle("��Ӳ�ϵ");
		JPanel foodtype_panal = new JPanel();
		foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
		addfoodtype_frame.setContentPane(foodtype_panal);
		
		JLabel label = new JLabel("��ϵ���ͣ�");
		label.setBounds(10, 17, 70, 15);
		addfoodtype_frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("��ϵ˵����");
		label_1.setBounds(10, 53, 70, 15);
		addfoodtype_frame.getContentPane().add(label_1);
		
		JEditorPane name = new JEditorPane();
		name.setBounds(76, 17, 150, 21);
		addfoodtype_frame.getContentPane().add(name);
		
		JTextArea text = new JTextArea();
		text.setBounds(76, 49, 150, 83);
		addfoodtype_frame.getContentPane().add(text);
		
		foodtype_panal.setLayout(null);
		JButton addfoodtypebtn = new JButton("ȷ�����");
		addfoodtypebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameStr=StrEmptyUtil.strTrim(name.getText());
				String textStr=StrEmptyUtil.strTrim(new String(text.getText()));
				
				if(StrEmptyUtil.isEmpty(nameStr)){
					JOptionPane.showMessageDialog(null, "������Ϣ����Ϊ�գ�");
					return;
				}
				if(StrEmptyUtil.isEmpty(nameStr)){
					JOptionPane.showMessageDialog(null, "˵����Ϣ����Ϊ�գ�");
					return;
				}
				Food_type ft = new Food_type();
				ft.setname(nameStr);
				ft.settext(textStr);
				Result result = ftd.create(ft);
				if(result.success){
					addfoodtype_frame.dispose();
					try {
		                //ˢ���б�
		                JTableOperationft table = new JTableOperationft();
		                table.reloadJTable(admin.getjTable());
		            } catch (Exception e1) {
		                e1.printStackTrace();
		            }
					
				}else{
					JOptionPane.showMessageDialog(null, "�����Ϣʧ����������ӣ�");
				}
			}
		});
		addfoodtypebtn.setBounds(100, 180, 93, 23);
		foodtype_panal.add(addfoodtypebtn);
		addfoodtype_frame.setVisible(true);
		//���õ�¼ҳ�����JFrame
		addfoodtype_frame.setLocationRelativeTo(null);
	}
	//�༭��ϵ����
		void upfoodtypeAux(){
			int rowN = admin.getjTable().getSelectedRow();
			Object obj = admin.getjTable().getValueAt(rowN, 0);
			int rowId = Integer.parseInt(obj.toString());
			Object objName = admin.getjTable().getValueAt(rowN, 1);
			String rowName = String.valueOf(objName);
			Object objText = admin.getjTable().getValueAt(rowN, 2);
			String rowText = String.valueOf(objText);
			
			JFrame addfoodtype_frame = new JFrame();
			addfoodtype_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			addfoodtype_frame.setBounds(10, 10, 300, 250);
			addfoodtype_frame.setTitle("�༭��ϵ");
			JPanel foodtype_panal = new JPanel();
			foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
			addfoodtype_frame.setContentPane(foodtype_panal);
			
			JLabel label = new JLabel("��ϵ���ͣ�");
			label.setBounds(10, 17, 70, 15);
			addfoodtype_frame.getContentPane().add(label);
			
			JLabel label_1 = new JLabel("��ϵ˵����");
			label_1.setBounds(10, 53, 70, 15);
			addfoodtype_frame.getContentPane().add(label_1);
			
			JEditorPane name = new JEditorPane();
			name.setText(rowName);
			name.setBounds(76, 17, 150, 21);
			addfoodtype_frame.getContentPane().add(name);
			
			JTextArea text = new JTextArea();
			text.setText(rowText);
			text.setBounds(76, 49, 150, 83);
			addfoodtype_frame.getContentPane().add(text);
			
			foodtype_panal.setLayout(null);
			JButton addfoodtypebtn = new JButton("����༭");
			addfoodtypebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String nameStr=StrEmptyUtil.strTrim(name.getText());
					String textStr=StrEmptyUtil.strTrim(new String(text.getText()));
					
					if(StrEmptyUtil.isEmpty(nameStr)){
						JOptionPane.showMessageDialog(null, "������Ϣ����Ϊ�գ�");
						return;
					}
					if(StrEmptyUtil.isEmpty(nameStr)){
						JOptionPane.showMessageDialog(null, "˵����Ϣ����Ϊ�գ�");
						return;
					}
					Food_type ft = new Food_type();
					ft.setid(rowId);
					ft.setname(nameStr);
					ft.settext(textStr);
					Result result = ftd.update(ft);
					if(result.success){
						addfoodtype_frame.dispose();
						try {
			                //ˢ���б�
			                JTableOperationft table = new JTableOperationft();
			                table.reloadJTable(admin.getjTable());
			            } catch (Exception e1) {
			                e1.printStackTrace();
			            }
						
					}else{
						JOptionPane.showMessageDialog(null, "������Ϣʧ�������±༭��");
					}
				}
			});
			addfoodtypebtn.setBounds(10, 180, 93, 23);
			foodtype_panal.add(addfoodtypebtn);
			addfoodtype_frame.setVisible(true);
			//���õ�¼ҳ�����JFrame
			addfoodtype_frame.setLocationRelativeTo(null);
		}
	
	//
	//��Ʒ����
		//��ϵ�б��ࣨˢ���ã�
		public class JTableOperationfood{
		    public JTable JtableDataInit(){
		    	ArrayList<Food> food = fd.select();
				Object[][] data = new Object[food.size()][8];
				String[] columnNames = {"���","��Ʒ��","�۸�","������ϵ","��λ","����","������","����ʱ��"};
				for (int i=0; i < food.size(); i++) {
					data[i][0] = food.get(i).getid();
					data[i][1] = food.get(i).getname();
					data[i][2] = food.get(i).getprice();
					ArrayList<Food_type> foodtp = ftd.getId(food.get(i).getfood_type_id());
					if(foodtp == null){
						break;
					}
					data[i][3] = foodtp.get(0).getname();
					data[i][4] = food.get(i).getunit();
					data[i][5] = food.get(i).getdescribe();
					data[i][6] = food.get(i).getmnemonic_no();
					data[i][7] = food.get(i).getcreate_time();
				}

				JTable jt = new JTable(data,columnNames);
				jt.getColumnModel().getColumn(0).setPreferredWidth(40);
				jt.getColumnModel().getColumn(1).setPreferredWidth(100);
				jt.getColumnModel().getColumn(2).setPreferredWidth(100);
				jt.getColumnModel().getColumn(3).setPreferredWidth(100);
				jt.getColumnModel().getColumn(4).setPreferredWidth(50);
				jt.getColumnModel().getColumn(5).setPreferredWidth(150);
				jt.getColumnModel().getColumn(6).setPreferredWidth(80);
				jt.getColumnModel().getColumn(7).setPreferredWidth(135);
				jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//				jt.setEnabled(false);
				//���ñ�����������ݾ���
				DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
			    tcr.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
			    jt.setDefaultRenderer(Object.class, tcr);
		        return jt;
		    }
		    public void reloadJTable(JTable jtable){
		    	ArrayList<Food> food = fd.select();
				Object[][] data = new Object[food.size()][8];
				String[] columnNames = {"���","��Ʒ��","�۸�","������ϵ","��λ","����","������","����ʱ��"};
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

				JTable jt = new JTable(data,columnNames);
				jt.getColumnModel().getColumn(0).setPreferredWidth(40);
				jt.getColumnModel().getColumn(1).setPreferredWidth(100);
				jt.getColumnModel().getColumn(2).setPreferredWidth(100);
				jt.getColumnModel().getColumn(3).setPreferredWidth(100);
				jt.getColumnModel().getColumn(4).setPreferredWidth(50);
				jt.getColumnModel().getColumn(5).setPreferredWidth(150);
				jt.getColumnModel().getColumn(6).setPreferredWidth(80);
				jt.getColumnModel().getColumn(7).setPreferredWidth(135);
				jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				//���ñ�����������ݾ���
				DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
			    tcr.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
			    jt.setDefaultRenderer(Object.class, tcr);
				admin.setjTable(jt);
		        admin.getjScrollPane().setViewportView(jt);
		    }
		}
		    
		//��Ʒ�б���
		void fooddialog(){
			JFrame foodtype_frame = new JFrame();
			foodtype_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			foodtype_frame.setBounds(10, 10, 700, 330);
			foodtype_frame.setTitle("��Ʒ����");
			JPanel foodtype_panal = new JPanel();
			foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
			foodtype_frame.setContentPane(foodtype_panal);
			JTableOperationfood jTableInit = new JTableOperationfood();
			JTable table = jTableInit.JtableDataInit();
			
			//������
			JScrollPane jtablesp= new JScrollPane(table);
			jtablesp.setBounds(10, 10, 660, 220);
			foodtype_panal.add(jtablesp);
			//��scrollPane��jTable ��ӵ�ȫ�ֱ�����
			admin.setjScrollPane(jtablesp);
			admin.setjTable(table);
			
			foodtype_panal.setLayout(null);
			JButton addfoodtypebtn = new JButton("��Ӳ�Ʒ");
			addfoodtypebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addfoodAux();
				}
			});
			addfoodtypebtn.setBounds(10, 240, 143, 23);
			foodtype_panal.add(addfoodtypebtn);
			JButton upfoodtypebtn = new JButton("�༭ѡ�в�Ʒ");
			upfoodtypebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					upfoodAux();
				}
			});
			upfoodtypebtn.setBounds(190, 240, 143, 23);
			foodtype_panal.add(upfoodtypebtn);
			JButton defoodtypebtn = new JButton("ɾ��ѡ�в�Ʒ");
			defoodtypebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int rowN = admin.getjTable().getSelectedRow();
					Object obj = admin.getjTable().getValueAt(rowN, 0);
					int rown = Integer.parseInt(obj.toString());
					Result result = fd.delete(rown);
					if(result.success){
						try {
			                //ˢ���б�
							JTableOperationfood table = new JTableOperationfood();
			                table.reloadJTable(admin.getjTable());
			            } catch (Exception e1) {
			                e1.printStackTrace();
			            }
						JOptionPane.showMessageDialog(null,"ɾ���ɹ���");
					}else{
						JOptionPane.showMessageDialog(null, result.message);
					}
				}
			});
			defoodtypebtn.setBounds(370, 240, 143, 23);
			foodtype_panal.add(defoodtypebtn);
			foodtype_frame.setVisible(true);
			//���õ�¼ҳ�����JFrame
			foodtype_frame.setLocationRelativeTo(null);
		}
		//���Ӳ�Ʒ����
		void addfoodAux(){
			
			JFrame addfood_frame = new JFrame();
			addfood_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			addfood_frame.setBounds(10, 10, 300, 320);
			addfood_frame.setTitle("��Ӳ�Ʒ");
			JPanel foodtype_panal = new JPanel();
			foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
			addfood_frame.setContentPane(foodtype_panal);
			
			JLabel lblNewLabel = new JLabel("��Ʒ���ƣ�");
			lblNewLabel.setBounds(10, 15, 70, 15);
			foodtype_panal.add(lblNewLabel);
			
			JEditorPane nameText = new JEditorPane();
			nameText.setBounds(93, 15, 150, 21);
			foodtype_panal.add(nameText);
			
			JLabel lblNewLabel_1 = new JLabel("��Ʒ���ͣ�");
			lblNewLabel_1.setBounds(10, 60, 70, 15);
			foodtype_panal.add(lblNewLabel_1);
			
			ArrayList<Food_type> foodtypelist = ftd.select();
			Vector model = new Vector();
			for (int i=0; i < foodtypelist.size(); i++) {
				model.addElement(new Item(foodtypelist.get(i).getid(), foodtypelist.get(i).getname()));
			}
			JComboBox combox = new JComboBox(model);
			combox.setBounds(93, 60, 150, 21);
			foodtype_panal.add(combox);
			
			JLabel lblNewLabel_2 = new JLabel("���Ƿ���");
			lblNewLabel_2.setBounds(10, 105, 70, 15);
			foodtype_panal.add(lblNewLabel_2);
			
			JEditorPane mnoText = new JEditorPane();
			mnoText.setBounds(93, 105, 150, 21);
			foodtype_panal.add(mnoText);
			
			JLabel lblNewLabel_3 = new JLabel("�۸�");
			lblNewLabel_3.setBounds(10, 150, 70, 15);
			foodtype_panal.add(lblNewLabel_3);
			
			JEditorPane priceText = new JEditorPane();
			priceText.setBounds(93, 150, 150, 21);
			foodtype_panal.add(priceText);
			
			JLabel lblNewLabel_4 = new JLabel("��λ��");
			lblNewLabel_4.setBounds(10, 195, 70, 15);
			foodtype_panal.add(lblNewLabel_4);
			
			JEditorPane unitText = new JEditorPane();
			unitText.setBounds(93, 195, 150, 21);
			foodtype_panal.add(unitText);
			
			foodtype_panal.setLayout(null);
			JButton addfoodtypebtn = new JButton("ȷ�����");
			addfoodtypebtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String nameStr=StrEmptyUtil.strTrim(nameText.getText());
					String mnoStr=StrEmptyUtil.strTrim(mnoText.getText());
					String priceStr=StrEmptyUtil.strTrim(priceText.getText());
					double priceDou = 0;
					String unitStr=StrEmptyUtil.strTrim(unitText.getText());
					Item item = (Item) combox.getSelectedItem();
					int listInt = item.getId();
					
					if(StrEmptyUtil.isEmpty(nameStr)){
						JOptionPane.showMessageDialog(null, "������Ϣ����Ϊ�գ�");
						return;
					}
					if(StrEmptyUtil.isEmpty(mnoStr)){
						JOptionPane.showMessageDialog(null, "���Ƿ���Ϣ����Ϊ�գ�");
						return;
					}
					if(StrEmptyUtil.isEmpty(priceStr)){
						JOptionPane.showMessageDialog(null, "�۸���Ϣ����Ϊ�գ�");
						return;
					}else{
						try {
							priceDou = Double.parseDouble(priceStr);
							} catch (NumberFormatException e1) {
							//����
								JOptionPane.showMessageDialog(null, "�۸���Ϣֻ��Ϊ�����֣�");
								return;
							}
					}
					if(StrEmptyUtil.isEmpty(unitStr)){
						JOptionPane.showMessageDialog(null, "��λ��Ϣ����Ϊ�գ�");
						return;
					}
					Food food = new Food();
					food.setname(nameStr);
					food.setfood_type_id(listInt);
					food.setmnemonic_no(mnoStr);
					food.setprice(priceDou);
					food.setunit(unitStr);
					
					Result result = fd.create(food);
					if(result.success){
						addfood_frame.dispose();
						try {
			                //ˢ���б�
							JTableOperationfood table = new JTableOperationfood();
			                table.reloadJTable(admin.getjTable());
			            } catch (Exception e1) {
			                e1.printStackTrace();
			            }
						
					}else{
						JOptionPane.showMessageDialog(null, "�����Ϣʧ����������ӣ�");
					}
				}
			});
			addfoodtypebtn.setBounds(100, 250, 93, 23);
			foodtype_panal.add(addfoodtypebtn);
			addfood_frame.setVisible(true);
			//���õ�¼ҳ�����JFrame
			addfood_frame.setLocationRelativeTo(null);
		}
		
		//�༭��Ʒ����
			void upfoodAux(){
				int rowN = admin.getjTable().getSelectedRow();
				Object obj = admin.getjTable().getValueAt(rowN, 0);
				int rowId = Integer.parseInt(obj.toString());
				ArrayList<Food> foodtypelist = fd.getId(rowId);
				String rowName = foodtypelist.get(0).getname();
				String rowDes = foodtypelist.get(0).getdescribe();
				int rowRank = foodtypelist.get(0).getrank();
				double rowPrice = foodtypelist.get(0).getprice();
				int rowFtid = foodtypelist.get(0).getfood_type_id();
				
				JFrame addfood_frame = new JFrame();
				addfood_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				addfood_frame.setBounds(10, 10, 300, 320);
				addfood_frame.setTitle("���²�Ʒ");
				JPanel foodtype_panal = new JPanel();
				foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
				addfood_frame.setContentPane(foodtype_panal);
				
				JLabel lblNewLabel = new JLabel("��Ʒ���ƣ�");
				lblNewLabel.setBounds(10, 15, 70, 15);
				foodtype_panal.add(lblNewLabel);
				
				JEditorPane nameText = new JEditorPane();
				nameText.setText(rowName);
				nameText.setBounds(93, 15, 150, 21);
				foodtype_panal.add(nameText);
				
				JLabel lblNewLabel_1 = new JLabel("��Ʒ���ͣ�");
				lblNewLabel_1.setBounds(10, 60, 70, 15);
				foodtype_panal.add(lblNewLabel_1);
				
				ArrayList<Food_type> foodtypelistup = ftd.select();
				Vector model = new Vector();
				int selectN = 1;
				for (int i=0; i < foodtypelistup.size(); i++) {
					model.addElement(new Item(foodtypelistup.get(i).getid(), foodtypelistup.get(i).getname()));
					if(foodtypelistup.get(i).getid() == rowFtid){
						selectN = i;
					}
				}
				JComboBox combox = new JComboBox(model);
				combox.setSelectedIndex(selectN);;
				combox.setBounds(93, 60, 150, 21);
				foodtype_panal.add(combox);
				
				JLabel lblNewLabel_2 = new JLabel("��Ʒ������");
				lblNewLabel_2.setBounds(10, 105, 70, 15);
				foodtype_panal.add(lblNewLabel_2);
				
				JEditorPane mnoText = new JEditorPane();
				mnoText.setText(rowDes);
				mnoText.setBounds(93, 105, 150, 21);
				foodtype_panal.add(mnoText);
				
				JLabel lblNewLabel_3 = new JLabel("�۸�");
				lblNewLabel_3.setBounds(10, 150, 70, 15);
				foodtype_panal.add(lblNewLabel_3);
				
				JEditorPane priceText = new JEditorPane();
				priceText.setText(String.valueOf(rowPrice));
				priceText.setBounds(93, 150, 150, 21);
				foodtype_panal.add(priceText);
				
				
				JLabel lblNewLabel_4 = new JLabel("����");
				lblNewLabel_4.setBounds(10, 195, 70, 15);
				foodtype_panal.add(lblNewLabel_4);
				
				JEditorPane unitText = new JEditorPane();
				unitText.setText(String.valueOf(rowRank));
				unitText.setBounds(93, 195, 150, 21);
				foodtype_panal.add(unitText);
				
				foodtype_panal.setLayout(null);
				JButton addfoodtypebtn = new JButton("ȷ�ϸ���");
				addfoodtypebtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String nameStr=StrEmptyUtil.strTrim(nameText.getText());
						String mnoStr=StrEmptyUtil.strTrim(mnoText.getText());
						String priceStr=StrEmptyUtil.strTrim(priceText.getText());
						String unitStr=StrEmptyUtil.strTrim(unitText.getText());
						Item item = (Item) combox.getSelectedItem();
						int listInt = item.getId();
						double priceDou = 0;
						int rankInt = 0; 
						
						if(StrEmptyUtil.isEmpty(nameStr)){
							JOptionPane.showMessageDialog(null, "������Ϣ����Ϊ�գ�");
							return;
						}
						if(StrEmptyUtil.isEmpty(mnoStr)){
							JOptionPane.showMessageDialog(null, "��Ʒ������Ϣ����Ϊ�գ�");
							return;
						}
						if(StrEmptyUtil.isEmpty(priceStr)){
							JOptionPane.showMessageDialog(null, "�۸���Ϣ����Ϊ�գ�");
							return;
						}else{
							try {
								priceDou = Double.parseDouble(priceStr);
								} catch (NumberFormatException e1) {
								//����
									JOptionPane.showMessageDialog(null, "�۸���Ϣֻ��Ϊ�����֣�");
									return;
								}
						}
						if(StrEmptyUtil.isEmpty(unitStr)){
							JOptionPane.showMessageDialog(null, "������Ϣ����Ϊ�գ�");
							return;
						}else{
							try {
								rankInt = Integer.parseInt(unitStr);
								} catch (NumberFormatException e1) {
								//����
									JOptionPane.showMessageDialog(null, "������Ϣֻ��Ϊ�����֣�");
									return;
								}
						}
						
						Food food = new Food();
						food.setid(rowId);
						food.setname(nameStr);
						food.setfood_type_id(listInt);
						food.setdescribe(mnoStr);
						food.setprice(priceDou);
						food.setrank(rankInt);
						
						Result result = fd.update(food);
						if(result.success){
							addfood_frame.dispose();
							try {
				                //ˢ���б�
								JTableOperationfood table = new JTableOperationfood();
				                table.reloadJTable(admin.getjTable());
				            } catch (Exception e1) {
				                e1.printStackTrace();
				            }
							
						}else{
							JOptionPane.showMessageDialog(null, "�����Ϣʧ����������ӣ�");
						}
					}
				});
				addfoodtypebtn.setBounds(100, 250, 93, 23);
				foodtype_panal.add(addfoodtypebtn);
				addfood_frame.setVisible(true);
				//���õ�¼ҳ�����JFrame
				addfood_frame.setLocationRelativeTo(null);
			}
			//��������
			//�����б��ࣨˢ���ã�
			public class JTableOperationbf{
			    public JTable JtableDataInit(){
			    	ArrayList<Buffet> buffet = bfd.select();
					Object[][] data = new Object[buffet.size()][5];
					String[] columnNames = {"���","������","��λ��","״̬","����ʱ��"};
					for (int i=0; i < buffet.size(); i++) {
						data[i][0] = buffet.get(i).getid();
						data[i][1] = buffet.get(i).gettable_no();
						data[i][2] = buffet.get(i).getseating_num();
						if(buffet.get(i).getstatus() == 1){
							data[i][3] = "����";
						}else if(buffet.get(i).getstatus() == 2){
							data[i][3] = "ʹ����";
						}else{
							data[i][3] = "�쳣";
						}
						
						data[i][4] = buffet.get(i).getcreate_time();
					}
			        JTable jt=new JTable(data,columnNames);
					jt = new JTable(data,columnNames);
					jt.getColumnModel().getColumn(0).setPreferredWidth(40);
					jt.getColumnModel().getColumn(1).setPreferredWidth(100);
					jt.getColumnModel().getColumn(2).setPreferredWidth(150);
					jt.getColumnModel().getColumn(3).setPreferredWidth(135);
					jt.getColumnModel().getColumn(4).setPreferredWidth(135);
					jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//					jt.setEnabled(false);
					//���ñ�����������ݾ���
					DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
				    tcr.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
				    jt.setDefaultRenderer(Object.class, tcr);
			        return jt;
			    }
			    public void reloadJTable(JTable jtable){
			    	ArrayList<Buffet> buffet = bfd.select();
					Object[][] data = new Object[buffet.size()][5];
					String[] columnNames = {"���","������","��λ��","״̬","����ʱ��"};
					for (int i=0; i < buffet.size(); i++) {
						data[i][0] = buffet.get(i).getid();
						data[i][1] = buffet.get(i).gettable_no();
						data[i][2] = buffet.get(i).getseating_num();
						if(buffet.get(i).getstatus() == 1){
							data[i][3] = "����";
						}else if(buffet.get(i).getstatus() == 2){
							data[i][3] = "ʹ����";
						}else{
							data[i][3] = "�쳣";
						}
						
						data[i][4] = buffet.get(i).getcreate_time();
					}
			        JTable jt=new JTable(data,columnNames);
					jt = new JTable(data,columnNames);
					jt.getColumnModel().getColumn(0).setPreferredWidth(40);
					jt.getColumnModel().getColumn(1).setPreferredWidth(100);
					jt.getColumnModel().getColumn(2).setPreferredWidth(150);
					jt.getColumnModel().getColumn(3).setPreferredWidth(135);
					jt.getColumnModel().getColumn(4).setPreferredWidth(135);
					jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					//���ñ�����������ݾ���
					DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
				    tcr.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
				    jt.setDefaultRenderer(Object.class, tcr);
					admin.setjTable(jt);
			        admin.getjScrollPane().setViewportView(jt);
			    }
			}
			    
			//��ϵ�б���
			void buffetdialog(){
				JFrame foodtype_frame = new JFrame();
				foodtype_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				foodtype_frame.setBounds(10, 10, 540, 330);
				foodtype_frame.setTitle("��������");
				JPanel foodtype_panal = new JPanel();
				foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
				foodtype_frame.setContentPane(foodtype_panal);
				JTableOperationbf jTableInit = new JTableOperationbf();
				JTable table = jTableInit.JtableDataInit();
				
				//������
				JScrollPane jtablesp= new JScrollPane(table);
				jtablesp.setBounds(10, 10, 500, 220);
				foodtype_panal.add(jtablesp);
				//��scrollPane��jTable ��ӵ�ȫ�ֱ�����
				admin.setjScrollPane(jtablesp);
				admin.setjTable(table);
				
				foodtype_panal.setLayout(null);
				JButton addfoodtypebtn = new JButton("��Ӳ���");
				addfoodtypebtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addbuffetAux();
					}
				});
				addfoodtypebtn.setBounds(10, 240, 143, 23);
				foodtype_panal.add(addfoodtypebtn);
				JButton upfoodtypebtn = new JButton("�༭ѡ�в���");
				upfoodtypebtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						upbuffetAux();
					}
				});
				upfoodtypebtn.setBounds(190, 240, 143, 23);
				foodtype_panal.add(upfoodtypebtn);
				JButton defoodtypebtn = new JButton("ɾ��ѡ�в���");
				defoodtypebtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int rowN = admin.getjTable().getSelectedRow();
						Object obj = admin.getjTable().getValueAt(rowN, 0);
						int rown = Integer.parseInt(obj.toString());
						Result result = bfd.delete(rown);
						if(result.success){
							try {
				                //ˢ���б�
								JTableOperationbf table = new JTableOperationbf();
				                table.reloadJTable(admin.getjTable());
				            } catch (Exception e1) {
				                e1.printStackTrace();
				            }
							JOptionPane.showMessageDialog(null,"ɾ���ɹ���");
						}else{
							JOptionPane.showMessageDialog(null, result.message);
						}
					}
				});
				defoodtypebtn.setBounds(370, 240, 143, 23);
				foodtype_panal.add(defoodtypebtn);
				foodtype_frame.setVisible(true);
				//���õ�¼ҳ�����JFrame
				foodtype_frame.setLocationRelativeTo(null);
			}
			//���Ӳ�������
			void addbuffetAux(){
				
				JFrame addfoodtype_frame = new JFrame();
				addfoodtype_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				addfoodtype_frame.setBounds(10, 10, 300, 250);
				addfoodtype_frame.setTitle("��Ӳ���");
				JPanel foodtype_panal = new JPanel();
				foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
				addfoodtype_frame.setContentPane(foodtype_panal);
				
				JLabel lblNewLabel = new JLabel("�����ţ�");
				lblNewLabel.setBounds(10, 15, 70, 15);
				foodtype_panal.add(lblNewLabel);
				
				JEditorPane noText = new JEditorPane();
				noText.setBounds(93, 15, 150, 21);
				foodtype_panal.add(noText);
				
				JLabel lblNewLabel_1 = new JLabel("��λ����");
				lblNewLabel_1.setBounds(10, 60, 70, 15);
				foodtype_panal.add(lblNewLabel_1);
				
				JEditorPane numText = new JEditorPane();
				numText.setBounds(93, 60, 150, 21);
				foodtype_panal.add(numText);
				
				
				
				foodtype_panal.setLayout(null);
				JButton addfoodtypebtn = new JButton("ȷ�����");
				addfoodtypebtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String noStr=StrEmptyUtil.strTrim(noText.getText());
						String numStr=StrEmptyUtil.strTrim(numText.getText());
						int noInt = 0;
						int numInt = 0;
						
						if(StrEmptyUtil.isEmpty(noStr)){
							JOptionPane.showMessageDialog(null, "�����Ų���Ϊ�գ�");
							return;
						}else{
							try{
								noInt = Integer.parseInt(noStr);
							} catch (NumberFormatException e1) {
								//����
							JOptionPane.showMessageDialog(null, "������ֻ��Ϊ�����֣�");
							return;
							}
						}
						if(StrEmptyUtil.isEmpty(numStr)){
							JOptionPane.showMessageDialog(null, "��λ������Ϊ�գ�");
							return;
						}else{
							try{
								numInt = Integer.parseInt(numStr);
							} catch (NumberFormatException e1) {
								//����
							JOptionPane.showMessageDialog(null, "��λ��ֻ��Ϊ�����֣�");
							return;
							}
						}
						Buffet bf = new Buffet();
						Result result = bfd.create(noInt,numInt);
						if(result.success){
							addfoodtype_frame.dispose();
							try {
				                //ˢ���б�
								JTableOperationbf table = new JTableOperationbf();
				                table.reloadJTable(admin.getjTable());
				            } catch (Exception e1) {
				                e1.printStackTrace();
				            }
							
						}else{
							JOptionPane.showMessageDialog(null, "�����Ϣʧ����������ӣ�");
						}
					}
				});
				addfoodtypebtn.setBounds(100, 180, 93, 23);
				foodtype_panal.add(addfoodtypebtn);
				addfoodtype_frame.setVisible(true);
				//���õ�¼ҳ�����JFrame
				addfoodtype_frame.setLocationRelativeTo(null);
			}
			//�༭��ϵ����
				void upbuffetAux(){
					int rowN = admin.getjTable().getSelectedRow();
					Object objId = admin.getjTable().getValueAt(rowN, 0);
					int rowId = Integer.parseInt(objId.toString());
					Object objNo = admin.getjTable().getValueAt(rowN, 1);
					String rowNo = String.valueOf(objNo);
					Object objNum = admin.getjTable().getValueAt(rowN, 2);
					String rowNum = String.valueOf(objNum);
					
					JFrame addfoodtype_frame = new JFrame();
					addfoodtype_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					addfoodtype_frame.setBounds(10, 10, 300, 250);
					addfoodtype_frame.setTitle("�༭����");
					JPanel foodtype_panal = new JPanel();
					foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
					addfoodtype_frame.setContentPane(foodtype_panal);
					
					JLabel lblNewLabel = new JLabel("�����ţ�");
					lblNewLabel.setBounds(10, 15, 70, 15);
					foodtype_panal.add(lblNewLabel);
					
					JEditorPane noText = new JEditorPane();
					noText.setText(rowNo);
					noText.setEnabled(false);
					noText.setBounds(93, 15, 150, 21);
					foodtype_panal.add(noText);
					
					JLabel lblNewLabel_1 = new JLabel("��λ����");
					lblNewLabel_1.setBounds(10, 60, 70, 15);
					foodtype_panal.add(lblNewLabel_1);
					
					JEditorPane numText = new JEditorPane();
					numText.setText(rowNum);
					numText.setBounds(93, 60, 150, 21);
					foodtype_panal.add(numText);
					
					foodtype_panal.setLayout(null);
					JButton addfoodtypebtn = new JButton("����༭");
					addfoodtypebtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String noStr=StrEmptyUtil.strTrim(noText.getText());
							String numStr=StrEmptyUtil.strTrim(numText.getText());
							int noInt = 0;
							int numInt = 0;
							
							if(StrEmptyUtil.isEmpty(noStr)){
								JOptionPane.showMessageDialog(null, "�����Ų���Ϊ�գ�");
								return;
							}else{
								try{
									noInt = Integer.parseInt(noStr);
								} catch (NumberFormatException e1) {
									//����
								JOptionPane.showMessageDialog(null, "������ֻ��Ϊ�����֣�");
								return;
								}
							}
							if(StrEmptyUtil.isEmpty(numStr)){
								JOptionPane.showMessageDialog(null, "��λ������Ϊ�գ�");
								return;
							}else{
								try{
									numInt = Integer.parseInt(numStr);
								} catch (NumberFormatException e1) {
									//����
								JOptionPane.showMessageDialog(null, "��λ��ֻ��Ϊ�����֣�");
								return;
								}
							}
							Buffet bf = new Buffet();
							Result result = bfd.update(rowId,numInt);
							if(result.success){
								addfoodtype_frame.dispose();
								try {
					                //ˢ���б�
									JTableOperationbf table = new JTableOperationbf();
					                table.reloadJTable(admin.getjTable());
					            } catch (Exception e1) {
					                e1.printStackTrace();
					            }
								
							}else{
								JOptionPane.showMessageDialog(null, "�����Ϣʧ����������ӣ�");
							}
						}
					});
					addfoodtypebtn.setBounds(10, 180, 93, 23);
					foodtype_panal.add(addfoodtypebtn);
					addfoodtype_frame.setVisible(true);
					//���õ�¼ҳ�����JFrame
					addfoodtype_frame.setLocationRelativeTo(null);
				}
				class JPanelImg extends JPanel{
					Image img;
					public JPanelImg(){
						img=new ImageIcon(index.class.getResource("/img/12.jpg")).getImage();
					}
					public void paintComponent(Graphics g)
					{
						g.drawImage(img,0,0,1050,750,null );
					}
				}
}
