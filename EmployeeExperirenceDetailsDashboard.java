package com.mobile.tool.stock.manager.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jgoodies.forms.factories.Borders;
import com.mobile.tool.stock.manager.entity.EmployeeProfessionalDetails;
import com.mobile.tool.stock.manager.repository.EmployeeProfessionDetailsRepository;

public class EmployeeExperirenceDetailsDashboard {

    public JComponent build(String employeeCode) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(Borders.DIALOG_BORDER);
		panel.add(buildHorizontalSplit(employeeCode));
		return panel;
    }

	private JComponent buildHorizontalSplit(String employeeCode) {
		JComponent lowerPanel = new JScrollPane(buildExperiencePanel(employeeCode));
		return lowerPanel;
	}

	private Component buildExperiencePanel(String employeeCode) {
		List<EmployeeProfessionalDetails> employeeProfessionalDetails = EmployeeProfessionDetailsRepository.getEmployeeProfessionalDetailsByEmployeeCode(employeeCode);
		JPanel lowerPanel = null;
		if(employeeProfessionalDetails!=null && employeeProfessionalDetails.size()>0){
			lowerPanel = new JPanel(new GridLayout(employeeProfessionalDetails.size(), 1));
			for (EmployeeProfessionalDetails employeeProfessionalDetail : employeeProfessionalDetails) {
				lowerPanel.add(new JLabel("<html><body><h1 class=\"oRowTitle\">"+employeeProfessionalDetail.getJobTitle()+"        <span class=\"oHMin\">"+employeeProfessionalDetail.getCompanyName()+"</span>"+
				        "</h1><div class=\"oSupportInfo\">"+employeeProfessionalDetail.getStartDate()+" - "+employeeProfessionalDetail.getEndDate()+"        </div> <div data-id=\"3298079\">"+employeeProfessionalDetail.getResponsobilities()+"</div></body></html>"));
			}
		}else{
			lowerPanel = new JPanel();
		}
		return lowerPanel;
	}
}
