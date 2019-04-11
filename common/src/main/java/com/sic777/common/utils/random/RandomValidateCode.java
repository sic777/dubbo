package com.sic777.common.utils.random;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class RandomValidateCode {
    private final static Random random = new Random();
    private final static String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final static int width = 80;
    private final static int height = 26;
    private final static int lineSize = 40;
    private final static int stringNum = 4;

    private static Font getFont() {
        return new Font("Fixedsys", Font.PLAIN, 18);
    }

    private static Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    public static String outputRandomCode(OutputStream outputStream, String phone) throws IOException {
        return outputRandomCode(outputStream, phone, null);
    }

    public static String outputRandomCode(OutputStream outputStream, String phone, IStoreProcess process) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        g.setColor(getRandColor(110, 133));
        for (int i = 0; i <= lineSize; i++) {
            drawLine(g);
        }
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drawString(g, randomString, i);
        }
        g.dispose();
        ImageIO.write(image, "JPEG", outputStream);
        if (null != process) {
            process.store(phone, randomString);
        }
        return randomString;
    }

    private static String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }

    private static String drawString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    private static void drawLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    public interface IStoreProcess {
        void store(String phone, String randomCode);
    }
}


