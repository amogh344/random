import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPUnified
{
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Run as (1) Server or (2) Client: ");
        if (sc.nextInt() == 1)
        {
            try (
                ServerSocket server = new ServerSocket(4000);
                 Socket conn = server.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                 PrintWriter out = new PrintWriter(conn.getOutputStream(), true))
            {
                System.out.println("Server ready. Client connected.");
                String fileName = in.readLine();
                try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName)))
                {
                    fileReader.lines().forEach(out::println);
                }
                catch (FileNotFoundException e)
                {
                    out.println("File not found");
                }
            }
        }
        else
        {
            try (Socket client = new Socket("127.0.0.1", 4000);
                 PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream())))
            {
                System.out.print("Enter file name: ");
                out.println(sc.next());
                System.out.println("File content:");
                in.lines().forEach(System.out::println);
            }
        }
    }
}
