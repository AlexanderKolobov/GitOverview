package net.kolobov.gitoverview;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * @author Kolobov Alex
 */
public class BarcodeTest {

    public static void main(String[] args) throws Exception {

        String prepareId = String.valueOf(102365L);

        //Get Code-128 Barcode instance from the Factory
        Barcode barcodeLabel = BarcodeFactory.createCode128(prepareId);
        barcodeLabel.setBarWidth(5);
        barcodeLabel.setResolution(1000);

        File image = new File("128.png");

        BarcodeImageHandler.savePNG(barcodeLabel, image);

        //Write barcode to buffer
        BufferedImage bufferedImage = BarcodeImageHandler.getImage(barcodeLabel);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);

        byte[] barcodeImage = baos.toByteArray();
    }
}
