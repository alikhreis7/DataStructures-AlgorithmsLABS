/**
 * A simple node class for a singly-linked list.  Each node has a
 * reference to a stored element and a next node.
 * This class is based on the <code>DNode</code> class by Roberto Tamassia.
 *
 */

public class DNode {
  private Object element;
  private DNode previous;
  private DNode next;
  DNode() { this(null, null, null); }
    DNode(Object e, DNode P, DNode n) {
    element = e;
	previous = P;
    next = n;
  }
  public void setElement(Object newElem) { element = newElem; }
  public void setPrevious(DNode newPrevious) { previous = newPrevious; }
  public void setNext(DNode newNext) { next = newNext; }
  public Object getElement() { return element; }
  public DNode getPrevious() { return previous; }
  public DNode getNext() { return next; }
}