<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
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
            width: 90%;
            max-width: 800px; /* Optional: limit the max width */
            background: rgba(255, 255, 255, 0.55); /* White with 35% opacity */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h2 {
            font-size: 3em;
            text-align: center;
            margin-bottom: 20px;
        }
        .menu {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            margin-top: 30px;
        }
        .menu a {
            text-decoration: none;
            color: white;
            padding: 20px;
            margin: 10px;
            background-color: #161d24; /* Dark Lavender */
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            flex: 1 1 20%;
            text-align: center;
        }
        .menu a:hover {
            background-color: #161d24; /* Slightly darker Lavender */
        }
        .logout {
            position: absolute;
            top: 10px;
            right: 20px;
            text-decoration: none;
            color: white;
            padding: 10px 20px;
            background-color: #752815; /* Red */
            border-radius: 8px;
        }
        .logout:hover {
            background-color: #752815; /* Darker Red */
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="logoutServlet" class="logout">Logout</a>
        <h2>Customer Dashboard</h2>
        <div class="menu">
            <a href="setPassword.jsp">Set Password</a>
            <a href="withdraw.jsp">Withdraw</a>
            <a href="deposit.jsp">Deposit</a>
            <a href="balanceServlet">Check Balance</a>
            <a href="transactionServlet">Transactions</a>
        </div>
    </div>
</body>
</html>
