<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="Model.Transaction"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transaction History</title>
    <style>
        body {
            font-family: TimesNewroman, serif;
            background: url('images/new.jpg') no-repeat center center fixed; /* Replace with your wallpaper path */
            background-size: cover;
            margin: 0;
            padding: 0;
            display: flex;
            height: 100vh;
        }
        .container {
            width: 90%;
            margin: 50px ;
            background: rgba(255, 255, 255, 0.75);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            font-family: Georgia, serif;
            font-size: 1.5em;
        }
        h2 {
        font-size: 2em;
            text-align: center;
            margin-bottom: 20px;
        }
        table {
            
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #161d24; /* Dark Lavender */
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .button-container {
            text-align: center;
            margin-top: 20px;
        }
        .download-btn {
            padding: 10px 10px;
            background-color: #161d24;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        .download-btn:hover {
            background-color:  #161d24;
        }
         .dashboard {
            position: absolute;
            top: 5px;
            right: 5px;
            text-decoration: none;
            color: black;
            padding: 5px 10px;
            background-color:  #a8980a; /* Red */
            border-radius: 5px;
        }
        .dashboard:hover {
            background-color: #f5dd0f; /* Darker Red */
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="customerDashboard.jsp"class="dashboard">Back to Dashboard</a>
        <h2>Transaction History</h2>
        <table>
            <thead>
                <tr>
                    <th>Transaction ID</th>
                    <th>Account Number</th>
                    <th>Date</th>
                    <th>Type</th>
                    <th>Amount</th>
                    <th>Balance After</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
                    if (transactions != null) {
                        for (Transaction transaction : transactions) {
                %>
                <tr>
                    <td><%= transaction.getTransactionId() %></td>
                    <td><%= transaction.getAccountNo() %></td>
                    <td><%= transaction.getTransactionDate() %></td>
                    <td><%= transaction.getTransactionType() %></td>
                    <td><%= transaction.getAmount() %></td>
                    <td><%= transaction.getBalanceAfter() %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6">No transactions available.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <div class="button-container">
            <form action="downloadTransactions" method="get">
                <button type="submit" class="download-btn">Download Transactions as PDF</button>
            </form>
        </div>
    </div>
</body>
</html>
