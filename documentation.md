
# Product Documentation

## Introduction
#### The Ticketing System is designed to manage, track, and organize tickets for customers and agents. It provides separate access roles, ensuring relevant data is available to each type of user.

### Features & Functionality 

User Authentication

    Registration: New users can sign up and choose their roles (either as a customer or agent).
    Login: Existing users can securely log in to access their dashboard and functionalities relevant to their role.

Ticket Management

    For Customers:
        Create a new ticket
        View their tickets
    For Agents:
        View all tickets
        Respond to and manage tickets

Role-Based Access

    Customers: Can only see and manage their own tickets.
    Agents: Can see and manage all tickets in the system.

System Requirements

    Frontend: Angular
    Backend: Spring Boot with Spring Security for authentication and authorization.

# Process Documentation 
User Registration Process

    The user navigates to the /register endpoint.
    User provides a username, password, and selects a role.
    If registration is successful, a confirmation message is shown.

User Login Process

    The user navigates to the /login endpoint.
    User provides their username and password.
    If the credentials are correct, the user is directed to their dashboard; otherwise, an error message is displayed.

Ticket Creation by Customer

    After logging in, the customer navigates to the "Create Ticket" section.
    The customer fills out the ticket details and submits the form.
    A confirmation message is shown, and the ticket is added to the system.

Ticket Management by Agent

    After logging in, the agent navigates to the "All Tickets" section.
    Here, the agent can view all tickets from all customers.
    The agent can click on a ticket to view its details and provide a response or take action.
