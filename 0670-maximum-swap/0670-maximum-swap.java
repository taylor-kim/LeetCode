class Solution {
    public int maximumSwap(int num) {
        return mySol(num);
    }

    public int mySol(int num) {
        String s = String.valueOf(num);
        char[] arr = new char[s.length()];

        List<Integer>[] freq = new List[10];

        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList();
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = s.charAt(i);
            freq[arr[i] - '0'].add(i);
        }

        int left = 0;

        while (left < arr.length) {
            int digit = (int)(arr[left] - '0');

            // System.out.println(String.format("left:%d, digit:%d", left, digit));

            for (int max = 9; max > digit; max--) {
                if (freq[max].size() > 0) {
                    int j = freq[max].get(freq[max].size() - 1);

                    if (left >= j) continue;

                    char temp = arr[left];
                    arr[left] = arr[j];
                    arr[j] = temp;
                    
                    return Integer.parseInt(new String(arr));
                }
            }

            left++;
        }

        return num;
    }
}