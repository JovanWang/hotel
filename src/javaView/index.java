package javaView;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class index extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String username = "";
					index frame = new index(username);
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
	public index(String username) {
		setResizable(false);
		//setBackground(new Color(240, 240, 240));
		setTitle("酒店管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1050, 750);
		contentPane = new JPanelImg("/img/10.jpg",0,0,1050,750,0,0, 1050, 750);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBounds(0,0,1050,750);
		JLabel  usernameLab;
		if(username == ""){
			usernameLab = new JLabel("未登录");
		}else{
			usernameLab = new JLabel(username+"，你好！");
		}
		usernameLab.setBounds(900, 10, 150,20);
		contentPane.add(usernameLab);

		ImageIcon imgicon =new ImageIcon(index.class.getResource("/img/chquit.jpg"));
		JButton logout = new JButton("退出",imgicon);
		logout.setContentAreaFilled(false);
		logout.setBounds(810, 330, 210, 138);
		logout.setBorder(null);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new login().setVisible(true);
			}
		});
		contentPane.add(logout);
		
		
		imgicon =new ImageIcon(index.class.getResource("/img/chfood.jpg"));
		JButton createbtn = new JButton(imgicon);
		createbtn.setContentAreaFilled(false);
		createbtn.setBounds(100, 200, 502, 264);
		createbtn.setBorder(null);
		createbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new table(username).setVisible(true);
			}
		});
		//createbtn.setBounds(480, 214, 93, 23);
//		JButton selectbtn = new JButton("点餐记录");
//		selectbtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
//		selectbtn.setBounds(533, 326, 93, 23);
		contentPane.setLayout(null);
		contentPane.add(createbtn);
//		contentPane.add(selectbtn);
		//设置登录页面居中JFrame
		this.setLocationRelativeTo(null);
		
	}
	class JPanelImg extends JPanel{
		Image img;
		int x11,x12,y11,y12,x21,x22,y21,y22;
		public JPanelImg(String src,int x11,int y11,int x12,int y12,int x21,int y21,int x22,int y22){
			img=new ImageIcon(index.class.getResource(src)).getImage();
			this.x11 = x11;
			this.x12 = x12;
			this.y11 = y11;
			this.y12 = y12;
			this.x21 = x21;
			this.x22 = x22;
			this.y21 = y21;
			this.y22 = y22;
		}
		public void paintComponent(Graphics g)
		{
			g.drawImage(img,x11,y11,x12,y12,x21, y21,x22, y22,null );
		}
	}
}
