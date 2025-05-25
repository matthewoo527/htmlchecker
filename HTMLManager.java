import java.util.*;

public class HTMLManager {
   private Queue<HTMLTag> tags;
  
   public HTMLManager(Queue<HTMLTag> html){
      if (html== null){
         throw new IllegalArgumentException();
      }else{
         tags= new LinkedList<HTMLTag>();
         for(HTMLTag tag : html){
            tags.add(tag);
         }
      }
   }    
  
   public Queue<HTMLTag> getTags(){
      return tags ;  
   }
  
   public String toString(){
      String newTag = "";
      for(HTMLTag tag : tags){
         newTag += tag;
      }
      return newTag.trim();
   }
  
   public void fixHTML(){
      Stack<HTMLTag> s = new Stack<HTMLTag>();
      for (HTMLTag tag : tags){
         HTMLTag saveTag = tags.remove();
         if(saveTag.isSelfClosing()){
            tags.add(saveTag);
         }else if (saveTag.isOpening()){
            s.push(saveTag);
            tags.add(saveTag);           
         }else if(saveTag.isClosing()){
            HTMLTag topVal = s.peek();
            if (topVal.equals(saveTag)){
               tags.add(topVal);
               s.pop();
            }else if( !topVal.equals(saveTag)){
               HTMLTag closingMatch = topVal.getMatching();
               tags.add(closingMatch);
            }
         }
      }
   }    
}
