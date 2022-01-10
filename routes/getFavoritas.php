<?php
require "conn.php";

//$idUsuario = $_POST["id"];
$idUsuario = 10;

$qry = "SELECT * FROM receitas_favoritas WHERE id_usuario = $idUsuario";

$result = mysqli_query($conn, $qry);

$nomes = array();
$descricoes = array();
$tipos = array();
$ids = array();
$imgs = array();
$ingredientes = array();
$ings = "";

if (mysqli_num_rows($result) > 0) {
    while ($row2 = mysqli_fetch_assoc($result)) {

        $id_receita = $row2["id_receita"];

        $qry2 = "SELECT * FROM receitas WHERE id = $id_receita";
        $result2 = mysqli_query($conn, $qry2);

        if (mysqli_num_rows($result2) > 0) {
            while ($row = mysqli_fetch_assoc($result2)) {
                array_push($nomes, $row["nome"]);
                $modoPreparo = $row["texto"];
                $modoPreparo = str_replace(",","...",$modoPreparo);
                array_push($descricoes, $modoPreparo);
                array_push($tipos, $row["tipo_receita"]);
                $id = $row["id"];
                array_push($ids,$id);

                $row["imagem"] = str_replace('./', '/', $row["imagem"]);

                array_push($imgs, $row["imagem"]);

                $qry = "SELECT * FROM receita_ingrediente WHERE id_receita like '$id'";
                $result2 = mysqli_query($conn, $qry);

                while ($row = mysqli_fetch_assoc($result2)) {
                    $idIngred =  $row["id_ingrediente"];

                    $qry = "SELECT * FROM ingredientes WHERE id like '$idIngred'";
                    $result3 = mysqli_query($conn, $qry);

                    while ($row = mysqli_fetch_assoc($result3)) {
                        $ings = $ings . $row["nome"] . "-";
                    }
                }

                array_push($ingredientes, $ings);
                $ings = "";
            }
        }
    }

    echo json_encode($nomes) . "<>" . json_encode($descricoes) . "<>" . json_encode($tipos) . "<>" . json_encode($ids) . "<>" . json_encode($imgs) . "<>" . json_encode($ingredientes);
} else {
    echo "Não há receitas";
}
