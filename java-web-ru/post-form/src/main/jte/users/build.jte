<!-- BEGIN -->
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <title>Create new user</title>
</head>
    <body>
        <form action="/users" method="post">
            <div>
                <label>
                    Имя
                    <input type="text" name="firstName" />
                </label>
            </div>
            <div>
                <label>
                    Имя
                    <input type="text" name="secondName" />
                </label>
            </div>
            <div>
                <label>
                    Email
                    <input type="email" required name="email" />
                </label>
            </div>
            <div>
                <label>
                    Пароль
                    <input type="password" required name="password" />
                </label>
            </div>
            <input type="submit" value="Зарегистрировать" />
        </form>
    </body>
</html>
<!-- END -->
