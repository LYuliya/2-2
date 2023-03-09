package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.model.Car;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.addUser(new User("Anna", "Polyakova", "ap@mail.ru", new Car("UAZ", 8)));
      userService.addUser(new User("Anton", "Zatupok", "pb@mail.ru", new Car("Zaporogec", 0)));
      userService.addUser(new User("Igor", "Glazkov", "nyasha1967@mail.ru", new Car("Toyota", 4)));
      userService.addUser(new User("Svetochka", "Moskolkova", "svemo@mail.ru", new Car("Nissan", 7)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("User`s model of car = " + user.getCar().getModel());
         System.out.println("User`s series of car = " + user.getCar().getSeries());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println();
      }

      System.out.println(userService.findUser("Nissan", 7));
      context.close();
   }
}
