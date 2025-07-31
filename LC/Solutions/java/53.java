// 53. 最大子数组和
// https://leetcode.cn/problems/maximum-subarray/description/

// 「子数组」、「前缀和」、「动态规划」

/**
 * 最大子数组和, 解法包括「动态规划」和「前缀和」两种。
 * 
 * 动态规划的思路:
 * ➤ 1. 定义 f[i] 表示以 a[i] 为结尾的元素最大子数组和, 前提是一定包括 a[i];
 * ➤ 2. 状态转移方程: f[i] = Math.max(f[i - 1], 0) + a[i];
 * ➤ 3. 边界条件: f[0] = a[0].
 * 
 * 前缀和思路:
 * ➤ 1. 理解 前缀和之差 s[i] - s[j] 表示 a[j + 1] + ... + a[i];
 * ➤ 2. 为了保证子数组不为空, 先计算答案 | 再更新.
 * ➤ 3. 答案初始化为 Integer.MIN_VALUE
 */

class Solution {
    // 🔺方法一: 动态规划
    public int maxSubArray_dp(int[] nums) {
        int[] f = new int[nums.length];
        f[0] = nums[0];
        int ans = f[0];
        for (int i = 1; i < nums.length; i++) {
            f[i] = Math.max(f[i - 1], 0) + nums[i];
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    // 🔺方法二: 前缀和
    public int maxSubArray_preSum(int[] nums) {
        // 初始化赋值 ans 为 Integer.MIN_VALUE
        int ans = Integer.MIN_VALUE;
        int minPreSum = 0; // 最小前缀和 —— 等价于股票买入点
        int preSum = 0; // 当前前缀和 —— 等价于股票售出点

        // ➤ preSum - minPreSum 就是连续子数组和
        for (int x : nums) {
            preSum += x;
            // 由于本题题目要求 「连续子数组不能为空」
            // 先计算 preSum - minPreSum 再更新 minPreSum
            ans = Math.max(ans, preSum - minPreSum);
            minPreSum = Math.min(minPreSum, preSum);
        }
        return ans;
    }
}