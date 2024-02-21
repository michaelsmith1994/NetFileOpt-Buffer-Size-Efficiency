package server;
                                                                                                                                            
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        // Array of buffer sizes to test
        int[] bufferSizes = {1024 * 1024, 2 * 1024 * 1024, 5 * 1024 * 1024, 10 * 1024 * 1024}; // Buffer sizes: 1MB, 2MB, 5MB, 10MB
        long[] timeTotals = new long[bufferSizes.length];
        long totalAverageTime = 0;

        for (int sizeIndex = 0; sizeIndex < bufferSizes.length; sizeIndex++) {
            int bufferSize = bufferSizes[sizeIndex];
            long timeTotalForThisBufferSize = 0;

            for (int count = 1; count <= 10; count++) {
                ServerSocket serverSock = new ServerSocket(5000);
                System.out.println("Waiting for client...");
                Socket socket = serverSock.accept();
                System.out.println("Connection established for file " + count + " with buffer size: " + bufferSize);

                long startTime = System.currentTimeMillis();

                File file = new File("C:/Users/smith/Downloads/MOV00514.mpg");
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                OutputStream os = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(bufferSize); // Send buffer size to client first

                long fileLength = file.length();
                long current = 0;
                byte[] contents = new byte[bufferSize];

                while (current != fileLength) {
                    int bytesToRead = fis.read(contents);
                    os.write(contents, 0, bytesToRead);
                    current += bytesToRead;
                }

                os.flush();
                socket.close();
                serverSock.close();

                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                timeTotalForThisBufferSize += duration;
                System.out.println("File " + count + " finished with buffer size " + bufferSize + " in " + duration + " ms.");
            }

            timeTotals[sizeIndex] = timeTotalForThisBufferSize / 10; // Average time for this buffer size
            System.out.println("Average time for buffer size " + bufferSize + ": " + timeTotals[sizeIndex] + " ms");
            System.out.println("-------------------------------------------------------");
            
            totalAverageTime += timeTotals[sizeIndex];
        }

        // Print total average time
        System.out.println("Total Average Time for all buffer sizes: " + (totalAverageTime / bufferSizes.length) + " ms");
        
        // Determine the efficient buffer size
        long minTime = timeTotals[0];
        int minIndex = 0;
        for (int i = 1; i < timeTotals.length; i++) {
            if (timeTotals[i] < minTime) {
                minTime = timeTotals[i];
                minIndex = i;
            }
        }

        System.out.println("The most efficient buffer size is " + bufferSizes[minIndex] + " with an average time of " + minTime + " ms.");
    }
}
