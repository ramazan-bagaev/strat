package Generation;

import java.util.ArrayList;
import java.util.Random;

public class NameGenerator {

    private static char[] vowels = new char[]{'e', 'e', 'a', 'a', 'o', 'o', 'i', 'y', 'u', 'i', 'o', 'a'};
    private static char[] consonant = new char[]{'q', 'w', 'r', 't', 'p', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'v', 'b', 'n', 'm'};

    private Random random;

    public NameGenerator(Random random){
        this.random = random;
    }

    public String generate(){
        String result = new String();
        int size = random.nextInt(8) + 2;
        boolean vow = random.nextBoolean();
        if (vow) {
            char a = vowels[random.nextInt(vowels.length)];
            result+=a;
            result.toUpperCase();
            for(int i = 1; i < size; i++){
                if (i % 2 == 0) {
                    a = vowels[random.nextInt(vowels.length)];
                    result+=a;
                }
                else{
                    a = consonant[random.nextInt(consonant.length)];
                    result+=a;
                }
            }
        }
        if (!vow) {
            char a = consonant[random.nextInt(consonant.length)];
            result+=a;
            result.toUpperCase();
            for(int i = 1; i < size; i++){
                if (i % 2 == 1) {
                    a = vowels[random.nextInt(vowels.length)];
                    result+=a;
                }
                else{
                    a = consonant[random.nextInt(consonant.length)];
                    result+=a;
                }
            }
        }
        return result;
    }
}
