package Dodge;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Help_Screen extends JPanel implements ActionListener {
	Main_Frame mf;
	static int page=1;
	private Image help_bg;
	private Image help_bg2;
	private Image help_bg3;
	private Image help_bg4;
	private Image help_bg5;
	private Image help_bg6;
	private JButton btn;
	private JButton btn2;
	private JButton btn_home;
	Images img = new Images();
	public Help_Screen(Main_Frame mf) {
		this.mf = mf;
		try {
			help_bg = ImageIO.read(new File("images/help_bg1.jpg"));
			help_bg2 = ImageIO.read(new File("images/help_bg2.jpg"));
			help_bg3 = ImageIO.read(new File("images/help_bg3.jpg"));
			help_bg4 = ImageIO.read(new File("images/help_bg4.jpg"));
			help_bg5 = ImageIO.read(new File("images/help_bg5.jpg"));
			help_bg6 = ImageIO.read(new File("images/help_bg6.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		page=1;
		Bt_h();
		Bt_h2();
		Bt_home();
	}
	public void Bt_h() {
		btn=new JButton();
		btn.setBounds(510, 230, 30, 30);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false); 
		btn.setContentAreaFilled(false);
		btn.addActionListener(this);
		this.mf.add(btn);
	}
	public void Bt_h2() {
		btn2=new JButton();
		btn2.setBounds(100, 230, 30, 30);
		btn2.setBorderPainted(false);
		btn2.setFocusPainted(false); 
		btn2.setContentAreaFilled(false);
		btn2.addActionListener(this);
		this.mf.add(btn2);
	}
	public void Bt_home() {
		btn_home=new JButton();
		btn_home.setBounds(102, 73, 40, 40);
		btn_home.setBorderPainted(false);
		btn_home.setFocusPainted(false); 
		btn_home.setContentAreaFilled(false);
		btn_home.addActionListener(this);
		this.mf.add(btn_home);
	}

	@Override
	protected void paintComponent(Graphics g) { // 이미지 작업
		super.paintComponent(g);
		if(page==1)
			g.drawImage(help_bg, 0, 0, 640, 480, null);
		else if(page==2)
			g.drawImage(help_bg2, 0, 0, 640, 480, null);
		else if(page==3)
			g.drawImage(help_bg3, 0, 0, 640, 480, null);
		else if(page==4)
			g.drawImage(help_bg4, 0, 0, 640, 480, null);
		else if(page==5)
			g.drawImage(help_bg5, 0, 0, 640, 480, null);
		else if(page==6)
			g.drawImage(help_bg6, 0, 0, 640, 480, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn) {
			page++;
			if(page>=6) {
				page=6;
			}
			repaint();
			revalidate();
		}
		else if (e.getSource()==btn2) {
			page--;
			if(page<=1)
				page=1;
			repaint();
			revalidate();
		}
		else if(e.getSource()==btn_home) {
			mf.Help_next();
			btn.setVisible(false);
			btn2.setVisible(false);
			btn_home.setVisible(false);
		}
	}
	public static void main(String[] args) {
	}
}