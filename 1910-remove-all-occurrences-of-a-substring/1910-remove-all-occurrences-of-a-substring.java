class Solution {
    public String removeOccurrences(String s, String part) {
        return official_kmp(s, part);
    }

    public String official_kmp(String s, String part) {
        // Precompute KMP longest prefix-suffix array for the pattern
        int[] kmpLPS = computeLongestPrefixSuffix(part);

        // Using stack to track characters and support pattern matching
        Stack<Character> charStack = new Stack<>();

        // Array to store pattern matching indices during traversal
        int[] patternIndexes = new int[s.length() + 1];

        for (
            int strIndex = 0, patternIndex = 0;
            strIndex < s.length();
            strIndex++
        ) {
            char currentChar = s.charAt(strIndex);
            charStack.push(currentChar);

            if (currentChar == part.charAt(patternIndex)) {
                // Track the next pattern index when characters match
                patternIndexes[charStack.size()] = ++patternIndex;

                if (patternIndex == part.length()) {
                    // Remove entire matched pattern from stack
                    int remainingLength = part.length();
                    while (remainingLength != 0) {
                        charStack.pop();
                        remainingLength--;
                    }

                    // Restore pattern index for next potential match
                    patternIndex = charStack.isEmpty()
                        ? 0
                        : patternIndexes[charStack.size()];
                }
            } else {
                if (patternIndex != 0) {
                    // Backtrack pattern matching using KMP LPS
                    strIndex--;
                    patternIndex = kmpLPS[patternIndex - 1];
                    charStack.pop();
                } else {
                    // Reset pattern index tracking when no match is possible
                    patternIndexes[charStack.size()] = 0;
                }
            }
        }

        // Convert remaining stack characters to result string
        StringBuilder result = new StringBuilder();
        while (!charStack.isEmpty()) {
            result.append(charStack.pop());
        }

        return result.reverse().toString();
    }

    private int[] computeLongestPrefixSuffix(String pattern) {
        // Array to store the longest proper prefix which is also a suffix
        int[] lps = new int[pattern.length()];

        // Initialize tracking variables for prefix and current position
        for (int current = 1, prefixLength = 0; current < pattern.length();) {
            // If characters match, extend the prefix length
            if (pattern.charAt(current) == pattern.charAt(prefixLength)) {
                // Store the length of matching prefix
                lps[current] = ++prefixLength;
                current++;
            }
            // If characters don't match and we're not at the start of the pattern
            else if (prefixLength != 0) {
                // Backtrack to the previous longest prefix-suffix
                prefixLength = lps[prefixLength - 1];
            }
            // If no match and no prefix to backtrack
            else {
                // No prefix-suffix match found
                lps[current] = 0;
                current++;
            }
        }

        // Return the computed longest prefix-suffix array
        return lps;
    }

    public String official_bf(String s, String part) {
        // Continue removing occurrences of 'part' as long as it exists in 's'
        while (s.contains(part)) {
            // Find the index of the leftmost occurrence of 'part'
            int partStartIndex = s.indexOf(part);

            // Remove the substring 'part' from 's' by concatenating the segments before and after 'part'
            s =
                s.substring(0, partStartIndex) +
                s.substring(partStartIndex + part.length());
        }
        // Return the updated string after all occurrences are removed
        return s;
    }

    public String official_stack(String s, String part) {
        Stack<Character> stk = new Stack<>();
        int strLength = s.length();
        int partLength = part.length();

        // Iterate through each character in the string
        for (int index = 0; index < strLength; index++) {
            // Push current character to stack
            stk.push(s.charAt(index));

            // If stack size is greater than or equal to the part length, check for match
            if (stk.size() >= partLength && checkMatch(stk, part, partLength)) {
                // Pop the characters matching 'part' from the stack
                for (int j = 0; j < partLength; j++) {
                    stk.pop();
                }
            }
        }

        // Convert stack to string with correct order
        StringBuilder result = new StringBuilder();
        while (!stk.isEmpty()) {
            result.append(stk.pop());
        }
        result.reverse();

        return result.toString();
    }

    // Helper function to check if the top of the stack matches the 'part'
    private boolean checkMatch(
        Stack<Character> stk,
        String part,
        int partLength
    ) {
        Stack<Character> temp = new Stack<>();
        temp.addAll(stk); // Copy the stack to avoid modifying the original

        // Iterate through part from right to left
        for (int partIndex = partLength - 1; partIndex >= 0; partIndex--) {
            // If current stack top doesn't match expected character
            if (temp.isEmpty() || temp.peek() != part.charAt(partIndex)) {
                return false;
            }
            temp.pop();
        }
        return true;
    }

    public String try_stack2_after_read_editorial(String s, String part) {
        Stack<Character> stack = new Stack();
        Stack<Character> popped = new Stack();

        for (char c : s.toCharArray()) {
            stack.push(c);
            
            if (stack.size() >= part.length()) {

                boolean matched = true;
                
                for (int i = part.length() - 1; i >= 0; i--) {
                    popped.push(stack.pop());
                    if (popped.peek() != part.charAt(i)) {
                        matched = false;
                        break;
                    }
                }

                if (matched) {
                    popped.clear();
                } else {
                    while (!popped.isEmpty()) {
                        stack.push(popped.pop());
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }

    public String try_stack_fail(String s, String part) {
        Stack<int[]> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (stack.isEmpty()) {
                if (c == part.charAt(0)) {
                    stack.push(new int[] {i, 1});
                } else {
                    stack.push(new int[] {i, 0});
                }
            } else {
                int[] prev = stack.peek();

                if (prev[1] == 0 || c != part.charAt(prev[1])) {
                    if (c == part.charAt(0)) {
                        stack.push(new int[] {i, 1});
                    } else {
                        stack.push(new int[] {i, 0});
                    }
                } else if (++prev[1] == part.length()) {
                    System.out.println("matched");
                    stack.pop();
                }
            }

            if (!stack.isEmpty() && stack.peek()[1] == part.length()) {
                System.out.println("matched");
                stack.pop();
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            int[] data = stack.pop();

            if (data[1] == 0) {
                sb.append(s.charAt(data[0]));
            } else {
                for (int i = data[1] - 1; i >= 0; i--) {
                    sb.append(part.charAt(i));
                }
            }

            System.out.println(sb.toString());
        }

        return sb.reverse().toString();
    }

    public String mySol(String s, String part) {
        int index = -1;

        while ((index = s.indexOf(part)) >= 0) {
            s = s.substring(0, index) + s.substring(index + part.length());

            // System.out.println(String.format("index:%d, s:%s", index, s));
        }

        return s;
    }
}