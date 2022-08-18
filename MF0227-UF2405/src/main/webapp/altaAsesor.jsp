<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alta</title>
</head>
<body>
    <h1>Alta de asesor de seguros</h1>
    <form action="AsesorServlet" method="post">
        <div>
            <label for="nombre">Nombre*:</label>
            <input type="text" name="nombre" id="nombre" required>
        </div>
        <div>
            <label for="apellidos">Apellidos*:</label>
            <input type="text" name="apellidos" id="apellidos" required>
        </div>
        <div>
            <label for="dni">DNI*:</label>
            <input type="text" name="dni" id="dni" pattern="[0-9]{8}[A-Za-z]{1}" required>
        </div>
        <div>
            <label for="contrato">Relación Contraactual:</label>
            <select name="contrato" id="contrato">
                <option value="autonomo">Autónomo</option>
                <option value="fijo">Asalariado fijo</option>
                <option value="comision">Asalariado a Comisión</option>
            </select>
        </div>
        <div>
            <label for="seguro">Tipo de seguros que vende:</label>
            <input type="radio" name="seguro" value="automovil" checked>
            <label for="automovil">Automóvil:</label>
            <input type="radio" name="seguro" value="hogar">
            <label for="hogar">Hogar:</label>
            <input type="radio" name="seguro" value="decesos">
            <label for="decesos">Decesos:</label>
        </div>
        <div>
            <label for="promedio">Num Ventas Promedio:</label>
            <input type="number" name="promedio" id="promedio" min=1 step=1 value=15>
        </div>
        <div>
            <input class="boton" type="submit" value="Confirmar">
        </div>
    </form>
</body>
</html>