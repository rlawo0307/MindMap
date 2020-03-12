package 프로젝트;
import java.awt.event.*;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
class MyActionListener implements ActionListener{
	   private int i;
	   private JFileChooser jfc = new JFileChooser();
	   private String openway=new String();

	   public MyActionListener(int i) { this.i = i; }
	   public void actionPerformed(ActionEvent e) {
	      switch(i) {
	      case 0:
	    	   myframe.p4.parent = new Node[100];
	    	   myframe.p4.sibling = new Node[100];
	    	   myframe.p4. num=0;
	    	   myframe.p4.removeAll();
	    	   myframe.p4.repaint();
		       for(int i=0; i<6; i++)
			         myframe.input[i].setText(""); 
	    	  break;
	      case 1:
	    	  if(jfc.showOpenDialog(myframe.MenuItem[1]) == JFileChooser.APPROVE_OPTION){
	    		 openway= jfc.getSelectedFile().toString(); //파일 경로 담기
	    	  }
	    	  break;
	    
	      case 2:
	    	  if(jfc.showSaveDialog(myframe.MenuItem[2]) == JFileChooser.APPROVE_OPTION){
                              // showSaveDialog 저장 창을 열고 확인 버튼을 눌렀는지 확인
	    		  System.out.println("dfsdf");
	    		  new Save_j(jfc.getSelectedFile().toString() + "." +
	    		  jfc.getFileFilter().getDescription(),jfc.getSelectedFile().getName());}
	    	  break;
	    	  
	      case 3:  if(jfc.showSaveDialog(myframe.MenuItem[2]) == JFileChooser.APPROVE_OPTION){
              // showSaveDialog 저장 창을 열고 확인 버튼을 눌렀는지 확인
	    	  	  new Save_j(jfc.getSelectedFile().toString() + "." +
	    			 jfc.getFileFilter().getDescription(),jfc.getSelectedFile().getName());}
	      		break;
	    	  
	      case 6: { System.exit(0); break; }
	      }
	   }
	}


