package org.example.dsa.linked_list

/**
 * You are given the heads of two sorted linked
 * lists list1 and list2.

 * Merge the two lists into one sorted list.
 * The list should be made by splicing together the nodes of the first two lists.

 * Return the head of the merged linked list.
 * */

fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    val newHead = ListNode(0)
    var firstListCurrent = list1
    var secondListCurrent = list2
    var current=newHead

    while (firstListCurrent != null && secondListCurrent!= null) {


        if (firstListCurrent.`val`<secondListCurrent.`val`){

            current.next=firstListCurrent
            firstListCurrent=firstListCurrent.next
        }else{
            current.next=secondListCurrent
            secondListCurrent=secondListCurrent.next
        }
        current=current.next!!
        if (firstListCurrent?.next!=null){current.next=firstListCurrent}
        if (secondListCurrent?.next!=null){current.next=secondListCurrent}
    }
    return newHead.next
}



