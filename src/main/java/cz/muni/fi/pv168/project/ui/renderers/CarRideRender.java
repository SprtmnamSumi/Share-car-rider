package cz.muni.fi.pv168.project.ui.renderers;

import cz.muni.fi.pv168.project.business.model.CarRide;
import javax.swing.JLabel;

public class CarRideRender extends AbstractRenderer<CarRide> {

    public CarRideRender() {
        super(CarRide.class);
    }

    @Override
    protected void updateLabel(JLabel label, CarRide value) {
        label.setText(String.format("%s %s", value.getTitle(), value.getDescription()));
    }
}
