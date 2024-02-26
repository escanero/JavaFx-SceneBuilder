package com.crud.crud;

import com.crud.crud.dao.AlumnoDao;
import com.crud.crud.model.Alumno;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController {

    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldApellidos;
    @FXML
    private TextField textFieldCorreo;
    @FXML
    private ComboBox<Alumno.Grado> comboBoxGrado;
    @FXML
    private TableView<Alumno> tableViewAlumnos;
    @FXML
    private TableColumn<Alumno, Integer> columnId;
    @FXML
    private TableColumn<Alumno, String> columnNombre;
    @FXML
    private TableColumn<Alumno, String> columnApellidos;
    @FXML
    private TableColumn<Alumno, String> columnCorreo;
    @FXML
    private TableColumn<Alumno, Alumno.Grado> columnGrado;

    private AlumnoDao alumnoDAO;

    @FXML
    public void initialize() {
        alumnoDAO = new AlumnoDao();

        // Inicializa el ComboBox con los grados.
        comboBoxGrado.setItems(FXCollections.observableArrayList(Alumno.Grado.values()));

        // Configura las columnas de la tabla.
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        columnCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        columnGrado.setCellValueFactory(new PropertyValueFactory<>("grado"));
        
        tableViewAlumnos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cargarDatosAlumno(newSelection);
            }
        });

        // Carga los datos de los alumnos en la tabla.
        loadAlumnosData();
    }
    
    @FXML
    private void cargarDatosAlumno(Alumno alumno) {
        textFieldNombre.setText(alumno.getNombre());
        textFieldApellidos.setText(alumno.getApellidos());
        textFieldCorreo.setText(alumno.getCorreo());
        comboBoxGrado.setValue(alumno.getGrado());
    }
    
    
    @FXML
    private void limpiarCampos() {
        textFieldNombre.setText("");
        textFieldApellidos.setText("");
        textFieldCorreo.setText("");
        comboBoxGrado.setValue(null); // Esto asume que no quieres seleccionar ningún grado por defecto
    }



    @FXML
    private void handleAdd() {
    	
    	  if (!validarInputs()) {
              return; // Detiene la ejecución si la validación falla
          }
        // Implementa la lógica para agregar un nuevo alumno utilizando alumnoDAO.
        Alumno nuevoAlumno = new Alumno();
        nuevoAlumno.setNombre(textFieldNombre.getText());
        nuevoAlumno.setApellidos(textFieldApellidos.getText());
        nuevoAlumno.setCorreo(textFieldCorreo.getText());
        nuevoAlumno.setGrado(comboBoxGrado.getValue());
        alumnoDAO.addAlumno(nuevoAlumno);
        limpiarCampos();
        loadAlumnosData();
    }
    
    @FXML
    private boolean validarInputs() {
        // Ejemplo de validación de presencia de valores
        if (textFieldNombre.getText().trim().isEmpty() || textFieldApellidos.getText().trim().isEmpty() ||
            textFieldCorreo.getText().trim().isEmpty() || comboBoxGrado.getValue() == null) {
            mostrarMensajeError("Todos los campos son obligatorios.");
            return false;
        }

        // Ejemplo de validación de formato para el correo
        if (!textFieldCorreo.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            mostrarMensajeError("El formato del correo electrónico no es válido.");
            return false;
        }
        
        // Validación de longitud máxima de 60 caracteres
        if (textFieldNombre.getText().length() > 60 || textFieldApellidos.getText().length() > 60 || 
            textFieldCorreo.getText().length() > 60) {
            mostrarMensajeError("No puedes ingresar más de 60 caracteres en los campos.");
            return false;
        }

        // Aquí puedes agregar más validaciones según sea necesario...

        return true; // Retorna true si todas las validaciones pasan
    }

    @FXML
    private void mostrarMensajeError(String mensaje) {
        // Implementación para mostrar mensajes de error, por ejemplo, usando un diálogo de alerta
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void handleUpdate() {
        // Implementa la lógica para actualizar un alumno seleccionado.
        Alumno alumnoSeleccionado = tableViewAlumnos.getSelectionModel().getSelectedItem();
        if (alumnoSeleccionado != null) {
        	  if (!validarInputs()) {
                  return; // Detiene la ejecución si la validación falla
              }
            alumnoSeleccionado.setNombre(textFieldNombre.getText());
            alumnoSeleccionado.setApellidos(textFieldApellidos.getText());
            alumnoSeleccionado.setCorreo(textFieldCorreo.getText());
            alumnoSeleccionado.setGrado(comboBoxGrado.getValue());
            alumnoDAO.updateAlumno(alumnoSeleccionado);
            limpiarCampos();
            loadAlumnosData();
        }
    }

    @FXML
    private void handleDelete() {
        // Implementa la lógica para eliminar un alumno seleccionado.
        Alumno alumnoSeleccionado = tableViewAlumnos.getSelectionModel().getSelectedItem();
        if (alumnoSeleccionado != null) {
            alumnoDAO.deleteAlumno(alumnoSeleccionado.getId());
            limpiarCampos();
            loadAlumnosData();
        }
    }

    private void loadAlumnosData() {
        ObservableList<Alumno> alumnosData = FXCollections.observableArrayList(alumnoDAO.getAllAlumnos());
        tableViewAlumnos.setItems(alumnosData);
    }

 
}