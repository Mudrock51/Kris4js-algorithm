# Kris4js-algorithm
Java/C++/Go/Python Algorithm Study Experience.



# LeetCode

---

LeetCode Algorithm



|  #   |                            Title                             |               Solution                |  Difficulty   |
| :--: | :----------------------------------------------------------: | :-----------------------------------: | :-----------: |
|  53  | [最大子数组和](https://leetcode.cn/problems/maximum-subarray/description/) |  [java](./LC/Solutions/java/53.java)  |     Easy+     |
| 152  | [乘积最大子数组](https://leetcode.cn/problems/maximum-product-subarray/) |  [java](LC/Solutions/java/152.java)   |    Medium+    |
| 198  |    [打家劫舍](https://leetcode.cn/problems/house-robber/)    |                                       |    Medium-    |
| 213  | [打家劫舍 II](https://leetcode.cn/problems/house-robber-ii/) |                                       |    Medium     |
| 740  | [删除并获得点数](https://leetcode.cn/problems/delete-and-earn/) | [java](./LC/Solutions/java/740.java)  |    Medium     |
| 1749 | [任意子数组和的绝对值的最大值](https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray/) |  [java](LC/Solutions/java/1749.java)  | Medium-(1542) |
| 2320 | [统计放置房子的方式数](https://leetcode.cn/problems/count-number-of-ways-to-place-houses/) | [java](./LC/Solutions/java/2320.java) | Medium(1608)  |
| 3186 | [施咒的最大总伤害](https://leetcode.cn/problems/maximum-total-damage-with-spell-casting/) | [java](./LC/Solutions/java/3186.java) | Medium+(1841) |





## 动态规划 Dynamic Programming

### 1. DP 入门

#### 1.1 打家劫舍

> [!important]
>
> 打家劫舍类问题是动态规划的入门型问题，主要思路就是「**选 OR 不选**」.
>
> - 如果选择第 `i` 个元素，那么第 `i - 1` 个元素无法选择，只能从第 `i - 2` 个元素中选取
> - 如果不选择第 `i` 个元素，那么可以选择第 `i - 1` 个元素。



我们定义 $f[i]$ 表示前 $i$ 个元素可以获得的最大值。

那么：

1. 选择第 $i$ 个元素：$`f[i] = max(f[i - 1], f[i - 2] + nums[i])`$；
2. 不选择第 $i$ 个元素，$`f[i] = f[i - 1]`$；

接下来考虑数组的初始化：

- 只有一个元素时，获得的最大值一定是 `nums[0]`；
- 有两个元素时，获得的最大值一定是 `max(nums[0], nums[1])`；



标准的打家劫舍代码如下：

```cpp
// 空间优化
int f0 = 0;
int f1 = 0;
for (int x : nums) {
    int new_f = max(f1, f0 + x); // 为了避免覆盖原来的 f1
    f0 = f1;
    f1 = new_f;
}
return f1;
```



> [!tip]
>
> 打家劫舍进阶题型：
>
> - 位置上的「选 OR 不选」转变为元素值的「选 OR 不选」——值域问题；
> - 值域问题下 `1e9` 数据的离散化思路；





|  #   |                            Title                             |               Solution                |  Difficulty   |
| :--: | :----------------------------------------------------------: | :-----------------------------------: | :-----------: |
| 198  |    [打家劫舍](https://leetcode.cn/problems/house-robber/)    |                                       |     Easy+     |
| 213  | [打家劫舍 II](https://leetcode.cn/problems/house-robber-ii/) |                                       |    Medium     |
| 2320 | [统计放置房子的方式数](https://leetcode.cn/problems/count-number-of-ways-to-place-houses/) | [java](./LC/Solutions/java/2320.java) | Medium(1608)  |
| 740  | [删除并获得点数](https://leetcode.cn/problems/delete-and-earn/) | [java](./LC/Solutions/java/740.java)  |    Medium     |
| 3186 | [施咒的最大总伤害](https://leetcode.cn/problems/maximum-total-damage-with-spell-casting/) | [java](./LC/Solutions/java/3186.java) | Medium+(1841) |





#### 1.2 最大子数组和



> [!important]
>
> **最大子数组和** 问题有两种解决方法：
>
> 1. 定义 `f[i]` 表示以 `a[i]` 结尾的最大子数组和。是否和 `f[i - 1]` 拼接，状态转移方程是 $f[i] = max(f[i - 1],0) + a[i]$。最后的答案是 `max(f)`。
> 1. 用前缀和思路，将问题转换为买卖股票问题。



---

**动态规划思路**

```java
// 1.定义 f [i] 表示以 a [i] 结尾的最大子数组和
int[] f = new int[n];
// 2.边界条件: 子数组不能够为空集
f[0] = nums[0];
int ans = f[0]; // 枚举记录最大 f [i]
for (int i = 1; i < n; i++) {
    // 3.状态转移方程
    f[i] = Math.max(f[i - 1], 0) + a[i]; // 以 a [i] 结尾表示必须选择
    ans = Math.max(ans, f[i]);
}
return ans;
```

---

**前缀和思路**

```java
// 1.初始化赋值 ans 为 Integer.MIN_VALUE
int ans = Integer.MIN_VALUE;
int minPreSum = 0; // 最小前缀和 —— 等价于股票买入点
int preSum = 0;	   // 当前前缀和 —— 等价于股票售出点

// ➤ preSum - minPreSum 就是连续子数组和
for (int x : nums) {
    preSum += x;
    // 由于本题题目要求 「连续子数组不能为空」
    // 先计算 preSum - minPreSum 再更新 minPreSum
    ans = Math.max(ans, preSum - minPreSum);
    minPreSum = Math.min(minPreSum, preSum);
}
return ans;
```

---



|  #   |                            Title                             |              Solution               |  Difficulty   |
| :--: | :----------------------------------------------------------: | :---------------------------------: | :-----------: |
|  53  | [最大子数组和](https://leetcode.cn/problems/maximum-subarray/description/) | [java](./LC/Solutions/java/53.java) |     Easy+     |
| 2606 | [找到最大开销的子字符串](https://leetcode.cn/problems/find-the-substring-with-maximum-cost/) | [java](LC/Solutions/java/2606.java) | Medium-(1422) |
| 1749 | [任意子数组和的绝对值的最大值](https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray/) | [java](LC/Solutions/java/1749.java) | Medium-(1542) |
| 1191 | [K 次串联后最大子数组之和](https://leetcode.cn/problems/k-concatenation-maximum-sum/description/) | [java](LC/Solutions/java/1191.java) | Medium+(1748) |
| 152  | [乘积最大子数组](https://leetcode.cn/problems/maximum-product-subarray/) | [java](LC/Solutions/java/152.java)  |    Medium+    |



