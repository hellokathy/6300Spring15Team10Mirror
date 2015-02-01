# **Requirements Document -- Team 10**

General requirements (from videos):  
The program must be written in Java and must not make use of any nonstandard Java libraries. You will be tested on a machine with the vanilla installation of Java 1.6 (Instructor Notes: Even if the video says otherwise, it is fine to use Java 1.7 for this project.). Your program must compile on the command line using the javac command without any additional options. All code required to execute the program that is not part of the standard JDK, must be included as source code with your program. Your program should be an application, that is, it should have a main method and should be executable from the command line using the Java command.

The user should be able to provide a file path to the file they wish to be analyzed as a command line argument. Users should be able to specify which delimiters count as sentence separators, using the flag -d, defaulting to Lauren's initial thoughts on what should be used as delimiters (Please note that the comma is not to be used as a default sentence delimiter. Please also consider Lauren's sentence about the minimal word length as meaning "3 letters and above."). The user should be able to specify a lower limit for word length, using the flag -l, defaulting to Lauren's guess at what value might be good. Finally, the program's output should be the average sentence length, rounded down to the nearest integer.

##1 User Requirements

###1.1 User Characteristics

The intended users of the product should at least
a)be able to write in English
b)know how to use command line


###1.2 System's functionality

I would like the system to help me calculate the average number of words of the sentences in my essay. And it would be more helpful if it has functions that can specify a lower limit and/or higher limit for word length and allow me to specify file path and delimiters.


###1.3 User Interfaces

All users will have to use this system through command line.


##2 System Requirements

###2.1 Functional Requirements
Req#1
The system should accept user specified file path.

Req#2
The system should accept user specified delimiters (,;.!) with -d. 

Req#3
The system should accept user specified word length (above 3 letters) with -l.

Req#4
The system should output the average sentence length, rounded down to the nearest integer.

Req#5
The system should output a prompt for incorrect input regarding delimiters and/or file types.

###2.2 Non-Functional Requirements
Req#1
The system should be portable to Windows, Linux and Mac operating systems.

Req#2
The system should be capable of dealing with different document formats.

Req#3
The system should be stable and not crash for an invalid input.