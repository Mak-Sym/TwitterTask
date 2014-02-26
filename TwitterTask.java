//--- http://habrahabr.ru/post/200190/

package com.mak_sym.test;

public class TwitterTask {
    public static void main (String[] args) throws java.lang.Exception
    {
        int[] myIntArray = {2, 5, 1, 2, 3, 4, 7, 7, 6};
        int expected = 10;

        int[] testData1 = {1, 2, 1, 3, 2, 1, 0, 2, 4, 3, 1, 5, 3, 1, 4, 1, 2, 1, 0};
        int expectedResult1 = 17;
        int[] testData2 = {5, 1, 2, 1, 3, 2, 1, 0, 2, 4, 3, 1, 5, 3, 1, 4, 1, 2, 1, 0};
        int expectedResult2 = 40;
        int[] testData3 = {1, 3, 5, 1, 2, 1, 3, 2, 1, 0, 2, 4, 3, 1, 5, 3, 1, 4, 1, 2, 1, 0};
        int expectedResult3 = 40;
        int[] testData4 = {2, 1, 3, 5, 1, 2, 1, 3, 2, 1, 0, 2, 4, 3, 1, 5, 3, 1, 4, 1, 2, 1, 0};
        int expectedResult4 = 41;
        int[] testData5 = {2, 1, 3, 5, 1, 2, 1, 3, 2, 1, 0, 2, 4, 3, 1, 5, 3, 1, 4, 1, 2, 1, 0, 5, 2, 1, 3};
        int expectedResult5 = 62;
        int[] testData6 = {2};
        int expectedResult6 = 0;
        int[] testData7 = {2, 1};
        int expectedResult7 = 0;
        int[] testData8 = {2, 3, 1};
        int expectedResult8 = 0;

        test(expected, myIntArray);
        test(expectedResult1, testData1);
        test(expectedResult2, testData2);
        test(expectedResult3, testData3);
        test(expectedResult4, testData4);
        test(expectedResult5, testData5);
        test(expectedResult6, testData6);
        test(expectedResult7, testData7);
        test(expectedResult8, testData8);
    }

    public static void test(int expected, int[] array){

        System.out.println("TEST_V1");
        int result = calculateVolume_v1(array);
        if(result != expected) {
            System.out.println("[ERROR] Test failure! Expected: " + expected + " but was: " + result);
        } else {
            System.out.println("[INFO] Test successful!");
        }

        System.out.println("TEST_V2");
        result = calculateVolume_v2(array);
        if(result != expected) {
            System.out.println("[ERROR] Test failure! Expected: " + expected + " but was: " + result);
        } else {
            System.out.println("[INFO] Test successful!");
        }
    }

    public static int calculateVolume_v1(int[] land){
        int result = 0, waterOnTheLevel = 0, level = 0;

        while(waterOnTheLevel >= 0){
            result += waterOnTheLevel;
            waterOnTheLevel = calculateWaterOnTheLevel(level++, land);
        }

        return result;
    }

    private static int calculateWaterOnTheLevel(int level, int[] land) {
        int volume = 0;
        int left = 0, right = land.length - 1;

        while (left < land.length) {
            if(land[left] >= level){
                break;
            }
            ++left;
        }

        while (right >= 0) {
            if(land[right] >= level){
                break;
            }
            --right;
        }
        if(left == land.length || left == right) { //that means we're exceeded the maximum or nothing to calculate
            return -1;
        }

        while(left < right){
            if(land[left] < level) {
                ++volume;
            }
            ++left;
        }

        return volume;
    }

    public static int calculateVolume_v2(int[] land) {

        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = land.length - 1;
        int volume = 0;

        while(left < right) {
            if(land[left] > leftMax) {
                leftMax = land[left];
            }
            if(land[right] > rightMax) {
                rightMax = land[right];
            }
            if(leftMax >= rightMax) {
                volume += rightMax - land[right];
                right--;
            } else {
                volume += leftMax - land[left];
                left++;
            }
        }
        return volume;
    }
}
