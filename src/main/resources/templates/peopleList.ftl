<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>People List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body {
            background-image: url('/img/logo.png');
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: 110px 90px;
            background-position: center top;
        }
    </style>

</head>
<body>
<div class="container-fluid">
    <br>
    <h3>People List</h3>


    <div>
        <fieldset>
            <legend>Find  person</legend>
            <form name="search" action="" method="POST">
                Last name:<@spring.formInput "searchForm.string" "" "text"/>
                <br>
                <input type="submit" value="Search"/>
            </form>
        </fieldset>
    </div>

    <br>

    <a href="http://localhost:8080/" type="button" class="btn btn-light" style="float:left; margin-top:5px;"><i class="fa fa-chevron-circle-left"></i>Back to home page</a>

    <a href="/web/person/add" type="button" class="btn btn-light" style="float:right; margin-top:5px;"><i class="fa fa-plus-square-o"></i>Add new person</a>

    <br>

    <div>
            <table class="table table-sm table-striped table-bordered">
                <tr class="thead-dark">
                    <th scope="col">ID</th>
                    <th scope="col">First Name</th>
                    <th scope="col">Last Name   <a href="/web/person/list/sorted" type="button" class="btn btn-outline-light">
                            <i class="fa fa-sort-alpha-asc"></i></a> <a href="http://localhost:8080/web/person/list" type="button" class="btn btn-outline-light">
                            <i class="fa fa-undo"></i>Undo sort</a></th>
                    <th scope="col">Gender</th>
                    <th scope="col">Delete</th>
                    <th scope="col">Edit</th>
                </tr>
                <#list people as person>
        <tr>
            <td>${person.id}</td>
            <td>${person.firstName}</td>
            <td>${person.lastName}</td>
            <td>${person.gender}</td>
            <td><a href="/web/person/delete/${person.id}" type="button" class="btn btn-outline-danger" onclick="return confirm('Are you sure you want to delete ${person.firstName} ${person.lastName}?');"><i class="fa fa-trash"></i></a></td>
            <td><a href="/web/person/edit/${person.id}" type="button" class="btn btn-light" ><i class="fa fa-edit"></i>Edit</a></td>
        </tr>
    </#list>

    </table>
</div>

</div>
</body>
</html>
