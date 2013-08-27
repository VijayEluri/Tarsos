package be.hogent.tarsos.ui.link.layers.coordinatesystemlayers;

import java.awt.Color;
import java.awt.Graphics2D;

import be.hogent.tarsos.ui.link.LinkedPanel;
import be.hogent.tarsos.ui.link.coordinatessystems.CoordinateSystem;
import be.hogent.tarsos.ui.link.layers.LayerUtilities;

public class AmplitudeCoordinateSystemLayer extends AxisLayer {
	
	public AmplitudeCoordinateSystemLayer(LinkedPanel parent, char direction) {
		super(parent, direction);
		this.name = "Amplitude Axis";
	}
	
	public void draw(Graphics2D graphics) {
		CoordinateSystem cs = parent.getCoordinateSystem();

		// draw legend
		graphics.setColor(Color.black);
		boolean horizontal = direction == DIRECTION_X ? true : false;
		int startOppositeAxis = Math.round(cs.getMin(oppositeDirection));

		
		for (int i = (int) cs.getMin(direction); i < cs.getMax(direction); i+=1) {
			if (i % 1000 == 0) {
				int lineWidth = Math.round(LayerUtilities.pixelsToUnits(
						graphics, 4, !horizontal));
				if (direction == DIRECTION_Y) {
					graphics.drawLine(startOppositeAxis, i, startOppositeAxis
							+ lineWidth, i);
				} else {
					graphics.drawLine(i, startOppositeAxis, i,
							startOppositeAxis + lineWidth);
				}
				String text = String.valueOf(i/1000);
				int textOffset = Math.round(LayerUtilities.pixelsToUnits(
						graphics, 12, !horizontal));
				LayerUtilities.drawString(graphics, text, startOppositeAxis
						+ textOffset, i, horizontal, !horizontal);
			} else if (i%100 == 0) {
				int lineWidth = Math.round(LayerUtilities.pixelsToUnits(
						graphics, 2, !horizontal));
				if (direction == AxisLayer.DIRECTION_Y) {
					graphics.drawLine(startOppositeAxis, i, startOppositeAxis
							+ lineWidth, i);
				} else {
					graphics.drawLine(i, startOppositeAxis, i, startOppositeAxis
							+ lineWidth);
				}
				
			}
		}
	}

}
