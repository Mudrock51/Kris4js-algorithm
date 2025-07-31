// 152. 乘积最大子数组
// https://leetcode.cn/problems/maximum-product-subarray/description/

// 「子数组」、「乘法」

/**
 * 53. 最大子数组和的乘法变种题目
 * 
 * 思路很类似于 「1749. 任意子数组和的绝对值的最大值」, 通过维护滚动最值来解决符号问题
 * 主要通过动态规划来解决本题, 步骤如下:
 * ➤ 1. 定义 fMax[i]、fMin[i] (滚动变量) 表示以 x = nums[i] 结尾的子数组乘积最值;
 * ➤ 2. 单独考虑每个最值的变化:
 * ◾ 当 x > 0, fMax[i] = max(fMax[i-1] * x, x), fMin[i] = min(fMin[i-1] * x, x);
 * ◾ 当 x < 0, fMax[i] = max(fMin[i-1] * x, x), fMin[i] = min(fMax[i-1] * x, x);
 * ◾ 当 x = 0, 维护当前最值.
 * ➤ 状态转移: fMax[i] = max(max(fMax[i-1] * x, fMin[i-1] * x), x); —— (1)
 * ➤ 状态转移: fMin[i] = min(min(fMax[i-1] * x, fMin[i-1] * x), x); —— (2)
 * 
 * fMax[i] 在 (1) 式中已更新, 故应该使用一个临时变量 temp 保存 fMax[i-1] 来实现滚动数组的空间优化
 */

class Solution {
    public int maxProduct(int[] nums) {
        int ans = Integer.MIN_VALUE;
        // 定义 fMax[i], fMin[i] 表示以 nums[i] 结尾的子数组的最大/小乘积
        int fMin = 1;
        int fMax = 1;
        for(int x : nums) { 
            int temp = fMax;

            // fMax[i] = Math.max(Math.max(fMax[i - 1] * x, fMin[i - 1] * x), x);
            fMax = Math.max(Math.max(fMax * x, fMin * x), x);
            // fMin[i] = Math.min(Math.min(fMax[i - 1] * x, fMin[i - 1] * x), x);
            fMin = Math.min(Math.min(temp * x, fMin * x), x);
            
            ans = Math.max(ans, fMax);
        }
        return ans;
    }
}