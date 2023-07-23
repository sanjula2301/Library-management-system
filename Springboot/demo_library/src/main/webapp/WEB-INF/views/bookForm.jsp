<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Book Form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h2 class="text-center">Book Form</h2>
            <form action="${pageContext.request.contextPath}/books/postBook" method="post">

                <div class="form-group">
                    <input type="hidden" name="id" value="${book.id}" />
                </div>

                <div class="form-group">
                    <label for="title">Title:</label>
                    <input type="text" class="form-control" id="title" name="title" value="${book.title}" required />
                </div>

                <div class="form-group">
                    <label for="author">Author:</label>
                    <input type="text" class="form-control" id="author" name="author" value="${book.author}" required />
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Save</button>
                    <a href="${pageContext.request.contextPath}/books/deleteBook/${book.id}" class="btn btn-danger">Delete</a>
                    <a href="${pageContext.request.contextPath}/books/getBook/${book.id}" class="btn btn-secondary">Update</a>
                </div>

            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
