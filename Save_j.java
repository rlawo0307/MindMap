package ������Ʈ;

import java.io.*;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class Save_j {
   public Save_j(String path,String name) {
      JSONObject root = new JSONObject(); 
      JSONObject test = new JSONObject();
      JSONObject test_1 = new JSONObject();
      
      int i = 0;
      int j = 0;
      int check = 0;
      Node temp = new Node(null);
      Node node = new Node(null);
      node = myframe.nodemake.node[0];
      
      root.put("data", node.getData()); //�θ��� �ֱ�
      root.put("x", Integer.toString(node.getx()));
      root.put("y", Integer.toString(node.gety()));
      root.put("w", Integer.toString(node.getw()));
      root.put("h", Integer.toString(node.geth()));
      
      
      if(node.getChild() != null) { //�ڽ� ����� + �ڽ��� �ڽ�, �������� ���
         node = node.getChild();
         test = new JSONObject();
         test.put("data", node.getData().trim());
         test.put("x", Integer.toString(node.getx()));
         test.put("y", Integer.toString(node.gety()));
         test.put("w", Integer.toString(node.getw()));
         test.put("h", Integer.toString(node.geth()));
         root.put("child" + i, test);
         temp = node;
         if(node.getChild() != null) {
            node = node.getChild();
            test_1 = new JSONObject();
            test_1.put("data", node.getData().trim());
            test_1.put("x", Integer.toString(node.getx()));
            test_1.put("y", Integer.toString(node.gety()));
            test_1.put("w", Integer.toString(node.getw()));
            test_1.put("h", Integer.toString(node.geth()));
            test.put("child_"+ j, test_1);
            j++;
            check = 1;
         }
         if(check == 1) {
         while(node.getSibling() != null) {
            node = node.getSibling();
            test_1 = new JSONObject();
            test_1.put("data", node.getData().trim());
            test_1.put("x", Integer.toString(node.getx()));
            test_1.put("y", Integer.toString(node.gety()));
            test_1.put("w", Integer.toString(node.getw()));
            test_1.put("h", Integer.toString(node.geth()));
            test.put("child_" + j, test_1);
            j++;
         
            }
         }
         i++;
      }
      check = 0;
      node = temp;
      
      while(node.getSibling() != null) {
         node = node.getSibling();
         test = new JSONObject();
         test.put("data", node.getData().trim());
         test.put("x", Integer.toString(node.getx()));
         test.put("y", Integer.toString(node.gety()));
         test.put("w", Integer.toString(node.getw()));
         test.put("h", Integer.toString(node.geth()));
         root.put("child" + i, test);
         temp = node;
         if(node.getChild() != null) {
            node = node.getChild();
            test_1 = new JSONObject();
            test_1.put("data", node.getData().trim());
            test_1.put("x", Integer.toString(node.getx()));
            test_1.put("y", Integer.toString(node.gety()));
            test_1.put("w", Integer.toString(node.getw()));
            test_1.put("h", Integer.toString(node.geth()));
            test.put("child_"+ j, test_1);
            j++;
         }
         if(check == 1) {
         while(node.getSibling() != null) {
            node = node.getSibling();
            test_1 = new JSONObject();
            test_1.put("data", node.getData().trim());
            test_1.put("x", Integer.toString(node.getx()));
            test_1.put("y", Integer.toString(node.gety()));
            test_1.put("w", Integer.toString(node.getw()));
            test_1.put("h", Integer.toString(node.geth()));
            test.put("child_" + j, test_1);
            j++;
         
            }
         check = 0;
         }
         node = temp;
         i++;
      }

      
      try {
         FileWriter file; 
         if (path == null) //������� ������ ����Ʈ�� ����
            file = new FileWriter("c:\\test.json");
         else //��� ������ �� �־��ٸ� ������ ��ο� ����
            file = new FileWriter(path);
         file.write(root.toJSONString());
         file.flush();
         file.close();
         
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      
      }
   }
}
