import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.io.Serial;

class NumOnlyDocument extends PlainDocument {
    @Serial
    private static final long serialVersionUID = 1001689415662878505L;

    public NumOnlyDocument() {
        super();
    }

    public void insertString(int offset, String inStr, AttributeSet attrSet)
            throws BadLocationException {
        String numStr = getText(0, offset) + inStr + getText(offset, getLength() - offset);

        if (!numStr.matches("^\\d+$") || numStr.startsWith("-")) {
            return;
        }

        super.insertString(offset, inStr, attrSet);
    }

}
