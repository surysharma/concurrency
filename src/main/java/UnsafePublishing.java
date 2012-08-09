import java.util.ArrayList;
import java.util.List;

class EmployeeService {
    private List<String> salaryTypes = new ArrayList<String>();

    public List<String> getSalaryTypes() {
        Thread.yield();
        return salaryTypes;
    }

    public void setSalaryTypes(String salaryType) {
        Thread.yield();
        salaryTypes.add(salaryType);
    }
}

public class UnsafePublishing {

    final static EmployeeService employeeService = new EmployeeService();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    employeeService.setSalaryTypes(Thread.currentThread().getName() + ":" + i);
                }

            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                List<String> salaryTypes = employeeService.getSalaryTypes();
                for (int i = 0; i < salaryTypes.size(); i++) {
                    salaryTypes.add(i, "contaminated" + salaryTypes.get(i));
                }

            }
        });
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                System.out.println("Resultant list:" + employeeService.getSalaryTypes());

            }
        });


        t1.start();
        t2.start();
        t3.start();

    }

}
