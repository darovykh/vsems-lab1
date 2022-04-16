import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    public static String cleanFromPunctuationSigns(String content) {
        return content.replaceAll("[^A-Za-z ]", "");
    }

    public static String [] splitByWords(String content) {
        return content.split(" ");
    }

    public static String [] findDistinctWords(String [] words) {
        String uniqueAsString = "";
        for(int i = 0; i < words.length; i++){
            words[i] = words[i].toLowerCase();
            if(!uniqueAsString.contains(words[i])) {
                uniqueAsString += words[i] + " ";
            }
        }

        return uniqueAsString.split(" ");
    }

    public static String [] sortByAlphabet(String [] words) {
        Arrays.sort(words);
        return words;
    }

    public static String getLongestWord(String [] words){
        String longestWord = words[0];
        for(int i = 1; i < words.length; i++) {
            if(words[i].length() > longestWord.length()) {
                longestWord = words[i];
            }
        }
        return longestWord;
    }

    public static int getLinesNumberWithGivenWord(String content, String word) {
        content = content.replaceAll("[^A-Za-z " + System.lineSeparator() + "]", "");
        String [] lines = content.split(System.lineSeparator());

        int lineNumber = 0;
        for(int i = 0; i < lines.length; i++) {
            String [] words = splitByWords(lines[i]);
            for(int j = 0; j < words.length; j++) {
                if(word.equals(words[j])) {
                    lineNumber++;
                    break;
                }
            }
        }

        return lineNumber;
    }

    public static int getWordsNumberBeginsWithGivenLetter(String [] words, String letter){
        int wordsNumber = 0;
        for(String i: words) {
            if(letter.equals(i.charAt(0) + "")) {
                wordsNumber++;
            }
        }
        return wordsNumber;
    }

    public static int [] getArrayOfHashes(Object [] objects) {
        int [] hashes = new int[objects.length];
        for(int i =0; i < objects.length; i++) {
            hashes[i] = objects[i].hashCode();
        }
        return hashes;
    }

    public static int [] findDistinctNumbers(int [] numbers) {
        return Arrays.stream(numbers).distinct().toArray();
    }

    public static int [] countIntersections(int [] numbers) {
        int [] distincts = findDistinctNumbers(numbers);
        int [] intersections = new int[distincts.length];
        for(int i = 0; i < distincts.length; i++) {
            intersections[i] = 0;
            for(int j = 0; j < numbers.length; j++) {
                if(distincts[i] == numbers[j]) {
                    intersections[i]++;
                }
            }
        }
        return intersections;
    }

    public static void main(String [] args) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("harry.txt")));
        String [] words = splitByWords(cleanFromPunctuationSigns(content));
        //1
        System.out.println("Longest word is: " + getLongestWord(words));
        //2
        System.out.println("Number of lines where word \"Harry\" exists: " + getLinesNumberWithGivenWord(content, "Harry"));
        //3
        String distincts = null;
        String [] arrayOfDistincts = findDistinctWords(words);
        for (int i = 0; i < arrayOfDistincts.length; i++) {
            if (i == 0) {
                distincts = "[" + arrayOfDistincts[i] + ", ";
            } else if(i == arrayOfDistincts.length - 1) {
                distincts += arrayOfDistincts[i] + "]";
            } else {
                distincts += arrayOfDistincts[i] + ", ";
            }
        }
        System.out.println("Distincts:" + System.lineSeparator() + distincts);
        //4
        System.out.println("Distincts that begin from the letter \"C\": "
                + getWordsNumberBeginsWithGivenLetter(arrayOfDistincts, "c"));
        //5
        int [] arrayOfHashes = getArrayOfHashes(words);
        String hashes = null;
        for (int i = 0; i < arrayOfHashes.length; i++) {
            if (i == 0) {
                hashes = "[" + arrayOfHashes[i] + ", ";
            } else if(i == arrayOfHashes.length - 1) {
                hashes += arrayOfHashes[i] + "]";
            } else {
                hashes += arrayOfHashes[i] + ", ";
            }
        }
        System.out.println("Array of hashes: " + System.lineSeparator() + hashes);
        //6
        int [] distinctHashes = findDistinctNumbers(arrayOfHashes);
        int [] intersectionsArray = countIntersections(arrayOfHashes);
        System.out.println("Hashes intersections number:");
        String intersections = null;
        for(int i = 0; i < intersectionsArray.length; i++) {
            if (i == 0) {
                intersections = "[" + distinctHashes[i] + " - " + intersectionsArray[i] + ", ";
            } else if(i == intersectionsArray.length - 1) {
                intersections += distinctHashes[i] + " - " + intersectionsArray[i] + "]";
            } else {
                intersections += distinctHashes[i] + " - " + intersectionsArray[i] + ", ";
            }
        }
        System.out.println(intersections);
    }

}