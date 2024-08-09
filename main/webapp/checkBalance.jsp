<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Check Balance</title>
    <style>
        body {
            font-family: Georgia, serif;
            background: url('images/new.jpg') no-repeat center center fixed; /* Replace with your wallpaper path */
            background-size: cover;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Full viewport height */
        }
        .container {
            width: 50%;
            max-width: 400px; /* Optional: limit the max width */
            background: rgba(255, 255, 255, 0.80); /* White with 35% opacity */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            font-family: Georgia, serif;
            font-size: 1.5em;
        }
        h2 {
            font-size: 1.8em;
            text-align: center;
            margin-bottom: 20px;
        }
         .dashboard {
            position: absolute;
            top: 10px;
            right: 20px;
            text-decoration: none;
            color: black;
            padding: 5px 15px;
            background-color:  #a8980a; /* Red */
            border-radius: 8px;
        }
        .dashboard:hover {
            background-color: #f5dd0f; /* Darker Red */
        }
    </style>
</head>
<body>
    <div class="container">
     <a href="customerDashboard.jsp"class="dashboard">Back to Dashboard</a>
        <h2>Account Balance</h2>
        <p>Your current balance is: <strong>${balance}</strong></p>
    </div>
</body>
</html>
