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
public class SystemEnvContext implements Serializable {

    private final File rootDir;

    private final File bizDir;

    private final File otherRootDir;

    public SystemEnvContext() {

        // クラスローディング
        // 外部ディレクトリにあるプロパティファイを読込み
        ResourceBundle resourceBundle = ResourceBundle.getBundle("env");
        this.rootDir = new File(resourceBundle.getString("root.dir"));
        rootDir.mkdirs();

        log.debug("ルートディレクトリ：{}", rootDir.getAbsolutePath());

        // サブディレクトリ
        ResourceBundle subResourceBundle = ResourceBundle.getBundle("sub/biz");
        this.bizDir = new File(subResourceBundle.getString("sub.dir"));
        bizDir.mkdirs();
        log.debug("業務ディレクトリ：{}", bizDir.getAbsolutePath());

        // 別の外部ディレクトリ
        ResourceBundle resource2Bundle = ResourceBundle.getBundle("env2");
        this.otherRootDir = new File(resource2Bundle.getString("root.dir"));
        otherRootDir.mkdirs();
        log.debug("別のルートディレクトリ：{}", otherRootDir.getAbsolutePath());
    }

}
