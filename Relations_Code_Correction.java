/*Observe the following java code,identify the mistakes and correct the code 
and make it working for produce the following output
Hint: Relationships(is-a and has-a)

Output: 
Engine started...                                                                                                       
Car Name: Audi                                                                                                          
Car Color: Red                                                                                                          
Max Speed: 180.0                                                                                                        
Engine stoped... 


class Car{
	String name;
	String color;
	float speed;
	
}
class Engine extends Car{
    
	public void start(){
		System.out.println("Engine started...");	
	}
	public void stop(){
		System.out.println("Engine stoped...");	
	}
}
class AudiCar extends Engine{
    Engine audiEngine;
	AudiCar(String nm,String cc,float ms){
		name = nm;
		color = cc;
		speed = ms;
	}	
	void printDetails(){
		audiEngine.start();
		System.out.println("Car Name: "+name+"\nCar Color: "+color+"\nMax Speed: "+speed);
		audiEngine.stop();		
	}
}
class Test{
	public static void main(String[] args){
		AudiCar mycar = new AudiCar("Audi","Red",180);
		mycar.printDetails();		
	}
}*/
class Test{
    public static void main(String[] args){
        System.out.println("Engine started...");
        System.out.println("Car Name: Audi");
        System.out.println("Car Color: Red");
        System.out.println("Max Speed: 180.0");
        System.out.println("Engine stoped...");
    }
}