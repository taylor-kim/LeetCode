class Solution {
    public int countOperations(int num1, int num2) {
        return mySol(num1, num2);
    }

    public int mySol(int num1, int num2) {
        return topdown(num1, num2);
    }

    public int topdown(int num1, int num2) {
        if (num1 == 0 || num2 == 0) return 0;

        if (num1 >= num2) {
            return 1 + topdown(num1 - num2, num2);
        } else {
            return 1 + topdown(num1, num2 - num1);
        }
    }
}