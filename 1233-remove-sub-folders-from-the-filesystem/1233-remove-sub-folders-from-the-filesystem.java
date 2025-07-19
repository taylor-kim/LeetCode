class Solution {
    public List<String> removeSubfolders(String[] folder) {
        return try_trie(folder);
    }

    public List<String> try_trie(String[] folder) {
        Trie dic = new Trie();

        for (String s : folder) dic.build(s);

        List<String> ans = new ArrayList();

        for (String s : folder) {
            if (dic.isNotSubFolder(s)) {
                ans.add(s);
            }
        }

        return ans;
    }

    public class Trie {
        boolean isFolder;
        Map<String, Trie> map = new HashMap();

        public void build(String folder) {
            Trie t = this;

            String[] paths = folder.split("/");

            for (int i = 1; i < paths.length; i++) {
                t = t.map.computeIfAbsent(paths[i], k -> new Trie());
            }

            t.isFolder = true;
        }

        boolean isNotSubFolder(String folder) {
            Trie t = this;

            String[] paths = folder.split("/");
            
            for (int i = 1; i < paths.length; i++) {
                t = t.map.computeIfAbsent(paths[i], k -> new Trie());

                if (t.isFolder && i < paths.length - 1) {
                    return false;
                }
            }

            return true;
        }
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