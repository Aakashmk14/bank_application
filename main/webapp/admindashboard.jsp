<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Georgia, serif;;
            background-image: url('images/admin.jpg'); /* Replace with your background image URL */
            background-size: cover;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            width: 70%;
            max-width: 600px;
            background-color: rgba(255, 255, 255, 0.50); /* White with transparency */
            padding: 10px;
            margin: 20px,30px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .header {
             /* Dark Lavender */
            padding: 20px;
            border-radius: 8px 8px 0 0;
            position: relative;
        }
        .header a {
            position: absolute;
            right: 20px;
            top: 20px;
            text-decoration: none;
            color: white;
            background-color: #333;
            padding: 10px 20px;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .header a:hover {
            background-color: #555;
        }
        .menu {
            margin: 20px 30px;
            padding: 10px;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            margin-top: 15px;
        
        }
         .menu a {
            text-decoration: none;
            color: white;
            padding: 25px;
            margin: 10px;
            background-color: #25475e; /* Dark Lavender */
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.50);
            flex: 1 1 20%;
            text-align: center;
        }
        .menu a:hover {
            background-color:#7fb6db; /* Slightly darker lavender */
        }
        .content {
            padding: 20px;
        }
         .logout {
            position: absolute;
            top: 10px;
            right: 20px;
            text-decoration: none;
            color: white;
            padding: 10px 20px;
            background-color: #f21313; /* Red */
            border-radius: 8px;
        }
        .logout:hover {
            background-color: #752815; /* Darker Red */
        }
    </style>
</head>
<body>
    <div class="container">
    <a href="login.jsp" class="logout">Logout</a>
        <div class="header">
            <h1>Admin Dashboard</h1>
        </div>
        <div class="menu">
            <a href="registerNewCustomer.jsp">Register New Customer</a>
            <a href="viewCustomers">View Customers</a>
        </div>
        <div class="content">
            <h2>Welcome, Admin!</h2>
            <p>Use the menu above to manage customers.</p>
        </div>
    </div>
</body>
</html>
