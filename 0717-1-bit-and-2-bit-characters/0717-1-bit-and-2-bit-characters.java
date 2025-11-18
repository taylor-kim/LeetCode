class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        return mySol2(bits);
    }

    public boolean mySol2(int[] bits) {
        return topdown(bits, 0);
    }

    public boolean topdown(int[] bits, int index) {
        if (index == bits.length) return false;
        if (index == bits.length - 1) return bits[index] == 0;

        if (bits[index] == 0) {
            return topdown(bits, index + 1);
        } else {
            return topdown(bits, index + 2);
        }
    }

    public boolean mySol_fail(int[] bits) {
        if (bits.length % 2 == 0) return false;

        int n = bits.length;

        if (bits[n - 1] == 1) return false;

        for (int i = n - 3; i >= 0; i -= 2) {
            if (bits[i] == 0) return false;
        }
        
        return true;
    }
}