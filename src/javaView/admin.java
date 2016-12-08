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
		setTitle("�Ƶ����-��̨����ϵͳ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1050, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		
		JButton foodtypebtn = new JButton("��ϵ����");
		foodtypebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foodtypedialog();
			}
		});
		foodtypebtn.setBounds(29, 16, 93, 23);
		contentPane.add(foodtypebtn);
		JButton foodbtn = new JButton("��Ʒ����");
		foodbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fooddialog();
			}
		});
		foodbtn.setBounds(145, 16, 93, 23);
		contentPane.add(foodbtn);
		
		
		//���õ�¼ҳ�����JFrame
		this.setLocationRelativeTo(null);
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
    //��ϵ�б��ࣨˢ���ã�
	public class JTableOperation{
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
		JTableOperation jTableInit = new JTableOperation();
		JTable table = jTableInit.JtableDataInit();
		
		//������
		JScrollPane jtablesp= new JScrollPane(table);
		jtablesp.setBounds(10, 10, 500, 220);
		foodtype_panal.add(jtablesp);
		//��scrollPane��jTable ��ӵ�ȫ�ֱ�����
		admin.setjScrollPane(jtablesp);
		admin.setjTable(jTable);
		
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
				addfoodtypeAux();
			}
		});
		upfoodtypebtn.setBounds(190, 240, 143, 23);
		foodtype_panal.add(upfoodtypebtn);
		JButton defoodtypebtn = new JButton("ɾ��ѡ�в�ϵ");
		defoodtypebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowN = table.getSelectedRow();
				Object obj = table.getValueAt(rowN, 0);
				int rown = Integer.parseInt(obj.toString());
				Result result = ftd.delete(rown);
				if(result.success){
					try {
		                //ˢ���б�
		                JTableOperation jTableOperation = new JTableOperation();
		                jTableOperation.reloadJTable(admin.getjTable());
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
		                JTableOperation jTableOperation = new JTableOperation();
		                jTableOperation.reloadJTable(admin.getjTable());
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
	
	//
	//��Ʒ����
	void fooddialog(){
		JFrame foodtype_frame = new JFrame();
		foodtype_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		foodtype_frame.setBounds(10, 10, 700, 330);
		foodtype_frame.setTitle("��Ʒ����");
		JPanel foodtype_panal = new JPanel();
		foodtype_panal.setBorder(new EmptyBorder(5, 5, 5, 5));
		foodtype_frame.setContentPane(foodtype_panal);
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
		//���ñ�����������ݾ���
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
	    tcr.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
	    table.setDefaultRenderer(Object.class, tcr);
		//������
		JScrollPane jtablesp= new JScrollPane(table);
		jtablesp.setBounds(10, 10, 660, 220);
		foodtype_panal.add(jtablesp);
		
		foodtype_panal.setLayout(null);
		JButton addfoodtypebtn = new JButton("��Ӳ�ϵ");
		addfoodtypebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addfoodtypeAux();
			}
		});
		addfoodtypebtn.setBounds(10, 240, 93, 23);
		foodtype_panal.add(addfoodtypebtn);
		foodtype_frame.setVisible(true);
		//���õ�¼ҳ�����JFrame
		foodtype_frame.setLocationRelativeTo(null);
	}
}
