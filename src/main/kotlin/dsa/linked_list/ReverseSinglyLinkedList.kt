package org.example.dsa.linked_list

/**
 * Definition for singly-linked list.
 *
 * @property `val` The integer value of the node.
 * @property next The reference to the next node in the list.
 */
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {

    /**
     * Reverses a singly linked list.
     *
     * This function takes the head of a singly linked list and reverses the links
     * between the nodes, returning the new head of the reversed list.
     *
     * @param head The head node of the original linked list.
     * @return The new head node of the reversed linked list.
     */
    fun reverseList(head: ListNode?): ListNode? {
        if (head?.next == null) return head
        var current = head
        var newHead: ListNode? = null
        while (current != null) {
            val tempNext = current.next
            current.next=newHead
            newHead=current
            current=tempNext
        }
        return newHead
    }
}