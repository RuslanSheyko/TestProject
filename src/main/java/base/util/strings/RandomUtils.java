package base.util.strings;

import java.util.Random;

public class RandomUtils {
    public String randomLatinString15() {
        return randomLatinString(15);
    }

    /**
     * Randomize a string of a certain length from letters 'a' - 'z'
     *
     * @param length - length of the string
     * @return - received string
     */
    public String randomLatinString(int length) {
        int leftLimit = 97; // 'a'
        int rightLimit = 122; // 'z'
        int targetStringLength = length;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();

    }
}