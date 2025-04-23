package org.example.dsa.linked_list

import kotlin.math.pow

/**
 * Given head which is a reference node to a singly-linked list.
 * The value of each node in the linked list is either 0 or 1.
 * The linked list holds the binary representation of a number.
 *
 * @param head The head node of the linked list
 * @return The decimal value of the number in the linked list.
 *
 * The most significant bit is at the head of the linked list.
 */
/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
fun getDecimalValue(head: ListNode?): Int {
        var travrser=head
        val digits= mutableListOf<Int>()
        var result=0.0
    while (travrser!=null){
        digits.add(travrser.`val`)
        travrser=travrser.next
    }
    for (i in  digits.indices) {
        result += (digits.reversed()[i]*(2.0.pow(i.toDouble())))
    }

    return result.toInt()
}
    //lift shifting
    fun getDecimalValue2(head: ListNode?): Int {
        var num = 0
        var current = head
        while (current != null) {
            num = num * 2 + current.`val`
            current = current.next
        }
        return num
    }
