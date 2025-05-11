package org.example.dsa.linked_list



/**
 * Given the head of a linked list,
 * remove the nth node from the end of the list and return its head.*/
fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    var newHead=head
    var current=newHead
    var counter=1

    while (current?.next!=null){
        current= current.next!!
        counter++
    }
    current=newHead
    val index=counter-n
    if(index<=0)return newHead?.next

    for (i in 1 until  index){
        current=current?.next
    }
    current?.next=current?.next?.next

    return newHead
}