package context;

import java.io.File;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.deltaspike.core.api.config.ConfigProperty;

import com.byteslounge.cdi.annotation.Property;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Getter
@Slf4j
public class EnvContext implements Serializable {

    @Property(resourceBundleBaseName = "env", value = "base.dir")
    private String baseDirPath;

    @Inject
    @ConfigProperty(name = "base.dir")
    private String baseDirPathByDeltaspike;

    private File baseDir;

    private File miscDir;

    private File uploadFileDir;

    @Property(resourceBundleBaseName = "sub/biz", value = "biz")
    private String biz;

    @Property(resourceBundleBaseName = "env2", value = "other")
    private String other;

    @PostConstruct
    public void init() {

        this.baseDir = new File(baseDirPath);
        this.miscDir = initDir(baseDir, "misc");
        this.uploadFileDir = initDir(baseDir, "file/upload");

        log.debug(biz);
        log.debug(other);
        log.debug(baseDirPathByDeltaspike);
    }

    private File initDir(final File parentDir, final String dirname) {

        File dir = new File(parentDir, dirname);
        dir.mkdirs();
        log.debug("ディレクトリパス：{}", dir.getAbsolutePath());

        return dir;
    }

}
