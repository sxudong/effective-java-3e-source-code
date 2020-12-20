package effectivejava.chapter12.item86.serial;

import java.io.Serializable;

public class BaseBean  implements Serializable {
   private static final String TAG = "TestService";
   public String property4;
   public String property5;
   public int property6;
 
   public BaseBean(String id){
 
   }
 
   public BaseBean(){}
   //setter getter...
   private void readObjectNoData() {
      System.out.println("readObjectNoData:" + TAG);
      this.property4 = "readObject ....";
      this.property5 = "readObject ....";
      this.property6 = 0;
   }
 
   @Override
   public String toString() {
      return "BaseBean{" +
              "property4='" + property4 + '\'' +
              ", property5='" + property5 + '\'' +
              ", property6=" + property6 +
              '}';
   }
}