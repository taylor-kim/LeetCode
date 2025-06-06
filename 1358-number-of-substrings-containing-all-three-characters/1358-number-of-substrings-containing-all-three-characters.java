class Solution {
    public int numberOfSubstrings(String s) {
        return official_track_last_pos(s);
    }

    public int official_track_last_pos(String s) {
        int ans = 0;
        int[] pos = {-1,-1,-1};
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];

            pos[c - 'a'] = i;

            ans += 1 + Math.min(pos[0], Math.min(pos[1], pos[2]));
        }

        return ans;
    }


    public int mySol(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int ans = 0;

        int[] map = new int[3];

        int left = 0;

        for (int right = 0; right < n; right++) {
            char c = arr[right];

            map[c - 'a']++;

            while (isOk(map)) {
                ans += n - right;

                char lc = arr[left++];

                map[lc - 'a']--;
            }
        }

        return ans;
    }

    private boolean isOk(int[] map) {
        return map[0] > 0 && map[1] > 0 && map[2] > 0;
    }
}