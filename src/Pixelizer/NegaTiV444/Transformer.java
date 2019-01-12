package Pixelizer.NegaTiV444;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Transformer {

    //Brightness = -1/0/1 - make image darker/don't change/brighter
    //Saturation = -1/0/1 - desaturate/don't change/saturate image
    //isInvert = false/true don't change/invert colors

    public static WritableImage transform(Image img, int pixelSize, int brightness, int saturation, boolean isInvert)
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
                if (brightness == -1)
                    newColor = newColor.darker();
                else
                    if (brightness == 1)
                        newColor = newColor.brighter();

                if (saturation == -1)
                    newColor = newColor.desaturate();
                else
                    if (saturation == 1)
                        newColor = newColor.saturate();
                if (isInvert)
                    newColor = newColor.invert();
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
