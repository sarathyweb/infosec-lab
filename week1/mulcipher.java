import java.util.Scanner;
public class mulcipher
{
    public static String letters = "abcdefghijklmnopqrstuvwxyz";
    public static String encrypt(String msg, int key)
    {
        msg=msg.toLowerCase();
        String res="";
        for (int i=0;i<msg.length();i++)
        {
            int position=letters.indexOf(msg.charAt(i));
            int res_val=(key*position)%26;
            char c=letters.charAt(res_val);
            res += c;
        }
        return res;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String for Encryption: ");
        String text = new String();
        text = sc.nextLine();
        System.out.println(encrypt(text, 3));
        sc.close();
    }
}
