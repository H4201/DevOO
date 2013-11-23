package com.h4201.prototype.vue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import com.h4201.prototype.modele.TrancheHoraire;

public class TableRecap /*extends AbstractTableModel */{
	
	Vector <String> lesTranchesHoraires;

	public TableRecap(Vector <TrancheHoraire> tranchesHoraires) {
		DateFormat fd = new SimpleDateFormat("");
		for(TrancheHoraire trancheHoraire : tranchesHoraires )
		{
		    Date aujourdhui = Calendar.getInstance().getTime();
		    String dateFormatee = fd.format(aujourdhui);
			
		    lesTranchesHoraires.addElement(dateFormatee);
		}
	}

	
}
