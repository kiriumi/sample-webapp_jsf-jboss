package security;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

import javax.inject.Inject;

import org.primefaces.virusscan.VirusException;
import org.primefaces.virusscan.VirusScanner;

import context.EnvContext;

public class CustomVirusScanner implements VirusScanner {

    @Inject
    private EnvContext envContext;

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void performVirusScan(InputStream inputStream) throws VirusException {

        int exitValue = 0;

//        try {
            // ウィルススキャン用ディレクトリにファイルを作成
            UUID uuid = UUID.randomUUID();
            String filename = uuid.toString() + ".upload";
            File uploadedFile = new File("C:/Users/kengo/git/sample-webapp_jsf-jboss/file/upload", filename);
//            Files.copy(inputStream, uploadedFile.toPath());

            // ウィルススキャンを実施
            String[] commands = { "cmd", "/c", "notepad.exe" }; // 本来はウィルススキャンソフトを指定
            Runtime runtime = Runtime.getRuntime();
            //            Process proc = runtime.exec(commands);
            //            exitValue = proc.exitValue();
            exitValue = 0;

//        } catch (IOException e) {
//            throw new RuntimeException();
//        }

        if (exitValue == 1) {
            throw new VirusException();
        }

    }

}
