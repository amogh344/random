import java.util.Scanner;

public class Leaky 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter bucket size: ");
        int bucketSize = sc.nextInt();

        System.out.print("Enter number of groups: ");
        int numGroups = sc.nextInt();

        int totalPackets = 0, requiredBandwidth = 0;

        for (int i = 0; i < numGroups; i++) 
        {
            System.out.print("Packets for group " + (i + 1) + ": ");
            int packets = sc.nextInt();

            System.out.print("Input bandwidth for group " + (i + 1) + ": ");
            int bandwidth = sc.nextInt();

            if ((totalPackets += packets) > bucketSize) 
            {
                int drop = totalPackets - bucketSize;
                System.out.println("Bucket Overflow! Dropped " + drop + " packet(s).");
                totalPackets = bucketSize;
                packets -= drop;
            }

            requiredBandwidth += packets * bandwidth;
        }

        System.out.println("Required bandwidth: " + requiredBandwidth);
        System.out.print("Enter output bandwidth: ");
        int outputBandwidth = sc.nextInt();

        while (requiredBandwidth > 0 && totalPackets > 0) 
        {
            totalPackets--;
            requiredBandwidth -= outputBandwidth;
            System.out.println(totalPackets + " packet(s) remaining. Remaining bandwidth: " + Math.max(requiredBandwidth, 0));
            if (requiredBandwidth < outputBandwidth && totalPackets > 0) 
            {
                System.out.println(totalPackets + " packet(s) cannot be sent due to insufficient bandwidth!");
                break;
            }
        }

        sc.close();
    }
}
