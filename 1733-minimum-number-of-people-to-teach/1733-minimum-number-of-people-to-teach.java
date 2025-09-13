class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        return official(n, languages, friendships);
    }

    public int official(int n, int[][] languages, int[][] friendships) {
        Set<Integer> needToTeach = new HashSet();

        for (int[] friend : friendships) {
            Set<Integer> set = new HashSet();

            int a = friend[0] - 1;
            int b = friend[1] - 1;

            for (int lang : languages[a]) {
                set.add(lang);
            }

            boolean canComm = false;

            for (int lang : languages[b]) {
                if (set.contains(lang)) {
                    canComm = true;
                    break;
                }
            }

            if (!canComm) {
                needToTeach.add(a);
                needToTeach.add(b);
            }
        }

        int[] count = new int[n + 1];
        int mostKnownLangCount = 0;

        for (int user : needToTeach) {
            for (int lang : languages[user]) {
                count[lang]++;
                mostKnownLangCount = Math.max(mostKnownLangCount, count[lang]);
            }
        }

        return needToTeach.size() - mostKnownLangCount;
    }

    public int mySol_improve(int n, int[][] languages, int[][] friendships) {
        int ans = languages.length;

        Set<Integer>[] langs = new Set[languages.length];
        Set<Integer>[] peopleCanSpeak = new Set[n + 1];

        for (int lang = 1; lang <= n; lang++) {
            peopleCanSpeak[lang] = new HashSet();
        }

        for (int i = 0; i < languages.length; i++) {
            langs[i] = new HashSet();

            for (int lang : languages[i]) {
                langs[i].add(lang);
                peopleCanSpeak[lang].add(i);
            }
        }

        Map<Integer, Set<Integer>> canComm = new HashMap();

        for (int[] friend : friendships) {
            int a = friend[0] - 1;
            int b = friend[1] - 1;

            canComm.computeIfAbsent(a, key -> new HashSet());
            canComm.computeIfAbsent(b, key -> new HashSet());

            for (int lang = 1; lang <= n; lang++) {
                if (peopleCanSpeak[lang].contains(a) && peopleCanSpeak[lang].contains(b)) {
                    canComm.get(a).add(b);
                    canComm.get(b).add(a);
                    break;
                }
            }
        }

        for (int lang = 1; lang <= n; lang++) {
            Set<Integer> teach = new HashSet();

            for (int[] friend : friendships) {
                int a = friend[0] - 1;
                int b = friend[1] - 1;

                if (canComm.get(a).contains(b)) {
                    continue;
                }

                if (!langs[a].contains(lang)) {
                    teach.add(a);
                }

                if (!langs[b].contains(lang)) {
                    teach.add(b);
                }
            }

            ans = Math.min(ans, teach.size());
        }

        return ans;
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