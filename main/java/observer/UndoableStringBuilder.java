package observer;

import java.util.Stack;

/**
 * A mutable sequence of characters. This class provides an API compatible with
 * StringBuffer, but with no guarantee of synchronization. This class is
 * designed for use as a drop-in replacement for StringBuffer in places where
 * the string buffer was being used by a single thread (as is generally the
 * case). Where possible, it is recommended that this class be used in
 * preference to StringBuffer as it will be faster under most implementations.
 * for this UndoableStringBuilder class we use the known StringBuilder class
 * with an addition of an undo function for the given function,also this class
 * uses a stack in order to store the string after any action taken as for
 * example: if we append the string by "abc" and delete the last character so
 * the string now is "ab",so the stack now contains "abc","ab" if we undo the
 * last action then the string will be "abc" again.
 */
public class UndoableStringBuilder {
    private StringBuilder s;
    private Stack<StringBuilder> st;

    public UndoableStringBuilder() {
        this.s = new StringBuilder();
        this.st = new Stack<>();
        st.push(s);
    }

    /**
     * pops the first string out of the stack and points at the next one so it
     * returns the string that was created before doing the action.
     *
     * @return the next peek of the stack
     */
    public void undo() {

        if (st.isEmpty()) {
            this.s = s.append("");
        }
        if (st.size() == 1) {
            st.pop();
            this.s = new StringBuilder();
        }
        st.pop();
        StringBuilder ans = st.peek();
        s = new StringBuilder(ans);
    }

    /**
     * Appends the string representation of the char argument to this sequence.
     *
     * @param str received string
     * @return UndoableStringBuilder type
     */
    public UndoableStringBuilder append(String str) {
        s.append(str);
        StringBuilder ss = new StringBuilder(s.toString());
        st.push(ss);

        return this;
    }

    /**
     * Removes the characters in a substring of this sequence. The substring begins
     * at the specified start and extends to the character at index end - 1 or to
     * the end of the sequence if no such character exists. If start is equal to
     * end, no changes are made.
     *
     * @param start index
     * @param end   index
     * @return a new string without the deleted characters of UndoableStringBuilder
     * type.
     */
    public UndoableStringBuilder delete(int start, int end) {
        s.delete(start, end);
        StringBuilder ss = new StringBuilder(s.toString());

        st.push(ss);

        return this;
    }

    /**
     * Inserts the string into this character sequence.
     *
     * @param offset as of the starting index
     * @param str    the given string to insert
     * @return the new string with the inserted string of UndoableStringBuilder
     * type.
     */
    public UndoableStringBuilder insert(int offset, String str) {
        s.insert(offset, str);

        StringBuilder ss = new StringBuilder(s.toString());
        st.push(ss);

        return this;
    }

    /**
     * Replaces the characters in a substring of this sequence with characters in
     * the specified String.
     *
     * @param start index
     * @param end   index
     * @param str   the given string to replace
     * @return the new string with the replaced indexes of UndoableStringBuilder
     * type.
     */
    public UndoableStringBuilder replace(int start, int end, String str) {
        s.replace(start, end, str);
        StringBuilder ss = new StringBuilder(s.toString());

        st.push(ss);

        return this;
    }

    /**
     * Causes this character sequence to be replaced by the reverse of the sequence.
     *
     * @return UndoableStringBuilder type.
     */
    public UndoableStringBuilder reverse() {
        s.reverse();
        StringBuilder ss = new StringBuilder(s.toString());

        st.push(ss);

        return this;
    }

    @Override
    public String toString() {
        return this.s.toString();
    }
}
