package org.github.ponking66.ccecdit.util;

/**
 * @author pony
 * @date 2023/7/14
 */
public class WordUtil {

    private WordUtil() {
    }


    public static boolean isUpperCase(String word) {
        for (char alpha : word.toCharArray()) {
            if (!Character.isUpperCase(alpha)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isCompositeName(String sub) {
        return isCompositeNameByUnderline(sub) || isCompositeNameByOneAlphaUpperCase(sub);
    }

    public static boolean isCompositeNameByOneAlphaUpperCase(String sub) {
        char[] characters = sub.toCharArray();
        for (char character : characters) {
            if (character >= 'A' && character <= 'Z') {
                return true;
            }
        }
        return false;
    }

    public static boolean isCompositeNameByUnderline(String sub) {
        int index = sub.lastIndexOf("_");
        return index != -1;
    }


    /**
     * @param sub       单词或语句
     * @param underline 是否是下划分割
     */
    public static int findLastWordStartIndex(String sub, boolean underline) {
        if (!underline) {
            char[] characters = sub.toCharArray();
            for (int i = characters.length - 1; i >= 0; i--) {
                if (characters[i] >= 'A' && characters[i] <= 'Z') {
                    return i;
                }
            }
            return -1;
        } else {
            return sub.lastIndexOf("_") + 1;
        }
    }

    /**
     * @param sub       单词或语句
     * @param underline 是否是下划分割
     */
    public static String findLastWord(String sub, boolean underline) {
        int lastWordStartIndex = findLastWordStartIndex(sub, underline);
        return sub.substring(lastWordStartIndex);
    }

}
