package cz.muni.fi.pv168.employees.ui.renderers;

import cz.muni.fi.pv168.employees.model.CarRide;

import javax.swing.*;

public class CarRideRender extends AbstractRenderer<CarRide> {

    public CarRideRender() {
        super(CarRide.class);
    }

    @Override
    protected void updateLabel(JLabel label, CarRide value) {
        label.setText(String.format("%s %s", value.getTitle(), value.getDescription()));
    }
}
