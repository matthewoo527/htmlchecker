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
      int tagsSize = tags.size();
      for(int i = 0; i <= tagsSize; i++) {
         HTMLTag curTag = tags.remove();
         if(curTag.isSelfClosing()){
            tags.add(curTag);
         }else if (curTag.isOpening()){
            tags.add(curTag);
            s.push(curTag);           
         }else if(curTag.isClosing()){
            HTMLTag topVal = s.peek();
            if (topVal.equals(curTag)){
               tags.add(curTag);
               s.pop();
            }else if(!topVal.equals(curTag)){
               HTMLTag closingMatch = topVal.getMatching();
               tags.add(closingMatch);
            }
         }else {
            while(!s.isEmpty()) {
               HTMLTag topTag = s.pop();
               HTMLTag topTagMatch = curTag.getMatching();
               tags.add(topTagMatch);
            }
         }
      }
   }    
}
