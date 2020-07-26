package context;

import java.io.File;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Getter
@Slf4j
public class EnvContext implements Serializable {

    private final File baseDir;

    private final File miscDir;

    private final File uploadFileDir;

    public EnvContext() {

        this.baseDir = new File(ResourceBundle.getBundle("env").getString("base.dir"));
        this.miscDir = initDir(baseDir, "misc");
        this.uploadFileDir = initDir(baseDir, "file/upload");

        String biz = ResourceBundle.getBundle("sub/biz").getString("biz");
        log.debug(biz);

        String other = ResourceBundle.getBundle("env2").getString("other");
        log.debug(other);
    }

    private File initDir(final File parentDir, final String dirname) {

        File dir = new File(parentDir, dirname);
        dir.mkdirs();
        log.debug("ディレクトリパス：{}", dir.getAbsolutePath());

        return dir;
    }

}
