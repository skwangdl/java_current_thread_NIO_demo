package jdkproxy;

import java.lang.reflect.Proxy;

/**
 * @author typ
 *
 */
public class Test {
	public static void main(String[] args) {
		Service aService = new AService();
		MyInvocationHandler handler = new MyInvocationHandler(aService);
		// ProxyΪInvocationHandlerʵ���ද̬����һ������ĳһ�ӿڵĴ���ʵ��
		Service aServiceProxy = (Service) Proxy.newProxyInstance(aService
				.getClass().getClassLoader(), aService.getClass()
				.getInterfaces(), handler);
		// �ɶ�̬���ɵĴ��������aServiceProxy ����ִ�г�������aServiceProxy ����Service�ӿ�
		aServiceProxy.add();
		System.out.println();
		aServiceProxy.update();
		// �����Ƕ�B�Ĵ���
		// Service bService = new BService();
		// MyInvocationHandler handler = new MyInvocationHandler(bService);
		// Service bServiceProxy = (Service) Proxy.newProxyInstance(bService
		// .getClass().getClassLoader(), bService.getClass()
		// .getInterfaces(), handler);
		// bServiceProxy.add();
		// System.out.println();
		// bServiceProxy.update();
	}
}
