package observer;

public class ConcreteMember implements Member {
    UndoableStringBuilder undoable = new UndoableStringBuilder();


    /***
     * shallow copy of the undoable from the GroupAdmin
     * @param usb the stringBuilder of GroupAdmin
     */
    @Override

    public void update(UndoableStringBuilder usb) {

        this.undoable = usb;

    }

    /***
     * built for test cases, because changing interfaces is forbidden,
     * so we override the undoablestringbuilder tostring()
     * @return string
     */
    @Override
    public String toString() {
        return undoable.toString();
    }
}