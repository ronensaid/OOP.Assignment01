package observer;

import observer.*;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);

    // stub method to check external dependencies compatibility
    @Test
    public void testJvm() {

        Sender G = new GroupAdmin();

        logger.info(() -> "Footprint after Initialization: " + JvmUtilities.objectFootprint(G));

        Member m1 = new ConcreteMember();
        Member m2 = new ConcreteMember();
        Member m3 = new ConcreteMember();


        G.register(m1);
        G.register(m2);
        G.register(m3);

        logger.info(() -> "Footprint after adding members to the list: " + JvmUtilities.objectFootprint(G));

        G.append("welcome");
        logger.info(() -> "Footprint after append to the object: " + JvmUtilities.objectFootprint(G));

        logger.info(() -> " JVM INFO : " + JvmUtilities.jvmInfo());


    }

    @Test
    public void deleteMember() {
        Sender groupAdmin = new GroupAdmin();
        Member m1 = new ConcreteMember();
        Member m2 = new ConcreteMember();
        Member m3 = new ConcreteMember();

        groupAdmin.register(m1);
        groupAdmin.register(m2);
        groupAdmin.register(m3);
        groupAdmin.append("welcome");

        assert (JvmUtilities.objectTotalSize(m1.toString()).equals(JvmUtilities.objectTotalSize("member")));
        assert (JvmUtilities.objectTotalSize(m2.toString()).equals(JvmUtilities.objectTotalSize("member")));
        assert (JvmUtilities.objectTotalSize(m3.toString()).equals(JvmUtilities.objectTotalSize("member")));
        groupAdmin.unregister(m1);
        groupAdmin.append(" got changed");
        assert (JvmUtilities.objectTotalSize(m2.toString()).equals(JvmUtilities.objectTotalSize("member got changed")));
        assert (JvmUtilities.objectTotalSize(m3.toString()).equals(JvmUtilities.objectTotalSize("member got changed")));

    }

    @Test
    public void updateMembers_undo() {
        Sender groupAdmin = new GroupAdmin();
        Member m1 = new ConcreteMember();
        Member m2 = new ConcreteMember();
        Member m3 = new ConcreteMember();

        groupAdmin.register(m1);
        groupAdmin.register(m2);
        groupAdmin.register(m3);
        groupAdmin.append("spread love");

        assert (JvmUtilities.objectTotalSize(m1.toString()).equals(JvmUtilities.objectTotalSize("spread love")));
        assert (JvmUtilities.objectTotalSize(m2.toString()).equals(JvmUtilities.objectTotalSize("spread love")));
        assert (JvmUtilities.objectTotalSize(m3.toString()).equals(JvmUtilities.objectTotalSize("spread love")));
        groupAdmin.insert(11, " not hate");

        assert (JvmUtilities.objectTotalSize(m1.toString()).equals(JvmUtilities.objectTotalSize("spread love not hate")));
        assert (JvmUtilities.objectTotalSize(m2.toString()).equals(JvmUtilities.objectTotalSize("spread love not hate")));
        assert (JvmUtilities.objectTotalSize(m3.toString()).equals(JvmUtilities.objectTotalSize("spread love not hate")));

        groupAdmin.undo();
        assert (JvmUtilities.objectTotalSize(m1.toString()).equals(JvmUtilities.objectTotalSize("spread love")));
        assert (JvmUtilities.objectTotalSize(m2.toString()).equals(JvmUtilities.objectTotalSize("spread love")));
        assert (JvmUtilities.objectTotalSize(m3.toString()).equals(JvmUtilities.objectTotalSize("spread love")));


    }

    @Test
    public void test3() {
        Sender groupAdmin = new GroupAdmin();
        Member m1 = new ConcreteMember();
        Member m2 = new ConcreteMember();
        Member m3 = new ConcreteMember();

        groupAdmin.append("peace all over the world");

        groupAdmin.register(m1);
        groupAdmin.register(m2);
        groupAdmin.register(m3);
        assert (JvmUtilities.objectTotalSize(m1.toString()).equals(JvmUtilities.objectTotalSize("peace all over the world")));
        assert (JvmUtilities.objectTotalSize(m2.toString()).equals(JvmUtilities.objectTotalSize("peace all over the world")));
        assert (JvmUtilities.objectTotalSize(m3.toString()).equals(JvmUtilities.objectTotalSize("peace all over the world")));
        groupAdmin.delete(5, 24);

        assert (JvmUtilities.objectTotalSize(m1.toString()).equals(JvmUtilities.objectTotalSize("peace")));
        assert (JvmUtilities.objectTotalSize(m2.toString()).equals(JvmUtilities.objectTotalSize("peace")));
        assert (JvmUtilities.objectTotalSize(m3.toString()).equals(JvmUtilities.objectTotalSize("peace")));

        Member m4 = new ConcreteMember();
        groupAdmin.register(m4);
        assert (JvmUtilities.objectTotalSize(m4.toString()).equals(JvmUtilities.objectTotalSize("peace")));


    }


}