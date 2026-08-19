class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        return editorial(n, reservedSeats);
    }

    public int editorial(int n, int[][] reserved) {
        Map<Integer, Integer> map = new HashMap();

        int left = 0b00001111;
        int mid = 0b11000011;
        int right = 0b11110000;

        for (int[] r : reserved) {
            if (r[1] == 1 || r[1] == 10) continue;

            map.put(r[0], map.getOrDefault(r[0], 0) | (1 << (r[1] - 2)));
        }

        int ans = (n - map.size()) * 2;

        for (int occufied : map.values()) {
            if ((occufied | left) == left
                || (occufied | mid) == mid
                || (occufied | right) == right) {
                    ans++;
                }
        }

        return ans;
    }

    public int mySol(int n, int[][] reserved) {
        Map<Integer, List<Integer>> map = new HashMap();

        for (int[] r : reserved) {
            map.computeIfAbsent(r[0], k -> new ArrayList()).add(r[1]);
        }

        Map<Integer, List<Integer>> impossibles = Map.of(
            2, List.of(0),
            3, List.of(0),
            4, List.of(0,1),
            5, List.of(0,1),
            6, List.of(1,2),
            7, List.of(1,2),
            8, List.of(2),
            9, List.of(2)
        );

        int ans = (n - map.size()) * 2;

        for (List<Integer> row : map.values()) {
            boolean[] possibles = {true, true, true};

            for (int r : row) {
                if (!impossibles.containsKey(r)) continue;

                for (int impossible : impossibles.get(r)) {
                    possibles[impossible] = false;
                }
            }

            int count = 0;

            if (possibles[0]) {
                count++;
            }
            if (possibles[2]) {
                count++;
            }
            if (count == 0 && possibles[1]) {
                count++;
            }

            ans += count;
        }

        return ans;
    }
}