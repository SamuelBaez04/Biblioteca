import controller.Controlador;
import vista.ConsoleView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main{
    public static void main(String[] args) {
        Controlador controlador = new Controlador();

        ConsoleView vista = new ConsoleView(controlador);

        vista.init();

    }
}

