package interceptor;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@Logging
public class LogInterceptor {

    private final Logger LOGGER = LogManager.getLogger(LogInterceptor.class);

    @AroundInvoke
    public Object around(final InvocationContext context) throws Exception {

        String methodName = context.getMethod().getName();
        LOGGER.debug("開始：{}", methodName);

        List<Object> params = Arrays.asList(context.getParameters());
        params.stream().forEach(param -> LOGGER.debug("{}", param.toString()));

        Object result = context.proceed();

        LOGGER.debug("終了：{}", methodName);

        return result;
    }
}
