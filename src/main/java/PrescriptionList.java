package main.java;

public class PrescriptionList {
    private class ListRecord {
        public Prescription data;
        public ListRecord next;

        public ListRecord(Prescription pr) {
            this.data = pr;
            this.next = null;
        }
    }

    private ListRecord head;
    private ListRecord current;

    public PrescriptionList() {
        head = null;
        current = null;
    }

    private static boolean comesBefore(Prescription pr1, Prescription pr2) {
        return pr1.getDateOfIssue().isAfter(pr2.getDateOfIssue());
    }

    public void add(Prescription pr) {
        ListRecord newRecord = new ListRecord(pr);

        if (head == null || comesBefore(pr, head.data)) {
            newRecord.next = head;
            head = newRecord;
            return;
        }

        ListRecord pBefore = head;
        while (pBefore.next != null && comesBefore(pBefore.next.data, pr)) {
            pBefore = pBefore.next;
        }

        newRecord.next = pBefore.next;
        pBefore.next = newRecord;
    }

    public void init() {
        current = head;
    }

    public Prescription next() {
        if (current == null) return null;
        Prescription result = current.data;
        current = current.next;
        return result;
    }
}
