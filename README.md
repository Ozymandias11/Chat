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
____________________________________________________________________________________________________________________
project 2: Penguin Company
This project involves implementing three packages: "tree," "penguinDate," and "company." In the "tree" package, a generic tree data structure is created, along with methods for node insertion, leaf determination, tree size calculation, node removal, and tree traversal. The "penguinDate" package simulates penguins going on dates based on their genealogy tree distance. The "company" package models a company structure using generic data structures, including methods for adding, firing, and finding employees within the company. A CompanySimulation class handles input and performs corresponding operations for the company simulation.
____________________________________________________________________________________________________________________
project 3: Spotij
SpotiJy is a simple song manager program that allows users to store and manage data about songs, albums, and artists. The project consists of four classes - Song, Album, Artist, and SpotiJy. Each class handles specific data management tasks.

In the Song class, users can create and manage song objects with attributes such as title, release year, duration, and likes. The class provides methods to change the duration, like, and unlike songs. Songs can also be compared based on their attributes.

The Album class stores a collection of songs and provides methods to add songs, shuffle the songs, and sort them based on various criteria like title, duration, release year, and popularity.

The Artist class represents individual artists with attributes like first name, last name, birth year, albums, and singles. The class offers methods to find the most and least liked songs, total likes, and compare artists.

SpotiJy is the main class that manages a collection of artists. It provides methods to add artists, retrieve the top trending artist, album, and song based on the total number of likes from all songs across all albums and singles.

             
 
