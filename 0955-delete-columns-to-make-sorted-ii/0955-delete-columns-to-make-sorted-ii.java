class Solution {
    public int minDeletionSize(String[] strs) {
        return editorial2(strs);
    }

    public int editorial2(String[] strs) {
        int ans = 0;
        boolean[] cuts = new boolean[strs.length - 1];

        out: for (int c = 0; c < strs[0].length(); c++) {
            for (int r = 0; r < strs.length - 1; r++) {
                if (!cuts[r] && strs[r].charAt(c) > strs[r + 1].charAt(c)) {
                    ans++;
                    continue out;
                }
            }

            for (int r = 0; r < strs.length - 1; r++) {
                if (strs[r].charAt(c) < strs[r + 1].charAt(c)) {
                    cuts[r] = true;
                }
            }
        }

        return ans;
    }

    public int editorial1(String[] strs) {
        int ans = 0;

        String[] cur = new String[strs.length];

        for (int c = 0; c < strs[0].length(); c++) {

            String[] cur2 = Arrays.copyOf(cur, strs.length);

            for (int r = 0; r < strs.length; r++) {
                cur2[r] += strs[r].charAt(c);
            }

            if (isSorted(cur2)) {
                cur = cur2;
            } else {
                ans++;
            }
        }

        return ans;
    }

    private boolean isSorted(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) return false;
        }

        return true;
    }

    public int mySol3(String[] strs) {
        int ans = 0;

        for (int c = 0; c < strs[0].length(); c++) {
            boolean deleted = false;
            boolean hasTie = false;

            int prev = strs[0].charAt(c);

            for (int r = 1; r < strs.length; r++) {
                int current = strs[r].charAt(c);

                if (prev > current) {
                    deleted = true;
                    break;
                } else if (prev == current) {
                    hasTie = true;
                }

                prev = current;
            }

            if (deleted) {
                ans++;
            } else if (!hasTie) {
                break;
            }
        }

        return ans;
    }

    public int mySol2_fail(String[] strs) {
        Set<Integer> set = new HashSet();

        List<Integer> list = new ArrayList();

        for (int i = 0; i < strs.length; i++) list.add(i);

        topdown(strs, 0, list, set);

        return set.size();
    }

    private void topdown(String[] strs, int start, List<Integer> rowIndices, Set<Integer> set) {
        int m = strs[0].length();

        System.out.println("start:%d, rowIndices:%s, set:%s".formatted(start, rowIndices, set));

        for (int c = start; c < m && !rowIndices.isEmpty(); c++) {
            boolean deleted = false;
            Map<Integer, Set<Integer>> tiesMap = new HashMap();

            int prev = strs[rowIndices.get(0)].charAt(c) - 'a';

            for (int r = 1; r < rowIndices.size(); r++) {
                int current = strs[rowIndices.get(r)].charAt(c) - 'a';

                if (prev > current) {
                    deleted = true;

                    // System.out.println("deleted!!! start:%d, rowIndices:%s, set:%s".formatted(start, rowIndices, set));
                    set.add(c);
                    break;
                } else if (prev == current) {
                    tiesMap.computeIfAbsent(prev, k -> new HashSet()).add(r - 1);
                    tiesMap.computeIfAbsent(prev, k -> new HashSet()).add(r);
                }

                prev = current;
            }

            if (!deleted) {
                if (!tiesMap.isEmpty()) {
                    System.out.println(tiesMap + ", " + set);
                    for (Set<Integer> nextIndices : tiesMap.values()) {
                        topdown(strs, c + 1, new ArrayList(nextIndices), set);
                    }
                    return;
                } else {
                    return;
                }
            }
        }
    }

    public int mySol_fail(String[] strs) {
        int ans = 0;

        List<Integer> list = new ArrayList();

        for (int i = 0; i < strs.length; i++) list.add(i);

        for (int c = 0; c < strs[0].length() && !list.isEmpty(); c++) {
            Set<Integer> tie = new HashSet();
            int prev = strs[list.get(0)].charAt(c) - 'a';

            boolean deleted = false;

            for (int r = 1; r < list.size(); r++) {
                int current = strs[list.get(r)].charAt(c) - 'a';

                if (prev > current) {
                    ans++;
                    deleted = true;
                    break;
                } else if (prev == current) {
                    tie.add(r - 1);
                    tie.add(r);
                }

                prev = current;
            }

            if (!deleted) {
                // System.out.println(tie);
                list = new ArrayList(tie);
            }
        }

        return ans;
    }
}