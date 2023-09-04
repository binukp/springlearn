package com.binu.spinglearn;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyTests {

    public  static  void main (String args[]){
        MyTests myTests = new MyTests();
        int[] nums = {1,2,3,6,7,6};
        int[] duplicatesRemovedArray = myTests.removeDuplicates(nums);
       // Arrays.stream(duplicatesRemovedArray).forEach(System.out::println);
        //employeeStreamTests();
        //intStreamTest();

        Map<Integer,Integer> hs = new HashMap<>();
        hs.put(4,9);
        hs.put(9,5);
        hs.put(6,1);
        hs.put(1,4);

        int StartNode = 6;
        int finalNode = 0;
        while(hs.get(StartNode) !=null){
            finalNode = hs.get(StartNode) ;
            StartNode = finalNode;
        }

        System.out.println(finalNode);

        /*for (Map.Entry<Integer, Integer> entry : hs.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("Key=" + key + ", Value=" + value);
        }*/

    }

         //Remove Duplicates from Array
        public int[] removeDuplicates(int[] nums) {
            int[] duplicatesRemovedArray = Arrays.stream(nums).distinct().toArray();
            Arrays.stream(nums).distinct().toArray();
            return  duplicatesRemovedArray;
        }

      public static  void intStreamTest(){
          List<Integer> intList =  Arrays.asList(9,10, 2, 3, 4,5,2);
          intList.stream().mapToInt(Integer::intValue).sum();
          IntStream.range(1,10).forEach(System.out::println);
          System.out.println("filter");
          intList.stream().filter(n->n%2==0).sorted().distinct().forEach(System.out::println);
      }
      public static void  employeeStreamTests() {
       employees.stream().forEach(employee -> System.out.println(employee.firstName));
       System.out.println("more than 5000 salary employees");
       List<Employee> employeeList =  employees.stream().filter(employee -> employee.getSalary()>5000).collect(Collectors.toList());
       System.out.println(employeeList);
       Optional<Employee> max =  employeeList.stream().max(Comparator.comparing(Employee::getSalary));
       System.out.println("max salary ="+max.get());
       System.out.println("sorted");
       employees.stream().sorted(Comparator.comparing(Employee::getFirstName).reversed()).collect(Collectors.toList()).forEach(employee -> System.out.println(employee.getFirstName()));
       System.out.println("total - " + employees.stream().mapToDouble(Employee::getSalary).sum());
       double salaryWithParallelStream = employees.parallelStream().map(Employee::getSalary).mapToDouble(i -> i).average().getAsDouble();
       System.out.println("avg - " + salaryWithParallelStream);
       double salaryWithParallelStream2 =  employees.parallelStream().map(Employee::getSalary).reduce(0.0,Double::sum);
       System.out.println("total - " + salaryWithParallelStream2);
       System.out.println( "max salary:" + employees.parallelStream().max(Comparator.comparing(Employee::getSalary)));
       System.out.println( "distinct projects" + employees.parallelStream().flatMap(employee -> employee.getProjects().stream()).distinct().collect(Collectors.toList()));


       employeeList.stream().max(Comparator.comparing(Employee::getSalary));

       employees.parallelStream().mapToDouble(employee-> employee.getSalary()).average();
       employees.parallelStream().map(employee -> employee.firstName).distinct().forEach(System.out::println);

       employees.stream().sorted(Comparator.comparing(Employee::getLastName).reversed()).forEach(employee -> System.out.println(employee.getFirstName()));

        employees.stream().sorted(Comparator.comparing(Employee::getLastName).reversed().thenComparing(Employee::getFirstName)).forEach(employee -> System.out.println(employee.getFirstName()));



        Map<String, Long> result =
                  employees.stream().map(employee-> employee.firstName)
                 .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
       System.out.println("group by - " + result);

          Map<String, Double> sum = employees.stream().collect(
                  Collectors.groupingBy(Employee::getFirstName, Collectors.summingDouble(Employee::getSalary)));

          System.out.println("group & sum - " + sum);

          Map<String, Map<String, List<Employee>>> sum2 = employees.stream().collect(Collectors.groupingBy(Employee::getFirstName, Collectors.groupingBy(Employee::getLastName)));

          System.out.println("group & sum multi fields - " + sum2);

          List<Employee> nikihillist = employees.stream().collect(Collectors.groupingBy(Employee::getFirstName)).get("Nikhil");
          System.out.println("get Nikil - " + nikihillist.get(0));

          HashMap<String, Employee> teeingresult = employeeList.stream().collect(
                  Collectors.teeing(
                          Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
                          Collectors.minBy(Comparator.comparing(Employee::getSalary)),
                          (e1, e2) -> {
                              HashMap<String, Employee> map = new HashMap();
                              map.put("MAX", e1.get());
                              map.put("MIN", e2.get());
                              return map;
                          }
                  ));
          System.out.println("teeing - " + teeingresult);
          Collections.sort(employeeList,Comparator.comparing(Employee::getSalary));

          Stream.of("India", "Australia", "Newzealand", "", "England", "Srilanka")
                  .dropWhile(o->!o.isEmpty())
                  .forEach(System.out::print);

         Stream.of(1, 2, 3, 5)
                  .max(Comparator.reverseOrder()).orElseThrow();

      }



    static List<Employee> employees = new ArrayList<>();
    {
        employees.add(
                new Employee("Shabbir", "Dawoodi", 5000.0, List.of("Project 1","Project 2"))
        );

        employees.add(
                new Employee("Nikhil", "Gupta", 6000.0, List.of("Project 1","Project 3"))
        );

        employees.add(
                new Employee("Shivam", "Kumar", 5500.0, List.of("Project 3","Project 4"))
        );

        employees.add(
                new Employee("Nikhil", "Kumar", 5500.0, List.of("Project 3","Project 4"))
        );

    }

    public class Employee {

        private String firstName;
        private String lastName;
        private Double salary;
        private List<String> projects;

        @Override
        public String toString() {
            return "Employee{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", salary=" + salary +
                    ", projects=" + projects +
                    '}';
        }

        public Employee(String firstName, String lastName, Double salary, List<String> projects) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.salary = salary;
            this.projects = projects;
        }

        public Employee() {
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Double getSalary() {
            return salary;
        }

        public void setSalary(Double salary) {
            this.salary = salary;
        }

        public List<String> getProjects() {
            return projects;
        }

        public void setProjects(List<String> projects) {
            this.projects = projects;
        }

        public void testme(int a){

        }


    }

}
