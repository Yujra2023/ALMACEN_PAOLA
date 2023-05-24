package com.emergentes.dao;

import com.emergentes.modelo.almacen;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class almacenDAOimpl extends ConexionDB implements almacenDAO {

    @Override
    public void insert(almacen Almacen) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO productos(descripcion,cantidad,precio,categoria)values (?,?,?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, Almacen.getDescripcion());
            ps.setInt(2, Almacen.getCantidad());
            ps.setFloat(3, Almacen.getPrecio());
            ps.setString(4, Almacen.getCategoria());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(almacen Almacen) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE productos set descripcion=?,cantidad=?,precio=?,categoria=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, Almacen.getDescripcion());
            ps.setInt(2, Almacen.getCantidad());
            ps.setFloat(3, Almacen.getPrecio());
            ps.setString(4, Almacen.getCategoria());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            String sql = "DELETE FROM productos WHERE id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public almacen getById(int id) throws Exception {
        almacen alma = new almacen();
        try {
            this.conectar();
            String sql = "SELECT * FROM productos where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                alma.setId(rs.getInt("id"));
                alma.setDescripcion(rs.getString("descripcion"));
                alma.setCantidad(rs.getInt("cantidad"));
                alma.setPrecio(rs.getFloat("precio"));
                alma.setCategoria(rs.getString("categoria"));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return alma;

    }

    @Override
    public List<almacen> getAll() throws Exception {
        ArrayList<almacen> lista = new ArrayList<almacen>();
        try {

            this.conectar();
            String sql = "SELECT * FROM productos";
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                almacen alma = new almacen();

                alma.setId(rs.getInt("id"));
                alma.setDescripcion(rs.getString("descripcion"));
                alma.setCantidad(rs.getInt("cantidad"));
                alma.setPrecio(rs.getFloat("precio"));
                alma.setCategoria(rs.getString("categoria"));

                lista.add(alma);
            }

            rs.close();
            ps.close();
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

}
