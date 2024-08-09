<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Customers</title>
    <style>
        body {
            font-family: TimesNewroman, serif;
            background: url('images/admin.jpg') no-repeat center center fixed;
            background-size: cover;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            -
        }
        .container {
            width: 90%;
            max-width: 1200px;
            background: rgba(255, 255, 255, 0.70);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
            margin: 50px auto;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
            font-size: 2em;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            table-layout: auto;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #25475e;
            color: white;
        }
        tr {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: grey;
        }
        .btn {
            padding: 10px 20px;
            background-color:  #25475e;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
            text-decoration: none;
        }
        .btn:hover {
            background-color: #7accde;
        }
        .backlink {
            position: absolute;
            display: inline-block;
            margin-bottom: 20px;
            text-decoration: none;
            color: white;
            padding: 10px 20px;
            background-color: #f21313;
            border-radius: 8px;
    
        }
        .backlink:hover {
            background-color: #d31111;
        }
        .actions {
            display: flex;
            gap: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="admindashboard.jsp" class="backlink">Back to Dashboard</a>
        <h2>Customer Details</h2>
        <table>
            <thead>
                <tr>
                    <th>Account No</th>
                    <th>Username</th>
                    <th>Address</th>
                    <th>Mobile No</th>
                    <th>Email ID</th>
                    <th>Account Type</th>
                    <th>Date of Birth</th>
                    <th>ID Proof</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
                    if (customers != null && !customers.isEmpty()) {
                        for (Customer customer : customers) {
                %>
                <tr>
                    <td><%= customer.getAccountNo() %></td>
                    <td><%= customer.getUsername() %></td>
                    <td><%= customer.getAddress() %></td>
                    <td><%= customer.getMobileNo() %></td>
                    <td><%= customer.getEmailId() %></td>
                    <td><%= customer.getAccountType() %></td>
                    <td><%= customer.getDateOfBirth() %></td>
                    <td><%= customer.getIdProof() %></td>
                    <td class="actions">
                        <a href="updateCustomer?accountNo=<%= customer.getAccountNo() %>" class="btn">Update</a> 
                        <a href="deleteCustomer?accountNo=<%= customer.getAccountNo() %>" class="btn">Delete</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="9">No customer details available.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
