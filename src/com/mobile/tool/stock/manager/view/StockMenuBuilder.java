package com.mobile.tool.stock.manager.view;

/*
 * Copyright (c) 2001-2005 JGoodies Karsten Lentzsch. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 *  o Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer. 
 *     
 *  o Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution. 
 *     
 *  o Neither the name of JGoodies Karsten Lentzsch nor the names of 
 *    its contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission. 
 *     
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, 
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;

/**
 * Builds the menu bar and pull-down menus in the Simple Looks Demo. 
 * Demonstrates and tests different multi-platform issues.<p>
 * 
 * This class provides a couple of factory methods that create
 * menu items, check box menu items, and radio button menu items.
 * The full JGoodies Looks Demo overrides these methods to vend
 * components from the JGoodies UI framework that better handle
 * different platforms.
 *
 * @author Karsten Lentzsch
 * @version $Revision: 1.9 $
 */

public class StockMenuBuilder {
    
	JFrame frame;
	
	public StockMenuBuilder(JFrame frame) {
		super();
		this.frame = frame;
	}

	/**
	 * Builds, configures, and answers the menubar. Requests HeaderStyle, 
	 * look-specific BorderStyles, and Plastic 3D hint from Launcher.
	 */
	JMenuBar buildMenuBar(
        Settings settings, 
        ActionListener helpActionListener, 
        ActionListener aboutActionListener) {
            
		JMenuBar bar = new JMenuBar();
		bar.putClientProperty(Options.HEADER_STYLE_KEY, 
							  settings.getMenuBarHeaderStyle());
		bar.putClientProperty(PlasticLookAndFeel.BORDER_STYLE_KEY, 
							  settings.getMenuBarPlasticBorderStyle());
		bar.putClientProperty(PlasticXPLookAndFeel.BORDER_STYLE_KEY, 
							  settings.getMenuBarWindowsBorderStyle());
		bar.putClientProperty(PlasticLookAndFeel.IS_3D_KEY,
							  settings.getMenuBar3DHint());

		bar.add(buildFileMenu());
		bar.add(buildHelpMenu(helpActionListener, aboutActionListener));
		return bar;
	}
	
	
	/**
	 * Builds and answers the file menu.
	 */
	private JMenu buildFileMenu() {
		JMenuItem item;
		
		JMenu menu = createMenu("Option", 'O');
		
		item = createMenuItem("Log Out",  readImageIcon("logout.png"), 'C', KeyStroke.getKeyStroke("ctrl F4"));
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {
				frame.dispose();
				LogInView.invoke();
			}
		});
		menu.add(item);
		
		return menu;
	}
 
	/**
	 * Builds and answers the help menu.
	 */
	private JMenu buildHelpMenu(
        ActionListener helpActionListener, 
        ActionListener aboutActionListener) {

		JMenu menu = createMenu("Help", 'H');
		
		JMenuItem item;
        item = createMenuItem("Help Contents", readImageIcon("help.gif"), 'H');
        if (helpActionListener != null) {
    		item.addActionListener(helpActionListener);
        }
        menu.add(item);
        if (!isAboutInOSMenu()) {
            menu.addSeparator();
            item = createMenuItem("About", 'a');
            item.addActionListener(aboutActionListener);
            menu.add(item);
        }
		
		return menu;
	}
    
    // Factory Methods ********************************************************

    protected JMenu createMenu(String text, char mnemonic) {
        JMenu menu = new JMenu(text);
        menu.setMnemonic(mnemonic);
        return menu;
    }
        
    protected JMenuItem createMenuItem(String text) {
        return new JMenuItem(text);
    }
    
    protected JMenuItem createMenuItem(String text, char mnemonic) {
        return new JMenuItem(text, mnemonic);
    }
    
    protected JMenuItem createMenuItem(String text, char mnemonic, KeyStroke key) {
        JMenuItem menuItem = new JMenuItem(text, mnemonic);
        menuItem.setAccelerator(key);
        return menuItem;
    }
    
    protected JMenuItem createMenuItem(String text, Icon icon) {
        return new JMenuItem(text, icon);
    }
	
    protected JMenuItem createMenuItem(String text, Icon icon, char mnemonic) {
        JMenuItem menuItem = new JMenuItem(text, icon);
        menuItem.setMnemonic(mnemonic);
        return menuItem;
    }
    
    protected JMenuItem createMenuItem(String text, Icon icon, char mnemonic, KeyStroke key) {
        JMenuItem menuItem = createMenuItem(text, icon, mnemonic);
        menuItem.setAccelerator(key);
        return menuItem;
    }
    
    protected JRadioButtonMenuItem createRadioButtonMenuItem(String text, boolean selected) {
        return new JRadioButtonMenuItem(text, selected);
    }
    
    protected JCheckBoxMenuItem createCheckBoxMenuItem(String text, boolean selected) {
        return new JCheckBoxMenuItem(text, selected);
    }
    

    // Subclass will override the following methods ***************************
    
    /**
     * Checks and answers whether the quit action has been moved to an
     * operating system specific menu, e.g. the OS X application menu.
     *  
     * @return true if the quit action is in an OS-specific menu
     */
    protected boolean isQuitInOSMenu() {
        return false;
    }
    

    /**
     * Checks and answers whether the about action has been moved to an
     * operating system specific menu, e.g. the OS X application menu.
     *  
     * @return true if the about action is in an OS-specific menu
     */
    protected boolean isAboutInOSMenu() {
        return false;
    }
	
	/**
	 *  Answers an appropriate label for the given enablement and selection state.
	 */
	protected String getToggleLabel(boolean enabled, boolean selected) {
		String prefix = enabled  ? "Enabled" : "Disabled";
		String suffix = selected ? "Selected" : "Deselected";
		return prefix + " and " + suffix;
	}
	
    // Helper Code ************************************************************
    
    /**
     * Looks up and answers an icon for the specified filename suffix.
     */
    private ImageIcon readImageIcon(String filename) {
        URL url = getClass().getClassLoader().getResource("images/" + filename);
        return new ImageIcon(url);
    }
    

}
