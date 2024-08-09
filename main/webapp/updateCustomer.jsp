<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Customer</title>
    <style>
        body {
            font-family: Georgia, serif;
            background: url('images/admin.jpg') no-repeat center center fixed;
            background-size: cover;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 60%;
            margin: 50px auto;
            background: rgba(255, 255, 255, 0.55);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            font-size: 2em;
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin: 10px 0 5px;
            font-weight: bold;
        }
        input[type="text"] {
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .btn {
            background-color: #25475e; /* Dark Lavender */
            color: white;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-align: center;
        }
        .btn:hover {
            background-color: #5a4b7d;
        }
        .message {
            color: green;
            font-weight: bold;
            text-align: center;
            margin-bottom: 20px;
        }
        .error {
            color: red;
            font-weight: bold;
            text-align: center;
            margin-bottom: 20px;
        }
         .backlink {
            position: absolute;
            top: 10px;
            right: 20px;
            text-decoration: none;
            color: white;
            padding: 10px 20px;
            background-color: #f21313; /* Red */
            border-radius: 8px;
        }
        .backlink:hover {
            background-color: #752815
         }
    </style>
</head>
<body>
    <div class="container">
     <a href="admindashboard.jsp" class="backlink">Back to Dashboard</a>
        <h2>Update Customer Details</h2>
        <%
            Customer customer = (Customer) request.getAttribute("customer");
            if (customer != null) {
        %>
        <form action="updateCustomer" method="post">
            <input type="hidden" name="accountNo" value="<%= customer.getAccountNo() %>">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="<%= customer.getUsername() %>" required>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="<%= customer.getAddress() %>" required>

            <label for="mobileNo">Mobile No:</label>
            <input type="text" id="mobileNo" name="mobileNo" value="<%= customer.getMobileNo() %>" required>

            <label for="emailId">Email ID:</label>
            <input type="text" id="emailId" name="emailId" value="<%= customer.getEmailId() %>" required>

            <label for="accountType">Account Type:</label>
            <input type="text" id="accountType" name="accountType" value="<%= customer.getAccountType() %>" required>

            <label for="dateOfBirth">Date of Birth:</label>
            <input type="text" id="dateOfBirth" name="dateOfBirth" value="<%= customer.getDateOfBirth() %>" required>

            <label for="idProof">ID Proof:</label>
            <input type="text" id="idProof" name="idProof" value="<%= customer.getIdProof() %>" required>

            <input type="submit" value="Update Customer" class="btn">
        </form>
        <% 
            } else {
        %>
        <div class="error">Customer details not found.</div>
        <% 
            }
        %>
       
    </div>
</body>
</html>
