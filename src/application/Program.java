package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Program {
	public static void main(String[] args) {
		
		Map<String, Integer> registers = new TreeMap<String, Integer>();
		
		String path = "C:\\temp\\in.txt";
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			String line = br.readLine();
			while (line != null) {
				
				String[] data = line.split(",");
				String candidateName = data[0];
				Integer candidateVotes = Integer.parseInt(data[1]);
				
				if(registers.containsKey(candidateName)) {
					int voteSoFar = registers.get(candidateName);
					registers.put(candidateName, candidateVotes + voteSoFar);
				}
				else {
					registers.put(candidateName, candidateVotes);
				}
				
				line = br.readLine();
			}
			
			String path2 = "C:\\temp\\out.csv";
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(path2))){
				for(String key : registers.keySet()) {
					bw.write(key + ": " + registers.get(key));
					bw.newLine();
				}
			}
			catch(IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}