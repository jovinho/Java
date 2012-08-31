import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;


public class SimpleDNS {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Entre com um endereço ");
		String url = scanner.next();
		try {
			InetAddress address = InetAddress.getByName(url);
			byte[] IP = address.getAddress();
			for (int index = 0; index < IP.length; index++){
				if (index > 0){
					System.out.print(".");
				}
				System.out.print(((int)IP[index]) & 0xff);		
			}
			
			System.out.println();
		} catch (UnknownHostException e) {
			System.out.println("Não foi possivel localizar o host name");
			e.printStackTrace();
		}
		
		
	}

}
