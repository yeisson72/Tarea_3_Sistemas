function ListadoProfesores() {
  d3.json("http://localhost:8089/rest/profesores", function(json) {
    var content = d3.select("#content");
    content.selectAll("div").remove();
    var div = content.append("div");
    div.append("h2").text("Listado de profesores");
    var table = div.append("table");
    var thead = table.append("thead");
    thead.append("th").text("Nombre");
    thead.append("th").text("Titulo");
    thead.append("th").text("Area");
    thead.append("th").text("Telefono");
    var tr = table.selectAll("tr")
                .data(json.profesor)
                .enter().append("tr")
                .attr("onClick","DetalleProfesor(this)");
    tr.attr("id",function(d) {return d.id;});
    tr.append("td").text(function(d) {return d.nombre;});
    tr.append("td").text(function(d) {return d.titulo;});
    tr.append("td").text(function(d) {return d.area;});
    tr.append("td").text(function(d) {return d.telefono;});
    div.append("input").attr("type","button")
       .attr("value","Agregar").attr("id","button")
       .attr("onClick","AgregarProfesor(this)");
  });
}