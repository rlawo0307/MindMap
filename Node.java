package 프로젝트;

import java.awt.Color;

import javax.swing.JLabel;

class Node {
   JLabel la; // 마입드맵에 부착할 라벨
   private int x, y;
   private int w = 50;
   private int h = 50;
   Color color;
   int [] RGB = new int[3]; // 라벨의 배경색의 RGB 저장
   
   private String data;
   private Node Child = null;
   private Node Sibling = null; 
   
   public void initcolor(Color color) { // 마인드맵에 처음 라벨을 부착할 대 배경색 초기화 
      this.color = color;
      RGB[0] = color.getRed();
      RGB[1] = color.getGreen();
      RGB[2] = color.getBlue();
   }
   public void setx(int x) { this.x = x;} // x좌표 설정
   public void sety(int y) { this.y = y;} // y좌표 설정
   public void setw(int w) { this.w = w;} // 너비 설정
   public void seth(int h) { this.h = h;} // 높이 설정
   public void setcolor(String input) { //색깔 설정, ButtonListener의 trans 함수에서 문자열을 6글자로 바꾸었음
      RGB[0] = Integer.parseInt(input.substring(0, 2), 16);
      RGB[1] = Integer.parseInt(input.substring(2, 4), 16);
      RGB[2] = Integer.parseInt(input.substring(4, 6), 16);
      color = new Color(RGB[0], RGB[1], RGB[2]);
   }
   public int getx() { return x; } // x좌표 리턴
   public int gety() { return y; } // y좌표 리턴
   public int getw() { return w; } // 너비 리턴
   public int geth() { return h; } // 높이 리턴
   public Color getcolor() { return color; } // 색깔 리턴
   
   public Node(String data) { this.data = data; }
   public void setData(String data) { this.data = data; }
   public String getData() { return data; }
   public void setChild(Node Child) { this.Child = Child; }
   public Node getChild() { return Child; }
   public void setSibling(Node Sibling) { this.Sibling = Sibling; }
   public Node getSibling() { return Sibling; }
}