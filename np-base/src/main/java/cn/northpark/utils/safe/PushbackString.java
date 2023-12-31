package cn.northpark.utils.safe;

public class PushbackString {
    private String input;
    private Character pushback;
    private Character temp;
    private int index = 0;
    private int mark = 0;

    public PushbackString(String input) {
        this.input = input;
    }

    public void pushback(Character c) {
        this.pushback = c;
    }

    public int index() {
        return this.index;
    }

    public boolean hasNext() {
        if (this.pushback != null) return true;
        if (this.input == null) return false;
        if (this.input.length() == 0) return false;
        return (this.index < this.input.length());
    }

    public Character next() {
        if (this.pushback != null) {
            Character save = this.pushback;
            this.pushback = null;
            return save;
        }
        if (this.input == null) return null;
        if (this.input.length() == 0) return null;
        if (this.index >= this.input.length()) return null;
        return new Character(this.input.charAt(this.index++));
    }

    public Character nextHex() {
        Character c = next();
        if (c == null) return null;
        if (isHexDigit(c)) return c;
        return null;
    }

    public static boolean isHexDigit(Character c) {
        return ("0123456789ABCDEFabcdef".indexOf(c.charValue()) != -1);
    }

    public Character peek() {
        if (this.pushback != null) return this.pushback;
        if (this.input == null) return null;
        if (this.input.length() == 0) return null;
        if (this.index >= this.input.length()) return null;
        return new Character(this.input.charAt(this.index));
    }

    public boolean peek(char c) {
        if ((this.pushback != null) && (this.pushback.charValue() == c)) return true;
        if (this.input == null) return false;
        if (this.input.length() == 0) return false;
        if (this.index >= this.input.length()) return false;
        return (this.input.charAt(this.index) == c);
    }

    public boolean isPushback() {
        return (this.pushback != null);
    }

    public void mark() {
        this.temp = this.pushback;
        this.mark = this.index;
    }

    public void reset() {
        this.pushback = this.temp;
        this.index = this.mark;
    }

    protected String remainder() {
        String output = this.input.substring(this.index);
        if (this.pushback != null) {
            output = this.pushback + output;
        }
        return output;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();

        if (this.pushback != null)
            sb.append(this.pushback);
        if ((this.input != null) && (this.index < this.input.length()))
            sb.append(this.input.substring(this.index));
        return sb.toString();
    }
}