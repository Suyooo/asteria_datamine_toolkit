package be.suyo.toastoolkit.ccbi2ccb.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

import be.suyo.toastoolkit.ccbi2ccb.structs.Node;

public class NodeIterator implements Iterator<Node> {
    Stack<Node> stack = new Stack<>();

    public NodeIterator(Node start) {
        stack.add(start);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Node next() {
        Node current = stack.pop();
        stack.addAll(Arrays.asList(current.children));
        return current;
    }
}
