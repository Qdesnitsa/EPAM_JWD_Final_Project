package by.sidina.it_team.service.impl;

import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.impl.PaymentDAOImpl;
import by.sidina.it_team.dao.repository.PaymentDAO;
import by.sidina.it_team.service.PaymentService;
import by.sidina.it_team.service.exception.ServiceException;

import java.sql.Date;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentDAO paymentDAO;
    public PaymentServiceImpl(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }
    @Override
    public boolean addPaymentByProjectAndCustomerID(ProjectDto projectDto, double amount, Date date) throws ServiceException {
        boolean isAdded;
        try {
            isAdded = paymentDAO.addPaymentByProjectAndCustomerID(projectDto, amount, date);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isAdded;
    }
}
