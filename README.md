# NetFileOpt-Buffer-Size-Efficiency

This system consists of a server and client application for transferring files over a network connection. The purpose of this file transfer system is to provide a simple and efficient way to transfer files between devices connected to a network. It also serves as a tool for analyzing the efficiency of different buffer sizes in file transfer operations. By testing various buffer sizes, users can determine the optimal buffer size for their network environment, maximizing file transfer speed and efficiency.

## Server

The server application listens for incoming connections from clients and sends files to them upon request.

### Instructions For Server

1. **Development Environment:** This application was developed using NetBeans IDE.

2. **Compile:** Compile the `Server.java` file using a Java compiler.

3. **Run:** Execute the compiled `Server.class` file to start the server.

4. **Client Connectivity:** Ensure that clients know the IP address and port number of this server to connect and request files.

5. **File Transfer:** Clients can connect to the server and request files. Upon connection, the server sends the requested file to the client.

### Additional Notes

- By default, the server listens on port 5000 for incoming connections. You can modify this port in the source code if needed.
- Files to be transferred should be placed in the specified directory.
- Ensure that firewall settings allow incoming connections on the specified port.

### Network Protocols

The server and client communicate using TCP/IP protocols for reliable and ordered data transmission.

### Dependencies

- Java Development Kit (JDK)
- NetBeans IDE

### Author

Michael Smith

## Client

The client application connects to a file transfer server to request and receive files over a network connection.

### Instructions

1. **Development Environment:** This application was developed using NetBeans IDE.

2. **Compile:** Compile the `Client.java` file using a Java compiler.

3. **Run:** Execute the compiled `Client.class` file to start the client.

4. **Server Connectivity:** Ensure that the client knows the IP address and port number of the server to connect and request files.

5. **File Request:** Connect to the server and request the desired file. The server will transfer the requested file to the client.

### Additional Notes

- By default, the client connects to the server IP address and port 5000. Modify the server details in the source code if necessary.
- Ensure that firewall settings allow outgoing connections to the server.
- The transferred file is saved in the specified directory.

### Dependencies

- Java Development Kit (JDK)
- NetBeans IDE

### Author

Michael Smith
