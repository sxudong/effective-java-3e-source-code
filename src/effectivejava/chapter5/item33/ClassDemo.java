package effectivejava.chapter5.item33;

import java.lang.annotation.Annotation;

public class ClassDemo {

    public static void main(String []args) {
        
     ClassDemo cls = new ClassDemo();
     Class c = cls.getClass();
          
     Annotation[] a = c.getAnnotations();
     if(a.length != 0) {
        for(Annotation val : a) {
           System.out.println(val.toString());
        }
     }
     else {
        System.out.println("Annotations is not present...");
     }
   }
} //原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java/lang/class_getannotations.html

