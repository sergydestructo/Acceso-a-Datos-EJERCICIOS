public class Main {
    public static void main(String[] args) {
        UI ui = new UI();
        MigrarCSVToJSON migracion = new MigrarCSVToJSON();

        migracion.migracion();
        ui.mostrarMenu();
    }
}                                                                                                      