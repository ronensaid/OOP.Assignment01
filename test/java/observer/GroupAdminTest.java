package observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupAdminTest {

    @Test
    void register() {
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        ConcreteMember m3 = new ConcreteMember();
        GroupAdmin G = new GroupAdmin();
        G.insert(0, "marian");
        G.register(m1);
        G.register(m2);
        G.register(m3);
        assertEquals(G.undoable, m1.undoable);
        assertEquals(G.undoable, m2.undoable);
        assertEquals(G.undoable, m3.undoable);
        assertEquals(G.undoable.toString(), "marian");
        assertEquals(m1.undoable.toString(), "marian");
        assertEquals(m2.undoable.toString(), "marian");
        assertEquals(m3.undoable.toString(), "marian");
        //a test to make sure that we cannot register that same Member twice.
        G.register(m1);
        assertNotEquals(m1.undoable.toString(), "the Member is already registered!");
        assertEquals(G.Members.size(), 3);
    }

    @Test
    void unregister() {
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        ConcreteMember m3 = new ConcreteMember();
        GroupAdmin G = new GroupAdmin();
        G.register(m1);
        G.register(m2);
        G.register(m3);
        G.unregister(m2);
        G.unregister(m3);
        assertEquals(G.Members.size(), 1);
        assertEquals(G.Members.get(0), m1);
        G.unregister(m3);
        //a test to make sure that we cannot unregister that same Member twice.
        assertNotEquals(m3.undoable.toString(), "there is no such Member!");
        //there is no need to check the size because the Member is already
        //unregistered but just to make sure we keep it.
        assertEquals(G.Members.size(), 1);


    }

    @Test
    void insert() {
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        ConcreteMember m3 = new ConcreteMember();
        GroupAdmin G = new GroupAdmin();
        G.register(m1);
        G.register(m2);
        G.register(m3);
        G.insert(0, "ronen");
        assertEquals(G.undoable, m1.undoable);
        assertEquals(G.undoable, m2.undoable);
        assertEquals(G.undoable, m3.undoable);
        assertEquals(G.undoable.toString(), "ronen");
        assertEquals(m1.undoable.toString(), "ronen");
        assertEquals(m2.undoable.toString(), "ronen");
        assertEquals(m3.undoable.toString(), "ronen");

    }

    @Test
    void append() {
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        ConcreteMember m3 = new ConcreteMember();
        GroupAdmin G = new GroupAdmin();
        G.insert(0, "marian");
        G.register(m1);
        G.register(m2);
        G.register(m3);
        G.append("ronen");
        assertEquals(G.undoable, m1.undoable);
        assertEquals(G.undoable, m2.undoable);
        assertEquals(G.undoable, m3.undoable);
        assertEquals(G.undoable.toString(), "marianronen");
        assertEquals(m1.undoable.toString(), "marianronen");
        assertEquals(m2.undoable.toString(), "marianronen");
        assertEquals(m3.undoable.toString(), "marianronen");
    }

    @Test
    void delete() {
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        ConcreteMember m3 = new ConcreteMember();
        GroupAdmin G = new GroupAdmin();
        G.insert(0, "ronen");
        G.register(m1);
        G.register(m2);
        G.register(m3);
        G.delete(3, 5);
        assertEquals(G.undoable, m1.undoable);
        assertEquals(G.undoable, m2.undoable);
        assertEquals(G.undoable, m3.undoable);
        assertEquals(G.undoable.toString(), "ron");
        assertEquals(m1.undoable.toString(), "ron");
        assertEquals(m2.undoable.toString(), "ron");
        assertEquals(m3.undoable.toString(), "ron");
    }

    @Test
    void undo() {
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        ConcreteMember m3 = new ConcreteMember();
        GroupAdmin G = new GroupAdmin();
        G.insert(0, "ronen");
        G.register(m1);
        G.register(m2);
        G.register(m3);
        G.delete(4, 5);
        G.undo();
        assertEquals(G.undoable, m1.undoable);
        assertEquals(G.undoable, m2.undoable);
        assertEquals(G.undoable, m3.undoable);
        assertEquals(G.undoable.toString(), "ronen");
        assertEquals(m1.undoable.toString(), "ronen");
        assertEquals(m2.undoable.toString(), "ronen");
        assertEquals(m3.undoable.toString(), "ronen");
    }
}