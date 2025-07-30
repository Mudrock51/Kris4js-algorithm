// 740. 删除并获得点数
// https://leetcode.cn/problems/delete-and-earn/description/

// 「值域」、「打家劫舍」类问题

/**
 * 选择元素 nums[i] 后必须删除 nums[i] - 1 和 nums[i] + 1.
 * 相邻数字不能选择的问题 —— 打家劫舍
 * 
 * ➤ 巧妙的思路: 值域数组加法 a[x] += x 能够存储所有 x 之和
 * 
 * 接下来就是打家劫舍问题。
 */

class Solution {
    // 空间优化的打家劫舍问题
    private int rob(int[] nums) {
        int f0 = 0;
        int f1 = 0;
        for (int x : nums) {
            int new_f = Math.max(f1, f0 + x); // 避免覆盖 f1
            f0 = f1;
            f1 = new_f;
        }

        return f1;
    }

    public int deleteAndEarn(int[] nums) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }

        int[] s = new int[mx + 1];
        for (int x : nums) {
            s[x] += x;
        }

        return rob(s);
    }
}