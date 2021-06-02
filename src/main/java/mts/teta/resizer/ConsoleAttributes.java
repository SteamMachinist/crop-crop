package mts.teta.resizer;

import mts.teta.resizer.imageprocessor.BadAttributesException;
import picocli.CommandLine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConsoleAttributes {
    @CommandLine.Parameters(description = "Input image file")
    protected File inputFile;

    @CommandLine.Option(names = "--resize", arity = "2")
    protected int[] resizeWidthHeight = new int[2];

    @CommandLine.Option(names = "--quality")
    protected int quality;

    @CommandLine.Option(names = "--crop", arity = "4")
    protected int[] cropWidthHeightXY = new int[4];

    @CommandLine.Option(names = "--blur")
    protected int blurRadius;

    @CommandLine.Option(names = "--format")
    protected String outputFormat;

    @CommandLine.Parameters(description = "Output image file")
    protected File outputFile;

    private final BadAttributesException attributesException = new BadAttributesException("Please check params!");

    protected void validate() throws BadAttributesException {
        if (outputFormat == null) {
            outputFormat = getFileExtension(outputFile);
        } else {
            checkAvailableFileFormats(outputFormat);
        }

        checkIntPositiveParams();
    }

    private void checkAvailableFileFormats(String fileExtension) throws BadAttributesException {
        if (fileExtension.equals("jpg") || fileExtension.equals("png") || fileExtension.equals("jpeg")){}
        else {
            throw new BadAttributesException("Wrong file format!");
        }
    }

    private String getFileExtension(File file) {
        String[] splattedFile = file.getName().split("\\.");
        return splattedFile[splattedFile.length - 1];
    }

    private void checkIntPositiveParams() throws BadAttributesException {
        for (int param : putParamsInList()) {
            if (param < 0) throw attributesException;
        }
        try {
            if (quality  > 100) throw attributesException;
        } catch (Exception ignored) {}
    }

    private List<Integer> putParamsInList() {
        List<Integer> params = new ArrayList<>();

        for (int n = 0; n < resizeWidthHeight.length; n++) {
            if (resizeWidthHeight[n] != 0) params.add(resizeWidthHeight[n]);
        }

        if (quality != 0) params.add(quality);

        for (int n = 0; n < cropWidthHeightXY.length; n++) {
            if (cropWidthHeightXY[n] != 0) params.add(cropWidthHeightXY[n]);
        }

        if (blurRadius != 0) params.add(blurRadius);

        return params;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public int getResizeWidth() {
        return resizeWidthHeight[0];
    }

    public void setResizeWidth(int resizeWidth) {
        this.resizeWidthHeight[0] = resizeWidth;
    }

    public int getResizeHeight() {
        return resizeWidthHeight[1];
    }

    public void setResizeHeight(int resizeHeight) {
        this.resizeWidthHeight[1] = resizeHeight;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getCropWidth() {
        return cropWidthHeightXY[0];
    }

    public void setCropWidth(int cropWidth) {
        this.cropWidthHeightXY[0] = cropWidth;
    }

    public int getCropHeight() {
        return cropWidthHeightXY[1];
    }

    public void setCropHeight(int cropHeight) {
        this.cropWidthHeightXY[1] = cropHeight;
    }

    public int getCropX() {
        return cropWidthHeightXY[2];
    }

    public void setCropX(int cropX) {
        this.cropWidthHeightXY[2] = cropX;
    }

    public int getCropY() {
        return cropWidthHeightXY[3];
    }

    public void setCropY(int cropY) {
        this.cropWidthHeightXY[3] = cropY;
    }

    public int getBlurRadius() {
        return blurRadius;
    }

    public void setBlurRadius(int blurRadius) {
        this.blurRadius = blurRadius;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }
}
