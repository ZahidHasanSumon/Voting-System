/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voting.system;

import com.google.gson.GsonBuilder;
import java.io.*;
import java.lang.*;
import java.util.*;

/**
 *
 * @author Zahid Hasan
 */
public class VotingSystem {
    public static ArrayList<Voter> VoterRecord = new ArrayList<Voter>();
    private static String voterID, candidateOP;
    private static int i=0,select, candidateA=0, candidateB=0, candidateC=0;
    private static char addMore;
    public static int difficulty = 5;

  
   
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan=new Scanner(System.in);
        
//         String voterID[] = new String[3];
//         String candidateOP[] = new String[3];
         int option = 0;
         System.out.println("Choose Your Algorithm:\nPress 1 for algorithm 1\nPress 2 for algorithm 2");
         option = scan.nextInt();
         scan.nextLine();
         
         if(option == 1){
             long start = System.currentTimeMillis();


            

            do{
              
                    System.out.println("This is block "+(i+1));
                    
                    System.out.println("Enter Voter ID:");
                    voterID=scan.nextLine();
                    
                    System.out.println("Enter Candidate Option (A, B, C):");
                    candidateOP=scan.nextLine();

                if(i==0){
                    VoterRecord.add(new Voter(voterID, candidateOP, "0"));
                }else{
                    VoterRecord.add(new Voter(voterID, candidateOP,VoterRecord.get(VoterRecord.size()-1).hash));
                
                }
                System.out.println("Trying to Mine block "+(i+1)+"......");
		VoterRecord.get(i).mineBlock(difficulty);
                i++;
                 System.out.println("********************************************************");
                
                    
                System.out.println("Do you want to add more voter:Y/N");
                addMore=scan.next().charAt(0);
                scan.nextLine();
                    
                    
                }while(addMore=='Y' || addMore=='y');
                
                
                System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(VoterRecord);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
                
                System.out.println("Voting Results: ");
                
                for(int j = 0; j < VoterRecord.size(); j++){
                    if("A".equals(VoterRecord.get(j).getCandidate()) || "a".equals(VoterRecord.get(j).getCandidate())){
                        candidateA++;
                        
                    }else if("B".equals(VoterRecord.get(j).getCandidate()) || "b".equals(VoterRecord.get(j).getCandidate())){
                        candidateB++;
                    }else if("C".equals(VoterRecord.get(j).getCandidate()) || "c".equals(VoterRecord.get(j).getCandidate())){
                        candidateC++;
                    
                    }
                    
                }
                System.out.println("Cadidate A: " + candidateA + " votes" +"\nCadidate B: " + candidateB +" votes"+"\nCadidate C: " + candidateC +" votes");
                if(candidateA > candidateB && candidateA > candidateC){
                    System.out.println("Winner is candidate: A");
                
                }else if(candidateB > candidateA && candidateB > candidateC){
                    System.out.println("Winner is candidate: B");
                
                }else if(candidateC > candidateA && candidateC > candidateB){
                    System.out.println("Winner is candidate: C");
                }else{
                    System.out.println("Draw!!!!");
                }
                
                
                long end = System.currentTimeMillis();
                float sec = (end - start) / 1000F;
                System.out.println("Total execution time : "+ sec + " seconds");
            
         }else if(option == 2){
//             long start = System.currentTimeMillis();
//             for(int i = 0; i < voterID.length; i++){
//                System.out.println("Enter Voter ID " + (i+1) + ":");
//                voterID[i] = scan.nextLine();
//                System.out.println("Enter Candidate Option for voter " + (i+1) + "(A, B, C) :");
//                candidateOP[i] = scan.nextLine();
//                System.out.println("*********************************");
//                System.out.println("*********************************");
//                
//                for(int j = 0; j <= i; j++){
//                    
//                    System.out.println(voterID[j]+ "   |   " + candidateOP[j]);
//                }
//
//            }
//             long end = System.currentTimeMillis();
//             float sec = (end - start) / 1000F;
//             System.out.println("Total execution time : "+sec + " seconds");
             
             
         }
         
         
        
    }
    public static Boolean isChainValid() {
		Voter currentBlock; 
		Voter previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < VoterRecord.size(); i++) {
			currentBlock = VoterRecord.get(i);
			previousBlock = VoterRecord.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}

 
    
}
