import java.net.*;
import java.io.*;

public class Client {
	public static void main(String[] args)
	{
		try {
			String pstr, gstr, Astr;
			String serverName = "localhost";
			int port = 8088;

			// Declare p, g, and Key of Client
			int p = 23;
			int g = 9;
			int a = 4;
			double Adash, serverB;

			
			System.out.println("Connecting to " + serverName
							+ " on port " + port);
			Socket Client = new Socket(serverName, port);
			System.out.println("Just connected to "
							+ Client.getRemoteSocketAddress());

			
			OutputStream outToServer = Client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);

			pstr = Integer.toString(p);
			out.writeUTF(pstr); // Sending p

			gstr = Integer.toString(g);
			out.writeUTF(gstr); // Sending g

			double A = ((Math.pow(g, a)) % p); 
			Astr = Double.toString(A);
			out.writeUTF(Astr); // Sending A

			
			System.out.println("From Client : Private Key = " + a);

			
			DataInputStream in = new DataInputStream(Client.getInputStream());

			serverB = Double.parseDouble(in.readUTF());
			System.out.println("From Server : Public Key = " + serverB);

			Adash = ((Math.pow(serverB, a)) % p); 

			System.out.println("Secret Key to perform Symmetric Encryption = "
							+ Adash);
			Client.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
