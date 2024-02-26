module com.crud.crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    // Abre paquetes específicos a módulos específicos de JavaFX
    opens com.crud.crud to javafx.fxml;
    opens com.crud.crud.model to javafx.base, javafx.fxml;

    // Exporta tu paquete principal para que sea visible fuera del módulo
    exports com.crud.crud;
}
