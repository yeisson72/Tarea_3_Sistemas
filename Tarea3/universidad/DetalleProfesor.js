function DetalleProfesor(row) {
  d3.json("http://localhost:8089/rest/profesores/"+row.id, function(profesor) {
    var content = d3.select("#content");
    content.selectAll("div").remove();
    var div = content.append("div");
    div.append("input")
      .attr("value",profesor.id)
      .attr("id","id").
      attr("type","hidden");
    div.append("h2").text("Detalle de profesor");
    var table = div.append("table");
    var tr = table.append("tr");
    tr.append("td").text("Cedula:");
    tr.append("td").append("input")
      .attr("value",profesor.cedula)
      .attr("id","cedula");
    tr.append("td").text("Nombre:");
    tr.append("td").append("input")
      .attr("value",profesor.nombre)
      .attr("id","nombre");
    tr = table.append("tr");
    tr.append("td").text("Titulo:");
    tr.append("td").append("input")
      .attr("value",profesor.titulo)
      .attr("id","titulo");;
    tr.append("td").text("Area:");
    tr.append("td").append("input")
      .attr("value",profesor.area)
      .attr("id","area");;
    tr = table.append("tr");
    tr.append("td").text("Correo:");
    tr.append("td").append("input")
      .attr("value",profesor.correo)
      .attr("id","correo");;
    tr.append("td").text("Telefono:");
    tr.append("td").append("input")
      .attr("value",profesor.telefono)
      .attr("id","telefono");;
    div.append("input").attr("type","button")
       .attr("value","Actualizar").attr("id","button")
       .attr("onClick","ActualizarProfesor(this)");
    div.append("input").attr("type","button")
       .attr("value","Eliminar").attr("id","button")
       .attr("onClick","EliminarProfesor(this)");
  });
}