import proiectFinal.service.AuthService;
import proiectFinal.service.AuthServiceImpl;
import proiectFinal.service.UserInteractionService;
import proiectFinal.service.UserInteractionServiceImpl;

public class Main {
    public static void main(String[] args) {
        AuthService authService = new AuthServiceImpl();
        authService.authenticate();
        // secure here
        UserInteractionService userInteractionService = new UserInteractionServiceImpl();
        userInteractionService.initInteraction();
    }


}
