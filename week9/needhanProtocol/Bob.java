import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Bob {
    
    Socket socket = null;
    ServerSocket server = null;
    DataInputStream in = null;
    DataOutputStream out = null;
    Scanner input = null;
  
    public static void encrypt(int[] data,int n,int key) {
          for(int i=0;i<n;i++){
              data[i]+=key;
          }
    }

    public static void decrypt(int[] data,int n,int key) {
          for(int i=0;i<n;i++){
              data[i]-=key;
          }
    }

    public static int f(int[] data) {
        return data[1]+1;
    }
    public Bob(int port){
        try{
            server = new ServerSocket(port);
            System.out.println("Server started.");
            System.out.println("Waiting for a client at port "+port+".....");
            socket = server.accept();
            System.out.println("Server accepted the client.");
            
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            input = new Scanner(System.in);
            out = new DataOutputStream(socket.getOutputStream());
            int kb = 4;
            int[] data=new int[2];
            String receiveData[] = in.readUTF().split(",");
            for(int i=0;i<receiveData.length;i++)
                data[i]=Integer.parseInt(receiveData[i]);
            decrypt(data,2,2);
            decrypt(data,2,kb);
            System.out.println("Ks: "+data[0]+" Bob nonce: "+data[1]);
            int check = data[1] = 13;
            System.out.println("Ks: "+data[0]+" Bob nonce: "+data[1]);
            check = f(data);
            encrypt(data,2,2);
            String senddata = String.valueOf(data[0])+","+String.valueOf(data[1]);
            out.writeUTF(senddata);
            System.out.println("check: "+check);
            receiveData = in.readUTF().split(",");
            for(int i=0;i<receiveData.length;i++){
                data[i]=Integer.parseInt(receiveData[i]);
            }
            
            decrypt(data,2,2);
            if(check==data[1])
                System.out.println("Authentication Completed!");
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
        Bob Bob = new Bob(6000);
    }
}
