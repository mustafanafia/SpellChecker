import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class spellChecker <T extends String> extends BinarySearchTree {



    private static BinarySearchTree<String>[] dictionary = new BinarySearchTree[26];
    private static int correct=0;
    private static int inCorrect=0;



    public spellChecker() {
        for (int i = 0; i < 26; i++) {
            dictionary[i] = new BinarySearchTree<>();
        }
    }

    public String[] SymbolsRemover(String line) {
        line = line.toLowerCase();
        line = line.replaceAll("\n", "");
        line = line.replaceAll("'", ""); //replaces all apostrophes to concatenate words
        line = line.replaceAll("[^a-z]", " ");//replaces all non-alpha and non-space characters with a space
        line = line.replaceAll("[0-9]", "");
        line = line.replaceAll("\\s+", " ").trim(); //replaces all spaces two positions or more with one space
        String[] parts = line.split(" "); //split line by space into words parts is the array
        return parts;
    }  //symbol method



    public void InsertAndSortDictionary() throws FileNotFoundException {

        Scanner ScanDictionary = new Scanner(new File("C:\\Users\\musta\\Desktop\\random_dictionary.txt"));

        while (ScanDictionary.hasNext()) {

            String DictionaryWord = (ScanDictionary.next().toLowerCase());


            dictionary[DictionaryWord.charAt(0) - 'a'].insert(DictionaryWord);


        }
    }

    public void OliverFile() throws FileNotFoundException {

        Scanner ScanFile = new Scanner(new File("C:\\Users\\musta\\Desktop\\oliver.txt"));
        String[] FileWord;

        while (ScanFile.hasNext()) {

            FileWord = SymbolsRemover(ScanFile.next());

            for (int i = 0; i < FileWord.length; i++) {
                if (!FileWord[0].equals("")) {


                    if (dictionary[FileWord[i].charAt(0) - 'a'].search(FileWord[i]))
                        this.correct++;
                    else
                        this.inCorrect++;
                }
            }
        }
    }



    public static void main(String[] args) throws FileNotFoundException {


        spellChecker Object=new spellChecker();
        long startTime = System.currentTimeMillis();


        Object.InsertAndSortDictionary();
        Object.OliverFile();

        long endTime = System.currentTimeMillis();
        float totalTime = endTime - startTime;

        System.out.println("Correct words: " + Object.correct + "\n" + "Incorrect words: " + Object.inCorrect);
        System.out.println("Time: " + totalTime/1000);
    }

}






