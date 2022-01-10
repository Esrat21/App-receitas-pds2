<?php
require "conn.php";

$email = $_POST["email"];
$pwd = $_POST["senha"];
$premium = false;

$qry = "SELECT * FROM usuarios WHERE login like '$email' and senha like '$pwd'";

$result = mysqli_query($conn,$qry);

if(mysqli_num_rows($result) > 0){
    while ($row = mysqli_fetch_assoc($result)) {
       echo  $row["id"]."<>".$row["login"];
    }
    
}else{
    echo "false";
}

$conn->close();