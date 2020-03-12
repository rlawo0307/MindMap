package ������Ʈ;

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
	   static int i; // TextToMat �޼ҵ尡 ����Լ� �̹Ƿ� �ش� ��尡 �ִ� �ε��� ��ȣ�� �˷��� i �������� ����
	   static int num;
	   
	   ButtonListener(int num){
		   this.num=num;
	   }
	   public void actionPerformed(ActionEvent e) {
	      String str=e.getActionCommand();
	      if(str.equals("����")){ // ���� ��ư�� ������ ��
	         treemake.node = new Node[100]; // ���� ��ư�� 2ȸ�̻� ������ �� ��带 ������Ʈ
	         treemake.array = new String(); // ���� ��ư�� 2ȸ�̻� ������ �� �ؽ�Ʈ ���� ������Ʈ
	         i = 0; // �ε��� ��ȣ �ʱ�ȭ
	         myframe.p4.removeAll(); // ���� ��ư�� 2ȸ�̻� ������ �� ���ε� ���� �� ������ ���� �ش�(�ؿ��� �ٽ� �󺧰� ���ἳ �׷���) 
	         treemake.array = myframe.p2.getText(); // �ؽ�Ʈ ���ο��� �Էµ� ��(���)���� ������
	         cnt = treemake.StringToNode(); // Ʈ���� ����� �Էµ� ����� ������ ����
	         setXXYY(treemake.root,150); 
	         TextToMap(treemake.root);
	         myframe.p4.repaint(); // ���ἱ ������Ʈ
	         for(int i=0; i<6; i++)
	         myframe.input[i].setText(""); // ������ ������ ��� �Ӽ������� �ʱ�ȭ(�ƹ��͵� �������� �ʾ����ϱ�, �� �����ϸ� ������ ��������)
	      }
	      else if(str.equals("����")) { // ���� ��ư�� ������ ��
	         for(int i=0; i<cnt; i++)
	            if(myframe.input[0].getText().equals(treemake.node[i].getData())) { // Ʈ�� �������� �ش� ��带 ã�´�
	               String tmp = trans(myframe.input[5].getText()); // �Է¹��� ���ڿ��� 6�ڸ��� ����
	               Node node = treemake.node[i];
	               node.setx(Integer.parseInt(myframe.input[1].getText())); // x��ǥ �缳��
	               node.sety(Integer.parseInt(myframe.input[2].getText())); // y��ǥ �缳��
	               node.setw(Integer.parseInt(myframe.input[3].getText())); // �ʺ� �缳��
	               node.seth(Integer.parseInt(myframe.input[4].getText())); // ���� �缳��
	               node.setcolor(tmp); // ���� �缳��
	               node.la.setLocation(node.getx(), node.gety()); // ���� ��ġ �缳��
	               node.la.setSize(node.getw(), node.geth()); // �� ������ �缳��
	               node.la.setBackground(node.getcolor()); // �� ���� �缳��
	               break;
	            }
	         myframe.p4.repaint(); // ���ἱ ������Ʈ
	      }
	   }
	   public String trans(String input) { // ���ڿ��� �Է¹��� ������ 6���ڷ� ����
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
	   void TextToMap(Node root) { // Ʈ���� ��� ����� ���� ���ε�� ���ο� ���
	          print(root);
	          i++;
	          if(root.getChild() != null) TextToMap(root.getChild());
	          if(root.getSibling() != null) TextToMap(root.getSibling());
	       }
	   void print(Node node) { // ���ε�� ���ο� �� ���
	          int x = node.getx();
	          int y = node.gety();
	          treemake.node[i].la = new JLabel(treemake.node[i].getData());
	          treemake.node[i].la.setSize(treemake.node[i].getw(), treemake.node[i].geth()); // �������
	          treemake.node[i].la.setBackground(treemake.node[i].getcolor()); // ���� ����
	          treemake.node[i].la.setOpaque(true); // �� ä���
	          treemake.node[i].la.setHorizontalAlignment(SwingConstants.CENTER); // ���ڸ� ��� ����
	          Border border = new LineBorder(Color.BLACK, 4); // �׵θ� ����
	          treemake.node[i].la.setBorder(border);
	          treemake.node[i].la.setLocation(x, y); // ��ġ ����
	          myframe.p4.add(treemake.node[i].la);
	      }
	}