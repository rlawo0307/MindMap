package 프로젝트;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

class ButtonListener extends Frame implements ActionListener{
	   NodeMake treemake = myframe.nodemake;
	   static int cnt = 0;
	   static int i; // TextToMat 메소드가 재귀함수 이므로 해당 노드가 있는 인덱스 번호를 알려줄 i 전역변수 선언
	   static int num;
	   
	   ButtonListener(int num){
		   this.num=num;
	   }
	   public void actionPerformed(ActionEvent e) {
	      String str=e.getActionCommand();
	      if(str.equals("적용")){ // 적용 버튼을 눌렀을 때
	         treemake.node = new Node[100]; // 적용 버튼을 2회이상 눌렀을 때 노드를 업데이트
	         treemake.array = new String(); // 적용 버튼을 2회이상 눌렀을 때 텍스트 페인 업데이트
	         i = 0; // 인덱스 번호 초기화
	         myframe.p4.removeAll(); // 적용 버튼을 2회이상 눌렀을 때 마인드 먼저 맵 페인을 지워 준다(밑에서 다시 라벨과 연결설 그려줌) 
	         treemake.array = myframe.p2.getText(); // 텍스트 페인에서 입력된 행(노드)들을 가져옴
	         cnt = treemake.StringToNode(); // 트리를 만들고 입력된 노드의 갯수를 리턴
	         setXXYY(treemake.root,150); 
	         TextToMap(treemake.root);
	         myframe.p4.repaint(); // 연결선 업데이트
	         for(int i=0; i<6; i++)
	         myframe.input[i].setText(""); // 적용을 눌렀을 경우 속성페인을 초기화(아무것도 선택하지 않았으니까, 라벨 선택하면 정보를 띄을것임)
	      }
	      else if(str.equals("변경")) { // 변경 버튼을 눌렀을 때
	         for(int i=0; i<cnt; i++)
	            if(myframe.input[0].getText().equals(treemake.node[i].getData())) { // 트리 구조에서 해당 노드를 찾는다
	               String tmp = trans(myframe.input[5].getText()); // 입력받은 문자열을 6자리로 번역
	               Node node = treemake.node[i];
	               node.setx(Integer.parseInt(myframe.input[1].getText())); // x좌표 재설정
	               node.sety(Integer.parseInt(myframe.input[2].getText())); // y좌표 재설정
	               node.setw(Integer.parseInt(myframe.input[3].getText())); // 너비 재설정
	               node.seth(Integer.parseInt(myframe.input[4].getText())); // 높이 재설정
	               node.setcolor(tmp); // 색깔 재설정
	               node.la.setLocation(node.getx(), node.gety()); // 라벨의 위치 재설정
	               node.la.setSize(node.getw(), node.geth()); // 라벨 사이즈 재설정
	               node.la.setBackground(node.getcolor()); // 라벨 색깔 재설정
	               break;
	            }
	         myframe.p4.repaint(); // 연결선 업데이트
	      }
	   }
	   public String trans(String input) { // 문자열로 입력받은 색깔을 6글자로 번역
	      String color = "";
	      for(int i=0; i<input.length(); i++)
	         if(input.charAt(i) == '0')
	            color += "00";
	         else color += input.charAt(i);
	      return color;
	   }
	   public void setXXYY(Node node,int radius) {
	      if(node.getChild() != null) {
	           setXY(node,radius);
	           setXXYY(node.getChild(),radius/2);
	      }
	      if(node.getSibling() != null) setXXYY(node.getSibling(),radius);
	   }
	   public void setXY(Node root,int radius) {
	      int x,y;
	      int seta = 0;
	      Node temp;
	      Node[] save = new Node[100];
	      if(root.getChild()==null) return;
	      if(root == treemake.root) {
	         root.setx((int)myframe.p4.getSize().getWidth()/2);
	            root.sety((int)myframe.p4.getSize().getHeight()/2);
	            x = treemake.root.getx();
	            y = treemake.root.gety();
	      }
	        x = root.getx();
	        y = root.gety();
	        temp = root.getChild();
	        save[0] = temp;
	        seta++;
	        while(true) {
	           if(temp.getSibling()==null) break;
	            temp = temp.getSibling();
	            save[seta] = temp;
	            seta++;
	        }
	        for(int i=0;i<seta;i++) {
	           save[i].setx(x+(int)(Math.cos(2*3.141592/seta*(i+1)+2)*radius));
	            save[i].sety(y+(int)(Math.sin(2*3.141592/seta*(i+1)+2)*radius));
	        }
	   }
	   void TextToMap(Node root) { // 트리의 모든 노드의 라벨을 마인드맵 페인에 찍기
	          print(root);
	          i++;
	          if(root.getChild() != null) TextToMap(root.getChild());
	          if(root.getSibling() != null) TextToMap(root.getSibling());
	       }
	   void print(Node node) { // 마인드맵 페인에 라벨 찍기
	          int x = node.getx();
	          int y = node.gety();
	          treemake.node[i].la = new JLabel(treemake.node[i].getData());
	          treemake.node[i].la.setSize(treemake.node[i].getw(), treemake.node[i].geth()); // 사이즈설정
	          treemake.node[i].la.setBackground(treemake.node[i].getcolor()); // 배경색 설정
	          treemake.node[i].la.setOpaque(true); // 색 채우기
	          treemake.node[i].la.setHorizontalAlignment(SwingConstants.CENTER); // 글자를 가운데 정렬
	          Border border = new LineBorder(Color.BLACK, 4); // 테두리 설정
	          treemake.node[i].la.setBorder(border);
	          treemake.node[i].la.setLocation(x, y); // 위치 설정
	          myframe.p4.add(treemake.node[i].la);
	      }
	}