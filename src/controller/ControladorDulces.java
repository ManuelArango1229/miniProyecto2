package controller;

import model.Datos;
import model.Dulce;
import view.InterfazGrafica;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ControladorDulces implements ActionListener {

    public ControladorDulces(Datos almacenamientoDatos, InterfazGrafica interfaz) {
        this.data = almacenamientoDatos;
        this.view = interfaz;
        view.buttonCrearDulce.addActionListener(this);
        view.ButtonactualizarM.addActionListener(this);
        view.buttonComprobarA.addActionListener(this);
        view.buttonActualizarA.addActionListener(this);
        view.buttonEliminar.addActionListener(this);
        view.buttonAllDulces.addActionListener(this);
        view.exitAll.addActionListener(this);
    }

    public void iniciar() {
        view.iniciarComponentes();
        view.ventana.setVisible(true);
    }

    public void crearDulce(String name, char tipo) {
        Dulce nuevo = new Dulce(tipo, name);
        data.agregarAllDulces(nuevo);
        data.agregarDulces(nuevo);
    }

    public String mostrarDulces() {
        String texto = "";
        for (Dulce e : data.obteberDulces()) {
            texto += "-- \t" + e.getName() + "\t";
            texto += e.getTipo() + "\n";
        }
        return texto;
    }

    public String mostrarAllDulces() {
        String texto = "";
        for (Dulce e : data.obteberAllDulces()) {
            texto += "-- \t" + e.getName() + "\t";
            texto += e.getTipo() + "\n";
        }
        return texto;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == view.buttonCrearDulce) {
            char tipo = 'X';
            if (view.dulce.isSelected()) {
                tipo = 'D';
            } else if (view.acido.isSelected()) {
                tipo = 'A';
            } else {
                tipo = 'S';
            }
            crearDulce(view.campoNombre.getText(), tipo);
            JOptionPane.showMessageDialog(null, "Dulce Creado");
            view.campoNombre.setText("");
            view.categoriaDulce.clearSelection();
            view.ButtonactualizarM.doClick();
        }

        if (e.getSource() == view.ButtonactualizarM) {
            view.areaMostrar.setText(mostrarDulces());
        }
        if (e.getSource() == view.buttonComprobarA) {
            boolean band = false;
            for (int i = 0; i < data.obteberDulces().size(); i++) {
                if (data.obteberDulces().get(i).getName().equals(view.campoNombreActualizar.getText())) {
                    band = true;
                    index = i;
                    break;
                }
            }
            if (band) {
                view.campoNombreActualizar.setText("");
                view.labelNuevosDatos.setVisible(true);
                view.buttonComprobarA.setVisible(false);
                view.buttonActualizarA.setVisible(true);
                view.acidoA.setVisible(true);
                view.sinAzucarA.setVisible(true);
                view.dulceA.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Dulce No Encontrado");
                view.campoNombreActualizar.setText("");
            }
        }
        if (e.getSource() == view.buttonActualizarA) {
            char tipo = 'X';
            if (view.dulceA.isSelected()) {
                tipo = 'D';
            } else if (view.acidoA.isSelected()) {
                tipo = 'A';
            } else {
                tipo = 'S';
            }
            data.obteberDulces().get(index).setName(view.campoNombreActualizar.getText());
            data.obteberDulces().get(index).setTipo(tipo);
            JOptionPane.showMessageDialog(null, "Dulce Actualizado");
            view.campoNombreActualizar.setText("");
            view.labelNuevosDatos.setVisible(false);
            view.acidoA.setVisible(false);
            view.sinAzucarA.setVisible(false);
            view.dulceA.setVisible(false);
            view.buttonActualizarA.setVisible(false);
            view.buttonComprobarA.setVisible(true);
            view.grupoA.clearSelection();
            view.ButtonactualizarM.doClick();
        }
        if (e.getSource() == view.buttonEliminar) {
            boolean band = false;
            for (int i = 0; i < data.obteberDulces().size(); i++) {
                if (data.obteberDulces().get(i).getName().equals(view.campoNombreEliminar.getText())) {
                    band = true;
                    index = i;
                    break;
                }
            }
            if (band) {
                data.obteberDulces().remove(index);
                JOptionPane.showMessageDialog(null, "Dulce Eliminado");
                view.campoNombreEliminar.setText("");
                view.ButtonactualizarM.doClick();
            } else {
                JOptionPane.showMessageDialog(null, "No Se Encontro El Dulce");
                view.campoNombreEliminar.setText("");
            }
        }
        if (e.getSource() == view.buttonAllDulces) {
            view.title.setText("RESUMEN TODOS LOS DULCES CREADOS");
            view.title.setBounds(70, 30, 260, 30);
            view.acido.setVisible(false);
            view.sinAzucar.setVisible(false);
            view.dulce.setVisible(false);
            view.labelTipoDulce.setVisible(false);
            view.campoNombre.setVisible(false);
            view.nombreDulce.setVisible(false);
            view.buttonAllDulces.setVisible(false);
            view.buttonCrearDulce.setVisible(false);
            view.areaMostrarAll.setBounds(50, 70, 320, 190);
            view.areaMostrarAll.setBackground(new Color(196, 223, 223));
            view.areaMostrarAll.setEditable(false);
            view.areaMostrarAll.setVisible(true);
            view.areaMostrarAll.setText(mostrarAllDulces());
            view.exitAll.setBounds(150, 260, 80, 30);
            view.exitAll.setVisible(true);
            view.laminaAgregar.add(view.exitAll);
            view.laminaAgregar.add(view.areaMostrarAll);
        }
        if (e.getSource() == view.exitAll) {
            view.exitAll.setVisible(false);
            view.areaMostrarAll.setVisible(false);
            view.title.setText("AGREGAR DULCES");
            view.title.setBounds(130, 30, 130, 30);
            view.acido.setVisible(true);
            view.sinAzucar.setVisible(true);
            view.dulce.setVisible(true);
            view.labelTipoDulce.setVisible(true);
            view.campoNombre.setVisible(true);
            view.nombreDulce.setVisible(true);
            view.buttonAllDulces.setVisible(true);
            view.buttonCrearDulce.setVisible(true);

        }
    }

    private int index = 0;
    private Datos data;
    private InterfazGrafica view;

}
