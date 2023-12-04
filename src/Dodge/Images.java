package Dodge;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Images {
	private Image cha1;// 캐릭터이미지1
	private Image cha2;// 캐릭터이미지2
	private Image cha3;// 캐릭터이미지3
	private Image tis; // 휴지 아이템
	private Image dd;// 장애물
	private Image life;// 목숨 이미지
	private Image umb;// 우산 아이템
	private Image ucha1;// 우산캐릭터 이미지1
	private Image ucha2;// 우산캐릭터 이미지2
	private Image ucha3;// 우산캐릭터 이미지3
	private Image Game_bg1; //게임 레벨1 배경화면
	private Image Game_bg2; //게임 레벨2 배경화면
	private Image Game_bg3; //게임 레벨3 배경화면
	private Image A;
	private Image F;
	ImageIcon start; //시작 버튼
	ImageIcon start2; //시작 버튼(마우스 위치시)
	ImageIcon help; //도움말 버튼
	ImageIcon help2; //도움말 버튼(마우스 위치시)
	ImageIcon exit; //나가기 버튼
	ImageIcon exit2; //나가기 버튼(마우스 위치시)
	ImageIcon rank; //랭킹 버튼
	ImageIcon rank2; //랭킹 버튼(마우스 위치시)
	ImageIcon level1; //레벨1 스크린
	ImageIcon level2; //레벨2 스크린
	ImageIcon level3; //레벨3 스크린
	ImageIcon sound_o; //sound on
	ImageIcon sound_x; //sound off
	ImageIcon gear;
	
	public Images() {
		cha1 = Toolkit.getDefaultToolkit().getImage("images/char1.png"); //캐릭터 기본 이미지1 
        cha2 = Toolkit.getDefaultToolkit().getImage("images/char2.png"); //캐릭터 기본 이미지2
        cha3 = Toolkit.getDefaultToolkit().getImage("images/char3.png"); //캐릭터 기본 이미지3
        ucha1 = Toolkit.getDefaultToolkit().getImage("images/u1.png"); //우산캐릭터 이미지1
        ucha2 = Toolkit.getDefaultToolkit().getImage("images/u2.png"); //우산캐릭터 이미지2
        ucha3 = Toolkit.getDefaultToolkit().getImage("images/u3.png"); //우산캐릭터 이미지3
        tis = Toolkit.getDefaultToolkit().getImage("images/tissue.png"); //휴지아이템 이미지
        A = Toolkit.getDefaultToolkit().getImage("images/A.png"); //a 이미지
        dd = Toolkit.getDefaultToolkit().getImage("images/dd.png"); //장애물 이미지
        F = Toolkit.getDefaultToolkit().getImage("images/f.png"); //f 이미지
        life = Toolkit.getDefaultToolkit().getImage("images/heart.png"); // 목숨 이미지
        umb = Toolkit.getDefaultToolkit().getImage("images/umb.png"); // 우산아이템 이미지
		Game_bg1 = Toolkit.getDefaultToolkit().getImage("images/bg1.jpg");
		Game_bg2 = Toolkit.getDefaultToolkit().getImage("images/bg2.jpg");
		Game_bg3 = Toolkit.getDefaultToolkit().getImage("images/bg3.jpg");
        start = new ImageIcon("images/start.png");
        start2 = new ImageIcon("images/start2.png");
        exit = new ImageIcon("images/exit.png");
        exit2 = new ImageIcon("images/exit2.png");
        help = new ImageIcon("images/help.png");
        help2 = new ImageIcon("images/help2.png");
        rank = new ImageIcon("images/rank.png");
        rank2 = new ImageIcon("images/rank2.png");
        level1 = new ImageIcon("images/level1.png");
        level2 = new ImageIcon("images/level2.png");
        level3 = new ImageIcon("images/level3.png");
        sound_x = new ImageIcon("images/sound_x.png");
        sound_o = new ImageIcon("images/sound_o.png");
        gear = new ImageIcon("images/gear.png");
	}
	public Image getimg(String code) { //객체 이미지 리턴
		if(code.equals("cha1"))
			return cha1;
		else if(code.equals("cha2"))
			return cha2;
		else if(code.equals("cha3"))
			return cha3;
		else if(code.equals("ucha1"))
			return ucha1;
		else if(code.equals("ucha2"))
			return ucha2;
		else if(code.equals("ucha3"))
			return ucha3;
		else if(code.equals("tis"))
			return tis;
		else if(code.equals("dd"))
			return dd;
		else if(code.equals("life"))
			return life;
		else if(code.equals("umb"))
			return umb;
		else if(code.equals("A"))
			return A;
		else if(code.equals("F"))
			return F;
		else
			return cha1;
	}
	public ImageIcon geticon(String code) { //아이콘 리턴
		if(code.equals("start"))
			return start;
		else if(code.equals("start2"))
			return start2;
		else if(code.equals("help"))
			return help;
		else if(code.equals("help2"))
			return help2;
		else if(code.equals("rank"))
			return rank;
		else if(code.equals("rank2"))
			return rank2;
		else if(code.equals("exit"))
			return exit;
		else if(code.equals("exit2"))
			return exit2;
		else if(code.equals("level1"))
			return level1;
		else if(code.equals("level2"))
			return level2;
		else if(code.equals("level3"))
			return level3;
		else if(code.equals("sound_o"))
			return sound_o;
		else if(code.equals("sound_x"))
			return sound_x;
		else if(code.equals("gear"))
			return gear;
		else
			return start;
		
	}
	public Image getbg(String code) { //배경 리턴
		if(code.equals("Game_bg1"))
			return Game_bg1;
		else if(code.equals("Game_bg2"))
			return Game_bg2;
		else if(code.equals("Game_bg3"))
			return Game_bg3;
		else
			return Game_bg1;
		
	}
	
}