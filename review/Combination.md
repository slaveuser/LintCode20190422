 
 
 
## Combination (4)
**0. [Combinations.java](https://github.com/awangdev/LintCode/blob/master/Java/Combinations.java)**      Level: Medium      Tags: [Backtracking, Combination, DFS]
      

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

#### DFS, Backtracking
- for loop, recursive (dfs).
- 每个item用一次, 下一个level dfs(index + 1)
- Combination DFS. 画个图想想. 每次从1~n里面pick一个数字i
- 因为下一层不能重新回去 [0~i]选，所以下一层recursive要从i+1开始选。



---

**1. [Combination Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      
time: O(n!)
space: O(n!)

给一串数字candidates (no duplicates), 和一个target. 

找到所有unique的 组合(combination) int[], 要求每个combination的和 = target.

注意: 同一个candidate integer, 可以用任意多次.


#### DFS, Backtracking
- 考虑input: 没有duplicate, 不需要sort
- 考虑重复使用的规则: 可以重复使用, 那么for loop里面dfs的时候, 使用curr index i
- the result is trivial, save success list into result.

##### Time complexity for Combination (reuse-candidate)
- at each level dfs, we have the index as starting point: 
- if we are at `index=0, we can have n child dfs() options via for loop`; 
- if at `index=1, we will have (n-1) dfs options via for loop`. 
- Consider it as the `pick/not-pick` problem, where the difference is you can pick `x` times at each index rather than only 2 times. 
- Overall, we will multiply the # of possibilities: n * (n - 1) * (n - 2) ... * 1 = n! => `O(n!)`

##### Combination DFS 思想
- 在每个index上面都要面临: `pick/not pick的选择`, 用for loop over index + backtracking 实现 picks.
- 每次pick以后, 就生成一条新的routine, from this index
- 下一个level的dfs从这个index开始, 对后面(或者当下/if allow index reuse) 进行同样的 pick/not pick 的选择
- 注意1: 每个level dfs 里面, for loop 里会有 end condition: 就不必要dfs下去了.
- 注意2: Backtracking在success case && dfs case 后都要做, 因为backtrack 是为了之前上一层dfs.




---

**2. [Combination Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum%20II.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      

给一串数字candidates (can have duplicates), 和一个target. 

找到所有unique的 组合(combination) int[], 要求每个combination的和 = target.

注意: 同一个candidate integer, 只可以用一次.

#### DFS, Backtracking
- when the input has duplicates, and want to skip redundant items? 
- 1. sort. 2. in for loop, skip same neighbor.
- 考虑input: 有duplicate, 必须sort
- 考虑重复使用的规则: 不可以重复使用
- 1. for loop里面dfs的时候, 使用curr index + 1
- 2. for loop里面, 同一个level, 同一个数字, 不能重复使用: `(i > index && candidates[i] == candidates[i - 1]) continue`
- 因为在同一个level里面重复的数字在下一个dfs level里面是会被考虑到的, 这里必须skip (这个就记住吧)
- the result is trivial, save success list into result.

##### Time complexity
- Which one?
- Time: every level has 1 less element to choose, worst case is: cannot find any solution over all combinations: O(m!)
- Time: Same as `subsetII`, pick/not=pick an item as we go, no reuse of item. Worst case: all unique items in the set. O(2^n)




---

**3. [Combination Sum III.java](https://github.com/awangdev/LintCode/blob/master/Java/Combination%20Sum%20III.java)**      Level: Medium      Tags: [Array, Backtracking, Combination, DFS]
      

给一个integer k, 和一个target n. 

从positive数字[1 ~ 9], 找到所有unique的 组合(combination) int[], size = k, 要求每个combination的和 = n.

(隐藏条件, 需要clarify): 同一个candidate integer [1 ~ 9], 只可以用一次.

#### DFS, Backtracking
- 跟Combination Sum I, II 没什么太大区别, 只不过, 一定要用k个数字, 也就是一个for loop里面的特别条件
- 考虑input: 没有重复数字 [1 ~ 9]
- 考虑candidate重复利用: 不可以重复利用, next level dfs 时候, curr index + 1
- the result is trivial, save success list into result.

##### Time Complexity
- Which one?
- worst case: tried all numbers and cannot find: O(m!), m = 9, all possible integers in [1~9]
- C(n,k), n choose k problem : `n! / (k! * (n-k)!)` => ends up being `O(min(n^k, n^(n-k)))`



---

