package test.main.java;

import main.java.BinarySearchTree;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    // Helper classes for testing the BinarySearchTree
    static class TestIdentity implements BinarySearchTree.Identity {
        private int id;

        public TestIdentity(int id) {
            this.id = id;
        }

        @Override
        public boolean match(BinarySearchTree.Identity other) {
            if (other instanceof TestIdentity) {
                return this.id == ((TestIdentity) other).id;
            }
            return false;
        }

        @Override
        public boolean isLessThan(BinarySearchTree.Identity other) {
            if (other instanceof TestIdentity) {
                return this.id < ((TestIdentity) other).id;
            }
            return false;
        }

        @Override
        public String toString() {
            return "TestIdentity{" +
                    "id=" + id +
                    "}";
        }
    }

    static class TestIdentifiedObject implements BinarySearchTree.IdentifiedObject {
        private int id;
        private String name;

        public TestIdentifiedObject(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public BinarySearchTree.Identity getIdentity() {
            return new TestIdentity(this.id);
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "TestIdentifiedObject{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    "}";
        }
    }

    @Test
    void add() {
        BinarySearchTree bst = new BinarySearchTree();
        TestIdentifiedObject obj1 = new TestIdentifiedObject(1, "Alice");
        TestIdentifiedObject obj2 = new TestIdentifiedObject(2, "Bob");
        TestIdentifiedObject obj3 = new TestIdentifiedObject(3, "Charlie");

        bst.add(obj2);
        bst.add(obj1);
        bst.add(obj3);

        BinarySearchTree.Identity findId1 = new TestIdentity(1);
        BinarySearchTree.Identity findId2 = new TestIdentity(2);
        BinarySearchTree.Identity findId3 = new TestIdentity(3);

        assertEquals(obj1.getId(), ((TestIdentifiedObject) bst.find(findId1)).getId(), "Check Alice was added correctly");
        assertEquals(obj2.getId(), ((TestIdentifiedObject) bst.find(findId2)).getId(), "Check Bob was added correctly");
        assertEquals(obj3.getId(), ((TestIdentifiedObject) bst.find(findId3)).getId(), "Check Charlie was added correctly");
    }

    @Test
    void find() {
        BinarySearchTree bst = new BinarySearchTree();
        TestIdentifiedObject obj1 = new TestIdentifiedObject(1, "Alice");
        TestIdentifiedObject obj2 = new TestIdentifiedObject(2, "Bob");
        bst.add(obj1);
        bst.add(obj2);

        BinarySearchTree.Identity findId1 = new TestIdentity(1);
        BinarySearchTree.Identity findId2 = new TestIdentity(2);
        BinarySearchTree.Identity findId3 = new TestIdentity(3); // Not in the tree

        assertEquals(obj1.getId(), ((TestIdentifiedObject) bst.find(findId1)).getId(), "Should find Alice");
        assertEquals(obj2.getId(), ((TestIdentifiedObject) bst.find(findId2)).getId(), "Should find Bob");
        assertNull(bst.find(findId3), "Should not find Charlie");
    }
}
