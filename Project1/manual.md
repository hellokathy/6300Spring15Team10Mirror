Average Sentence Length User Manual:

This program will analyze a given text document and output the average number of words per sentence in that document.
The default definition of a word is any word which contains three or more characters.  This default can be changed (see explanation below).
The default "end of sentence" characters are periods, exclamation marks, and question marks.  This default can changed (see explanation below).

Required:
-Run the given executable via the command line
-Please specify as the first argument a path to the text document that you would like analyzed.
-Additionally, you may optionally use the flags described above
Example input: ./Analyzer.exe "C:/Path/input.txt"
Example output: The average length of your sentences is 7 words.

Optional:
-Flag "-l" can be used to change the number of characters contained in an average word.  Please only use positive integers.
-Flag "-d" can be used to add additional "end of sentence" characters.
Example input: ./Analyzer.exe "C:/Path/input.txt" -l 5 -d %&
Example output: The average length of your sentences is 3 words.