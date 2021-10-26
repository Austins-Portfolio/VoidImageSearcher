package construct;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderListener implements ChangeListener{

	private int pos;
	
	public SliderListener(JSlider slider) {
		pos = slider.getValue();
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
            pos = source.getValue();
        }
	}
	
	public int getPos() {
		return pos;
	}
	
}
