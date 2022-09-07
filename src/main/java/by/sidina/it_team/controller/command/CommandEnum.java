package by.sidina.it_team.controller.command;

import by.sidina.it_team.controller.Command;
import by.sidina.it_team.controller.command.impl.*;
import by.sidina.it_team.controller.command.impl.EditProjectGetCommand;
import by.sidina.it_team.controller.command.impl.HomeShowProjectsCommand;
import by.sidina.it_team.controller.command.impl.HomePage;
import by.sidina.it_team.controller.command.impl.ShowEmployeesGetCommand;

public enum CommandEnum {
    SELECT_LOCALE {
        {
            this.command = new SelectLocalePostCommand();
        }
    },
    SIGN_IN_GET {
        {
            this.command = new SignInGetCommand();
        }
    },
    SIGN_IN_POST {
        {
            this.command = new SignInPostCommand();
        }
    },
    SIGN_UP_GET {
        {
            this.command = new SignUpGetCommand();
        }
    },
    SIGN_UP_POST {
        {
            this.command = new SignUpPostCommand();
        }
    },
    SIGN_OUT_POST {
        {
            this.command = new SignOutPostCommand();
        }
    },
    HOME_GET {
        {
            this.command = new HomePage();
        }
    },
    POST_HOURS_POST {
        {
            this.command = new PostHoursPostCommand();
        }
    },
    POST_HOURS_GET {
        {
            this.command = new PostHoursGetCommand();
        }
    },
    SHOW_PROJECTS {
        {
            this.command = new HomeShowProjectsCommand();
        }
    },
    EDIT_PROJECT_GET {
        {
            this.command = new EditProjectGetCommand();
        }
    },
    SHOW_EMPLOYEES_GET {
        {
            this.command = new ShowEmployeesGetCommand();
        }
    },
    SHOW_FREE_EMPLOYEES_GET {
        {
            this.command = new ShowFreeEmployeesGetCommand();
        }
    },
    SHOW_EMPLOYEES_ON_PROJECT_GET {
        {
            this.command = new ShowEmployeesOnProjectGetCommand();
        }
    },
    SHOW_EMPLOYEE_GET {
        {
            this.command = new ShowEmployeeGetCommand();
        }
    },
    EDIT_EMPLOYEE_GET {
        {
            this.command = new EditEmployeeGetCommand();
        }
    },
    SHOW_CUSTOMERS_GET {
        {
            this.command = new ShowCustomersGetCommand();
        }
    },
    SHOW_CUSTOMER_GET {
        {
            this.command = new ShowCustomerGetCommand();
        }
    },
    EDIT_CUSTOMER_GET {
        {
            this.command = new EditCustomerGetCommand();
        }
    },
    NEW_PROJECT_GET {
        {
            this.command = new NewProjectGetCommand();
        }
    },
    NEW_PROJECT_POST {
        {
            this.command = new NewProjectPostCommand();
        }
    },
    NEW_PAYMENT_POST {
        {
            this.command = new NewPaymentPostCommand();
        }
    },
    SHOW_PROJECT_GET {
        {
            this.command = new ShowProjectGetCommand();
        }
    },
    CHANGE_EMPLOYEE_STATUS_POST {
        {
            this.command = new ChangeEmployeeStatusPostCommand();
        }
    },
    CHANGE_CUSTOMER_STATUS_POST {
        {
            this.command = new ChangeCustomerStatusPostCommand();
        }
    },
    CHANGE_EMPLOYEE_POSITION_POST {
        {
            this.command = new ChangeEmployeePositionPostCommand();
        }
    },
    CHANGE_EMPLOYEE_LEVEL_POST {
        {
            this.command = new ChangeEmployeeLevelPostCommand();
        }
    },
    CHANGE_PROJECT_START {
        {
            this.command = new ChangeProjectStartDatePostCommand();
        }
    },
    CHANGE_PROJECT_STATUS {
        {
            this.command = new ChangeProjectStatusPostCommand();
        }
    },
    CHANGE_PROJECT_END {
        {
            this.command = new ChangeProjectEndDatePostCommand();
        }
    },
    ADD_EMPLOYEE_POST {
        {
            this.command = new AddEmployeeOnProjectPostCommand();
        }
    },
    ADD_NEW_EMPLOYEE_POST {
        {
            this.command = new AddNewEmployeePostCommand();
        }
    },
    ADD_NEW_EMPLOYEE_GET {
        {
            this.command = new AddNewEmployeeGetCommand();
        }
    },
    SHOW_CUSTOMERS_BY_PATTERN {
        {
            this.command = new ShowCustomersGetCommand();
        }
    },
    REMOVE_EMPLOYEES_POST {
        {
            this.command = new RemoveEmployeeFromProjectPostCommand();
        }
    },
    ADD_CALCULATION_PROJECT_POST {
        {
            this.command = new AddCalculationProjectPostCommand();
        }
    },
    REMOVE_CALCULATION_PROJECT_POST {
        {
            this.command = new RemoveCalculationProjectPostCommand();
        }
    };

    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}