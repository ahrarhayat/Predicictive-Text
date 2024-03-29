
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MarkovWordTwo implements IMarkovModel {

    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-2);  // random word to start with
		String key1 = myText[index];
		String key2= myText[index+1];
		sb.append(key1);
		sb.append(" ");
		sb.append(key2);
		sb.append(" ");
		for(int k=0; k < numWords-2; k++){
		    ArrayList<String> follows = getFollows(key1,key2);
		    //System.out.println(follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			//sb.append(next+1);
			//sb.append(" ");
			key1 = key2;
			key2= next;
			//System.out.println(key);
		}
		
		return sb.toString().trim();
	}
	private int indexOf(String [] words, String target1,String target2, int start)
	{
	   for(int k=start;k<words.length;k++)
	   {
	       if(words[k].equals(target1) && words[k+1].equals(target2))
	       {
	           return k;
	       }
	   }
	   return -1; 
	   }
	   
        public void testIndexOf()
        {
            String [] test ={"this","is","just","a","test","yes","this","is","a","simple","test"};
            System.out.println("key1:this key2: is "+ "starting at 0 "+indexOf(test,"this","is",0));
            //System.out.println("this "+ "starting at 3 "+indexOf(test,"this",3));
            //System.out.println("frog "+ "starting at 0 "+indexOf(test,"frog",0));
            //System.out.println("frog "+ "starting at 5 "+indexOf(test,"frog",5));
            //System.out.println("simple "+ "starting at 2 "+indexOf(test,"simple",2));
            //System.out.println("test "+ "starting at 5 "+indexOf(test,"test",5));
        }
	private ArrayList<String> getFollows(String key1, String key2) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int pos =0;
	   while(pos<myText.length)
	   {
	       int start = indexOf(myText,key1,key2,pos);
	       if(start==-1)
	       {
	           break;
	       }
	       if(start+key1.length()+key2.length()>=myText.length-1)
	       {
	           break;
	       }
	       String follow=myText[start+2];
	       follows.add(follow); 
	       pos=start+1;
	   }
	    return follows;
    }

}
