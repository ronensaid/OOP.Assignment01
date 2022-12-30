package observer;

import java.util.ArrayList;
import java.util.List;

public class GroupAdmin implements Sender {

    UndoableStringBuilder undoable;
    ArrayList<Member> Members;

    public GroupAdmin() {
        Members = new ArrayList<>();
        undoable = new UndoableStringBuilder();
    }

    /***
     * we register the members that want to join into an ArrayList,
     * the reason behind choosing ArrayList because it is easier to,
     * navigate through.
     * @param obj of type member
     */
    @Override
    public void register(Member obj) {
        if (Members.contains(obj)) {
            System.out.println("the Member is already registered!");
            return;
        }
        Members.add(obj);
        obj.update(undoable);

    }

    /***
     * we unregister the members that want to leave,
     * @param obj of type member
     */
    @Override
    public void unregister(Member obj) {
        if (!(Members.contains(obj))) {
            System.out.println("there is no such Member!");
            return;
        }
        Members.remove(obj);
    }

    @Override
    public void insert(int offset, String obj) {
        undoable.insert(offset, obj);
        updateAll();

    }

    @Override
    public void append(String obj) {
        undoable.append(obj);
        updateAll();
    }

    @Override
    public void delete(int start, int end) {
        undoable.delete(start, end);
        updateAll();
    }

    @Override
    public void undo() {
        undoable.undo();
        updateAll();
    }

    public String toString() {
        return undoable.toString();
    }

    private void updateAll() {
        for (int i = 0; i < Members.size(); i++) {
            Members.get(i).update(undoable);
        }
    }
}
