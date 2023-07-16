import java.util.PriorityQueue;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import java.util.Comparator;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Anushya Shankar
 * @version 1.0
 * @userid ashankar92
 * @GTID 903826158
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class Sorting {

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n^2)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array entered or comparator is null.");
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[min]) < 0) {
                    min = j;
                }
            }

            swap(arr, min, i);
        }
    }

    /**
     * Swap two elements in an array.
     * @param arr the array the elements are in
     * @param a the index of one of the elements
     * @param b the index of the other element
     * @param <T> the data type
     */
    private static <T> void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * Implement cocktail sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array entered or comparator is null.");
        }

        boolean swapsMade = true;
        int startInd = 0;
        int endInd = arr.length - 1;
        int swapInd = 0;

        while (swapsMade) {
            swapsMade = false;
            for (int i = startInd; i < endInd; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    swapsMade = true;
                    swapInd = i;
                }
            }

            if (!swapsMade) {
                break;
            }

            endInd = swapInd;
            if (swapsMade) {
                swapsMade = false;
                for (int i = endInd; i > startInd; i--) {
                    if (comparator.compare(arr[i], arr[i - 1]) < 0) {
                        swap(arr, i, i - 1);
                        swapsMade = true;
                        swapInd = i;
                    }
                }
            }
            startInd = swapInd;
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array entered or comparator is null.");
        }

        if (arr.length > 1) {
            T[] left = (T[]) new Object[arr.length / 2];
            T[] right = (T[]) new Object[arr.length - left.length];

            for (int i = 0; i < left.length; i++) {
                left[i] = arr[i];
            }

            for (int i = 0; i < right.length; i++) {
                right[i] = arr[i + left.length];
            }

            mergeSort(left, comparator);
            mergeSort(right, comparator);

            rMerge(arr, comparator, left, right);
        }
    }

    /**
     * Private helper method for merge sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @param left the left sub array
     * @param right the right sub array
     * @param <T> the data type to sort
     */
    private static <T> void rMerge(T[] arr, Comparator<T> comparator, T[] left, T[] right) {
        int i = 0;
        int j = 0;

        for (int k = 0; k < arr.length; k++) {
            if (j >= right.length || (i < left.length && comparator.compare(left[i], right[j]) <= 0)) {
                arr[k] = left[i++];
            } else {
                arr[k] = right[j++];
            }
        }
    }

    /**
     * Implement kth select.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param k          the index to retrieve data from + 1 (due to
     *                   0-indexing) if the array was sorted; the 'k' in "kth
     *                   select"; e.g. if k == 1, return the smallest element
     *                   in the array
     * @param arr        the array that should be modified after the method
     *                   is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @return the kth smallest element
     * @throws java.lang.IllegalArgumentException if the array or comparator
     *                                            or rand is null or k is not
     *                                            in the range of 1 to arr
     *                                            .length
     */
    public static <T> T kthSelect(int k, T[] arr, Comparator<T> comparator,
                                  Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Array entered or comparator or random is null.");
        }
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("K is not in the range of 1 to arr.length.");
        }

        return rKthSelect(k, arr, comparator, rand, 0, arr.length - 1);
    }

    /**
     * Private helper method for kth select.
     * @param k the index to retrieve data from + 1 (due to
     *          0-indexing) if the array was sorted; the 'k' in "kth
     *          select"; e.g. if k == 1, return the smallest element
     *          in the array
     * @param arr the array that should be modified after the method
     *            is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param start the start index
     * @param end the end index
     * @return the kth smallest element
     * @param <T> the data type to sort
     */
    private static <T> T rKthSelect(int k, T[] arr, Comparator<T> comparator, Random rand, int start, int end) {
        int pivotIdx = rand.nextInt(end - start + 1) + start;
        T pivotVal = arr[pivotIdx];

        arr[pivotIdx] = arr[start];
        arr[start] = pivotVal;

        int i = start + 1;
        int j = end;

        while (i <= j) {
            while (i <= j && comparator.compare(pivotVal, arr[i]) >= 0) {
                i++;
            }
            while (i <= j && comparator.compare(pivotVal, arr[j]) <= 0) {
                j--;
            }

            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        arr[start] = arr[j];
        arr[j] = pivotVal;

        if (j == k - 1) {
            return arr[j];
        } else if (j < k - 1) {
            return rKthSelect(k, arr, comparator, rand, j + 1, end);
        } else {
            return rKthSelect(k, arr, comparator, rand, start, j - 1);
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need. The number of iterations
     * can be determined using the number with the largest magnitude.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array entered is null.");
        }

        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < 19; i++) {
            buckets[i] = new LinkedList<>();
        }

        int mod = 10;
        int div = 1;
        boolean cont = true;

        while (cont) {
            cont = false;
            for (int num : arr) {
                int bucket = num / div;
                if (bucket / 10 != 0) {
                    cont = true;
                }

                if (buckets[bucket % mod  + 9] == null) {
                    buckets[bucket % mod  + 9] = new LinkedList<>();
                }

                buckets[bucket % mod  + 9].add(num);
            }

            int arrInd = 0;
            for (LinkedList<Integer> bucket : buckets) {
                if (bucket != null) {
                    for (int num : bucket) {
                        arr[arrInd++] = num;
                    }
                    bucket.clear();
                }
            }

            div *= 10;
        }
    }

    /**
     * Implement heap sort.
     *
     * It should be:
     * out-of-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Use java.util.PriorityQueue as the heap. Note that in this
     * PriorityQueue implementation, elements are removed from smallest
     * element to largest element.
     *
     * Initialize the PriorityQueue using its build heap constructor (look at
     * the different constructors of java.util.PriorityQueue).
     *
     * Return an int array with a capacity equal to the size of the list. The
     * returned array should have the elements in the list in sorted order.
     *
     * @param data the data to sort
     * @return the array with length equal to the size of the input list that
     * holds the elements from the list is sorted order
     * @throws java.lang.IllegalArgumentException if the data is null
     */
    public static int[] heapSort(List<Integer> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data entered is null.");
        }

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(data);

        int[] sorted = new int[data.size()];

        for (int i = 0; i < data.size(); i++) {
            sorted[i] = queue.remove();
        }

        return sorted;
    }
}
