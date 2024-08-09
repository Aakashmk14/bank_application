<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Georgia, serif;
            background: url('images/login image.jpg') no-repeat center center fixed;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        
        .login-box {
            background: rgba(255, 255, 255, 0.40);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }
        .login-box h2 {
            font-size: 2.5em;
            margin-bottom: 20px;
            text-align: center;
            color: #333;
        }
        .login-box input[type="text"],
        .login-box input[type="password"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .login-box input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 4px;
            background-color: #8c070e;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        .login-box input[type="submit"]:hover {
            background-color: #8c070e;
        }
        .role-selector {
            margin: 20px 0;
            text-align: center;
        }
        .role-selector label {
            margin-right: 10px;
        }
    </style>
</head>
<body>

        <div class="login-box">
            <h2>Login</h2>
            <form action="loginServlet" method="post">
                <div class="role-selector">
                    <label for="role-admin">
                        <input type="radio" id="role-admin" name="role" value="admin" required> Admin
                    </label>
                    <label for="role-customer">
                        <input type="radio" id="role-customer" name="role" value="customer" required> Customer
                    </label>
                </div>
                <label for="username">Username or Account Number</label>
                <input type="text" id="username" name="identifier" required>
                
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
                
                <input type="submit" value="Login">
            </form>
        </div>
</body>
</html>
