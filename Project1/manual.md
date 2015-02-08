# **Average Sentence Length User Manual:**

This program will analyze a given text document and output the average number of words per sentence in that document.  
The default definition of a word is any word which contains three or more characters.  This default can be changed (see explanation below).  
The default "end of sentence" characters are periods, exclamation marks, and question marks. This default can be expanded (see explanation below).

**Required:**
-------------
-Run the given software via the command line using the java command and program name  
-Specify the path to the document to be analyzed as your first argument in quotations ("")  
**Example input:** java edu.gatech.seclass.prj1.Main "C:/Path/input.txt"  
**Example output:** The average length of your sentences is 7 words.  

**Optional:**
-------------
-Flags "-l" and "-d" can optionally be used to control certain options for the program.  
-Flag "-l" can be used to change the minimum number of characters contained in a word. Please only use positive integers.  
-Flag "-d" can be used to add additional "end of sentence" characters. (commas, colons, etc.)  
**Example input:** java edu.gatech.seclass.prj1.Main "C:/Path/input.txt" -l 5 -d %&  
**Example output:** The average length of your sentences is 3 words.  
