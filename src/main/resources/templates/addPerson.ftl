<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Person</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet"
          type="text/css" href="<@spring.url '/css/style.css'/>"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        body {
            background-image: url('/img/logo.png');
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: 110px 90px;
            background-position: center top;
        }

        input[type=submit] {
            padding: 5px 20px 9px !important;
            font-size: 13px !important;
            background-color: whitesmoke;
            font-weight: bold;
            text-shadow: 1px 1px #eef3eb;
            color: #000000;
            border-radius: 100px;
            -moz-border-radius: 100px;
            -webkit-border-radius: 100px;
            border: 1px solid #eef3eb;
            cursor: pointer;
            box-shadow: 0 1px 0 rgba(0, 0, 0, 0.5) inset;
            -moz-box-shadow: 0 1px 0 rgba(0, 0, 0, 0.5) inset;
            -webkit-box-shadow: 0 1px 0 rgba(0, 0, 0, 0.5) inset;
        }


    </style>

</head>

<body>
<div>
    <fieldset>
        <legend>Add person</legend>
        <form name="person" action="" method="POST">
            First name:<@spring.formInput "personForm.firstName" "" "text"/>
            <br>
            Last name:<@spring.formInput "personForm.lastName" "" "text"/>
            <br>
            Gender:<@spring.formInput "personForm.gender" "" "text"/>
            <input type="submit" value="Save"/>
        </form>
    </fieldset>
</div>

<br>

<a href="http://localhost:8080/web/person/list" type="button" class="btn btn-light"><i class="fa fa-chevron-circle-left">
    </i>Back to table</a>

</body>
</html>