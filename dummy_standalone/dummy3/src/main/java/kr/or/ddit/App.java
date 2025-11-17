package kr.or.ddit;
import net.datafaker.*;
/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println(new Faker().name().fullName());
    }
}
