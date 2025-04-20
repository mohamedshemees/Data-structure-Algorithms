package org.example

class LL {
    private var head: Node? = null
    private var tail: Node? = null
    private var size: Int = 0


    fun insertAtStart(value: Int) {
        val newNode = Node(value)
        if (head == null) {
            tail = newNode
            head = newNode
            newNode.apply {
                this.next = this
                this.previous = this
            }
        } else {
            newNode.next = head
            newNode.previous = tail
            head?.previous = newNode
            tail?.next = newNode
            head = newNode

        }
        size++
    }

    fun insertAtEnd(value: Int) {
        if (size == 0) {
            insertAtStart(value)
        } else {
            val newNode = Node(value)
            newNode.next = head
            newNode.previous = tail
            tail?.next = newNode
            tail = newNode
            head?.previous = tail
            size++
        }

    }

    fun insertAtIndex(value: Int, index: Int) {
        when {
            size == 0 -> {
                insertAtStart(value)
            }

            index < 0 -> {
                println("Invalid Negative Index please inter a valid index between 0 and $size")
            }

            index > size -> {
                println("Invalid out of bound Index please inter a valid index between 0 and $size")

            }

            else -> {
                val newNode = Node(value)
                var iterator = head
                for (i in 0 until index) {
                    iterator = iterator?.next
                }
                newNode.next = iterator
                newNode.previous = iterator?.previous
                iterator?.previous?.next = newNode
                iterator?.previous = newNode
                size++
            }
        }
    }

    fun removeFromStart() {
        when (size) {
            0 -> println("list is already empty !")
            1 -> {
                head = null;tail = null;size--
            }

            else -> {
                head = head?.next
                head?.previous = tail
                tail?.next = head
                size--
            }
        }
    }

    fun removeFromEnd() {
        when (size) {
            0 -> println("list is already empty")
            1 -> {
                removeFromStart()
            }

            else -> {
                tail = tail?.previous
                tail?.next = head
                head?.previous = tail
                size--
            }
        }
    }

    fun removeFromIndex(index: Int) {
        val nodeToBeRemoved = traverse(index)
        nodeToBeRemoved?.previous?.next = nodeToBeRemoved?.next
        nodeToBeRemoved?.next?.previous = nodeToBeRemoved?.previous
        size--

    }


    private fun getLastElement(): Int? {
        return tail?.value
    }

    private fun getFirstElement(): Int? {
        return head?.value
    }

    fun getAtIndex(index: Int): Int? {
        return when {
            size == 0 -> {
                println("List is empty")
                null
            }

            index < 0 || index >= size -> {
                println("Invalid index: $index. Must be between 0 and ${size - 1}")
                null
            }

            else -> {
                traverse(index)?.value
            }
        }

    }

    fun findElement(value: Int):Int?{

        var traverser=head
        for (i in 0..size){
            if(traverser?.value==value){
                return i
            }
            traverser=traverser?.next
        }
        return null
    }

    private fun traverse(index: Int): Node? {
        var traverser = head
        for (i in 0 until index) {
            traverser = traverser?.next
        }
        return traverser
    }

    fun updateAtIndex(value: Int, index: Int){
        var traverser = head
        for (i in 0 until index) {
            traverser = traverser?.next
        }
        traverser?.value=value

    }


    fun display() {
        var traverser = head
        for (i in 0 until size) {
            print(" ${traverser?.value} -->  ")
            traverser = traverser?.next
        }
        println(traverser)
    }
    fun reverse(){
        var traversar=head
        for (i in 0..size){
            val tempNode=traversar
            traversar?.next
            traversar=traversar?.next

        }

    }

    fun getSize() = size
    fun isEmpty():Boolean{
        return size==0
    }

}

data class Node(
    var value: Int,
    var next: Node? = null,
    var previous: Node? = null
) {
    override fun toString(): String {
        return "${this.value}"
    }
}


