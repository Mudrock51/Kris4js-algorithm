// 3186. 施咒的最大总伤害
// https://leetcode.cn/problems/maximum-total-damage-with-spell-casting/description/

// 「值域」、「离散化」、「打家劫舍」

/**
 * 选择元素 power[i] 后不能使用 power[i] ± 1 和 power[i] ± 2
 * 
 * 值域型打家劫舍问题, 前置题目为:
 * 🔺 740. 删除并获得点数 https://leetcode.cn/problems/delete-and-earn/description/
 * 🔺 在前置题目的基础上将值域从 1e6 扩大为 1e9, 因此如何「离散化」值域是需要考虑的问题。
 * 
 * 1) 值域离散解决方案:
 * ➤ 统计每个元素的出现次数, 记录在哈希表 cnt 中并顺序排序出哈希表的 Key 得到数组 a
 * 
 * 2) 接下来是打家劫舍 Plus:
 * ➤ 定义 f[i] 表示 Key 值数组 a 的前 i 个元素最大和
 * ➤ 「选或不选」
 * 🔺 不选: 问题变成从 a[0] 到 a[i - 1] 之间的最大元素和, f[i] = f[i - 1];
 * 🔺 选: 问题变成除开 {a[i] - 2, a[i] - 1} 两个元素之外的最大元素和, f[i] = f[j - 1] + a[i] * cnt[a[i]];
 * 注: j 是满足最小的 a[j] ≥ a[i] - 2 的数。
 */

import java.util.*;

class Solution {
    // 递推
    public long maximumTotalDamage_recursion(int[] power) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : power) {
            cnt.merge(x, 1, Integer::sum);
        }

        int n = cnt.size();
        int[] a = new int[n];
        int k = 0;
        for (int x : cnt.keySet()) {
            a[k++] = x;
        }
        Arrays.sort(a);

        // ➤ 为了表示 i < 0 的「边界」情况, 将所有状态右移一位, f[i] 变成 f[i+1];
        // ➤ f[i+1] 表示从 a[0] 到 a[i] 中选择得到的最大元素和;
        // ➤ 当前仅当 i = -1 时, f[0] 表示递归边界
        long[] f = new long[n + 1];

        int j = 0;
        for (int i = 0; i < n; i++) {
            while (a[j] < a[i] - 2) {
                j++;
            }
            f[i + 1] = Math.max(f[i], f[j] + (long) a[i] * cnt.get(a[i]));
        }

        return f[n];
    }

    // 记忆化搜索
    public long maximumTotalDamage_memory_search(int[] power) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : power) {
            cnt.merge(x, 1, Integer::sum); // java 的 Map 统计元素数量
        }

        int n = cnt.size();
        int[] a = new int[n]; // Key 值数组
        int k = 0;
        for (int x : cnt.keySet()) {
            a[k++] = x;
        }
        Arrays.sort(a);

        long[] memo = new long[n];
        Arrays.fill(memo, -1);

        return dfs(a, cnt, memo, n - 1); // 记忆化搜索
    }

    private long dfs(int[] a, Map<Integer, Integer> cnt, long[] memo, int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }

        int x = a[i];
        int j = i;
        while (j > 0 && a[j - 1] >= x - 2) {
            j--;
        }
        return memo[i] = Math.max(
                dfs(a, cnt, memo, i - 1),
                dfs(a, cnt, memo, j - 1) + (long) x * cnt.get(x));
    }
}