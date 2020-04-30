package context;

import java.io.File;
import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;

import lombok.Value;

@ApplicationScoped
@Value
public class SystemDirContext {

    private File rootDir;

    private File bizDir;

    private File otherRootDir;

    public SystemDirContext() {

        // クラスローディング
        // 外部ディレクトリにあるプロパティファイを読込み
        ResourceBundle resourceBundle = ResourceBundle.getBundle("env");
        this.rootDir = new File(resourceBundle.getString("root.dir"));
        rootDir.mkdirs();

        // サブディレクトリ
        ResourceBundle subResourceBundle = ResourceBundle.getBundle("sub/biz");
        this.bizDir = new File(subResourceBundle.getString("sub.dir"));

        // 別の外部ディレクトリ
        ResourceBundle resource2Bundle = ResourceBundle.getBundle("env2");
        this.otherRootDir = new File(resource2Bundle.getString("root.dir"));
    }
}
