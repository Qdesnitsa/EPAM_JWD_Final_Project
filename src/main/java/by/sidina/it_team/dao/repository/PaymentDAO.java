package by.sidina.it_team.dao.repository;

import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.exception.DAOException;

import java.sql.Date;

public interface PaymentDAO {
    boolean addPaymentByProjectAndCustomerID(ProjectDto projectDto, double amount, Date date) throws DAOException;
}
