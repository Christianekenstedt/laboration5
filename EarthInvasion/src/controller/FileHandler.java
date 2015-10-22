package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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
    
    
    private String savedScore;
    private final String PATH;
    private String savedLevel;

    public FileHandler() {
        PATH = "highscores.txt";
    }
    

    /**
     * The method read reads the file "booklist.ser" and deserializes the data.
     * If the file is not found, it create a new .ser file and returns null.
     *
     * @return books, a reference to a temporary list of the type Book. Returns
     * null if the file is not found.
     * @throws Exception
     */
    public void read() throws Exception {
        String line;
        String name;
        int score, level;
        StringTokenizer tokenizer;
        String highscoreString = "";

        BufferedReader fin = null;

        try {
            fin = new BufferedReader(new FileReader(PATH));

            line = fin.readLine(); 	// Read a line from the file...
            while (line != null) {	// ...while there's more to read.

                try {
                    tokenizer = new StringTokenizer(line);
                    
                    
                    //ArrayList<String> s = new ArrayList<>();
                    
                    name = tokenizer.nextToken();
                    level =Integer.parseInt(tokenizer.nextToken());
                    score = Integer.parseInt(tokenizer.nextToken());
                    highscoreString +=name + "\t\t" + level + "\t\t" + score + "\n";
                    
                    
                } catch (NumberFormatException ne) {
                    System.out.println("Input mismatch. Skipping this line.");
                }
                line = fin.readLine();  // Read another line	
            }
        } catch (FileNotFoundException fe) {
            System.out.println("Can not find the specified file, creating a new.");
            
            
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException ie) {
            }
            
        }
        
        savedScore = highscoreString;
        

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
            fw = new FileWriter(PATH);
            fout = new PrintWriter(new BufferedWriter(fw));
            // Writing to file...
            fout.println(savedScore+highscoresString);
        } finally {
            if (fout != null) {
                fout.close();
            }
        }
    }

    public String getSavedScore() {
        return savedScore;
    }

    public String getSavedLevel() {
        return savedLevel;
    }
    
}
