package javaView;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javaDao.BuffetDao;
import javaDao.BillDao;
import javaDao.FoodDao;
import javaModel.Buffet;
import javaModel.Food;
import javaModel.Food_type;
import javaModel.Result;
import javaView.admin.Item;
import javaView.admin.JTableOperationfood;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class table extends JFrame {

	private JPanel contentPane;
	private BuffetDao bd = new BuffetDao();
	private BillDao bld = new BillDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					table frame = new table("jovan");
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
	public table(String username) {
		setTitle("选择餐桌");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanelImg();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("选择就餐桌号：");
		label.setBounds(120, 30, 130, 15);
		ArrayList<Buffet> buffet = bd.select();
		Vector model = new Vector();
		for(int i=0;i<buffet.size();i++){
			if(buffet.get(i).getstatus() == 1){
			model.addElement(new Item(buffet.get(i).getid(), String.valueOf(buffet.get(i).gettable_no())));
			
			}
		}
		JLabel labelnum = new JLabel();
		labelnum.setBounds(120, 110, 130, 15);
		JComboBox comboBox = new JComboBox(model);
		
		JButton button = new JButton("确定");
		button.setBounds(150, 110, 67, 23);
		AbstractButton botton;
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) comboBox.getSelectedItem();
				int i = item.getId();
				Result result1 = bld.create(i,username);
				Result result2 = bd.upstatus(i,2);
				if(result1.success&&result2.success){
					dispose();
					new foodfile(result1.data,username).setVisible(true);
					
				}else{
					JOptionPane.showMessageDialog(null, "创建失败请检查网络后重新创建！");
				}
				
				//ArrayList<Food_type> food_type = fld.select();
				
			}
			});
		JButton button_11 = new JButton("返回");
		
		button_11.setBounds(235, 110, 67, 23);
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new login().setVisible(true);
			}
			});
		comboBox.setBounds(219, 27, 99, 21);
		contentPane.setLayout(null);
		contentPane.add(button);
		contentPane.add(label);
		contentPane.add(labelnum);
		contentPane.add(button_11);
		contentPane.add(comboBox);
		//设置页面居中JFrame
		setLocationRelativeTo(null);
	}
	class JPanelImg extends JPanel{
		Image img;
		public JPanelImg(){
			img=new ImageIcon(index.class.getResource("/img/table1.jpg")).getImage();
		}
		public void paintComponent(Graphics g)
		{
			g.drawImage(img,0,0,450,300,null );
		}
	}
}
