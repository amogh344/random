import java.util.Scanner;

class CRC 
{
    public String computeCRC(String dv, String ds) 
    {
        while (dv.length() >= ds.length()) 
        {
            dv = Integer.toBinaryString(
                    Integer.parseInt(dv, 2) ^ (Integer.parseInt(ds, 2) << (dv.length() - ds.length()))
            );
        }
        return String.format("%16s", dv).replace(' ', '0');
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the data: ");
        String data = sc.next();
        String poly = "10001000000100001";
        
        String paddedData = data + "0".repeat(poly.length() - 1);
        CRC obj = new CRC();
        String checksum = obj.computeCRC(paddedData, poly);
        
        System.out.println("Checksum: " + checksum);
        System.out.println("Codeword: " + data + checksum);

        System.out.print("Enter received codeword: ");
        String receivedData = sc.next();
        String result = obj.computeCRC(receivedData, poly);
        System.out.println(result.equals("0".repeat(16)) ? "No error" : "Error in transmission");
    }
}
