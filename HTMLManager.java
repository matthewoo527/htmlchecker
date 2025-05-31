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
      // A Stack that stores open tag
      Stack<HTMLTag> s = new Stack<HTMLTag>();
      // Save the tags queue size so that it won't change how many times the for loop runs while the queue is changing size
      int tagsSize = tags.size();
      // A for loop that go through every tags in tags queue
      for(int i = 0; i < tagsSize; i++) {
         // save the tag to a variable which is the tag that we are currently looking
         HTMLTag curTag = tags.remove();
         // If curTag is self closing
         if(curTag.isSelfClosing()){
            // Then add it back to the tags queue
            tags.add(curTag);
         // else if the curTag is opening
         }else if (curTag.isOpening()){
            // then add it back to the tags queue and on top of the stack
            tags.add(curTag);
            s.push(curTag); 
         // else if the curTag is closing
         }else if(curTag.isClosing()){
            // if the stack is not empty   
            if(!s.isEmpty()){
               //Get the tp[ value of the stack
               HTMLTag topVal = s.peek();
               // if the top value of the stack matches the current tag
               if (topVal.matches(curTag)){
                  // then remove it from the stack and add it into the tags queue
                  s.pop();
                  tags.add(curTag);
               // else if the top value does not matches the current tag
               }else if(!topVal.matches(curTag)){
                  // then remove it from the stack
                  s.pop();
                  // and add it matching tag into the tags queue
                  HTMLTag closingMatch = topVal.getMatching();
                  tags.add(closingMatch);
               }
            }
         }
      }
      // If there are still tags in the stack (stack is not empty)
      while(!s.isEmpty()) {
         // then get it matching tag and add it to the tags queue;
         HTMLTag topTag = s.pop();
         HTMLTag topTagMatch = topTag.getMatching();
         tags.add(topTagMatch);
      }
   }   
}
