package gui.tabs;

import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class GameTab extends BasicTabbedPaneUI {

	@Override
	protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {

		Component tabComponent = tabPane.getTabComponentAt(tabIndex);
		if (tabComponent != null) {
			return tabComponent.getWidth() + 7;
		}
		return super.calculateTabWidth(tabPlacement, tabIndex, metrics);
	}

	@Override
	protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect) {

		Component tabComponent = tabPane.getTabComponentAt(tabIndex);
		if (tabComponent != null) {
			Rectangle tabRect = rects[tabIndex];
			Insets tabInsets = getTabInsets(tabPlacement, tabIndex);
			Rectangle clipRect = g.getClipBounds();
			clipRect.width = tabRect.width + tabInsets.right;
			clipRect.x = tabRect.x + tabRect.width - tabInsets.right;
			g.setClip(clipRect);
			tabComponent.paint(g);
			g.setClip(clipRect.x, clipRect.y, clipRect.width + tabInsets.right, clipRect.height);
		}
	}

	@Override
	protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {


	}

	@Override
	protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {

	}
}