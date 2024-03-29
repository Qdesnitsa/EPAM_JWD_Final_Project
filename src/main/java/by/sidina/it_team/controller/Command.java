package by.sidina.it_team.controller;

import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException;
}