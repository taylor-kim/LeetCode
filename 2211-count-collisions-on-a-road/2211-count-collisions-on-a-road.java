class Solution {
    public int countCollisions(String directions) {
        return mySol(directions);
    }

    public int mySol(String directions) {
        int ans = 0;

        char[] arr = directions.toCharArray();

        int left = 0;
        int right = arr.length - 1;

        while (left < arr.length && arr[left] == 'L') {
            left++;
        }

        while (right >= 0 && arr[right] == 'R') {
            right--;
        }

        for (int i = left; i <= right; i++) {
            if (arr[i] != 'S') ans++;
        }

        return ans;
    }
}