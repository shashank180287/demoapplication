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
import com.mobile.tool.stock.manager.entity.EmployeeQualificationDetails;
import com.mobile.tool.stock.manager.repository.EmployeeQualificationDetailsRepository;

public class EmployeeQualificationDetailsDashboard {

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
		List<EmployeeQualificationDetails> employeeQualificationDetails = EmployeeQualificationDetailsRepository.getEmployeeQualificationDetailsByQuery(employeeCode);
		JPanel lowerPanel = null;
		if(employeeQualificationDetails!=null && employeeQualificationDetails.size()>0){
			lowerPanel = new JPanel(new GridLayout(employeeQualificationDetails.size(), 1));
			for (EmployeeQualificationDetails employeeQualificationDetail : employeeQualificationDetails) {
				lowerPanel.add(new JLabel("<html><body><h1 class=\"oRowTitle\">"+employeeQualificationDetail.getDegreeName()+"        <span class=\"oHMin\">"+employeeQualificationDetail.getInstitutionName()+"</span>"+
				        "</h1><div class=\"oSupportInfo\">"+employeeQualificationDetail.getStartDate()+" - "+employeeQualificationDetail.getEndDate()+
				        "        </div> <div data-id=\"3298079\">"+employeeQualificationDetail.getPercentage()+"</div></body></html>"));
			}
		}else{
			lowerPanel = new JPanel();
		}
		return lowerPanel;
	}
}
