package 프로젝트;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.border.*;

public class myframe extends JFrame {
   static NodeMake nodemake = new NodeMake(); // 트리는 모든 클래스 사용하므로 트리를 만드는 nodemake를 전역변수로 설정
   JPanel p1 = new JPanel(); // 좌측 패널
   static JPanel p3 = new JPanel(); // 중앙 패널 
   JPanel p5 = new JPanel(); // 좌측 패널
   static JTextArea p2 = new JTextArea(10, 20); // 텍스트 페인
   static drawMap p4 = new drawMap(); // JPanel을 상속받은 그래픽(연결선)을 그릴수 있는 마인드맵 페인 
   static JPanel p6 = new JPanel(); // 속성 페인
   static JTextField [] input = new JTextField[6]; // 속성페인에 부착될 텍스트페인
   ButtonListener buttonlistener1 = new ButtonListener(0); // 적용, 변경 버튼에 부착될 버튼리스너
   static  JMenuItem [] MenuItem;
   
   myframe(){
   setTitle("프로젝트 실행");
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임을 닫으면 프로그램 종료
   
   createMenu(); // 메뉴바 생성
   createTool(); // 툴바 생성
   splitPane(); // 프레임에 텍스트페인, 마인드맵 페인, 속성페인으로 나누어서 붙임
   TextEditorPane(); 
   MindMapPane();
   AttributePane();
   setSize(1200,800); // 프레임 크기 설정
   setVisible(true); // 창 띄우기
   }
   void splitPane() { // 먼저 프레임을 두개로 나누고 나눈것의 왼쪽 페인을 다시 두개로 나누어 총 3개의 페인으로 나눈다
      JSplitPane split1 = new JSplitPane();
      JSplitPane split2 = new JSplitPane();
      split1.setOrientation(JSplitPane.HORIZONTAL_SPLIT); // 가로로 나누기
      split2.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
      getContentPane().add(split1);
      split1.setRightComponent(split2);
      split1.setDividerLocation(300);
      split2.setDividerLocation(600);
      split1.setDividerSize(1); // 구분선 사이즈 설정
      split2.setDividerSize(1);
      p1.setBackground(Color.WHITE); // 나눈 3개의 페널 배경색 설정
      p3.setBackground(Color.WHITE);
      p5.setBackground(Color.WHITE);
      split1.setLeftComponent(p1);
      split2.setLeftComponent(p3);
      split2.setRightComponent(p5);
   }
   void createMenu() {
	      JMenuBar menubar = new JMenuBar();
	      JMenu Menu = new JMenu("Menu");
	      menubar.add(Menu);
	      MenuItem = new JMenuItem[7];
	      String [] MenuName = {"새로 만들기", "열기", "저장", "다른 이름으로 저장", "적용", "변경", "닫기"};
	      for(int i=0; i<7; i++) {
	         MenuItem[i] = new JMenuItem(MenuName[i]);
	         if(i==4||i==5)
	        	 MenuItem[i].addActionListener(buttonlistener1);
	         else
	        	 MenuItem[i].addActionListener(new MyActionListener(i)); 
	         Menu.add(MenuItem[i]);
	      }
	      setJMenuBar(menubar);
	   }
   void createTool() {
      JToolBar toolbar = new JToolBar("Kitae Menu"); // 먼저 툴바를 만들고
      toolbar.setBackground(Color.LIGHT_GRAY); // 툴바 색 설정
      
      String [] imgName = {"new.jpg", "open.jpg", "save.jpg", "another_save.jpg", "adapt.jpg", "change.jpg", "close.jpg"};
      String [] buttonTip = {"새로만들기", "열기", "저장", "다른 이름으로 저장", "적용", "변경", "종료"};
      JButton [] button = new JButton[7];
      for(int i=0; i<7;i++) {
         button[i] = new JButton(new ImageIcon(imgName[i])); // 이미지를 불러와서 툴바에 버튼 생성
         button[i].setToolTipText(buttonTip[i]); // 툴팁 생성
         if(i==4||i==5)
        	 button[i].addActionListener(buttonlistener1);
         else	 
        	 button[i].addActionListener(new MyActionListener(i)); //이미지버튼에 해당 기능 설정
         toolbar.add(button[i]);
      }
        this.add(toolbar, BorderLayout.NORTH); // 툴바를 프레임의 북쪽(상단)에 위치시킴
        //this.setSize(500, 500); // 
        this.setVisible(true);
   }
   void TextEditorPane() { 
      p1.setLayout(new BorderLayout()); // 배치관리자 설정
      
      JLabel title1 = new JLabel("Text Editor Pane"); // 페인 이름 붙이기
      AbstractBorder border = new LineBorder(Color.BLACK); // 테두리 설정
      title1.setBorder(border);
      title1.setFont(getFont().deriveFont(25.0f)); // 글자 폰트 설정
      title1.setForeground(Color.BLACK); // 글자색 설정
      title1.setHorizontalAlignment(SwingConstants.CENTER); // 글자 가운데 정렬
      p1.add(title1, BorderLayout.NORTH); // 이름 라벨을 페인의 상단에 부착
      
      p2.setBackground(Color.WHITE); // 실제 글자를 입력하는 텍스트 페인
        JScrollPane scroll = new JScrollPane(p2); // 텍스트 페인에 스크롤 바 설정
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // 세로 스크롤 바 설정
      scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // 가로 스크롤 바 설정
      p1.add(scroll, BorderLayout.CENTER);
      
      JButton button1 = new JButton("적용"); // 적용 버튼 생성
         button1.setBackground(Color.RED); // 색 설정
         button1.setFont(getFont().deriveFont(30.0f)); // 글자 폰트 설정
         p1.add(button1, BorderLayout.SOUTH); // 하단에 부착
         button1.addActionListener(buttonlistener1); // 버튼을 누르면 실행되는 이벤트리스너 부착
   }
   void MindMapPane() {
      p3.setLayout(new BorderLayout());
      
      JLabel title2 = new JLabel("Mind Map Pane");
      AbstractBorder border = new LineBorder(Color.BLACK);
      title2.setBorder(border);
      title2.setFont(getFont().deriveFont(25.0f));
      title2.setForeground(Color.BLACK);
      title2.setHorizontalAlignment(SwingConstants.CENTER);
      p3.add(title2, BorderLayout.NORTH);
      
      p4.setBackground(Color.WHITE);
      p3.add(p4, BorderLayout.CENTER);
      
      mouseListener ml = new mouseListener(); // 마우스리스너 생성
       p4.addMouseListener(ml); // 마인드맵 페인에 마우스리스너 부착
       p4.addMouseMotionListener(ml); // 마인드맵 페인에 마우스모던리스너 부착
   }
   void AttributePane() {
      p5.setLayout(new BorderLayout());
      
      JLabel title3 = new JLabel("Attribute Pane");
      AbstractBorder border = new LineBorder(Color.BLACK);
      title3.setBorder(border);
      title3.setFont(getFont().deriveFont(25.0f));
      title3.setForeground(Color.BLACK);
      title3.setHorizontalAlignment(SwingConstants.CENTER);
      p5.add(title3, BorderLayout.NORTH);
      
      p6.setBackground(Color.GRAY);
      p5.add(p6, BorderLayout.CENTER);
      GridLayout grid = new GridLayout(6, 2); // Grid 배치관리자 설정
      grid.setVgap(20); // 위 아래 사이값 설정
      p6.setLayout(grid);
      
      JLabel [] attr = new JLabel[6];
      for(int i=0; i<6; i++)
         if(i == 0) input[i] = new JTextField(" "); // 정보를 입력받을 텍스트필드 설정
         else input[i] = new JTextField("0"); // 0으로 초기화
      String [] attName = {"TEXT:", "X:", "Y:", "W:", "H:", "Color:"};
      for(int i=0; i<6; i++) {
         attr[i] = new JLabel(attName[i]);
         attr[i].setFont(getFont().deriveFont(25.0f));
         attr[i].setHorizontalAlignment(SwingConstants.CENTER);
         p6.add(attr[i]);
         p6.add(input[i]); 
         if(i == 0) { // 이름은 사용자가 못 고치도록
            input[i].setBackground(Color.BLACK); // 텍스트필드의 배경색 설정
            input[i].setEnabled(false); // 사용자가 못 고치도록 설정
         }
      }
      JButton button2 = new JButton("변경"); // 변경 버튼 부착
        button2.setBackground(Color.RED);
        button2.setFont(getFont().deriveFont(30.0f));
        p5.add(button2, BorderLayout.SOUTH);
        button2.addActionListener(buttonlistener1); // 버튼을 누르면 실행되는 이벤트리스너 부착
   }
   public static void main(String[] args) {
      myframe frame = new myframe();
   }
}

class drawMap extends JPanel{
   Node[] parent = new Node[100];
   Node[] sibling = new Node[100];
   int num;
   public void paintComponent(Graphics g) {
      NodeMake treemake = myframe.nodemake;
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setColor(Color.RED); // 연결선 색깔 설정
      g2.setStroke(new BasicStroke(5,BasicStroke.CAP_ROUND,0)); // 연결선 굵기 설정
      for(int i=0;i<num;i++) // 연결선 그리기
         g.drawLine(parent[i].getx()+parent[i].getw()*1/2, parent[i].gety()+parent[i].geth()*1/2,
               sibling[i].getx()+sibling[i].getw()*1/2,sibling[i].gety()+sibling[i].geth()*1/2);
   }
}
class mouseListener implements MouseListener, MouseMotionListener{
   int i = -1; // node의 인덱스 번호 
   int x, y;
   Node selectednode;
   JLabel selectedlabel;
   int [] signal = new int[2]; // 0: (선택,) 이동, 1: 크기조절 -> drag할때 경우를 나누기 위한 것이므로 라벨을 선택(drag호출x)할 경우는 생각하지 않음 
   NodeMake realnode = myframe.nodemake;
   int err = 10; // 마우스 인식범위 오차
   
   public void mousePressed(MouseEvent e) {
      x = e.getX();
      y = e.getY();
      for(int j=0; j<ButtonListener.cnt; j++)
         if(x >= realnode.node[j].getx()-err && x <= realnode.node[j].getx()+realnode.node[j].getw()+err
         && y >= realnode.node[j].gety()-err && y <= realnode.node[j].gety()+realnode.node[j].geth()+err) { //마우스로 라벨에 무언가(선택, 이동, 크기조절)를 하고싶을때
            i = j;
            selectednode = realnode.node[i];
            selectedlabel= selectednode.la;
            if(x > realnode.node[j].getx()+err && x < realnode.node[j].getx()+realnode.node[j].getw()-err
                  && y > realnode.node[j].gety()+err && y < realnode.node[j].gety()+realnode.node[j].geth()-err) signal[0] = 1; // 라벨이동을 하고 싶을때
            else signal[1] = 1; // 라벨 크기조절을 하고 싶을때
            break;
         }
   }
   public void mouseDragged(MouseEvent e) {
      int mousex = e.getX();
      int mousey = e.getY();
      if(signal[0] == 1) { // 위치이동
         selectedlabel.setLocation(mousex, mousey); //x, y 재설정
         selectednode.setx(mousex);
         selectednode.sety(mousey);
         myframe.input[0].setText(selectednode.getData()); // 속성페인에 나타나는 정보 업데이트
         myframe.input[1].setText(Integer.toString(selectednode.getx()));
         myframe.input[2].setText(Integer.toString(selectednode.gety()));
         myframe.input[3].setText(Integer.toString(selectednode.getw()));
         myframe.input[4].setText(Integer.toString(selectednode.geth()));
         myframe.input[5].setText(Integer.toHexString(selectednode.RGB[0])+Integer.toHexString(selectednode.RGB[1])+Integer.toHexString(selectednode.RGB[2]));
         selectedlabel.setBackground(Color.WHITE); // 라벨이 위치이동될대 흰색으로 반전
      }
      else if(signal[1] == 1) { //크기조절
         int [] px = {selectednode.getx(), selectednode.getx()+selectednode.getw()/2, selectednode.getx()+selectednode.getw()};
         int [] py = {selectednode.gety(), selectednode.gety()+selectednode.geth()/2, selectednode.gety()+selectednode.geth()};
         if(x > px[0]-err && x < px[0]+err) {
            if(y > py[0]-err && y < py[0]+err){ // 라벨의 왼쪽 상단 모서리가 선택되었을경우
               selectednode.setw(selectednode.getw()+selectednode.getx()-mousex);
               selectednode.seth(selectednode.geth()+selectednode.gety()-mousey);
               selectednode.setx(mousex);
               selectednode.sety(mousey);
            }
            else if(y > py[1]-err && y < py[1]+err) { // 라벨의 왼쪽 중앙 연결점이 선택되었을 경우
               if(mousex < selectednode.getx()) selectednode.setw(selectednode.getw()+(int)(Math.abs(selectednode.getx()-mousex)));
               else selectednode.setw(selectednode.getw()-(int)(Math.abs(selectednode.getx()-mousex)));
               selectednode.setx(mousex);
            }
            else if (y > py[2]-err && y < py[2]+err) { // 라벨의 왼쪽 하단 모서리가 선택되었을 경우
               if(mousex < selectednode.getx()) selectednode.setw(selectednode.getw()+(int)(Math.abs(selectednode.getx()-mousex)));
               else selectednode.setw(selectednode.getw()-(int)(Math.abs(selectednode.getx()-mousex)));
               selectednode.seth(mousey-selectednode.gety());
               selectednode.setx(mousex);
            }
         }
         else if(x > px[1]-err && x < px[1]+err) {
            if(y > py[0]-err && y < py[0]+err) { // 라벨의 중앙 상단 연결점이 선택되었을 경우
               selectednode.seth(selectednode.gety()+selectednode.geth()-mousey);
               selectednode.sety(mousey);
            }
            else if(y > py[2]-err && y < py[2]+err) selectednode.seth(mousey-selectednode.gety()); // 라벨의 하단 연결점이 선택되었을경우
         }
         else if(x > px[2]-err && x < px[2]+err) {
            if(y > py[0]-err && y < py[0]+err) { // 라벨의 오른쪽 상단 모서리가 선택되었을 경우
               selectednode.setw((int)Math.abs(mousex-selectednode.getx()));
               if(mousey < selectednode.gety()) selectednode.seth(selectednode.geth()+selectednode.gety()-mousey);
               else selectednode.seth(selectednode.geth()-(int)(Math.abs(selectednode.gety()-mousey)));
               selectednode.sety(mousey);
            }
            else if(y > py[1]-err && y < py[1]+err) selectednode.setw(mousex-selectednode.getx()); // 라벨의 오른쪽 연결점이 선택되었을 경우
            else if(y > py[2]-err && y < py[2]+err) { // 라벨의 오른쪽 하단 모서리가 선택되었을 경우
               selectednode.setw((int)(Math.abs(mousex-selectednode.getx())));
               selectednode.seth((int)(Math.abs(mousey-selectednode.gety())));
            }
         }
         selectedlabel.setLocation(selectednode.getx(), selectednode.gety()); // 라벨 위치 이동
         selectedlabel.setSize(selectednode.getw(), selectednode.geth()); // 라벨 사이즈 설정
         myframe.input[0].setText(selectednode.getData()); // 속성페인 업데이트
         myframe.input[1].setText(Integer.toString(selectednode.getx()));
         myframe.input[2].setText(Integer.toString(selectednode.gety()));
         myframe.input[3].setText(Integer.toString(selectednode.getw()));
         myframe.input[4].setText(Integer.toString(selectednode.geth()));
         x = mousex; 
         y = mousey;
      }
   }
   public void mouseClicked(MouseEvent e) { // 마우스를 선택 했을 때 속성페인에 정보를 띄움
      myframe.input[0].setText(selectednode.getData());
      myframe.input[1].setText(Integer.toString(selectednode.getx()));
      myframe.input[2].setText(Integer.toString(selectednode.gety()));
      myframe.input[3].setText(Integer.toString(selectednode.getw()));
      myframe.input[4].setText(Integer.toString(selectednode.geth()));
      myframe.input[5].setText(Integer.toHexString(selectednode.RGB[0])+Integer.toHexString(selectednode.RGB[1])+Integer.toHexString(selectednode.RGB[2]));
   }
   public void mouseReleased(MouseEvent e) {
      if(signal[0] == 1) selectedlabel.setBackground(selectednode.getcolor()); // 라벨을 이동하고 마우스르 뗏을 때 이동하는 동안 흰색으로 반전된 색을 다시 원래 색으로 돌려놓음
      myframe.p4.repaint(); // 연결선 업데이트
      signal[0]=0; signal[1]=0;
   }
   public void mouseEntered(MouseEvent e) {}
   public void mouseExited(MouseEvent e) {}
   public void mouseMoved(MouseEvent e) {}
}