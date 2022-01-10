<?php
require "conn.php";

$id = $_POST["id"];
// $id = "15";

$qry = "SELECT * FROM receitas WHERE id like '$id'";

$result = mysqli_query($conn,$qry);

if(mysqli_num_rows($result) > 0){
    while ($row = mysqli_fetch_assoc($result)) {
        echo  "receita: " . $row["nome"] . "\n ";
        echo "texto: " . $row["texto"];
        
        $qry = "SELECT * FROM receita_ingrediente WHERE id_receita like '$id'";
        $result2 = mysqli_query($conn,$qry);

        echo "\n ingredientes: ";

        while ($row = mysqli_fetch_assoc($result2)) {
            $idIngred =  $row["id_ingrediente"];
            
            $qry = "SELECT * FROM ingredientes WHERE id like '$idIngred'";
            $result3 = mysqli_query($conn,$qry);

            while ($row = mysqli_fetch_assoc($result3)) {
                echo $row["nome"] . ",";
        
            }

        }
    }
    
}else{
    echo "Receita não encontrada";
}

$conn->close();