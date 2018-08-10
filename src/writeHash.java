import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class writeHash extends Window{
	
	public static /*void main(String[] args)*/int writeHashCodes() throws IOException {
		
		
			FileReader fr = new FileReader("keys.txt");
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter("hashKeys.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			int maxHashCode = 0;
			String line = new String();
			while ((line = br.readLine())!=null){
			
				int hash = getHashCode(line);
				//System.out.println(maxHashCode+" "+hash);
				if (maxHashCode < hash)
					maxHashCode  = hash;
				bw.write(Integer.toString(hash));
				bw.write("\n");
				
			}
			System.out.println(maxHashCode);
			br.close();
			bw.close();
			
			return maxHashCode;
	}
	
	
	
}
