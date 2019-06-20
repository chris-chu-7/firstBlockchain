import java.util.Date;
import java.security.MessageDigest;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class blockChain {
	private String hash;
	private String prevHash;
	private String data;
	private long timeStamp;
	private int nonce; 
	public static int difficulty = 5;
	
	public static ArrayList<blockChain> block = new ArrayList<blockChain>();
	
	public blockChain(String data, String prevHash) {
		this.data = data;
		this.prevHash = prevHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	
	//generates a current sha-256 to be contributed to the final hash. 
	public static String SHA256(String input) {
		String result = null;
		try {
			MessageDigest message = MessageDigest.getInstance("SHA-256");
			byte[] hash = message.digest(input.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for(int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			result = hexString.toString();
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public String calculateHash() {
		String hash = SHA256(prevHash + Long.toString(timeStamp) + data + Integer.toString(nonce));
		return hash;
	}
	
	public static boolean isChainValid() {
		blockChain currBlock;
		blockChain prevBlock;
		for(int j = 1; j < block.size(); j++) {
			currBlock = block.get(j);
			prevBlock = block.get(j - 1);
			if(!currBlock.hash.equals(currBlock.calculateHash())) {
				System.out.println("Current hashes not equal");
				return false; 
			}
//			if(!prevBlock.hash.contentEquals(prevBlock.prevHash)) {
//				System.out.println("Previous hashes not equal");
//				return false;
//			}
		}
		
		return true;
	}
	
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0');
		while(!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		
		System.out.println("Block Mined!!: " + hash + "\n\n");
	}
	
	public static void main(String[] args) {
		blockChain first = new blockChain("My name is Christopher Chu", "0");
		System.out.println("Hash for block 1 : " + first.hash);
		block.add(first);
		System.out.println("Mining block 1: ");
		first.mineBlock(difficulty);
		
		blockChain second = new blockChain("My social security number is 123-45-6789",first.hash);
		System.out.println("Hash for block 2 : " + second.hash);
		block.add(second);
		System.out.println("Mining block 2: ");
		second.mineBlock(difficulty);
		
		blockChain third = new blockChain("And my address is 1234 Cherry Ln",second.hash);
		System.out.println("Hash for block 3 : " + third.hash);
		block.add(third);
		System.out.println("Mining block 3: ");
		third.mineBlock(difficulty);
		
		String blockChainJSON = new GsonBuilder().setPrettyPrinting().create().toJson(block);
		System.out.println("The following blockchain looks like this: ");
		System.out.println(blockChainJSON + "\n\n");
		
		if(isChainValid()) {
			System.out.println("Blockchain is valid! :)");
		} else {
			System.out.println("Blockchain is not valid. Try again :'(");
		}
		
	}
	
}
