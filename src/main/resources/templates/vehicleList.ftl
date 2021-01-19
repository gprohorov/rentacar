<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Vehicles List</title>
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
    <h3>Vehicles List</h3>
    <br>


    <div>
        <fieldset>
            <legend>Find  brand</legend>
            <form name="search" action="" method="POST">
                Brand name:<@spring.formInput "searchForm.string" "" "text"/>
                <br>
                <input type="submit" value="Search"/>
            </form>
        </fieldset>
    </div>


    <br>

    <a href="http://localhost:8080/" type="button" class="btn btn-light" style="float:left; margin-top:5px;"><i class="fa fa-chevron-circle-left"></i>Back to home page</a>

    <a href="/web/vehicle/add" type="button" class="btn btn-light" style="float:right; margin-top:5px;"><i class="fa fa-plus-square-o"></i>Add new car</a>

    <br>

    <div>
        <table class="table table-sm table-striped table-bordered">
            <tr class="thead-dark">
                <th scope="col">ID</th>
                <th scope="col">Brand</th>
                <th scope="col">Model</th>
                <th scope="col">Picture</th>
                <th scope="col">Cost</th>
                <th scope="col">License Plate Number</th>
                <th scope="col">Type</th>
                <th scope="col">Year of Issue</th>
                <th scope="col">Rental Price <a href="/web/vehicle/list/sorted" type="button" class="btn btn-outline-light">
                        <i class="fa fa-sort-numeric-asc"></i></a> <br> <a href="http://localhost:8080/web/vehicle/list" type="button" class="btn btn-outline-light">
                        <i class="fa fa-undo"></i>Undo sort</a></th>
                <th scope="col">Is being repaired</th>
                <th scope="col">Taken</th>
                <th scope="col">Minutes left</th>
                <th scope="col">Delete</th>
                <th scope="col">Edit</th>
            </tr>
            <#list carset as car>

                <#assign repair = "">
                <#if car.maintenance == true>
                    <#assign repair = "yes">
                <#else>
                    <#assign repair = "no">
                </#if>
                <#assign rent = "">
                <#if car.taken == true>
                    <#assign rent = "yes">
                <#else>
                    <#assign rent = "no">
                </#if>
<#--
                <#assign minutes = 0>
                <#if car.hourBack >
                    <#assign minutes = car.hourBack>
                </#if>
-->

                <tr height="100" align="center">
                    <td>${car.id}</td>
                    <td>${car.brand}</td>
                    <td>${car.model}</td>
               <#--     <td> <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/1994_Audi_100_E_2.0_Front.jpg/305px-1994_Audi_100_E_2.0_Front.jpg" alt="Image is not available" style="width:100px;height:100px;"> </td>
               -->  <td> <img src="${car.url}" alt="Image is not available" style="max-width:100px;max-height:100px;">
                    </td>
                    <td>${car.cost}</td>
                    <td>${car.licensePlate}</td>
                    <td>${car.type}</td>
                    <td>${car.yearOfIssue}</td>
                    <td>${car.rentalFee}</td>
                    <td>${repair}</td>
                    <td>${rent}</td>
                    <td>${car.hourBack}</td>
                    <td><a href="/web/vehicle/delete/${car.id}" type="button" class="btn btn-outline-danger" onclick="return confirm('Are you sure you want to delete ${car.brand} ${car.model} with the license plate: ${car.licensePlate}?');"><i class="fa fa-trash"></i></a></td>
                    <td><a href="/web/vehicle/edit/${car.id}" type="button" class="btn btn-light" ><i class="fa fa-edit"></i>Edit</a></td>

                </tr>
            </#list>

        </table>
        </form>
    </div>

</div>
</body>
</html>

