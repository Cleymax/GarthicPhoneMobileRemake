package fr.univ_smb.iut_acy.perrincl.garthicphone.tools;

public class RandomUtils {

    public static int getRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
