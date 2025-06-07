class Solution {
    public String clearStars(String s) {
        return official_good(s);
    }

    public String official_good(String s) {
        LinkedList<Integer>[] freq = new LinkedList[26];

        for (int i = 0; i < 26; i++) {
            freq[i] = new LinkedList();
        }

        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];

            if (c == '*') {
                for (int j = 0; j < 26; j++) {
                    if (freq[j].size() > 0) {
                        arr[freq[j].pollLast()] = '*';
                        break;
                    }
                }
            } else {
                freq[c - 'a'].add(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != '*') {
                sb.append(arr[i]);
            }
        }

        return sb.toString();
    }

    public String mySol2(String s) {
        LinkedList<Integer>[] freq = new LinkedList[26];

        for (int i = 0; i < 26; i++) {
            freq[i] = new LinkedList();
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '*') {
                for (int j = 0; j < 26; j++) {
                    if (freq[j].size() > 0) {
                        freq[j].pollLast();
                        break;
                    }
                }
            } else {
                freq[c - 'a'].add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("1".repeat(s.length()));

        for (int i = 0; i < 26; i++) {
            char c = (char)(i + 'a');

            for (int index : freq[i]) {
                sb.setCharAt(index, c);
            }
        }

        return sb.toString().replaceAll("1", "");
    }

    public String mySol(String s) {
        LinkedList<Integer>[] freq = new LinkedList[26];

        for (int i = 0; i < 26; i++) {
            freq[i] = new LinkedList();
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '*') {
                for (int j = 0; j < 26; j++) {
                    if (freq[j].size() > 0) {
                        freq[j].pollLast();
                        break;
                    }
                }
            } else {
                freq[c - 'a'].add(i);
            }
        }

        List<int[]> data = new ArrayList();

        for (int i = 0; i < 26; i++) {
            if (freq[i].size() > 0) {
                for (int index : freq[i]) {
                    data.add(new int[] {i, index});
                }
            }
        }

        Collections.sort(data, (a, b) -> {
            return a[1] - b[1];
        });

        for (int[] d : data) {
            sb.append((char)(d[0] + 'a'));
        }

        return sb.toString();
    }
}