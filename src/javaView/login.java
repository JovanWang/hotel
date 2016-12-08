package javaView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

import javaUtil.ConUtil;
import javaUtil.StrEmptyUtil;
import javaDao.UserDao;
import javaModel.Result;
import javax.swing.JRadioButton;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	ButtonGroup typeGroup = new ButtonGroup();
	JRadioButton userRdbtn = new JRadioButton("顾客");
	JRadioButton adminRdbtn = new JRadioButton("管理");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setTitle("酒店管理系统");
		//改变系统默认字体
				Font font = new Font("Dialog", Font.PLAIN, 12);
				java.util.Enumeration keys = UIManager.getDefaults().keys();
				while (keys.hasMoreElements()) {
					Object key = keys.nextElement();
					Object value = UIManager.get(key);
					if (value instanceof javax.swing.plaf.FontUIResource) {
						UIManager.put(key, font);
					}
				}
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("账号");
		lblNewLabel.setBounds(124, 76, 24, 15);
		
		JLabel lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setBounds(124, 115, 24, 15);
		
		username = new JTextField();
		username.setBounds(152, 73, 136, 21);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(152, 112, 136, 21);
		
		JButton login = new JButton("登录");
		login.setBounds(124, 177, 72, 23);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginAux(e);
			}
		});
		
		JButton btnNewButton_1 = new JButton("置空");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetNull(e);
			}
		});
		btnNewButton_1.setBounds(226, 177, 71, 23);
		
		JLabel lblNewLabel_2 = new JLabel("酒店管理系统");
		lblNewLabel_2.setBounds(120, 10, 193, 30);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 30));
		contentPane.setLayout(null);
		
		
		contentPane.add(lblNewLabel_2);
		contentPane.add(login);
		contentPane.add(btnNewButton_1);
		contentPane.add(lblNewLabel_1);
		contentPane.add(password);
		contentPane.add(lblNewLabel);
		contentPane.add(username);
		userRdbtn.setFont(new Font("宋体", Font.PLAIN, 12));
		
		userRdbtn.setSelected(true);
		userRdbtn.setBounds(152, 139, 55, 23);
		contentPane.add(userRdbtn);
		adminRdbtn.setFont(new Font("宋体", Font.PLAIN, 12));
		adminRdbtn.setBounds(226, 139, 62, 23);
		contentPane.add(adminRdbtn);
		typeGroup.add(userRdbtn);
		typeGroup.add(adminRdbtn);
		//设置登录页面居中JFrame
		this.setLocationRelativeTo(null);
	}
	class JPanelImg extends JPanel{
		Image img;
		public JPanelImg(){
			img=new ImageIcon(index.class.getResource("/img/head1.jpg")).getImage();
		}
		public void paintComponent(Graphics g)
		{
			g.drawImage(img,0,0,450,300,null );
		}
	}
	void loginAux(ActionEvent e){
		String usernameStr=StrEmptyUtil.strTrim(username.getText());
		String passwordStr=StrEmptyUtil.strTrim(new String(password.getPassword()));
		int role = 0;
		if(userRdbtn.isSelected()){
			role = 1;
		}else if(adminRdbtn.isSelected()){
			role = 2;
		}
		if(StrEmptyUtil.isEmpty(usernameStr)){
			JOptionPane.showMessageDialog(null, "用户名信息不能为空！");
			return;
		}
		if(StrEmptyUtil.isEmpty(passwordStr)){
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		if(role == 0){
			JOptionPane.showMessageDialog(null, "请选择用户类型！");
			return;
		}
		UserDao userDao = new UserDao();
		Result result = userDao.login(usernameStr,passwordStr,role);
		if(result.success){
			dispose();
			if(role == 1){
				new index(usernameStr).setVisible(true);
			}else if(role == 2){
				
			}
			
		}else{
			JOptionPane.showMessageDialog(null, result.message);
		}
	}
	void resetNull(ActionEvent e){
		username.setText(null);
		password.setText(null);
		userRdbtn.setSelected(true);
	}
}
