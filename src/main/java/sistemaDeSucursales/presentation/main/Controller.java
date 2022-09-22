package sistemaDeSucursales.presentation.main;

import sistemaDeSucursales.Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Controller {
    Model model;
    View view;

    public Controller(View view, Model model) {
        this.model = model;
        this.view = view;
        view.setModel(model);
        view.setController(this);
    }
    public void show(){
        Application.window.setContentPane(view.getPanel());
    }
}
