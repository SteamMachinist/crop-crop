package mts.teta.resizer;

import mts.teta.resizer.imageprocessor.BadAttributesException;
import mts.teta.resizer.imageprocessor.ImageProcessor;
import picocli.CommandLine;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "convert", mixinStandardHelpOptions = true, sortOptions = false, version = "https://gitlab.com/SteamMachinist/MTS-TETA-task",
        description = "Useful image processor", synopsisHeading = "", customSynopsis = {
        "Available formats: jpeg png",
        "Usage: convert input-file [options ...] output-file",
        "Options Settings:",
        "--resize width height       resize the image",
        "--quality value             JPEG/PNG compression level between 1 and 100",
        "--crop width height x y     cut out one rectangular area of the image",
        "--blur {radius}             reduce image noise detail levels",
        "--format outputFormat       the image format type (png or jpeg)"})
public class ResizerApp extends ConsoleAttributes implements Callable<Integer> {
    public static void main(String... args) {
        int exitCode = runConsole(args);
        System.exit(exitCode);
    }

    protected static int runConsole(String[] args) {
        return new CommandLine(new ResizerApp()).execute(args);
    }

    @Override
    public Integer call() throws IOException, BadAttributesException {
        validate();
        ImageProcessor imageProcessor = new ImageProcessor();
        imageProcessor.processImage(ImageIO.read(inputFile), this);
        return 0;
    }
}
