H
1534348524
tags: String, DP, Sequence DP, Double Sequence DP, Backtracking

跟WildCard Matching 一样, 分清楚情况讨论 string p last char is '*' 还有并不是 '*'

这里的区别是, '*' 需要有一个preceding element, 那么:
- repeat 0 times
- repeat 1 times: need s[i-1] match with prior char p[i-2]

```
/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*/

/*
Thoughts:
Two sequence, DP, find if possible to match.
The '*' takes effect of preceding element, so we can start matching from end.
DP[i][j]: is it possible to match s[0 ~ i - 1] and p[0 ~ j - 1].
Check last index of s and p, there can be a few possibilities:
1. s[i-1]==p[j-1] and they are normal characters => && dp[i - 1][j - 1];
2. p[j-1] == '.', match => dp[i - 1][j - 1]
3. p[j-1] == '*':
    a. ignore a* => |= dp[i][j - 2];
    b. use a*    => |= dp[i - 1][j]; 

init:
dp[0][j] and dp[i][0] will all be false since there can't be any match.

*/
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                    continue;
                }
                if (j == 0) { // When p is empty but s is not empty, should not match
                    dp[i][j] = false;
                    continue;
                }

                // j >= 1
                dp[i][j] = false;
                if (pp[j - 1] != '*') {
                    if (i >= 1 && (ss[i - 1] == pp[j - 1] || pp[j - 1] == '.')) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else { // tail = '*'. ex: a*
                    if (j >= 2 ) { // ignore a*, repeat 0 times
                        dp[i][j] |= dp[i][j - 2];
                    }
                    // repeat the char befeore * for 1 time, so ss[i-1] should match pp[j-2] or pp[j-2] == '.'
                    if (j >= 2 && i >= 1 && (ss[i - 1] == pp[j - 2] || pp[j - 2] == '.')) { 
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
```