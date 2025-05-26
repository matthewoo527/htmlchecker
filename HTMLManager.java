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
         newTag += tag.toString().trim() + " ";
      }
      return newTag;
   }
  
   public void fixHTML(){
      Stack<HTMLTag> s = new Stack<HTMLTag>();
      int tagsSize = tags.size();
      for(int i = 0; i < tagsSize; i++) {
         HTMLTag curTag = tags.remove();
         if(curTag.isSelfClosing()){
            tags.add(curTag);
         }else if (curTag.isOpening()){
            tags.add(curTag);
            s.push(curTag); 
         }else if(curTag.isClosing()){
            if(!s.isEmpty()){
               HTMLTag topVal = s.peek();
               if (topVal.matches(curTag)){
                  s.pop();
                  tags.add(curTag);
               }else if(!topVal.matches(curTag)){
                  s.pop();
                  HTMLTag closingMatch = topVal.getMatching();
                  tags.add(closingMatch);
               }
            }
         }
      }
      while(!s.isEmpty()) {
         HTMLTag topTag = s.pop();
         HTMLTag topTagMatch = topTag.getMatching();
         tags.add(topTagMatch);
      }
   }   
}
