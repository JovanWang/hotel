package javaView;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class finish extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					finish frame = new finish(-1,"jovan");
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
	public finish(int id, String username) {
		setTitle("�µ�ȷ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanelImg();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�𾴵�"+username+",��ӭ�òͣ�");
		lblNewLabel.setBounds(125, 89, 253, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("���˴��ò͵Ķ�����Ϊ��"+id);
		lblNewLabel_1.setBounds(125, 118, 253, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("�뵽ǰ̨ȷ�Ͻ��˺�ʼ�òͣ�");
		lblNewLabel_2.setBounds(125, 147, 253, 15);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("�òͽ���");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new index(username).setVisible(true);
			}
		});
		btnNewButton.setBounds(160, 187, 93, 23);
		contentPane.add(btnNewButton);
		//���õ�¼ҳ�����JFrame
		this.setLocationRelativeTo(null);
	}
	class JPanelImg extends JPanel{
		Image img;
		public JPanelImg(){
			img=new ImageIcon(index.class.getResource("/img/panzi.jpg")).getImage();
		}
		public void paintComponent(Graphics g)
		{
			g.drawImage(img,0,0,450,300,null );
		}
	}
}
