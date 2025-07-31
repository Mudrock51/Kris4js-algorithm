// 2606. 找到最大开销的子字符串
// https://leetcode.cn/problems/find-the-substring-with-maximum-cost/description/

// 「字符」、「子数组」

/**
 * 与 53. 最大子数组和 一致, 本题中其基础上添加了对 26 个字符的处理。
 * 
 * 本题解着重分析对于字符的处理过程。
 * 
 * 动态规划的思路:
 * ➤ 1. 定义 f[i] 表示以 a[i] 为结尾的元素最大子数组和, 前提是一定包括 a[i];
 * ➤ 2. 状态转移方程: f[i] = Math.max(f[i - 1], 0) + a[i];
 * ➤ 3. 边界条件: f[0] = a[0].
 */

class Solution {
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        // 🔺在「字符」问题中, 大多创建大小为 26 的数组来存储字符代表的「含义」
        int[] mapping = new int[26];
        for (int i = 0; i < 26; i++) {
            mapping[i] = i + 1;
        }
        for (int i = 0; i < vals.length; i++) {
            // java 可以使用 String.charAt(i) 来访问 String 对象第 i 个位置上的字符
            mapping[chars.charAt(i) - 'a'] = vals[i];
        }

        int ans = 0;
        int f = 0;
        // java 可以通过 String.toCharArray() 来枚举访问字符串的每个字符
        for (char c : s.toCharArray()) {
            f = Math.max(f, 0) + mapping[c - 'a'];
            ans = Math.max(ans, f);
        }
        return ans;
    }
}