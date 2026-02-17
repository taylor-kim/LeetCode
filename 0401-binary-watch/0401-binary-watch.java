class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        return mySol(turnedOn);
    }

    public List<String> mySol(int turnedOn) {
        Set<String> set = new HashSet();

        topdown(set, turnedOn, 0, new boolean[10]);

        return new ArrayList(set);
    }

    private void topdown(Set<String> set, int turnedOn, int index, boolean[] arr) {
        if (turnedOn == 0 || index >= arr.length) {
            String s = time(arr);

            if (s != null) set.add(s);

            return;
        }

        for (int i = index; i < arr.length; i++) {
            if (!arr[i]) {
                arr[i] = true;
                topdown(set, turnedOn - 1, index + 1, arr);
                arr[i] = false;
            }
        }
    }

    private String time(boolean[] arr) {
        StringBuilder sb = new StringBuilder();

        int h = getNum(arr, 0, 4);

        if (h > 11) return null;

        int m = getNum(arr, 4, arr.length);

        if (m > 59) return null;

        sb.append(h).append(":").append("%02d".formatted(m));

        return sb.toString();
    }

    private int getNum(boolean[] arr, int start, int end) {
        int n = 0;

        int pos = 0;

        for (int i = start; i < end; i++) {
            if (arr[i]) {
                n |= (1 << pos);
            }
            pos++;
        }

        return n;
    }
}