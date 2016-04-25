package levels;

import javax.swing.JComponent;

public class MouseClickListener implements Runnable {

	private JComponent parent;

	public MouseClickListener(JComponent parent) {
		this.parent = parent;
	}

	@Override
	public void run() {
		try {
			parent.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}