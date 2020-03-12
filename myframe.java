package ������Ʈ;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.border.*;

public class myframe extends JFrame {
   static NodeMake nodemake = new NodeMake(); // Ʈ���� ��� Ŭ���� ����ϹǷ� Ʈ���� ����� nodemake�� ���������� ����
   JPanel p1 = new JPanel(); // ���� �г�
   static JPanel p3 = new JPanel(); // �߾� �г� 
   JPanel p5 = new JPanel(); // ���� �г�
   static JTextArea p2 = new JTextArea(10, 20); // �ؽ�Ʈ ����
   static drawMap p4 = new drawMap(); // JPanel�� ��ӹ��� �׷���(���ἱ)�� �׸��� �ִ� ���ε�� ���� 
   static JPanel p6 = new JPanel(); // �Ӽ� ����
   static JTextField [] input = new JTextField[6]; // �Ӽ����ο� ������ �ؽ�Ʈ����
   ButtonListener buttonlistener1 = new ButtonListener(0); // ����, ���� ��ư�� ������ ��ư������
   static  JMenuItem [] MenuItem;
   
   myframe(){
   setTitle("������Ʈ ����");
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �������� ������ ���α׷� ����
   
   createMenu(); // �޴��� ����
   createTool(); // ���� ����
   splitPane(); // �����ӿ� �ؽ�Ʈ����, ���ε�� ����, �Ӽ��������� ����� ����
   TextEditorPane(); 
   MindMapPane();
   AttributePane();
   setSize(1200,800); // ������ ũ�� ����
   setVisible(true); // â ����
   }
   void splitPane() { // ���� �������� �ΰ��� ������ �������� ���� ������ �ٽ� �ΰ��� ������ �� 3���� �������� ������
      JSplitPane split1 = new JSplitPane();
      JSplitPane split2 = new JSplitPane();
      split1.setOrientation(JSplitPane.HORIZONTAL_SPLIT); // ���η� ������
      split2.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
      getContentPane().add(split1);
      split1.setRightComponent(split2);
      split1.setDividerLocation(300);
      split2.setDividerLocation(600);
      split1.setDividerSize(1); // ���м� ������ ����
      split2.setDividerSize(1);
      p1.setBackground(Color.WHITE); // ���� 3���� ��� ���� ����
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
	      String [] MenuName = {"���� �����", "����", "����", "�ٸ� �̸����� ����", "����", "����", "�ݱ�"};
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
      JToolBar toolbar = new JToolBar("Kitae Menu"); // ���� ���ٸ� �����
      toolbar.setBackground(Color.LIGHT_GRAY); // ���� �� ����
      
      String [] imgName = {"new.jpg", "open.jpg", "save.jpg", "another_save.jpg", "adapt.jpg", "change.jpg", "close.jpg"};
      String [] buttonTip = {"���θ����", "����", "����", "�ٸ� �̸����� ����", "����", "����", "����"};
      JButton [] button = new JButton[7];
      for(int i=0; i<7;i++) {
         button[i] = new JButton(new ImageIcon(imgName[i])); // �̹����� �ҷ��ͼ� ���ٿ� ��ư ����
         button[i].setToolTipText(buttonTip[i]); // ���� ����
         if(i==4||i==5)
        	 button[i].addActionListener(buttonlistener1);
         else	 
        	 button[i].addActionListener(new MyActionListener(i)); //�̹�����ư�� �ش� ��� ����
         toolbar.add(button[i]);
      }
        this.add(toolbar, BorderLayout.NORTH); // ���ٸ� �������� ����(���)�� ��ġ��Ŵ
        //this.setSize(500, 500); // 
        this.setVisible(true);
   }
   void TextEditorPane() { 
      p1.setLayout(new BorderLayout()); // ��ġ������ ����
      
      JLabel title1 = new JLabel("Text Editor Pane"); // ���� �̸� ���̱�
      AbstractBorder border = new LineBorder(Color.BLACK); // �׵θ� ����
      title1.setBorder(border);
      title1.setFont(getFont().deriveFont(25.0f)); // ���� ��Ʈ ����
      title1.setForeground(Color.BLACK); // ���ڻ� ����
      title1.setHorizontalAlignment(SwingConstants.CENTER); // ���� ��� ����
      p1.add(title1, BorderLayout.NORTH); // �̸� ���� ������ ��ܿ� ����
      
      p2.setBackground(Color.WHITE); // ���� ���ڸ� �Է��ϴ� �ؽ�Ʈ ����
        JScrollPane scroll = new JScrollPane(p2); // �ؽ�Ʈ ���ο� ��ũ�� �� ����
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // ���� ��ũ�� �� ����
      scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // ���� ��ũ�� �� ����
      p1.add(scroll, BorderLayout.CENTER);
      
      JButton button1 = new JButton("����"); // ���� ��ư ����
         button1.setBackground(Color.RED); // �� ����
         button1.setFont(getFont().deriveFont(30.0f)); // ���� ��Ʈ ����
         p1.add(button1, BorderLayout.SOUTH); // �ϴܿ� ����
         button1.addActionListener(buttonlistener1); // ��ư�� ������ ����Ǵ� �̺�Ʈ������ ����
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
      
      mouseListener ml = new mouseListener(); // ���콺������ ����
       p4.addMouseListener(ml); // ���ε�� ���ο� ���콺������ ����
       p4.addMouseMotionListener(ml); // ���ε�� ���ο� ���콺��������� ����
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
      GridLayout grid = new GridLayout(6, 2); // Grid ��ġ������ ����
      grid.setVgap(20); // �� �Ʒ� ���̰� ����
      p6.setLayout(grid);
      
      JLabel [] attr = new JLabel[6];
      for(int i=0; i<6; i++)
         if(i == 0) input[i] = new JTextField(" "); // ������ �Է¹��� �ؽ�Ʈ�ʵ� ����
         else input[i] = new JTextField("0"); // 0���� �ʱ�ȭ
      String [] attName = {"TEXT:", "X:", "Y:", "W:", "H:", "Color:"};
      for(int i=0; i<6; i++) {
         attr[i] = new JLabel(attName[i]);
         attr[i].setFont(getFont().deriveFont(25.0f));
         attr[i].setHorizontalAlignment(SwingConstants.CENTER);
         p6.add(attr[i]);
         p6.add(input[i]); 
         if(i == 0) { // �̸��� ����ڰ� �� ��ġ����
            input[i].setBackground(Color.BLACK); // �ؽ�Ʈ�ʵ��� ���� ����
            input[i].setEnabled(false); // ����ڰ� �� ��ġ���� ����
         }
      }
      JButton button2 = new JButton("����"); // ���� ��ư ����
        button2.setBackground(Color.RED);
        button2.setFont(getFont().deriveFont(30.0f));
        p5.add(button2, BorderLayout.SOUTH);
        button2.addActionListener(buttonlistener1); // ��ư�� ������ ����Ǵ� �̺�Ʈ������ ����
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
      g2.setColor(Color.RED); // ���ἱ ���� ����
      g2.setStroke(new BasicStroke(5,BasicStroke.CAP_ROUND,0)); // ���ἱ ���� ����
      for(int i=0;i<num;i++) // ���ἱ �׸���
         g.drawLine(parent[i].getx()+parent[i].getw()*1/2, parent[i].gety()+parent[i].geth()*1/2,
               sibling[i].getx()+sibling[i].getw()*1/2,sibling[i].gety()+sibling[i].geth()*1/2);
   }
}
class mouseListener implements MouseListener, MouseMotionListener{
   int i = -1; // node�� �ε��� ��ȣ 
   int x, y;
   Node selectednode;
   JLabel selectedlabel;
   int [] signal = new int[2]; // 0: (����,) �̵�, 1: ũ������ -> drag�Ҷ� ��츦 ������ ���� ���̹Ƿ� ���� ����(dragȣ��x)�� ���� �������� ���� 
   NodeMake realnode = myframe.nodemake;
   int err = 10; // ���콺 �νĹ��� ����
   
   public void mousePressed(MouseEvent e) {
      x = e.getX();
      y = e.getY();
      for(int j=0; j<ButtonListener.cnt; j++)
         if(x >= realnode.node[j].getx()-err && x <= realnode.node[j].getx()+realnode.node[j].getw()+err
         && y >= realnode.node[j].gety()-err && y <= realnode.node[j].gety()+realnode.node[j].geth()+err) { //���콺�� �󺧿� ����(����, �̵�, ũ������)�� �ϰ������
            i = j;
            selectednode = realnode.node[i];
            selectedlabel= selectednode.la;
            if(x > realnode.node[j].getx()+err && x < realnode.node[j].getx()+realnode.node[j].getw()-err
                  && y > realnode.node[j].gety()+err && y < realnode.node[j].gety()+realnode.node[j].geth()-err) signal[0] = 1; // ���̵��� �ϰ� ������
            else signal[1] = 1; // �� ũ�������� �ϰ� ������
            break;
         }
   }
   public void mouseDragged(MouseEvent e) {
      int mousex = e.getX();
      int mousey = e.getY();
      if(signal[0] == 1) { // ��ġ�̵�
         selectedlabel.setLocation(mousex, mousey); //x, y �缳��
         selectednode.setx(mousex);
         selectednode.sety(mousey);
         myframe.input[0].setText(selectednode.getData()); // �Ӽ����ο� ��Ÿ���� ���� ������Ʈ
         myframe.input[1].setText(Integer.toString(selectednode.getx()));
         myframe.input[2].setText(Integer.toString(selectednode.gety()));
         myframe.input[3].setText(Integer.toString(selectednode.getw()));
         myframe.input[4].setText(Integer.toString(selectednode.geth()));
         myframe.input[5].setText(Integer.toHexString(selectednode.RGB[0])+Integer.toHexString(selectednode.RGB[1])+Integer.toHexString(selectednode.RGB[2]));
         selectedlabel.setBackground(Color.WHITE); // ���� ��ġ�̵��ɴ� ������� ����
      }
      else if(signal[1] == 1) { //ũ������
         int [] px = {selectednode.getx(), selectednode.getx()+selectednode.getw()/2, selectednode.getx()+selectednode.getw()};
         int [] py = {selectednode.gety(), selectednode.gety()+selectednode.geth()/2, selectednode.gety()+selectednode.geth()};
         if(x > px[0]-err && x < px[0]+err) {
            if(y > py[0]-err && y < py[0]+err){ // ���� ���� ��� �𼭸��� ���õǾ������
               selectednode.setw(selectednode.getw()+selectednode.getx()-mousex);
               selectednode.seth(selectednode.geth()+selectednode.gety()-mousey);
               selectednode.setx(mousex);
               selectednode.sety(mousey);
            }
            else if(y > py[1]-err && y < py[1]+err) { // ���� ���� �߾� �������� ���õǾ��� ���
               if(mousex < selectednode.getx()) selectednode.setw(selectednode.getw()+(int)(Math.abs(selectednode.getx()-mousex)));
               else selectednode.setw(selectednode.getw()-(int)(Math.abs(selectednode.getx()-mousex)));
               selectednode.setx(mousex);
            }
            else if (y > py[2]-err && y < py[2]+err) { // ���� ���� �ϴ� �𼭸��� ���õǾ��� ���
               if(mousex < selectednode.getx()) selectednode.setw(selectednode.getw()+(int)(Math.abs(selectednode.getx()-mousex)));
               else selectednode.setw(selectednode.getw()-(int)(Math.abs(selectednode.getx()-mousex)));
               selectednode.seth(mousey-selectednode.gety());
               selectednode.setx(mousex);
            }
         }
         else if(x > px[1]-err && x < px[1]+err) {
            if(y > py[0]-err && y < py[0]+err) { // ���� �߾� ��� �������� ���õǾ��� ���
               selectednode.seth(selectednode.gety()+selectednode.geth()-mousey);
               selectednode.sety(mousey);
            }
            else if(y > py[2]-err && y < py[2]+err) selectednode.seth(mousey-selectednode.gety()); // ���� �ϴ� �������� ���õǾ������
         }
         else if(x > px[2]-err && x < px[2]+err) {
            if(y > py[0]-err && y < py[0]+err) { // ���� ������ ��� �𼭸��� ���õǾ��� ���
               selectednode.setw((int)Math.abs(mousex-selectednode.getx()));
               if(mousey < selectednode.gety()) selectednode.seth(selectednode.geth()+selectednode.gety()-mousey);
               else selectednode.seth(selectednode.geth()-(int)(Math.abs(selectednode.gety()-mousey)));
               selectednode.sety(mousey);
            }
            else if(y > py[1]-err && y < py[1]+err) selectednode.setw(mousex-selectednode.getx()); // ���� ������ �������� ���õǾ��� ���
            else if(y > py[2]-err && y < py[2]+err) { // ���� ������ �ϴ� �𼭸��� ���õǾ��� ���
               selectednode.setw((int)(Math.abs(mousex-selectednode.getx())));
               selectednode.seth((int)(Math.abs(mousey-selectednode.gety())));
            }
         }
         selectedlabel.setLocation(selectednode.getx(), selectednode.gety()); // �� ��ġ �̵�
         selectedlabel.setSize(selectednode.getw(), selectednode.geth()); // �� ������ ����
         myframe.input[0].setText(selectednode.getData()); // �Ӽ����� ������Ʈ
         myframe.input[1].setText(Integer.toString(selectednode.getx()));
         myframe.input[2].setText(Integer.toString(selectednode.gety()));
         myframe.input[3].setText(Integer.toString(selectednode.getw()));
         myframe.input[4].setText(Integer.toString(selectednode.geth()));
         x = mousex; 
         y = mousey;
      }
   }
   public void mouseClicked(MouseEvent e) { // ���콺�� ���� ���� �� �Ӽ����ο� ������ ���
      myframe.input[0].setText(selectednode.getData());
      myframe.input[1].setText(Integer.toString(selectednode.getx()));
      myframe.input[2].setText(Integer.toString(selectednode.gety()));
      myframe.input[3].setText(Integer.toString(selectednode.getw()));
      myframe.input[4].setText(Integer.toString(selectednode.geth()));
      myframe.input[5].setText(Integer.toHexString(selectednode.RGB[0])+Integer.toHexString(selectednode.RGB[1])+Integer.toHexString(selectednode.RGB[2]));
   }
   public void mouseReleased(MouseEvent e) {
      if(signal[0] == 1) selectedlabel.setBackground(selectednode.getcolor()); // ���� �̵��ϰ� ���콺�� ���� �� �̵��ϴ� ���� ������� ������ ���� �ٽ� ���� ������ ��������
      myframe.p4.repaint(); // ���ἱ ������Ʈ
      signal[0]=0; signal[1]=0;
   }
   public void mouseEntered(MouseEvent e) {}
   public void mouseExited(MouseEvent e) {}
   public void mouseMoved(MouseEvent e) {}
}