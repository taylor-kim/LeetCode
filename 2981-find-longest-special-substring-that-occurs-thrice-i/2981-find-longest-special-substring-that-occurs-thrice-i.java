class Solution {
    public int maximumLength(String s) {
        return mySol(s);
    }

    public int mySol(String s) {
        /**
        max : n - 2
        int[] maxContinue => 글자별 special의 최대 길이
        int[] freq
        find the
         */

         int[] maxSpecials = new int[26];
         int[] freq = new int[26];

         int maxSpecial = 0;
         int maxFreq = 0;

         char prev = '0';
         int count = 0;

         for (char c : s.toCharArray()) {
            maxFreq = Math.max(maxFreq, ++freq[c - 'a']);
            
            if (prev == c) {
                count++;
            } else {
                prev = c;
                count = 1;
            }

            maxSpecials[c - 'a'] = Math.max(maxSpecials[c - 'a'], count);
            maxSpecial = Math.max(maxSpecial, maxSpecials[c - 'a']);
         }

        //  System.out.println(Arrays.toString(freq));
        //  System.out.println(Arrays.toString(maxSpecials));

        //  System.out.println(String.format("maxFreq:%d, maxSpecial:%d", maxFreq, maxSpecial));

         if (maxFreq < 3) return -1;

         int ans = Math.min(maxSpecial, s.length() - 2);

         while (ans > 0) {
            for (int i = 0; i < maxSpecials.length; i++) {
                if (maxSpecials[i] < ans) {
                    continue;
                }
                char c = (char)('a' + i);

                String target = String.valueOf(c).repeat(ans);

                int start = 0;
                int time = 3;

                while (time-- > 0) {
                    int index = s.indexOf(target, start);

                    if (index < 0) {
                        break;
                    }

                    start = index + 1;
                }

                if (time < 0) {
                    return ans;
                }
            }
            ans--;
         }

         return -1;
    }
}