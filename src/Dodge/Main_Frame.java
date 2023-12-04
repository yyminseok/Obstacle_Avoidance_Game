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
		this.setSize(640, 480); //frame 사이즈 지정
		this.setTitle("Dodge");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x 누르면 프로그램을 종료하도록
		this.setResizable(false); // 사이즈 변경 못하게 고정
		this.setLocationRelativeTo(null); // 스크린의 중앙에 뜨게 함
		sound_on=true;
		skin="변";
		Go_main();
		this.setVisible(true);
		new Server();
	}

	public void Go_main() { //첫 번째 페이지로 이동
		ms = new Menu_Screen(this, sound_on, skin);
		this.add(ms);
	}
	public void Help_msg() {// 도움말 페이지로 이동
		remove(ms);
		hs = new Help_Screen(this);
		this.add(hs);
		this.repaint();
		this.revalidate();
	}
	public void Help_next() {//도움말에서 첫 번째 페이지로 이동
		remove(hs);
		this.repaint();
		this.revalidate();
		Go_main();
	}
	public void Choose_back() { //선택 페이지에서 첫 번째 페이지로 이동
		remove(cs);
		this.repaint();
		this.revalidate();
		Go_main();
	}
	public void Intro_next(boolean sound, String ski) {//첫 번째 페이지에서 선택 페이지로 이동
		sound_on=sound;
		skin=ski;
		remove(ms);
		cs = new Choice_Screen(this, sound_on);
		this.add(cs);
		this.repaint();
		this.revalidate();
	}
	public void Choose_next(int level, boolean mouse) {//선택 페이지에서 게임실행 페이지로 이동
		this.remove(cs);
		is=level;
		ps=new Play_Screen(this, is, mouse, sound_on, skin);
		this.add(ps); 
		ps.setFocusable(true); //포커스 추가!
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
