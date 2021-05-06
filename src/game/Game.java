/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.*;

class Player implements Comparable<Player>{
    int id = 0;
    String name;
    int age;
    boolean isout = false;
    boolean isPlaying = false;
    int score = 0;
    
    Player(int id, String name, int age, int score){
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public int compareTo(Player o) {
        return this.score + o.score;
    }
   
}

class Scorer{
    
    List<Player> players = new ArrayList<Player>();
    int idProvider = 0;
    
    void addPlayer(String name, int age , int score){
        Player p1 = new Player(idProvider, name, age , score);
        players.add(p1);
        idProvider += 1;
    }
    
    void addcurrentPlaying(String name){
        System.out.println("/////////////////////////////");
        for (int i=0;i<players.size();i++){
            System.out.println(players.get(i).name);
            System.out.println(players.get(i).name.equalsIgnoreCase(name));
            if (players.get(i).name.equalsIgnoreCase(name)){
                players.get(i).isPlaying = true;
            }
            
        }
        System.out.println("/////////////////////////////");
    }
    
    void getoutPlayerList(){
        System.out.println("/////////////////////////////");
        System.out.println("Out Players Name");
        for (int i=0;i<players.size();i++){
            if (players.get(i).isout == true){
                System.out.println(players.get(i).name);
            }  
        }
        System.out.println("/////////////////////////////");
    }
    
    void outBy(String name){
        for (int i=0;i<players.size();i++){
            if (players.get(i).name.equalsIgnoreCase(name) && players.get(i).isPlaying == true){
                players.get(i).isout = true;
                players.get(i).isPlaying = false;
            }
           
        }
    }
    
        void getCurrentPlayingPlayerList(){
        System.out.println("/////////////////////////////");
        System.out.println("Playing...");
        for (int i=0;i<players.size();i++){
            if (players.get(i).isPlaying == true){
                System.out.println(players.get(i).name);
            }  
        }
        System.out.println("/////////////////////////////");
    }
    
    
    void updateScore(String name, int score){
        
        System.out.println("/////////////////////////////");
        for (int i=0;i<players.size();i++){
            if (players.get(i).name.equalsIgnoreCase(name) && players.get(i).isPlaying){
                players.get(i).score += score;
            }
            
        }
        System.out.println("/////////////////////////////");
    }
    
    void showScore(){
        
        System.out.println("=============================================");
        System.out.println("Name \t Score");
        for(int i=0 ;i<players.size() ; i++){
            
            if (players.get(i).score == 0 && players.get(i).isout) {
                System.out.println(players.get(i).name + "\t" + players.get(i).score);
            }else if (players.get(i).isPlaying) {
                System.out.println(players.get(i).name + "\t" + players.get(i).score + "*");
            } else if (players.get(i).score != 0 ){
                System.out.println(players.get(i).name + "\t" + players.get(i).score);
            } 
            else {
                System.out.println(players.get(i).name + "\tNot yet bat");
            }
        }
        System.out.println("=============================================");
    }
    
    void showTop3() {
        
    List<Player> tempPlayers = players;
    
    Collections.sort(tempPlayers);
        
    System.out.println("=============================================");
        System.out.println("Name \t Score");
        for(int i=0 ;i<3;i++){
                System.out.println(tempPlayers.get(i).name + "\t" + tempPlayers.get(i).score);
        }
        System.out.println("=============================================");
    }
}

public class Game {

    public static void main(String[] args) {
        
        Scanner scan =  new Scanner(System.in);
        
        Scorer scorer = new Scorer();
        
        while(true){
             System.out.println("*******************************");
            System.out.println("\n1.Add Player \n2.Playing \n3.Scoreboard \n4.Top 3 bastman \n5.Exit \n Choose Option : ");
            System.out.println("*******************************");
            int option = scan.nextInt();
            
            switch(option){
                case 1:
                    if (scorer.players.size() == 11){
                    System.out.println("Team is Full !");
                    }
                    else {
                        
                        while(scorer.players.size() < 11){
                        System.out.print("\nEnter name of player "+ (scorer.idProvider + 1) +" :");
                        String tempName = scan.next();
                        
                        System.out.print("Enter Age of player "+ (scorer.idProvider + 1) + " :");
                        int tempAge = scan.nextInt();
                        
//                        System.out.print("Enter Score of player "+ scorer.idProvider+ " :");
//                        int tempScore = scan.nextInt();
                        
                        scorer.addPlayer(tempName, tempAge, 0);
                        }
                    }
                    break;
                case 2:
                            System.out.println("*******************************");
                        System.out.println("1.Current Playing \n2.Show out players \n3.Update Player Score \n4.Out Player\n5.Add in Play");
                        System.out.println("*******************************");
                        int optionfor2time = scan.nextInt();
                        
                        switch(optionfor2time){
                            case 1:
                                scorer.getCurrentPlayingPlayerList();
                                break;
                            case 2:   
                                scorer.getoutPlayerList();
                                break;
                            case 3:
                                System.out.print("Enter Score name player :");
                                String playerNameForAddIn = scan.next();
                                
                                System.out.print("Enter Score of player :");
                                int addScore = scan.nextInt();
                                
                                scorer.updateScore(playerNameForAddIn, addScore);
                                break;
                                
                            case 4:
                                System.out.print("Enter Name of player :");
                                String outByname = scan.next();
                                scorer.outBy(outByname);
                                break;
                            case 5:
                                System.out.print("Enter Name of player :");
                                String addIn = scan.next();
                                scorer.addcurrentPlaying(addIn);
                               break;
                            
                            default:
                                System.out.println("Invalid Option");      
                        }
                            
                            
                            break;
                case 3:
                    System.out.println("Scoreboard");
                    
                    if (scorer.players.size() < 11){
                        System.out.println("Please Add Team First !");
                    }
                    else {
                        scorer.showScore();
                    }
                    break;
                case 4:
                    scorer.showTop3();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid Option");
            }
        }
        
    }
    
}
