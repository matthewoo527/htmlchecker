import java.util.*;

public class HTMLManager {
   private Queue<HTMLTag> tags;
  
   public HTMLManager(Queue<HTMLTag> html){
      if (html== null){
         throw new IllegalArgumentException();
      }else{
         tags= new LinkedList<HTMLTag>();
         for(HTMLTag tag: html){
            tags.add(tag);
         }
      }
   }    
  
   public Queue<HTMLTag> getTags(){
      return tags ;  
   }
  
   public String toString(){
      String newTag="";
      for(HTMLTag tag: tags){
         newTag+=tag;
      }
      return newTag.trim();
   }
  
   public void fixHTML(){
      Stack<HTMLTag>s=new Stack<HTMLTag>();
      for (HTMLTag tag: tags){
         HTMLTag safetag= tags.remove();
         if(safetag.isSelfClosing()){
            tags.add(safetag);
         }else if (safetag.isOpening()){
            s.push(safetag);
            tags.add(safetag);           
         }else if(safetag.isClosing()){
            HTMLTag top_val= s.peek();
            if (top_val.equals(safetag)){
               tags.add(top_val);
               s.pop();
            }else if( !top_val.equals(safetag)){
            
            }
         }
      }
   }    
}
