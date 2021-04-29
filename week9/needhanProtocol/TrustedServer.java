import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class TrustedServer {
    Socket socket = null;
    ServerSocket server = null;
    DataInputStream in = null;
    DataOutputStream out = null;
    Scanner input = null;
  
    public static void encrypt(int[] data,int n,int key) {
          for(int i=0;i<n;i++)
              data[i]+=key;
    }

    public static void decrypt(int[] data,int n,int key) {
          for(int i=0;i<n;i++)
              data[i]-=key;
    }

    public static int f(int[] data) {
        return data[1]+1;
    }

    public TrustedServer(int port){
        try{
            server = new ServerSocket(port);
            System.out.println("Server started.");
            System.out.println("Waiting for a client at port "+port+".....");
            socket = server.accept();
            System.out.println("Server accepted the client.");
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            input = new Scanner(System.in);
            out = new DataOutputStream(socket.getOutputStream());
            
            Map<Integer,Integer> keys = new HashMap<Integer,Integer>();
            keys.put(123,3);
            keys.put(234,4);
            
            int[] data = new int[5];
            String receiveData[] = in.readUTF().split(",");
            
            for(int i=0;i<receiveData.length;i++){
                data[i]=Integer.parseInt(receiveData[i]);
            }

            System.out.println("Alice id: "+data[0]+" Bob is: "+data[1]+" Nonce of Alice: "+data[2]);
            
            int ks = 12;
            int[] reply1 = new int[2];
            reply1[0]=ks;
            reply1[1]=data[0];
            encrypt(reply1,2,keys.get(data[1]));
            int[] reply2 = new int[5];
            reply2[0]=ks;
            reply2[1]=data[1];
            reply2[2]=data[2];
            reply2[3]=reply1[0];
            reply2[4]=reply1[1];
            
            System.out.println("Before encryption: ");
            System.out.println("Ks: "+reply2[0]+" Bob id: "+reply2[1]+" Alice nonce: "+reply2[2]);
            System.out.println("Encryption with kb:\nKs: "+reply2[3]+" Alice Id: "+reply2[4]);
            encrypt(reply2,5,keys.get(data[0]));
            System.out.println("After encryption: ");
            System.out.println("Ks: "+reply2[0]+" Bob id: "+reply2[1]+" Alice nonce: "+reply2[2]);
            System.out.println("Encryption with kb:\nKs: "+reply2[3]+" Alice Id: "+reply2[4]);
            
            String sendData = String.valueOf(reply2[0])+","+String.valueOf(reply2[1])+","+String.valueOf(reply2[2])+","+String.valueOf(reply2[3])+","+String.valueOf(reply2[4]);
            out.writeUTF(sendData);
            System.out.println("Closing connection....");
            socket.close();
            in.close();
            out.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
    public static void main(String args[])
    {
        TrustedServer TrustedServer = new TrustedServer(5000);
    }
}
