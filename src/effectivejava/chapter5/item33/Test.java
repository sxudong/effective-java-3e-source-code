package effectivejava.chapter5.item33;

import java.util.*;
import java.lang.annotation.*; 
  
// create a custom Annotation 
@Retention(RetentionPolicy.RUNTIME) 
@interface Annotation { 
  
    // This annotation has two attributes. 
    public String key(); 
  
    public String value(); 
} 
  
// call Annotation for method and pass values for annotation
@Annotation(key = "GFG", value = "GeeksForGeeks")
public class Test {

    public static void main(String[] args) throws ClassNotFoundException {
  
        // returns the Class object for this class 
        Class myClass = Test.class; 
  
        System.out.println("Class represented by myClass:" + myClass.toString());
  
        // Get the annotation using getAnnotation() method
        System.out.println( "Annotation of myClass:" + myClass.getAnnotation(Annotation.class));
    } 
} // https://vimsky.com/examples/usage/class-getannotation-method-in-java-with-examples.html
/* Output:
Class represented by myClass:class effectivejava.chapter5.item33.Test
Annotation of myClass:@effectivejava.chapter5.item33.Annotation(key="GFG", value="GeeksForGeeks")
*/