package org.example.dsa.linked_list

/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
/**
 * You are given the head of a linked list,
 * which contains a series of integers separated by 0's.
 * @param val ==0The beginning and end of the linked list will have  val == 0.
 * For every two consecutive 0's,
 * merge all the nodes lying in between them into a single node whose value is the sum of all the merged nodes.
 * The modified list should not contain any 0's.
 * Return the head of the modified linked list.
 */

fun mergeNodes(head: ListNode?): ListNode? {
    var current = head?.next
    val dummyhead = ListNode(0)
    var tail = dummyhead
    var sum = 0
    while (current != null) {
        if (current.`val` == 0) {
            tail.next = ListNode(sum)
            tail = tail.next!!
            sum = 0
        }
        sum += current.`val`
        current = current.next
    }
    return dummyhead.next
}
