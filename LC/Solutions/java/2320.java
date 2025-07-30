// 2320. 统计放置房子的方式数
// https://leetcode.cn/problems/count-number-of-ways-to-place-houses/

// 方案数问题: 从「选与不选」的角度思考问题

/**
 * 定义 f[i] 表示前 i 个地块的放置方案数
 * 
 * 考虑第 i 个地块:
 * ➤ 若不放房子那么, 第 i-1 个可放可不放, f[i] = f[i-1];
 * ➤ 若放房子那么, 第 i-1 个地块无法放房子, 第 i-2 个地块可放可不放 f[i] = f[i-2];
 */

class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countHousePlacements(int n) {
        int[] f = new int[n + 1];

        f[0] = 1;
        f[1] = 2; // 第 1 个地块可放可不放, 故有两种方案
        for (int i = 2; i <= n; i++) {
            f[i] = (f[i - 1] + f[i - 2]) % MOD;
        }

        return (int) ((long) f[n] * f[n] % MOD);
    }
}