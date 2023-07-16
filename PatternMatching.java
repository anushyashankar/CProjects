import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Your implementations of various string searching algorithms.
 *
 * @author Anushya Shankar
 * @version 1.0
 * @userid ashankar92
 * @GTID 903826158
 *
 * Collaborators: n/a
 *
 * Resources: n/a
 */
public class PatternMatching {

    /**
     * Knuth-Morris-Pratt (KMP) algorithm relies on the failure table (also
     * called failure function). Works better with small alphabets.
     *
     * Make sure to implement the buildFailureTable() method before implementing
     * this method.
     *
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text,
                                    CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot be null or empty.");
        }
        if (text == null || comparator == null) {
            throw new IllegalArgumentException("Text or comparator cannot be null.");
        }
        List<Integer> iList = new ArrayList<>();

        if (pattern.length() > text.length()) {
            return iList;
        }

        int[] shift = buildFailureTable(pattern, comparator);
        int i = 0;
        int j = 0;

        while (i + (pattern.length() - j - 1) < text.length()) {
            if (comparator.compare(text.charAt(i), pattern.charAt(j)) == 0) {
                if (j == pattern.length() - 1) {
                    iList.add(i - pattern.length() + 1);
                    i++;
                    j = shift[j];
                } else {
                    i++;
                    j++;
                }
            } else {
                if (j == 0) {
                    i++;
                } else {
                    j = shift[j - 1];
                }
            }
        }
        return iList;
    }

    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     *
     * The table built should be the length of the input pattern.
     *
     * Note that a given index i will contain the length of the largest prefix
     * of the pattern indices [0..i] that is also a suffix of the pattern
     * indices [1..i]. This means that index 0 of the returned table will always
     * be equal to 0
     *
     * Ex.
     * pattern:       a  b  a  b  a  c
     * failureTable: [0, 0, 1, 2, 3, 0]
     *
     * If the pattern is empty, return an empty array.
     *
     * @param pattern    a pattern you're building a failure table for
     * @param comparator you MUST use this to check if characters are equal
     * @return integer array holding your failure table
     * @throws java.lang.IllegalArgumentException if the pattern or comparator
     *                                            is null
     */
    public static int[] buildFailureTable(CharSequence pattern,
                                          CharacterComparator comparator) {
        if (pattern == null || comparator == null) {
            throw new IllegalArgumentException("Pattern or comparator is null");
        }

        int[] table = new int[pattern.length()];
        if (pattern.length() != 0) {
            table[0] = 0;
        }

        int i = 0;
        int j = 1;

        while (j < pattern.length()) {
            if (comparator.compare(pattern.charAt(j), pattern.charAt(i)) == 0) {
                table[j++] = ++i;
            } else {
                if (i != 0) {
                    i = table[i - 1];
                } else {
                    table[j++] = i;
                }
            }
        }

        return table;
    }

    /**
     * Boyer Moore algorithm that relies on last occurrence table. Works better
     * with large alphabets.
     *
     * Make sure to implement the buildLastTable() method before implementing
     * this method. Do NOT implement the Galil Rule in this method.
     *
     * Note: You may find the getOrDefault() method from Java's Map class
     * useful.
     *
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for the pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
                                           CharSequence text,
                                           CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot be null or be empty.");
        }
        if (text == null || comparator == null) {
            throw new IllegalArgumentException("Text or comparator cannot be null.");
        }

        List<Integer> iList = new ArrayList<>();

        if (text.length() < pattern.length()) {
            return iList;
        }

        Map<Character, Integer> table = buildLastTable(pattern);

        int i = 0;
        while (i <= text.length() - pattern.length()) {
            int j = pattern.length() - 1;
            while (j >= 0 && comparator.compare(text.charAt(i + j), pattern.charAt(j)) == 0) {
                j--;
            }

            if (j < 0) {
                iList.add(i++);
            } else {
                int shift = table.getOrDefault(text.charAt(i + pattern.length() - 1), -1);

                if (shift < j) {
                    i += j - shift;
                } else {
                    i++;
                }
            }
        }

        return iList;
    }

    /**
     * Builds last occurrence table that will be used to run the Boyer Moore
     * algorithm.
     *
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     *
     * Ex. pattern = octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     *
     * @param pattern a pattern you are building last table for
     * @return a Map with keys of all of the characters in the pattern mapping
     * to their last occurrence in the pattern
     * @throws java.lang.IllegalArgumentException if the pattern is null
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern is null");
        }

        Map<Character, Integer> table = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            table.put(pattern.charAt(i), i);
        }

        return table;
    }

    /**
     * Prime base used for Rabin-Karp hashing.
     * DO NOT EDIT!
     */
    private static final int BASE = 113;

    /**
     * Runs the Rabin-Karp algorithm. This algorithms generates hashes for the
     * pattern and compares this hash to substrings of the text before doing
     * character by character comparisons.
     *
     * When the hashes are equal and you do character comparisons, compare
     * starting from the beginning of the pattern to the end, not from the end
     * to the beginning.
     *
     * You must use the Rabin-Karp Rolling Hash for this implementation. The
     * formula for it is:
     *
     * sum of: c * BASE ^ (pattern.length - 1 - i)
     *   c is the integer value of the current character, and
     *   i is the index of the character
     *
     * We recommend building the hash for the pattern and the first m characters
     * of the text by starting at index (m - 1) to efficiently exponentiate the
     * BASE. This allows you to avoid using Math.pow().
     *
     * Note that if you were dealing with very large numbers here, your hash
     * will likely overflow; you will not need to handle this case.
     * You may assume that all powers and calculations CAN be done without
     * overflow. However, be careful with how you carry out your calculations.
     * For example, if BASE^(m - 1) is a number that fits into an int, it's
     * possible for BASE^m will overflow. So, you would not want to do
     * BASE^m / BASE to calculate BASE^(m - 1).
     *
     * Ex. Hashing "bunn" as a substring of "bunny" with base 113
     * = (b * 113 ^ 3) + (u * 113 ^ 2) + (n * 113 ^ 1) + (n * 113 ^ 0)
     * = (98 * 113 ^ 3) + (117 * 113 ^ 2) + (110 * 113 ^ 1) + (110 * 113 ^ 0)
     * = 142910419
     *
     * Another key point of this algorithm is that updating the hash from
     * one substring to the next substring must be O(1). To update the hash,
     * subtract the oldChar times BASE raised to the length - 1, multiply by
     * BASE, and add the newChar as shown by this formula:
     * (oldHash - oldChar * BASE ^ (pattern.length - 1)) * BASE + newChar
     *
     * Ex. Shifting from "bunn" to "unny" in "bunny" with base 113
     * hash("unny") = (hash("bunn") - b * 113 ^ 3) * 113 + y
     *              = (142910419 - 98 * 113 ^ 3) * 113 + 121
     *              = 170236090
     *
     * Keep in mind that calculating exponents is not O(1) in general, so you'll
     * need to keep track of what BASE^(m - 1) is for updating the hash.
     *
     * Do NOT use Math.pow() in this method.
     * Do NOT implement your own version of Math.pow().
     *
     * @param pattern    a string you're searching for in a body of text
     * @param text       the body of text where you search for pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> rabinKarp(CharSequence pattern,
                                          CharSequence text,
                                          CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot be null or empty.");
        }
        if (text == null || comparator == null) {
            throw new IllegalArgumentException("Text or comparator cannot be null.");
        }

        List<Integer> iList = new ArrayList<>();

        if (pattern.length() > text.length()) {
            return iList;
        }

        int patHash = pattern.charAt(0);
        int substrHash = text.charAt(0);
        int baseToTheMMin1 = 1;

        for (int i = 1; i < pattern.length(); i++) {
            patHash *= BASE;
            substrHash *= BASE;
            patHash += pattern.charAt(i);
            substrHash += text.charAt(i);
            baseToTheMMin1 *= BASE;
        }

        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            if (patHash == substrHash) {
                int j = 0;
                for (int k = i; j < pattern.length(); j++, k++) {
                    if (comparator.compare(text.charAt(k), pattern.charAt(j)) != 0) {
                        break;
                    }
                }

                if (j == pattern.length()) {
                    iList.add(i);
                }
            }

            if (i < text.length() - pattern.length()) {
                substrHash = ((substrHash - (text.charAt(i) * baseToTheMMin1)) * BASE)
                        + text.charAt(i + pattern.length());
            }
        }

        return iList;

    }

    /**
     * Calculate hash.
     * @param text the string that will get calculated
     * @return the hash value of the string
     */
    private static int hash(CharSequence text) {
        int output = 0;
        for (int i = 0; i < text.length(); i++) {
            output += text.charAt(i) * power(BASE, text.length() - 1 - i);
        }

        return output;
    }

    /**
     * Power method.
     * @param x the number that gets multiplied
     * @param y the number of times x gets multiplied
     * @return the value of x^y
     */
    private static int power(int x, int y) {
        if (y == 0) {
            return 1;
        } else {
            return x * power(x, y - 1);
        }
    }

    /**
     * Checks if text and pattern match.
     * @param pattern string searching for
     * @param text body of text where you are searching
     * @param comparator tool for checking equality
     * @return whether text and pattern match
     */
    private static boolean check(CharSequence pattern, CharSequence text, CharacterComparator comparator) {
        for (int i = 0;  i < pattern.length(); i++) {
            if (comparator.compare(text.charAt(i), pattern.charAt(i)) != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method is OPTIONAL and for extra credit only.
     *
     * The Galil Rule is an addition to Boyer Moore that optimizes how we shift the pattern
     * after a full match. Please read Extra Credit: Galil Rule section in the homework pdf for details.
     *
     * Make sure to implement the buildLastTable() method and buildFailureTable() method
     * before implementing this method.
     *
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for the pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> boyerMooreGalilRule(CharSequence pattern,
                                          CharSequence text,
                                          CharacterComparator comparator) {
        return null; // if you are not implementing this method, do NOT modify this line
    }
}
