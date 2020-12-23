package dev.esz.aoc.day23;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class CircularLinkedList {

    @RequiredArgsConstructor
    @Getter
    public static class Node {
        private final int value;

        @Setter
        private Node next;
    }

    private Node head;
    private Node tail;
    private int size;

    public Node add(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
            head.setNext(head);
            tail = head;
        } else {
            node.setNext(head);
            tail.setNext(node);
            tail = node;
        }
        size++;
        return node;
    }
}
