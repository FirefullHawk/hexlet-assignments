@import exercise.dto.posts.PostPage
@param PostPage page

<!-- BEGIN -->
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    <title>User</title>
  </head>
  <body>
    <div class="mx-auto p-4 py-md-5">
      <main>
        <p>
            <table class = "table">
                <tr>
                <td>ID</td>
                <td>Name</td>
                <td>Body</td>
                </tr>
                <tr>
                <td>${page.getPost().getId()}</td>
                <td>${page.getPost().getName()}</td>
                <td>${page.getPost().getBody()}</td>
                </tr>
            </table>
        </p>
      </main>
    </div>
  </body>
</html>
<!-- END -->
