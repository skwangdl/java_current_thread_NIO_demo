package jdkproxy;

/**
 * �������࣬��Ŀ����target
 * 
 * @author typ
 * 
 */
public class AService implements Service {
	/*
	 * (non-Javadoc)
	 * 
	 * @see jdkproxy.Service#add()
	 */
	public void add() {
		System.out.println("AService add>>>>>>>>>>>>>>>>>>");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jdkproxy.Service#update()
	 */
	public void update() {
		System.out.println("AService update>>>>>>>>>>>>>>>");
	}
}