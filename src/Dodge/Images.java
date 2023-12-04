package Dodge;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Images {
	private Image cha1;// ĳ�����̹���1
	private Image cha2;// ĳ�����̹���2
	private Image cha3;// ĳ�����̹���3
	private Image tis; // ���� ������
	private Image dd;// ��ֹ�
	private Image life;// ��� �̹���
	private Image umb;// ��� ������
	private Image ucha1;// ���ĳ���� �̹���1
	private Image ucha2;// ���ĳ���� �̹���2
	private Image ucha3;// ���ĳ���� �̹���3
	private Image Game_bg1; //���� ����1 ���ȭ��
	private Image Game_bg2; //���� ����2 ���ȭ��
	private Image Game_bg3; //���� ����3 ���ȭ��
	private Image A;
	private Image F;
	ImageIcon start; //���� ��ư
	ImageIcon start2; //���� ��ư(���콺 ��ġ��)
	ImageIcon help; //���� ��ư
	ImageIcon help2; //���� ��ư(���콺 ��ġ��)
	ImageIcon exit; //������ ��ư
	ImageIcon exit2; //������ ��ư(���콺 ��ġ��)
	ImageIcon rank; //��ŷ ��ư
	ImageIcon rank2; //��ŷ ��ư(���콺 ��ġ��)
	ImageIcon level1; //����1 ��ũ��
	ImageIcon level2; //����2 ��ũ��
	ImageIcon level3; //����3 ��ũ��
	ImageIcon sound_o; //sound on
	ImageIcon sound_x; //sound off
	ImageIcon gear;
	
	public Images() {
		cha1 = Toolkit.getDefaultToolkit().getImage("images/char1.png"); //ĳ���� �⺻ �̹���1 
        cha2 = Toolkit.getDefaultToolkit().getImage("images/char2.png"); //ĳ���� �⺻ �̹���2
        cha3 = Toolkit.getDefaultToolkit().getImage("images/char3.png"); //ĳ���� �⺻ �̹���3
        ucha1 = Toolkit.getDefaultToolkit().getImage("images/u1.png"); //���ĳ���� �̹���1
        ucha2 = Toolkit.getDefaultToolkit().getImage("images/u2.png"); //���ĳ���� �̹���2
        ucha3 = Toolkit.getDefaultToolkit().getImage("images/u3.png"); //���ĳ���� �̹���3
        tis = Toolkit.getDefaultToolkit().getImage("images/tissue.png"); //���������� �̹���
        A = Toolkit.getDefaultToolkit().getImage("images/A.png"); //a �̹���
        dd = Toolkit.getDefaultToolkit().getImage("images/dd.png"); //��ֹ� �̹���
        F = Toolkit.getDefaultToolkit().getImage("images/f.png"); //f �̹���
        life = Toolkit.getDefaultToolkit().getImage("images/heart.png"); // ��� �̹���
        umb = Toolkit.getDefaultToolkit().getImage("images/umb.png"); // �������� �̹���
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
	public Image getimg(String code) { //��ü �̹��� ����
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
	public ImageIcon geticon(String code) { //������ ����
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
	public Image getbg(String code) { //��� ����
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