package Dodge;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Dodge.Choice_Screen.Music_class;

import java.io.*;
import javax.imageio.ImageIO;
import java.net.URI;
import java.net.URISyntaxException;
import javax.sound.sampled.*;

public class Menu_Screen extends JPanel implements ActionListener {
	JButton start_bt; // 시작 버튼
	JButton help_bt;  // 도움말 버튼
	JButton rank_bt;  // 랭킹 버튼
	JButton exit_bt;  // 나가기 버튼
	JButton sound_bt;  // 사운드 버튼 
	JButton skin_bt;
	Main_Frame mf;
	Images img = new Images();
	static boolean sound_on;
	static Clip c;
	static String skin;
	private Image Intimg; //배경 이미지 받기
	//---------- 버튼 이미지들---------- //
	ImageIcon start=img.geticon("start");
	ImageIcon start2=img.geticon("start2"); 
	ImageIcon help=img.geticon("help");
	ImageIcon help2=img.geticon("help2");
	ImageIcon rank=img.geticon("rank");
	ImageIcon rank2=img.geticon("rank2");
	ImageIcon exit=img.geticon("exit");
	ImageIcon exit2=img.geticon("exit2");
	ImageIcon sound_o=img.geticon("sound_o");
	ImageIcon sound_x=img.geticon("sound_x");
	ImageIcon gear = img.geticon("gear");
	public Menu_Screen(Main_Frame mf,boolean sound, String sk) {
		this.mf = mf; //TestFrame 클래스로 부터 창 크기를 받아옴
		this.setSize(640, 480);
		this.setLayout(null);
		try {
			Intimg = ImageIO.read(new File("images/002.jpg")); //배경 이미지 받기
		} catch (IOException e) {
			e.printStackTrace();
		}
		sound_on=sound;
		skin=sk;
		Bt1(); //start 버튼
		Bt2(); //help 버튼 
		Bt3(); //rank 버튼
		Bt4(); //exit 버튼
		Bt5(); //sound 버튼
		Bt6();
		if(sound_on) {
			Music_class music = new Music_class();
			music.start();
		}
	}
	public class Music_class extends Thread {
		File a;
    	public void run() {
    		try {
    			while (sound_on) {
    				a = new File("music\\menu.wav");
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
	

	private void Bt1() {// start버튼
		start_bt = new JButton(start);
		start_bt.setRolloverIcon(start2); //마우스를 올렸을 경우 이미지 변경
		start_bt.setBounds(270, 150, 100, 32);//버튼의 위치 및 크기 지정
		start_bt.setBorderPainted(false); //JButton 외곽선 지우기
		start_bt.setFocusPainted(false);  //JButton이 선택 되었을때 테두리 사용안함
		start_bt.setContentAreaFilled(false); //JButton 채우기 안함
		start_bt.addActionListener(this); //액션 읽음
		mf.add(start_bt);// 패널에 버튼 추가
	}
	private void Bt2() {// help버튼
		help_bt = new JButton(help);
		help_bt.setRolloverIcon(help2);//마우스를 올렸을 경우 이미지 변경
		help_bt.setBounds(270, 190, 100, 32);//버튼의 위치 및 크기 지정
		help_bt.setBorderPainted(false);//JButton 외곽선 지우기
		help_bt.setFocusPainted(false);//JButton이 선택 되었을때 테두리 사용안함
		help_bt.setContentAreaFilled(false); //JButton 채우기 안함
		help_bt.addActionListener(this); //액션 읽음
		mf.add(help_bt);// 패널에 버튼 추가
	}
	private void Bt3() {// rank버튼
		rank_bt = new JButton(rank);
		rank_bt.setRolloverIcon(rank2);//마우스를 올렸을 경우 이미지 변경
		rank_bt.setBounds(270, 230, 100, 32);//버튼의 위치 및 크기 지정
		rank_bt.setBorderPainted(false);//JButton 외곽선 지우기
		rank_bt.setFocusPainted(false);//JButton이 선택 되었을때 테두리 사용안함
		rank_bt.setContentAreaFilled(false); //JButton 채우기 안함
		rank_bt.addActionListener(this); //액션 읽음
		mf.add(rank_bt);// 패널에 버튼 추가
	}
	private void Bt4() {// exit버튼
		exit_bt = new JButton(exit);
		exit_bt.setRolloverIcon(exit2);//마우스를 올렸을 경우 이미지 변경
		exit_bt.setBounds(270, 270, 100, 32);//버튼의 위치 및 크기 지정
		exit_bt.setBorderPainted(false);//JButton 외곽선 지우기
		exit_bt.setFocusPainted(false);//JButton이 선택 되었을때 테두리 사용안함
		exit_bt.setContentAreaFilled(false); //JButton 채우기 안함
		exit_bt.addActionListener(this); //액션 읽음
		mf.add(exit_bt);// 패널에 버튼 추가
	}
	private void Bt5() { //sound 버튼
		if(sound_on==true)
			sound_bt = new JButton(sound_o);
		else
			sound_bt = new JButton(sound_x);
		sound_bt.setBounds(570, 5, 40, 38);//버튼의 위치 및 크기 지정
		sound_bt.setBorderPainted(false); //JButton 외곽선 지우기
		sound_bt.setFocusPainted(false);  //JButton이 선택 되었을때 테두리 사용안함
		sound_bt.setContentAreaFilled(false); //JButton 채우기 안함
		sound_bt.addActionListener(this); //액션 읽음
		mf.add(sound_bt);
	}
	private void Bt6() { //설정 버튼
		
		skin_bt = new JButton(gear);
		skin_bt.setBounds(15, 390, 40, 40);//버튼의 위치 및 크기 지정
		skin_bt.setBorderPainted(false); //JButton 외곽선 지우기
		skin_bt.setFocusPainted(false);  //JButton이 선택 되었을때 테두리 사용안함
		skin_bt.setContentAreaFilled(false); //JButton 채우기 안함
		skin_bt.addActionListener(this); //액션 읽음
		mf.add(skin_bt);
	}

	@Override
	public void actionPerformed(ActionEvent e) { //버튼 이벤트 처리
		if (e.getSource() == start_bt) {
			c.stop();
			c.close();
			mf.Intro_next(sound_on, skin); //선택 화면으로 이동
			// 버튼들 지워줌
			sound_bt.setVisible(false);
			start_bt.setVisible(false); 
			help_bt.setVisible(false);
			rank_bt.setVisible(false);
			exit_bt.setVisible(false);
			skin_bt.setVisible(false);
		}
		else if(e.getSource() == help_bt) {
			c.stop();
			c.close();
			mf.Help_msg();// 도움말 화면으로 이동
			// 버튼들 지워줌
			sound_bt.setVisible(false);
			start_bt.setVisible(false);
			help_bt.setVisible(false);
			rank_bt.setVisible(false);
			exit_bt.setVisible(false);
			skin_bt.setVisible(false);
		} else if (e.getSource() == rank_bt) {
			try {
				Desktop.getDesktop().browse(new URI("http://localhost:3000"));
			} catch (IOException | URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == exit_bt) {
			System.exit(0); //게임 종료
		}
		else if(e.getSource()==sound_bt) {
			if(sound_bt.getIcon()!=sound_x) {
				sound_bt.setIcon(sound_x);
				sound_on=false;
				c.stop();
				c.close();
			}
			else {
				sound_bt.setIcon(sound_o);
				sound_on=true;
				Music_class music = new Music_class();
				music.start();
			}
		}
		else if(e.getSource()==skin_bt) {
			String[] sk = {"변","학점"};
			String ski = (String) JOptionPane.showInputDialog(null, "장애물 스킨을 설정하세요", "스킨 설정", JOptionPane.PLAIN_MESSAGE, null, sk, sk[0]);
			if(ski==null)
				ski=skin;
			System.out.println(ski);
			skin=ski;
		}

	}

	@Override
	protected void paintComponent(Graphics g) { // 이미지 작업
		super.paintComponent(g);
		g.drawImage(Intimg, 0, 0, 640, 480, null); //배경 그리기
	}

	public static void main(String[] args) {

	}

}