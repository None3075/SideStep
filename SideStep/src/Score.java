import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.io.FileReader;
public class Score extends Rectangle{
	

	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int playerscore;
	static int scorecounter;
	int MaxValue;
	String MaxPlayer="No one";
	HashMap<String,ArrayList<Integer>> scorearray = new HashMap<String,ArrayList<Integer>>();
	Score(){
		loadscore();
	}
	Score(int GAME_WIDTH, int GAME_HEIGHT){
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
		loadscore();
	}
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Comic Sans",Font.ITALIC,30));
		g.drawString(String.valueOf(playerscore), 0, GAME_HEIGHT);
		if (scorecounter<=0) {
			playerscore++;
			scorecounter=120;
		}
		else if (GamePanel.slowtime ) {
			scorecounter=scorecounter - 4;
			
		}
		else {
			scorecounter--;
		}
	}
	public void savescore() {
		  try {
			  if (scorearray.containsKey(Usernamewindow.Username)) {
				FileWriter writer = new FileWriter("tempscores.txt",false);
				BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
				String line = reader.readLine();
				while (line != null) {
							String[] parts = line.split(";");
							if(Objects.equals(parts[0],Usernamewindow.Username)) {
								line = line+";"+playerscore;
							}
							System.out.println(line);
							writer.append(line+"\n");
							line = reader.readLine();
							
						}
						reader.close();
						writer.close();
						File f1 = new File("tempscores.txt");
						File f2 = new File("scores.txt");
						f2.delete();
						f1.renameTo(f2);
						
			   }else {
				FileWriter writer = new FileWriter("scores.txt",true);
				   writer.append(Usernamewindow.Username+";"+playerscore+"\n");
				   writer.close();
			   }
			  
			  
			  } 
			  catch (IOException e) {
			   e.printStackTrace();
			  }
		
	}
	public void loadscore() {
		try {
		BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
		String line = reader.readLine();

		while (line != null) {
			String[] parts = line.split(";");
			ArrayList<Integer> tempArray = new ArrayList<Integer>();
			for(int i = 1;i<parts.length;i++) {
				tempArray.add(Integer.valueOf(parts[i]));
			}
			scorearray.put(parts[0],tempArray);
			line = reader.readLine();

		}
		reader.close();
	} catch (IOException e) {
		e.printStackTrace();
	}finally {
		System.out.println(scorearray);
		if(scorearray.size()!=0) {
		highscore(scorearray);
		}
	}
	}
	public void highscore(HashMap<String,ArrayList<Integer>> scorearray) {
		int MaxVal=0;
		String MaxPlay="";
		for (Entry<String, ArrayList<Integer>> entry : scorearray.entrySet()) {
			for(int i : entry.getValue()){
				if(MaxVal<i){
					MaxVal=i;
					MaxPlay=entry.getKey();
				}
			}
		}
		MaxPlayer=MaxPlay;
		MaxValue=MaxVal;

		
	}
}
