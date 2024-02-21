package client;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
public class Client {
    
    public static void main(String[] args) throws Exception{
        int i = 1;
        while(i <= 10){   
            // File chunks are sent in size 5MB, multiples of 1024 are known to be more efficient for system handling   
            Socket socket = new Socket(InetAddress.getByName("localhost"), 5000);
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            // Read the buffer size sent by the server
            int bufferSize = dis.readInt();
            byte[] contents = new byte[bufferSize];
            // This attempt is on windows thus the file location for the phone video is here and in the specified format, mac users should change to their file dir format requirements. 
            FileOutputStream fos = new FileOutputStream("C:/Users/smith/OneDrive - University of Houston-Clear Lake/Documents/transferredFile/MOV00514"+i+".mpg");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            InputStream is = socket.getInputStream();
            int bytesRead = 0;
            while((bytesRead=is.read(contents))!=-1)
            bos.write(contents, 0, bytesRead);
            bos.flush();
            socket.close();
            System.out.println("File saved");
            i++;    
        }
    }
}
