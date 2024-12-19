import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPUnified
{
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Run as (1) Server or (2) Client: ");
        int choice = sc.nextInt();

        if (choice == 1)
        {
            ServerSocket server = new ServerSocket(4000);
            System.out.println("Server started. Waiting for connection...");
            Socket connection = server.accept();
            System.out.println("Client connected.");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
            String fileName = in.readLine();

            try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName)))
            {
                String line;
                while ((line = fileReader.readLine()) != null)
                {
                    out.println(line);
                }
            }
            catch (FileNotFoundException e)
            {
                out.println("File not found");
            }
        }
        else
        {
            Socket client = new Socket("127.0.0.1", 4000);
            System.out.print("Enter file name: ");
            String fileName = sc.next();
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out.println(fileName);

            String line;
            System.out.println("File content:");
            while ((line = in.readLine()) != null)
            {
                System.out.println(line);
            }
        }
    }
}