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
	Image cha; // 주인공 캐릭터 이미지 받기
	ImageIcon RIP= new ImageIcon("images/RIP.png"); //게임 끝나고 나오는 메세지에 들어가는 아이콘
	int l = 3; // 목숨
	int score = 0; //점수
	boolean gameover;//게임오버 체크
	static boolean mouse_ch;//마우스 사용 체크
	static int ucheck = 0; //우산 아이템 체크
	static boolean sound_on; //사운드 체크
	static int level; //난이도 체크
	private Image bg;//배경 이미지
	static Clip c;
	static String skin;
	private static Point man = new Point(320, 407); // 주인공 포인터 객체
	private ArrayList<Point> st = new ArrayList<Point>(); // 장애물 포인터 객체
	private ArrayList<Point> item1s = new ArrayList<Point>(); // 휴지 아이템 포인터 객체
	private ArrayList<Point> item2s = new ArrayList<Point>(); // 우산 아이템 포인터 객체

	
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
	
	class thread1 extends Thread { // 장애물 내려오는 스레드
		public void run() {
			while (gameover == false) {
				Random ran = new Random();
				int a = ran.nextInt(640); //장애물 생성 위치 랜덤 설정
				int b = ran.nextInt(640); //장애물 생성 위치 랜덤 설정
				st.add(new Point(a, 0));  //장애물 객체 생성
				if(level==2 || level==3) { //레벨이 2또는 3인 경우 장애물 추가
					st.add(new Point(b, 0));
				}
				try {
					Thread.sleep(500); //생성 주기 관리
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}
	class thread3 extends Thread { //레벨 3일 때 장애물 스레드 추가
		public void run() {
			while (gameover == false) {
				Random ran = new Random();
				int a = ran.nextInt(640); //장애물 생성 위치 랜덤 설정
				st.add(new Point(a, 0));  //장애물 객체 생성
				try {
					Thread.sleep(400); //생성 주기 관리
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}
	class thread2 extends Thread{ //아이템 내려오는 스레드
    	public void run(){
    		while(gameover == false) {
            	Random ran=new Random();
            	int a=ran.nextInt(640);
				if(a%2==0) // 아이템종류가 랜덤
					item1s.add(new Point(a, 0)); //휴지
				else
					item2s.add(new Point(a,0));	//우산
    			try {
    				Thread.sleep(8000); //8000 권장
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    				return;
    			}
    		}
    	}
    }

	public Play_Screen(Main_Frame mf, int is, boolean mouse_check, boolean sound, String ski) {
		new javax.swing.Timer(80, this).start();//80
		gameover = false; //게임 오버체크 디폴트 false
		level = is; //게임 난이도
		mouse_ch=mouse_check;
		sound_on=sound;
		skin=ski;
		this.mf = mf;
		System.out.println(mouse_ch);
		cha = im.getimg("cha1"); //캐릭터 기본 이미지
		this.setLayout(null); 
		this.addKeyListener(this);
		//난이도 별 배경 변경
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
		thread1 thd = new thread1(); // 장애물 스레드
//		thd.setDaemon(true);
		thd.start();
		thread2 thd2 = new thread2();// 아이템 스레드
//        thd2.setDaemon(true);
        thd2.start();
        if(level==3) {
        	thread3 thd3 = new thread3(); // 장애물 스레드
//    		thd3.setDaemon(true);
    		thd3.start();
        }
        
        
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			man.x -= 10;
			break; // 왼쪽으로 이동
		case KeyEvent.VK_RIGHT:
			man.x += 10;
			break; // 오른쪽으로 이동
		case KeyEvent.VK_ESCAPE:
			if(sound_on==true) {
				c.stop();
				c.close();}
				gameover = true;
				Game_Over();
				break;
		}
		if (man.x < 10) // 캐릭터가 창 밖으로 나가지 못하게 함
			man.x += 10;
		else if (man.x > 620)
			man.x -= 10;
		this.repaint();
		this.revalidate();
	}

	public void times(int i) { // 우산효과 메소드 매개변수 i는 캐릭터의 외형 상태를 결정
		if (i == 1)
			cha = im.getimg("ucha1");
		else if (i == 2)
			cha = im.getimg("ucha2");
		else if (i == 3)
			cha = im.getimg("ucha3");
		TimerTask ta = new TimerTask() { // 우산 효과 타이머 함수
			@Override
			public void run() {
				ucheck = 0; // 타이머가 끝나면 우산 효과가 끝남
			}
		};
		new Timer().schedule(ta, 3000);// 3초간 타이머 실행
	}

	public static void mouseread() {
		   Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); // 현재 모니터의 높이,넓이
		   int x= d.width; //모니터의 가로 길이
		   int y=d.height;
		   x=(x-640)/2; // 창과 모니터의 x=0 좌표의 거리
		   PointerInfo pt = MouseInfo.getPointerInfo();
		 if(mouse_ch==true) {
               pt = MouseInfo.getPointerInfo();
               man.x=pt.getLocation().x-x; //마우스의 위치에 따라 캐릭터의 위치 변화
               if((pt.getLocation().x-x)>=640) // 마우스가 창 밖에 있을 경우
             	  man.x=640;
               else if((pt.getLocation().x-x)<0) // 마우스가 창 밖에 있을 경우
             	  man.x=0;
		  }
		   
	   }
	
	
	public void actionPerformed(ActionEvent e) {
		mouseread();
		// -----------우산 아이템-------------
		Iterator<Point> uu = item2s.iterator();
		while (uu.hasNext() && gameover == false) {
			Point item = uu.next();
			item.y += 20; // 우산 아이템의 y좌표가 20씩 내려옴
			if (item.y > 457) // 아이템이 바닥에 닿을 경우
				uu.remove(); // 아이템 삭제
			else if (item.y > man.y - 40 && (item.x <= man.x + 22 && item.x > man.x - 22)) { // 아이템이 캐릭터에 닿을 경우
				ucheck = 1; // 우산 아이템 효과 적용
				uu.remove();// 아이템 삭제
			}

		}
		// ----------휴지 아이템------------
		Iterator<Point> tt = item1s.iterator();
		while (tt.hasNext() && gameover == false) {
			Point item1 = tt.next();
			item1.y += 10; // 휴지 아이템의 y좌표가 10씩 내려옴
			if (item1.y > 457) // 아이템이 바닥에 닿을 경우
				tt.remove();// 아이템 삭제
			else if (item1.y > man.y - 45 && (item1.x <= man.x + 22 && item1.x > man.x - 22)) { // 아이템이 캐릭터에 닿을 경우
				tt.remove();// 아이템 삭제
				l++;
			}
		}
		// --------------장애물--------------
		Iterator<Point> it = st.iterator();
		while (it.hasNext() && gameover == false) {
			Point st = it.next();
			st.y += 10; // 장애물이 10의 속도롤 떨어짐
			if (st.y > 450) { // 장애물이 바닥에 닿을 경우
				it.remove(); // 장애물이 사라짐
				score += 10;
			}
			else if (st.y > man.y - 45 && (st.x - 5 <= man.x + 20 && st.x + 5 > man.x - 28)) { // 장애물이 캐릭터에 닿을 경우
				it.remove(); // 장애물 삭제
				score += 100; // 장애물을 맞을 경우 점수가 100올라감
				if(ucheck==0)
					l--;
			}
			// --------구간별 속도 조절------------
			else if (st.y > 10 && st.y < 100) {
				st.y += 15;
			} else if (st.y >= 100 && st.y < 200) {
				st.y += 25;
			} else if (st.y >= 200 && st.y < 250) {
				st.y += 30;
			} else if (st.y >= 250) {
				st.y += 35;
			}
			if (l >= 3) { // life 가 3일 경우
				cha = im.getimg("cha1");
				l = 3;
				if (ucheck == 1)
					times(1); // 우산 효과
			} else if (l == 2) {// life 가 2일 경우
				cha = im.getimg("cha2");
				if (ucheck == 1)
					times(2);// 우산 효과

			} else if (l <= 1) {// life 가 1일 경우
				cha = im.getimg("cha3");
				if (ucheck == 1)
					times(3);// 우산 효과

				if (l <= 0) { //life가 0일 경우 게임 오버
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

	public void Game_Over() { // 게임 끝나는 메소드
		int GO_msg = JOptionPane.showConfirmDialog(null, "Game Over\nscore : " + score + "\n점수를 등록하시겠습니까?", "안타깝네요!",
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, RIP); //점수 등록 여부
		if (GO_msg == 0) { // 선택화면 이동
			String Id = JOptionPane.showInputDialog(null, "SCORE : " + score + "\n등록할 이름을 입력하세요", "랭킹 입력",
					JOptionPane.PLAIN_MESSAGE); //랭킹 등록 패널
			if (Id != null) { //점수 입력
				if(Id.equals("")) {
					Id="익명";
				}
				System.out.println("score : " + score + "\nname : " + Id+"\nlevel : "+level); //이 부분은 나중에 랭킹등록하는 메소드 호출로 변경
				db.inserts(Id,score,level);
			}
			
		}
		int GO_msg2 = JOptionPane.showConfirmDialog(null, "Try Again?", "다시할래?", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE); // 재시작 여부
		if (GO_msg2 == 0) {// yes 선택시, 선택 화면 이동
			mf.Game_End(2,sound_on,skin); 
		} else if (GO_msg2 == -1 || GO_msg2 == 1) { // no 또는 x버튼 선택시, 메뉴 화면 이동
			mf.Game_End(1,sound_on,skin);
		}

	}

	public void paintComponent(Graphics g) { //화면 그리기
		g.drawImage(bg, 0, 0, 640, 480, this); //배경 그리기
		g.drawImage(cha, man.x - im.getimg("cha1").getWidth(this) / 2, man.y - im.getimg("cha1").getHeight(this) / 2,
				this); // 캐릭터 그리기(객체, x좌표, y좌표)
		g.setColor(Color.black); // 글씨 색상
		g.drawImage(im.getimg("life"), 10, 5, this); // life 이미지 그리기
		if (skin.equals("변")) {
			for (Point item1 : item1s) // 휴지 아이템 그리기
				g.drawImage(im.getimg("tis"), item1.x, item1.y, this);
			for (Point sts : st) // 장애물 그리기
				g.drawImage(im.getimg("dd"), sts.x, sts.y, this);
		}
		else if(skin.equals("학점")) {
			for (Point item1 : item1s) // 휴지 아이템 그리기
				g.drawImage(im.getimg("A"), item1.x, item1.y, this);
			for (Point sts : st) // 장애물 그리기
				g.drawImage(im.getimg("F"), sts.x, sts.y, this);
		}
		for (Point item2 : item2s) // 우산 아이템 그리기
			g.drawImage(im.getimg("umb"), item2.x, item2.y, this);
		
		g.setFont(new Font(null, Font.BOLD, 10)); // 글씨 폰트 및 크기 설정
		g.drawString("Score : " + score, 10, 30); // 점수 표시
		g.drawString("×" + l, 28, 15); // 점수 표시
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	public static void main(String[] args) {}

}