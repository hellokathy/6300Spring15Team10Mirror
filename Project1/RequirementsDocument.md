# **Requirements Document -- Team 10**

General requirements (from videos):  
The program must be written in Java and must not make you any nonstandard Java libraries. You will be tested on a machine with the vanilla installation of Java 1.6. Your program must compile on the command line using the Javac command without any additional options. All code required to execute the program that is not part of the standard JDK, must be included as source code with your program. Your program should be an application. That is, it should have a main method and should be executable from the command line using the Java command.

The user should be able to provide a file path to the file they wish to be analyzed as a command line argument. User should be able to specify which delimiters count as sentence separators, using the flag -d, defaulting to Lauren's initial thoughts on what should be used as delimiters. The user should be able to specify a lower limit for word length, using the flag -l, defaulting to Lauren's guess at what value might be good. Finally, the program's output should be the average sentence length, rounded down to the nearest integer.

user specifies file path  
user specifies delimiters (,;.!) with –d  
user specifies word length –l (above 3 letters)  

##1 User Requirements

###1.1 User Characteristics

The intended users of the product should at least
a)be able to write in English
b)be familiar with computers and know how to use command line


###1.2 System's functionality

I would like the system to help me calculate the average number of words of the sentences in my essay. And it would be more helpful if it has functions that can specify a lower limit (and higher limit?) for word length and allow me to specify file path and delimiters.


###1.3 User Interfaces

All users will have to use this system through command line.


##2 System Requirements

###2.1 Functional Requirements
Req#1
user specifies file path

Req#2
user specifies delimiters (,;.!) with –d 

Req#3
user specifies word length (above 3 letters) with –l 

Req#4
output the average sentence length, rounded down to the nearest integer


###2.2 Non-Functional Requirements


