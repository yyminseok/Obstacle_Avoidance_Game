package Dodge;

import java.io.IOException;

import javax.swing.JFrame;

public class Main_Frame extends JFrame {
	Menu_Screen ms;
	Choice_Screen cs;
	Play_Screen ps;
	Help_Screen hs;
	Images im=new Images();
	static boolean sound_on;
	static String skin;
	int is;
	public Main_Frame() throws IOException {
		this.setSize(640, 480); //frame ������ ����
		this.setTitle("Dodge");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x ������ ���α׷��� �����ϵ���
		this.setResizable(false); // ������ ���� ���ϰ� ����
		this.setLocationRelativeTo(null); // ��ũ���� �߾ӿ� �߰� ��
		sound_on=true;
		skin="��";
		Go_main();
		this.setVisible(true);
		new Server();
	}

	public void Go_main() { //ù ��° �������� �̵�
		ms = new Menu_Screen(this, sound_on, skin);
		this.add(ms);
	}
	public void Help_msg() {// ���� �������� �̵�
		remove(ms);
		hs = new Help_Screen(this);
		this.add(hs);
		this.repaint();
		this.revalidate();
	}
	public void Help_next() {//���򸻿��� ù ��° �������� �̵�
		remove(hs);
		this.repaint();
		this.revalidate();
		Go_main();
	}
	public void Choose_back() { //���� ���������� ù ��° �������� �̵�
		remove(cs);
		this.repaint();
		this.revalidate();
		Go_main();
	}
	public void Intro_next(boolean sound, String ski) {//ù ��° ���������� ���� �������� �̵�
		sound_on=sound;
		skin=ski;
		remove(ms);
		cs = new Choice_Screen(this, sound_on);
		this.add(cs);
		this.repaint();
		this.revalidate();
	}
	public void Choose_next(int level, boolean mouse) {//���� ���������� ���ӽ��� �������� �̵�
		this.remove(cs);
		is=level;
		ps=new Play_Screen(this, is, mouse, sound_on, skin);
		this.add(ps); 
		ps.setFocusable(true); //��Ŀ�� �߰�!
		ps.requestFocus(); 
		this.repaint(); 
		this.revalidate();
	}
	public void Game_End(int menu,boolean sound, String ski) {
		remove(ps);
		skin=ski;
		this.repaint();
		this.revalidate();
		if(menu==1) {
			Go_main();}
		else if(menu==2) {
			Intro_next(sound, skin);}
	}

	public static void main(String[] args) throws IOException {
		new Main_Frame();
		
	}

	

}
