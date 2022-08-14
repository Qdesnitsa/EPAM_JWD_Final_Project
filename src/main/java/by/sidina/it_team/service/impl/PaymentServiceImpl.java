package by.sidina.it_team.service.impl;

import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.PaymentDAO;
import by.sidina.it_team.service.repository.PaymentService;
import by.sidina.it_team.service.exception.ServiceException;
import by.sidina.it_team.service.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;

public class PaymentServiceImpl implements PaymentService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final PaymentDAO paymentDAO;
    public PaymentServiceImpl(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }
    @Override
    public boolean addPaymentByProjectAndCustomerID(ProjectDto projectDto, String amount, Date date) throws ServiceException {
        boolean isAdded = false;
        if (Validator.isValidPayment(amount) && projectDto != null) {
            try {
                isAdded = paymentDAO.addPaymentByProjectAndCustomerID(projectDto, Double.parseDouble(amount), date);
            } catch (DAOException e) {
                LOGGER.error(e);
                throw new ServiceException("Failed to add payment from DAO", e);
            }
        }
        return isAdded;
    }
}
