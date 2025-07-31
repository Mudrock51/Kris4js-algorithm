// 1191. K 次串联后最大子数组之和
// https://leetcode.cn/problems/k-concatenation-maximum-sum/description/

// 「循环数组」、「子数组」、「动态规划」

/**
 * 最大子数组之和的进阶题型, 通过添加循环次数来考查「最大」的元素范围
 * 
 * 下面例举本题思路, 假设 s = (nums[0] + nums[1] + ... + nums[n - 1]):
 * ➤ 0. 如果 k = 1, 本题就是 53. 最大子数组和 的子数组可为空版本;
 * ➤ 1. s > 0: 最大子数组一定包括 [1, n - 2] 的数组, 在首尾{0, n-1} 两个数组中找到一个最大的连续子数组;
 * ➤ 2. s = 0: 最大子数组在 [0, 1] 两个数组中寻找一个最大的连续子数组;
 * ➤ 3. s < 0: 同理, 一定是在 [0, 1] 两个数组中寻找一个最大的连续子数组;
 */

class Solution {
    private static final int MOD = (int)1e9 + 7;
    public int kConcatenationMaxSum(int[] arr, int k) {
        int n = arr.length;
        long s = 0;
        for (int x : arr) {
            s = s + x;
        }

        int ans = 0;
        int f1 = 0;
        // k = 1 则变成最大连续子数组问题;
        // k ≠ 1 则在两个数组中寻找最大连续子数组.
        for (int i = 0; k == 1 ? i < n : i < 2 * n; i++) {
            f1 = Math.max(f1, 0) + arr[i % n];
            ans = Math.max(ans, f1);
        }

        // 当 s > 0 时, 最优一定涵括所有的数组.
        if (s > 0 && k > 1) {
            ans = (int)(ans + (long)s * (k - 2) % MOD) % MOD;
        }
        return ans;
    }
}