<?php
require "conn.php";

$idReceita = $_POST["receita"];
$idUsuario = $_POST["usuario"];

$qry = "INSERT INTO receitas_favoritas (id_receita, id_usuario) VALUES ('$idReceita', '$idUsuario')";

$qry2 = "SELECT quant_likes from receitas WHERE id = $idReceita";
$result = mysqli_query($conn,$qry2);

if($conn->query($qry) === TRUE){
    echo "Adicionou Aos Favoritos";
    if(mysqli_num_rows($result) > 0){
        while ($row = mysqli_fetch_assoc($result)) {
            $likes = $row["quant_likes"];
        }

        $likes = intval($likes) + 1;

        $qry3 = "UPDATE receitas SET quant_likes = $likes WHERE id = $idReceita";
    }
    $conn->query($qry3);

}else{
    echo "Não Adicionou aos Favoritos";
}
