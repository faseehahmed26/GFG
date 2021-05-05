/*
create a Calculator application with base class as Calculator,Derived classes 
as SchoolCalculator and ScientificCalculator 
the following functions should be implemented 

1 SchoolCalculator:
	-perfectNumber()
	-armstrongNumber()
2 ScientificCalculator:
	-bmiCalculation()
	-fahrenheit_to_celsius()
Note: Intialization of attributes should be in base class


*/
import java.util.Scanner;
import java.lang.Math;
class Calculator{
int n,sum,i;
int a,suma,t,r;
double h,w,bmi;
double f,c;
}
class SchoolCalculator extends Calculator{
    public void perfectNumber(){
        Scanner in=new Scanner(System.in);
        System.out.println("Enter number to check perfect number");
        int n=in.nextInt();
        int sum=0;
        for(int i=1;i<=n/2;i++){
            if(n%i==0){
                sum=sum+i;
            }
        }
        if(sum==n){
            System.out.println("Perfect Number");
        }
        else{
            System.out.println("Not Perfect Number");
        }
    }
    public void amstrongNumber(){
        Scanner in=new Scanner(System.in);
        System.out.println("Enter a number to check amstrong number");
         a=in.nextInt();
         suma=0;
         t=a;
        while(t>0){
            r=t%10;
            suma=suma+r*r*r;
            t=t/10;
        }
        if(sum==n){
            System.out.println("Amstrong Number");
        }
        else{
            System.out.println("Not Amstrong Number");
        }
    }
}
class ScientificCalculator extends Calculator{
    public void bmiCalculator(){
        Scanner in=new Scanner(System.in);
        System.out.println("Enter height and weight");
        h=in.nextDouble();
        w=in.nextDouble();
        h=Math.pow(h,2);
        bmi=w/h;
        System.out.println("BMI INDEX :"+bmi);
    }
    public void fahrenheit_to_celsius(){
        Scanner in=new Scanner(System.in);
        System.out.println("Enter temp in fahrenheit");
        f=in.nextDouble();
        c=((f-32)*5)/9;
        System.out.println("Celcius Temp:"+c);
        
    } 
}
class Test{
    public static void main(String[] args){
        SchoolCalculator sc=new SchoolCalculator();
        sc.amstrongNumber();
        sc.perfectNumber();
        ScientificCalculator sci=new ScientificCalculator();
        sci.bmiCalculator();
        sci.fahrenheit_to_celsius();
    }
}