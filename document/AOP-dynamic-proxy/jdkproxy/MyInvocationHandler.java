package jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author typ
 *
 */
public class MyInvocationHandler implements InvocationHandler {
	private Object target;

	MyInvocationHandler() {
		super();
	}

	MyInvocationHandler(Object target) {
		super();
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// ����ִ��ǰ�����߼�
		System.out.println("before-----------------------------");
		// ����ִ��
		Object result = method.invoke(target, args);
		// ����ִ�к�����߼�
		System.out.println("after------------------------------");
		return result;
	}

}
