package Pixelizer.NegaTiV444;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Transformer {

    public static WritableImage transform(Image img, int pixelSize)
    {
        int height = (int)img.getHeight(), width = (int)img.getWidth();
        WritableImage resultImg = new WritableImage(width, height);
        PixelReader pr = img.getPixelReader();
        PixelWriter pw = resultImg.getPixelWriter();
        Color newColor, curColor = pr.getColor(0, 0);

        int x, y, sizeX, sizeY, lenH = height / pixelSize, lenW = width / pixelSize;

        int deltaH = height - lenH * pixelSize, deltaW = width - lenW * pixelSize;
        double R, G, B;

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
            {
                pw.setColor(i, j, Color.WHITE);
            }

        for (int i = 0; i < lenW; i++)
            for (int j = 0; j < lenH; j++)
            {
                R = 0;
                G = 0;
                B = 0;
                sizeX = pixelSize;
                for (x = i * pixelSize; sizeX > 0; x++, sizeX--)
                {
                    sizeY = pixelSize;
                    for (y = j * pixelSize; sizeY > 0; y++, sizeY--)
                    {
                        curColor = pr.getColor(x, y);
                        R += curColor.getRed();
                        G += curColor.getGreen();
                        B += curColor.getBlue();
                    }
                }
                newColor = new Color(R/pixelSize/pixelSize, G/pixelSize/pixelSize, B/pixelSize/pixelSize, 1.0);
                sizeX = pixelSize;
                newColor = newColor.brighter();
                newColor = newColor.saturate();


                for (x = i * pixelSize; sizeX > 0; x++, sizeX--)
                {
                    sizeY = pixelSize;
                    for (y = j * pixelSize; sizeY > 0; y++, sizeY--)
                    {
                        pw.setColor(x, y, newColor);
                    }
                }
            }
            //TODO Fix delta-line
//            for (int i = 0; deltaW > 0; i++, deltaW--)
//                for (int j = 0; deltaH > 0; j++, deltaH--)
//                {
//
//                }
        return resultImg;
    }

}
