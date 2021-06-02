package mts.teta.resizer.imageprocessor;

import marvin.image.MarvinImage;
import mts.teta.resizer.ResizerApp;
import net.coobird.thumbnailator.Thumbnails;
import static marvinplugins.MarvinPluginCollection.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageProcessor {
    private BufferedImage image;
    private MarvinImage img;

    public void processImage(BufferedImage image, ResizerApp resizer) throws IOException {
        this.image = image;
        img = new MarvinImage();
        img.setBufferedImage(image);
        if (resizer.getResizeWidth() != 0) resize(resizer.getResizeWidth(), resizer.getResizeHeight());
        if (resizer.getQuality() != 0) changeQuality(resizer.getQuality());
        if (resizer.getCropWidth() != 0) cropImage(resizer.getCropX(), resizer.getCropY(), resizer.getCropWidth(), resizer.getCropHeight());
        if (resizer.getBlurRadius() != 0) blur(resizer.getBlurRadius());
        ImageIO.write(this.image, resizer.getOutputFormat(), resizer.getOutputFile());
    }

    private void resize(int width, int height) throws IOException {
        image = Thumbnails.of(image).size(width, height).keepAspectRatio(false).asBufferedImage();
    }

    private void changeQuality(int quality) throws IOException {
        image = Thumbnails.of(image).scale(1).outputQuality((quality - 0.1) / 100).asBufferedImage();
    }

    private void cropImage(int x, int y, int width, int height){
        crop(img.clone(), img, x, y, width, height);
        image = img.getBufferedImage();
    }

    private void blur(int blurRadius){
        gaussianBlur(img.clone(), img, blurRadius);
        image = img.getBufferedImage();
    }
}
