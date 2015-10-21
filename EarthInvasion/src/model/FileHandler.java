package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The class FileHandler contains read and write methods. The class read and
 * writes to a specific file called "booklist.ser", if the file is not found it
 * will create a new file with that specific name.
 *
 * @author Christian Ekenstedt & Joachim Zetterman
 */
@SuppressWarnings("unchecked")
public class FileHandler {
    static String newline = System.getProperty("line.separator");

    /**
     * The method read reads the file "booklist.ser" and deserializes the data.
     * If the file is not found, it create a new .ser file and returns null.
     *
     * @return books, a reference to a temporary list of the type Book. Returns
     * null if the file is not found.
     * @throws Exception
     */
    public String read() throws Exception {
        String line;
        String name;
        int score;
        StringTokenizer tokenizer;
        String highscoreString = "";

        BufferedReader fin = null;

        try {
            fin = new BufferedReader(new FileReader("highscores.txt"));

            line = fin.readLine(); 	// Read a line from the file...
            while (line != null) {	// ...while there's more to read.

                try {
                    tokenizer = new StringTokenizer(line);
                    highscoreString += tokenizer.nextToken() + "\t\t" + tokenizer.nextToken() + "\t\t" + tokenizer.nextToken() + "\n";
                } catch (NumberFormatException ne) {
                    System.out.println("Input mismatch. Skipping this line.");
                }
                line = fin.readLine();  // Read another line	
            }
        } catch (FileNotFoundException fe) {
            System.out.println("Can not find the specified file.");
            throw fe; // "do not nail your corpse in the upright position"
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException ie) {
            }
            
        }
        return highscoreString;

    }

    /**
     * The method write serializes data to the file booklist.ser
     *
     * @throws Exception
     */
    public void write(String highscoresString) throws Exception {
        PrintWriter fout = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("highscores.txt");
            fout = new PrintWriter(new BufferedWriter(fw));
            // Writing to file...
            fout.println(highscoresString);
        } finally {
            if (fout != null) {
                fout.close();
            }
        }
    }
}
