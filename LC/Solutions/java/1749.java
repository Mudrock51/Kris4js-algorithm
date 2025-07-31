// 1749. 任意子数组和的绝对值的最大值
// https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray/description/

// 「绝对值」、「子数组」

/**
 * 与 53. 最大子数组和 一致, 本题将子数组的最大和调整为最大绝对值和。
 * ◾ 如果 x ≥ 0, 那么 x 越大越好，问题转化成  53. 最大子数组和。
 * ◾ 如果 x < 0, 那么 x 越小，−x 就越大，问题转化成最小子数组和。
 * 
 * 解题思路: 「动态规划」、「前缀和」
 * 1. 动态规划
 * ➤ 1.1 定义 f[i] 表示以 nums[i] 结尾的最大子数组和;
 * ➤ 1.2 使用 fMax 作为滚动变量在空间上优化 f[i] 的数组空间;
 * ➤ 1.3 同理为了解决「最小子数组」问题, 使用 fMin 作为滚动变量记录最小子数组和.
 * 
 * 2. 前缀和
 * ➤ 2.1 子数组的和等于两个前缀和的差 s[j]−s[i], 绝对值即为 |s[j] − s[i]|;
 * ➤ 2.2 仔细思考发现只需要 s[j] 和 s[i] 相差最大即可;
 * ➤ 2.3 记录前缀和枚举过程中的最大值和最小值, 差值即为答案.
 */

class Solution {
    public int maxAbsoluteSum_dp(int[] nums) {
        int ans = 0;
        int f0 = Integer.MAX_VALUE, f1 = Integer.MIN_VALUE;
        for (int x : nums) {
            f0 = Math.min(f0, 0) + x;
            f1 = Math.max(f1, 0) + x;
            ans = Math.max(ans, Math.max(Math.abs(f0), Math.abs(f1)));
        }
        return ans;
    }

    public int maxAbsoluteSum_preSum(int[] nums) {
        int s = 0, mx = 0, mn = 0;
        for (int x : nums) {
            s += x;
            // 效率更高的写法
            if (s > mx) {
                mx = s; // mx = Math.max(mx, s);
            }
            else if (s < mn) {
                mn = s; // mn = Math.min(mn, s);
            }
        }
        return mx - mn;
    }
}