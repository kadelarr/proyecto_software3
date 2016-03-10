<?php

$mail="servicioalcliente@ingenieriaycampo.com";
$nombre =$_POST['userName'];
$email =$_POST['userEmail'];
$telefono =$_POST['userTel'];
$comentario =$_POST['userMsg'];
$message="
Nombre: ".$nombre."
Email: ".$email."
Telefono: ".$telefono."
Comentario:".$comentario."
";
if(mail ($mail,"Nuevo Mensaje", $message))
    
  
    header ("Location: ../contact2.html");

?>