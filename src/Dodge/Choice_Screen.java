package Dodge;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Choice_Screen extends JPanel implements ActionListener{
	Main_Frame mf;
	private JButton btn;
	private JButton h_btn;
	private JButton level_btn1;
	private JButton level_btn2;
	private JButton level_btn3;
	private JButton mouse;
	private JButton keyboard;
	private Image Intimg;
	static int level;
	static boolean mouse_check;
	static Clip c;
	static boolean sound_on;
	Images ti = new Images();
	ImageIcon le1 = ti.geticon("level1");
	ImageIcon le2 = ti.geticon("level2");
	ImageIcon le3 = ti.geticon("level3");
	JLabel imageLabel;
	ImageIcon s = new ImageIcon("images/ring.png");
	ImageIcon u = new ImageIcon("images/unvisible.png");
	
	public class Music_class extends Thread {
		File a;
    	public void run() {
    		try {
    			while (sound_on) {
    				a = new File("music\\choice.wav");
    				AudioInputStream b = AudioSystem.getAudioInputStream(a);
    				c = AudioSystem.getClip();
    				c.open(b);
    				c.start();
    				Thread.sleep(c.getMicrosecondLength() / 1000);
    			}
    		} catch (Exception e) {
    			return;
    		}
    		
    	}
    }
	public Choice_Screen(Main_Frame mf,boolean sound) {
		this.mf=mf;
		try {
			Intimg = ImageIO.read(new File("images/choose_bg1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("선택 화면");
		level=1;
		mouse_check=false;
		btn=new JButton();
		btn.setBounds(90, 260, 60, 60);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false); 
		btn.setContentAreaFilled(false);
		btn.addActionListener(this);
		sound_on=sound;
		if(sound_on==true) {
			Music_class music = new Music_class();
			music.start();
		}
		this.mf.add(btn);
		home_btn();
		level_btn();
		Operation_btn();
		screan(level);
		
	}
	
	public void home_btn(){
		h_btn=new JButton();
		h_btn.setBounds(100, 40, 40, 40);
		h_btn.setBorderPainted(false);
		h_btn.setFocusPainted(false); 
		h_btn.setContentAreaFilled(false);
		h_btn.addActionListener(this);
		this.mf.add(h_btn);
	}
	public void level_btn() {
		level_btn1=new JButton();
		level_btn1.setBounds(283, 335, 80, 80);
		level_btn1.setBorderPainted(false);
		level_btn1.setFocusPainted(false); 
		level_btn1.setContentAreaFilled(false);
		level_btn1.addActionListener(this);
		
		level_btn2=new JButton();
		level_btn2.setBounds(373, 335, 80, 80);
		level_btn2.setBorderPainted(false);
		level_btn2.setFocusPainted(false); 
		level_btn2.setContentAreaFilled(false);
		level_btn2.addActionListener(this);
		
		level_btn3=new JButton();
		level_btn3.setBounds(463, 335, 80, 80);
		level_btn3.setBorderPainted(false);
		level_btn3.setFocusPainted(false); 
		level_btn3.setContentAreaFilled(false);
		level_btn3.addActionListener(this);
		this.mf.add(level_btn1);
		this.mf.add(level_btn2);
		this.mf.add(level_btn3);
	}
	public void Operation_btn() {
		mouse=new JButton();
		mouse.setBounds(525, 35, 40, 40);
		mouse.setBorderPainted(false);
		mouse.setFocusPainted(false); 
		mouse.setContentAreaFilled(false);
		mouse.addActionListener(this);
		keyboard=new JButton();
		keyboard.setBounds(450, 40, 40, 40);
		keyboard.setBorderPainted(false);
		keyboard.setFocusPainted(false); 
		keyboard.setContentAreaFilled(false);
		keyboard.addActionListener(this);
		if(mouse_check==false) {
			keyboard.setIcon(s);
		}
		this.mf.add(mouse);
		this.mf.add(keyboard);
	}
	public void screan(int le) {
		if(le==1) {
			imageLabel = new JLabel(le1);
		}
		else if(le==2){
			imageLabel = new JLabel(le2);
		}
		else if(le==3) {
			imageLabel = new JLabel(le3);
		}
		add(imageLabel);
		System.out.println(level);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn) {//게임 시작
			if(sound_on==true) {
				c.stop();
				c.close();}
			mf.Choose_next(level, mouse_check);
			btn.setVisible(false);
			h_btn.setVisible(false);
			level_btn1.setVisible(false);
			level_btn2.setVisible(false);
			level_btn3.setVisible(false);
			keyboard.setVisible(false);
			mouse.setVisible(false);
		}
		else if(e.getSource()==h_btn){
			if(sound_on==true) {//뒤로가기
				c.stop();
				c.close();}
			mf.Choose_back();
			btn.setVisible(false);
			h_btn.setVisible(false);
			level_btn1.setVisible(false);
			level_btn2.setVisible(false);
			level_btn3.setVisible(false);
			keyboard.setVisible(false);
			mouse.setVisible(false);
			
		}
		else if (e.getSource() == level_btn1) {
			level=1;
			System.out.println(level);
			imageLabel.setIcon(le1);
		} else if (e.getSource() == level_btn2) {
			level=2;
			System.out.println(level);
			imageLabel.setIcon(le2);
		} else if (e.getSource() == level_btn3) {
			level=3;
			System.out.println(level);
			imageLabel.setIcon(le3);
		}
		else if (e.getSource()==mouse) {
			mouse_check=true;
			System.out.println("mouse");
			mouse.setIcon(s);
			keyboard.setIcon(u);
		}
		else if (e.getSource()==keyboard) {
			mouse_check=false;
			System.out.println("key");
			mouse.setIcon(u);
			keyboard.setIcon(s);
		}
	}

	@Override
	protected void paintComponent(Graphics g) { // 배경이미지 작업
		g.drawImage(Intimg, 0, 0, 640, 480, null);
	}

	public static void main(String[] args) {

	}

}