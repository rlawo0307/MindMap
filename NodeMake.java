package 프로젝트;

import java.awt.Color;

class NodeMake {
      Node root;
      static Node[] node;
      String array;
      Node temp;
      int tabNum = 0;
      
      public int StringToNode() {
         Node[] tree = new Node[5];
         String[] words = array.split("\n");
          int i = 0;
          for(String wo : words) { //개행문자 중심으로 문자열 자르기
             tabNum=0;
              for(int j = 0; words[i].charAt(j) == '\t'; j++) //탭 개수 계산하기
                  tabNum++;
              words[i] = words[i].substring(tabNum);
              
              if(tabNum == 0) { //젤 처음 부모 노드일때
                  tree[tabNum] = new Node(words[i]);
                  node[i] = tree[tabNum];
                  node[i].initcolor(Color.BLUE);
              }
               else { //자식 노드일때
                   temp = new Node(words[i]);
                   add(tree[tabNum-1],temp);
                   tree[tabNum] = temp;
                   node[i] = tree[tabNum];
                   if(tabNum == 1) node[i].initcolor(Color.ORANGE);
                   else if(tabNum == 2) node[i].initcolor(Color.GREEN);
                   else node[i].initcolor(Color.YELLOW);
                   myframe.p4.sibling[i-1]=node[i];
                   myframe.p4.parent[i-1]=tree[tabNum-1];
               }
               i++;
          }
          myframe.p4.num = i-1;
          root = tree[0];
          return i;
      }
      public static void add(Node parent, Node child) {
              if(parent.getChild() == null) parent.setChild(child);
              else {
                  Node temp = parent.getChild();
                  while(temp.getSibling() != null)
                      temp = temp.getSibling();
                      temp.setSibling(child);
              }
      }
}