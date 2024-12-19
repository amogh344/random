import java.io.*;
import java.net.*;
import java.util.*;

class UDPUnified

{
    public static void main(String[] args) throws Exception
    {
        DatagramSocket socket = new DatagramSocket();
        Scanner sc = new Scanner(System.in);

        System.out.print("Run as (1) Server or (2) Client: ");
        int choice = sc.nextInt();

        if (choice == 1)
        {
            DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] buffer = new byte[1024];
            System.out.println("Server ready. Waiting for client...");
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(receivePacket);

            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received: " + message);
        }
        else
        {
            System.out.print("Enter a message: ");
            sc.nextLine(); // Consume newline
            String message = sc.nextLine();

            byte[] buffer = message.getBytes();
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 9876);
            socket.send(packet);

            System.out.println("Message sent.");
        }

        sc.close();
    }
}