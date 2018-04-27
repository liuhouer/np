package cn.northpark.utils.safe;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class XSS {
    public String URLEncode(String val) throws Exception {
        if (val != null) {
            try {
                return URLEncoder.encode(val, "utf-8");
            } catch (UnsupportedEncodingException e) {
                throw new Exception(e);
            }
        }
        return "";
    }

    public String URLDecode(String val) throws Exception {
        if (val != null) {
            try {
                return URLDecoder.decode(val, "utf-8");
            } catch (UnsupportedEncodingException e) {
                throw new Exception(e);
            }
        }
        return "";
    }

    public String HTMLEncode(String val) {
        if (val != null) {
            val = val.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;")
                    .replace("/", "&#x2F;").replace("'", "&#x27;");
            return val;
        }
        return "";
    }

    public String HTMLDecode(String val) {
        if (val != null) {
            val = val.replace("&amp;", "&").replace("&lt;", "<").replace("&gt;", ">").replace("&quot;", "\"")
                    .replace("&#x2F;", "/").replace("&#x27;", "'");
            return val;
        }
        return "";
    }

    public String JSEncode(String val) {
        if (val != null) {
            val = val.replace("\\", "\\\\").replace("\"", "\\\"").replace("&", "\\&").replace("<", "\\<")
                    .replace(">", "\\>").replace("/", "\\/").replace("'", "\\'");
            return val;
        }
        return "";
    }

    public String JSDecode(String val) {
        if (val != null) {
            val = val.replace("\\\\", "\\").replace("\\\"", "\"").replace("\\&", "&").replace("\\<", "<")
                    .replace("\\>", ">").replace("\\/", "/").replace("\\'", "'");
            return val;
        }
        return "";
    }

    public String JSStrictEncode(String input) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            sb.append(encodeCharacter(new Character(c)));
        }
        return sb.toString();
    }

    public String encodeCharacter(Character c) {
        char ch = c.charValue();
        switch (ch) {
            case '\0':
                return "\\0";
            case '\b':
                return "\\b";
            case '\t':
                return "\\t";
            case '\n':
                return "\\n";
            case '\f':
                return "\\f";
            case '\r':
                return "\\r";
            case '\"':
                return "\\\"";
            case '\'':
                return "\\'";
            case '\\':
                return "\\\\";
        }

        String temp = Integer.toHexString(ch);
        if (ch <= 255) {
            String pad = "00".substring(temp.length());
            return "\\x" + pad + temp.toUpperCase();
        }

        String pad = "0000".substring(temp.length());
        return "\\u" + pad + temp.toUpperCase();
    }

    public String JSStrictDecode(String input) {
        StringBuffer sb = new StringBuffer();
        PushbackString pbs = new PushbackString(input);
        while (pbs.hasNext()) {
            Character c = decodeCharacter(pbs);
            if (c != null)
                sb.append(c);
            else {
                sb.append(pbs.next());
            }
        }
        return sb.toString();
    }

    public Character decodeCharacter(PushbackString input) {
        input.mark();
        Character first = input.next();
        if (first == null) {
            input.reset();
            return null;
        }

        if (first.charValue() != '\\') {
            input.reset();
            return null;
        }
        Character second = input.next();
        if (second == null) {
            input.reset();
            return null;
        }
        if (second.charValue() == '0')
            return new Character('\0');
        if (second.charValue() == 'b')
            return new Character('\b');
        if (second.charValue() == 't')
            return new Character('\t');
        if (second.charValue() == 'n')
            return new Character('\n');
        if (second.charValue() == 'f')
            return new Character('\f');
        if (second.charValue() == 'r')
            return new Character('\r');
        if (second.charValue() == '"')
            return new Character('\"');
        if (second.charValue() == '\'')
            return new Character('\'');
        if (second.charValue() == '\\') {
            return new Character('\\');
        }
        if (Character.toLowerCase(second.charValue()) == 'x') {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 2; ++i) {
                Character c = input.nextHex();
                if (c == null)
                    continue;
                sb.append(c);
            }
            if (sb.length() != 2)
                try {
                    int i = Integer.parseInt(sb.toString(), 16);

                    return new Character((char) i);
                } catch (NumberFormatException e) {
                    input.reset();
                    return null;
                }

        }

        if (Character.toLowerCase(second.charValue()) == 'u') {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 4; ++i) {
                Character c = input.nextHex();
                if (c == null)
                    continue;
                sb.append(c);
            }
            if (sb.length() == 4) {
                try {
                    int i = Integer.parseInt(sb.toString(), 16);

                    return new Character((char) i);
                } catch (NumberFormatException e) {
                    input.reset();
                    return null;
                }
            }
        }
        return second;
    }
}