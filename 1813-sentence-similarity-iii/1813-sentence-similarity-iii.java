class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        return official_deque(sentence1, sentence2);
    }

    public boolean official_deque(String s1, String s2) {
        Deque<String> d1 = new LinkedList(Arrays.asList(s1.split(" ")));
        Deque<String> d2 = new LinkedList(Arrays.asList(s2.split(" ")));

        while (!d1.isEmpty() && !d2.isEmpty() && d1.peek().equals(d2.peek())) {
            d1.poll();
            d2.poll();
        }

        while (!d1.isEmpty() && !d2.isEmpty() && d1.peekLast().equals(d2.peekLast())) {
            d1.pollLast();
            d2.pollLast();
        }

        return d1.isEmpty() || d2.isEmpty();
    }

    public boolean mySol_fail(String s1, String s2) {
        if (s1.length() > s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }

        String[] arr1 = s1.split(" ");
        String[] arr2 = s2.split(" ");

        int i1 = 0;
        int i2 = 0;

        int skipFrom = -1;

        while (i1 < arr1.length && i2 < arr2.length) {
            if (arr1[i1].equals(arr2[i2])) {
                i1++;
                i2++;
            } else {
                if (skipFrom == -1 || skipFrom == i1) {
                    if (skipFrom == -1) {
                        skipFrom = i1;
                    }
                    i2++;
                } else {
                    if (i1 == 0) i2++;

                    i1 = 0;
                }
            }
        }

        return i1 == arr1.length;
    }
}