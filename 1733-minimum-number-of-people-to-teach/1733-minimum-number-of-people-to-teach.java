class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        return mySol(n, languages, friendships);
    }

    public int mySol(int n, int[][] languages, int[][] friendships) {
        int ans = languages.length;

        Set<Integer>[] langs = new Set[languages.length];
        Set<Integer>[] peopleCanSpeak = new Set[n + 1];


        for (int i = 0; i < languages.length; i++) {
            langs[i] = new HashSet();

            for (int lang : languages[i]) {
                langs[i].add(lang);

                if (peopleCanSpeak[lang] == null) {
                    peopleCanSpeak[lang] = new HashSet();
                }
                peopleCanSpeak[lang].add(i);
            }
        }

        for (int i = 1; i <= n; i++) {
            Set<Integer> teach = new HashSet();
            for (int[] friend : friendships) {
                int a = friend[0] - 1;
                int b = friend[1] - 1;

                boolean ok = false;

                for (int lang : languages[a]) {
                    if (peopleCanSpeak[lang].contains(b)) {
                        ok = true;
                        break;
                    }
                }

                if (ok) continue;

                if (!langs[a].contains(i)) {
                    teach.add(a);
                }
                if (!langs[b].contains(i)) {
                    teach.add(b);
                }
            }
            ans = Math.min(ans, teach.size());
        }

        return ans;
    }
}