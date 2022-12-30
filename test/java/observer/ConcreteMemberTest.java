package observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteMemberTest {

    @Test
    void update() {
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        ConcreteMember m3 = new ConcreteMember();
        GroupAdmin G = new GroupAdmin();
        G.register(m1);
        G.register(m2);
        G.register(m3);
        G.insert(0, "voice of the heroes");
        for (int i = 0; i < G.Members.size(); i++) {
            assertEquals(G.undoable, ((ConcreteMember) G.Members.get(i)).undoable);
            assertEquals(((ConcreteMember) G.Members.get(i)).undoable.toString(), "voice of the heroes");
        }
    }
}