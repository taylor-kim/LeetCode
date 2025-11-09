class Solution {
    public int countOperations(int num1, int num2) {
        return official(num1, num2);
    }

    public int official(int num1, int num2) {
        int ans = 0;

        while (num1 > 0 && num2 > 0) {
            ans += num1 / num2;

            num1 %= num2;

            num1 += num2;
            num2 = num1 - num2;
            num1 = num1 - num2;
        }

        return ans;
    }

    public int mySol2(int num1, int num2) {
        int ans = 0;

        while (num1 > 0 && num2 > 0) {
            if (num1 >= num2) {
                num1 -= num2;
            } else {
                num2 -= num1;
            }
            ans++;
        }

        return ans;
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