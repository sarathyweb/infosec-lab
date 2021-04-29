    import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Alice {
    Socket socket = null;
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

    public Alice(String address,int port1,int port2){
        try{
            socket = new Socket(address,port1);
            System.out.println("Connected to Trusted server at Port "+port1);
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            input = new Scanner(System.in);
            out = new DataOutputStream(socket.getOutputStream());
            
            int ka = 3,ida = 123,idb = 234,na=11;
            int[] data = new int[5];
            data[0] = ida;
            data[1] = idb;
            data[2] = na;
            System.out.println("Alice Id: "+data[0]+" Bob id: "+data[1]+" Nonce of Alice: "+data[2]);
            
            String sendData = String.valueOf(data[0])+","+String.valueOf(data[1])+","+String.valueOf(data[2]);
            out.writeUTF(sendData);
            String receiveData[] = in.readUTF().split(",");
            for(int i=0;i<receiveData.length;i++){
                data[i]=Integer.parseInt(receiveData[i]);
            }

            decrypt(data,5,ka);
            System.out.println("Ks: "+data[0]+" Bob id: "+data[1]+" Nonce of Alice: "+data[2]);
            System.out.println("Encryption with kb:\nKs: "+data[3]+" Alice Id: "+data[4]);
            input.close();
            out.close();
            socket.close();
            
            socket = new Socket(address,port2);
            System.out.println("CONNECTED with Bob at Port "+port2);
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            input = new Scanner(System.in);
            out = new DataOutputStream(socket.getOutputStream());
            
            int[] bdata = new int[2];
            bdata[0]=data[3];
            bdata[1]=data[4];
            encrypt(bdata,2,2);
            sendData = String.valueOf(bdata[0])+","+String.valueOf(bdata[1]);
            out.writeUTF(sendData);
           
            receiveData = in.readUTF().split(",");
            for(int i=0;i<receiveData.length;i++){
                bdata[i]=Integer.parseInt(receiveData[i]);
            }
            
            decrypt(bdata,2,2);
            System.out.println("ks: "+bdata[0]+" Bob nonce: "+bdata[1]);
            bdata[1]=f(bdata);
            System.out.println("ks: "+bdata[0]+" Bob nonce: "+bdata[1]);
            
            encrypt(bdata,2,2);
            sendData = String.valueOf(bdata[0])+","+String.valueOf(bdata[1]);
            out.writeUTF(sendData);
            input.close();
            out.close();
            socket.close();
        }
        catch(Exception e)
        {
          System.out.println(e);
        }
    }
    public static void main(String[] args) {
        Alice Alice = new Alice("127.0.0.1", 5000,6000);
    }
}
