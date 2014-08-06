package com.mobile.tool.stock.manager.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;

public class EmployeeDetailsDashboard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5563196550611319791L;

	private final Settings settings;
	private final String employeeCode;
	/**
	 * Constructs a <code>DemoFrame</code>, configures the UI, and builds the
	 * content.
	 * @param userLoginDetails 
	 */
	protected EmployeeDetailsDashboard(Settings settings, String employeeCode) {
		this.settings = settings;
		this.employeeCode = employeeCode;
		configureUI();
		build();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		repaint();
	}

	public static void main(String[] args) {
		EmployeeDetailsDashboard instance = new EmployeeDetailsDashboard(createSettings(), "1");
		instance.setSize(600, 600);
		instance.locateOnScreen(instance);
		instance.setVisible(true);
	}

	public static Settings createSettings() {
		Settings settings = Settings.createDefault();
		// Configure the settings here.
		return settings;
	}

	/**
	 * Configures the user interface; requests Swing settings and jGoodies Looks
	 * options from the launcher.
	 */
	private void configureUI() {
		Options.setDefaultIconSize(new Dimension(18, 18));

		// Set font options
		UIManager.put(Options.USE_SYSTEM_FONTS_APP_KEY,
				settings.isUseSystemFonts());
		Icon empty = new BlankTreeIcon();
        UIManager.put("Tree.closedIcon", empty);
        UIManager.put("Tree.openIcon", empty);
        UIManager.put("Tree.collapsedIcon", empty);
        UIManager.put("Tree.expandedIcon", empty);
        UIManager.put("Tree.leafIcon", empty);
        
		Options.setGlobalFontSizeHints(settings.getFontSizeHints());
		Options.setUseNarrowButtons(settings.isUseNarrowButtons());

		// Global options
		Options.setTabIconsEnabled(settings.isTabIconsEnabled());
		UIManager.put(Options.POPUP_DROP_SHADOW_ENABLED_KEY,
				settings.isPopupDropShadowEnabled());

		// Swing Settings
		LookAndFeel selectedLaf = settings.getSelectedLookAndFeel();
		if (selectedLaf instanceof PlasticLookAndFeel) {
			PlasticLookAndFeel.setMyCurrentTheme(settings.getSelectedTheme());
			PlasticLookAndFeel.setTabStyle(settings.getPlasticTabStyle());
			PlasticLookAndFeel.setHighContrastFocusColorsEnabled(settings
					.isPlasticHighContrastFocusEnabled());
		} else if (selectedLaf.getClass() == MetalLookAndFeel.class) {
			MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
		}

		// Work around caching in MetalRadioButtonUI
		JRadioButton radio = new JRadioButton();
		radio.getUI().uninstallUI(radio);
		JCheckBox checkBox = new JCheckBox();
		checkBox.getUI().uninstallUI(checkBox);

		try {
			UIManager.setLookAndFeel(selectedLaf);
		} catch (Exception e) {
			System.out.println("Can't change L&F: " + e);
		}

	}

	/**
	 * Builds the <code>DemoFrame</code> using Options from the Launcher.
	 */
	private void build() {
		setContentPane(buildContentPane());
		setTitle(getWindowTitle());
		setIconImage(readImageIcon("eye_16x16.gif").getImage());
	}

	/**
	 * Builds and answers the content.
	 */
	private JComponent buildContentPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(buildMainPanel(), BorderLayout.CENTER);
		return panel;
	}

	/**
	 * Builds and answers the tabbed pane.
	 */
	private Component buildMainPanel() {
		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.addTab("Personal Details", new EmployeePersonDetailsDashboard().build(employeeCode));
		tabbedPane.addTab("Qualification", new EmployeeQualificationDetailsDashboard().build(employeeCode));
		tabbedPane.addTab("Experience", new EmployeeExperirenceDetailsDashboard().build(employeeCode));
		tabbedPane.addTab("Additional Details", new EmployeeAdditionalDetailsDashboard().build(employeeCode));
		tabbedPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		return tabbedPane;
	}



	protected String getWindowTitle() {
		return "Employee Details";
	}

	/*
	 * Looks up and answers an icon for the specified filename suffix.<p>
	 */
	protected static ImageIcon readImageIcon(String filename) {
		URL url = EmployeeDetailsDashboard.class.getClassLoader().getResource(
				"images/" + filename);
		return new ImageIcon(url);
	}

	/**
	 * Locates the given component on the screen's center.
	 */
	protected void locateOnScreen(Component component) {
		Dimension paneSize = component.getSize();
		Dimension screenSize = component.getToolkit().getScreenSize();
		component.setLocation((screenSize.width - paneSize.width) / 2,
				(screenSize.height - paneSize.height) / 2);
	}
}
