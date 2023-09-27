package exercise;

import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String file1, String file2, String file3) throws ExecutionException, InterruptedException, IOException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "");
        if (!file1.contains("/")) {
            URL url = ClassLoader.getSystemResource(file1);
            if (url != null) {
                file1 = url.getFile();
            } else {
                System.out.println("NoSuchFileException");
                return future;
            }
        }
        if (!file2.contains("/")) {
            URL url = ClassLoader.getSystemResource(file2);
            if (url != null) {
                file2 = url.getFile();
            } else {
                System.out.println("NoSuchFileException");
                return future;
            }
        }


        String finalFile1 = file1;
        String finalFile2 = file2;
        if (!Files.exists(Path.of(file1)) || !Files.exists(Path.of(file2))) {
            throw new NoSuchFileException("");
        }

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Path path = Paths.get(finalFile1);
                return Files.readString(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Path path = Paths.get(finalFile2);
                return Files.readString(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2);
        String result1 = future1.get();
        String result2 = future2.get();
        String file3Text = result1 + result2;
        System.out.println(file3Text);

        Files.writeString(Path.of(file3), file3Text);

        return future1;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = App.unionFiles("file1.txt", "file2.txt", "dest.txt");
        System.out.println(result);
        // END
    }
}

