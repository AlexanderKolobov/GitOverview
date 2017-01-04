package net.kolobov.gitoverview;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author Kolobov Alex
 */
public class BarcodeTest {


    public static void main(String[] args) throws Exception {
        String prepareId1 = String.valueOf(106061L);
        String prepareId2 = String.valueOf(106062L);
        String prepareId3 = String.valueOf(106063L);
        String prepareId4 = String.valueOf(987654L);
        String prepareId5 = String.valueOf(345678L);

//        createBarcodeImage(prepareId1);
//        createImageCell();
        byte[] barcodeImage1 = createBarcodeImage(prepareId1);
        byte[] barcodeImage2 = createBarcodeImage(prepareId2);
        byte[] barcodeImage3 = createBarcodeImage(prepareId3);
        byte[] barcodeImage4 = createBarcodeImage(prepareId4);
        byte[] barcodeImage5 = createBarcodeImage(prepareId5);

        insertImageInTemplate(barcodeImage1,
                barcodeImage2,
                barcodeImage3,
                barcodeImage4,
                barcodeImage5);


    }

    private static byte[] createBarcodeImage(String prepareId) throws BarcodeException, OutputException, IOException {
        //Get Code-128 Barcode instance from the Factory
        Barcode barcodeLabel = BarcodeFactory.createCode128(prepareId);
        barcodeLabel.setBarWidth(6);
        barcodeLabel.setResolution(1000);
        barcodeLabel.setBounds(1,1,100,100);

        File image = new File("128.png");

        BarcodeImageHandler.savePNG(barcodeLabel, image);

        //Write barcode to buffer
        BufferedImage bufferedImage = BarcodeImageHandler.getImage(barcodeLabel);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);

        byte[] barcodeImage = baos.toByteArray();
        return barcodeImage;
    }

    public static void createImageCell() throws IOException, WriteException {
        //Creates a writable workbook with the given file name
        WritableWorkbook workbook = Workbook.createWorkbook(new File("AddImage.xls"));

        WritableSheet sheet = workbook.createSheet("My Sheet", 0);

        WritableImage image1 = new WritableImage(
                2, 4,   //column, row
                6, 11,   //width, height in terms of number of cells
                new File("128.png")); //Supports only 'png' images

        sheet.addImage(image1);

        //Writes out the data held in this workbook in Excel format
        workbook.write();

        //Close and free allocated memory
        workbook.close();
    }

    private static void insertImageInTemplate(byte[]... barcodeImage) throws IOException, BiffException, WriteException {

        WorkbookSettings ws = new WorkbookSettings();
        ws.setSuppressWarnings(true);

        Workbook workbook = Workbook.getWorkbook(new File("Stock labels.xls"), ws);

        WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File("Result.xls"), workbook);
        WritableSheet writableSheet = writableWorkbook.getSheet(0);

        // Create cell font and format
        /*WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 22);
        cellFont.setBoldStyle(WritableFont.BOLD);

        WritableCellFormat cellFormat = new WritableCellFormat(cellFont);

        Label label = new Label(5, 0, "Hello, world!", cellFormat);
        Label label1 = new Label(1, 0, "My new stock Label", cellFormat);

        writableSheet.addCell(label);
        writableSheet.addCell(label1);*/
        int length = barcodeImage.length;
        WritableImage writableImage1 = null;
//        WritableImage writableImage2 = null;
//        WritableImage writableImage3 = null;
        for (int i = 1; i <= length; i++) {
            writableImage1 = new WritableImage(0, i * 9 - 1, 4, 1, barcodeImage[i - 1]);
//            writableImage2 = new WritableImage(0, 17, 4, 1, barcodeImage[1]);
//            writableImage3 = new WritableImage(0, 26, 4, 1, barcodeImage[2]);
            writableSheet.addImage(writableImage1);
        }

//        writableSheet.addImage(writableImage2);
//        writableSheet.addImage(writableImage3);

        writableWorkbook.write();
        writableWorkbook.close();
        workbook.close();


    }
}
