class Solution {
    public boolean isIsomorphic(String s, String t) {
        return tryAgain(s, t);
    }

    private boolean tryAgain(String s, String t) {
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();

        if (ss.length != tt.length) {
            return false;
        }

        int[] sToT = new int[256];
        int[] tToS = new int[256];

        Arrays.fill(sToT, -1);
        Arrays.fill(tToS, -1);

        for (int i = 0; i < ss.length; i++) {
            int sc = (int)ss[i];
            int tc = (int)tt[i];

            if (sToT[sc] == -1 && tToS[tc] == -1) {
                sToT[sc] = tc;
                tToS[tc] = sc;
            } else if (sToT[sc] != tc || tToS[tc] != sc) {
                return false;
            }
        }

        return true;
    }

    private boolean officialSol(String s, String t) {
        int mapSToT[] = new int[256];
        int mapTToS[] = new int[256];

        Arrays.fill(mapSToT, -1);
        Arrays.fill(mapTToS, -1);

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (mapSToT[c1] == -1 && mapTToS[c2] == -1) {
                mapSToT[c1] = c2;
                mapTToS[c2] = c1;
            } else if (mapSToT[c1] != c2 || mapTToS[c2] != c1) {
                return false;
            }
        }

        return true;
    }

    private boolean mySol(String s, String t) {
        Map<Character, Character> map = new HashMap();
        Map<Character, Character> map2 = new HashMap();

        for (int i = 0; i < s.length(); i++) {
            char one = s.charAt(i);
            char otherOne = t.charAt(i);

            Character mapped = map.get(one);
            Character mapped2 = map2.get(otherOne);

            if (mapped != null && mapped != otherOne) {
                return false;
            }

            if (mapped2 != null && mapped2 != one) {
                return false;
            }

            map.put(one, otherOne);
            map2.put(otherOne, one);
        }

        return true;
    }
}