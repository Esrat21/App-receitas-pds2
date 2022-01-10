<?php

require "conn.php";

$idUsuario = $_POST["id"];
$pwd = $_POST["senha"];

$qry = "UPDATE usuarios SET senha = '$pwd' WHERE id = '$idUsuario'";

if($conn->query($qry) === TRUE){
    echo "true";
}else{
    echo "false";
}

$conn->close();
