package tetris;

import javax.swing.JPanel;

/**
 * A transparent JPanel designed to be used without any components
 * Typically used for spacing and dividers in Layout Managers
 */
class EmptyPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a transparent, empty panel
	 */
	public EmptyPanel()
	{
		super();
		setOpaque(false);
	}
}