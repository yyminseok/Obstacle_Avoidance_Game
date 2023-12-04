package Dodge;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

public class Play_Screen extends JPanel implements KeyListener, ActionListener {
	Main_Frame mf;
	DataBase db;
	Images im = new Images(); 
	Image cha; // ���ΰ� ĳ���� �̹��� �ޱ�
	ImageIcon RIP= new ImageIcon("images/RIP.png"); //���� ������ ������ �޼����� ���� ������
	int l = 3; // ���
	int score = 0; //����
	boolean gameover;//���ӿ��� üũ
	static boolean mouse_ch;//���콺 ��� üũ
	static int ucheck = 0; //��� ������ üũ
	static boolean sound_on; //���� üũ
	static int level; //���̵� üũ
	private Image bg;//��� �̹���
	static Clip c;
	static String skin;
	private static Point man = new Point(320, 407); // ���ΰ� ������ ��ü
	private ArrayList<Point> st = new ArrayList<Point>(); // ��ֹ� ������ ��ü
	private ArrayList<Point> item1s = new ArrayList<Point>(); // ���� ������ ������ ��ü
	private ArrayList<Point> item2s = new ArrayList<Point>(); // ��� ������ ������ ��ü

	
	public class Music_class extends Thread {
		File a;
    	public void run() {
    		try {
    			while (gameover==false) {
    				if(level==1) {
    					a = new File("music\\level1.wav");}
    				else if(level==2) {
    				    a = new File("music\\level2.wav");}
    				else if(level==3) {
    					a = new File("music\\level3.wav");}
    				
    				AudioInputStream b = AudioSystem.getAudioInputStream(a);
    				c = AudioSystem.getClip();
    				if(sound_on==true) {
    				c.open(b);
    				c.start();}
    				Thread.sleep(c.getMicrosecondLength() / 1000);
    			}
    		} catch (Exception e) {
    			return;
    		}
    		
    	}
    }
	
	class thread1 extends Thread { // ��ֹ� �������� ������
		public void run() {
			while (gameover == false) {
				Random ran = new Random();
				int a = ran.nextInt(640); //��ֹ� ���� ��ġ ���� ����
				int b = ran.nextInt(640); //��ֹ� ���� ��ġ ���� ����
				st.add(new Point(a, 0));  //��ֹ� ��ü ����
				if(level==2 || level==3) { //������ 2�Ǵ� 3�� ��� ��ֹ� �߰�
					st.add(new Point(b, 0));
				}
				try {
					Thread.sleep(500); //���� �ֱ� ����
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}
	class thread3 extends Thread { //���� 3�� �� ��ֹ� ������ �߰�
		public void run() {
			while (gameover == false) {
				Random ran = new Random();
				int a = ran.nextInt(640); //��ֹ� ���� ��ġ ���� ����
				st.add(new Point(a, 0));  //��ֹ� ��ü ����
				try {
					Thread.sleep(400); //���� �ֱ� ����
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}
	class thread2 extends Thread{ //������ �������� ������
    	public void run(){
    		while(gameover == false) {
            	Random ran=new Random();
            	int a=ran.nextInt(640);
				if(a%2==0) // ������������ ����
					item1s.add(new Point(a, 0)); //����
				else
					item2s.add(new Point(a,0));	//���
    			try {
    				Thread.sleep(8000); //8000 ����
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    				return;
    			}
    		}
    	}
    }

	public Play_Screen(Main_Frame mf, int is, boolean mouse_check, boolean sound, String ski) {
		new javax.swing.Timer(80, this).start();//80
		gameover = false; //���� ����üũ ����Ʈ false
		level = is; //���� ���̵�
		mouse_ch=mouse_check;
		sound_on=sound;
		skin=ski;
		this.mf = mf;
		System.out.println(mouse_ch);
		cha = im.getimg("cha1"); //ĳ���� �⺻ �̹���
		this.setLayout(null); 
		this.addKeyListener(this);
		//���̵� �� ��� ����
		if(level==1) 
			bg=im.getbg("Game_bg1");
		else if(level==2)
			bg=im.getbg("Game_bg2");
		else if(level==3)
			bg=im.getbg("Game_bg3");
		
		if (sound_on == true) {
			Music_class music = new Music_class();
			music.start();
		}
		thread1 thd = new thread1(); // ��ֹ� ������
//		thd.setDaemon(true);
		thd.start();
		thread2 thd2 = new thread2();// ������ ������
//        thd2.setDaemon(true);
        thd2.start();
        if(level==3) {
        	thread3 thd3 = new thread3(); // ��ֹ� ������
//    		thd3.setDaemon(true);
    		thd3.start();
        }
        
        
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			man.x -= 10;
			break; // �������� �̵�
		case KeyEvent.VK_RIGHT:
			man.x += 10;
			break; // ���������� �̵�
		case KeyEvent.VK_ESCAPE:
			if(sound_on==true) {
				c.stop();
				c.close();}
				gameover = true;
				Game_Over();
				break;
		}
		if (man.x < 10) // ĳ���Ͱ� â ������ ������ ���ϰ� ��
			man.x += 10;
		else if (man.x > 620)
			man.x -= 10;
		this.repaint();
		this.revalidate();
	}

	public void times(int i) { // ���ȿ�� �޼ҵ� �Ű����� i�� ĳ������ ���� ���¸� ����
		if (i == 1)
			cha = im.getimg("ucha1");
		else if (i == 2)
			cha = im.getimg("ucha2");
		else if (i == 3)
			cha = im.getimg("ucha3");
		TimerTask ta = new TimerTask() { // ��� ȿ�� Ÿ�̸� �Լ�
			@Override
			public void run() {
				ucheck = 0; // Ÿ�̸Ӱ� ������ ��� ȿ���� ����
			}
		};
		new Timer().schedule(ta, 3000);// 3�ʰ� Ÿ�̸� ����
	}

	public static void mouseread() {
		   Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); // ���� ������� ����,����
		   int x= d.width; //������� ���� ����
		   int y=d.height;
		   x=(x-640)/2; // â�� ������� x=0 ��ǥ�� �Ÿ�
		   PointerInfo pt = MouseInfo.getPointerInfo();
		 if(mouse_ch==true) {
               pt = MouseInfo.getPointerInfo();
               man.x=pt.getLocation().x-x; //���콺�� ��ġ�� ���� ĳ������ ��ġ ��ȭ
               if((pt.getLocation().x-x)>=640) // ���콺�� â �ۿ� ���� ���
             	  man.x=640;
               else if((pt.getLocation().x-x)<0) // ���콺�� â �ۿ� ���� ���
             	  man.x=0;
		  }
		   
	   }
	
	
	public void actionPerformed(ActionEvent e) {
		mouseread();
		// -----------��� ������-------------
		Iterator<Point> uu = item2s.iterator();
		while (uu.hasNext() && gameover == false) {
			Point item = uu.next();
			item.y += 20; // ��� �������� y��ǥ�� 20�� ������
			if (item.y > 457) // �������� �ٴڿ� ���� ���
				uu.remove(); // ������ ����
			else if (item.y > man.y - 40 && (item.x <= man.x + 22 && item.x > man.x - 22)) { // �������� ĳ���Ϳ� ���� ���
				ucheck = 1; // ��� ������ ȿ�� ����
				uu.remove();// ������ ����
			}

		}
		// ----------���� ������------------
		Iterator<Point> tt = item1s.iterator();
		while (tt.hasNext() && gameover == false) {
			Point item1 = tt.next();
			item1.y += 10; // ���� �������� y��ǥ�� 10�� ������
			if (item1.y > 457) // �������� �ٴڿ� ���� ���
				tt.remove();// ������ ����
			else if (item1.y > man.y - 45 && (item1.x <= man.x + 22 && item1.x > man.x - 22)) { // �������� ĳ���Ϳ� ���� ���
				tt.remove();// ������ ����
				l++;
			}
		}
		// --------------��ֹ�--------------
		Iterator<Point> it = st.iterator();
		while (it.hasNext() && gameover == false) {
			Point st = it.next();
			st.y += 10; // ��ֹ��� 10�� �ӵ��� ������
			if (st.y > 450) { // ��ֹ��� �ٴڿ� ���� ���
				it.remove(); // ��ֹ��� �����
				score += 10;
			}
			else if (st.y > man.y - 45 && (st.x - 5 <= man.x + 20 && st.x + 5 > man.x - 28)) { // ��ֹ��� ĳ���Ϳ� ���� ���
				it.remove(); // ��ֹ� ����
				score += 100; // ��ֹ��� ���� ��� ������ 100�ö�
				if(ucheck==0)
					l--;
			}
			// --------������ �ӵ� ����------------
			else if (st.y > 10 && st.y < 100) {
				st.y += 15;
			} else if (st.y >= 100 && st.y < 200) {
				st.y += 25;
			} else if (st.y >= 200 && st.y < 250) {
				st.y += 30;
			} else if (st.y >= 250) {
				st.y += 35;
			}
			if (l >= 3) { // life �� 3�� ���
				cha = im.getimg("cha1");
				l = 3;
				if (ucheck == 1)
					times(1); // ��� ȿ��
			} else if (l == 2) {// life �� 2�� ���
				cha = im.getimg("cha2");
				if (ucheck == 1)
					times(2);// ��� ȿ��

			} else if (l <= 1) {// life �� 1�� ���
				cha = im.getimg("cha3");
				if (ucheck == 1)
					times(3);// ��� ȿ��

				if (l <= 0) { //life�� 0�� ��� ���� ����
					if(sound_on==true) {
					c.stop();
					c.close();}
					gameover = true;
					Game_Over();
					if(gameover==true)
						break;
				}
			}
			repaint();
		}
	}

	public void Game_Over() { // ���� ������ �޼ҵ�
		int GO_msg = JOptionPane.showConfirmDialog(null, "Game Over\nscore : " + score + "\n������ ����Ͻðڽ��ϱ�?", "��Ÿ���׿�!",
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, RIP); //���� ��� ����
		if (GO_msg == 0) { // ����ȭ�� �̵�
			String Id = JOptionPane.showInputDialog(null, "SCORE : " + score + "\n����� �̸��� �Է��ϼ���", "��ŷ �Է�",
					JOptionPane.PLAIN_MESSAGE); //��ŷ ��� �г�
			if (Id != null) { //���� �Է�
				if(Id.equals("")) {
					Id="�͸�";
				}
				System.out.println("score : " + score + "\nname : " + Id+"\nlevel : "+level); //�� �κ��� ���߿� ��ŷ����ϴ� �޼ҵ� ȣ��� ����
				db.inserts(Id,score,level);
			}
			
		}
		int GO_msg2 = JOptionPane.showConfirmDialog(null, "Try Again?", "�ٽ��ҷ�?", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE); // ����� ����
		if (GO_msg2 == 0) {// yes ���ý�, ���� ȭ�� �̵�
			mf.Game_End(2,sound_on,skin); 
		} else if (GO_msg2 == -1 || GO_msg2 == 1) { // no �Ǵ� x��ư ���ý�, �޴� ȭ�� �̵�
			mf.Game_End(1,sound_on,skin);
		}

	}

	public void paintComponent(Graphics g) { //ȭ�� �׸���
		g.drawImage(bg, 0, 0, 640, 480, this); //��� �׸���
		g.drawImage(cha, man.x - im.getimg("cha1").getWidth(this) / 2, man.y - im.getimg("cha1").getHeight(this) / 2,
				this); // ĳ���� �׸���(��ü, x��ǥ, y��ǥ)
		g.setColor(Color.black); // �۾� ����
		g.drawImage(im.getimg("life"), 10, 5, this); // life �̹��� �׸���
		if (skin.equals("��")) {
			for (Point item1 : item1s) // ���� ������ �׸���
				g.drawImage(im.getimg("tis"), item1.x, item1.y, this);
			for (Point sts : st) // ��ֹ� �׸���
				g.drawImage(im.getimg("dd"), sts.x, sts.y, this);
		}
		else if(skin.equals("����")) {
			for (Point item1 : item1s) // ���� ������ �׸���
				g.drawImage(im.getimg("A"), item1.x, item1.y, this);
			for (Point sts : st) // ��ֹ� �׸���
				g.drawImage(im.getimg("F"), sts.x, sts.y, this);
		}
		for (Point item2 : item2s) // ��� ������ �׸���
			g.drawImage(im.getimg("umb"), item2.x, item2.y, this);
		
		g.setFont(new Font(null, Font.BOLD, 10)); // �۾� ��Ʈ �� ũ�� ����
		g.drawString("Score : " + score, 10, 30); // ���� ǥ��
		g.drawString("��" + l, 28, 15); // ���� ǥ��
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	public static void main(String[] args) {}

}