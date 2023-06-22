package org.kosta.jeongdab.tou.controller;
/*
 *  FrontControllerServlet(공통정책담당) 과  Controller ( 개별컨트롤러로직담당) 를
 *  연결하는 역할 
 *  Reflection API를 통해 Front 에서 전달하는 command 에 따라 런타임시에 
 *  동적으로 컨트롤러 객체를 생성해 Front 에 리턴한다 
 *  
 *  HandlerMapping 은 시스템 상에 단 한번 생성해서 공유해 사용하면 되므로 
 *  Singleton Design Pattern 을 사용한다 
 */
public class HandlerMapping {
	private static HandlerMapping instance=new HandlerMapping();
	private HandlerMapping() {}
	public static HandlerMapping getInstance() {
		return instance;
	}
	public Controller create(String command) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		//런타임시에 command 전달받아 컨트롤러 객체 생성해 반환 => 연결자 역할 
		/*
		String packageInfo=this.getClass().getPackage().getName();
		System.out.println("packageInfo:"+packageInfo);
		System.out.println("command:"+command);
		System.out.println(packageInfo+"."+command+"Controller");
		*/
		StringBuilder classInfo=new StringBuilder(this.getClass().getPackage().getName());
		classInfo.append(".").append(command).append("Controller");
		return (Controller) Class.forName(classInfo.toString()).newInstance();
	}
}
















