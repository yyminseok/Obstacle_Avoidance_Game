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
	JButton start_bt; // ���� ��ư
	JButton help_bt;  // ���� ��ư
	JButton rank_bt;  // ��ŷ ��ư
	JButton exit_bt;  // ������ ��ư
	JButton sound_bt;  // ���� ��ư 
	JButton skin_bt;
	Main_Frame mf;
	Images img = new Images();
	static boolean sound_on;
	static Clip c;
	static String skin;
	private Image Intimg; //��� �̹��� �ޱ�
	//---------- ��ư �̹�����---------- //
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
		this.mf = mf; //TestFrame Ŭ������ ���� â ũ�⸦ �޾ƿ�
		this.setSize(640, 480);
		this.setLayout(null);
		try {
			Intimg = ImageIO.read(new File("images/002.jpg")); //��� �̹��� �ޱ�
		} catch (IOException e) {
			e.printStackTrace();
		}
		sound_on=sound;
		skin=sk;
		Bt1(); //start ��ư
		Bt2(); //help ��ư 
		Bt3(); //rank ��ư
		Bt4(); //exit ��ư
		Bt5(); //sound ��ư
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
	

	private void Bt1() {// start��ư
		start_bt = new JButton(start);
		start_bt.setRolloverIcon(start2); //���콺�� �÷��� ��� �̹��� ����
		start_bt.setBounds(270, 150, 100, 32);//��ư�� ��ġ �� ũ�� ����
		start_bt.setBorderPainted(false); //JButton �ܰ��� �����
		start_bt.setFocusPainted(false);  //JButton�� ���� �Ǿ����� �׵θ� ������
		start_bt.setContentAreaFilled(false); //JButton ä��� ����
		start_bt.addActionListener(this); //�׼� ����
		mf.add(start_bt);// �гο� ��ư �߰�
	}
	private void Bt2() {// help��ư
		help_bt = new JButton(help);
		help_bt.setRolloverIcon(help2);//���콺�� �÷��� ��� �̹��� ����
		help_bt.setBounds(270, 190, 100, 32);//��ư�� ��ġ �� ũ�� ����
		help_bt.setBorderPainted(false);//JButton �ܰ��� �����
		help_bt.setFocusPainted(false);//JButton�� ���� �Ǿ����� �׵θ� ������
		help_bt.setContentAreaFilled(false); //JButton ä��� ����
		help_bt.addActionListener(this); //�׼� ����
		mf.add(help_bt);// �гο� ��ư �߰�
	}
	private void Bt3() {// rank��ư
		rank_bt = new JButton(rank);
		rank_bt.setRolloverIcon(rank2);//���콺�� �÷��� ��� �̹��� ����
		rank_bt.setBounds(270, 230, 100, 32);//��ư�� ��ġ �� ũ�� ����
		rank_bt.setBorderPainted(false);//JButton �ܰ��� �����
		rank_bt.setFocusPainted(false);//JButton�� ���� �Ǿ����� �׵θ� ������
		rank_bt.setContentAreaFilled(false); //JButton ä��� ����
		rank_bt.addActionListener(this); //�׼� ����
		mf.add(rank_bt);// �гο� ��ư �߰�
	}
	private void Bt4() {// exit��ư
		exit_bt = new JButton(exit);
		exit_bt.setRolloverIcon(exit2);//���콺�� �÷��� ��� �̹��� ����
		exit_bt.setBounds(270, 270, 100, 32);//��ư�� ��ġ �� ũ�� ����
		exit_bt.setBorderPainted(false);//JButton �ܰ��� �����
		exit_bt.setFocusPainted(false);//JButton�� ���� �Ǿ����� �׵θ� ������
		exit_bt.setContentAreaFilled(false); //JButton ä��� ����
		exit_bt.addActionListener(this); //�׼� ����
		mf.add(exit_bt);// �гο� ��ư �߰�
	}
	private void Bt5() { //sound ��ư
		if(sound_on==true)
			sound_bt = new JButton(sound_o);
		else
			sound_bt = new JButton(sound_x);
		sound_bt.setBounds(570, 5, 40, 38);//��ư�� ��ġ �� ũ�� ����
		sound_bt.setBorderPainted(false); //JButton �ܰ��� �����
		sound_bt.setFocusPainted(false);  //JButton�� ���� �Ǿ����� �׵θ� ������
		sound_bt.setContentAreaFilled(false); //JButton ä��� ����
		sound_bt.addActionListener(this); //�׼� ����
		mf.add(sound_bt);
	}
	private void Bt6() { //���� ��ư
		
		skin_bt = new JButton(gear);
		skin_bt.setBounds(15, 390, 40, 40);//��ư�� ��ġ �� ũ�� ����
		skin_bt.setBorderPainted(false); //JButton �ܰ��� �����
		skin_bt.setFocusPainted(false);  //JButton�� ���� �Ǿ����� �׵θ� ������
		skin_bt.setContentAreaFilled(false); //JButton ä��� ����
		skin_bt.addActionListener(this); //�׼� ����
		mf.add(skin_bt);
	}

	@Override
	public void actionPerformed(ActionEvent e) { //��ư �̺�Ʈ ó��
		if (e.getSource() == start_bt) {
			c.stop();
			c.close();
			mf.Intro_next(sound_on, skin); //���� ȭ������ �̵�
			// ��ư�� ������
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
			mf.Help_msg();// ���� ȭ������ �̵�
			// ��ư�� ������
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
			System.exit(0); //���� ����
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
			String[] sk = {"��","����"};
			String ski = (String) JOptionPane.showInputDialog(null, "��ֹ� ��Ų�� �����ϼ���", "��Ų ����", JOptionPane.PLAIN_MESSAGE, null, sk, sk[0]);
			if(ski==null)
				ski=skin;
			System.out.println(ski);
			skin=ski;
		}

	}

	@Override
	protected void paintComponent(Graphics g) { // �̹��� �۾�
		super.paintComponent(g);
		g.drawImage(Intimg, 0, 0, 640, 480, null); //��� �׸���
	}

	public static void main(String[] args) {

	}

}