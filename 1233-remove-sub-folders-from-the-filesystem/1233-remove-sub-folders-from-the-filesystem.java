class Solution {
    public List<String> removeSubfolders(String[] folder) {
        return mySol(folder);
    }

    public List<String> mySol(String[] folder) {
        Arrays.sort(folder);

        int start = 0;
        int next = 1;

        List<String> ans = new ArrayList();

        while (start < folder.length) {
            String root = folder[start];

            ans.add(root);

            next = start + 1;

            while (next < folder.length) {
                if (!folder[next].startsWith(root + "/")) {
                    break;
                }
                next++;
            }

            start = next;
        }

        return ans;
    }
}