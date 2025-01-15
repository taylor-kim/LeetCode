class Solution {
    public int minimizeXor(int num1, int num2) {
        return mySol(num1, num2);
    }

    public int mySol(int num1, int num2) {
        int count1 = Integer.bitCount(num1);
        int count2 = Integer.bitCount(num2);

        // /**
        // num1 : 01110000
        // x.   : 01100000
        
        //  */

        int x = num1;

        if (count1 == count2) {
            return x;
        } else if (count1 > count2) {
            int remove = count1 - count2;
            for (int i = 0; i < 32 && remove > 0; i++) {
                if (isSet(x, i)) {
                    x = erase(x, i);
                    remove--;
                }
            }
        } else {
            int set = count2 - count1;
            for (int i = 0; i < 32 && set > 0; i++) {
                if (!isSet(x, i)) {
                    x = set(x, i);
                    set--;
                }
            }
        }

        return x;
    }

    boolean isSet(int num, int pos) {
        return (num & (1 << pos)) > 0;
    }

    int set(int num, int pos) {
        return num | (1 << pos);
    }

    int erase(int num, int pos) {
        return num & (~(1 << pos));
    }
}