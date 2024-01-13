import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VideofileIntegrityCheck {

    public static void main(String[] args) {
        String videoFilePath = "C:\\Users\\Avnish\\Desktop\\ffmpeg\\a.mp4";  // Replace with the actual path to your video file

        try {
            // Build the FFmpeg command
           String[] command = {"ffmpeg", "-v", "error", "-i", videoFilePath, "-f", "null", "-"};
          // String command = "ffmpeg -v error -i C:\\Users\\Avnish\\Desktop\\ffmpeg\\a.mp4 -f null -";

            // Create a process builder
          //  ProcessBuilder processBuilder = new ProcessBuilder(command.split("//s+"));
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            // Redirect error stream to the input stream
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Read the output of the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                // Process the output as needed
                System.out.println(line);
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();

            // Check the exit code
            if (exitCode == 0) {
                System.out.println("Video file is valid.");
            } else {
                System.out.println("Video file may be corrupt or have issues. Exit code: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
