package chapter9;

public class Sonata extends Car {


	@Override
	public void drive() {
		System.out.println("Sonata 달립니다.");
		
	}


	@Override
	public void turnoff() {
		System.out.println("Sonata 시동을 끕니다.");
		
	}
	@Override
	public void start() {
		System.out.println("Sonata 달립니다.");
		
	}


	@Override
	public void stop() {
		System.out.println("Sonata 멈춥니다.");
		
	}
	

}
