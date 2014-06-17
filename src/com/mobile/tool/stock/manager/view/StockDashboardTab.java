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



import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.looks.LookUtils;
import com.mobile.tool.stock.manager.repository.SaleRecordsRepository;

/** 
 * Demos the <code>JDesktopPane</code>.
 * 
 * @author Karsten Lentzsch
 * @version $Revision: 1.6 $
 */
public class StockDashboardTab {
    
    private static final float SIZE_FACTOR = LookUtils.IS_LOW_RESOLUTION ? 1f : 1.175f;

    /**
     * Builds the panel.
     */
    public JComponent build() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(Borders.DIALOG_BORDER);
        panel.add(new JScrollPane(buildDesktopPane()));
        return panel;
    }

    private JComponent buildDesktopPane() {
        int gap      = (int) (10 * SIZE_FACTOR);
        int originX1 = 10;
        int extentX1 = (int) (193 * SIZE_FACTOR);
        int originX2 = originX1 + extentX1 + gap;
        int extentX2 = extentX1;
        
        JDesktopPane desktop = new JDesktopPane();
        JInternalFrame frame;

        frame = new JInternalFrame("Product Details", true, false, true, true);
        frame.setContentPane(buildFrame2ContentPane(buildProductListTable()));
        frame.setBounds(originX1, 10, extentX1, 320);
        desktop.add(frame);
        frame.setVisible(true);

        frame = new JInternalFrame("Customer Details", true, false, true, true);
        frame.setContentPane(buildFrame2ContentPane(buildCustomerListTable()));
        frame.setBounds(originX2, 10, extentX2, 320);
        desktop.add(frame);
        frame.setVisible(true);


        return desktop;
    }

	private JComponent buildFrame2ContentPane(JTable table) {
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(100, 140));
        return scrollPane;
    }


	private JTable buildProductListTable() {
        JTable table = new JTable(
        		SaleRecordsRepository.getProductSalesRecord(),
                new String[] { "Product", "Balance" });
        return table;
	}
	
	
	private JTable buildCustomerListTable() {
        JTable table = new JTable(
        		SaleRecordsRepository.getCustomerSalesRecord(),
                new String[] { "Customer", "Balance" });
        return table;
	}

}
