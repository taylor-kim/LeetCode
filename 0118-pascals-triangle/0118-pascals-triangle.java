class Solution {
    public List<List<Integer>> generate(int numRows) {
        return mySol(numRows);
    }

    public List<List<Integer>> mySol(int numRows) {
        List<List<Integer>> ans = new ArrayList();
        ans.add(List.of(1));

        for (int i = 1; i < numRows; i++) {
            List<Integer> prevRow = ans.get(ans.size() - 1);

            List<Integer> row = new ArrayList();
            row.add(1);
            
            for (int prevCol = 1; prevCol < prevRow.size(); prevCol++) {
                row.add(prevRow.get(prevCol - 1) + prevRow.get(prevCol));
            }

            row.add(1);

            ans.add(row);
        }

        return ans;
    }
}