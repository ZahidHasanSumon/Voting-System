/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voting.system;

import java.util.Date;

/**
 *
 * @author Zahid Hasan
 */
public class Voter {
    public String hash;
    public String previousHash; 
    private String data; //our data will be a simple message.
    private long timeStamp; //as number of milliseconds since 1/1/1970.
    private int nonce; 
    private String voterId, candidate;

    public Voter(String voterId, String candidate, String previousHash) {
        this.voterId = voterId;
        this.candidate = candidate;
        this.timeStamp = new Date().getTime();
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }
    
    

  

    String calculateHash() {
        String calculatedhash = Hash.getMd5( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				voterId + candidate + previousHash 
				);
		return calculatedhash;
    }
    
    public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block ID : " + hash);
	}
    
    
    
}
