package ������Ʈ;

import java.awt.Color;

import javax.swing.JLabel;

class Node {
   JLabel la; // ���Ե�ʿ� ������ ��
   private int x, y;
   private int w = 50;
   private int h = 50;
   Color color;
   int [] RGB = new int[3]; // ���� ������ RGB ����
   
   private String data;
   private Node Child = null;
   private Node Sibling = null; 
   
   public void initcolor(Color color) { // ���ε�ʿ� ó�� ���� ������ �� ���� �ʱ�ȭ 
      this.color = color;
      RGB[0] = color.getRed();
      RGB[1] = color.getGreen();
      RGB[2] = color.getBlue();
   }
   public void setx(int x) { this.x = x;} // x��ǥ ����
   public void sety(int y) { this.y = y;} // y��ǥ ����
   public void setw(int w) { this.w = w;} // �ʺ� ����
   public void seth(int h) { this.h = h;} // ���� ����
   public void setcolor(String input) { //���� ����, ButtonListener�� trans �Լ����� ���ڿ��� 6���ڷ� �ٲپ���
      RGB[0] = Integer.parseInt(input.substring(0, 2), 16);
      RGB[1] = Integer.parseInt(input.substring(2, 4), 16);
      RGB[2] = Integer.parseInt(input.substring(4, 6), 16);
      color = new Color(RGB[0], RGB[1], RGB[2]);
   }
   public int getx() { return x; } // x��ǥ ����
   public int gety() { return y; } // y��ǥ ����
   public int getw() { return w; } // �ʺ� ����
   public int geth() { return h; } // ���� ����
   public Color getcolor() { return color; } // ���� ����
   
   public Node(String data) { this.data = data; }
   public void setData(String data) { this.data = data; }
   public String getData() { return data; }
   public void setChild(Node Child) { this.Child = Child; }
   public Node getChild() { return Child; }
   public void setSibling(Node Sibling) { this.Sibling = Sibling; }
   public Node getSibling() { return Sibling; }
}