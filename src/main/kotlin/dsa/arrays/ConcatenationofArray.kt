package org.example.dsa.arrays

/**
 * Given an integer array [nums] of length `n`, this function returns a new array [ans] of length `2n`
 * such that:
 *
 * - For all `0 <= i < n`, `ans[i] == nums[i]`
 * - For all `0 <= i < n`, `ans[i + n] == nums[i]`
 *
 * In simpler terms, [ans] is the result of concatenating [nums] with itself.
 *
 * @param nums An integer array of length `n`.
 * @return A new integer array of length `2n` that is the concatenation of two [nums] arrays.
 */

//slow approach
fun getConcatenation(nums: IntArray): IntArray {
    val ans = IntArray(nums.size * 2)
    for (i in ans.indices) {
        if (i < nums.size) {
            ans[i] = nums[i]
        } else {
            ans[i] = nums[i - nums.size]
        }
    }
    return ans
}
//faster approach
fun getConcatenation2(nums: IntArray): IntArray {
    val arraySize = nums.size
    val ans = IntArray(arraySize * 2)
    for (i in nums.indices) {
        ans[i] = nums[i]
        ans[i + arraySize] = ans[i]
    }
    return ans
}