package interceptor;

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

	Logger logger = LogManager.getLogger();

	@AroundInvoke
	public Object around(final InvocationContext context) throws Exception {

		String methodName = context.getMethod().getName();

		logger.debug("インターセプタ―開始：{}", methodName);

		Object result = context.proceed();

		logger.debug("インターセプタ―終了：{}", methodName);

		return result;

	}
}
