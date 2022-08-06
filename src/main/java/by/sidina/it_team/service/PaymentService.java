package by.sidina.it_team.service;

import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.service.exception.ServiceException;

import java.sql.Date;

public interface PaymentService {
    boolean addPaymentByProjectAndCustomerID(ProjectDto projectDto, double amount, Date date) throws ServiceException;
}
