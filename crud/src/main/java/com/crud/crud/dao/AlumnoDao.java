package com.crud.crud.dao;



import com.crud.crud.model.Alumno;
import com.crud.crud.connection.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDao {

    private Connection connection;

    public AlumnoDao() {
        connection = Conexion.getConexion();
    }

    public void addAlumno(Alumno alumno) {
        try {
            String query = "INSERT INTO alumno (nombre, apellidos, correo, grado) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, alumno.getNombre());
            preparedStatement.setString(2, alumno.getApellidos());
            preparedStatement.setString(3, alumno.getCorreo());
            preparedStatement.setString(4, alumno.getGrado().toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Alumno> getAllAlumnos() {
        List<Alumno> alumnos = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM alumno");
            while (resultSet.next()) {
                Alumno alumno = new Alumno();
                alumno.setId(resultSet.getInt("id"));
                alumno.setNombre(resultSet.getString("nombre"));
                alumno.setApellidos(resultSet.getString("apellidos"));
                alumno.setCorreo(resultSet.getString("correo"));
                alumno.setGrado(Alumno.Grado.valueOf(resultSet.getString("grado")));
                alumnos.add(alumno);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }

    public Alumno getAlumnoById(int alumnoId) {
        Alumno alumno = new Alumno();
        try {
            String query = "SELECT * FROM alumno WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, alumnoId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            alumno.setId(resultSet.getInt("id"));
            alumno.setNombre(resultSet.getString("nombre"));
            alumno.setApellidos(resultSet.getString("apellidos"));
            alumno.setCorreo(resultSet.getString("correo"));
            alumno.setGrado(Alumno.Grado.valueOf(resultSet.getString("grado")));
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumno;
    }

    public void updateAlumno(Alumno alumno) {
        try {
            String query = "UPDATE alumno SET nombre = ?, apellidos = ?, correo = ?, grado = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, alumno.getNombre());
            preparedStatement.setString(2, alumno.getApellidos());
            preparedStatement.setString(3, alumno.getCorreo());
            preparedStatement.setString(4, alumno.getGrado().toString());
            preparedStatement.setInt(5, alumno.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAlumno(int alumnoId) {
        try {
            String query = "DELETE FROM alumno WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, alumnoId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
