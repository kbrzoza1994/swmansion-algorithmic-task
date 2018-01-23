package org.swmansion.algorithmic;


        /*Write a function that receives two sequences: A and B of integers and returns one sequence C.
        Sequence C should contain all elements from sequence A (maintaining the order) except those,
        that are present in sequence B p times, where p is a prime number.*/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {

        Integer[] arrayA = {};
        Integer[] arrayB = {};

        System.out.println(Arrays.toString(arrayFinder(arrayA, arrayB)));
        System.out.println(arrayFinderWithCollection(arrayA, arrayB).toString());
    }


    private static Integer[] arrayFinder(Integer[] arrayA, Integer[] arrayB) {

        Integer[] keyB = new Integer[arrayB.length];
        Integer[] valueB = new Integer[arrayB.length];
        int indexB = 0;

        for (Integer number : arrayB) {
            if (isArrayGotNumber(keyB, number)) {
                valueB[getIndexOfNumber(keyB, number)] += 1;
            } else {
                keyB[indexB] = number;
                valueB[indexB] = 1;
                indexB++;
            }
        }

        int counter = arrayA.length;
        Integer[] arrayTemp = new Integer[arrayA.length];
        int j = 0;

        for (Integer number : arrayA) {
            if (isArrayGotNumber(keyB, number) && isPrime(valueB[getIndexOfNumber(keyB, number)])) {
                counter--;
            } else {
                arrayTemp[j] = number;
                j++;
            }
        }

        Integer[] arrayC = new Integer[counter];
        System.arraycopy(arrayTemp, 0, arrayC, 0, arrayC.length);

        return arrayC;
    }


    private static ArrayList<Integer> arrayFinderWithCollection(Integer[] arrayA, Integer[] arrayB) {

        HashMap<Integer, Integer> mapB = new HashMap<>();
        ArrayList<Integer> arrayC = new ArrayList<>();

        for (Integer number : arrayB) {
            if (mapB.containsKey(number)) {
                mapB.replace(number, mapB.get(number) + 1);
            } else {
                mapB.put(number, 1);
            }
        }

        for (Integer anArrayA : arrayA) {
            if (!(mapB.containsKey(anArrayA) && isPrime(mapB.get(anArrayA)))) {
                arrayC.add(anArrayA);
            }
        }

        return arrayC;
    }


    private static boolean isPrime(Integer number) {

        if (number == 2) {
            return true;
        } else if (number % 2 == 0) {
            return false;
        } else {
            for (int i = 1; i * i <= number; i += 2) {
                if (number % i == 0)
                    return false;
            }
        }
        return true;
    }


    private static boolean isArrayGotNumber(Integer[] arr, Integer number) {
        for (Integer val : arr) {
            if (number == val)
                return true;
        }
        return false;
    }


    private static int getIndexOfNumber(Integer[] arr, Integer number) {
        for (int i = 0; i < arr.length; i++) {
            if (number == arr[i]) {
                return i;
            }
        }
        return -1;
    }
}
