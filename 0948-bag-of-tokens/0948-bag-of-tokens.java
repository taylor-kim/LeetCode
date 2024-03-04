class Solution {
    public int bagOfTokensScore(int[] tokens, int power) {
        return mySol_third(tokens, power);
    }
    
    public int mySol_third(int[] tokens, int power) {
        Arrays.sort(tokens);
        
        int n = tokens.length;
        
        int left = 0;
        int right = n - 1;
        int score = 0;
        
        while (left <= right) {
            if (tokens[left] <= power) {
                score++;
                power -= tokens[left++];
            } else if (left < right && score > 0) {
                score--;
                power += tokens[right--];
            } else {
                return score;
            }
        }
        
        return score;
    }
    
    public int mySol_bottomup(int[] tokens, int power) {
        int n = tokens.length;
        int[] dp = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            int t = tokens[i];
            
            if (t <= power) {
                dp[i + 1] = dp[i] + 1;
            }
        }
        
        return -1;
    }
    
    public int mySol_topdown_fail(int[] tokens, int power) {
        return mySol_topdown(tokens, power, 0, 0, new Integer[tokens.length][tokens.length + 1]);
    }
    
    public int mySol_topdown(int[] tokens, int power, int index, int score, Integer[][] memo) {
        if (index >= tokens.length) return score;
        
        if (memo[index][score] != null) {
            return memo[index][score];
        }
        
        int t = tokens[index];
        
        int ans = Integer.MIN_VALUE;
        
        if (power >= t) {
            ans = mySol_topdown(tokens, power - t, index + 1, score + 1, memo);
        } else if (score >= 1) {
            ans = mySol_topdown(tokens, power + t, index + 1, score - 1, memo);
        } else {
            ans = mySol_topdown(tokens, power, index + 1, score, memo);
        }
        
        return memo[index][score] = ans;
    }
}