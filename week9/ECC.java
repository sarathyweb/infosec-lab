import java.util.*;
public class ECC
{
    public static int inverseModulo(int a,int mod)
    {
        for(int i=0;i<mod;i++)
        {
            if(mod(a*i,mod) ==1){
                return i;
            }
        }
        return -1;
    }

    public static int mod(int a,int mod)
    {
        int result = a % mod;
        if(result<0)
            result = mod + result;
        return result;
    }

    public static int[] add(int[] point1,int[] point2,int a,int p)
    {
        int[] result = new int[2];
        int m = 0,num,den;
        if(point1[0] == point2[0])
        {
            m =mod((mod(((3*point1[0]*point1[0])+a),p)*inverseModulo(2*point1[1],p)),p);
        }
        else
        {
            m = mod((mod((point2[1]-point1[1]),p) * inverseModulo((point2[0]-point1[0]),p)),p);
        }
        //System.out.println("m:"+m);
        result[0] = mod((mod(m*m,p)-point1[0]-point2[0]),p);
        result[1] = mod((m*(point1[0]-result[0])-point1[1]),p);
        return result;
    }

    public static int[][] generateCyclicGroup(int[] point,int n,int a,int p)
    {
        int[][] group = new int[n-1][2];
        group[0] = point;
        for(int i=1;i<n-1;i++)
            group[i] = add(point,group[i-1],a,p);
        return group;
    }
    
	public static void main(String[] args) {
	    Scanner ip = new Scanner(System.in);
	    int p = 17,a = 2,b=2,n=19,na,nb;
	    int[] point1 = new int[2];
	    int[] pa = new int[2];
	    int[] pb = new int[2];
	    int[] ka = new int[2];
	    int[] kb = new int[2];
	    int[][] group = new int[n-1][2];
	    point1[0] = 5;
	    point1[1] = 1;
	    group = generateCyclicGroup(point1,n,a,p);
		System.out.println("..........Elliptic Curve Diffie Hellman..........");
		System.out.println("Enter na value :");
		na = Integer.parseInt(ip.nextLine());
		System.out.println("Enter na value :");
		nb = Integer.parseInt(ip.nextLine());
		pa = group[na-1];
        System.out.println("Pa calculated by Alice:   ("+pa[0]+", "+pa[1]+")");
        pb = group[nb-1];
        System.out.println("Pb calculated by Bob:   ("+pb[0]+", "+pb[1]+")");
		for(int i=0;i<n-1;i++)
		{
		    if(group[i]==pb)
		        ka = group[(((i+1)*na)%n)+1];
		}
        System.out.println("Ka calculated by Alice:   ("+ka[0]+", "+ka[1]+")");
        for(int i=0;i<n-1;i++)
		{
		    if(group[i]==pa)
		        kb = group[(((i+1)*nb)%n)+1];
		}
        System.out.println("Kb calculated by Bob:   ("+kb[0]+", "+kb[1]+")");
	}
}
