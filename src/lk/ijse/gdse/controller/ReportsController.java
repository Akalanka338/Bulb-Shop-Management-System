package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import lk.ijse.gdse.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;

public class ReportsController {
    public void ItemReportsOnAction(ActionEvent actionEvent) throws JRException {
        InputStream inputStream = this.getClass().getResourceAsStream("/lk/ijse/gdse/reports/item_details.jrxml");
        JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null,
                DBConnection.getDbConnection().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }
}
