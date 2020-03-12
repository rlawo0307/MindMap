package 프로젝트;

public class ChangeNode {
   String xx;
   String yy;
   String ww;
   String hh;
   String color;
   int x,y,w,h;
   
   public ChangeNode() {
      color=new String();
      xx=new String();
      yy=new String();
      hh=new String();
      ww=new String();
   }
   
   public void StringToInt() {
      x=Integer.parseInt(xx);
      y=Integer.parseInt(yy);
      w=Integer.parseInt(ww);
      h=Integer.parseInt(hh);
   }
   
   public void print() {
      System.out.println(x);
      System.out.println(y);
      System.out.println(w);
      System.out.println(h);
      System.out.println(color);
   }
}