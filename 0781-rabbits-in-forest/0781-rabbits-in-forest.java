class Solution {
    public int numRabbits(int[] answers) {
        return mySol(answers);
    }

    public int mySol(int[] answers) {
        int ans = 0;

        Map<Integer, Integer> map = new HashMap();

        for (int num : answers) {
            map.put(num + 1, map.getOrDefault(num + 1, 0) + 1);
        }

        int count = 0;

        // while ((count = reduce(map)) > 0) {
        //     ans += count;
        // }

        ans = reduce(map);

        return ans;
    }

    private int reduce(Map<Integer, Integer> map) {
        Map<Integer, Integer> odds = new HashMap();

        int ans = 0;

        for (int numberOfSameColor : map.keySet()) {
            int rabbits = map.get(numberOfSameColor);

            if (numberOfSameColor >= rabbits) {
                ans += numberOfSameColor;
            } else {
                int colors = rabbits / numberOfSameColor;
                // int odd = colors % numberOfSameColor;

                // System.out.println(String.format(""))

                ans += (colors * numberOfSameColor) + (rabbits % numberOfSameColor == 0 ? 0 : numberOfSameColor);
            }
        }

        return ans;
    }
}