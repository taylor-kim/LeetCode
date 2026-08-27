class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        
        StringBuilder res = new StringBuilder();
        int n = target.length();
        for (int i = 0; i < n; i++) {
            int targetChar = target.charAt(i) - 'a';

            // 情况1：先尝试在当前位置放置与 target[i] 相同的字符
            if (cnt[targetChar] > 0) {
                cnt[targetChar]--;
                // 检查剩余字符能否构成大于 target[i+1:] 的字符串
                if (canFormGreater(cnt, target, i + 1)) {
                    res.append(target.charAt(i));
                    continue;
                }
                // 不能构成更大的字符串，回溯
                cnt[targetChar]++;
            }
            
            // 情况2：在当前位置放置一个大于 target[i] 的字符
            for (int j = targetChar + 1; j < 26; j++) {
                if (cnt[j] > 0) {
                    cnt[j]--;
                    res.append((char)('a' + j));
                    // 剩余位置按最小字典序填充
                    res.append(getMinString(cnt));
                    return res.toString();
                }
            }
            
            // 无法找到可行方案, 直接返回
            return "";
        }
        
        return "";
    }
    
    // 检查剩余字符是否能构成大于 suffix 的字符串
    private boolean canFormGreater(int[] cnt, String target, int start) {
        String maxStr = getMaxString(cnt);
        String suffix = target.substring(start);
        return maxStr.compareTo(suffix) > 0;
    }
    
    // 获取最大字典序字符串（降序排列）
    private String getMaxString(int[] cnt) {
        StringBuilder res = new StringBuilder();
        for (int i = 25; i >= 0; i--) {
            if (cnt[i] > 0) {
                res.append(String.valueOf((char)('a' + i)).repeat(cnt[i]));
            }
        }
        return res.toString();
    }
    
    // 获取最小字典序字符串（升序排列）
    private String getMinString(int[] cnt) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                res.append(String.valueOf((char)('a' + i)).repeat(cnt[i]));
            }
        }
        return res.toString();
    }
}
