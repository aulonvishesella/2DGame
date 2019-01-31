package search;

import java.util.ArrayList;
import static sun.net.www.http.HttpClient.New;

/**
 * Class of operations on ordered lists of strings. You should fill in the
 * bodies of these methods.
 */
public class Search {

    /**
     * Returns the number of unique elements in the list
     *
     * @param a list of strings, in ascending order
     * @return number of unique elements in the list.
     */
    public int countUnique(StringList a) {
        
        int repetition = 0; //set repetition to equal zero
        //invariant: o≤ countUnique < a.size()-1 and repetition  a[0]...a[i-1] 

        for (int countUnique = 0; countUnique < a.size() - 1; countUnique++) { //for loop, set countUnique to zero
            //loops as the countUnique less than
            //size of letter a -1.
            //Increment countUnique

            if (a.get(countUnique).equals(a.get(countUnique + 1))) {

                repetition++; //increment repetition.
            }
        }

        return a.size() - repetition;

    }

    /**
     * Returns the most commonly occurring string in the list. If two or more
     * are equally common, return the one that comes earliest.
     *
     * @param a collection of strings, in ascending order
     * @return most common string
     */
    public String mostCommon(StringList a) {
    
        String mostCommon = "NULL"; //set the string mostCommon to NULL.

        int mostCommonCount = 0;  // set the count of most ommon to 0

        int currentCount = 1; //set the integer of the current count to equal 1

        String lastString = a.get(0); //set the lastString to the first index at a.

        String current = "NULL";
        //Invariant : 1≤i<a.size() and currentCount a[1].....a[i-1] and mostCommonCount a[0]....a[i-1]
        for (int i = 1; i < a.size(); i++) {

            current = a.get(i); //set current to index at a.

            if (current.equals(lastString)) {  //if statement to see if the current value equals to mostCommonCount
                currentCount++; //increment currentCount if true.
            } else {
                if (currentCount > mostCommonCount) {

                    mostCommonCount = currentCount;

                    mostCommon = lastString;
                }
                currentCount = 1;
            }
            lastString = current;
        }
        return mostCommon;
    }

    /**
     * Search for a string in an ordered collection
     *
     * @param a collection of strings, in ascending order
     * @param k string to search for
     * @return position of an entry in a equal to k, if any, otherwise -1
     */
    public int findEqual(StringList a, String k) {
       
        int low = 0;
        
        int high = a.size() - 1; //n-1
        
        int middle = 0;

        
        
        while (low <= high) {
            middle = (low + high) / 2;
            if (a.get(middle).equals(k)) {
                return middle;
            } else if (k.compareTo(a.get(middle)) < 0) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;

    }

    /**
     * Position of a string in an ordered collection
     *
     * @param a collection of strings, in ascending order
     * @param k string to search for
     * @return number of strings in a less than k
     */
    public int countLess(StringList a, String k) {
   
        int low = 0;
        int high = a.size() - 1; //like saying high = n-1
        int middle;
        // invariant: 0 ≤ low ≤ high+1 ≤ a.size() and a[0..low-1] < k ≤ a[high+1..a.size()-1]
        while (low <= high) {
            middle = (low + high) / 2;

            if ((k).compareTo(a.get(middle)) > 0) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return low;
    }

    /**
     * Position after a string in an ordered collection
     *
     * @param a collection of strings, in ascending order
     * @param k string to search for
     * @return number of strings in a less than or equal to k
     */
    public int countLessOrEqual(StringList a, String k) {
        int low = 0;
        int high = a.size() - 1;
        int middle;
        // invariant: 0 ≤ low ≤ high+1 ≤ a.size() and a[0..low-1] ≤ k ≤ a[high+1..a.size()-1]
        while (low <= high) {
            middle = (low + high) / 2;

            if ((k).compareTo(a.get(middle)) >= 0) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return low;
    }

    /**
     * Determine the size of a range in an ordered collection
     *
     * @param a collection of strings, in ascending order
     * @param k1 first string to search for
     * @param k2 second string to search for (greater than or equal to k1)
     * @return number of strings between k1 and k2 (inclusive)
     */
    public int countBetween(StringList a, String k1, String k2) {

        return countLessOrEqual(a, k2) - countLess(a, k1);

    }


/**
 * Count partial matches in an ordered collection
 *
 * @param a collection of strings, in ascending order
 * @param prefix first string to search for
 * @param suffix second string to search for
 * @return number of strings with both the prefix and suffix
 */
 public int countMatches(StringList a, String prefix, String suffix) {       

   
     int i = countLess(a,prefix); 
    
     int matches = 0; //initiliase matches to equal zero
     
     boolean loop = true; //initilaise loop to true
     
     while(loop && i < a.size()){ //while loop that checks that BOTH loop and i are less than the size of the string a
     
         String currentString = a.get(i); //current string is equal to the index of the string a.
         
         if(currentString.startsWith(prefix)){ //if the current string starts with the prefix
         
             if(currentString.endsWith(suffix)){ //if the current string ends with that suffix.
             
                 matches++; //increment the matches
             }
             i++; //increment the index i
         }
         else{
             
             loop=false; //set loop to false
         }
     }
         return matches; //return the match
         
     }
        

}