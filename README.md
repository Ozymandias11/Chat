# Java-projects
Project 1: Multi-user Chat Application

Message Types
PinguChat supports five types of messages:
Direct Message: @username message sends a DM to the respective client.
WHOIS: If the client sends WHOIS, they receive a list of all currently connected clients and their connection time.
LOGOUT: If a client sends LOGOUT, their connection to the server is closed.
PINGU: If a client sends PINGU, all currently connected clients receive an important fact about penguins.
Ordinary Messages: All other messages are considered as ordinary messages and are sent to all connected clients.

Additional Functionality:
You are encouraged to introduce further functionality in the application. Once a client connects, they will receive a welcome message listing all available features.

Implementation Details:
Server
The server is implemented in Java and uses sockets to accept connections from clients.
Each established connection spawns an independent thread for communication with the client.
The server maintains a synchronized data structure of all currently connected clients.
Clients are removed from the data structure if their socket is closed or if there's an IO error while sending a message.
Client
The client application is implemented in Java and establishes a connection to the ChatServer via sockets.
All messages received are displayed on the console, and the user can write their own messages.

             
 
