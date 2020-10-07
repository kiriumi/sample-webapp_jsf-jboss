package context;

import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.core.api.config.PropertyFileConfig;

@ApplicationScoped
public class EnvConfig implements PropertyFileConfig {

    @Override
    public String getPropertyFileName() {
        return "env.properties";
    }

    @Override
    public boolean isOptional() {
        return false;
    }

}
